package electric.view;

import electric.MainApp;
import electric.model.VoltageRelay;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Created by AdminPC on 14.07.2015.
 */
public class VoltageRelayOverviewController {
    @FXML
    private TableView<VoltageRelay> voltageRelayTable;

    @FXML
    private TableColumn<VoltageRelay, String> titleColumn;
    @FXML
    private TableColumn<VoltageRelay, String> manufacturerColumn;

    @FXML
    private TextField typeField;

    @FXML
    private TextField countPhaseField;
    @FXML
    private TextField ncContactsField;
    @FXML
    private TextField noContactsField;

    @FXML
    private TextField ratedCoilVoltageField;
    @FXML
    private TextField ratedCurrentField;
    @FXML
    private TextField maxCurrentField;
    @FXML
    private TextField ratedVoltageField;
    @FXML
    private TextField maxVoltageField;
    @FXML
    private TextField ratedPowerField;

    @FXML
    private TextArea descriptionArea;


    private MainApp mainApp;

    private ObservableList<VoltageRelay> voltageRelayData;

    public VoltageRelayOverviewController() {

    }

    @FXML
    private void initialize() {

        // Initialize  table with the two columns.
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        manufacturerColumn.setCellValueFactory(cellData -> cellData.getValue().manufacturerTitleProperty());

        // Clear details.
        showVoltageRelayDetails(null);

        // Listen for selection changes and show the m details when changed.
        voltageRelayTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showVoltageRelayDetails(newValue));

    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void callbackGetData() {
        // Add observable list data to the table
        if(voltageRelayData == null) {
            voltageRelayData = mainApp.getVoltageRelayData();
            voltageRelayTable.setItems(voltageRelayData);
        }
    }

    private void showVoltageRelayDetails(VoltageRelay vr) {
        if (vr != null) {
            // Fill the labels with info from the object.
            typeField.setText(vr.getMounting());

            int cp = vr.getCountPhase();
            int nc = vr.getNumberNcContacts();
            int no = vr.getNumberNoContacts();

            double rcv = vr.getRatedCoilVoltage();
            double rc = vr.getRatedCurrent();

            double mc = vr.getMaxCurrent();
            double rv = vr.getRatedVoltage();
            double mv = vr.getMaxVoltage();
            double rp = vr.getRatedPower();

            countPhaseField.setText(cp > 0 ? String.valueOf(cp) : "N/A");
            ncContactsField.setText(nc > 0 ? String.valueOf(nc) : "N/A");
            noContactsField.setText(no > 0 ? String.valueOf(no) : "N/A");

            ratedCoilVoltageField.setText(rcv > 0 ? String.valueOf(rcv) : "N/A");
            ratedCurrentField.setText(rc > 0 ? String.valueOf(rc) : "N/A");
            maxCurrentField.setText(mc > 0 ? String.valueOf(mc) : "N/A");
            ratedVoltageField.setText(rv > 0 ? String.valueOf(rv) : "N/A");
            maxVoltageField.setText(mv > 0 ? String.valueOf(mv) : "N/A");
            ratedPowerField.setText(rp > 0 ? String.valueOf(rp) : "N/A");

            descriptionArea.setText(vr.getDescription());

        } else {
            // VoltageRelay is null, remove all the text.
            typeField.setText("");
            countPhaseField.setText("");
            ncContactsField.setText("");
            noContactsField.setText("");

            ratedCoilVoltageField.setText("");
            ratedCurrentField.setText("");
            maxCurrentField.setText("");
            ratedVoltageField.setText("");
            maxVoltageField.setText("");
            ratedPowerField.setText("");

            descriptionArea.setText("");
        }
    }

    @FXML
    private void handleDeleteVoltageRelay() {
        int selectedIndex = voltageRelayTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {

            VoltageRelay vr = voltageRelayData.get(selectedIndex);

            if(mainApp.getDb().delete(vr)) {
                voltageRelayData.remove(selectedIndex);
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка, невозможно удалить");
                alert.setHeaderText("Ошибка удаления в базе данных");
                alert.setContentText("Повторите удаление или обратитись к поставщику");

                alert.showAndWait();
            }


        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка, невозможно удалить");
            alert.setHeaderText("Не выбрано реле для удаления");
            alert.setContentText("Выберите реле и нажмите на кнопку \"Удалить\"");

            alert.showAndWait();
        }

    }

    @FXML
    private void handleNewVoltageRelay() {
        VoltageRelay tempVoltageRelay = new VoltageRelay(0,"",0,"",0,"",-1,-1,-1,-1d,-1d,-1d,-1d,-1d,-1d,"");

        boolean okClicked = mainApp.showVoltageRelayEditDialog(tempVoltageRelay);
        if (okClicked) {
            //add to db
            mainApp.getDb().insert(tempVoltageRelay);
            if(tempVoltageRelay.getId() > 0) {
                mainApp.getVoltageRelayData().add(tempVoltageRelay);
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка, невозможно добавить запись");
                alert.setHeaderText("Ошибка добавления в базу данных");
                alert.setContentText("Повторите, возможно некоторые данные неккоректны!");

                alert.showAndWait();
            }
        }
    }

    @FXML
    private void handleEditVoltageRelay() {
        VoltageRelay selected = voltageRelayTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            boolean okClicked = mainApp.showVoltageRelayEditDialog(selected);
            if (okClicked) {
                showVoltageRelayDetails(selected);
                // update in db
                if(mainApp.getDb().update(selected)) {
                    // ok
                    // возможно необходимо обработать
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка, невозможно обновить запись");
                    alert.setHeaderText("Ошибка обновления");
                    alert.setContentText("Повторите, возможно некоторые данные неккоректны!");

                    alert.showAndWait();
                }

            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка, невозможно обновить запись");
            alert.setHeaderText("Ошибка обновления");
            alert.setContentText("Выберите из списка реле и повторите обновление!");

            alert.showAndWait();
        }
    }

}

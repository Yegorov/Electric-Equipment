package electric.view;

import electric.MainApp;
import electric.model.Manufacturer;
import electric.model.Record;
import electric.model.VoltageRelay;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.stream.Collectors;

/**
 * Created by AdminPC on 14.07.2015.
 */
public class VoltageRelayEditDialogController {
    @FXML
    private TextField titleField;

    @FXML
    private ComboBox<String> manufacturerBox;
    @FXML
    private ComboBox<String> typeBox;


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


    private Stage dialogStage;
    private VoltageRelay voltageRelay;
    private boolean okClicked = false;

    private MainApp mainApp;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        manufacturerBox.setItems(FXCollections.observableArrayList(
                mainApp.getDb().selectAllManufacturer()
                        .stream()
                        .map(Manufacturer::getTitle)
                        .collect(Collectors.toList())));


        typeBox.setItems(FXCollections.observableArrayList(
                mainApp.getDb().selectAllRecord("types_mounting")
                        .stream()
                        .map(Record::getTitle)
                        .collect(Collectors.toList())));
    }

    /**
     * Sets the VoltageRelay to be edited in the dialog.
     */
    public void setVoltageRelay(VoltageRelay vr) {
        this.voltageRelay = vr;

        titleField.setText(vr.getTitle());

        countPhaseField.setText(String.valueOf(vr.getCountPhase()));
        ncContactsField.setText(String.valueOf(vr.getNumberNcContacts()));
        noContactsField.setText(String.valueOf(vr.getNumberNoContacts()));


        ratedCoilVoltageField.setText(String.valueOf(vr.getRatedCoilVoltage()));
        ratedCurrentField.setText(String.valueOf(vr.getRatedCurrent()));
        maxCurrentField.setText(String.valueOf(vr.getMaxCurrent()));
        ratedVoltageField.setText(String.valueOf(vr.getRatedVoltage()));
        maxVoltageField.setText(String.valueOf(vr.getMaxVoltage()));
        ratedPowerField.setText(String.valueOf(vr.getRatedPower()));

        descriptionArea.setText(vr.getDescription());

        manufacturerBox.getSelectionModel().select(vr.getManufacturerTitle());

        if(manufacturerBox.getSelectionModel().getSelectedItem().isEmpty())
            manufacturerBox.getSelectionModel().selectFirst();


        typeBox.getSelectionModel().select(vr.getMounting());

        if(typeBox.getSelectionModel().getSelectedItem().isEmpty())
            typeBox.getSelectionModel().selectFirst();

    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {

            voltageRelay.setTitle(titleField.getText());


            voltageRelay.setCountPhase(Integer.parseInt(countPhaseField.getText()));
            voltageRelay.setNumberNcContacts(Integer.parseInt(ncContactsField.getText()));
            voltageRelay.setNumberNoContacts(Integer.parseInt(noContactsField.getText()));

            voltageRelay.setRatedCoilVoltage(Double.parseDouble(ratedCoilVoltageField.getText()));
            voltageRelay.setRatedCurrent(Double.parseDouble(ratedCurrentField.getText()));
            voltageRelay.setMaxCurrent(Double.parseDouble(maxCurrentField.getText()));
            voltageRelay.setRatedVoltage(Double.parseDouble(ratedVoltageField.getText()));
            voltageRelay.setMaxVoltage(Double.parseDouble(maxVoltageField.getText()));
            voltageRelay.setRatedPower(Double.parseDouble(ratedPowerField.getText()));

            String manufacturer = manufacturerBox.getValue();
            Manufacturer m = mainApp.getDb().selectAllManufacturer().stream()
                    .filter(cur -> cur.getTitle().contentEquals(manufacturer))
                    .findFirst()
                    .get();

            voltageRelay.setManufacturerTitle(manufacturer);
            voltageRelay.setIdManufacturer(m.getId());


            String type = typeBox.getValue();
            Record t = mainApp.getDb().selectAllRecord("types_mounting").stream()
                    .filter(cur -> cur.getTitle().contentEquals(type))
                    .findFirst()
                    .get();

            voltageRelay.setMounting(type);
            voltageRelay.setIdMounting(t.getId());

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (titleField.getText() == null || titleField.getText().length() == 0) {
            errorMessage += "Не заполнено название!\n";
        }

        if (descriptionArea.getText() == null || descriptionArea.getText().length() == 0) {
            errorMessage += "Не заполнено описание!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        }
        else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка, неверно заполнены поля");
            alert.setHeaderText("Введите корректные значения");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

}

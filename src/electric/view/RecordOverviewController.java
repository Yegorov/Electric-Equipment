package electric.view;

import electric.MainApp;
import electric.model.Record;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Map;

/**
 * Created by AdminPC on 13.07.2015.
 */
public class RecordOverviewController {

    @FXML
    private TableView<Record> recordTable;

    @FXML
    private TableColumn<Record, String> titleColumn;

    @FXML
    private ListView<String> tableNameList;

    @FXML
    private TextField titleField;

    private MainApp mainApp;


    private ObservableList<String> listViewData = FXCollections.observableArrayList();;
    private Map<String, ObservableList<Record>> recordsData;
    private Map<String, String> refNames;


    public RecordOverviewController() {

    }

    @FXML
    private void initialize() {

        // Initialize  table with the two columns.
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());


        // Clear details.
        showRecordDetails(null);

        // Listen for selection changes and show the details when changed.
        recordTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showRecordDetails(newValue));

        tableNameList.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    changeTable(newValue);
                });

    }
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

    }

    public void showRecordDetails(Record r) {
        if(r != null) {
            titleField.setText(r.getTitle());
        }
        else {
            titleField.setText("");
        }
    }


    public void callbackGetData() {
        // Add observable list data to the table
        if(recordsData == null) {
            recordsData = mainApp.getRecordsData();
            refNames = mainApp.getRefNames();
            for(String s : refNames.keySet()) {
                listViewData.add(s);
            }
            tableNameList.setItems(listViewData);
        }
    }

    private void changeTable(String t) {
        recordTable.setItems(recordsData.get(refNames.get(t)));
    }

    @FXML
    private void handleDeleteRecord() {
        int selectedIndex = recordTable.getSelectionModel().getSelectedIndex();
        String nameTable = refNames.get(tableNameList.getSelectionModel().getSelectedItem());

        if (selectedIndex >= 0) {

            Record r = recordsData.get(nameTable).get(selectedIndex);

            if(mainApp.getDb().delete(r, nameTable)) {
                recordsData.get(nameTable).remove(selectedIndex);
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
            alert.setHeaderText("Не выбрано названия для удаления");
            alert.setContentText("Выберите название и нажмите на кнопку \"Удалить\"");

            alert.showAndWait();
        }

    }

    @FXML
    private void handleNewRecord() {
        Record tempRecord = new Record(0, "");
        String nameTable = refNames.get(tableNameList.getSelectionModel().getSelectedItem());

        tempRecord.setTitle(titleField.getText());
        //add to db
        mainApp.getDb().insert(tempRecord, nameTable);
        if(tempRecord.getId() > 0) {
            mainApp.getRecordsData().get(nameTable).add(tempRecord);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка, невозможно добавить запись");
            alert.setHeaderText("Ошибка добавления в базу данных");
            alert.setContentText("Повторите, возможно некоторые данные неккоректны!");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleEditRecord() {
        Record selected = recordTable.getSelectionModel().getSelectedItem();
        String nameTable = refNames.get(tableNameList.getSelectionModel().getSelectedItem());

        if (selected != null) {

            selected.setTitle(titleField.getText());

            showRecordDetails(selected);
            // update in db
            if(mainApp.getDb().update(selected, nameTable)) {
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

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка, невозможно обновить запись");
            alert.setHeaderText("Ошибка обновления");
            alert.setContentText("Выберите из списка название и повторите обновление!");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleClearTitle() {
        titleField.setText("");
    }


}

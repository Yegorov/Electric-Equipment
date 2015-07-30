package electric.view;

import electric.MainApp;
import electric.model.Country;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Created by AdminPC on 13.07.2015.
 */
public class CountryOverviewController {

    @FXML
    private TableView<Country> countryTable;

    @FXML
    private TableColumn<Country, String> countryColumn;

    @FXML
    private TableColumn<Country, String> countryCodeColumn;

    @FXML
    private TextField countryField;

    @FXML
    private TextField countryCodeField;


    private MainApp mainApp;

    private ObservableList<Country> countryData;


    public CountryOverviewController() {

    }

    @FXML
    private void initialize() {

        // Initialize  table with the two columns.
        countryColumn.setCellValueFactory(cellData -> cellData.getValue().countryProperty());
        countryCodeColumn.setCellValueFactory(cellData -> cellData.getValue().countryCodeProperty());

        // Clear details.
        showCountryDetails(null);

        // Listen for selection changes and show the m details when changed.
        countryTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showCountryDetails(newValue));


    }
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

    }

    public void callbackGetData() {
        // Add observable list data to the table
        if(countryData == null) {
            countryData = mainApp.getCountryData();
            countryTable.setItems(countryData);
        }
    }


    private void showCountryDetails(Country c) {
        if(c != null) {
            countryField.setText(c.getCountry());
            countryCodeField.setText(c.getCountryCode());
        }
        else {
            countryField.setText("");
            countryCodeField.setText("");
        }
    }


    @FXML
    private void handleDeleteCountry() {
        int selectedIndex = countryTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {

            Country c = countryData.get(selectedIndex);

            if(mainApp.getDb().delete(c)) {
                countryData.remove(selectedIndex);
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
            alert.setHeaderText("Не выбрано страны для удаления");
            alert.setContentText("Выберите название страны и нажмите на кнопку \"Удалить\"");

            alert.showAndWait();
        }

    }

    @FXML
    private void handleNewCountry() {
        Country tempCountry = new Country(0, "", "");
        tempCountry.setCountry(countryField.getText());
        tempCountry.setCountryCode(countryCodeField.getText());
        //add to db
        mainApp.getDb().insert(tempCountry);
        if(tempCountry.getId() > 0) {
            mainApp.getCountryData().add(tempCountry);
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
    private void handleEditCountry() {
        Country selected = countryTable.getSelectionModel().getSelectedItem();
        if (selected != null) {

            selected.setCountry(countryField.getText());
            selected.setCountryCode(countryCodeField.getText());

            showCountryDetails(selected);
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

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка, невозможно обновить запись");
            alert.setHeaderText("Ошибка обновления");
            alert.setContentText("Выберите из списка страну и повторите обновление!");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleClearCountry() {
        countryField.setText("");
    }

    @FXML
    private void handleClearCountryCode() {
        countryCodeField.setText("");
    }

}

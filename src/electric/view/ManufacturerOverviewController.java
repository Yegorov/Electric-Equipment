package electric.view;

import com.sun.deploy.uitoolkit.impl.fx.HostServicesFactory;
import com.sun.javafx.application.HostServicesDelegate;
import electric.MainApp;
import electric.model.Manufacturer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Created by AdminPC on 13.07.2015.
 */
public class ManufacturerOverviewController {
    @FXML
    private TextField searchField;

    @FXML
    private TableView<Manufacturer> manufacturerTable;

    @FXML
    private TableColumn<Manufacturer, String> titleColumn;
    @FXML
    private TableColumn<Manufacturer, String> countryColumn;

    @FXML
    private TextField titleField;
    @FXML
    private TextField countryField;
    @FXML
    private Hyperlink siteLink;

    @FXML
    private ListView<String> contactsListView;


    @FXML
    private TextArea descriptionArea;


    private MainApp mainApp;

    private ObservableList<Manufacturer> manufacturerData;

    public ManufacturerOverviewController() {

    }

    @FXML
    private void initialize() {

        // Initialize  table with the two columns.
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        countryColumn.setCellValueFactory(cellData -> cellData.getValue().countryCodeProperty());

        // Clear details.
        showManufacturerDetails(null);

        // Listen for selection changes and show the m details when changed.
        manufacturerTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showManufacturerDetails(newValue));

        siteLink.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HostServicesDelegate hostServices = HostServicesFactory.getInstance(ManufacturerOverviewController.this.mainApp);
                hostServices.showDocument(siteLink.getText());
            }
        });

    }

    public void setFilterList() {
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Manufacturer> filteredData = new FilteredList<>(manufacturerData, p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(m -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (m.getTitle().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (m.getCountry().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Manufacturer> sortedData = new SortedList<>(filteredData);
        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(manufacturerTable.comparatorProperty());
        // 5. Add sorted (and filtered) data to the table.
        manufacturerTable.setItems(sortedData);
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        manufacturerData = mainApp.getManufacturerData();
        setFilterList();
    }

    private void showManufacturerDetails(Manufacturer m) {
        if (m != null) {
            // Fill the labels with info from the person object.
            titleField.setText(m.getTitle());
            countryField.setText(m.getCountryWithCode());

            descriptionArea.setText(m.getDescription());
            siteLink.setText(m.getSite());

            contactsListView.setItems(FXCollections.observableArrayList(m.getContacts()));

        } else {
            // Manufacturer is null, remove all the text.
            titleField.setText("");
            countryField.setText("");
            descriptionArea.setText("");
            siteLink.setText("");
            contactsListView.getItems().clear();
        }
    }

    @FXML
    private void handleDeleteManufacturer() {
        int selectedIndex = manufacturerTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {

            //Manufacturer m = manufacturerTable.getItems().remove(selectedIndex);
            Manufacturer m = manufacturerData.get(selectedIndex);

            if(mainApp.getDb().delete(m)) {
                manufacturerData.remove(selectedIndex);
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
            alert.setHeaderText("Не выбрано производителя для удаления");
            alert.setContentText("Выберите производителя и нажмите на кнопку \"Удалить\"");

            alert.showAndWait();
        }

    }

    @FXML
    private void handleNewManufacturer() {
        Manufacturer tempManufacturer = new Manufacturer(0, "", 0, "", "", "", "", "", "");
        boolean okClicked = mainApp.showManufacturerEditDialog(tempManufacturer);
        if (okClicked) {
            //add to db
            mainApp.getDb().insert(tempManufacturer);
            if(tempManufacturer.getId() > 0) {
                mainApp.getManufacturerData().add(tempManufacturer);
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
    private void handleEditManufacturer() {
        Manufacturer selected = manufacturerTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            boolean okClicked = mainApp.showManufacturerEditDialog(selected);
            if (okClicked) {
                showManufacturerDetails(selected);
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
            alert.setContentText("Выберите из списка производителя и повторите обновление!");

            alert.showAndWait();
        }
    }

}

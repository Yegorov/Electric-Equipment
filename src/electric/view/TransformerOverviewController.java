package electric.view;

import electric.MainApp;
import electric.model.Transformer;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Created by AdminPC on 13.07.2015.
 */
public class TransformerOverviewController {
    @FXML
    private TableView<Transformer> transformerTable;

    @FXML
    private TableColumn<Transformer, String> titleColumn;
    @FXML
    private TableColumn<Transformer, String> manufacturerColumn;

    @FXML
    private TextField categoryField;
    @FXML
    private TextField typeField;
    @FXML
    private TextField coresField;
    @FXML
    private TextField coolingField;
    @FXML
    private TextField materialsField;
    @FXML
    private TextField windingField;

    @FXML
    private TextField countWindingField;
    @FXML
    private TextField countPhaseField;
    @FXML
    private TextField ratedCurrentField;
    @FXML
    private TextField primaryField;
    @FXML
    private TextField secondaryField;

    @FXML
    private TextArea descriptionArea;


    private MainApp mainApp;

    private ObservableList<Transformer> transformerData;

    public TransformerOverviewController() {

    }


    @FXML
    private void initialize() {

        // Initialize  table with the two columns.
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        manufacturerColumn.setCellValueFactory(cellData -> cellData.getValue().manufacturerTitleProperty());

        // Clear details.
        showTransformerDetails(null);

        // Listen for selection changes and show the m details when changed.
        transformerTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showTransformerDetails(newValue));

    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void callbackGetData() {
        // Add observable list data to the table
        if(transformerData == null) {
            transformerData = mainApp.getTransformerData();
            transformerTable.setItems(transformerData);
        }
    }

    private void showTransformerDetails(Transformer t) {
        if (t != null) {
            // Fill the labels with info from the person object.
            categoryField.setText(t.getCategory());
            typeField.setText(t.getType());
            coresField.setText(t.getCore());
            coolingField.setText(t.getCooling());
            materialsField.setText(t.getMaterialWinding());
            windingField.setText(t.getWindingConfiguration());


            int cv = t.getCountWinding();
            int cp = t.getCountPhase();
            double rc = t.getRatedCurrent();
            double p = t.getPrimaryWindingVoltage();
            double s = t.getSecondaryWindingVoltage();

            countWindingField.setText(cv > 0 ? String.valueOf(cv) : "N/A");
            countPhaseField.setText(cp > 0 ? String.valueOf(cp) : "N/A");
            ratedCurrentField.setText(rc > 0 ? String.valueOf(rc) : "N/A");
            primaryField.setText(p > 0 ? String.valueOf(p) : "N/A");
            secondaryField.setText(s > 0 ? String.valueOf(s) : "N/A");


            descriptionArea.setText(t.getDescription());

        } else {
            // Transformer is null, remove all the text.
            categoryField.setText("");
            typeField.setText("");
            coresField.setText("");
            coolingField.setText("");
            materialsField.setText("");
            windingField.setText("");

            countWindingField.setText("");
            countPhaseField.setText("");
            ratedCurrentField.setText("");
            primaryField.setText("");
            secondaryField.setText("");

            descriptionArea.setText("");
        }
    }

    @FXML
    private void handleDeleteTransformer() {
        int selectedIndex = transformerTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {

            Transformer t = transformerData.get(selectedIndex);

            if(mainApp.getDb().delete(t)) {
                transformerData.remove(selectedIndex);
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
            alert.setHeaderText("Не выбрано трансформатора для удаления");
            alert.setContentText("Выберите трансформатор и нажмите на кнопку \"Удалить\"");

            alert.showAndWait();
        }

    }

    @FXML
    private void handleNewTransformer() {
        Transformer tempTransformer = new Transformer(0,"",1,"",1,"",1,"",1,"",1,"",1,"",1,"",-1,-1,-1d,-1d,-1d,"");

        boolean okClicked = mainApp.showTransformerEditDialog(tempTransformer);
        if (okClicked) {
            //add to db
            mainApp.getDb().insert(tempTransformer);
            if(tempTransformer.getId() > 0) {
                mainApp.getTransformerData().add(tempTransformer);
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
    private void handleEditTransformer() {
        Transformer selected = transformerTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            boolean okClicked = mainApp.showTransformerEditDialog(selected);
            if (okClicked) {
                showTransformerDetails(selected);
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
            alert.setContentText("Выберите из списка трансформатор и повторите обновление!");

            alert.showAndWait();
        }
    }

}

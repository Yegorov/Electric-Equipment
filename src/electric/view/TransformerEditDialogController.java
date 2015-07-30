package electric.view;

import electric.MainApp;
import electric.model.Manufacturer;
import electric.model.Record;
import electric.model.Transformer;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.stream.Collectors;

/**
 * Created by AdminPC on 13.07.2015.
 */
public class TransformerEditDialogController {
    @FXML
    private TextField titleField;

    @FXML
    private ComboBox<String> manufacturerBox;

    @FXML
    private ComboBox<String> categoryBox;

    @FXML
    private ComboBox<String> typeBox;

    @FXML
    private ComboBox<String> coresBox;

    @FXML
    private ComboBox<String> coolingBox;

    @FXML
    private ComboBox<String> materialsBox;

    @FXML
    private ComboBox<String> windingBox;

    @FXML
    private TextField countWindingField;

    @FXML
    private TextField countPhaseField;

    @FXML
    private TextField primaryField;

    @FXML
    private TextField secondaryField;

    @FXML
    private TextField ratedCurrentField;

    @FXML
    private TextArea descriptionArea;


    private Stage dialogStage;
    private Transformer transformer;
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

        categoryBox.setItems(FXCollections.observableArrayList(
                mainApp.getDb().selectAllRecord("categories_transformers")
                        .stream()
                        .map(Record::getTitle)
                        .collect(Collectors.toList())));

        typeBox.setItems(FXCollections.observableArrayList(
                mainApp.getDb().selectAllRecord("types_transformers")
                        .stream()
                        .map(Record::getTitle)
                        .collect(Collectors.toList())));

        coresBox.setItems(FXCollections.observableArrayList(
                mainApp.getDb().selectAllRecord("cores")
                        .stream()
                        .map(Record::getTitle)
                        .collect(Collectors.toList())));

        coolingBox.setItems(FXCollections.observableArrayList(
                mainApp.getDb().selectAllRecord("cooling_types")
                        .stream()
                        .map(Record::getTitle)
                        .collect(Collectors.toList())));

        materialsBox.setItems(FXCollections.observableArrayList(
                mainApp.getDb().selectAllRecord("materials")
                        .stream()
                        .map(Record::getTitle)
                        .collect(Collectors.toList())));

        windingBox.setItems(FXCollections.observableArrayList(
                mainApp.getDb().selectAllRecord("winding_configuration")
                        .stream()
                        .map(Record::getTitle)
                        .collect(Collectors.toList())));
    }

    /**
     * Sets the transformer to be edited in the dialog.
     */
    public void setTransformer(Transformer t) {
        this.transformer = t;

        titleField.setText(t.getTitle());

        countWindingField.setText(String.valueOf(t.getCountWinding()));
        countPhaseField.setText(String.valueOf(t.getCountPhase()));
        primaryField.setText(String.valueOf(t.getPrimaryWindingVoltage()));
        secondaryField.setText(String.valueOf(t.getSecondaryWindingVoltage()));
        ratedCurrentField.setText(String.valueOf(t.getRatedCurrent()));

        descriptionArea.setText(t.getDescription());

        manufacturerBox.getSelectionModel().select(t.getManufacturerTitle());

        if(manufacturerBox.getSelectionModel().getSelectedItem().isEmpty())
            manufacturerBox.getSelectionModel().selectFirst();

        categoryBox.getSelectionModel().select(t.getCategory());

        if(categoryBox.getSelectionModel().getSelectedItem().isEmpty())
            categoryBox.getSelectionModel().selectFirst();

        typeBox.getSelectionModel().select(t.getType());

        if(typeBox.getSelectionModel().getSelectedItem().isEmpty())
            typeBox.getSelectionModel().selectFirst();

        coresBox.getSelectionModel().select(t.getCore());

        if(coresBox.getSelectionModel().getSelectedItem().isEmpty())
            coresBox.getSelectionModel().selectFirst();

        coolingBox.getSelectionModel().select(t.getCooling());

        if(coolingBox.getSelectionModel().getSelectedItem().isEmpty())
            coolingBox.getSelectionModel().selectFirst();

        materialsBox.getSelectionModel().select(t.getMaterialWinding());

        if(materialsBox.getSelectionModel().getSelectedItem().isEmpty())
            materialsBox.getSelectionModel().selectFirst();

        windingBox.getSelectionModel().select(t.getWindingConfiguration());

        if(windingBox.getSelectionModel().getSelectedItem().isEmpty())
            windingBox.getSelectionModel().selectFirst();

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

            transformer.setTitle(titleField.getText());
            transformer.setCountWinding(Integer.parseInt(countWindingField.getText()));
            transformer.setCountPhase(Integer.parseInt(countPhaseField.getText()));
            transformer.setPrimaryWindingVoltage(Double.parseDouble(primaryField.getText()));
            transformer.setSecondaryWindingVoltage(Double.parseDouble(secondaryField.getText()));
            transformer.setRatedCurrent(Double.parseDouble(ratedCurrentField.getText()));
            transformer.setDescription(descriptionArea.getText());


            String manufacturer = manufacturerBox.getValue();
            Manufacturer m = mainApp.getDb().selectAllManufacturer().stream()
                    .filter(cur -> cur.getTitle().contentEquals(manufacturer))
                    .findFirst()
                    .get();

            transformer.setManufacturerTitle(manufacturer);
            transformer.setIdManufacturer(m.getId());


            String category = categoryBox.getValue();
            Record c = mainApp.getDb().selectAllRecord("categories_transformers").stream()
                    .filter(cur -> cur.getTitle().contentEquals(category))
                    .findFirst()
                    .get();

            transformer.setCategory(category);
            transformer.setIdCategory(c.getId());

            String type = typeBox.getValue();
            Record t = mainApp.getDb().selectAllRecord("types_transformers").stream()
                    .filter(cur -> cur.getTitle().contentEquals(type))
                    .findFirst()
                    .get();

            transformer.setType(type);
            transformer.setIdType(t.getId());


            String cores = coresBox.getValue();
            Record cc = mainApp.getDb().selectAllRecord("cores").stream()
                    .filter(cur -> cur.getTitle().contentEquals(cores))
                    .findFirst()
                    .get();

            transformer.setCore(cores);
            transformer.setIdCore(cc.getId());

            String cooling = coolingBox.getValue();
            Record coo = mainApp.getDb().selectAllRecord("cooling_types").stream()
                    .filter(cur -> cur.getTitle().contentEquals(cooling))
                    .findFirst()
                    .get();

            transformer.setCooling(cooling);
            transformer.setIdCooling(coo.getId());

            String materials = materialsBox.getValue();
            Record mat = mainApp.getDb().selectAllRecord("materials").stream()
                    .filter(cur -> cur.getTitle().contentEquals(materials))
                    .findFirst()
                    .get();

            transformer.setMaterialWinding(materials);
            transformer.setIdMaterialWinding(mat.getId());


            String winding = windingBox.getValue();
            Record wi = mainApp.getDb().selectAllRecord("winding_configuration").stream()
                    .filter(cur -> cur.getTitle().contentEquals(winding))
                    .findFirst()
                    .get();

            transformer.setWindingConfiguration(winding);
            transformer.setIdWindingConfiguration(wi.getId());

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

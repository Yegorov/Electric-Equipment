package electric;

import electric.model.*;
import electric.model.sql.DatabaseHandler;
import electric.view.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainApp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    private DatabaseHandler db;

    private Map<String, ObservableList<Record>> refTable;
    private Map<String, String> refNames;

    private ObservableList<Manufacturer> manufacturerData = FXCollections.observableArrayList();
    private ObservableList<Country> countryData = FXCollections.observableArrayList();
    private ObservableList<Transformer> transformerData = FXCollections.observableArrayList();
    private ObservableList<VoltageRelay> voltageRelayData = FXCollections.observableArrayList();

    private CountryOverviewController countryController;
    private RecordOverviewController recordController;
    private TransformerOverviewController transformerController;
    private VoltageRelayOverviewController voltageRelayController;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Electric equipment");
        this.primaryStage.setMinHeight(600);
        this.primaryStage.setMinWidth(800);

        initRootLayout();

        showOverview();

    }

    private void initRootLayout()  throws Exception {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));

            rootLayout = (BorderPane) loader.load();
            ((TabPane)rootLayout.getCenter())
                    .getSelectionModel()
                    .selectedItemProperty()
                    .addListener((observable, oldValue, newValue) -> {
                        if(newValue.getText().contentEquals("Страны")) {
                            countryController.callbackGetData();
                        }
                        else if (newValue.getText().contentEquals("Трансформаторы")) {
                            transformerController.callbackGetData();
                        }
                        else if (newValue.getText().contentEquals("Реле")) {
                            voltageRelayController.callbackGetData();
                        }
                        else if (newValue.getText().contentEquals("Доп. таблицы")) {
                            recordController.callbackGetData();
                        }
                    });

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showOverview() {
        try {
            FXMLLoader manufacturerLoader = new FXMLLoader();
            manufacturerLoader.setLocation(MainApp.class.getResource("view/ManufacturerOverview.fxml"));
            AnchorPane anchorManufacturer = (AnchorPane) manufacturerLoader.load();
            Tab tabManufacturer = ((TabPane)rootLayout.getCenter()).getTabs().get(0);

            AnchorPane.setTopAnchor(anchorManufacturer, 0.0);
            AnchorPane.setBottomAnchor(anchorManufacturer, 0.0);
            AnchorPane.setLeftAnchor(anchorManufacturer, 0.0);
            AnchorPane.setRightAnchor(anchorManufacturer, 0.0);

            ((AnchorPane)tabManufacturer.getContent()).getChildren().setAll(anchorManufacturer);

            ManufacturerOverviewController manufacturerController = manufacturerLoader.getController();
            manufacturerController.setMainApp(this);

            //-------------

            FXMLLoader countryLoader = new FXMLLoader();
            countryLoader.setLocation(MainApp.class.getResource("view/CountryOverview.fxml"));
            AnchorPane anchorCountry = (AnchorPane) countryLoader.load();
            Tab tabCountry = ((TabPane)rootLayout.getCenter()).getTabs().get(1);

            AnchorPane.setTopAnchor(anchorCountry, 0.0);
            AnchorPane.setBottomAnchor(anchorCountry, 0.0);
            AnchorPane.setLeftAnchor(anchorCountry, 0.0);
            AnchorPane.setRightAnchor(anchorCountry, 0.0);

            ((AnchorPane)tabCountry.getContent()).getChildren().setAll(anchorCountry);

            this.countryController = countryLoader.getController();
            this.countryController.setMainApp(this);

            //--------------

            FXMLLoader recordLoader = new FXMLLoader();
            recordLoader.setLocation(MainApp.class.getResource("view/RecordOverview.fxml"));
            AnchorPane anchorRecord = (AnchorPane) recordLoader.load();
            Tab tabRecord = ((TabPane)rootLayout.getCenter()).getTabs().get(4);

            AnchorPane.setTopAnchor(anchorRecord, 0.0);
            AnchorPane.setBottomAnchor(anchorRecord, 0.0);
            AnchorPane.setLeftAnchor(anchorRecord, 0.0);
            AnchorPane.setRightAnchor(anchorRecord, 0.0);

            ((AnchorPane)tabRecord.getContent()).getChildren().setAll(anchorRecord);

            this.recordController = recordLoader.getController();
            this.recordController.setMainApp(this);

            //--------------

            FXMLLoader transformerLoader = new FXMLLoader();
            transformerLoader.setLocation(MainApp.class.getResource("view/TransformerOverview.fxml"));
            AnchorPane anchorTransformer = (AnchorPane) transformerLoader.load();
            Tab tabTransformer = ((TabPane)rootLayout.getCenter()).getTabs().get(2);

            AnchorPane.setTopAnchor(anchorTransformer, 0.0);
            AnchorPane.setBottomAnchor(anchorTransformer, 0.0);
            AnchorPane.setLeftAnchor(anchorTransformer, 0.0);
            AnchorPane.setRightAnchor(anchorTransformer, 0.0);

            ((AnchorPane)tabTransformer.getContent()).getChildren().setAll(anchorTransformer);

            this.transformerController = transformerLoader.getController();
            this.transformerController.setMainApp(this);

            //--------------

            FXMLLoader voltageRelayLoader = new FXMLLoader();
            voltageRelayLoader.setLocation(MainApp.class.getResource("view/VoltageRelayOverview.fxml"));
            AnchorPane anchorVoltageRelay = (AnchorPane) voltageRelayLoader.load();
            Tab tabVoltageRelay = ((TabPane)rootLayout.getCenter()).getTabs().get(3);

            AnchorPane.setTopAnchor(anchorVoltageRelay, 0.0);
            AnchorPane.setBottomAnchor(anchorVoltageRelay, 0.0);
            AnchorPane.setLeftAnchor(anchorVoltageRelay, 0.0);
            AnchorPane.setRightAnchor(anchorVoltageRelay, 0.0);

            ((AnchorPane)tabVoltageRelay.getContent()).getChildren().setAll(anchorVoltageRelay);

            this.voltageRelayController = voltageRelayLoader.getController();
            this.voltageRelayController.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Stage getPrimaryStage() {
        return this.primaryStage;
    }

    public MainApp() {
        refTable = new HashMap<>(7);
        refTable.put("categories_transformers", FXCollections.observableArrayList());
        refTable.put("types_transformers", FXCollections.observableArrayList());
        refTable.put("cores", FXCollections.observableArrayList());
        refTable.put("cooling_types", FXCollections.observableArrayList());
        refTable.put("materials", FXCollections.observableArrayList());
        refTable.put("winding_configuration", FXCollections.observableArrayList());
        refTable.put("types_mounting", FXCollections.observableArrayList());

        refNames = new HashMap<>(7);
        refNames.put("Категория трансформатора", "categories_transformers");
        refNames.put("Тип трансформатора", "types_transformers");
        refNames.put("Конструктивное исполнение", "cores");
        refNames.put("Способ охлаждения обмоток", "cooling_types");
        refNames.put("Материал обмотки", "materials");
        refNames.put("Соединения обмоток", "winding_configuration");
        refNames.put("Тип монтажа", "types_mounting");


        db = new DatabaseHandler();
        db.connect();
    }

    public DatabaseHandler getDb() {
        return db;
    }

    public ObservableList<Manufacturer> getManufacturerData() {
        if(manufacturerData.isEmpty()) {
            manufacturerData.setAll(db.selectAllManufacturer());
        }
        return manufacturerData;
    }

    public ObservableList<Country> getCountryData() {
        if(countryData.isEmpty()) {
            countryData.setAll(db.selectAllCountry());
        }
        return countryData;
    }

    public ObservableList<Transformer> getTransformerData() {
        if(transformerData.isEmpty()) {
            transformerData.setAll(db.selectAllTransformer());
        }
        return transformerData;
    }

    public ObservableList<VoltageRelay> getVoltageRelayData() {
        if(voltageRelayData.isEmpty()) {
            voltageRelayData.setAll(db.selectAllVoltageRelay());
        }
        return voltageRelayData;
    }

    public ObservableList<Record> getRecordData(String key_names) {
        ObservableList<Record> t = refTable.get(key_names);
        if(t.isEmpty()) {
            t.setAll(db.selectAllRecord(key_names));
        }
        return t;
    }

    public Map<String, ObservableList<Record>> getRecordsData() {

        for(String s : refTable.keySet()) {
            if(refTable.get(s).isEmpty()) {
                refTable.get(s).setAll(db.selectAllRecord(s));
            }
        }
        return refTable;
    }

    public Map<String, String> getRefNames() {
        return refNames;
    }


    public static void main(String[] args) {
        launch(args);
    }




    public boolean showManufacturerEditDialog(Manufacturer m) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ManufacturerEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Производитель");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.setFullScreen(false);
            dialogStage.setResizable(false);

            // Set the into the controller.
            ManufacturerEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainApp(this);
            controller.setManufacturer(m);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showTransformerEditDialog(Transformer t) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/TransformerEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Трансформатор");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.setFullScreen(false);
            dialogStage.setResizable(false);

            // Set the into the controller.
            TransformerEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainApp(this);
            controller.setTransformer(t);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showVoltageRelayEditDialog(VoltageRelay vr) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/VoltageRelayEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Реле");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.setFullScreen(false);
            dialogStage.setResizable(false);

            // Set the into the controller.
            VoltageRelayEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainApp(this);
            controller.setVoltageRelay(vr);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}

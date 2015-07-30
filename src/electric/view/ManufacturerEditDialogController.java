package electric.view;

import electric.MainApp;
import electric.model.Country;
import electric.model.Manufacturer;
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
public class ManufacturerEditDialogController {

    @FXML
    private TextField titleField;

    @FXML
    private ComboBox<String> countryBox;

    @FXML
    private TextField siteField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private TextField emailField;

    @FXML
    private TextArea descriptionArea;


    private Stage dialogStage;
    private Manufacturer manufacturer;
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

        countryBox.setItems(FXCollections.observableArrayList(
                mainApp.getDb().selectAllCountry()
                        .stream()
                        .map(Country::getCountry)
                        .collect(Collectors.toList())));
    }

    /**
     * Sets the manufacturer to be edited in the dialog.
     */
    public void setManufacturer(Manufacturer m) {
        this.manufacturer = m;

        titleField.setText(m.getTitle());
        siteField.setText(m.getSite());
        phoneNumberField.setText(m.getStringPhoneNumbers());
        emailField.setText(m.getStringEmails());
        descriptionArea.setText(m.getDescription());

        countryBox.getSelectionModel().select(m.getCountry());

        if(countryBox.getSelectionModel().getSelectedItem().isEmpty())
            countryBox.getSelectionModel().selectFirst();

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

            manufacturer.setTitle(titleField.getText());
            manufacturer.setSite(siteField.getText());
            manufacturer.setEmailsString(emailField.getText());
            manufacturer.setPhoneNumbersString(phoneNumberField.getText());
            manufacturer.setDescription(descriptionArea.getText());

            String country = countryBox.getValue();
            Country c = mainApp.getDb().selectAllCountry().stream()
                    .filter(cur -> cur.getCountry().contentEquals(country))
                    .findFirst()
                    .get();
            manufacturer.setCountry(country);
            manufacturer.setCountryCode(c.getCountryCode());
            manufacturer.setIdCountry(c.getId());

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
        if (siteField.getText() == null) {
            errorMessage += "Site error!\n";
        }
        if (emailField.getText() == null) {
            errorMessage += "Email error!\n";
        }

        if (phoneNumberField.getText() == null) {
            errorMessage += "Phone number error!\n";
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

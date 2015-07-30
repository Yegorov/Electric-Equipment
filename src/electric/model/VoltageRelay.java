package electric.model;

import javafx.beans.property.*;

public class VoltageRelay {

    private final IntegerProperty id;
    private final StringProperty title;

    private final IntegerProperty idManufacturer;
    private final StringProperty manufacturerTitle;

    private final IntegerProperty idMounting;
    private final StringProperty mounting;

    private final IntegerProperty countPhase;
    private final IntegerProperty numberNcContacts;
    private final IntegerProperty numberNoContacts;
    private final DoubleProperty ratedCoilVoltage;
    private final DoubleProperty ratedCurrent;
    private final DoubleProperty maxCurrent;
    private final DoubleProperty ratedVoltage;
    private final DoubleProperty maxVoltage;
    private final DoubleProperty ratedPower;
    private final StringProperty description;

    public VoltageRelay(Integer id,
                        String title,
                        Integer idManufacturer,
                        String manufacturerTitle,
                        Integer idMounting,
                        String mounting,
                        Integer countPhase,
                        Integer numberNcContacts,
                        Integer numberNoContacts,
                        Double ratedCoilVoltage,
                        Double ratedCurrent,
                        Double maxCurrent,
                        Double ratedVoltage,
                        Double maxVoltage,
                        Double ratedPower,
                        String description) {
        this.countPhase = new SimpleIntegerProperty(countPhase);
        this.id = new SimpleIntegerProperty(id);
        this.title = new SimpleStringProperty(title);
        this.idManufacturer = new SimpleIntegerProperty(idManufacturer);
        this.manufacturerTitle = new SimpleStringProperty(manufacturerTitle);
        this.idMounting = new SimpleIntegerProperty(idMounting);
        this.mounting = new SimpleStringProperty(mounting);
        this.numberNcContacts = new SimpleIntegerProperty(numberNcContacts);
        this.numberNoContacts = new SimpleIntegerProperty(numberNoContacts);
        this.ratedCoilVoltage = new SimpleDoubleProperty(ratedCoilVoltage);
        this.ratedCurrent = new SimpleDoubleProperty(ratedCurrent);
        this.maxCurrent = new SimpleDoubleProperty(maxCurrent);
        this.ratedVoltage = new SimpleDoubleProperty(ratedVoltage);
        this.maxVoltage = new SimpleDoubleProperty(maxVoltage);
        this.ratedPower = new SimpleDoubleProperty(ratedPower);
        this.description = new SimpleStringProperty(description);
    }

    public int getCountPhase() {
        return countPhase.get();
    }

    public IntegerProperty countPhaseProperty() {
        return countPhase;
    }

    public void setCountPhase(int countPhase) {
        this.countPhase.set(countPhase);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getIdManufacturer() {
        return idManufacturer.get();
    }

    public IntegerProperty idManufacturerProperty() {
        return idManufacturer;
    }

    public void setIdManufacturer(int idManufacturer) {
        this.idManufacturer.set(idManufacturer);
    }

    public String getManufacturerTitle() {
        return manufacturerTitle.get();
    }

    public StringProperty manufacturerTitleProperty() {
        return manufacturerTitle;
    }

    public void setManufacturerTitle(String manufacturerTitle) {
        this.manufacturerTitle.set(manufacturerTitle);
    }

    public double getMaxCurrent() {
        return maxCurrent.get();
    }

    public DoubleProperty maxCurrentProperty() {
        return maxCurrent;
    }

    public void setMaxCurrent(double maxCurrent) {
        this.maxCurrent.set(maxCurrent);
    }

    public double getMaxVoltage() {
        return maxVoltage.get();
    }

    public DoubleProperty maxVoltageProperty() {
        return maxVoltage;
    }

    public void setMaxVoltage(double maxVoltage) {
        this.maxVoltage.set(maxVoltage);
    }

    public String getMounting() {
        return mounting.get();
    }

    public StringProperty mountingProperty() {
        return mounting;
    }

    public void setMounting(String mounting) {
        this.mounting.set(mounting);
    }

    public int getNumberNcContacts() {
        return numberNcContacts.get();
    }

    public IntegerProperty numberNcContactsProperty() {
        return numberNcContacts;
    }

    public void setNumberNcContacts(int numberNcContacts) {
        this.numberNcContacts.set(numberNcContacts);
    }

    public int getNumberNoContacts() {
        return numberNoContacts.get();
    }

    public IntegerProperty numberNoContactsProperty() {
        return numberNoContacts;
    }

    public void setNumberNoContacts(int numberNoContacts) {
        this.numberNoContacts.set(numberNoContacts);
    }

    public double getRatedCoilVoltage() {
        return ratedCoilVoltage.get();
    }

    public DoubleProperty ratedCoilVoltageProperty() {
        return ratedCoilVoltage;
    }

    public void setRatedCoilVoltage(double ratedCoilVoltage) {
        this.ratedCoilVoltage.set(ratedCoilVoltage);
    }

    public double getRatedCurrent() {
        return ratedCurrent.get();
    }

    public DoubleProperty ratedCurrentProperty() {
        return ratedCurrent;
    }

    public void setRatedCurrent(double ratedCurrent) {
        this.ratedCurrent.set(ratedCurrent);
    }

    public double getRatedPower() {
        return ratedPower.get();
    }

    public DoubleProperty ratedPowerProperty() {
        return ratedPower;
    }

    public void setRatedPower(double ratedPower) {
        this.ratedPower.set(ratedPower);
    }

    public double getRatedVoltage() {
        return ratedVoltage.get();
    }

    public DoubleProperty ratedVoltageProperty() {
        return ratedVoltage;
    }

    public void setRatedVoltage(double ratedVoltage) {
        this.ratedVoltage.set(ratedVoltage);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public int getIdMounting() {
        return idMounting.get();
    }

    public IntegerProperty idMountingProperty() {
        return idMounting;
    }

    public void setIdMounting(int idMounting) {
        this.idMounting.set(idMounting);
    }

    @Override
    public String toString() {
        return String.format("%d\t\t%s\t\t%s\t\t%s\t\t%d\t\t%d\t\t%d\t\t%f\t\t%f\t\t%f\t\t%f\t\t%f\t\t%f\t\t%s",
                id.get(),
                title.get(),
                manufacturerTitle.get(),
                mounting.get(),
                countPhase.get(),
                numberNcContacts.get(),
                numberNoContacts.get(),
                ratedCoilVoltage.get(),
                ratedCurrent.get(),
                maxCurrent.get(),
                ratedVoltage.get(),
                maxVoltage.get(),
                ratedPower.get(),
                description.get()
                );
    }
}

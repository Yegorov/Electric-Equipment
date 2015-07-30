package electric.model;

import javafx.beans.property.*;

public class Transformer {

    private final IntegerProperty id;
    private final StringProperty title;

    private final IntegerProperty idManufacturer;
    private final StringProperty manufacturerTitle;

    private final IntegerProperty idCategory;
    private final StringProperty category;

    private final IntegerProperty idType;
    private final StringProperty type;

    private final IntegerProperty idCore;
    private final StringProperty core;

    private final IntegerProperty idCooling;
    private final StringProperty cooling;

    private final IntegerProperty idWindingConfiguration;
    private final StringProperty windingConfiguration;

    private final IntegerProperty idMaterialWinding;
    private final StringProperty materialWinding;

    private final IntegerProperty countWinding;
    private final IntegerProperty countPhase;
    private final DoubleProperty ratedCurrent;
    private final DoubleProperty primaryWindingVoltage;
    private final DoubleProperty secondaryWindingVoltage;
    private final StringProperty description;

    public Transformer(Integer id,
                       String title,
                       Integer idManufacturer,
                       String manufacturerTitle,
                       Integer idCategory,
                       String category,
                       Integer idType,
                       String type,
                       Integer idCore,
                       String core,
                       Integer idCooling,
                       String cooling,
                       Integer idWindingConfiguration,
                       String windingConfiguration,
                       Integer idMaterialWinding,
                       String materialWinding,
                       Integer countWinding,
                       Integer countPhase,
                       Double ratedCurrent,
                       Double primaryWindingVoltage,
                       Double secondaryWindingVoltage,
                       String description) {
        this.idCategory = new SimpleIntegerProperty(idCategory);
        this.idType = new SimpleIntegerProperty(idType);
        this.idCore = new SimpleIntegerProperty(idCore);
        this.idCooling = new SimpleIntegerProperty(idCooling);
        this.idWindingConfiguration = new SimpleIntegerProperty(idWindingConfiguration);
        this.idMaterialWinding = new SimpleIntegerProperty(idMaterialWinding);

        this.category = new SimpleStringProperty(category);
        this.id = new SimpleIntegerProperty(id);
        this.title = new SimpleStringProperty(title);
        this.idManufacturer = new SimpleIntegerProperty(idManufacturer);
        this.manufacturerTitle = new SimpleStringProperty(manufacturerTitle);
        this.type = new SimpleStringProperty(type);
        this.core = new SimpleStringProperty(core);
        this.cooling =  new SimpleStringProperty(cooling);
        this.windingConfiguration = new SimpleStringProperty(windingConfiguration);
        this.materialWinding = new SimpleStringProperty(materialWinding);
        this.countWinding = new SimpleIntegerProperty(countWinding);
        this.countPhase = new SimpleIntegerProperty(countPhase);
        this.ratedCurrent = new SimpleDoubleProperty(ratedCurrent);
        this.primaryWindingVoltage = new SimpleDoubleProperty(primaryWindingVoltage);
        this.secondaryWindingVoltage = new SimpleDoubleProperty(secondaryWindingVoltage);
        this.description = new SimpleStringProperty(description);
    }

    public String getCategory() {
        return category.get();
    }

    public StringProperty categoryProperty() {
        return category;
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public String getCooling() {
        return cooling.get();
    }

    public StringProperty coolingProperty() {
        return cooling;
    }

    public void setCooling(String cooling) {
        this.cooling.set(cooling);
    }

    public String getCore() {
        return core.get();
    }

    public StringProperty coreProperty() {
        return core;
    }

    public void setCore(String core) {
        this.core.set(core);
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

    public int getCountWinding() {
        return countWinding.get();
    }

    public IntegerProperty countWindingProperty() {
        return countWinding;
    }

    public void setCountWinding(int countWinding) {
        this.countWinding.set(countWinding);
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

    public String getMaterialWinding() {
        return materialWinding.get();
    }

    public StringProperty materialWindingProperty() {
        return materialWinding;
    }

    public void setMaterialWinding(String materialWinding) {
        this.materialWinding.set(materialWinding);
    }

    public double getPrimaryWindingVoltage() {
        return primaryWindingVoltage.get();
    }

    public DoubleProperty primaryWindingVoltageProperty() {
        return primaryWindingVoltage;
    }

    public void setPrimaryWindingVoltage(double primaryWindingVoltage) {
        this.primaryWindingVoltage.set(primaryWindingVoltage);
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

    public double getSecondaryWindingVoltage() {
        return secondaryWindingVoltage.get();
    }

    public DoubleProperty secondaryWindingVoltageProperty() {
        return secondaryWindingVoltage;
    }

    public void setSecondaryWindingVoltage(double secondaryWindingVoltage) {
        this.secondaryWindingVoltage.set(secondaryWindingVoltage);
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

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getWindingConfiguration() {
        return windingConfiguration.get();
    }

    public StringProperty windingConfigurationProperty() {
        return windingConfiguration;
    }

    public void setWindingConfiguration(String windingConfiguration) {
        this.windingConfiguration.set(windingConfiguration);
    }


    public int getIdCategory() {
        return idCategory.get();
    }

    public IntegerProperty idCategoryProperty() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory.set(idCategory);
    }

    public int getIdCooling() {
        return idCooling.get();
    }

    public IntegerProperty idCoolingProperty() {
        return idCooling;
    }

    public void setIdCooling(int idCooling) {
        this.idCooling.set(idCooling);
    }

    public int getIdCore() {
        return idCore.get();
    }

    public IntegerProperty idCoreProperty() {
        return idCore;
    }

    public void setIdCore(int idCore) {
        this.idCore.set(idCore);
    }

    public int getIdMaterialWinding() {
        return idMaterialWinding.get();
    }

    public IntegerProperty idMaterialWindingProperty() {
        return idMaterialWinding;
    }

    public void setIdMaterialWinding(int idMaterialWinding) {
        this.idMaterialWinding.set(idMaterialWinding);
    }

    public int getIdType() {
        return idType.get();
    }

    public IntegerProperty idTypeProperty() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType.set(idType);
    }

    public int getIdWindingConfiguration() {
        return idWindingConfiguration.get();
    }

    public IntegerProperty idWindingConfigurationProperty() {
        return idWindingConfiguration;
    }

    public void setIdWindingConfiguration(int idWindingConfiguration) {
        this.idWindingConfiguration.set(idWindingConfiguration);
    }

    @Override
    public String toString() {
        return String.format("%d\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s\t\t%d\t\t%d\t\t%f\t\t%f\t\t%f\t\t%s",
                id.get(),
                title.get(),
                manufacturerTitle.get(),
                category.get(),
                type.get(),
                core.get(),
                cooling.get(),
                windingConfiguration.get(),
                materialWinding.get(),
                countWinding.get(),
                countPhase.get(),
                ratedCurrent.get(),
                primaryWindingVoltage.get(),
                secondaryWindingVoltage.get(),
                description.get()
                );
    }
}

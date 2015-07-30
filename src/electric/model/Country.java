package electric.model;

import javafx.beans.property.*;

public class Country {

    private final IntegerProperty id;
    private final StringProperty country;
    private final StringProperty countryCode;


    public Country(Integer id, String country, String countryCode) {
        this.id = new SimpleIntegerProperty(id);
        this.country = new SimpleStringProperty(country);
        this.countryCode = new SimpleStringProperty(countryCode);
    }

    public String getCountry() {
        return country.get();
    }

    public StringProperty countryProperty() {
        return country;
    }

    public void setCountry(String country) {
        this.country.set(country);
    }


    public String getCountryCode() {
        return countryCode.get();
    }

    public StringProperty countryCodeProperty() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode.set(countryCode);
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

    @Override
    public String toString() {
        return String.format("%d\t\t%s\t\t%s", id.get(), country.get(), countryCode.get());
    }
}

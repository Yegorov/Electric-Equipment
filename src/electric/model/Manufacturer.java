package electric.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Manufacturer {

    private final IntegerProperty id;
    private final StringProperty title;

    private final IntegerProperty idCountry;
    private final StringProperty country;
    private final StringProperty countryCode;

    private List<StringProperty> phoneNumbers;
    private List<StringProperty> emails;

    private final StringProperty site;

    private final StringProperty description;

    public Manufacturer(Integer id,
                        String title,
                        Integer idCountry,
                        String country,
                        String countryCode,
                        String site,
                        String emails,
                        String phoneNumbers,
                        String description) {
        this.id = new SimpleIntegerProperty(id);
        this.idCountry = new SimpleIntegerProperty(idCountry);
        this.country = new SimpleStringProperty(country);
        this.title = new SimpleStringProperty(title);
        this.countryCode = new SimpleStringProperty(countryCode);
        this.site = new SimpleStringProperty(site);
        this.description = new SimpleStringProperty(description);

        this.emails = new ArrayList<StringProperty>();
        this.phoneNumbers = new ArrayList<StringProperty>();

        if(emails.isEmpty())
            this.emails.add(new SimpleStringProperty(""));
        else
            Arrays.stream(emails.split(",")).forEach(email -> this.emails.add(new SimpleStringProperty(email)));

        if(phoneNumbers.isEmpty())
            this.phoneNumbers.add(new SimpleStringProperty(""));
        else
            Arrays.stream(phoneNumbers.split(",")).forEach(number -> this.phoneNumbers.add(new SimpleStringProperty(number)));

    }

    public int getIdCountry() {
        return idCountry.get();
    }

    public IntegerProperty idCountryProperty() {
        return idCountry;
    }

    public void setIdCountry(int idCountry) {
        this.idCountry.set(idCountry);
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

    public String getCountryWithCode() {
        return String.format("%s (%s)", getCountry(), getCountryCode());
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

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getSite() {
        return site.get();
    }

    public StringProperty siteProperty() {
        return site;
    }

    public void setSite(String site) {
        this.site.set(site);
    }


    public int getCountPhoneNumbers() {
        return phoneNumbers.size();
    }

    public String getPhoneNumber(int i) {
        return phoneNumbers.get(i).get();
    }

    public StringProperty phoneNumberProperty(int i) {
        return phoneNumbers.get(i);
    }

    public void setPhoneNumber(int i, String phoneNumber) {
        this.phoneNumbers.get(i).set(phoneNumber);
    }

    public String getStringPhoneNumbers() {
        StringBuilder sb = new StringBuilder();
        this.phoneNumbers.stream().forEach(phoneNumber -> sb.append(phoneNumber.get() + ","));
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }


    public int getCountEmails() {
        return emails.size();
    }

    public String getEmail(int i) {
        return emails.get(i).get();
    }

    public StringProperty emailProperty(int i) {
        return emails.get(i);
    }

    public void setEmail(int i, String email) {
        this.emails.get(i).set(email);
    }

    public String getStringEmails() {
        StringBuilder sb = new StringBuilder();
        this.emails.stream().forEach(email -> sb.append(email.get() + ","));
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public List<String> getContacts() {
        List<String> l = new ArrayList<>(5);
        emails.stream().forEach(e -> {
            if(!e.get().isEmpty())
                l.add(e.get());
        });
        phoneNumbers.stream().forEach(n -> {
            if (!n.get().isEmpty())
                l.add(n.get());
        });
        return l;
    }


    public void setEmailsString(String s) {
        emails.clear();
        Arrays.stream(s.split(",")).forEach(email -> this.emails.add(new SimpleStringProperty(email)));

    }

    public void setPhoneNumbersString(String s) {
        phoneNumbers.clear();
        Arrays.stream(s.split(",")).forEach(number -> this.phoneNumbers.add(new SimpleStringProperty(number)));
    }

    @Override
    public String toString() {
        return String.format("%d\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s", id.get(), title.get(), getCountryWithCode(),
                site.get(), emails.get(0).get(), phoneNumbers.get(0).get(), description.get());
    }
}

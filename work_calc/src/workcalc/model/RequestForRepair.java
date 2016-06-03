package workcalc.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class RequestForRepair {
    /*
    *
    *Container for request of repair page
    *
    * */
    private IntegerProperty id;
    private StringProperty date;
    private StringProperty address;
    private StringProperty phoneNumber;
    private StringProperty typeOfProblem;
    private StringProperty name;

    public RequestForRepair(String date, String address, String phoneNumber, String type, String name)
    {
        this.date = new SimpleStringProperty(date);
        this.address = new SimpleStringProperty(address);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.typeOfProblem = new SimpleStringProperty(type);
        this.name = new SimpleStringProperty(name);
    }
    public RequestForRepair(int id,String date, String address, String phoneNumber, String type, String name)
    {
        this.id = new SimpleIntegerProperty(id);
        this.date = new SimpleStringProperty(date);
        this.address = new SimpleStringProperty(address);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.typeOfProblem = new SimpleStringProperty(type);
        this.name = new SimpleStringProperty(name);
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

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public String getTypeOfProblem() {
        return typeOfProblem.get();
    }

    public StringProperty typeOfProblemProperty() {
        return typeOfProblem;
    }

    public void setTypeOfProblem(String typeOfProblem) {
        this.typeOfProblem.set(typeOfProblem);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }
}

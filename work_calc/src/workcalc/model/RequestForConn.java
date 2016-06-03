package workcalc.model;

import javafx.beans.property.*;

public class RequestForConn {
    /*
    *
    *Container for request of conn page
    *
    * */
    private IntegerProperty id;
    private StringProperty date;
    private StringProperty view;
    private StringProperty address;
    private StringProperty phoneNumber;
    private StringProperty nameMounter;


    public RequestForConn(String date, String view, String address, String phoneNumber, String nameMounter)
    {
        this.date = new SimpleStringProperty(date);
        this.view = new SimpleStringProperty(view);
        this.address = new SimpleStringProperty(address);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.nameMounter = new SimpleStringProperty(nameMounter);
    }
    public RequestForConn(int id, String date, String view, String address, String phoneNumber, String nameMounter)
    {
        this.id = new SimpleIntegerProperty(id);
        this.date = new SimpleStringProperty(date);
        this.view = new SimpleStringProperty(view);
        this.address = new SimpleStringProperty(address);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.nameMounter = new SimpleStringProperty(nameMounter);
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

    public String getView() {
        return view.get();
    }

    public StringProperty viewProperty() {
        return view;
    }

    public void setView(String view) {
        this.view.set(view);
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

    public String getNameMounter() {
        return nameMounter.get();
    }

    public StringProperty nameMounterProperty() {
        return nameMounter;
    }

    public void setNameMounter(String nameMounter) {
        this.nameMounter.set(nameMounter);
    }

}

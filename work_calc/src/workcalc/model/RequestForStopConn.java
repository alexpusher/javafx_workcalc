package workcalc.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class RequestForStopConn {
    /*
    *
    *Container for stop conn page
    *
    * */
    private SimpleIntegerProperty id;
    private SimpleStringProperty date;
    private SimpleStringProperty view;
    private SimpleStringProperty ip;
    private SimpleStringProperty address;
    private SimpleStringProperty stopDate;
    private SimpleStringProperty status;
    private SimpleStringProperty comment;

    public RequestForStopConn(String date, String view, String ip, String address, String stopDate, String status, String comment)
    {
        this.date = new SimpleStringProperty(date);
        this.view = new SimpleStringProperty(view);
        this.ip = new SimpleStringProperty(ip);
        this.address = new SimpleStringProperty(address);
        this.stopDate = new SimpleStringProperty(stopDate);
        this.status = new SimpleStringProperty(status);
        this.comment = new SimpleStringProperty(comment);
    }
    public RequestForStopConn(int id, String date, String view, String ip, String address, String stopDate, String status, String comment)
    {
        this.id = new SimpleIntegerProperty(id);
        this.date = new SimpleStringProperty(date);
        this.view = new SimpleStringProperty(view);
        this.ip = new SimpleStringProperty(ip);
        this.address = new SimpleStringProperty(address);
        this.stopDate = new SimpleStringProperty(stopDate);
        this.status = new SimpleStringProperty(status);
        this.comment = new SimpleStringProperty(comment);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty IdProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getView() {
        return view.get();
    }

    public SimpleStringProperty viewProperty() {
        return view;
    }

    public void setView(String view) {
        this.view.set(view);
    }

    public String getIp() {
        return ip.get();
    }

    public SimpleStringProperty ipProperty() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip.set(ip);
    }

    public String getAddress() {
        return address.get();
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getStopDate() {
        return stopDate.get();
    }

    public SimpleStringProperty stopDateProperty() {
        return stopDate;
    }

    public void setStopDate(String stopDate) {
        this.stopDate.set(stopDate);
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public String getComment() {
        return comment.get();
    }

    public SimpleStringProperty commentProperty() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment.set(comment);
    }
}

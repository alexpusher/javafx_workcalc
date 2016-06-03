package workcalc.model;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class calc {
    /*
    *
    *Container for main page
    *
    * */
    private SimpleIntegerProperty startDay;
    private SimpleIntegerProperty overDay;
    private SimpleIntegerProperty startOfReceipt;
    private SimpleIntegerProperty endOfReceipt;
    private SimpleIntegerProperty sumReceipt;
    private SimpleStringProperty user;
    private SimpleStringProperty date;
    private SimpleStringProperty comment;


    public calc(int overDay, String date) {
        this.overDay = new SimpleIntegerProperty(overDay);
        this.date = new SimpleStringProperty(date);
    }

    public calc(int startDay, int overDay, int startOfReceipt, int endOfReceipt, int sumReceipt, String user) {
        this.startDay = new SimpleIntegerProperty(startDay);
        this.overDay = new SimpleIntegerProperty(overDay);
        this.startOfReceipt = new SimpleIntegerProperty(startOfReceipt);
        this.endOfReceipt = new SimpleIntegerProperty(endOfReceipt);
        this.sumReceipt = new SimpleIntegerProperty(sumReceipt);
        this.user = new SimpleStringProperty(user);
    }

    public calc(int startDay, int overDay, int startOfReceipt, int endOfReceipt, int sumReceipt, String user, String date) {
        this.startDay = new SimpleIntegerProperty(startDay);
        this.overDay = new SimpleIntegerProperty(overDay);
        this.startOfReceipt = new SimpleIntegerProperty(startOfReceipt);
        this.endOfReceipt = new SimpleIntegerProperty(endOfReceipt);
        this.sumReceipt = new SimpleIntegerProperty(sumReceipt);
        this.user = new SimpleStringProperty(user);
        this.date = new SimpleStringProperty(date);
    }
    public calc(int startDay, int overDay, int startOfReceipt, int endOfReceipt, int sumReceipt, String user, String date, String comment) {
        this.startDay = new SimpleIntegerProperty(startDay);
        this.overDay = new SimpleIntegerProperty(overDay);
        this.startOfReceipt = new SimpleIntegerProperty(startOfReceipt);
        this.endOfReceipt = new SimpleIntegerProperty(endOfReceipt);
        this.sumReceipt = new SimpleIntegerProperty(sumReceipt);
        this.user = new SimpleStringProperty(user);
        this.date = new SimpleStringProperty(date);
        this.comment = new SimpleStringProperty(comment);
    }

    public int countReceipt()
    {
        int i = getEndOfReceipt();
        i++;
        setEndOfReceipt(i);
        return i;
    }
    public int deCountReceipt()
    {
        int i = getEndOfReceipt();
        i--;
        setEndOfReceipt(i);
        return i;
    }
    public int getStartDay() {
        return startDay.get();
    }

    public SimpleIntegerProperty startDayProperty() {
        return startDay;
    }

    public void setStartDay(int startDay) {
        this.startDay.set(startDay);
    }

    public int getOverDay() {
        return overDay.get();
    }

    public SimpleIntegerProperty overDayProperty() {
        return overDay;
    }

    public void setOverDay(int overDay) {
        this.overDay.set(overDay);
    }

    public int getStartOfReceipt() {
        return startOfReceipt.get();
    }

    public SimpleIntegerProperty startOfReceiptProperty() {
        return startOfReceipt;
    }

    public void setStartOfReceipt(int startOfReceipt) {
        this.startOfReceipt.set(startOfReceipt);
    }

    public int getEndOfReceipt() {
        return endOfReceipt.get();
    }

    public SimpleIntegerProperty endOfReceiptProperty() {
        return endOfReceipt;
    }

    public void setEndOfReceipt(int endOfReceipt) {
        this.endOfReceipt.set(endOfReceipt);
    }

    public int getSumReceipt() {
        return sumReceipt.get();
    }

    public SimpleIntegerProperty sumReceiptProperty() {
        return sumReceipt;
    }

    public void setSumReceipt(int sumReceipt) {
        this.sumReceipt.set(sumReceipt);
    }

    public String getUser() {
        return user.get();
    }

    public SimpleStringProperty userProperty() {
        return user;
    }

    public void setUser(String user) {
        this.user.set(user);
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

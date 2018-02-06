package workcalc.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Alex on 14.07.2016.
 */
public class BoxForText {
    private SimpleIntegerProperty lastReceipt;
    private SimpleIntegerProperty lastVal;

    public BoxForText(int lastReceipt, int val)
    {
        this.lastReceipt = new SimpleIntegerProperty(lastReceipt);
        this.lastVal = new SimpleIntegerProperty(val);
    }

    public int getLastReceipt() {
        return lastReceipt.get();
    }

    public SimpleIntegerProperty lastReceiptProperty() {
        return lastReceipt;
    }

    public void setLastReceipt(int lastReceipt) {
        this.lastReceipt.set(lastReceipt);
    }

    public int getLastVal() {
        return lastVal.get();
    }

    public SimpleIntegerProperty lastValProperty() {
        return lastVal;
    }

    public void setLastVal(int lastVal) {
        this.lastVal.set(lastVal);
    }
}

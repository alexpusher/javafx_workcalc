package workcalc.view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import workcalc.model.BoxForText;
import java.util.Timer;
import java.util.TimerTask;


public class TextAreaController {

    @FXML
    public static TextArea textArea = new TextArea();
    //public static BoxForText boxForText = new BoxForText(0, 0);

    @FXML
    public void initialize()
    {
        //textArea.setText("№" + calcOverviewController.calc.getEndOfReceipt() + "\n" +
        //                "" + "\n" +
        //                setTextArea(a) + " " + "");z
        //System.out.println("dasda");
        textArea.setWrapText(true);
        textArea.setEditable(true);
        textArea.setText("asdsadad");
        //setTextArea();
        //refresh1();
    }
    public void appendText(String one, String two)
    {
        textArea.appendText("№" + one + "\n" +
                            "" + "\n" +
                            two + " " + "");

    }

    /*
    @FXML
    public void initialize()
    {
        //textArea.setText("№" + calcOverviewController.calc.getEndOfReceipt() + "\n" +
         //                "" + "\n" +
         //                setTextArea(a) + " " + "");z
        //System.out.println("dasda");
        textArea.setWrapText(true);
        textArea.setEditable(true);
        textArea.setText("asdsadad");
        //setTextArea();
        refresh1();
    }
    public void setTextArea()
    {
        //SimpleStringProperty a = new SimpleStringProperty("");
        //a.set("sadsada");
        //System.out.println(i);
        //textArea.appendText(text);
        //textArea.setText(String.valueOf(calcOverviewController.calc.getEndOfReceipt()));
            textArea.setText("№" + boxForText.getLastReceipt() + "\n" +
                    "" + "\n" +
                    boxForText.getLastVal() + " " + "" + "\n" +
                    textArea.getText());

        /*
        final Timeline timeline = new Timeline(new KeyFrame(javafx.util.Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                textArea.setText("№" + boxForText.getLastReceipt() + "\n" +
                                 "" + "\n" +
                                 boxForText.getLastVal() + " " + "");
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }
    public void refresh()
    {
        System.out.println("asdsad");
        new Timer().schedule(new TimerTask() {
            public void run () {
                System.out.println("235433");
                textArea.setText("№" + boxForText.getLastReceipt() + "\n" +
                        "" + "\n" +
                        boxForText.getLastVal() + " " + "" + "\n" +
                        textArea.getText());
            }
        }, 1000);
    }
    public void refresh1()
    {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                System.out.println("235433");
                textArea.setText("№" + boxForText.getLastReceipt() + "\n" +
                        "" + "\n" +
                        boxForText.getLastVal() + " " + "" + "\n" +
                        textArea.getText());
            }
        });
    }
    */
}

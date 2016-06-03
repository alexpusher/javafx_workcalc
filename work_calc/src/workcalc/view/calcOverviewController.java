package workcalc.view;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

import workcalc.Main;
import workcalc.model.calc;


public class calcOverviewController {
    /*
    *
    * Main page - model/calc
    *Here all press button and input value added in object calc and displayed on GUI
    *
    *
    * */
    @FXML
    private Label startDayPrint;
    @FXML
    private Label overDayPrint;
    @FXML
    private Label startOfReceiptPrint;
    @FXML
    private Label endOfReceiptPrint;
    @FXML
    private Label sumReceiptPrint;
    @FXML
    private Label datePrint;
    @FXML
    private Label timePrint;
    @FXML
    private Label userPrint;
    @FXML
    private TextField anotherSum;
    @FXML
    private Button b400;
    @FXML
    private Button b450;
    @FXML
    private Button b600;
    @FXML
    private Button b800;
    @FXML
    private Button b1200;
    @FXML
    private Button b1500;
    @FXML
    private Button b2250;
    @FXML
    private Button b3000;
    @FXML
    private Button b3600;

    private Connection con;
    private PreparedStatement stat;

    public static calc calc = new calc(0,0,0,0,0,null);

    @FXML
    private void initialize()
    {
        try{
            startDayPrint.setText(Integer.toString(calc.getStartDay()));
            overDayPrint.setText(Integer.toString(calc.getOverDay()));
            startOfReceiptPrint.setText(Integer.toString(calc.getStartOfReceipt()));
            userPrint.setText(calc.getUser());
            datePrint.setText(Date());
            Clock();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    @FXML
    private void handle400()
    {
        int i = Integer.parseInt(b400.getText());
        calc.setOverDay(calc.getOverDay()+ i);
        calc.setSumReceipt(calc.getOverDay());
        overDayPrint.setText(Integer.toString(calc.getOverDay() + calc.getStartDay()));
        sumReceiptPrint.setText(Integer.toString(calc.getSumReceipt()));
        endOfReceiptPrint.setText(Integer.toString(calc.countReceipt()));
    }
    @FXML
    private void handle450()
    {
        int i = Integer.parseInt(b450.getText());
        calc.setOverDay(calc.getOverDay()+ i);
        calc.setSumReceipt(calc.getOverDay());
        overDayPrint.setText(Integer.toString(calc.getOverDay()+calc.getStartDay()));
        sumReceiptPrint.setText(Integer.toString(calc.getSumReceipt()));
        endOfReceiptPrint.setText(Integer.toString(calc.countReceipt()));
    }
    @FXML
    private void handle600()
    {
        int i = Integer.parseInt(b600.getText());
        calc.setOverDay(calc.getOverDay()+ i);
        calc.setSumReceipt(calc.getOverDay());
        overDayPrint.setText(Integer.toString(calc.getOverDay()+calc.getStartDay()));
        sumReceiptPrint.setText(Integer.toString(calc.getSumReceipt()));
        endOfReceiptPrint.setText(Integer.toString(calc.countReceipt()));
    }
    @FXML
    private void handle800()
    {
        int i = Integer.parseInt(b800.getText());
        calc.setOverDay(calc.getOverDay()+ i);
        calc.setSumReceipt(calc.getOverDay());
        overDayPrint.setText(Integer.toString(calc.getOverDay()+calc.getStartDay()));
        sumReceiptPrint.setText(Integer.toString(calc.getSumReceipt()));
        endOfReceiptPrint.setText(Integer.toString(calc.countReceipt()));
    }
    @FXML
    private void handle1200()
    {
        int i = Integer.parseInt(b1200.getText());
        calc.setOverDay(calc.getOverDay()+ i);
        calc.setSumReceipt(calc.getOverDay());
        overDayPrint.setText(Integer.toString(calc.getOverDay()+calc.getStartDay()));
        sumReceiptPrint.setText(Integer.toString(calc.getSumReceipt()));
        endOfReceiptPrint.setText(Integer.toString(calc.countReceipt()));
    }
    @FXML
    private void handle1500()
    {
        int i = Integer.parseInt(b1500.getText());
        calc.setOverDay(calc.getOverDay()+ i);
        calc.setSumReceipt(calc.getOverDay());
        overDayPrint.setText(Integer.toString(calc.getOverDay()+calc.getStartDay()));
        sumReceiptPrint.setText(Integer.toString(calc.getSumReceipt()));
        endOfReceiptPrint.setText(Integer.toString(calc.countReceipt()));
    }
    @FXML
    private void handle2250()
    {
        int i = Integer.parseInt(b2250.getText());
        calc.setOverDay(calc.getOverDay()+ i);
        calc.setSumReceipt(calc.getOverDay());
        overDayPrint.setText(Integer.toString(calc.getOverDay()+calc.getStartDay()));
        sumReceiptPrint.setText(Integer.toString(calc.getSumReceipt()));
        endOfReceiptPrint.setText(Integer.toString(calc.countReceipt()));
    }
    @FXML
    private void handle3000()
    {
        int i = Integer.parseInt(b3000.getText());
        calc.setOverDay(calc.getOverDay()+ i);
        calc.setSumReceipt(calc.getOverDay());
        overDayPrint.setText(Integer.toString(calc.getOverDay()+calc.getStartDay()));
        sumReceiptPrint.setText(Integer.toString(calc.getSumReceipt()));
        endOfReceiptPrint.setText(Integer.toString(calc.countReceipt()));
    }
    @FXML
    private void handle3600()
    {
        int i = Integer.parseInt(b3600.getText());
        calc.setOverDay(calc.getOverDay()+ i);
        calc.setSumReceipt(calc.getOverDay());
        overDayPrint.setText(Integer.toString(calc.getOverDay()+calc.getStartDay()));
        sumReceiptPrint.setText(Integer.toString(calc.getSumReceipt()));
        endOfReceiptPrint.setText(Integer.toString(calc.countReceipt()));
    }
    @FXML
    private void handleAction(KeyEvent event)
    {
        if(event.getCode() == KeyCode.ENTER)
        {
            try{
                int i = Integer.parseInt(anotherSum.getText());
                if(i > 0)
                {
                    calc.setOverDay(calc.getOverDay()+ i);
                    calc.setSumReceipt(calc.getOverDay());
                    overDayPrint.setText(Integer.toString(calc.getOverDay()+calc.getStartDay()));
                    sumReceiptPrint.setText(Integer.toString(calc.getSumReceipt()));
                    endOfReceiptPrint.setText(Integer.toString(calc.countReceipt()));
                    anotherSum.setText("");
                }else
                if(i < 0)
                {
                    calc.setOverDay(calc.getOverDay()+ i);
                    calc.setSumReceipt(calc.getOverDay());
                    overDayPrint.setText(Integer.toString(calc.getOverDay()+calc.getStartDay()));
                    sumReceiptPrint.setText(Integer.toString(calc.getSumReceipt()));
                    endOfReceiptPrint.setText(Integer.toString(calc.deCountReceipt()));
                    anotherSum.setText("");
                }
                else
                if(i == 0)
                {
                    anotherSum.setText("");
                }
            }catch (NumberFormatException e) {
                if (anotherSum.getText().equals("+0.1"))
                {
                    endOfReceiptPrint.setText(Integer.toString(calc.countReceipt()));
                    anotherSum.setText("");
                }else
                if(anotherSum.getText().equals("-0.1"))
                {
                    endOfReceiptPrint.setText(Integer.toString(calc.deCountReceipt()));
                    anotherSum.setText("");
                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("SYNTAX ERROR");
                    alert.setContentText("Need Input Number!!!");
                    alert.showAndWait();
                    anotherSum.setText("");
                }
            }
        }
    }

    public static String Date()
    {
        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String dateToday = simpleDateFormat.format(calendar.getTime());
        return dateToday;
    }

    private void Clock()
    {
        //Dynamic time line
        final Timeline timeline = new Timeline(new KeyFrame(javafx.util.Duration.seconds(1), new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                timePrint.setText(String.valueOf(LocalTime.now().getHour())+ ":"
                        + Minute() + ":" + Second());
                if(timePrint.getText().equals("23:03:15"))
                {
                    try {
                        save();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    close();
                }



            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    private String Minute()
    {
        //for correct display minute
        int i = LocalTime.now().getMinute();
        if (i < 10)
        {
            return "0" + String.valueOf(i);
        }
        return String.valueOf(i);
    }
    private String Second()
    {
        //for correct display second
        int i = LocalTime.now().getSecond();
        if (i < 10)
        {
            return "0" + String.valueOf(i);
        }
        return String.valueOf(i);
    }
    private void close()
    {
        //need to automatically close window in select time
        new Timer().schedule(new TimerTask() {
            public void run () {
                System.exit(0);
            }
        }, 1000);
    }
    private void save() throws SQLException {
        //before as use close window need save data in db
        calc.setOverDay(calc.getStartDay()+calc.getOverDay());
        try
        {
            con = null;
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:calcData.sqlite");
            if(con == null)
            {
                System.out.println("");
            }else
            {
                System.out.println("123");
            }
            String sql= "INSERT INTO 'calc'('startDay','overDay','startOfReceipt','endOfReceipt','sumReceipt','user','date') VALUES(?,?,?,?,?,?,?)";
            stat = con.prepareStatement(sql);
            stat.setInt(1,calc.getStartDay());
            stat.setInt(2,calc.getOverDay());
            stat.setInt(3,calc.getStartOfReceipt());
            stat.setInt(4,calc.getEndOfReceipt());
            stat.setInt(5,calc.getSumReceipt());
            stat.setString(6,calc.getUser());
            stat.setString(7,Date());
            stat.executeUpdate();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            stat.close();
            con.close();
        }
    }

}

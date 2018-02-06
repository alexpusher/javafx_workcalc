package workcalc.view;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

import javafx.stage.Modality;
import javafx.stage.Stage;
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


    public static boolean check;
    public static calc calc = new calc(0,0,0,0,0,null);

    @FXML
    private void initialize() throws SQLException {
            if(check)
            {
                startDayPrint.setText(Integer.toString(calc.getStartDay()));
                overDayPrint.setText(Integer.toString(calc.getOverDay()));
                startOfReceiptPrint.setText(Integer.toString(calc.getStartOfReceipt()));
                userPrint.setText(calc.getUser());
                datePrint.setText(Date());
                calc.setOverDay(calc.getStartDay());
                Clock();
                update();
            }else
            {
                startDayPrint.setText(Integer.toString(calc.getStartDay()));
                overDayPrint.setText(Integer.toString(calc.getOverDay()));
                startOfReceiptPrint.setText(Integer.toString(calc.getStartOfReceipt()));
                endOfReceiptPrint.setText(Integer.toString(calc.getEndOfReceipt()));
                sumReceiptPrint.setText(Integer.toString(calc.getSumReceipt()));
                userPrint.setText(calc.getUser());
                datePrint.setText(Date());
                Clock();
            }
    }
    @FXML
    private void handle400() throws SQLException {
        int i = Integer.parseInt(b400.getText());
        calc.setOverDay(calc.getOverDay()+ i);
        calc.setSumReceipt(calc.getSumReceipt()+i);
        overDayPrint.setText(Integer.toString(calc.getOverDay()));
        sumReceiptPrint.setText(Integer.toString(calc.getSumReceipt()));
        endOfReceiptPrint.setText(Integer.toString(calc.countReceipt()));
        new TextAreaController().appendText(String.valueOf(calc.getEndOfReceipt()), b400.getText());
        update();
    }
    @FXML
    private void handle450() throws SQLException {
        int i = Integer.parseInt(b450.getText());
        calc.setOverDay(calc.getOverDay()+ i);
        calc.setSumReceipt(calc.getSumReceipt()+i);
        overDayPrint.setText(Integer.toString(calc.getOverDay()));
        sumReceiptPrint.setText(Integer.toString(calc.getSumReceipt()));
        endOfReceiptPrint.setText(Integer.toString(calc.countReceipt()));
        update();
    }
    @FXML
    private void handle600() throws SQLException {
        int i = Integer.parseInt(b600.getText());
        calc.setOverDay(calc.getOverDay()+ i);
        calc.setSumReceipt(calc.getSumReceipt() + i);
        overDayPrint.setText(Integer.toString(calc.getOverDay()));
        sumReceiptPrint.setText(Integer.toString(calc.getSumReceipt()));
        endOfReceiptPrint.setText(Integer.toString(calc.countReceipt()));
        update();
    }
    @FXML
    private void handle800() throws SQLException {
        int i = Integer.parseInt(b800.getText());
        calc.setOverDay(calc.getOverDay()+ i);
        calc.setSumReceipt(calc.getSumReceipt() + i);
        overDayPrint.setText(Integer.toString(calc.getOverDay()));
        sumReceiptPrint.setText(Integer.toString(calc.getSumReceipt()));
        endOfReceiptPrint.setText(Integer.toString(calc.countReceipt()));
        update();
    }
    @FXML
    private void handle1200() throws SQLException {
        int i = Integer.parseInt(b1200.getText());
        calc.setOverDay(calc.getOverDay()+ i);
        calc.setSumReceipt(calc.getSumReceipt() + i);
        overDayPrint.setText(Integer.toString(calc.getOverDay()));
        sumReceiptPrint.setText(Integer.toString(calc.getSumReceipt()));
        endOfReceiptPrint.setText(Integer.toString(calc.countReceipt()));
        update();
    }
    @FXML
    private void handle1500() throws SQLException {
        int i = Integer.parseInt(b1500.getText());
        calc.setOverDay(calc.getOverDay()+ i);
        calc.setSumReceipt(calc.getSumReceipt() + i);
        overDayPrint.setText(Integer.toString(calc.getOverDay()));
        sumReceiptPrint.setText(Integer.toString(calc.getSumReceipt()));
        endOfReceiptPrint.setText(Integer.toString(calc.countReceipt()));
        update();
    }
    @FXML
    private void handle2250() throws SQLException {
        int i = Integer.parseInt(b2250.getText());
        calc.setOverDay(calc.getOverDay()+ i);
        calc.setSumReceipt(calc.getSumReceipt() + i);
        overDayPrint.setText(Integer.toString(calc.getOverDay()));
        sumReceiptPrint.setText(Integer.toString(calc.getSumReceipt()));
        endOfReceiptPrint.setText(Integer.toString(calc.countReceipt()));
        update();
    }
    @FXML
    private void handle3000() throws SQLException {
        int i = Integer.parseInt(b3000.getText());
        calc.setOverDay(calc.getOverDay()+ i);
        calc.setSumReceipt(calc.getSumReceipt()+i);
        overDayPrint.setText(Integer.toString(calc.getOverDay()));
        sumReceiptPrint.setText(Integer.toString(calc.getSumReceipt()));
        endOfReceiptPrint.setText(Integer.toString(calc.countReceipt()));
        update();
    }
    @FXML
    private void handle3600() throws SQLException {
        int i = Integer.parseInt(b3600.getText());
        calc.setOverDay(calc.getOverDay()+ i);
        calc.setSumReceipt(calc.getSumReceipt() + i);
        overDayPrint.setText(Integer.toString(calc.getOverDay()));
        sumReceiptPrint.setText(Integer.toString(calc.getSumReceipt()));
        endOfReceiptPrint.setText(Integer.toString(calc.countReceipt()));
        update();
    }
    @FXML
    private void handleGetMoney() throws IOException, SQLException {
        Parent parent = FXMLLoader.load(getClass().getResource("getMoneyLayout.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(new Scene(parent));
        stage.setTitle("Get Money");
        stage.showAndWait();
        update();
        overDayPrint.setText(Integer.toString(calc.getOverDay()));
    }
    @FXML
    private void handleAction(KeyEvent event) throws SQLException {
        if(event.getCode() == KeyCode.ENTER)
        {
            try{
                int i = Integer.parseInt(anotherSum.getText());
                if(i > 0)
                {
                    calc.setOverDay(calc.getOverDay()+ i);
                    calc.setSumReceipt(calc.getSumReceipt()+i);
                    overDayPrint.setText(Integer.toString(calc.getOverDay()));
                    sumReceiptPrint.setText(Integer.toString(calc.getSumReceipt()));
                    endOfReceiptPrint.setText(Integer.toString(calc.countReceipt()));
                    update();
                    anotherSum.setText("");
                }else
                if(i < 0)
                {
                    calc.setOverDay(calc.getOverDay()+ i);
                    calc.setSumReceipt(calc.getSumReceipt()+i);
                    overDayPrint.setText(Integer.toString(calc.getOverDay()));
                    sumReceiptPrint.setText(Integer.toString(calc.getSumReceipt()));
                    endOfReceiptPrint.setText(Integer.toString(calc.deCountReceipt()));
                    update();
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
                    update();
                    anotherSum.setText("");
                }else
                if(anotherSum.getText().equals("-0.1"))
                {
                    endOfReceiptPrint.setText(Integer.toString(calc.deCountReceipt()));
                    update();
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
                        update();
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
    public String Minute()
    {
        //for correct display minute
        int i = LocalTime.now().getMinute();
        if (i < 10)
        {
            return "0" + String.valueOf(i);
        }
        return String.valueOf(i);
    }
    public String Second()
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

    public void update() throws SQLException {
        try
        {

            con = null;
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:calcData.sqlite");
            String sql= "UPDATE calc SET startDay = ?, overDay = ?, startOfReceipt = ?, endOfReceipt = ?, sumReceipt = ?, user = ?" +
                    "where date = ?;";
            stat = con.prepareStatement(sql);
            stat.setInt(1, calc.getStartDay());
            stat.setInt(2, calc.getOverDay());
            stat.setInt(3, calc.getStartOfReceipt());
            stat.setInt(4, calc.getEndOfReceipt());
            stat.setInt(5, calc.getSumReceipt());
            stat.setString(6, calc.getUser());
            stat.setString(7, Date());
            stat.executeUpdate();
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            stat.close();
            con.close();
        }
    }
}

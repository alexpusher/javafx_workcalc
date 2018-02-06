package workcalc.view;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import workcalc.Main;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;



public class startLayoutController {

    @FXML
    private TextField balanceOnStart;
    @FXML
    private TextField startOfReceipt;
    @FXML
    private TextField userStart;
    @FXML
    private Button bOk;
    @FXML
    private Button bCan;

    Connection con;
    Statement stat;
    ResultSet resSet;


    public startLayoutController()
    {
    }
    @FXML
    private void initialize() throws SQLException {
        checkDate();
    }
    @FXML
    private void handleOk(ActionEvent event) throws Exception
    {
        /*
        *
        *Load main page
        *Open rootLayout and him controller rootLayoutController
        * rootLayoutController have menu bar with different menu
        *Open CalcLayout(main page) and him controller calcOverviewController
        * Input text on this page transfer in static object in calcOverviewController
        */
        if(event.getSource() == bOk && isInputValid())
        {
                ((Node) (event.getSource())).getScene().getWindow().hide();
                calcOverviewController.calc.setStartDay(Integer.parseInt(balanceOnStart.getText()));
                calcOverviewController.calc.setStartOfReceipt(Integer.parseInt(startOfReceipt.getText()));
                calcOverviewController.calc.setEndOfReceipt(Integer.parseInt(startOfReceipt.getText())-1);
                calcOverviewController.calc.setUser(userStart.getText());
                BorderPane pane = FXMLLoader.load(getClass().getResource("RootLayout.fxml"));
                Parent parent = FXMLLoader.load(getClass().getResource("CalcLayout.fxml"));
                Parent parent2 = FXMLLoader.load(getClass().getResource("TextAreaLayout.fxml"));
                pane.setCenter(parent);
                pane.setBottom(parent2);
                Stage stage = new Stage();
                stage.setScene(new Scene(pane));
                stage.setResizable(false);
                stage.setTitle("WorkCalc");
                stage.show();
        }
        if(event.getSource() == bCan)
        {
            new Main().stop();
            System.exit(0);
        }
    }
    @FXML
    private void handleStart(KeyEvent event) throws Exception
    {
        if(event.getCode() == KeyCode.ENTER && isInputValid())
        {
                ((Node) (event.getSource())).getScene().getWindow().hide();
                calcOverviewController.calc.setStartDay(Integer.parseInt(balanceOnStart.getText()));
                calcOverviewController.calc.setStartOfReceipt(Integer.parseInt(startOfReceipt.getText()));
                calcOverviewController.calc.setEndOfReceipt(Integer.parseInt(startOfReceipt.getText())-1);
                calcOverviewController.calc.setUser(userStart.getText());
                BorderPane pane = FXMLLoader.load(getClass().getResource("RootLayout.fxml"));
                Parent parent = FXMLLoader.load(getClass().getResource("CalcLayout.fxml"));
                Parent parent2 = FXMLLoader.load(getClass().getResource("TextAreaLayout.fxml"));
                pane.setCenter(parent);
                pane.setBottom(parent2);
                Stage stage = new Stage();
                stage.setScene(new Scene(pane));
                stage.setResizable(false);
                stage.setTitle("WorkCalc");
                stage.show();
        }
    }
    public void checkDate() throws SQLException {
        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -1);
            String tmpDate = simpleDateFormat.format(cal.getTime());
            con = null;
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:calcData.sqlite");
            if(con != null)
            {
                System.out.println("123");
            }
            stat = con.createStatement();
            resSet = stat.executeQuery("SELECT * FROM calc");
            while(resSet.next()) {
                if(resSet.getString("date").equals(tmpDate)) {
                    balanceOnStart.setText(String.valueOf(resSet.getInt("overDay")));
                    startOfReceipt.setText(String.valueOf(resSet.getInt("endOfReceipt")+1));
                    break;
                }else
                {
                    balanceOnStart.setText("");
                    startOfReceipt.setText("");
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            stat.close();
            con.close();
        }
    }
    private boolean isInputValid() {
        String errorMessage = "";

        if (balanceOnStart.getText() == null || balanceOnStart.getText().length() == 0) {
            errorMessage += "The balance is not entered!\n";
        }
        else{
            try {
                 Integer.parseInt(balanceOnStart.getText());
            }catch (NumberFormatException e)
            {
                errorMessage += "The input field must be integer!\n";
            }
        }
        if (startOfReceipt.getText() == null || startOfReceipt.getText().length() == 0) {
            errorMessage += "The number of receipt is not entered!\n";
        }
        else{
            try {
                Integer.parseInt(startOfReceipt.getText());
            }catch (NumberFormatException e)
            {
                errorMessage += "The input field must be integer!\n";
            }
        }
        if (userStart.getText() == null || userStart.getText().length() == 0) {
            errorMessage += "The username is not entered!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please enter data in fields");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }
}

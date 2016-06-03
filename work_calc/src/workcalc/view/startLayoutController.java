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


    public startLayoutController()
    {
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
        if(event.getSource() == bOk)
        {
            if(isInputValid())
            {
                ((Node) (event.getSource())).getScene().getWindow().hide();
                calcOverviewController.calc.setStartDay(Integer.parseInt(balanceOnStart.getText()));
                calcOverviewController.calc.setStartOfReceipt(Integer.parseInt(startOfReceipt.getText()));
                calcOverviewController.calc.setEndOfReceipt(Integer.parseInt(startOfReceipt.getText())-1);
                calcOverviewController.calc.setUser(userStart.getText());
                BorderPane pane = FXMLLoader.load(getClass().getResource("RootLayout.fxml"));
                Parent parent2 = FXMLLoader.load(getClass().getResource("CalcLayout.fxml"));
                pane.setCenter(parent2);
                Stage stage = new Stage();
                stage.setScene(new Scene(pane));
                stage.setResizable(false);
                stage.setTitle("CalcTest");
                stage.show();
            }
        }
        if(event.getSource()==bCan)
        {
            System.exit(0);
        }
    }
    @FXML
    private void handleStart(KeyEvent event) throws Exception
    {
        if(event.getCode() == KeyCode.ENTER)
        {
            if(isInputValid())
            {
                ((Node) (event.getSource())).getScene().getWindow().hide();
                calcOverviewController.calc.setStartDay(Integer.parseInt(balanceOnStart.getText()));
                calcOverviewController.calc.setStartOfReceipt(Integer.parseInt(startOfReceipt.getText()));
                calcOverviewController.calc.setEndOfReceipt(Integer.parseInt(startOfReceipt.getText())-1);
                calcOverviewController.calc.setUser(userStart.getText());
                BorderPane pane = FXMLLoader.load(getClass().getResource("RootLayout.fxml"));
                Parent parent2 = FXMLLoader.load(getClass().getResource("CalcLayout.fxml"));
                pane.setCenter(parent2);
                Stage stage = new Stage();
                stage.setScene(new Scene(pane));
                stage.setResizable(false);
                stage.setTitle("CalcTest");
                stage.show();
            }
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

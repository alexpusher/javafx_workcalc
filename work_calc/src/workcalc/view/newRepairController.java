package workcalc.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.SQLException;


public class newRepairController {

    @FXML
    TextField date;
    @FXML
    TextField typeProblem;
    @FXML
    TextField address;
    @FXML
    TextField phoneNumber;
    @FXML
    TextField name;
    @FXML
    Button bOk;
    @FXML
    Button bCancel;

    @FXML
    private void initialize()
    {}

    @FXML
    private void handleOk(ActionEvent event) throws SQLException {
        //add new problem use method in RequestForRepairController when user input correct data from all TextField
        if(isInputValid())
        {
            new RequestForRepairController().WriteDb(date.getText(),address.getText(),phoneNumber.getText(),typeProblem.getText(),name.getText());
            ((Node) (event.getSource())).getScene().getWindow().hide();
        }
    }

    @FXML
    private void handleCan(ActionEvent event)
    {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (date.getText() == null || date.getText().length() == 0) {
            errorMessage += "The date is not entered!\n";
        }
        if (address.getText() == null || address.getText().length() == 0) {
            errorMessage += "The address is not entered!\n";
        }

        if (phoneNumber.getText() == null || phoneNumber.getText().length() == 0) {
            errorMessage += "The phone number is not entered!\n";
        }
        if (typeProblem.getText() == null || typeProblem.getText().length() == 0) {
            errorMessage += "The type of problem is not entered!\n";
        }
        if (name.getText() == null || name.getText().length() == 0) {
            errorMessage += "The name is not entered!\n";
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

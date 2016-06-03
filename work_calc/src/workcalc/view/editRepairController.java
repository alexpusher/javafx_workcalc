package workcalc.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import workcalc.model.RequestForConn;
import workcalc.model.RequestForRepair;

import java.sql.SQLException;


public class editRepairController {


    @FXML
    TextField date;
    @FXML
    TextField address;
    @FXML
    TextField phoneNumber;
    @FXML
    TextField typeProblem;
    @FXML
    TextField name;
    @FXML
    Button bOk;

    public static RequestForRepair rfr = new RequestForRepair(0,null,null,null,null,null);

    @FXML
    private void initialize()
    {
        date.setText(rfr.getDate());
        typeProblem.setText(rfr.getTypeOfProblem());
        address.setText(rfr.getAddress());
        phoneNumber.setText(rfr.getPhoneNumber());
        name.setText(rfr.getName());
    }
    @FXML
    private void handleOk(ActionEvent event) throws SQLException {
        if(isInputValid())
        {
            new RequestForRepairController().UpdateDb(rfr.getId(),date.getText(),address.getText(),phoneNumber.getText(),typeProblem.getText(),name.getText());
            ((Node) (event.getSource())).getScene().getWindow().hide();
        }
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

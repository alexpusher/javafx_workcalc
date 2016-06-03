package workcalc.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import workcalc.model.RequestForConn;
import workcalc.model.RequestForStopConn;

import java.sql.SQLException;


public class EditStopConnController {



    @FXML
    TextField date;
    @FXML
    TextField view;
    @FXML
    TextField ip;
    @FXML
    TextField address;
    @FXML
    TextField stopDate;
    @FXML
    TextField status;
    @FXML
    TextField comment;
    @FXML
    Button bOk;

    public static RequestForStopConn rfsc = new RequestForStopConn(0,null,null,null,null,null,null,null);

    @FXML
    private void initialize()
    {
        date.setText(rfsc.getDate());
        view.setText(rfsc.getView());
        ip.setText(rfsc.getIp());
        address.setText(rfsc.getAddress());
        stopDate.setText(rfsc.getStopDate());
        status.setText(rfsc.getStatus());
        comment.setText(rfsc.getComment());
    }
    @FXML
    private void handleOk(ActionEvent event) throws SQLException {
        if(isInputValid())
        {
            new RequestForStopConnController().UpdateDb(rfsc.getId(), date.getText(),view.getText(),ip.getText(),address.getText(),stopDate.getText(),status.getText(),comment.getText());
            ((Node) (event.getSource())).getScene().getWindow().hide();
        }

    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (date.getText() == null || date.getText().length() == 0) {
            errorMessage += "The date is not entered!\n";
        }
        if (view.getText() == null || view.getText().length() == 0) {
            errorMessage += "The view is not entered!\n";
        }
        if (ip.getText() == null || ip.getText().length() == 0) {
            errorMessage += "The ip is not entered!\n";
        }
        if (address.getText() == null || address.getText().length() == 0) {
            errorMessage += "The address is not entered!\n";
        }

        if (stopDate.getText() == null ||stopDate.getText().length() == 0) {
            errorMessage += "The stop date is not entered!\n";
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

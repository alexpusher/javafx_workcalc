package workcalc.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import workcalc.model.calc;

import java.sql.SQLException;



public class newCommentController {
    @FXML
    TextField comm;

    public static calc calc = new calc(0,0,0,0,0,null);
    @FXML
    private void initialize()
    {}

    @FXML
    private void bOk(ActionEvent event) throws SQLException {
        //use method in statistics for update line
        if(isInputValid())
        {
            new statisticsLayoutController().UpdateDb(comm.getText(),
                                                      calc.getStartDay(),
                                                      calc.getOverDay(),
                                                      calc.getStartOfReceipt(),
                                                      calc.getEndOfReceipt(),
                                                      calc.getSumReceipt(),
                                                      calc.getUser());
            ((Node) (event.getSource())).getScene().getWindow().hide();
        }
    }
    @FXML
    private void bCan(ActionEvent event)
    {
        //close without save
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    private boolean isInputValid()
    {
        String errorMessage="";
        if(comm.getText()== null || comm.getText().length() == 0)
        {
            errorMessage +="The comment is not entered!\n";
        }
        if(errorMessage.length() == 0)
        {
            return true;
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please enter data in fields");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }

}

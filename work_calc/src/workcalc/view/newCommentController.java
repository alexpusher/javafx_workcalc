package workcalc.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import workcalc.model.calc;

import java.sql.SQLException;
import java.time.LocalTime;


public class newCommentController {
    @FXML
    private TextField comm;

    public static calc calc = new calc(null);
    private statisticsLayoutController sl = new statisticsLayoutController();
    private String tmp;

    @FXML
    private void initialize()
    {
    }

    @FXML
    private void bOk(ActionEvent event) throws SQLException {
        //use method in statistics for update line
        if(isInputValid())
        {
            tmp = sl.readString(calc.getDate());
            if(tmp.equals(""))
            {
                tmp = String.valueOf(LocalTime.now().getHour())+ ":"
                        + new calcOverviewController().Minute() + ":"
                        + new calcOverviewController().Second() + " : "  + comm.getText();
                sl.UpdateDb(tmp,calc.getDate());
                ((Node) (event.getSource())).getScene().getWindow().hide();
            }else
            {
                tmp += "\n" + String.valueOf(LocalTime.now().getHour())+ ":"
                        + new calcOverviewController().Minute() + ":"
                        + new calcOverviewController().Second() + " : " + comm.getText();
                sl.UpdateDb(tmp,calc.getDate());
                ((Node) (event.getSource())).getScene().getWindow().hide();
            }
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
        String errorMessage = "";
        if(comm.getText()== null || comm.getText().length() == 0)
        {
            errorMessage += "The comment is not entered!\n";
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

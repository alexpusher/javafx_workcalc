package workcalc.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.time.LocalTime;

/**
 * Created by Alex on 28.06.2016.
 */
public class getMoneyController {
    @FXML
    private TextField comm;
    @FXML
    private TextField money;

    private statisticsLayoutController sl = new statisticsLayoutController();
    private String tmp;
    private int sum;

    @FXML
    private void initialize()
    {
    }

    @FXML
    private void bOk(ActionEvent event) throws SQLException {
        //use method in statistics for update line
        if(isInputValid())
        {
            tmp = sl.readString(calcOverviewController.Date());
            sum = Integer.parseInt(money.getText());
            if(tmp.equals(""))
            {
                tmp = String.valueOf(LocalTime.now().getHour())+ ":"
                        + new calcOverviewController().Minute() + ":"
                        + new calcOverviewController().Second() + " : "  + comm.getText() + " " + sum;
                sl.UpdateDb(tmp, calcOverviewController.Date());
                calcOverviewController.calc.setOverDay(calcOverviewController.calc.getOverDay() - sum);
                ((Node) (event.getSource())).getScene().getWindow().hide();
            }else
            {
                tmp += "\n" + String.valueOf(LocalTime.now().getHour())+ ":"
                        + new calcOverviewController().Minute() + ":"
                        + new calcOverviewController().Second() + " : " + comm.getText() + " " + sum;
                sl.UpdateDb(tmp, calcOverviewController.Date());
                calcOverviewController.calc.setOverDay(calcOverviewController.calc.getOverDay() - sum);
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

    private boolean isInputValid() {
        String errorMessage = "";
        if (comm.getText() == null || comm.getText().length() == 0) {
            errorMessage += "The comment is not entered!\n";
        }
        if (money.getText() == null || money.getText().length() == 0) {
            errorMessage += "The money is not entered!\n";
        }
        else {
            try {
                Integer.parseInt(money.getText());
                if(Integer.parseInt(money.getText()) > calcOverviewController.calc.getOverDay())
                {
                    errorMessage += "The input number is greater than 'Ost k dnya'";
                }
            }catch (NumberFormatException e)
            {
                errorMessage += "The input field must be integer!\n";
            }
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

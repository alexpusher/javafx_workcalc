package workcalc.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class rootLayoutController {

    @FXML
    private void handleStatistics() throws IOException {
        /*
        *Statistics - display all information when user save
        *Use StatisticsLayout and statisticsLayoutController
        */
        Parent parent = FXMLLoader.load(getClass().getResource("StatisticsLayout.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(new Scene(parent));
        stage.setTitle("Statistics during all this time");
        stage.setResizable(false);
        stage.showAndWait();
    }
    @FXML
    private void handleDiagram() throws IOException {
        /*
        *Diagram - display diagram with total sum on month, for all month
        *Use DiagramLayout and diagramLayoutController
        */
        Parent parent = FXMLLoader.load(getClass().getResource("DiagramLayout.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(new Scene(parent));
        stage.setTitle("Month Diagram");
        stage.setResizable(false);
        stage.showAndWait();
    }
    @FXML
    private void handleRequest() throws IOException {
        /*
        *Request on connection
        *Use RequestForConnLayout and requestForConnController
        *
        */
        Parent parent = FXMLLoader.load(getClass().getResource("RequestForConnLayout.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(new Scene(parent));
        stage.setTitle("Request for Connection");
        stage.setResizable(false);
        stage.showAndWait();
    }
    @FXML
    private void handleRepair() throws IOException {
        /*
        *Request on problem with connect
        *Use RequestForRepairLayout and requestForRepairController
        */
        Parent parent = FXMLLoader.load(getClass().getResource("RequestForRepairLayout.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(new Scene(parent));
        stage.setTitle("Request for Repair");
        stage.setResizable(false);
        stage.showAndWait();
    }
    @FXML
    private void handleStopRequest() throws IOException {
        /*
        *Request on a temporary stop connection
        *Use RequestForStopConn and requestForStopConnController
        */
        Parent parent = FXMLLoader.load(getClass().getResource("RequestForStopConn.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(new Scene(parent));
        stage.setTitle("Request for Repair");
        stage.setResizable(false);
        stage.showAndWait();
    }

    @FXML
    private void handleExit() throws IOException {
        //simple exit without save data in db
        System.exit(0);
    }


}

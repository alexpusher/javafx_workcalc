package workcalc.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import workcalc.model.RequestForConn;
import workcalc.model.calc;

import java.io.IOException;
import java.sql.*;
import java.util.Optional;

public class statisticsLayoutController {
    private ObservableList<calc> data = FXCollections.observableArrayList();
    private Connection conn;
    private PreparedStatement preStat;
    private Statement stat;
    private ResultSet resSet;

    @FXML
    private TableView<calc> tableView = new TableView<>(data);

    @FXML
    private TableColumn<calc, Integer> startDayView;

    @FXML
    private TableColumn<calc, Integer> endDayView;
    @FXML
    private TableColumn<calc, Integer> startReceiptView;
    @FXML
    private TableColumn<calc, Integer> endReceiptView;
    @FXML
    private TableColumn<calc, Integer> sumReceiptView;
    @FXML
    private TableColumn<calc, String> userView;
    @FXML
    private TableColumn<calc, String> dateView;
    @FXML
    private TableColumn<calc, String> comment;

    @FXML
    private void initialize() throws SQLException {
        sql();
        ReadDb();
        startDayView.setCellValueFactory(cellData->cellData.getValue().startDayProperty().asObject());
        endDayView.setCellValueFactory(cellData->cellData.getValue().overDayProperty().asObject());
        startReceiptView.setCellValueFactory(cellData->cellData.getValue().startOfReceiptProperty().asObject());
        endReceiptView.setCellValueFactory(cellData->cellData.getValue().endOfReceiptProperty().asObject());
        sumReceiptView.setCellValueFactory(cellData->cellData.getValue().sumReceiptProperty().asObject());
        userView.setCellValueFactory(cellData->cellData.getValue().userProperty());
        dateView.setCellValueFactory(cellData->cellData.getValue().dateProperty());
        comment.setCellValueFactory(cellData->cellData.getValue().commentProperty());
        tableView.setItems(getList());
    }
    @FXML
    private void bComm() throws IOException, SQLException {
        //button for add comment for line, use NewComment and newCommentController
        int index = tableView.getSelectionModel().getSelectedIndex();
        if(index>=0)
        {
            calc calc = data.get(index);
            newCommentController.calc.setDate(calc.getDate());
            Parent parent = FXMLLoader.load(getClass().getResource("NewComment.fxml"));
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(new Scene(parent));
            stage.setTitle("Comment");
            stage.showAndWait();
            refresh();
        }else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Nothing Selected!!!");
            alert.showAndWait();
        }
    }
    @FXML
    private void bRem() throws SQLException {
        //delete comment from line and db
        int index = tableView.getSelectionModel().getSelectedIndex();
        if(index>=0)
        {
            calc calc = data.get(index);
            String tmp = calc.getComment();
            try{
                UpdateDb(tmp.substring(0,tmp.lastIndexOf("\n")), calc.getDate());
            }catch (StringIndexOutOfBoundsException e)
            {
               UpdateDb("", calc.getDate());
            }
            refresh();
        }else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Nothing Selected!!!");
            alert.showAndWait();
        }
    }
    @FXML
    private void bRemCell() throws SQLException {
        //delete cell from line and db
        int index = tableView.getSelectionModel().getSelectedIndex();
        if(index>=0)
        {
            calc calc = data.get(index);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete cell");
            alert.setHeaderText("Start of Day: " + String.valueOf(calc.getStartDay()) + "\n" +
                                "End of Day: " + String.valueOf(calc.getOverDay()) + "\n" +
                                "Start of Receipt: " + String.valueOf(calc.getStartOfReceipt()) + "\n" +
                                "End of Receipt: " + String.valueOf(calc.getEndOfReceipt()) + "\n" +
                                "Sum of Receipt: " + String.valueOf(calc.getSumReceipt()) + "\n" +
                                "User: " + String.valueOf(calc.getUser()) + "\n" +
                                "Date: " + String.valueOf(calc.getDate()) + "\n" +
                                "Comment: " + String.valueOf(calc.getComment()));
            alert.setContentText("Are you sure you want to delete cell with data?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK)
            {
                delete(String.valueOf(calc.getDate()));
                refresh();
            }
            else
            {
                refresh();
            }
        }else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Nothing Selected!!!");
            alert.showAndWait();
        }
    }
    private void refresh() throws SQLException {
        data.removeAll();
        sql();
        ReadDb();
        tableView.setItems(getList());

    }
    private Connection sql()
    {
        conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:calcData.sqlite");
            System.out.println("DataBase is connect");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return conn;
    }
    private void ReadDb() throws SQLException {
        try {
        data.clear();
        stat = conn.createStatement();
        resSet = stat.executeQuery("SELECT * FROM calc");
        while (resSet.next())
        {
            data.add(new calc(resSet.getInt("startDay"),
                              resSet.getInt("overDay"),
                              resSet.getInt("startOfReceipt"),
                              resSet.getInt("endOfReceipt"),
                              resSet.getInt("sumReceipt"),
                              resSet.getString("user"),
                              resSet.getString("date"),
                              resSet.getString("comment")));
        }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            resSet.close();
            stat.close();

        }
    }
    public void UpdateDb(String comment, String date) throws SQLException {
        try {
            sql();
            String sql= "UPDATE calc SET comment = ? where date = ?;";
            preStat = conn.prepareStatement(sql);
            preStat.setString(1, comment);
            preStat.setString(2, date);
            preStat.executeUpdate();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            preStat.close();
            conn.close();
        }
    }
    public String readString(String date) throws SQLException {
        String tmp = "";
        try{
            sql();
            stat = conn.createStatement();
            resSet = stat.executeQuery("SELECT * FROM calc");
            while(resSet.next())
            {
                if(resSet.getString("date").equals(date))
                {
                    tmp = resSet.getString("comment");
                }
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }finally {
            resSet.close();
            stat.close();
            conn.close();
        }
        return tmp;
    }
    private void delete(String date) throws SQLException {
        try{
            sql();
            stat = conn.createStatement();
            String sql= "DELETE FROM calc where date = ? ;";
            preStat = conn.prepareStatement(sql);
            preStat.setString(1, date);
            preStat.executeUpdate();
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            resSet.close();
            stat.close();
            conn.close();
        }
    }
    private ObservableList<calc> getList()
    {
        return data;
    }

}

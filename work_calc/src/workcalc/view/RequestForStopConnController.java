package workcalc.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import workcalc.model.RequestForStopConn;

import java.io.IOException;
import java.sql.*;

public class RequestForStopConnController {

    private ObservableList<RequestForStopConn> data = FXCollections.observableArrayList();
    private Connection conn;
    private PreparedStatement preStat;
    private Statement stat;
    private ResultSet resSet;

    @FXML
    private TableView<RequestForStopConn> requestStopConn = new TableView<>(data);

    @FXML
    private TableColumn<RequestForStopConn, String> date;
    @FXML
    private TableColumn<RequestForStopConn, String> view;
    @FXML
    private TableColumn<RequestForStopConn, String> ip;
    @FXML
    private TableColumn<RequestForStopConn, String> address;
    @FXML
    private TableColumn<RequestForStopConn, String> stopDate;
    @FXML
    private TableColumn<RequestForStopConn, String> status;
    @FXML
    private TableColumn<RequestForStopConn, String> comment;

    @FXML
    Button bAdd;
    @FXML
    Button bEdit;
    @FXML
    Button bRemove;



    @FXML
    private void initialize() throws SQLException {
        sql();
        ReadDb();
        date.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        view.setCellValueFactory(cellData->cellData.getValue().viewProperty());
        ip.setCellValueFactory(cellData -> cellData.getValue().ipProperty());
        address.setCellValueFactory(cellData->cellData.getValue().addressProperty());
        stopDate.setCellValueFactory(cellData -> cellData.getValue().stopDateProperty());
        status.setCellValueFactory(cellData->cellData.getValue().statusProperty());
        comment.setCellValueFactory(cellData->cellData.getValue().commentProperty());
        requestStopConn.setItems(getList());

    }

    @FXML
    private void handleAdd() throws IOException, SQLException {
        /*
        *
        * Add new stop request use NewStopConn and NewStopConnController
        *
        * */
        Parent parent = FXMLLoader.load(getClass().getResource("NewStopConn.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(new Scene(parent));
        stage.setTitle("Request for Connection");
        stage.setResizable(false);
        stage.showAndWait();
        refresh();
    }
    @FXML
    private void handleEdit() throws IOException, SQLException {
        /*
        *
        * Edit select request use EditStopConn and EditStopConnController
        *
        * */
        int index = requestStopConn.getSelectionModel().getSelectedIndex();
        if(index>=0)
        {
            RequestForStopConn rfsc = data.get(index);
            EditStopConnController.rfsc.setId(rfsc.getId());
            EditStopConnController.rfsc.setDate(rfsc.getDate());
            EditStopConnController.rfsc.setView(rfsc.getView());
            EditStopConnController.rfsc.setIp(rfsc.getIp());
            EditStopConnController.rfsc.setAddress(rfsc.getAddress());
            EditStopConnController.rfsc.setStopDate(rfsc.getStopDate());
            EditStopConnController.rfsc.setStatus(rfsc.getStatus());
            EditStopConnController.rfsc.setComment(rfsc.getComment());
            Parent parent = FXMLLoader.load(getClass().getResource("EditStopConn.fxml"));
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(new Scene(parent));
            stage.setTitle("Request for Connection");
            stage.setResizable(false);
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
    private void handleRemove() throws SQLException {
        //del select stop conn from table and db
        int index = requestStopConn.getSelectionModel().getSelectedIndex();
        if(index>=0)
        {
            RequestForStopConn rfsc = data.get(index);
            DelFromDb(rfsc.getDate(), rfsc.getView(), rfsc.getIp(), rfsc.getAddress(), rfsc.getStopDate(), rfsc.getStatus(), rfsc.getComment());
            requestStopConn.getItems().remove(index);
        }else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Nothing Selected!!!");
            alert.showAndWait();
        }
    }
    @FXML
    private void refresh() throws SQLException {
        data.removeAll();
        sql();
        ReadDb();
        requestStopConn.setItems(getList());
    }
    public Connection sql()
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

    public void WriteDb(String date, String view, String ip, String address, String stopDate, String status, String comment) throws SQLException {
        try {
            sql();
            String sql= "INSERT INTO 'requestStopConn'('date','view','ip','address','stopDate','status','comment') VALUES(?,?,?,?,?,?,?)";
            preStat = conn.prepareStatement(sql);
            preStat.setString(1, date);
            preStat.setString(2, view);
            preStat.setString(3, ip);
            preStat.setString(4, address);
            preStat.setString(5, stopDate);
            preStat.setString(6, status);
            preStat.setString(7, comment);
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
    public void ReadDb() throws SQLException {
        try {
            data.clear();
            stat = conn.createStatement();
            resSet = stat.executeQuery("SELECT * FROM requestStopConn");
            while (resSet.next())
            {
                data.add(new RequestForStopConn(resSet.getInt("id"),
                                                resSet.getString("date"),
                                                resSet.getString("view"),
                                                resSet.getString("ip"),
                                                resSet.getString("address"),
                                                resSet.getString("stopDate"),
                                                resSet.getString("status"),
                                                resSet.getString("comment")));
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            resSet.close();
            stat.close();
            conn.close();
        }
    }
    public void DelFromDb(String date, String view, String ip, String address, String stopDate, String status, String comment) throws SQLException {
        try {
            sql();
            String sql= "DELETE FROM requestStopConn where date = ? AND view = ? AND ip = ? AND address = ? AND stopDate = ?" +
                        " AND status = ? AND comment = ?;";
            preStat = conn.prepareStatement(sql);
            preStat.setString(1, date);
            preStat.setString(2, view);
            preStat.setString(3, ip);
            preStat.setString(4, address);
            preStat.setString(5, stopDate);
            preStat.setString(6, status);
            preStat.setString(7, comment);
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
    public void UpdateDb(int id, String date, String view, String ip, String address, String stopDate, String status, String comment) throws SQLException {
        try {
            sql();
            String sql= "UPDATE requestStopConn SET date = ?, view = ?, ip = ?, address = ?, stopDate = ?, status = ?, comment = ? " +
                        "where id = ?;";
            preStat = conn.prepareStatement(sql);
            preStat.setString(1, date);
            preStat.setString(2, view);
            preStat.setString(3, ip);
            preStat.setString(4, address);
            preStat.setString(5, stopDate);
            preStat.setString(6, status);
            preStat.setString(7, comment);
            preStat.setInt(8, id);
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
    private ObservableList<RequestForStopConn> getList()
    {
        return data;
    }


}

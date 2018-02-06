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
import workcalc.model.RequestForConn;
import workcalc.model.calc;

import java.io.IOException;
import java.sql.*;


public class RequestForConnController {

    private ObservableList<RequestForConn> data = FXCollections.observableArrayList();
    private Connection conn;
    private PreparedStatement preStat;
    private Statement stat;
    private ResultSet resSet;

    @FXML
    private TableView<RequestForConn> requestConn = new TableView<>(data);

    @FXML
    private TableColumn<RequestForConn, String> date;
    @FXML
    private TableColumn<RequestForConn, String> view;
    @FXML
    private TableColumn<RequestForConn, String> address;
    @FXML
    private TableColumn<RequestForConn, String> phoneNumber;
    @FXML
    private TableColumn<RequestForConn, String> nameMounter;

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
        address.setCellValueFactory(cellData->cellData.getValue().addressProperty());
        phoneNumber.setCellValueFactory(cellData->cellData.getValue().phoneNumberProperty());
        nameMounter.setCellValueFactory(cellData->cellData.getValue().nameMounterProperty());
        requestConn.setItems(getList());

    }

    @FXML
    private void handleAdd() throws IOException, SQLException {
        /*
        *
        * Add new request use NewRequestLayout and newRequestController
        *
        * */
        Parent parent = FXMLLoader.load(getClass().getResource("NewRequestLayout.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(new Scene(parent));
        stage.setTitle("New Request for Connection");
        stage.setResizable(false);
        stage.showAndWait();
        refresh();

    }
    @FXML
    private void handleEdit() throws IOException, SQLException {
        /*
        *
        * Edit select request use EditRequest and editRequestController
        *
        * */

        int index = requestConn.getSelectionModel().getSelectedIndex();
        if(index>=0)
        {
            RequestForConn rfc = data.get(index);
            editRequestController.rfc.setId(rfc.getId());
            editRequestController.rfc.setDate(rfc.getDate());
            editRequestController.rfc.setView(rfc.getView());
            editRequestController.rfc.setAddress(rfc.getAddress());
            editRequestController.rfc.setPhoneNumber(rfc.getPhoneNumber());
            editRequestController.rfc.setNameMounter(rfc.getNameMounter());
            Parent parent = FXMLLoader.load(getClass().getResource("EditRequest.fxml"));
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(new Scene(parent));
            stage.setTitle("Edit Request for Connection");
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
        //del select request from table and db
        int index = requestConn.getSelectionModel().getSelectedIndex();
        if(index>=0)
        {
            RequestForConn rfc = data.get(index);
            DelFromDb(rfc.getDate(), rfc.getView(), rfc.getAddress(), rfc.getPhoneNumber(), rfc.getNameMounter());
            requestConn.getItems().remove(index);
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
        requestConn.setItems(getList());
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

    public void WriteDb(String date, String view, String address, String phoneNum, String nameMounter) throws SQLException {
        try {
            sql();
            String sql= "INSERT INTO 'requestConn'('date','view','address','phoneNumber','nameMounter') VALUES(?,?,?,?,?)";
            preStat = conn.prepareStatement(sql);
            preStat.setString(1, date);
            preStat.setString(2, view);
            preStat.setString(3, address);
            preStat.setString(4, phoneNum);
            preStat.setString(5, nameMounter);
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
            resSet = stat.executeQuery("SELECT * FROM requestConn");
            while (resSet.next())
            {
                data.add(new RequestForConn(resSet.getInt("id"),
                                            resSet.getString("date"),
                                            resSet.getString("view"),
                                            resSet.getString("address"),
                                            resSet.getString("phoneNumber"),
                                            resSet.getString("nameMounter")));
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
    public void DelFromDb(String date, String view, String address, String phoneNum, String nameMounter) throws SQLException {
        try {
            sql();
            String sql= "DELETE FROM requestConn where date = ? AND view = ? AND address = ? AND phoneNumber = ? AND nameMounter = ?;";
            preStat = conn.prepareStatement(sql);
            preStat.setString(1, date);
            preStat.setString(2, view);
            preStat.setString(3, address);
            preStat.setString(4, phoneNum);
            preStat.setString(5, nameMounter);
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
    public void UpdateDb(int id, String date, String view, String address, String phoneNum, String nameMounter) throws SQLException {
        try {
            sql();
            String sql= "UPDATE requestConn SET date = ?, view = ?, address = ?, phoneNumber = ?, nameMounter = ? " +
                        "where id = ?;";
            preStat = conn.prepareStatement(sql);
            preStat.setString(1, date);
            preStat.setString(2, view);
            preStat.setString(3, address);
            preStat.setString(4, phoneNum);
            preStat.setString(5, nameMounter);
            preStat.setInt(6, id);
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
    private ObservableList<RequestForConn> getList()
    {
        return data;
    }


}

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
import workcalc.model.RequestForRepair;

import java.io.IOException;
import java.sql.*;


public class RequestForRepairController {

    private ObservableList<RequestForRepair> data = FXCollections.observableArrayList();
    private Connection conn;
    private PreparedStatement preStat;
    private Statement stat;
    private ResultSet resSet;

    @FXML
    private TableView<RequestForRepair> requestRepair = new TableView<>(data);

    @FXML
    private TableColumn<RequestForRepair, String> date;
    @FXML
    private TableColumn<RequestForRepair, String> typeProblem;
    @FXML
    private TableColumn<RequestForRepair, String> address;
    @FXML
    private TableColumn<RequestForRepair, String> phoneNumber;
    @FXML
    private TableColumn<RequestForRepair, String> nameMounter;

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
        address.setCellValueFactory(cellData->cellData.getValue().addressProperty());
        phoneNumber.setCellValueFactory(cellData->cellData.getValue().phoneNumberProperty());
        typeProblem.setCellValueFactory(cellData->cellData.getValue().typeOfProblemProperty());
        nameMounter.setCellValueFactory(cellData->cellData.getValue().nameProperty());
        requestRepair.setItems(getList());
    }

    @FXML
    private void handleAdd() throws IOException, SQLException {
        /*
        *
        * Add new repair use NewRepair and newRepairController
        *
        * */
        Parent parent = FXMLLoader.load(getClass().getResource("NewRepair.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(new Scene(parent));
        stage.setTitle("New Request for Repair");
        stage.setResizable(false);
        stage.showAndWait();
        refresh();
    }
    @FXML
    private void handleEdit() throws IOException, SQLException {
        /*
        *
        * Edit select repair use EditRepair and editRepairController
        *
        * */
        int index = requestRepair.getSelectionModel().getSelectedIndex();
        if(index>=0)
        {
            RequestForRepair rfr = data.get(index);
            editRepairController.rfr.setId(rfr.getId());
            editRepairController.rfr.setDate(rfr.getDate());
            editRepairController.rfr.setTypeOfProblem(rfr.getTypeOfProblem());
            editRepairController.rfr.setAddress(rfr.getAddress());
            editRepairController.rfr.setPhoneNumber(rfr.getPhoneNumber());
            editRepairController.rfr.setName(rfr.getName());
            Parent parent = FXMLLoader.load(getClass().getResource("EditRepair.fxml"));
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(new Scene(parent));
            stage.setTitle("Edit Repair");
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
        //del select repair table and db
        int index = requestRepair.getSelectionModel().getSelectedIndex();
        if(index>=0)
        {
            RequestForRepair rfr = data.get(index);
            DelFromDb(rfr.getDate(), rfr.getAddress(), rfr.getPhoneNumber(), rfr.getTypeOfProblem(), rfr.getName());
            requestRepair.getItems().remove(index);
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
        requestRepair.setItems(getList());
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

    public void WriteDb(String date, String address, String phoneNum, String typeProblem, String name) throws SQLException {
        try {
            sql();
            String sql= "INSERT INTO 'repairConn'('date','address','phoneNumber','typeProblem','name') VALUES(?,?,?,?,?)";
            preStat = conn.prepareStatement(sql);
            preStat.setString(1, date);
            preStat.setString(2, address);
            preStat.setString(3, phoneNum);
            preStat.setString(4, typeProblem);
            preStat.setString(5, name);
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
            resSet = stat.executeQuery("SELECT * FROM repairConn");
            while (resSet.next())
            {
                data.add(new RequestForRepair(resSet.getInt("id"),
                                              resSet.getString("date"),
                                              resSet.getString("address"),
                                              resSet.getString("phoneNumber"),
                                              resSet.getString("typeProblem"),
                                              resSet.getString("name")));
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
    public void DelFromDb(String date, String address, String phoneNum, String typeProblem, String name) throws SQLException {
        try {
            sql();
            String sql= "DELETE FROM repairConn where date = ? AND address = ? AND phoneNumber = ? AND typeProblem = ? AND name = ?;";
            preStat = conn.prepareStatement(sql);
            preStat.setString(1, date);
            preStat.setString(2, address);
            preStat.setString(3, phoneNum);
            preStat.setString(4, typeProblem);
            preStat.setString(5, name);
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
    public void UpdateDb(int id, String date, String address, String phoneNum, String typeProblem, String name) throws SQLException {
        try {
            sql();
            String sql= "UPDATE repairConn SET date = ?, address = ?, phoneNumber = ?, typeProblem = ?, name = ? " +
                        "where id = ?;";
            preStat = conn.prepareStatement(sql);
            preStat.setString(1, date);
            preStat.setString(2, address);
            preStat.setString(3, phoneNum);
            preStat.setString(4, typeProblem);
            preStat.setString(5, name);
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
    private ObservableList<RequestForRepair> getList()
    {
        return data;
    }
}

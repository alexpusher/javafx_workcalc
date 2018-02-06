package workcalc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import workcalc.model.RequestForConn;
import workcalc.view.calcOverviewController;
import java.io.*;
import java.sql.*;



public class Main extends Application {
    private Connection con;
    private PreparedStatement preStat;
    private ResultSet resSet;
    private Statement stat;
    private boolean check1;
    @Override
    public void start(Stage primaryStage) throws Exception {
        /*
        *
        *Load start page for enter in main page
        *Open startLayout and him controller startLayoutController
        *
        */
        if(check(check1)){
            create();
            Parent root = FXMLLoader.load(getClass().getResource("view/StartLayout.fxml"));
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("WorkCalc");
            primaryStage.setResizable(false);
            primaryStage.show();
        }else
        {
            BorderPane pane = FXMLLoader.load(getClass().getResource("view/RootLayout.fxml"));
            Parent parent = FXMLLoader.load(getClass().getResource("view/CalcLayout.fxml"));
            Parent parent2 = FXMLLoader.load(getClass().getResource("view/TextAreaLayout.fxml"));
            pane.setCenter(parent);
            pane.setBottom(parent2);
            primaryStage.setScene(new Scene(pane));
            primaryStage.setResizable(false);
            primaryStage.setTitle("WorkCalc");
            primaryStage.show();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void stop() throws SQLException {
        if(calcOverviewController.calc.getSumReceipt() == 0)
        {
            try
            {
                con = null;
                Class.forName("org.sqlite.JDBC");
                con = DriverManager.getConnection("jdbc:sqlite:calcData.sqlite");
                String sql= "DELETE FROM calc where date = ?;";
                preStat = con.prepareStatement(sql);
                preStat.setString(1, calcOverviewController.Date());
                preStat.executeUpdate();
            }catch (Exception e)
            {
                e.printStackTrace();
            }finally {
                preStat.close();
                con.close();
            }
        }
    }
    public void create() throws SQLException {

        try {
            con = null;
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:calcData.sqlite");
            String sql = "INSERT INTO 'calc'('startDay','overDay','startOfReceipt','endOfReceipt','sumReceipt','user','date','comment') VALUES(?,?,?,?,?,?,?,?)";
            preStat = con.prepareStatement(sql);
            preStat.setInt(1, 0);
            preStat.setInt(2, 0);
            preStat.setInt(3, 0);
            preStat.setInt(4, 0);
            preStat.setInt(5, 0);
            preStat.setString(6, null);
            preStat.setString(7, calcOverviewController.Date());
            preStat.setString(8, "");
            preStat.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            preStat.close();
            con.close();
        }
    }
    public boolean check(boolean as) throws IOException, SQLException {

        try {
            as = true;
            calcOverviewController.check = as;
            con = null;
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:calcData.sqlite");
            if(con != null)
            {
                System.out.println("123");
            }
            stat = con.createStatement();
            resSet = stat.executeQuery("SELECT * FROM calc");
            while (resSet.next()) {
                if (!resSet.getString("date").equals(calcOverviewController.Date())) {
                    as = true;
                    calcOverviewController.check = as;
                } else {
                    as = false;
                    calcOverviewController.check = as;
                    calcOverviewController.calc.setStartDay(resSet.getInt("startDay"));
                    calcOverviewController.calc.setOverDay(resSet.getInt("overDay"));
                    calcOverviewController.calc.setStartOfReceipt(resSet.getInt("startOfReceipt"));
                    calcOverviewController.calc.setEndOfReceipt(resSet.getInt("endOfReceipt"));
                    calcOverviewController.calc.setSumReceipt(resSet.getInt("sumReceipt"));
                    calcOverviewController.calc.setUser(resSet.getString("user"));
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            resSet.close();
            stat.close();
            con.close();
        }
        return as;
    }
}

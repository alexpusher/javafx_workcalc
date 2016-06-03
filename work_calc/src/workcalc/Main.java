package workcalc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import workcalc.view.calcOverviewController;
import java.io.*;
import java.sql.*;



public class Main extends Application {
    private Connection con;
    private PreparedStatement stat;

    @Override
    public void start(Stage primaryStage) throws Exception {
        /*
        *
        *Load start page for enter in main page
        *Open startLayout and him controller startLayoutController
        *
        */
        Parent root = FXMLLoader.load(getClass().getResource("view/StartLayout.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("WorkCalc");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void stop() throws IOException, SQLException {
        /*
        *
        * For close the window
        * Save data in db from 'calcOverviewController', which has the object instance 'calc'
        *
        *
        * */
        if(isValid())
        {
            calcOverviewController.calc.setOverDay(calcOverviewController.calc.getStartDay()+calcOverviewController.calc.getOverDay());
            try
            {
                con = null;
                Class.forName("org.sqlite.JDBC");
                con = DriverManager.getConnection("jdbc:sqlite:calcData.sqlite");
                if(con == null)
                {
                    System.out.println("");
                }else
                {
                    System.out.println("123");
                }
                String sql= "INSERT INTO 'calc'('startDay','overDay','startOfReceipt','endOfReceipt','sumReceipt','user','date') VALUES(?,?,?,?,?,?,?)";
                stat = con.prepareStatement(sql);
                stat.setInt(1,calcOverviewController.calc.getStartDay());
                stat.setInt(2,calcOverviewController.calc.getOverDay());
                stat.setInt(3,calcOverviewController.calc.getStartOfReceipt());
                stat.setInt(4,calcOverviewController.calc.getEndOfReceipt());
                stat.setInt(5,calcOverviewController.calc.getSumReceipt());
                stat.setString(6,calcOverviewController.calc.getUser());
                stat.setString(7,calcOverviewController.Date());
                stat.executeUpdate();
            }catch (Exception e)
            {
                e.printStackTrace();
            }
            finally {
                stat.close();
                con.close();
            }
        }
        else
        {
            System.exit(0);
        }
    }

    public boolean isValid()
    {
        if(calcOverviewController.calc.getSumReceipt() != 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}

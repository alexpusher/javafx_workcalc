package workcalc.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import workcalc.model.calc;

import java.sql.*;
import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Locale;


public class diagramLayoutController {
    private Connection conn;
    private Statement stat;
    private ResultSet resSet;

    @FXML
    private BarChart barChart;
    @FXML
    private CategoryAxis cAxis;

    private ObservableList monthList = FXCollections.observableArrayList();

    @FXML
    private void initialize() throws SQLException {
        String[] month = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
        monthList.addAll(Arrays.asList(month));
        cAxis.setCategories(monthList);
        diagram();
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

    public void diagram() throws SQLException {
        int[] i = new int[12];
        XYChart.Series series = new XYChart.Series<>();
        sql();
        try {
            stat = conn.createStatement();
            resSet = stat.executeQuery("SELECT * FROM calc");
            while (resSet.next())
            {
                String tmp =  resSet.getString("date");
                int tmp1 = Integer.parseInt(tmp.substring(3,5));
                if(tmp1 == 1)
                {
                    i[0] += resSet.getInt("overDay");
                }
                if(tmp1 == 2)
                {
                    i[1] += resSet.getInt("overDay");
                }
                if(tmp1 == 3)
                {
                    i[2] += resSet.getInt("overDay");
                }
                if(tmp1 == 4)
                {
                    i[3] += resSet.getInt("overDay");
                }
                if(tmp1 == 5)
                {
                    i[4] += resSet.getInt("overDay");
                }
                if(tmp1 == 6)
                {
                    i[5] += resSet.getInt("overDay");
                }
                if(tmp1 == 7)
                {
                    i[6] += resSet.getInt("overDay");
                }
                if(tmp1 == 8)
                {
                    i[7] += resSet.getInt("overDay");
                }
                if(tmp1 == 9)
                {
                    i[8] += resSet.getInt("overDay");
                }
                if(tmp1 == 10)
                {
                    i[9] += resSet.getInt("overDay");
                }
                if(tmp1 == 11)
                {
                    i[10] += resSet.getInt("overDay");
                }
                if(tmp1 == 12)
                {
                    i[11] += resSet.getInt("overDay");
                }
            }
            for(int a = 0; a<i.length; a++)
            {
                series.getData().add(new XYChart.Data<>(monthList.get(a),i[a]));
            }
            barChart.getData().add(series);

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
}

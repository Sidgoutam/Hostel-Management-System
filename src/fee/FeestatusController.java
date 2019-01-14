/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fee;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import viewroomstatus.RoomstatusController;
import viewstudents.DbConnection;

/**
 * FXML Controller class
 *
 * @author govind
 */
public class FeestatusController implements Initializable {

    @FXML
    private StackedBarChart<String, Integer> bar1;
    private DbConnection dc;
   @FXML
    private NumberAxis yAxis;
    @FXML
    private CategoryAxis xAxis;
        @FXML
    private ComboBox<String> hostel,year;
    private ObservableList<String> data;
    
     @FXML
    private Label label1;
        ObservableList<String> yearlist = FXCollections.observableArrayList("2016","2017","2018","2019","2020");
    

    @FXML
    void goback(ActionEvent event) throws IOException {
Parent s1 = FXMLLoader.load(getClass().getResource("/basic/bill.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc = new DbConnection();
        year.setDisable(true);
       year.setItems(yearlist);
        try {
            selecthostel();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FeestatusController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
      
    }    
    
       
    public void selecthostel() throws ClassNotFoundException {

        
        try {
  Connection conn = dc.Connect();
  data = FXCollections.observableArrayList(); 
  //String insert1 = "INSERT INTO user_info VALUES('anubhav','anubhav.com','')";
  //Statement stmt = (Statement) conn.createStatement();
   //stmt.executeUpdate(insert1);
  
  ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM hostel");
  while (rs.next())
  {
      String hn = rs.getString(1);
      data.add(hn);

    }
        }
    
      
        catch (SQLException ex)
                { 
                
               System.err.println("ERRor "+ex);

                }
        
         hostel.setItems(data);
         //hostelno1.setItems(data);
         
        
         
    
        
        
    }
    @FXML
    private void showyear(ActionEvent event)
    {
        year.setDisable(false);
    }
    @FXML
    private void loadchart(ActionEvent event) throws ClassNotFoundException {
          String  host = hostel.getValue().toString();
          String yr = year.getValue().toString();
        bar1.getData().clear();
        String query = "SELECT * FROM `fee month` WHERE year = '"+yr+"' AND "+host+">0";
        XYChart.Series<String,Integer> series = new XYChart.Series<>(); 
        series.setName("UnPaid money");
        XYChart.Series<String,Integer> series1 = new XYChart.Series<>();
                

        series1.setName("Paid money");
           try{
        
               bar1.setAnimated(false);
  Connection conn = dc.Connect();
  ResultSet rs = conn.createStatement().executeQuery(query);
  while (rs.next())
  {
     // String host = rs.getString(1);
      
      System.out.print(host);
      String mt = rs.getString(1);
    //  String yr = rs.getString(2);
     
      String query1 ="SELECT `total fee` FROM `pending bill` INNER JOIN `students` ON `pending bill`.reg = `students`.`reg. no` WHERE month = '"+mt+"' AND year = '"+yr+"' AND hostel = '"+host+"'";
      ResultSet rs1 = conn.createStatement().executeQuery(query1);
      
      int unpaid=0;
      while (rs1.next())
      {
           System.out.print(rs1.getInt(1)+"pend\n");
           
      unpaid+=rs1.getInt(1);
      }
      String query2 = "SELECT `total fee` FROM `paidbill` INNER JOIN `students` ON `paidbill`.reg = `students`.`reg. no` WHERE month = '"+mt+"' AND year = '"+yr+"' AND hostel = '"+host+"'";
      ResultSet rs2 = conn.createStatement().executeQuery(query2);
      int paid=0;
      while (rs2.next())
      {
           System.out.print(rs2.getInt(1)+"pad\n");
      paid+=rs2.getInt(1);
      }
      System.out.print("paid money" +paid);
       System.out.print("unpaidmoney"+unpaid);
      int tt = rs.getInt(host);
      series.getData().add(new XYChart.Data<>(rs.getString(1),tt-paid));
     // xAxis.setTickLabelRotation(-90);
      series1.getData().add(new XYChart.Data<>(rs.getString(1),paid));
  
  }

  bar1.getData().add(series1);
  bar1.getData().add(series);
   for (Series<String,Integer> serie: bar1.getData()){
            for (XYChart.Data<String, Integer> item: serie.getData()){
                item.getNode().setOnMouseEntered((MouseEvent event1) -> {
                    //System.out.println("Month"+item.getXValue().toString());
                    label1.setText(item.getYValue().toString());
                   
                    //System.out.println("paid"+item.getYValue().toString());
                });
                item.getNode().setOnMouseExited((MouseEvent event1) -> {
                    //System.out.println("Month"+item.getXValue().toString());
                   label1.setText("");
                });
                
            }
        }
           }
             catch (SQLException ex)
                { 
                
               System.err.println("ERRor "+ex);

                }
           //bt1.setDisable(true);
           bar1.setCategoryGap(30);
    }
    
    
     @FXML
    void showdata(MouseEvent event) throws ClassNotFoundException, SQLException {
        
      
   for (final XYChart.Series<String, Integer> data : bar1.getData()) {
        data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET,
        new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent e) {
            int curr=0,cap=0;
          //label.setTranslateX(e.getSceneX());
           //label.setTranslateY(e.getSceneY());
           String host= data.getName();
           try {
           Connection conn = dc.Connect();
           ResultSet rs1 = conn.createStatement().executeQuery("SELECT * FROM hostel WHERE hostel_name = '"+host+"' ");
           rs1.next();
           curr += Integer.parseInt(rs1.getString(3));
           cap += Integer.parseInt(rs1.getString(2));
           
           } catch (ClassNotFoundException ex) {
                Logger.getLogger(RoomstatusController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(RoomstatusController.class.getName()).log(Level.SEVERE, null, ex);
            }
           System.out.print("filled is "+curr+"\ntotal is "+cap);
            //label.setText();
           
            
                
            
         }
    });
}
   
        
    }
    
    
}

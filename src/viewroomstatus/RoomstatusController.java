/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewroomstatus;

import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import viewstudents.DbConnection;

/**
 * FXML Controller class
 *
 * @author govind
 */
public class RoomstatusController implements Initializable {
     

    
   // private BarChart<String, Integer> bar1;
    private DbConnection dc;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private CategoryAxis xAxis;
     @FXML
    private StackedBarChart<String,Integer> bar1;
    @FXML
    public PieChart pi1;
    @FXML
    private Label label;
    
    @FXML
    private ComboBox hostel;
   private ObservableList<Object> data;

     
    /**
     * Initializes the controller class.
     */
   
    
    public void choosehostel() throws ClassNotFoundException {
        
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc = new DbConnection();
        try {
            loadpichart();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RoomstatusController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RoomstatusController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            choosehostel();
            
    } catch (ClassNotFoundException ex) {
            Logger.getLogger(RoomstatusController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }   
     @FXML
    void bill(ActionEvent event) throws IOException {
Parent s1 = FXMLLoader.load(getClass().getResource("/basic/bill.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }
    @FXML
    void mess(ActionEvent event) throws IOException {
Parent s1 = FXMLLoader.load(getClass().getResource("/basic/mess.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }
    @FXML
    void dashboard(ActionEvent event) throws IOException {
Parent s1 = FXMLLoader.load(getClass().getResource("/viewroomstatus/roomstatus.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }
      @FXML
    void home(MouseEvent event) throws IOException {
        Parent s1 = FXMLLoader.load(getClass().getResource("/home/g1.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();

    }

    @FXML
    void logout(MouseEvent event) throws IOException {
 Alert alert;
         alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setTitle("Confirmation ");
         alert.setHeaderText("OK");
         alert.setContentText("are you sure you want to logout?");
         Optional<ButtonType> result = alert.showAndWait();
         if (result.get() == ButtonType.OK)
         {
               Parent s1 = FXMLLoader.load(getClass().getResource("/login/loginpage.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
         }
    }
    @FXML
    void creator(MouseEvent event) throws IOException {

Parent s1 = FXMLLoader.load(getClass().getResource("/creator/creator.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }
        @FXML
    void rules(MouseEvent event) throws IOException {

Parent s1 = FXMLLoader.load(getClass().getResource("/rules/rule.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }
      @FXML
    void rent(ActionEvent event) throws IOException {
Parent s1 = FXMLLoader.load(getClass().getResource("/basic/rent.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }


    @FXML
    void rooms(ActionEvent event) throws IOException {
Parent s1 = FXMLLoader.load(getClass().getResource("/basic/rooms.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }

    @FXML
    void search(ActionEvent event) throws IOException {
Parent s1 = FXMLLoader.load(getClass().getResource("/search/search.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }

    @FXML
    void setting(ActionEvent event) throws IOException {
Parent s1 = FXMLLoader.load(getClass().getResource("/resetpassword/sidemain.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }

    @FXML
    void shiftroom(ActionEvent event) throws IOException {
Parent s1 = FXMLLoader.load(getClass().getResource("/basic/shift.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }

    @FXML
    void student(ActionEvent event) throws IOException {
Parent s1 = FXMLLoader.load(getClass().getResource("/basic/student.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }
    

      @FXML
    void showdata(MouseEvent event) throws ClassNotFoundException, SQLException {
        
      
   for (final PieChart.Data data : pi1.getData()) {
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
           
            label.setText("current student is "+curr+"\ntotal capacity is "+cap);
           
            
                
            
         }
    });
}
   
        
    }
    @FXML
    void hidedata(MouseEvent event)
    {
    label.setText("");
    }
    
    
    @FXML
    void printok(ActionEvent event) throws ClassNotFoundException, SQLException {
loadchart(hostel.getValue().toString());
    }

    
    private void loadchart(String newValue) throws ClassNotFoundException, SQLException {
        String hno = newValue.toString();
        bar1.getData().clear();
        String query = "SELECT * FROM room  WHERE hostel_name = '"+hno+"' ";
        XYChart.Series<String,Integer> series = new XYChart.Series<>(); 
        series.setName("filled");
        XYChart.Series<String,Integer> series1 = new XYChart.Series<>();
                

        series1.setName("vacant");
           try{
               bar1.setAnimated(false);
  Connection conn = dc.Connect();
  ResultSet rs = conn.createStatement().executeQuery(query);
  while (rs.next())
  {
      
      series.getData().add(new XYChart.Data<>(rs.getString(3),rs.getInt(5)));
      xAxis.setTickLabelRotation(-90);
      series1.getData().add(new XYChart.Data<>(rs.getString(3),rs.getInt(4)-rs.getInt(5)));
  
  }

  bar1.getData().add(series);
  bar1.getData().add(series1);
           }
             catch (SQLException ex)
                { 
                
               System.err.println("ERRor "+ex);

                }
           //bt1.setDisable(true);
           bar1.setCategoryGap(30);
    }
           
         private void loadpichart() throws ClassNotFoundException, SQLException   {
            Connection conn = dc.Connect();
           ObservableList<PieChart.Data> List= FXCollections.observableArrayList();
           String query1 = "SELECT * FROM hostel";
           ResultSet rs = conn.createStatement().executeQuery(query1);
  while (rs.next())
  {
       List.add(new PieChart.Data(rs.getString(1),Integer.parseInt(rs.getString(3))));
       
  }
   
  List.stream().forEach(pieData -> {
            System.out.println(pieData.getName() + ": "
            + pieData.getPieValue());
        });
   pi1.setData(List);
   
   
           
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delete;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import viewstudents.DbConnection;

/**
 * FXML Controller class
 *
 * @author govind
 */
public class DeleteroomController implements Initializable {

    @FXML
    private ComboBox Hostelno;
    @FXML
    private ComboBox Roomno;
    private DbConnection dc;
    private ObservableList<Object> data;
    private ObservableList<Object> data1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc = new DbConnection();
    }    

    @FXML
    private void rooms(ActionEvent event) throws IOException {
        Parent s1 = FXMLLoader.load(getClass().getResource("/basic/rooms.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }

    @FXML
    private void selecthostel(MouseEvent event) throws ClassNotFoundException {
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
        
         Hostelno.setItems(data);
        
    }

    @FXML
    private void selectroom(MouseEvent event) throws ClassNotFoundException {
        
         
        
            String temp = (String) Hostelno.getValue();
               
        try {
  Connection conn = dc.Connect();
  data1 = FXCollections.observableArrayList(); 
  //String insert1 = "INSERT INTO user_info VALUES('anubhav','anubhav.com','')";
  //Statement stmt = (Statement) conn.createStatement();
   //stmt.executeUpdate(insert1);
  
  ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM room WHERE hostel_name ='"+temp+ "'");
  while (rs.next())
  {
      String rn = rs.getString(3);
      data1.add(rn);

    }
        }
    
      
        catch (SQLException ex)
                { 
                
               System.err.println("ERRor "+ex);

                }
         
        Roomno.setItems(data1);
        
    }

    @FXML
    private void delroom(ActionEvent event) throws ClassNotFoundException, IOException {
        
         Alert alert;
         alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setTitle("Confirmation ");
         alert.setHeaderText("OK");
         alert.setContentText("are you sure you want to delete this room?");
         Optional<ButtonType> result = alert.showAndWait();
         if (result.get() == ButtonType.OK)
         {
             String hno = (String) Hostelno.getValue();
         String rno = (String) Roomno.getValue();
         
         try {
          Connection conn = dc.Connect();
          ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM students WHERE room = '"+rno+"' AND hostel = '"+hno+"'");
          if (rs.next())
          {
          
          Alert alert1;
         alert1 = new Alert(Alert.AlertType.WARNING);
         alert1.setTitle("Warning ");
         alert1.setHeaderText("Not allowed");
         alert1.setContentText("first empty the room");
          alert1.showAndWait();
            Parent s1 = FXMLLoader.load(getClass().getResource("/basic/rooms.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
          
          
          }
          else
          {
          
                String query1 = "SELECT * FROM room WHERE `room_no` = '"+rno+"' AND hostel_name = '"+hno+"' ";
            ResultSet rs1 = conn.createStatement().executeQuery(query1);
            rs1.next();
            int cap = Integer.parseInt(rs1.getString(4));
            
            String query = "DELETE FROM `room` WHERE `room_no` = '"+rno+"' AND hostel_name = '"+hno+"'";
             Statement stmt2 = conn.createStatement();
             stmt2.execute(query);
             
              String query2 = "UPDATE `hostel` SET `capacity` = `capacity` -'"+cap+"' WHERE  `hostel_name` = '" +hno + "' ";
   Statement stmt = conn.createStatement();
   stmt.executeUpdate(query2);
             
          
            
     
              Alert alert1;
         alert1 = new Alert(Alert.AlertType.INFORMATION);
         alert1.setTitle("Sucessfull ");
         alert1.setHeaderText("Done");
         alert1.setContentText("room deleted sucessfully");
          alert1.showAndWait();
            Parent s1 = FXMLLoader.load(getClass().getResource("/basic/rooms.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
          }
          
         }
          catch (SQLException ex)
                { 
                
               System.err.println("ERRor "+ex);

                }
         }
         else
         {
           Parent s1 = FXMLLoader.load(getClass().getResource("/basic/rooms.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();}
    }
    
}

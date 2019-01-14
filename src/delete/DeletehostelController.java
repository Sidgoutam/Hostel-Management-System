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
public class DeletehostelController implements Initializable {

    private DbConnection dc;
    private ObservableList<Object> data;
    
     @FXML
    private ComboBox Hostelno;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc = new DbConnection();
    }    

    @FXML
    private void loadhostel(MouseEvent event) throws ClassNotFoundException {
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
    void rooms(ActionEvent event) throws IOException {
Parent s1 = FXMLLoader.load(getClass().getResource("/basic/rooms.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }

    @FXML
    private void deletehostel(ActionEvent event) throws ClassNotFoundException, IOException {
        
         Alert alert;
         alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setTitle("Confirmation ");
         alert.setHeaderText("OK");
         alert.setContentText("are you sure you want to delete this hostel?");
         Optional<ButtonType> result = alert.showAndWait();
         if (result.get() == ButtonType.OK)
         {
         String hno = (String) Hostelno.getValue();
         try {
          Connection conn = dc.Connect();
          ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM room WHERE hostel_name = '"+hno+"'");
          if (rs.next())
          {
          
          Alert alert1;
         alert1 = new Alert(Alert.AlertType.WARNING);
         alert1.setTitle("Warning ");
         alert1.setHeaderText("Not allowed");
         alert1.setContentText("first delete all the room of this hostel");
          alert1.showAndWait();
          Parent s1 = FXMLLoader.load(getClass().getResource("/basic/rooms.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
          
          
          }
          else
          {
          
            String query = "DELETE FROM hostel WHERE `hostel_name` = '"+hno+"'";
             Statement stmt2 = conn.createStatement();
             stmt2.execute(query);
              Alert alert1;
         alert1 = new Alert(Alert.AlertType.INFORMATION);
         alert1.setTitle("Sucessfull ");
         alert1.setHeaderText("Done");
         alert1.setContentText("hostel deleted sucessfull");
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
        window.show();
         
         }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addhostel;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import javafx.event.ActionEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import viewstudents.DbConnection;

/**
 * FXML Controller class
 *
 * @author govind
 */
public class Add_new_hostelController implements Initializable {

    
     @FXML
    private Button submit;
    private DbConnection dc;
    
    @FXML
    private JFXTextField h_name;
    private PreparedStatement pst;
   
   @FXML
    void rooms(ActionEvent event) throws IOException {
        Parent s1 = FXMLLoader.load(getClass().getResource("/basic/rooms.fxml"));
        Scene s1scene = new Scene(s1);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }
   @FXML
    void hcreate(ActionEvent event) throws ClassNotFoundException, IOException {
 System.out.println("you click this");
 Alert alert;
         alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setTitle("Confirmation ");
         alert.setHeaderText("OK");
         alert.setContentText("are you sure?");
         Optional<ButtonType> result = alert.showAndWait();
         if (result.get() == ButtonType.OK)
         {
          String hostname = h_name.getText();
          if (hostname.length()<2 || hostname.isEmpty())
          {
          
          Alert alert1;
         alert1 = new Alert(Alert.AlertType.ERROR);
         alert1.setTitle("HOSTEL "+hostname+"Not ADDED");
         alert1.setHeaderText("NOt allowed");
         alert1.setContentText("Hostel name must be greater than length 2");
         alert1.showAndWait();
          }
          else
          {
         try {
Connection conn = dc.Connect();
 
         
          ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM hostel WHERE hostel_name = '"+hostname+"'");
          if (rs.next()) {    Alert alert1;
         alert1 = new Alert(Alert.AlertType.ERROR);
         alert1.setTitle("HOSTEL "+hostname+"Not ADDED");
         alert1.setHeaderText("Hostel name already existed");
         alert1.setContentText("change hostel name");
         alert1.showAndWait();
         Parent s1 = FXMLLoader.load(getClass().getResource("/basic/rooms.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();}
          else {
  String query = "INSERT INTO `hostel` (hostel_name) VALUES(?)";
   pst = conn.prepareStatement(query);
   pst.setString(1, hostname);
   pst.execute();
   Alert alert1;
         alert1 = new Alert(Alert.AlertType.INFORMATION);
         alert1.setTitle("Sucessfull ");
         alert1.setHeaderText("OK");
         alert1.setContentText("Hostel Added Sucessfully!");
         alert1.showAndWait();
         
         String qry = "ALTER TABLE `fee month` ADD  "+hostname+" INT NOT NULL DEFAULT 0";
         Statement st2 = conn.createStatement();
         st2.execute(qry);
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
    }
         else
         {
             System.out.println("koi na");
             Parent s1 = FXMLLoader.load(getClass().getResource("/basic/rooms.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
         }
    }


    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 
        dc = new DbConnection();
        
        
    }    
    
}

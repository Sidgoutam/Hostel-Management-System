/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addrooms;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import viewstudents.DbConnection;
import viewstudents.students;

/**
 * FXML Controller class
 *
 * @author govind
 */
public class AddnewroomsController implements Initializable {

    
     @FXML
    private JFXComboBox<String> hostname;

    @FXML
    private JFXTextField roomname;

    @FXML
    private JFXTextField roomcap;
    
     private ObservableList<String>data;
    private DbConnection dc;


    
        ObservableList<String>  hostellist = FXCollections.observableArrayList("hostel1","hostel2","hostel3");
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
    void addroom(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {

        
        
        Alert alert;
         alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setTitle("Confirmation ");
         alert.setHeaderText("OK");
         alert.setContentText("are you sure?");
         Optional<ButtonType> result = alert.showAndWait();
         if (result.get() == ButtonType.OK)
         { 
               String room = roomname.getText();
        String cap1 = roomcap.getText();
      int cap = Integer.parseInt(cap1);  
        String hostno = hostname.getValue();
         if(room.isEmpty()  || cap<1)
         {
          Alert alert1;
         alert1 = new Alert(Alert.AlertType.ERROR);
         alert1.setTitle("Room "+room+" in hostel "+hostno+"Not ADDED");
         alert1.setHeaderText("NOt allowed");
         alert1.setContentText("room name not be empty or capacity must be greater than 0");
         alert1.showAndWait();
         
         
         }
         else
         {
             
             
         try {
Connection conn = dc.Connect();

        
         ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM room WHERE hostel_name = '"+hostno+"' AND room_no = '"+room+"'");
          if (rs.next()) {    Alert alert1;
         alert1 = new Alert(Alert.AlertType.ERROR);
         alert1.setTitle("Room "+room+" in hostel "+hostno+"Not ADDED");
         alert1.setHeaderText("NOt allowed");
         alert1.setContentText("change room name");
         alert1.showAndWait();
         Parent s1 = FXMLLoader.load(getClass().getResource("/basic/rooms.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();}
          else {
  String query = "INSERT INTO `room` (hostel_name,room_no,capacity) VALUES(?,?,?)";
   pst = conn.prepareStatement(query);
   pst.setString(1,hostno);
    pst.setString(2,room);
     pst.setString(3,cap1);
   
   pst.execute();

   
   String query2 = "UPDATE hostel SET capacity = `capacity` + '"+cap+"' WHERE  hostel_name = '" +hostno + "' ";
   Statement stmt = conn.createStatement();
   stmt.executeUpdate(query2);
     Alert alert1;
         alert1 = new Alert(Alert.AlertType.INFORMATION);
         alert1.setTitle("Sucessfull ");
         alert1.setHeaderText("OK");
         alert1.setContentText("ROOM "+room+" Added Sucessfully!");
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
         }
         else
         {
             
Parent s1 = FXMLLoader.load(getClass().getResource("/basic/rooms.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
             System.out.println("koi na");
         }
    }

        
        
    
    @FXML
    void selecthostel(MouseEvent event) throws ClassNotFoundException {

        
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
         hostname.setItems(data);
    }
    
    @FXML
    void selecthostel1(MouseEvent event) {

    }
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc = new DbConnection();
         hostname.setValue(null);
       
       // hostname.getItems().addAll("hostel A", "hostel B", "hostel  C");
    }    
    
}

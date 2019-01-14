/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package changeroom;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import viewstudents.DbConnection;

/**
 * FXML Controller class
 *
 * @author govind
 */
public class Shift_to_emptyRoomController implements Initializable {

    @FXML
    private JFXTextField reg;
    @FXML
    private Pane p1;
    
    @FXML
    private JFXButton submit;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField chostel;
    @FXML
    private JFXTextField croom;
    @FXML
    private Pane p2;
   
    @FXML
    private JFXComboBox room;
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
        p1.setVisible(false);
        p2.setVisible(false);
        submit.setDisable(true);
        room.getSelectionModel().selectedItemProperty().addListener((ObservableValue v , Object oldValue, Object newValue) -> {submit.setDisable(false);});
    }    
@FXML
    void shift(ActionEvent event) throws IOException {
Parent s1 = FXMLLoader.load(getClass().getResource("/basic/shift.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }
    @FXML
    private void checkreg(ActionEvent event) throws ClassNotFoundException, SQLException {
        
       String rg = reg.getText();
       if (rg.isEmpty()) 
       {
       
            Alert alert1;
         alert1 = new Alert(Alert.AlertType.ERROR);
         alert1.setTitle("Reg. no not exist");
         alert1.setHeaderText("NOt allowed");
         alert1.setContentText("fill the registraion no first");
         alert1.showAndWait();
       
       }
       else
       {
    
       try
       {
       Connection conn = dc.Connect();
       data = FXCollections.observableArrayList(); 

        String query = "SELECT * FROM students WHERE `reg. no` = '"+rg+"'";
        ResultSet rs = conn.createStatement().executeQuery(query);
        if (rs.next())
        {
   
    name.setText(rs.getString(5));
    chostel.setText(rs.getString(2));
            croom.setText(rs.getString(3));
           
            p1.setVisible(true);
            reg.setDisable(true);
        }
        else
        {
                 Alert alert1;
         alert1 = new Alert(Alert.AlertType.ERROR);
         alert1.setTitle("Reg no "+rg+"Not Exist");
         alert1.setHeaderText("NOt allowed");
         alert1.setContentText("Enter a valid reg no");
         alert1.showAndWait();
        }
       }
       catch (SQLException ex)
                { 
                
               System.err.println("ERRor "+ex);

                }
       }
       
    }
    @FXML
    void emptyroom(MouseEvent event) throws IOException {
Parent s1 = FXMLLoader.load(getClass().getResource("/changeroom/shift_to_empty room.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }
    @FXML
    private void changeroom(ActionEvent event ) throws ClassNotFoundException, SQLException, IOException
    {
        String rg = reg.getText();
        String rm = (String) room.getValue();
        String oldrm = croom.getText();
       
      
        try {
            Connection conn = dc.Connect();
           String query2 = "UPDATE students SET room = '"+rm+"' WHERE  `reg. no` = '" +rg + "' ";
   Statement stmt = conn.createStatement();
   stmt.executeUpdate(query2);
     String query3 = "UPDATE room SET current = `current`+1 WHERE  room_no = '" +rm + "' ";
   Statement stmt3 = conn.createStatement();
   stmt.executeUpdate(query3);
      String query4 = "UPDATE room SET current = `current`-1 WHERE  room_no = '" +oldrm + "' ";
   Statement stmt4 = conn.createStatement();
   stmt.executeUpdate(query4);
     Alert alert3;
         alert3 = new Alert(Alert.AlertType.INFORMATION);
         alert3.setTitle("ROOM CHANGE");
         alert3.setHeaderText("sucessfull");
         alert3.setContentText("room changed "+oldrm+" to " +rm );
         alert3.showAndWait();
         Parent s1 = FXMLLoader.load(getClass().getResource("/basic/shift.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
        }
        catch (SQLException ex)
                { 
                
               System.err.println("ERRor "+ex);

                }
    
        
        
    }
    

    @FXML
    private void showemptyroom(ActionEvent event) throws ClassNotFoundException {
        p2.setVisible(true);
        reg.setEditable(false);
        
        
            String temp = (String) chostel.getText();
               
        try {
  Connection conn = dc.Connect();
  data1 = FXCollections.observableArrayList(); 
  //String insert1 = "INSERT INTO user_info VALUES('anubhav','anubhav.com','')";
  //Statement stmt = (Statement) conn.createStatement();
   //stmt.executeUpdate(insert1);
  
  ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM room WHERE hostel_name ='"+temp+ "' AND capacity>current");
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
       
        room.setItems(data1);
        
    }
    
}

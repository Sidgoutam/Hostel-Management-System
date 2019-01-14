/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package changeroom;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import viewstudents.DbConnection;

/**
 * FXML Controller class
 *
 * @author govind
 */
public class Swap_to_roomController implements Initializable {

    @FXML
    private JFXTextField reg1;
    
    @FXML
    private AnchorPane pane2;
    @FXML
    private JFXTextField reg2;
    
    @FXML
    private JFXButton check1,check2;
    @FXML
    private Pane p1;
    @FXML
    private JFXTextField name1;
    @FXML
    private JFXTextField hostel1;
    @FXML
    private JFXTextField room1;
    @FXML
    private Pane p2;
    @FXML
    private JFXTextField name2;
    @FXML
    private JFXTextField hostel2;
    @FXML
    private JFXTextField room2;
    @FXML
    private JFXButton swap;
    private DbConnection dc;
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
        swap.setDisable(true);
        pane2.setVisible(false);
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
    private void checkreg1(ActionEvent event) throws ClassNotFoundException {
            String rg1 = reg1.getText();
            if (rg1.isEmpty())
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
       data1 = FXCollections.observableArrayList(); 

        String query = "SELECT * FROM students WHERE `reg. no` = '"+rg1+"'";
        ResultSet rs = conn.createStatement().executeQuery(query);
    if(rs.next())
    {
    name1.setText(rs.getString(5));
    hostel1.setText(rs.getString(2));
            room1.setText(rs.getString(3));
            p1.setVisible(true);
            pane2.setVisible(true);
            reg1.setEditable(false);
    }
    else
    {
             Alert alert1;
         alert1 = new Alert(Alert.AlertType.ERROR);
         alert1.setTitle("Reg no "+rg1+"Not Exist");
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
    private void checkreg2(ActionEvent event) throws ClassNotFoundException {
                 String rg1 = reg2.getText();
                 if(rg1.isEmpty())
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
       data1 = FXCollections.observableArrayList(); 

        String query = "SELECT * FROM students WHERE `reg. no` = '"+rg1+"'";
        ResultSet rs = conn.createStatement().executeQuery(query);
    if(rs.next())
    {
    name2.setText(rs.getString(5));
    hostel2.setText(rs.getString(2));
            room2.setText(rs.getString(3));
            p2.setVisible(true);
            reg2.setEditable(false);
            swap.setDisable(false);
    }
    else
    {
        
             Alert alert1;
         alert1 = new Alert(Alert.AlertType.ERROR);
         alert1.setTitle("Reg no "+rg1+"Not Exist");
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
    void refresh(ActionEvent event) throws IOException {
Parent s1 = FXMLLoader.load(getClass().getResource("/changeroom/swap_to_room.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }
    @FXML
    private void swaproom(ActionEvent event) throws ClassNotFoundException, IOException {
        
        String re1 = reg1.getText();
        String re2 = reg2.getText();
        String hl1 = hostel1.getText();
        String hl2 = hostel2.getText();
        String rm1 = room1.getText();
        String rm2 = room2.getText();
        try {
            Connection conn = dc.Connect();
           String query = "UPDATE `students` SET `room` = '"+rm1+"' , hostel = '"+hl1+"' WHERE  `reg. no` = '" +re2 + "' ";
   Statement stmt = conn.createStatement();
   stmt.executeUpdate(query);
           String query1 = "UPDATE `students` SET `room` = '"+rm2+"' , hostel = '"+hl2+"' WHERE  `reg. no` = '" +re1 + "' ";
   Statement stmt1 = conn.createStatement();
   stmt.executeUpdate(query1);
    Alert alert3;
         alert3 = new Alert(Alert.AlertType.INFORMATION);
         alert3.setTitle("ROOM CHANGE");
         alert3.setHeaderText("sucessfull");
         alert3.setContentText("room changed between reg no "+re1+" && reg no " +re2 );
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
    
}

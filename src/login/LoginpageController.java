/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author govind
 */
public class LoginpageController implements Initializable {

    
   @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;
     @FXML
    private Label label;
    
       




   
    private DbConnection dc;
    private ObservableList<Object> data;


    /**
     * Initializes the controller class.
     */
  

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dc = new DbConnection();
        
        // TODO
    }    

    /**
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void forget(ActionEvent event) throws IOException {
        Parent s1 = FXMLLoader.load(getClass().getResource("/forgotpassword/forgot_password.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }
    @FXML
    public void login(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        
        String uname = username.getText();
        String pword = password.getText();
        System.out.print(uname);
        System.out.print(pword);
        try {
  Connection conn = dc.Connect();
  data = FXCollections.observableArrayList(); 
  //String insert1 = "INSERT INTO user_info VALUES('anubhav','anubhav.com','')";
  //Statement stmt = (Statement) conn.createStatement();
   //stmt.executeUpdate(insert1);
  
  ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM `admin_panel` WHERE userid = '"+uname+"'");
  if (rs.next())
  {
      if (rs.getString(3).equals(pword))
      {
           Parent s1 = FXMLLoader.load(getClass().getResource("/home/g1.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
          
      }
      else
      {
         Alert alert1;
         alert1 = new Alert(Alert.AlertType.ERROR);
         alert1.setTitle("Wrong password");
         alert1.setHeaderText("permission denied");
         alert1.setContentText("enter the correct password");
         alert1.showAndWait();
      }
      
  }
  else
  {
     Alert alert1;
         alert1 = new Alert(Alert.AlertType.ERROR);
         alert1.setTitle("user not exist");
         alert1.setHeaderText("NOt allowed");
         alert1.setContentText("user is not existed");
         alert1.showAndWait();
  
  
  }
        }
         catch (SQLException ex)
                { 
                
               System.err.println("ERRor "+ex);

                }
       
    }
    

    @FXML
    private void loginhandle(ActionEvent event) {
    }
    
}

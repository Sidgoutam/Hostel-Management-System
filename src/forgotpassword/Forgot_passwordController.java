/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forgotpassword;

import com.jfoenix.controls.JFXButton;
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
import javafx.stage.Stage;
import viewstudents.DbConnection;

/**
 * FXML Controller class
 *
 * @author govind
 */
public class Forgot_passwordController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private JFXButton submit;
    @FXML
    private JFXTextField question;
 @FXML
    private JFXTextField username;
    @FXML
    private JFXButton ht;
    @FXML
    private JFXTextField answer;
    private DbConnection dc;
    private ObservableList<Object> data;
    
    private String hnt ="";
    @FXML
    public void changescene(ActionEvent event) throws IOException {
        Parent s2 = FXMLLoader.load(getClass().getResource("/login/loginpage.fxml"));
        Scene s2scene = new Scene(s2);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s2scene);
        window.show();
    }
       @FXML
    void showquestion(ActionEvent event) throws ClassNotFoundException, SQLException {
String uno = username.getText();
try {
  Connection conn = dc.Connect();
  data = FXCollections.observableArrayList(); 
  //String insert1 = "INSERT INTO user_info VALUES('anubhav','anubhav.com','')";
  //Statement stmt = (Statement) conn.createStatement();
   //stmt.executeUpdate(insert1);
  
  ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM `admin_panel` WHERE userid = '"+uno+"'");
  if (rs.next())
  {
      question.setText(rs.getString(5));
      username.setEditable(false);
      hnt = rs.getString(4);
      submit.setDisable(false);
      question.setDisable(false);
      answer.setDisable(false);
      ht.setDisable(false);
      
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
    void gethint(ActionEvent event) {
          Alert alert1;
         alert1 = new Alert(Alert.AlertType.ERROR);
         alert1.setTitle("Hint");
         alert1.setHeaderText("your hint");
         alert1.setContentText(hnt);
         alert1.showAndWait();

    }
    @FXML
    public void reset(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        
        String uno = username.getText();
        String ans = answer.getText();
try {
  Connection conn = dc.Connect();
  data = FXCollections.observableArrayList(); 
  //String insert1 = "INSERT INTO user_info VALUES('anubhav','anubhav.com','')";
  //Statement stmt = (Statement) conn.createStatement();
   //stmt.executeUpdate(insert1);
  
  ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM `admin_panel` WHERE userid = '"+uno+"'");
  if (rs.next())
  {
  if (rs.getString(6).equals(ans))
  {
  Parent s2 = FXMLLoader.load(getClass().getResource("/resetpassword/sidemain.fxml"));
        Scene s2scene = new Scene(s2);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s2scene);
        window.show();
  
  
  }
  else
  {
        Alert alert1;
         alert1 = new Alert(Alert.AlertType.ERROR);
         alert1.setTitle("Wrong answer");
         alert1.setHeaderText("permission denied");
         alert1.setContentText("enter the correct answer");
         alert1.showAndWait();
  
  }
  
  }
        
    }
 catch (SQLException ex)
                { 
                
               System.err.println("ERRor "+ex);

                }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc = new DbConnection();
        submit.setDisable(true);
        question.setDisable(true);
        answer.setDisable(true);
    }    
    
}

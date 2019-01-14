/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resetpassword;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import viewstudents.DbConnection;

/**
 * FXML Controller class
 *
 * @author govind
 */
public class PasswordController implements Initializable {

    @FXML
    private TextField user;
    @FXML
    private TextField conp;
    @FXML
    private TextField newp;
    @FXML
    private TextField oldp,an,qn,hint;

    
    private String pwd="";
    private DbConnection dc;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc = new DbConnection();
    }    

    @FXML
    private void submit(ActionEvent event) throws ClassNotFoundException {
        
        if (oldp.getText().isEmpty() || newp.getText().isEmpty() || conp.getText().isEmpty() || hint.getText().isEmpty() || qn.getText().isEmpty() || an.getText().isEmpty())
        {
        
         Alert alert2;
  alert2 = new Alert(Alert.AlertType.ERROR);
         alert2.setTitle("empty field");
         alert2.setHeaderText("not allowed");
         alert2.setContentText("first fill  the all textfields");
         alert2.showAndWait();
        }
        
        else {
        if (!oldp.getText().equals(pwd))
        {
          Alert alert2;
  alert2 = new Alert(Alert.AlertType.ERROR);
         alert2.setTitle("invalid password");
         alert2.setHeaderText("wrong password");
         alert2.setContentText("first enter the correct password");
         alert2.showAndWait();
        
        }
        else
        {
        if (!newp.getText().equals(conp.getText()))
        {
              Alert alert2;
  alert2 = new Alert(Alert.AlertType.ERROR);
         alert2.setTitle("invalid password");
         alert2.setHeaderText("password not match");
         alert2.setContentText("please fill the correct password in both textfiled");
         alert2.showAndWait();
        
        }
        else
        {
        try 
        {
            String pd = newp.getText();
            String ht = hint.getText();
            String q = qn.getText();
            String a = an.getText();
            Connection conn = dc.Connect();
            String sql = "UPDATE `admin_panel` SET `password`= '"+pd+"',`hint`='"+ht+"',`question`='"+q+"',`answer`='"+a+"' WHERE `userid` = '"+user.getText()+"'";
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
              Alert alert2;
  alert2 = new Alert(Alert.AlertType.INFORMATION);
         alert2.setTitle(" password update");
         alert2.setHeaderText("sucessfull !!");
         alert2.setContentText("password updated sucessfull");
         alert2.showAndWait();
             Stage stage = (Stage) oldp.getScene().getWindow();
    stage.close();
        
        }   catch (SQLException ex) {
                Logger.getLogger(PasswordController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        }
        
        
        }
    }
    }

    @FXML
    private void cancel(ActionEvent event) {
       
    }

    void setUser(String un) {
        user.setText(un);
        user.setEditable(false);//To change body of generated methods, choose Tools | Templates.
    }

  

    void setPass(String pd) {
       pwd = pd;//To change body of generated methods, choose Tools | Templates.
    }
    
}

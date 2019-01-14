/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resetpassword;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import process.PaymentController;
import viewstudents.DbConnection;

/**
 * FXML Controller class
 *
 * @author govind
 */
public  class SidemainController implements Initializable{

    @FXML
    private TextField reset;
    @FXML
    private TextField confirm;

    
        @FXML
    private TextField user;

    @FXML
    private RadioButton pass;

    @FXML
    private ToggleGroup type;

    @FXML
    private RadioButton security;
    private DbConnection dc;
    /**
     * Initializes the controller class.
     */
  @FXML
    void bill(ActionEvent event) throws IOException {
Parent s1 = FXMLLoader.load(getClass().getResource("/basic/bill.fxml"));
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
    void mess(ActionEvent event) throws IOException {
Parent s1 = FXMLLoader.load(getClass().getResource("/basic/mess.fxml"));
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
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc = new DbConnection();
        
    }    

   
    @FXML
    void done(ActionEvent event) throws ClassNotFoundException, IOException {
        if (user.getText().isEmpty())
        {
       Alert alert2;
  alert2 = new Alert(Alert.AlertType.ERROR);
         alert2.setTitle("invalid username");
         alert2.setHeaderText("cannot be empty");
         alert2.setContentText("first enter the userid");
         alert2.showAndWait();
        }
        else
        {
            try {
                String usr = user.getText();
            Connection conn = dc.Connect();
            String query = "SELECT * FROM `admin_panel` WHERE `userid` = '"+usr+"'";
            ResultSet rs = conn.createStatement().executeQuery(query);
            if (rs.next())
            {
            String un = rs.getString(2);
            String pd = rs.getString(3);
            Stage st = (Stage) user.getScene().getWindow();
           FXMLLoader root2 = new FXMLLoader();
            root2.setLocation(getClass().getResource("/resetpassword/password.fxml"));
           
             Stage stage = new Stage();
             stage.initModality(Modality.WINDOW_MODAL);
             stage.initOwner(st);
            Scene sc= new Scene(root2.load());
            stage.setTitle("changepassword");
            stage.setScene(sc);

            
            PasswordController editor1 = root2.getController();
            editor1.setUser(un);
            editor1.setPass(pd);
      
                        stage.show();
                        Parent s1 = FXMLLoader.load(getClass().getResource("/login/loginpage.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
            }
            else
            {
                 Alert alert2;
  alert2 = new Alert(Alert.AlertType.ERROR);
         alert2.setTitle("invalid username");
         alert2.setHeaderText("not valid");
         alert2.setContentText("first enter the correct userid");
         alert2.showAndWait();
            }
            } catch (SQLException ex) {
                Logger.getLogger(SidemainController.class.getName()).log(Level.SEVERE, null, ex);
            }
      
        }

    }
    
}

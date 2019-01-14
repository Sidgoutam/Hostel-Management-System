/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Welcome;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.application.Preloader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import viewstudents.ViewstudentsController;

/**
 * FXML Controller class
 *
 * @author govind
 */
public class WelcomeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Label dot;
     @FXML
    private JFXButton clickme;
     @FXML
    private AnchorPane a1;
  


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new loadscreen().start(); //To change body of generated methods, choose Tools | Templates.
    }
    
      class loadscreen extends Thread {
          
          @Override
           public void run()
          {
            try {
                Thread.sleep(3000);
             
              Platform.runLater(new Runnable() {
                 
                @Override
                public void run()
                {
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("/login/loginpage.fxml"));
                        
                        
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.show(); 
                        a1.getScene().getWindow().hide();
                    } catch (IOException ex) {
                        Logger.getLogger(WelcomeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            });
                   
            } catch (InterruptedException ex) {
                Logger.getLogger(WelcomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
          }
      }
        
    
  
    
    
}

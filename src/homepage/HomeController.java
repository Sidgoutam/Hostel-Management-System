/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homepage;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author govind
 */
public class HomeController implements Initializable {

    @FXML
    private Label hour;
    @FXML
    private Label minute;
    @FXML
    private Label second;
      private Thread thread=null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
           thread = new Thread((Runnable) this);
        thread.start();
       
    }    

    @FXML
    private void search(ActionEvent event) {
    }

    @FXML
    private void dashboard(ActionEvent event) throws IOException {
         Parent s1 = FXMLLoader.load(getClass().getResource("/viewroomstatus/roomstatus.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }

    @FXML
    private void rooms(ActionEvent event) throws IOException {
         Parent s1 = FXMLLoader.load(getClass().getResource("/viewroomstatus/roomstatus1.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }

    @FXML
    private void student(ActionEvent event) throws IOException {
         Parent s1 = FXMLLoader.load(getClass().getResource("/viewroomstatus/roomstatus2.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }

    @FXML
    private void fee(ActionEvent event) throws IOException {
         Parent s1 = FXMLLoader.load(getClass().getResource("/viewroomstatus/roomstatus3.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }

    @FXML
    private void settings(ActionEvent event) throws IOException {
         Parent s1 = FXMLLoader.load(getClass().getResource("/viewroomstatus/roomstatus4.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }
     public void run()
     {
       try  {
     long start = System.currentTimeMillis();
while (System.currentTimeMillis() - start <= 1000000) {
  // Your code goes here
  DateFormat dt = new SimpleDateFormat("hh");
        Calendar cd = Calendar.getInstance();
        String h = dt.format(cd.getTime());
       
        
        DateFormat dt1 = new SimpleDateFormat("mm");
        Calendar cd1 = Calendar.getInstance();
       
        String h1 = dt1.format(cd1.getTime());
        
          DateFormat dt2 = new SimpleDateFormat("ss");
        Calendar cd2 = Calendar.getInstance();
        
        String h2 = dt2.format(cd2.getTime());
        
     
          
                            Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                          hour.setText(h);
                minute.setText(h1);
                second.setText(h2);
                        

                    }
                });


                Thread.sleep(1000);
}
  

         }
       catch (InterruptedException e) { //Error check
            hour.setText("00");
            minute.setText("00");
            second.setText("00");
        }
     }
    
}

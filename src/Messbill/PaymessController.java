/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Messbill;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import process.PaymentController;
import viewstudents.DbConnection;
import viewstudents.ViewstudentsController;

/**
 * FXML Controller class
 *
 * @author govind
 */
public class PaymessController implements Initializable {

   @FXML
    private Pane p1,p2;
    @FXML
    private TextField name;
    @FXML
    private TextField hostel;
    @FXML
    private TextField room;
    @FXML
    private TextField reg;
    @FXML
    private Button check,ok;
     @FXML
    private TextField roomrent;

    @FXML
    private TextField fine;

    @FXML
    private TextField total;

    @FXML
    private TextField lastdate;

    @FXML
    private Button proceed;
    @FXML
    private ComboBox month;
    private DbConnection dc;
    private ObservableList<String> data;

    /**
     * Initializes the controller class.
     */
     @FXML
    void payment(ActionEvent event) {
 try {
       Stage st = (Stage) total.getScene().getWindow();
           FXMLLoader root2 = new FXMLLoader();
            root2.setLocation(getClass().getResource("/process/payment.fxml"));
           
             Stage stage = new Stage();
             stage.initModality(Modality.WINDOW_MODAL);
             stage.initOwner(st);
            Scene sc= new Scene(root2.load());
            stage.setTitle("Pay Mess bill");
            stage.setScene(sc);

            
            PaymentController editor1 = root2.getController();
            editor1.setReg1(reg.getText());
            editor1.setLb("3");
            
            editor1.setMonth1(month.getValue().toString());
            editor1.setTotalbill1(total.getText());
            String my = month.getValue().toString();
            String dta = LocalDate.now().toString();
            
            editor1.setPayid1("#"+reg.getText()+ my.substring(0,3)+my.substring(my.length()-5,my.length()-1)+dta.substring(dta.length()-2,dta.length()));
            
                        stage.show();
            // Hide this current window (if this is what you want)
            // ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(ViewstudentsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc = new DbConnection();
        p1.setVisible(false);
        p2.setVisible(false);
        month.setDisable(true);
        ok.setDisable(true);
        proceed.setVisible(false);
          month.getSelectionModel().selectedItemProperty().addListener((ObservableValue v , Object oldValue, Object newValue) -> {ok.setDisable(false);});

    }    
      @FXML
    void setok(ActionEvent event) throws ClassNotFoundException, SQLException {
        String rg = reg.getText();
        String mth = month.getValue().toString();
        String my = mth.substring(0,mth.indexOf("("));
        String yr = mth.substring(mth.indexOf("(")+1,mth.indexOf(")"));
month.setDisable(true);
p2.setVisible(true);
try {
Connection conn = dc.Connect();
String qry = "SELECT * FROM `pendmess` WHERE reg = '"+rg+"' AND month = '"+my+"' AND year = '"+yr+"'";
ResultSet rs1 = conn.createStatement().executeQuery(qry);
if (rs1.next())
{
    roomrent.setText(rs1.getString(5));
    fine.setText(rs1.getString(6));
    total.setText(rs1.getString(7));
    lastdate.setText(rs1.getString(8));
    proceed.setVisible(true);

}

}
 catch (SQLException ex)
                { 
                
               System.err.println("ERRor "+ex);

                }

    }

    @FXML
    private void checkreg(ActionEvent event) throws ClassNotFoundException {
         if(reg.getText().isEmpty())
        {
           Alert alert1;
         alert1 = new Alert(Alert.AlertType.ERROR);
         alert1.setTitle("fill the reg no");
         alert1.setHeaderText("NOt allowed");
         alert1.setContentText("Enter reg no");
         alert1.showAndWait();
        
        }
        else
        {
        try
        {
            String rg = reg.getText();
        Connection conn = dc.Connect();
        String query1 = "SELECT * FROM `students` WHERE `reg. no` = '"+rg+"'";
        ResultSet rs = conn.createStatement().executeQuery(query1);
        if(rs.next())
        {
            p1.setVisible(true);
            hostel.setText(rs.getString(2));
            name.setText(rs.getString(5));
            room.setText(rs.getString(3));
            month.setDisable(false);
            
            reg.setEditable(false);
            check.setDisable(true);
            
        }
        else
        {
          Alert alert1;
         alert1 = new Alert(Alert.AlertType.ERROR);
         alert1.setTitle("invalid reg no");
         alert1.setHeaderText("NOt allowed");
         alert1.setContentText("Enter valid reg no");
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
    void cross1(MouseEvent event) throws IOException {
month.setDisable(false);
ok.setDisable(false);
p2.setVisible(false);

    }
    
    @FXML
    void cross(MouseEvent event) throws IOException {
 Parent s1 = FXMLLoader.load(getClass().getResource("/Messbill/paymess.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }
   @FXML
    private void selectmonth(MouseEvent event) throws ClassNotFoundException {
                

        
        try {
  Connection conn = dc.Connect();
  data = FXCollections.observableArrayList(); 
  //String insert1 = "INSERT INTO user_info VALUES('anubhav','anubhav.com','')";
  //Statement stmt = (Statement) conn.createStatement();
   //stmt.executeUpdate(insert1);
  String rg = reg.getText();
  ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM `pendmess` WHERE reg = '"+rg+"'");
  while (rs.next())
  {
      String hn = rs.getString(3)+"("+rs.getString(4)+")";
      data.add(hn);

    }
        }
    
      
        catch (SQLException ex)
                { 
                
               System.err.println("ERRor "+ex);

                }
        
         month.setItems(data);
         
         //hostelno1.setItems(data);
         
        
         
    
        
        
    
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
          Parent s1 = FXMLLoader.load(getClass().getResource("/basic/mess.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }
    
}

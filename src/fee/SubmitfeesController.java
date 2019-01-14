/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fee;

import com.jfoenix.controls.JFXDatePicker;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.Optional;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

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
import java.util.UUID;
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
import javafx.scene.control.ComboBox;
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
public class SubmitfeesController implements Initializable {

    @FXML
    private JFXTextField reg;
     @FXML
    private Pane p1;
     @FXML
    private VBox p2;
     @FXML
     private JFXButton ok1,ok2;
    @FXML
    private ComboBox choosepending;
    @FXML
    private JFXTextField name;
    
     @FXML
    private JFXTextField duedate;

    @FXML
    private JFXTextField totalunit;

    @FXML
    private JFXTextField unitrate;

    @FXML
    private JFXTextField electricbill;

    @FXML
    private JFXTextField fine;

    @FXML
    private JFXTextField total;

   

    
    

 
    private DbConnection dc;
    private ObservableList<Object> data;
    
    /**
     * Initializes the controller class.
     */
    String tota="";
    int id1 = 0;
    private PreparedStatement pst;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc = new DbConnection();
        p1.setVisible(false);
        p2.setVisible(false);
        ok2.setDisable(true);
         choosepending.getSelectionModel().selectedItemProperty().addListener((ObservableValue v , Object oldValue, Object newValue) -> {ok2.setDisable(false);});

         
    }    
     @FXML
    void bill(ActionEvent event) throws IOException {
Parent s1 = FXMLLoader.load(getClass().getResource("/basic/bill.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }

    @FXML
    private void submitfee(ActionEvent event) throws ClassNotFoundException {
        
   try {
       Stage st = (Stage) total.getScene().getWindow();
           FXMLLoader root2 = new FXMLLoader();
            root2.setLocation(getClass().getResource("/process/payment.fxml"));
           
             Stage stage = new Stage();
             stage.initModality(Modality.WINDOW_MODAL);
             stage.initOwner(st);
            Scene sc= new Scene(root2.load());
            stage.setTitle("Pay electric bill");
            stage.setScene(sc);

            
            PaymentController editor1 = root2.getController();
            editor1.setReg1(reg.getText());
            editor1.setLb("1");
            editor1.setMonth1(choosepending.getValue().toString());
            editor1.setTotalbill1(total.getText());
            String my = choosepending.getValue().toString();
            String dta = LocalDate.now().toString();
            
            editor1.setPayid1("#"+reg.getText()+ my.substring(0,3)+my.substring(my.length()-5,my.length()-1)+dta.substring(dta.length()-2,dta.length()));
            
                        stage.show();
            // Hide this current window (if this is what you want)
            // ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(ViewstudentsController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        
    }
  
     public void setReg1(String rn1)
    {
    this.reg.setText(rn1);
    }
    @FXML
    private void selectpending(ActionEvent event) throws ClassNotFoundException 
    {
    String choose = (String) choosepending.getValue();
    String yr = choose.substring(choose.length()-5,choose.length() -1);
    String mnth = choose.substring(0,choose.length() - 6);
    try {
        Connection conn = dc.Connect();
        String query = "SELECT * FROM `pending bill` WHERE month = '"+mnth+"' AND year = '"+yr+"'";
        ResultSet rs = conn.createStatement().executeQuery(query);
        rs.next();
        totalunit.setText(rs.getString(5));
        unitrate.setText(rs.getString(6));
        duedate.setText(rs.getString(10));
        electricbill.setText(rs.getString(7));
        fine.setText(rs.getString(8));
        total.setText(rs.getString(9));
        tota = rs.getString(9);
        LocalDate ld = LocalDate.now();
        
    }
       catch (SQLException ex)
                { 
                
               System.err.println("ERRor "+ex);

                }
    p2.setVisible(true);
    choosepending.setDisable(true);
    ok2.setDisable(true);
    
    }
    @FXML
    private void cancel(ActionEvent event) throws IOException
    {
    Parent s1 = FXMLLoader.load(getClass().getResource("/basic/bill.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }
      @FXML
    void cancel1(MouseEvent event) throws IOException {
Parent s1 = FXMLLoader.load(getClass().getResource("submitfees.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }

    @FXML
    void cancel2(MouseEvent event) {
        p2.setVisible(false);
        choosepending.setDisable(false);
        ok2.setDisable(false);

    }
      @FXML
    private void checkreg(ActionEvent event) throws ClassNotFoundException, SQLException {
      submitreg();
        
    }
    
    
    
    public void submitreg() throws ClassNotFoundException
    {
    
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
           
        try {
        Connection conn = dc.Connect();
          data = FXCollections.observableArrayList(); 

        String query = "SELECT * FROM students WHERE `reg. no` = '"+rg+"'";
        ResultSet rs = conn.createStatement().executeQuery(query);
    if(rs.next())
    {
    name.setText(rs.getString(5));
    String query1 = "SELECT * FROM `pending bill` WHERE reg='"+rg+"'";
        ResultSet rs1 = conn.createStatement().executeQuery(query1);
        while (rs1.next())
        {    String str = rs1.getString(4);
             String last4 = str.substring(0,4);
          String hn = rs1.getString(3)+"("+last4+")";
      data.add(hn);
        }
        
         choosepending.setItems(data);
        p1.setVisible(true);
        ok1.setDisable(true);
        reg.setEditable(false);
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
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editstudent;
import com.jfoenix.controls.JFXButton;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import viewstudents.DbConnection;
import viewstudents.ViewstudentsController;

/**
 * FXML Controller class
 *
 * @author govind
 */

 
public class EditdetailsController implements Initializable {

    @FXML
    private TextField reg;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField fname;
    @FXML
    private JFXTextField mobile;
    @FXML
    private JFXTextField email,gender,city,guardno,mname;
    @FXML
    private JFXTextField roll;
     @FXML
    private JFXDatePicker dob;
      @FXML
    private JFXTextField hostel;
    @FXML
    private JFXTextField room;
    
      @FXML
    private JFXTextField regno;
    
         @FXML
    private AnchorPane a1;
    

    @FXML
    private ComboBox state;
    @FXML
    private JFXButton submit,cancel;
    @FXML
    private JFXTextField address;
        private DbConnection dc;
    private ObservableList<Object> data;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc = new DbConnection();
        submit.setVisible(false);
        cancel.setVisible(false);
        
        
    }   


    @FXML
    private void getdetails(ActionEvent event) throws ClassNotFoundException, SQLException, ParseException {
       
         String regno1 = reg.getText();
         reg.setDisable(true);
        try
           {
             Connection conn = dc.Connect();
             
            
             
             
            String query1 = "SELECT * FROM students WHERE `reg. no` = '" +regno1 + "' ";
    ResultSet rs1 = conn.createStatement().executeQuery(query1);
    rs1.next();
    submit.setVisible(true);
    cancel.setVisible(true);
   
    name.setText(rs1.getString(5));
    fname.setText(rs1.getString(7));
    mname.setText(rs1.getString(8));
    guardno.setText(rs1.getString(12));
    city.setText(rs1.getString(14));
    gender.setText(rs1.getString(6));
    roll.setText(rs1.getString(9));
    mobile.setText(rs1.getString(11));
    email.setText(rs1.getString(16));
    address.setText(rs1.getString(13));
    hostel.setText(rs1.getString(2));
    room.setText(rs1.getString(3));
    regno.setText(rs1.getString(4));
    hostel.setEditable(false);
    room.setEditable(false);
    regno.setEditable(false);
    
    
    
     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
String date = rs1.getString(10);

LocalDate localDate = LocalDate.parse(date, formatter);
dob.setValue(localDate);
state.setValue(rs1.getString(15));
    
     
    
   
        
    }
       catch (ClassNotFoundException ex) {
         Logger.getLogger(ViewstudentsController.class.getName()).log(Level.SEVERE, null, ex);
     } catch (SQLException ex) {
         Logger.getLogger(ViewstudentsController.class.getName()).log(Level.SEVERE, null, ex);
     }
   }
      @FXML
    void students(ActionEvent event) throws IOException {
Parent s1 = FXMLLoader.load(getClass().getResource("/basic/student.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }

    @FXML
    private void update(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        String regno1 = regno.getText();
         Alert alert;
         alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setTitle("Confirmation ");
         alert.setHeaderText("OK");
         alert.setContentText("are you sure?");
         Optional<ButtonType> result = alert.showAndWait();
         if (result.get() == ButtonType.OK)
         {
        try {
        Connection conn = dc.Connect();
             String dt = (String) dob.getEditor().getText();
             String st = (String) state.getValue();
             String query4 = "UPDATE `students` SET name = '"+name.getText()+"',`city` = '"+city.getText()+"' ,`gender` = '"+gender.getText()+"' ,`guardian no` = '"+guardno.getText()+"' , `mother name` = '"+mname.getText()+"' ,`father name` = '"+fname.getText()+"' ,`roll no` = '"+roll.getText()+"' ,`address` = '"+address.getText()+"' ,`email` = '"+email.getText()+"' ,`mobile no` = '"+mobile.getText()+"',`dob` = '"+dt+"',`state` = '"+st+"'  WHERE  `reg. no` = '" + regno1+ "' ";
   Statement stmt1 = conn.createStatement();
   stmt1.executeUpdate(query4);
   
        }
        catch (ClassNotFoundException ex) {
         Logger.getLogger(ViewstudentsController.class.getName()).log(Level.SEVERE, null, ex);
     } catch (SQLException ex) {
         Logger.getLogger(ViewstudentsController.class.getName()).log(Level.SEVERE, null, ex);
     }
         Parent s1 = FXMLLoader.load(getClass().getResource("/editstudent/editdetails.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
         }
        
    }
      @FXML
    void cancel(ActionEvent event) throws IOException {
   Parent s1 = FXMLLoader.load(getClass().getResource("editdetails.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }
    @FXML
    public void setReg1(String rn1)
    {
    this.reg.setText(rn1);
    }
     @FXML
     void selectstate(MouseEvent event) throws ClassNotFoundException
     {
     
           try {
  Connection conn = dc.Connect();
  data = FXCollections.observableArrayList(); 
  //String insert1 = "INSERT INTO user_info VALUES('anubhav','anubhav.com','')";
  //Statement stmt = (Statement) conn.createStatement();
   //stmt.executeUpdate(insert1);
  
  ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM states");
  while (rs.next())
  {
      String hn = rs.getString(1);
      data.add(hn);

    }
        }
    
      
        catch (SQLException ex)
                { 
                
               System.err.println("ERRor "+ex);

                }
         state.setItems(data);
        
        
         
    }

    public void setEmail1(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setAge1(String dob) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setMobile1(String reg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setName1(String reg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setState1(String reg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setFather1(String reg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addstudent;

import com.jfoenix.controls.JFXDatePicker;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author govind
 */
public class AddController implements Initializable {

    @FXML
    private ComboBox Roomno;
      
    @FXML
    private ComboBox Hostelno;

   

    @FXML
    private TextField Regno;

    @FXML
    private TextField Name;

    @FXML
    private TextField Fathername;

    @FXML
    private TextField Mothername;

    @FXML
    private TextField Rollno;

    @FXML
    private JFXDatePicker Dob;

    @FXML
    private TextField Mobileno;

    @FXML
    private TextField Guardianno;

    @FXML
    private TextField Address;

    @FXML
    private TextField City;

    @FXML
    private ComboBox State;

    
     @FXML
    private ToggleGroup sex;

   

    @FXML
    private TextField Email;
    
     @FXML
    private DbConnection dc;
     
     ObservableList<String> hostellist = FXCollections.observableArrayList("hostel1","hostel2","hostel3");
     ObservableList<String> roomlist = FXCollections.observableArrayList("A1","A2","A3");
     ObservableList<String> statelist = FXCollections.observableArrayList("Rajasthan","Assam","West Bengal","Bihar","punjab","gujrat","tamilnadu","delhi","kashmir","jharkhand");
    private PreparedStatement pst;
     
     
 
    
     
     
    
     @FXML
    void SubmitDatabase(ActionEvent event) throws ClassNotFoundException, SQLException {
        
  //System.out.println();
  //String insert1 = "INSERT INTO user_info VALUES('name','email','department')";
 //public void alert3(ActionEvent event) {
        
        Alert alert;
         alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setTitle("Confirmation ");
         alert.setHeaderText("OK");
         alert.setContentText("are you sure?");
         Optional<ButtonType> result = alert.showAndWait();
         if (result.get() == ButtonType.OK)
         {
         System.out.println("nice choice");
         try {
Connection conn = dc.Connect();
  String name = Name.getText();
  String reg = Regno.getText();
  String roll = Rollno.getText();
  String father = Fathername.getText();
  String mother = Mothername.getText();
  String mobile = Mobileno.getText();
  String city = City.getText();
  
  String address = Address.getText();
  String guardian = Guardianno.getText();
  String email = Email.getText();
  String hostel = (String) Hostelno.getValue();
  String room = (String) Roomno.getValue();
  String state = (String) State.getValue();
  String id = "123";
  String dob = (String) Dob.getEditor().getText();
  RadioButton selectedRadioButton = (RadioButton) sex.getSelectedToggle();
String gender = selectedRadioButton.getText();
  
 String query = "INSERT INTO `students`(`hostel`, `room`, `reg. no`, `name`, `gender`, `father name`, `mother name`, `roll no`,`dob`,  `mobile no`, `guardian no`, `address`, `city`, `state`, `email`) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
  //Statement stmt = (Statement) conn.createStatement();
     //stmt.executeUpdate(query);  

//String query = "INSERT INTO stu(name,reg,rollno,father,mother,mobile) VALUES('"+name+"','"+reg+"','"+roll+"','"+father+"','"+mother+"','"+mobile+"')";
  //String query = "INSERT INTO stu(name,reg,rollno,father,mother,mobile) VALUES('"+name+"','"+reg+"','"+roll+"','"+father+"','"+mother"','"+mobile+"')"; 
              pst = conn.prepareStatement(query);
             //pst.setString(1,id);
             pst.setString(1, hostel);
             pst.setString(2, room);
             pst.setString(3, reg);
             pst.setString(4, name);
             pst.setString(5, gender);
             pst.setString(6, father);
             pst.setString(7, mother);
             pst.setString(8, roll);
             pst.setString(9, dob);
             pst.setString(10, mobile);
             pst.setString(11, guardian);
             pst.setString(12, address);
             pst.setString(13, city);
             pst.setString(14, state);
             pst.setString(15, email);
   pst.execute();
  
             
             
             
             
           
             
        }
         catch (SQLException ex)
                { 
                
               System.err.println("ERRor "+ex);

                }
         }
         else
         {
             System.out.println("koi na ");
         }
    
    }
    
    
    
    
    
    
    
    
    
    
    
    
public void logout(ActionEvent event) throws IOException {
        Parent s1 = FXMLLoader.load(getClass().getResource("/login/loginpage.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }
public void view(ActionEvent event) throws IOException {
        Parent s1 = FXMLLoader.load(getClass().getResource("/viewstudents/viewstudents.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }
     public void addstudent(ActionEvent event) throws IOException {
        Parent s1 = FXMLLoader.load(getClass().getResource("/addstudent/addstudent.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }
     public void addroom(ActionEvent event) throws IOException {
        Parent s1 = FXMLLoader.load(getClass().getResource("/addroom/FXMLDocument.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }
     public void feedetails(ActionEvent event) throws IOException {
        Parent s1 = FXMLLoader.load(getClass().getResource("/fee/fee.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }
     public void dashboard(ActionEvent event) throws IOException {
        Parent s1 = FXMLLoader.load(getClass().getResource("/dashboard/Dashboard.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }
     public void settings(ActionEvent event) throws IOException {
        Parent s1 = FXMLLoader.load(getClass().getResource("/resetpassword/sidemain.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
   
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc = new DbConnection();
         Hostelno.setValue("hostel1");
        
        Hostelno.setItems(hostellist);
        Hostelno.getSelectionModel().selectedItemProperty().addListener((ObservableValue v , Object oldValue, Object newValue) -> {});
    
        Roomno.setValue("A1");
        
        Roomno.setItems(roomlist);
        Roomno.getSelectionModel().selectedItemProperty().addListener((ObservableValue v , Object oldValue, Object newValue) -> {});
     
        State.setValue("West Bengal");
        
        State.setItems(statelist);
        State.getSelectionModel().selectedItemProperty().addListener((ObservableValue v , Object oldValue, Object newValue) -> {});
     
    }    

    @FXML
    private void handle(ActionEvent event) {
    }

    private Object getSelectedItem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String string(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}

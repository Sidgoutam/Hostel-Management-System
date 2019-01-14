/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addstudent;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import viewstudents.DbConnection;

/**
 * FXML Controller class
 *
 * @author govind
 */
public class AddstudentController implements Initializable {


    @FXML
    private ComboBox Hostelno;

    @FXML
    private ComboBox Roomno;

   
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
    private DatePicker Dob;
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
    
    private DbConnection dc;
     
     
     
     ObservableList<String> statelist = FXCollections.observableArrayList("Rajasthan","Assam","West Bengal","Bihar","punjab","gujrat","tamilnadu","delhi","kashmir","jharkhand");
    private PreparedStatement pst;
    private ObservableList<String> data;
     private ObservableList<String> data1;
    @FXML
    private Label hour;
    @FXML
    private Label minute;
    @FXML
    private Label second;
       
    public void spage(ActionEvent event) throws IOException {
Parent s1 = FXMLLoader.load(getClass().getResource("/basic/student.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }
    @FXML
    public void selectstate(MouseEvent event) throws ClassNotFoundException
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
         State.setItems(data);
        
         
    }
         @FXML
    void selecthostel(MouseEvent event) throws ClassNotFoundException {

        
        try {
  Connection conn = dc.Connect();
  data = FXCollections.observableArrayList(); 
  //String insert1 = "INSERT INTO user_info VALUES('anubhav','anubhav.com','')";
  //Statement stmt = (Statement) conn.createStatement();
   //stmt.executeUpdate(insert1);
  
  ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM hostel");
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
        
         Hostelno.setItems(data);
         //hostelno1.setItems(data);
         
        
         
    
        
        
    }
  @FXML
    public void chooseoption(MouseEvent event) throws ClassNotFoundException, SQLException
    {
        
        
        
        
            String temp = (String) Hostelno.getValue();
               
        try {
  Connection conn = dc.Connect();
  data1 = FXCollections.observableArrayList(); 
  //String insert1 = "INSERT INTO user_info VALUES('anubhav','anubhav.com','')";
  //Statement stmt = (Statement) conn.createStatement();
   //stmt.executeUpdate(insert1);
  
  ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM `room` WHERE `hostel_name` ='"+temp+ "' AND `capacity`>`current`");
  while (rs.next())
  {
      String rn = rs.getString(3);
      data1.add(rn);

    }
        }
    
      
        catch (SQLException ex)
                { 
                
               System.err.println("ERRor "+ex);

                }
         
        Roomno.setItems(data1);
        
        
        
    }
    
  
    
     
     
    @FXML
     void SubmitDatabase(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        
  //System.out.println();
  //String insert1 = "INSERT INTO user_info VALUES('name','email','department')";
 //public void alert3(ActionEvent event) {
        if (Name.getText().isEmpty() || Regno.getText().isEmpty() || Hostelno.getValue()==null || State.getValue()==null || Roomno.getValue()==null)
  {
         Alert alert1;
         alert1 = new Alert(Alert.AlertType.ERROR);
         alert1.setTitle("Warning ");
         alert1.setHeaderText("Not allowed");
         alert1.setContentText("first filled the required filed");
          alert1.showAndWait();
  }
        else
        {
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
System.out.print(name);
System.out.print(reg);
System.out.print(hostel);
System.out.print(room);
System.out.print(state);

    ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM `students` WHERE hostel = '"+reg+"'");
  if (rs.next())
  {
       Alert alert1;
         alert1 = new Alert(Alert.AlertType.ERROR);
         alert1.setTitle("Warning ");
         alert1.setHeaderText("Not allowed");
         alert1.setContentText("reg no is already existed");
          alert1.showAndWait();
  }
  else
  {
  
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
   
 
   
   
   String query2 = "UPDATE room SET current = current+1 WHERE  room_no = '" +room + "' ";
   Statement stmt = conn.createStatement();
   stmt.executeUpdate(query2);
   
 
   
   String query4 = "UPDATE hostel SET current = current+1 WHERE  hostel_name = '" +hostel + "' ";
   Statement stmt1 = conn.createStatement();
   stmt1.executeUpdate(query4);
   
       Alert alert3;
         alert3 = new Alert(Alert.AlertType.INFORMATION);
         alert3.setTitle("sucessfull");
         alert3.setHeaderText("student added");
         alert3.setContentText(reg+" is added " );
         alert3.showAndWait();
         Parent s1 = FXMLLoader.load(getClass().getResource("/basic/student.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
  
             
             
             
             
  } 
             
        }
         catch (SQLException ex)
                { 
                
               System.err.println("ERRor "+ex);

                }
        
         }
          
       
         }
         
    
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
         //Hostelno.setValue("hostel1");
        
       // Hostelno.setItems(hostellist);
        //Hostelno.getSelectionModel().selectedItemProperty().addListener((ObservableValue v , Object oldValue, Object newValue) -> {});
    
        //Roomno.setValue("A1");
        
       // Roomno.setItems(roomlist1);
       // Roomno.getSelectionModel().selectedItemProperty().addListener((ObservableValue v , Object oldValue, Object newValue) -> {});
     
       // State.setValue("West Bengal");
        
       // State.setItems(statelist);
       // State.getSelectionModel().selectedItemProperty().addListener((ObservableValue v , Object oldValue, Object newValue) -> {});
     
    }    


     
}

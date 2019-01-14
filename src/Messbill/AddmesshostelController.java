/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Messbill;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import roomrent.checkrent;
import viewstudents.DbConnection;

/**
 * FXML Controller class
 *
 * @author govind
 */
public class AddmesshostelController implements Initializable {

     @FXML
    private Button back;
    @FXML
    private ComboBox hostel;
    @FXML
    private ComboBox month;
    @FXML
    private ComboBox year;
    @FXML
    private TextField roomrent;
    @FXML
    private TextField fine;
    @FXML
    private DatePicker lastdate;
    @FXML
    private Label label1;
    
    @FXML
    private Pane pane1;
    @FXML
    private Button proceed;
    private DbConnection dc;
    private ObservableList<String> data;
      final ObservableList<checkrent>data1 = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
         ObservableList<String> monthlist = FXCollections.observableArrayList("January","Febuary","March","April","May","June","July","August","September","October","November","December");

    ObservableList<String> yearlist = FXCollections.observableArrayList("2016","2017","2018","2019","2020");
    @FXML
    private TableView<checkrent> rentcheck;

    @FXML
    private TableColumn<checkrent,Integer> Columnid;

    @FXML
    private TableColumn<checkrent,Integer> Columnreg;

    @FXML
    private TableColumn<checkrent,String> Columnname;

    @FXML
    private TableColumn<checkrent,String> Columnroom;
    @FXML
    private TableColumn<checkrent,String> Columnmonth;

    @FXML
    private TableColumn<checkrent,Integer> Columntotalrent;
    private PreparedStatement pst;

    @FXML
    void cancel(ActionEvent event) {
  pane1.setVisible(false);
            proceed.setVisible(true);
            hostel.setDisable(false);
            month.setDisable(false);
            year.setDisable(false);
            roomrent.setEditable(true);
            fine.setEditable(true);
            lastdate.setDisable(false);
    }

    @FXML
    void confirm(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
Alert alert;
         alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setTitle("Confirmation ");
         alert.setHeaderText("OK");
         alert.setContentText("are you sure?");
         Optional<ButtonType> result = alert.showAndWait();
         if (result.get() == ButtonType.OK)
         {
             
              try{ Connection conn = dc.Connect();
String hostl = hostel.getValue().toString();
String host = hostl.substring(0,hostl.indexOf("("));
String hl = hostl.substring(hostl.indexOf("(")+1,hostl.indexOf(")"));
System.out.print(host);
        
  //String insert1 = "INSERT INTO user_info VALUES('anubhav','anubhav.com','')";
  //Statement stmt = (Statement) conn.createStatement();
   //stmt.executeUpdate(insert1);

  //SearchField.clear();
   int total =Integer.parseInt(roomrent.getText())+Integer.parseInt(fine.getText());
  ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM `students` WHERE hostel = '"+host+"'");
  while (rs.next())
  {
      String rg = rs.getString(4);
      String ld = lastdate.getValue().toString();
      String query1 = "INSERT INTO `pendmess`(`reg`, `month`, `year`, `mess bill`, `fine`, `total mess`,`last date`) VALUES (?,?,?,?,?,?,?)";
         pst = conn.prepareStatement(query1);
             //pst.setString(1,id);
            
             pst.setString(1, rg);
             pst.setString(2, month.getValue().toString());
             pst.setString(3, year.getValue().toString());
             pst.setInt(4, Integer.parseInt(roomrent.getText()));
             pst.setInt(5,Integer.parseInt(fine.getText()) );
             
             pst.setInt(6,total );
             pst.setString(7,ld);
             pst.execute();
  }
    Alert alert1;
         alert1 = new Alert(Alert.AlertType.INFORMATION);
         alert1.setTitle("rent added");
         alert1.setHeaderText("sucessfull");
         alert1.setContentText("In Hostel "+host+ " student "+hl+" room rent "+total+" ADDED");
         alert1.showAndWait();
            Parent s1 = FXMLLoader.load(getClass().getResource("/basic/rent.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
             
         }
                 catch (SQLException ex)
                { 
                
               System.err.println("ERRor "+ex);

                }
         
         }
         
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc= new DbConnection();
        month.setDisable(true);
        year.setDisable(true);
        proceed.setDisable(true);
        pane1.setVisible(false);
         month.setItems(monthlist);
        year.setItems(yearlist);
        hostel.getSelectionModel().selectedItemProperty().addListener((ObservableValue v , Object oldValue, Object newValue) -> {month.setDisable(false);String hostl = hostel.getValue().toString();
String host = hostl.substring(0,hostl.indexOf("("));
System.out.print(host);});
        month.getSelectionModel().selectedItemProperty().addListener((ObservableValue v , Object oldValue, Object newValue) -> {year.setDisable(false);});
        year.getSelectionModel().selectedItemProperty().addListener((ObservableValue v , Object oldValue, Object newValue) -> {proceed.setDisable(false);});

   

    }    

    @FXML
    private void goback(ActionEvent event) throws IOException {
        Parent s1 = FXMLLoader.load(getClass().getResource("/basic/mess.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }

    @FXML
    private void selecthostel(MouseEvent event) throws ClassNotFoundException {
                

        
        try {
  Connection conn = dc.Connect();
  data = FXCollections.observableArrayList(); 
  //String insert1 = "INSERT INTO user_info VALUES('anubhav','anubhav.com','')";
  //Statement stmt = (Statement) conn.createStatement();
   //stmt.executeUpdate(insert1);
  
  ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM hostel WHERE current>0");
  while (rs.next())
  {
      String hn = rs.getString(1)+"("+rs.getString(3)+")";
      data.add(hn);

    }
        }
    
      
        catch (SQLException ex)
                { 
                
               System.err.println("ERRor "+ex);

                }
        
         hostel.setItems(data);
         //hostelno1.setItems(data);
         
        
         
    
        
        
    
    }

    @FXML
    private void proceed(ActionEvent event) throws ClassNotFoundException {
        if (roomrent.getText().isEmpty() || fine.getText().isEmpty() || lastdate.getValue().equals(""))
        {
              Alert alert3;
         alert3 = new Alert(Alert.AlertType.ERROR);
         alert3.setTitle("Empty field");
         alert3.setHeaderText("Not Allowed");
         alert3.setContentText("please fill all the fields" );
         alert3.showAndWait();
        
        
        }
        else
        {
            pane1.setVisible(true);
            proceed.setVisible(false);
            hostel.setDisable(true);
            month.setDisable(true);
            year.setDisable(true);
            roomrent.setEditable(false);
            fine.setEditable(false);
            lastdate.setDisable(true);
        try{ Connection conn = dc.Connect();
String hostl = hostel.getValue().toString();
String host = hostl.substring(0,hostl.indexOf("("));
System.out.print(host);
        
  //String insert1 = "INSERT INTO user_info VALUES('anubhav','anubhav.com','')";
  //Statement stmt = (Statement) conn.createStatement();
   //stmt.executeUpdate(insert1);
  data1.clear();
  //SearchField.clear();
  ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM `students` WHERE hostel = '"+host+"'");
  int start =1;
  while (rs.next())
  {
    
      String name = rs.getString(5);
      String rg = rs.getString(4);
      String roomno = rs.getString(3);
      String mt = month.getValue().toString() + "("+year.getValue().toString()+")";
      int tt = Integer.parseInt(roomrent.getText())+Integer.parseInt(fine.getText());
      
    
  data1.add(new checkrent(start,Integer.parseInt(rg),name,roomno,mt,tt));
  start+=1;
  }
  System.out.print(data1);
    }
    
        catch (SQLException ex)
                { 
                
               System.err.println("ERRor "+ex);

                }
        Columnname.setCellValueFactory(new PropertyValueFactory<>("name"));
         //Columnhostel.setCellValueFactory(new PropertyValueFactory<>("hostel"));
          Columnroom.setCellValueFactory(new PropertyValueFactory<>("room"));
           
        Columnid.setCellValueFactory(new PropertyValueFactory<>("id"));
        Columnreg.setCellValueFactory(new PropertyValueFactory<>("reg"));
        Columnmonth.setCellValueFactory(new PropertyValueFactory<>("month"));
       // Columnyear.setCellValueFactory(new PropertyValueFactory<>("year"));
        
       // Columnfine.setCellValueFactory(new PropertyValueFactory<>("fine"));
        Columntotalrent.setCellValueFactory(new PropertyValueFactory<>("total"));
       //  Columnlastdate.setCellValueFactory(new PropertyValueFactory<>("lastdate"));
         
         // billpending.setItems(null);
         //System.out.print(data);
        rentcheck.setItems(data1);
        
        
        
        }
        
    }
    
}

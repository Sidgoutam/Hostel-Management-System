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
import java.util.ResourceBundle;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import viewstudents.DbConnection;

/**
 * FXML Controller class
 *
 * @author govind
 */
public class AddmessController implements Initializable {

    @FXML
    private Pane p1;
    @FXML
    private TextField name;
    @FXML
    private TextField hostel;
    @FXML
    private TextField room;
    @FXML
    private TextField reg;
    @FXML
    private TextField rent;
    @FXML
    private TextField fine;
    @FXML
    private Button check;
     @FXML
    private DatePicker date;
    @FXML
    private ComboBox<String> month;
    @FXML
    private ComboBox<String> year;
    @FXML
    private Button add;
    @FXML
    private Button confirm,cancel;
    private DbConnection dc;

    /**
     * Initializes the controller class.
     */
    ObservableList<String> monthlist = FXCollections.observableArrayList("January","Febuary","March","April","May","June","July","August","September","October","November","December");

    ObservableList<String> yearlist = FXCollections.observableArrayList("2016","2017","2018","2019","2020");
    private PreparedStatement pst;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc = new DbConnection();
        p1.setVisible(false);
        add.setDisable(true);
        cancel.setVisible(false);
        confirm.setVisible(false);
        month.setItems(monthlist);
        year.setItems(yearlist);
        
        
    }    

    @FXML
    private void addmess(ActionEvent event) {
        
        year.setDisable(true);
        month.setDisable(true);
        check.setDisable(true);
        rent.setEditable(false);
        fine.setEditable(false);
        date.setDisable(true);
        confirm.setVisible(true);
        cancel.setVisible(true);
        add.setVisible(false);
    }
    

    @FXML
    private void confirmmess(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        try
        {
            String rg = reg.getText();
            String mt = month.getValue().toString();
            String yr = year.getValue().toString();
        Connection conn = dc.Connect();
        String chk = "SELECT * FROM `pendmess` WHERE reg='"+rg+"' AND month = '"+mt+"' AND year ='"+yr+"'";
        ResultSet rs1 = conn.createStatement().executeQuery(chk);
        if (rs1.next())
        {
              Alert alert1;
         alert1 = new Alert(Alert.AlertType.ERROR);
         alert1.setTitle("not added");
         alert1.setHeaderText("NOt allowed");
         alert1.setContentText("already added!!");
         alert1.showAndWait(); 
        }
        else
        {
            String ld = date.getValue().toString();
            System.out.print(ld);
        String query1 = "INSERT INTO `pendmess`(`reg`, `month`, `year`, `mess bill`, `fine`, `total mess`,`last date`) VALUES (?,?,?,?,?,?,?)";
         pst = conn.prepareStatement(query1);
             //pst.setString(1,id);
             int total =Integer.parseInt(rent.getText())+Integer.parseInt(fine.getText());
             pst.setString(1, reg.getText());
             pst.setString(2, month.getValue().toString());
             pst.setString(3, year.getValue().toString());
             pst.setInt(4, Integer.parseInt(rent.getText()));
             pst.setInt(5,Integer.parseInt(fine.getText()) );
             
             pst.setInt(6,total );
             pst.setString(7,ld);
             pst.execute();
             Alert alert1;
         alert1 = new Alert(Alert.AlertType.INFORMATION);
         alert1.setTitle("Bill added");
         alert1.setHeaderText("sucessfull");
         alert1.setContentText("reg no "+reg.getText()+" mess bill "+total+"ADDED");
         alert1.showAndWait();
             Parent s1 = FXMLLoader.load(getClass().getResource("/basic/mess.fxml"));
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
        
        
        
    
    
     @FXML
    private void checkreg(ActionEvent event) throws ClassNotFoundException, SQLException {
         
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
            add.setDisable(false);
            reg.setEditable(false);
            
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
    private void cancelmess(ActionEvent event) {
        add.setVisible(true);
        confirm.setVisible(false);
        cancel.setVisible(false);
        month.setDisable(false);
        year.setDisable(false);
        rent.setEditable(true);
        fine.setEditable(true);
        date.setDisable(false);
        
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delete;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import roomrent.checkrent;
import viewstudents.DbConnection;
import viewstudents.ViewstudentsController;

/**
 * FXML Controller class
 *
 * @author govind
 */
public class DeletestudentController implements Initializable {

    @FXML
    private TextField reg;
    @FXML
    private TextField name;
    @FXML
    private TextField hostel;
    @FXML
    private TextField room;
    @FXML
    private Pane p1;
    private DbConnection dc;
       @FXML
    private Button cancel,delete;
     @FXML
    private TableView<check1> table1;

    @FXML
    private TableColumn<check1,String> cbill;

    @FXML
    private TableColumn<check1, String> cmonth;

    @FXML
    private TableColumn<check1,String> cyear;

    @FXML
    private TableColumn<check1,Integer> ctotal;

    /**
     * Initializes the controller class.
     */
      final ObservableList<check1>data = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        p1.setVisible(false);
        table1.setVisible(false);
        delete.setDisable(true);
        dc = new DbConnection();
    }    
    public void spage(ActionEvent event) throws IOException {
Parent s1 = FXMLLoader.load(getClass().getResource("/basic/student.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }
      @FXML
    private void check(ActionEvent event) throws ClassNotFoundException, SQLException {
        
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
    
       try
       {
       Connection conn = dc.Connect();
     

        String query = "SELECT * FROM students WHERE `reg. no` = '"+rg+"'";
        ResultSet rs = conn.createStatement().executeQuery(query);
        if (rs.next())
        {
   
    name.setText(rs.getString(5));
    hostel.setText(rs.getString(2));
            room.setText(rs.getString(3));
           
            p1.setVisible(true);
            reg.setDisable(true);
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
        @FXML
    void deletedata(ActionEvent event) throws IOException {
         Alert alert;
         alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setTitle("Confirmation ");
         alert.setHeaderText("OK");
         alert.setContentText("are you sure you want to delete this student from database?");
         Optional<ButtonType> result = alert.showAndWait();
         if (result.get() == ButtonType.OK)
         {
         String reg1 = reg.getText();
           String ho1 = hostel.getText();
           String ro1 = room.getText();
           try
           {
             Connection conn = dc.Connect();
             
             String query = "DELETE FROM students WHERE `reg. no` = '"+reg1+"'";
             Statement stmt2 = conn.createStatement();
             stmt2.execute(query);
            String query1 = "SELECT * FROM room WHERE room_no = '" +ro1 + "' ";
    ResultSet rs1 = conn.createStatement().executeQuery(query1);
    rs1.next();
   int curr = Integer.parseInt(rs1.getString(5));
   curr -= 1;
   
   
   String query2 = "UPDATE room SET current = '"+curr+"' WHERE  room_no = '" +ro1+ "' ";
   Statement stmt = conn.createStatement();
   stmt.executeUpdate(query2);
   
   
          String query3 = "SELECT * FROM hostel WHERE hostel_name = '" +ho1 + "' ";
    ResultSet rs3 = conn.createStatement().executeQuery(query3);
    rs3.next();
   int currentStudent = Integer.parseInt(rs3.getString(3));
   currentStudent -= 1;
   
   
   String query4 = "UPDATE hostel SET current = '"+currentStudent+"' WHERE  hostel_name = '" +ho1 + "' ";
   Statement stmt1 = conn.createStatement();
   stmt1.executeUpdate(query4);
  
           } catch (ClassNotFoundException ex) {
         Logger.getLogger(ViewstudentsController.class.getName()).log(Level.SEVERE, null, ex);
     } catch (SQLException ex) {
         Logger.getLogger(ViewstudentsController.class.getName()).log(Level.SEVERE, null, ex);
     }
           
              Alert alert1;
         alert1 = new Alert(Alert.AlertType.INFORMATION);
         alert1.setTitle("Sucessfull ");
         alert1.setHeaderText("Done");
         alert1.setContentText("student deleted sucessfully");
          alert1.showAndWait();
           Parent s1 = FXMLLoader.load(getClass().getResource("/basic/student.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
         }

    }
    @FXML
    private void correct(ActionEvent event) throws ClassNotFoundException, SQLException {
        table1.setVisible(true);
        cancel.setDisable(true);
        
        Connection conn = dc.Connect();
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM `pending bill` WHERE reg = '"+reg.getText()+"'");
        while (rs.next())
        {
            data.add(new check1("Electicity bill",rs.getString(3),rs.getString(4),rs.getInt(9)));
        }
         ResultSet rs1 = conn.createStatement().executeQuery("SELECT * FROM `pendmess` WHERE reg = '"+reg.getText()+"'");
        while (rs1.next())
        {
            data.add(new check1("Mess bill",rs1.getString(3),rs1.getString(4),rs1.getInt(7)));
        }
          ResultSet rs2 = conn.createStatement().executeQuery("SELECT * FROM `pendrent` WHERE reg = '"+reg.getText()+"'");
        while (rs2.next())
        {
            data.add(new check1("Room rent",rs2.getString(3),rs2.getString(4),rs2.getInt(7)));
        }
           
                 
        cbill.setCellValueFactory(new PropertyValueFactory<>("bill"));
        cmonth.setCellValueFactory(new PropertyValueFactory<>("month"));
        cyear.setCellValueFactory(new PropertyValueFactory<>("year"));
       // Columnyear.setCellValueFactory(new PropertyValueFactory<>("year"));
        
       // Columnfine.setCellValueFactory(new PropertyValueFactory<>("fine"));
        ctotal.setCellValueFactory(new PropertyValueFactory<>("total"));
       //  Columnlastdate.setCellValueFactory(new PropertyValueFactory<>("lastdate"));
         
         // billpending.setItems(null);
         //System.out.print(data);
         table1.getItems().clear();
        table1.setItems(data);
        if (data.isEmpty()) {delete.setDisable(false);}
        
    }

    @FXML
    private void cancel(ActionEvent event) {
        p1.setVisible(false);
        reg.setDisable(false);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process;

import fee.bill;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import java.io.IOException;
import javafx.scene.input.KeyEvent;
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
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import viewstudents.DbConnection;
import viewstudents.students;



/**
 * FXML Controller class
 *
 * @author govind
 */
public class ElecfeeController implements Initializable {
       @FXML
    private VBox v1;
      @FXML
    private Button back;
 
      String hostl = "";
      int ttl = 0;
      String mth = "";
      String yer = "";

       @FXML
    private TextField SearchField;
    @FXML
    private TableView<bill> billpending;
    @FXML
    private TableColumn<bill,Integer> Columnid;
    @FXML
    private TableColumn<bill,Integer> Columnreg;
    @FXML
    private TableColumn<bill,String> Columnmonth;
    @FXML
   private TableColumn<bill,String> Columnhostel;
    @FXML
   private TableColumn<bill,String> Columnroom;
    @FXML
   private TableColumn<bill,Float> Columntotalunit;
    @FXML
   private TableColumn<bill,Float> Columnunitrate;
    @FXML
    private TableColumn<bill,String> Columnname;
    @FXML
    private TableColumn<bill,String> Columnyear;
    @FXML
    private TableColumn<bill,Float> Columnamount;
    @FXML
    private TableColumn<bill,Float> Columnfine;
    @FXML
    private TableColumn<bill,Integer> Columntotal;
    @FXML
    private TableColumn<bill,String> Columndate;
    private DbConnection dc;
   // private ObservableList<bill> data;
    final ObservableList<bill>data = FXCollections.observableArrayList();
    FilteredList<bill> filtereddata = new FilteredList<>(data, e-> true);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc = new DbConnection();
        billpending.setEditable(true);
           try {
               loadpendingbill();
           } catch (ClassNotFoundException ex) {
               Logger.getLogger(ElecfeeController.class.getName()).log(Level.SEVERE, null, ex);
           }
       
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
    void cancel(ActionEvent event) throws IOException {
        
         Alert alert;
         alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setTitle("Cancel ");
         alert.setContentText("are you sure you want to cancel?");
         Optional<ButtonType> result = alert.showAndWait();
         if (result.get() == ButtonType.OK)
         {
             Parent s1 = FXMLLoader.load(getClass().getResource("/basic/bill.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
             
         }
         

    }

    @FXML
    void confirm(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
          Alert alert;
         alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setTitle("Confirm ");
         alert.setContentText("are you sure ?");
         Optional<ButtonType> result = alert.showAndWait();
         if (result.get() == ButtonType.OK)
         {
             try
             {
             Connection conn = dc.Connect();
             String sql = "insert into `pending bill`(`reg`, `month`, `year`, `unit(KWh)`, `unit rate`, `amount due`, `fine`, `total fee`, `due date`)  select * from `checkelectricbill`";
             Statement stmt = conn.prepareStatement(sql);
            stmt.executeUpdate(sql);
            
                 String query2 = "UPDATE `fee month` SET `" +hostl+ "`=`" +hostl+ "`+ '"+ttl+"' WHERE  `month` = '" +mth + "' AND `year` = '" +yer + "' ";
                 System.out.print(query2);
   Statement stmt1 = conn.createStatement();
   stmt1.executeUpdate(query2);
             
             }
             
        catch (SQLException ex)
                { 
                
               System.err.println("ERRor "+ex);

                }
             
             
             
             Parent s1 = FXMLLoader.load(getClass().getResource("/basic/bill.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
             
         }

    }

    
 


private void loadpendingbill() throws ClassNotFoundException
{
 try{ Connection conn = dc.Connect();

  //String insert1 = "INSERT INTO user_info VALUES('anubhav','anubhav.com','')";
  //Statement stmt = (Statement) conn.createStatement();
   //stmt.executeUpdate(insert1);
  data.clear();
  SearchField.clear();
  ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM `checkelectricbill`");
  int start =1;
  while (rs.next())
  {
      ResultSet rs1 = conn.createStatement().executeQuery("SELECT * FROM students WHERE `reg. no`='"+rs.getString(1)+"'");
      rs1.next();
      String name = rs1.getString(5);
     String hostno = rs1.getString(2);
     hostl = hostno;
      String roomno = rs1.getString(3);
              
    
  data.add(new bill(start,rs.getInt(1),name,hostno,roomno,rs.getString(2),rs.getString(3),rs.getFloat(4),rs.getFloat(5),rs.getFloat(6),rs.getFloat(7),rs.getInt(8),rs.getString(9)));
  ttl+=rs.getInt(8);
  mth = rs.getString(2);
  yer = rs.getString(3);
  start+=1;
  }
    }
    
        catch (SQLException ex)
                { 
                
               System.err.println("GSr ERRor "+ex);

                }
        Columnname.setCellValueFactory(new PropertyValueFactory<>("name"));
         Columnhostel.setCellValueFactory(new PropertyValueFactory<>("hostel"));
          Columnroom.setCellValueFactory(new PropertyValueFactory<>("room"));
           Columnunitrate.setCellValueFactory(new PropertyValueFactory<>("unitrate"));
            Columntotalunit.setCellValueFactory(new PropertyValueFactory<>("totalunit"));
        Columnid.setCellValueFactory(new PropertyValueFactory<>("id"));
        Columnreg.setCellValueFactory(new PropertyValueFactory<>("reg"));
        Columnmonth.setCellValueFactory(new PropertyValueFactory<>("month"));
        Columnyear.setCellValueFactory(new PropertyValueFactory<>("year"));
        Columnamount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        Columnfine.setCellValueFactory(new PropertyValueFactory<>("fine"));
        Columntotal.setCellValueFactory(new PropertyValueFactory<>("total"));
         Columndate.setCellValueFactory(new PropertyValueFactory<>("date"));
         
         // billpending.setItems(null);
         //System.out.print(data);
        billpending.setItems(data);

}

 @FXML
    void filtersearch(KeyEvent event) throws SQLException, ClassNotFoundException {
 
        
    SearchField.textProperty().addListener((observable,oldValue,newValue)->{
 
        filtereddata.setPredicate(bill ->{
     
     if(newValue == null || newValue.isEmpty())
     {   return true;
     }
     String lowerCaseFilter = newValue.toLowerCase();
     if (String.valueOf(bill.getName()).toLowerCase().contains(lowerCaseFilter))
     {   return true;
     }
     else if(String.valueOf(bill.getYear()).toLowerCase().contains(lowerCaseFilter))
     {
     return true;
     }
     else if(String.valueOf(bill.getMonth()).toLowerCase().contains(lowerCaseFilter))
     {
     return true;
     }
      else if(String.valueOf(bill.getReg()).toLowerCase().contains(lowerCaseFilter))
     {
     return true;
     }
     return false;
 
 });
 
 });
 Columnname.setCellValueFactory(new PropertyValueFactory<>("name"));
         Columnhostel.setCellValueFactory(new PropertyValueFactory<>("hostel"));
          Columnroom.setCellValueFactory(new PropertyValueFactory<>("room"));
           Columnunitrate.setCellValueFactory(new PropertyValueFactory<>("unitrate"));
            Columntotalunit.setCellValueFactory(new PropertyValueFactory<>("totalunit"));
        Columnid.setCellValueFactory(new PropertyValueFactory<>("id"));
        Columnreg.setCellValueFactory(new PropertyValueFactory<>("reg"));
        Columnmonth.setCellValueFactory(new PropertyValueFactory<>("month"));
        Columnyear.setCellValueFactory(new PropertyValueFactory<>("year"));
        Columnamount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        Columnfine.setCellValueFactory(new PropertyValueFactory<>("fine"));
        Columntotal.setCellValueFactory(new PropertyValueFactory<>("total"));
         Columndate.setCellValueFactory(new PropertyValueFactory<>("date"));
         
   
    
 
 SortedList<bill> sorteddata = new SortedList<>(filtereddata);
 sorteddata.comparatorProperty().bind(billpending.comparatorProperty());
 //tableUser.setItems(null);
 
 billpending.setItems(sorteddata);
        
       // System.out.println(event.getCode().toString().toLowerCase());
    }
  
  
    @FXML
    private void refreshbill(ActionEvent event) throws SQLException, ClassNotFoundException {
       loadpendingbill();
    
}
}

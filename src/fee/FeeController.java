/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fee;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Year;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import viewstudents.ViewstudentsController;
import viewstudents.DbConnection;

/**
 * FXML Controller class
 *
 * @author govind
 */
public class FeeController implements Initializable {
    @FXML
    private Pane p1,p2,p3;
      @FXML
    private VBox vb1,vb2;
          @FXML
    private Pane vb3;
    @FXML
    private ComboBox hostelno,select;
     @FXML
    private ComboBox hostelno1;
      @FXML
    private ComboBox roomno;
      @FXML
    private TextField fees,totalunit,unitrate,totalfee;

    @FXML
    private TextField reg;
    @FXML
    private DatePicker duedate;

    @FXML
    private ComboBox<String> year;
   @FXML
   private Button back,ok;
    @FXML
    private ComboBox<String> month;

        @FXML
    private JFXButton proreg,proroom,prohostel;
    @FXML
    private TextField name;

    @FXML
    private TextField fine;
    
            private DbConnection dc;
    private PreparedStatement pst;
    private ObservableList<Object> data;
    private ObservableList<Object> data1;

    String btnclk = "";
     @FXML
    void bills(ActionEvent event) throws IOException {
        System.out.print("clicked");
Parent s1 = FXMLLoader.load(getClass().getResource("/basic/bill.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
        
    }
    @FXML
    void proceed(ActionEvent event) {
        Alert alert2;
  alert2 = new Alert(Alert.AlertType.INFORMATION);
         alert2.setTitle("Check details");
         alert2.setHeaderText("Ok");
         alert2.setContentText("recheck all the entries");
         alert2.showAndWait();
JFXButton btn =(JFXButton) event.getSource();
if(btn.getId().equalsIgnoreCase("add3")) {
    btnclk = "3";
    p3.setVisible(false);
}
if(btn.getId().equalsIgnoreCase("add2")) {
btnclk = "2";
p2.setVisible(false);}
if(btn.getId().equalsIgnoreCase("add1")) {
btnclk = "1";
p1.setVisible(false);
}
vb1.setVisible(true);
vb2.setVisible(true);
vb3.setVisible(true);
select.setDisable(true);
year.setDisable(true);
month.setDisable(true);
duedate.setDisable(true);

fine.setEditable(false);
totalunit.setEditable(false);
unitrate.setEditable(false);
back.setDisable(true);
ok.setDisable(true);
int ut = Integer.parseInt(totalunit.getText());
Float ur = Float.parseFloat(unitrate.getText());
int fn = Integer.parseInt(fine.getText());
Float temp1 = ur*ut;
Float temp2 = temp1+fn;
fees.setText(temp1.toString());
totalfee.setText(temp2.toString());

    }
    @FXML
    void addbill(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        
 Alert alert;
         alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setTitle("Confirmation ");
         alert.setHeaderText("OK");
         alert.setContentText("are you sure?");
         Optional<ButtonType> result = alert.showAndWait();
         if (result.get() == ButtonType.OK)
         {
        if(btnclk.equals("1")) {
        addstudentwise();}
        if(btnclk.equals("2")) {
        addroomwise();}
        if(btnclk.equals("3")) {
        addhostelwise();}
          Parent s1 = FXMLLoader.load(getClass().getResource("/process/elecfee.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
         }
       

    }
      @FXML
    void goback(ActionEvent event) {
        vb1.setVisible(false);
         vb2.setVisible(false);
          vb3.setVisible(false);
          select.setDisable(false);
          year.setDisable(false);
           month.setDisable(false);
            fine.setEditable(true);
             duedate.setDisable(false);
              totalunit.setEditable(true);
               unitrate.setEditable(true);
               ok.setDisable(false);
               back.setDisable(false);
               if(btnclk.equals("1"))
               {
                   p1.setVisible(true);
               }
                if(btnclk.equals("2"))
               {
                   p2.setVisible(true);
               }
                 if(btnclk.equals("3"))
               {
                   p3.setVisible(true);
               }
                 
          
        
        

    }
    @FXML
    void typeselector(ActionEvent event)
    {
    
    if (select.getValue()=="student wise") {p1.setVisible(true); p2.setVisible(false); p3.setVisible(false);}
    if (select.getValue()=="room wise") {p1.setVisible(false); p2.setVisible(true); p3.setVisible(false);}
    if (select.getValue()=="hostel wise") {p1.setVisible(false); p2.setVisible(false); p3.setVisible(true);}
    }
    @FXML
    void addhostelwise() throws ClassNotFoundException
    {
        
    String hno1 = (String) hostelno1.getValue();
    String yr = year.getValue();
        String mnth = month.getValue();
        //String tunit = totalunit.getText();
        //String unitr = unitrate.getText();
          //String due = (String) duedate.getEditor().getText();
          Date dt = Date.valueOf(duedate.getValue());
          int tunit = Integer.parseInt(totalunit.getText());
          float unitr = Float.parseFloat(unitrate.getText());
          float efee = tunit*unitr;
          
          int fne = Integer.parseInt(fine.getText());
       try {
Connection conn = dc.Connect();
      String query2 = "SELECT * FROM hostel WHERE hostel_name = '" +hno1 + "' ";
    ResultSet rs2 = conn.createStatement().executeQuery(query2);
    rs2.next();
   int curr = Integer.parseInt(rs2.getString(3));
  float totalf = efee+fne;
  float tunit1 = (float) tunit/curr;
  float fne1 = (float) fne/curr;
  float eper = efee/curr;
  int total = (int) (totalf/curr);
  float testing = total*curr;
  if (testing!=totalf) { total++;}
   String query1 = "SELECT * FROM students WHERE  hostel ='"+hno1+"' ";
    ResultSet rs1 = conn.createStatement().executeQuery(query1);
    String del = "DELETE FROM `checkelectricbill`";
     Statement stmtdel = conn.createStatement();
             stmtdel.execute(del);
    while(rs1.next())
    {  String rg = rs1.getString(4);
            
String query = "INSERT INTO `checkelectricbill`(reg,month,year,`unit(KWh)`,`unit rate`,`amount due`,fine,`total fee`,`due date`) VALUES(?,?,?,?,?,?,?,?,?) ";
  pst = conn.prepareStatement(query);
             //pst.setString(1,id);
             pst.setString(1, rg);
             pst.setString(2, mnth);
             pst.setString(3, yr);
             pst.setFloat(4,tunit1);
             pst.setFloat(5, unitr);
             pst.setFloat(6, eper);
             pst.setFloat(7, fne1);
             pst.setInt(8, total);
             pst.setDate(9, dt);
             
             pst.execute();
             
    }    
      Alert alert3;
         alert3 = new Alert(Alert.AlertType.INFORMATION);
         alert3.setTitle("fee addedd");
         alert3.setHeaderText("Processing");
         alert3.setContentText("fee(" +total+ " rupees) adding to  "+curr+" students  from hostel ->" +hno1 );
         alert3.showAndWait();
         
          }
          catch (SQLException ex)
                { 
                
               System.err.println("ERRor "+ex);

                }
    
    
    }
    @FXML
    void checkname(ActionEvent event) {
String rno = reg.getText();
 try
           {
             Connection conn = dc.Connect();
             
            
             
             
            String query1 = "SELECT * FROM students WHERE `reg. no` = '" +rno + "' ";
    ResultSet rs1 = conn.createStatement().executeQuery(query1);
    
    if(rs1.next())
   
    {name.setText(rs1.getString(5));}
     else
    {
            Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Warning ");
         alert.setHeaderText("NO Student exist ");
         alert.setContentText("check reg no");
         alert.show();
            }
     }
   
    
       catch (ClassNotFoundException ex) {
         Logger.getLogger(ViewstudentsController.class.getName()).log(Level.SEVERE, null, ex);
     } catch (SQLException ex) {
         Logger.getLogger(ViewstudentsController.class.getName()).log(Level.SEVERE, null, ex);
     }
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
        
         hostelno.setItems(data);
         hostelno1.setItems(data);
         
        
         
    
        
        
    }
 @FXML
    public void chooseoption(MouseEvent event) throws ClassNotFoundException, SQLException
    {
        
        
        
        
            String temp = (String) hostelno.getValue();
               
        try {
  Connection conn = dc.Connect();
  data1 = FXCollections.observableArrayList(); 
  //String insert1 = "INSERT INTO user_info VALUES('anubhav','anubhav.com','')";
  //Statement stmt = (Statement) conn.createStatement();
   //stmt.executeUpdate(insert1);
  
  ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM room WHERE hostel_name ='"+temp+ "' AND current>0");
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
         
        roomno.setItems(data1);
        
        
        
    }


    @FXML
    void addstudentwise() throws ClassNotFoundException, SQLException {
        
        String rno = reg.getText();
        String yr = year.getValue();
        String mnth = month.getValue();
       
          //String due = (String) duedate.getEditor().getText();
          Date dt = Date.valueOf(duedate.getValue());
         // int efee = Integer.parseInt(fees.getText());
          int fne = Integer.parseInt(fine.getText());
          //String due = (String) duedate.getEditor().getText();
        //  Date dt = Date.valueOf(duedate.getValue());
         // String efee = fees.getText();
         // String fne = fine.getText();
            int tunit = Integer.parseInt(totalunit.getText());
          float unitr = Float.parseFloat(unitrate.getText());
          float efee = tunit*unitr;
            int curr = 1;
  float totalf = efee+fne;
  float eper = efee/curr;
  int total = (int) (totalf/curr);
  float testing = total*curr;
  if (testing!=totalf) { total++;}
          try {
Connection conn = dc.Connect();
String del = "DELETE FROM `checkelectricbill`";
     Statement stmtdel = conn.createStatement();
             stmtdel.execute(del);
String query = "INSERT INTO `checkelectricbill`(reg,month,year,`unit(KWh)`,`unit rate`,`amount due`,fine,`total fee`,`due date`) VALUES(?,?,?,?,?,?,?,?,?) ";
  pst = conn.prepareStatement(query);
             //pst.setString(1,id);
             pst.setString(1, rno);
             pst.setString(2, mnth);
             pst.setString(3, yr);
             pst.setFloat(4,tunit);
             pst.setFloat(5, unitr);
             pst.setFloat(6, eper);
             pst.setFloat(7, fne);
             pst.setInt(8, total);
             pst.setDate(9, dt);
             
             pst.execute();
               Alert alert3;
         alert3 = new Alert(Alert.AlertType.INFORMATION);
         alert3.setTitle("fee addedd");
         alert3.setHeaderText("sucessfull");
         alert3.setContentText("fee(" +total+ " rupees) added to reg no ->"+rno );
         alert3.showAndWait();
          }
          catch (SQLException ex)
                { 
                
               System.err.println("ERRor "+ex);

                }


    }
    @FXML
    void addroomwise() throws ClassNotFoundException, SQLException {
        
 String yr = year.getValue();
        String mnth = month.getValue();
          //String due = (String) duedate.getEditor().getText();
          Date dt = Date.valueOf(duedate.getValue());
         // int efee = Integer.parseInt(fees.getText());
          int fne = Integer.parseInt(fine.getText());
          String hno = (String) hostelno.getValue();
          String rno = (String) roomno.getValue();
          
          // String hno1 = (String) hostelno1.getValue();
   // String yr = year.getValue();
       // String mnth = month.getValue();
        //String tunit = totalunit.getText();
        //String unitr = unitrate.getText();
          //String due = (String) duedate.getEditor().getText();
         // Date dt = Date.valueOf(duedate.getValue());
          int tunit = Integer.parseInt(totalunit.getText());
          float unitr = Float.parseFloat(unitrate.getText());
          float efee = tunit*unitr;
          
        //  int fne = Integer.parseInt(fine.getText());
           try {
Connection conn = dc.Connect();
      String query2 = "SELECT * FROM room WHERE room_no = '" +rno + "' AND hostel_name ='"+hno+"' ";
    ResultSet rs2 = conn.createStatement().executeQuery(query2);
    rs2.next();
  int curr = Integer.parseInt(rs2.getString(5));
  float totalf = efee+fne;
  float eper = efee/curr;
  float fne1 = fne/curr;
  float tunit1 = (float)tunit/curr;
  int total = (int) (totalf/curr);
  float testing = total*curr;
  if (testing!=totalf) { total++;}
  
   String query1 = "SELECT * FROM students WHERE room = '" +rno + "' AND hostel ='"+hno+"' ";
    ResultSet rs1 = conn.createStatement().executeQuery(query1);
    String del = "DELETE FROM `checkelectricbill`";
     Statement stmtdel = conn.createStatement();
             stmtdel.execute(del);
    while(rs1.next())
    {  String rg = rs1.getString(4);
            
String query = "INSERT INTO `checkelectricbill`(reg,month,year,`unit(KWh)`,`unit rate`,`amount due`,fine,`total fee`,`due date`) VALUES(?,?,?,?,?,?,?,?,?) ";
  pst = conn.prepareStatement(query);
             //pst.setString(1,id);
             pst.setString(1, rg);
             pst.setString(2, mnth);
             pst.setString(3, yr);
             pst.setFloat(4,tunit1);
             pst.setFloat(5, unitr);
             pst.setFloat(6, eper);
             pst.setFloat(7, fne1);
             pst.setInt(8, total);
             pst.setDate(9, dt);
             
             pst.execute();
             
    }
      Alert alert3;
         alert3 = new Alert(Alert.AlertType.INFORMATION);
         alert3.setTitle("fee processing");
         alert3.setHeaderText("processing");
         alert3.setContentText("fee(" +total+ " rupees) added to  "+curr+" students  from hostel ->" +hno+" and room no ->" +rno );
         alert3.showAndWait();
    }    
          
          catch (SQLException ex)
                { 
                
               System.err.println("ERRor "+ex);

                }
          
    }
    /**
     * Initializes the controller class.
     */
    ObservableList<String> selectlist = FXCollections.observableArrayList("student wise","room wise","hostel wise");
    ObservableList<String> hostellist = FXCollections.observableArrayList("hostel1","hostel2","hostel3");
        ObservableList<String> monthlist = FXCollections.observableArrayList("January","Febuary","March","April","May","June","July","August","September","October","November","December");

    ObservableList<String> yearlist = FXCollections.observableArrayList("2016","2017","2018","2019","2020");
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        // TODO
        month.setItems(monthlist);
        year.setItems(yearlist);
        select.setItems(selectlist);
       p1.setVisible(false);
        p2.setVisible(false);
         p3.setVisible(false);
         vb1.setVisible(false);
         vb2.setVisible(false);
         vb3.setVisible(false);
         
         dc = new DbConnection();
       proreg.setId("add1");
       proroom.setId("add2");
       prohostel.setId("add3");
    }    


    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process;

import com.jfoenix.controls.JFXDatePicker;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import viewstudents.DbConnection;

/**
 * FXML Controller class
 *
 * @author govind
 */
public class PaymentController implements Initializable {

     @FXML
    private TextField reg;
    @FXML
    private Label label1;
    @FXML
    private TextField month;

    @FXML
    private TextField payid;

    @FXML
    private ComboBox method;

    @FXML
    private TextField transid;

    @FXML
    private TextField totalbill;

    @FXML
    private TextField remark;

    @FXML
    private JFXDatePicker submitdate;
 

        ObservableList<String> methodtype = FXCollections.observableArrayList("Cash","Online");
    private DbConnection dc;
    private PreparedStatement pst;
    private PreparedStatement pst1;

    public void setReg1(String rg)
    {
    reg.setText(rg);
    }
    public void setLb(String rg)
    {
   label1.setText(rg);
    }
     public void setMonth1(String rg)
    {
    month.setText(rg);
    }
      public void setPayid1(String rg)
    {
    payid.setText(rg);
    }
       public void setTotalbill1(String rg)
    {
    totalbill.setText(rg);
    }
    @FXML
    void cancel(ActionEvent event) throws IOException {
Stage st = (Stage) reg.getScene().getWindow();
st.close();
    }

    @FXML
    void payfee(ActionEvent event) throws ClassNotFoundException, IOException, SQLException {
String rno = reg.getText();
String my = month.getText();
String tot = totalbill.getText();
String md = method.getValue().toString();
 Date dt = Date.valueOf(submitdate.getValue());
String tid = transid.getText();
String rmk = remark.getText();
String pid = payid.getText();
String yr = my.substring(my.length()-5,my.length()-1);
String mth = my.substring(0,my.length()-6);
Alert alert;
         alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setTitle("Confirmation ");
         alert.setHeaderText("OK");
         alert.setContentText("are you sure?");
         Optional<ButtonType> result = alert.showAndWait();
         if (result.get() == ButtonType.OK)
         {
         System.out.println(mth);
         System.out.println(yr);
         try {
Connection conn = dc.Connect();
String query = null;
int ck = 0;
if (label1.getText()=="1")
{
 query = "INSERT INTO `transaction`(`pay_id`, `reg`, `month`, `method`, `trans_id`,`amount_paid`, `remark`, `pay_date`) VALUES (?,?,?,?,?,?,?,?)";
  ck = 1;
}
if (label1.getText()=="2")
{
 query = "INSERT INTO `transrent`(`pay_id`, `reg`, `month`, `method`, `trans_id`,`amount_paid`, `remark`, `pay_date`) VALUES (?,?,?,?,?,?,?,?)";
         ck=2;
}
if (label1.getText()=="3")
{
 query = "INSERT INTO `transmess`(`pay_id`, `reg`, `month`, `method`, `trans_id`,`amount_paid`, `remark`, `pay_date`) VALUES (?,?,?,?,?,?,?,?)";
         ck=3;
}
pst = conn.prepareStatement(query);
             //pst.setString(1,id);
             pst.setString(1, pid);
             pst.setString(2, rno);
             pst.setString(3, my);
             pst.setString(4, md);
             pst.setString(5, tid);
             pst.setString(6, tot);
             pst.setString(7, rmk);
             pst.setDate(8,dt);
             pst.execute();





if (ck==1)
{
             //pst.setString(1,id);
             String query1 ="SELECT * FROM `pending bill` WHERE reg = '"+rno+"' AND month = '"+mth+"' AND year ='"+yr+"'";
ResultSet rs = conn.createStatement().executeQuery(query1);
if(rs.next())
{
String  query2 = "INSERT INTO `paidbill`(`reg`, `month`, `year`, `total unit`, `unit rate`, `amount due`, `fine`, `total fee`, `due date`, `paid`) VALUES (?,?,?,?,?,?,?,?,?,?)";
       pst1 = conn.prepareStatement(query2);
       int id1 = rs.getInt(1);
             pst1.setString(1,rs.getString(2));
            pst1.setString(2,rs.getString(3));
             pst1.setString(3,rs.getString(4));
              pst1.setFloat(4,rs.getFloat(5));
               pst1.setFloat(5,rs.getFloat(6));
                pst1.setFloat(6,rs.getFloat(7));
                 pst1.setFloat(7,rs.getFloat(8));
                  pst1.setInt(8,rs.getInt(9));
                   pst1.setDate(9,rs.getDate(10));
                    pst1.setString(10,pid);
                    pst1.execute();
            String query3 = "DELETE FROM `pending bill` WHERE id = '"+id1+"' ";
             Statement stmt2 = conn.createStatement();
             stmt2.execute(query3);
              Alert alert1;
         alert1 = new Alert(Alert.AlertType.INFORMATION);
         alert1.setTitle("payment ");
         alert1.setHeaderText("sucessfull");
         alert1.setContentText("payment suceesfull");
          alert1.showAndWait();
            Parent s1 = FXMLLoader.load(getClass().getResource("/basic/bill.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
                    
                   

}
}
if (ck==2)
{
String query1 ="SELECT * FROM `pendrent` WHERE reg = '"+rno+"' AND month = '"+mth+"' AND year ='"+yr+"'";
ResultSet rs = conn.createStatement().executeQuery(query1);
if(rs.next())
{     int id1 = rs.getInt(1);
String  query2 = "INSERT INTO `comperent`(`reg`, `month`, `year`, `room rent`, `fine`, `total rent`, `last date`, `paid`) VALUES (?,?,?,?,?,?,?,?)";
       pst1 = conn.prepareStatement(query2);
             pst1.setString(1,rs.getString(2));
            pst1.setString(2,rs.getString(3));
             pst1.setString(3,rs.getString(4));
              pst1.setInt(4,rs.getInt(5));
               pst1.setInt(5,rs.getInt(6));
                pst1.setInt(6,rs.getInt(7));
                 pst1.setDate(7,rs.getDate(8));
              
                    pst1.setString(8,pid);
                    pst1.execute();
            String query3 = "DELETE FROM `pendrent` WHERE id = '"+id1+"' ";
             Statement stmt2 = conn.createStatement();
             stmt2.execute(query3);
              Alert alert1;
         alert1 = new Alert(Alert.AlertType.INFORMATION);
         alert1.setTitle("payment ");
         alert1.setHeaderText("sucessfull");
         alert1.setContentText("payment suceesfull");
          alert1.showAndWait();
            Parent s1 = FXMLLoader.load(getClass().getResource("/basic/bill.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();


}


         }
if (ck==3)
{
String query1 ="SELECT * FROM `pendmess` WHERE reg = '"+rno+"' AND month = '"+mth+"' AND year ='"+yr+"'";
ResultSet rs = conn.createStatement().executeQuery(query1);
if(rs.next())
{     int id1 = rs.getInt(1);
String  query2 = "INSERT INTO `compemess`(`reg`, `month`, `year`, `mess bill`, `fine`, `total mess`, `last date`, `paid`) VALUES (?,?,?,?,?,?,?,?)";
       pst1 = conn.prepareStatement(query2);
             pst1.setString(1,rs.getString(2));
            pst1.setString(2,rs.getString(3));
             pst1.setString(3,rs.getString(4));
              pst1.setInt(4,rs.getInt(5));
               pst1.setInt(5,rs.getInt(6));
                pst1.setInt(6,rs.getInt(7));
                 pst1.setDate(7,rs.getDate(8));
              
                    pst1.setString(8,pid);
                    pst1.execute();
            String query3 = "DELETE FROM `pendmess` WHERE id = '"+id1+"' ";
             Statement stmt2 = conn.createStatement();
             stmt2.execute(query3);
              Alert alert1;
         alert1 = new Alert(Alert.AlertType.INFORMATION);
         alert1.setTitle("payment ");
         alert1.setHeaderText("sucessfull");
         alert1.setContentText("payment suceesfull");
          alert1.showAndWait();
            Parent s1 = FXMLLoader.load(getClass().getResource("/basic/bill.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();


}


         }

         }
         catch (SQLException ex)
                { 
                
               System.err.println("ERRor "+ex);

                }


    
    }
    }
     /* Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc = new DbConnection();
        LocalDate ld = LocalDate.now();
        submitdate.setValue(ld);
        method.setItems(methodtype);
        method.getSelectionModel().selectFirst();
        transid.setText("NO");
        transid.setDisable(true);
                method.getSelectionModel().selectedItemProperty().addListener((ObservableValue v , Object oldValue, Object newValue) -> {if(newValue.equals("Online")) {transid.setDisable(false);} if(newValue.equals("Cash")) {transid.setText("NO");transid.setDisable(true);}});
                remark.setText("NO");
                
                

        
    }    

  
    
}

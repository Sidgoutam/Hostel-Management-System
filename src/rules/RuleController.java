/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rules;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import static com.itextpdf.text.Element.ALIGN_LEFT;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXTextArea;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import viewstudents.DbConnection;

/**
 * FXML Controller class
 *
 * @author govind
 */
public class RuleController implements Initializable {

    @FXML
    private FontAwesomeIconView lout;
    
        @FXML
    private JFXTextArea rule1;

    @FXML
    private Button edit;

    @FXML
    private Button save;
    private DbConnection dc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 
        save.setVisible(false);
        dc = new DbConnection();
        try {
            setrule();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RuleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
    public void setrule() throws ClassNotFoundException
    {
    try
    {
    Connection conn = dc.Connect();
    String qry = "SELECT `rule` FROM `rules`";
    ResultSet rs = conn.createStatement().executeQuery(qry);
    if (rs.next())
    {
    rule1.setText(rs.getString(1));
    }
    }   catch (SQLException ex) {
            Logger.getLogger(RuleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      @FXML
    void savepdf(ActionEvent event) throws DocumentException, BadElementException, FileNotFoundException, IOException {
TextInputDialog dialog = new TextInputDialog("Records");
dialog.setTitle("Save to Pdf");
dialog.setHeaderText("for pdf file name");
dialog.setContentText("Please enter file name:");

// Traditional way to get the response value.
Optional<String> result = dialog.showAndWait();
if (result.isPresent()){
    
if(result.get().isEmpty())
{
 Alert alert2;
  alert2 = new Alert(Alert.AlertType.ERROR);
         alert2.setTitle("file not saved");
         alert2.setHeaderText("file name empty");
         alert2.setContentText("you must have to fill the file name");
         alert2.showAndWait();
}
else
{
            
  Document doc = new Document(PageSize.A4);
  PdfWriter.getInstance(doc, new FileOutputStream("../../Downloads/"+result.get()+".pdf"));
  doc.open();
  Font f1=new Font(Font.FontFamily.TIMES_ROMAN,15.0f,Font.BOLD,BaseColor.BLACK);
  Font f2=new Font(Font.FontFamily.TIMES_ROMAN,13.0f,Font.NORMAL,BaseColor.BLUE);
   Font f3=new Font(Font.FontFamily.TIMES_ROMAN,10.0f,Font.UNDERLINE,BaseColor.BLUE);
   String path = "src/image/log.png";

  Paragraph para1 = new Paragraph("INDIAN INSTITUTE OF INFORMATION TECHNOLOGY KALYANI",f1);
  para1.setAlignment(Paragraph.ALIGN_CENTER);
  doc.add(para1);
    Image img = Image.getInstance(path);
  img.setAlignment(ALIGN_LEFT);
  img.setAbsolutePosition(60,730);
  img.scaleAbsolute(50,50);
  img.scaleToFit(50,50);
  doc.add(img);
  
  Paragraph para2 = new Paragraph("(Autonomous institution under MHRD , Govt. Of India & Department of\n Information"
          + "Technology & Electronics, Govt. of West Bengal)\n Webel IT Park,Kalyani-741235,West Bengal\n\n\n",f2);
  
  para2.setAlignment(Paragraph.ALIGN_CENTER);
  para2.setPaddingTop(10);
  doc.add(para2);
  Paragraph para3 = new Paragraph("Hostel Rules and Regulations \n\n",f3);
  para3.setAlignment(Paragraph.ALIGN_LEFT);
  doc.add(para3);
  Font f8=new Font(Font.FontFamily.TIMES_ROMAN,8.0f,Font.NORMAL,BaseColor.BLACK);
  Paragraph p3 = new Paragraph(rule1.getText(),f8);
  p3.setAlignment(Paragraph.ALIGN_LEFT);
  p3.setPaddingTop(10);
  doc.add(p3);
 
  
  

  String dat = LocalDateTime.now().toString();
  
  Font f4=new Font(Font.FontFamily.TIMES_ROMAN,10.0f,Font.NORMAL,BaseColor.BLACK);
    Paragraph para4 = new Paragraph("\nissued by govind singh at "+dat,f4);
  para4.setAlignment(Paragraph.ALIGN_BOTTOM);
  para4.setAlignment(Paragraph.ALIGN_RIGHT);
  doc.add(para4);
  doc.close(); 
    }
}
    

    }
    
   @FXML
    void onedit(ActionEvent event) {
edit.setVisible(false);
save.setVisible(true);
rule1.setEditable(true);
    }

    @FXML
    void onsave(ActionEvent event) throws ClassNotFoundException, IOException {
         Alert alert;
         alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setTitle("Confirmation ");
         alert.setHeaderText("OK");
         alert.setContentText("are you sure?");
         Optional<ButtonType> result = alert.showAndWait();
         if (result.get() == ButtonType.OK)
         {
try
{
Connection conn = dc.Connect();
String qry = "UPDATE `rules` SET `rule`= '"+rule1.getText()+"'";
Statement st = conn.createStatement();
st.executeUpdate(qry);
 Alert alert1;
         alert1 = new Alert(Alert.AlertType.INFORMATION);
         alert1.setTitle("Rules ");
         alert1.setHeaderText("sucessfull");
         alert1.setContentText("Rules updated sucessfully");
         alert1.showAndWait();
         Parent s1 = FXMLLoader.load(getClass().getResource("/rules/rule.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();


}       catch (SQLException ex) {
            Logger.getLogger(RuleController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    void dashboard(ActionEvent event) throws IOException {
Parent s1 = FXMLLoader.load(getClass().getResource("/viewroomstatus/roomstatus.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }
        @FXML
    void mess(ActionEvent event) throws IOException {
Parent s1 = FXMLLoader.load(getClass().getResource("/basic/mess.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }
      @FXML
    void home(MouseEvent event) throws IOException {
        Parent s1 = FXMLLoader.load(getClass().getResource("/home/g1.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();

    }

    @FXML
    void logout(MouseEvent event) throws IOException {
 Alert alert;
         alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setTitle("Confirmation ");
         alert.setHeaderText("OK");
         alert.setContentText("are you sure you want to logout?");
         Optional<ButtonType> result = alert.showAndWait();
         if (result.get() == ButtonType.OK)
         {
               Parent s1 = FXMLLoader.load(getClass().getResource("/login/loginpage.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
         }
    }
    @FXML
    void creator(MouseEvent event) throws IOException {

Parent s1 = FXMLLoader.load(getClass().getResource("/creator/creator.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }
        @FXML
    void rules(MouseEvent event) throws IOException {

Parent s1 = FXMLLoader.load(getClass().getResource("/rules/rule.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }
      @FXML
    void student(ActionEvent event) throws IOException {
Parent s1 = FXMLLoader.load(getClass().getResource("/basic/student.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }
  @FXML
    void rent(ActionEvent event) throws IOException {
Parent s1 = FXMLLoader.load(getClass().getResource("/basic/rent.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }

    @FXML
    void rooms(ActionEvent event) throws IOException {
Parent s1 = FXMLLoader.load(getClass().getResource("/basic/rooms.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }

    @FXML
    void search(ActionEvent event) throws IOException {
Parent s1 = FXMLLoader.load(getClass().getResource("/search/search.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }

    @FXML
    void setting(ActionEvent event) throws IOException {
Parent s1 = FXMLLoader.load(getClass().getResource("/resetpassword/sidemain.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }
      @FXML
    void shiftroom(ActionEvent event) throws IOException {
Parent s1 = FXMLLoader.load(getClass().getResource("/basic/shift.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }
}

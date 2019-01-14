/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Messbill;

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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import roomrent.PendingroomrentController;
import roomrent.rent;
import viewstudents.DbConnection;

/**
 * FXML Controller class
 *
 * @author govind
 */
public class PaidmessController implements Initializable {

     @FXML
    private TextField SearchField;
    @FXML
    private TableView<rent> pendrent;
    @FXML
    private TableColumn<rent,Integer> Columnid;
    @FXML
    private TableColumn<rent,Integer> Columnreg;
    @FXML
    private TableColumn<rent, String> Columnname;
    @FXML
    private TableColumn<rent, String> Columnhostel;
    @FXML
    private TableColumn<rent, String> Columnroom;
    @FXML
    private TableColumn<rent, String> Columnmonth;
    @FXML
    private TableColumn<rent, Integer> Columnyear;
    @FXML
    private TableColumn<rent, Integer> Columnfine;
    @FXML
    private TableColumn<rent, Integer> Columntotal;
    @FXML
    private TableColumn<rent, String> Columnlastdate;
      @FXML
    private TableColumn<rent, String> Columnpaid;
    
    private DbConnection dc;
  final ObservableList<rent>data = FXCollections.observableArrayList();
    FilteredList<rent> filtereddata = new FilteredList<>(data, e-> true);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc = new DbConnection();
         try {
             loaddata();
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(PendingroomrentController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }    
    
    @FXML
    void printdata(ActionEvent event) throws FileNotFoundException, DocumentException, BadElementException, IOException {
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
  Paragraph para3 = new Paragraph("Mess bill Paid \n\n",f3);
  para3.setAlignment(Paragraph.ALIGN_LEFT);
  doc.add(para3);
  
  PdfPTable table = new PdfPTable(11);
  table.setWidthPercentage(100f);
  PdfPCell cell = null;
 
  table.addCell(getcell1("id",PdfPCell.TITLE));
  table.addCell(getcell1("reg. mo",PdfPCell.ANCHOR));
  table.addCell(getcell1("name",PdfPCell.DIV));
  table.addCell(getcell1("hostel",PdfPCell.BOX));
  table.addCell(getcell1("room",PdfPCell.BOTTOM));
  table.addCell(getcell1("month",PdfPCell.HEADER));
  table.addCell(getcell1("year",PdfPCell.TOP));
  table.addCell(getcell1("fine",PdfPCell.ALIGN_CENTER));
  table.addCell(getcell1("total",PdfPCell.ALIGN_CENTER));
  table.addCell(getcell1("due date",PdfPCell.ALIGN_CENTER));
    table.addCell(getcell1("paid",PdfPCell.ALIGN_CENTER));
 
  
  for (int i=0;i<pendrent.getItems().size();i++)
  {
      table.addCell(getcell(pendrent.getItems().get(i).getId().toString(),PdfPCell.ALIGN_CENTER));
      table.addCell(getcell(pendrent.getItems().get(i).getReg().toString(),PdfPCell.ALIGN_CENTER));
       table.addCell(getcell(pendrent.getItems().get(i).getName(),PdfPCell.ALIGN_CENTER));
      table.addCell(getcell(pendrent.getItems().get(i).getHostel(),PdfPCell.ALIGN_CENTER));
      table.addCell(getcell(pendrent.getItems().get(i).getRoom(),PdfPCell.ALIGN_CENTER));
     
      table.addCell(getcell(pendrent.getItems().get(i).getMonth(),PdfPCell.ALIGN_CENTER));
      table.addCell(getcell(pendrent.getItems().get(i).getYear().toString(),PdfPCell.ALIGN_CENTER));
     
      table.addCell(getcell(pendrent.getItems().get(i).getFine().toString(),PdfPCell.ALIGN_CENTER));
      table.addCell(getcell(pendrent.getItems().get(i).getTotal().toString(),PdfPCell.ALIGN_CENTER));
      table.addCell(getcell(pendrent.getItems().get(i).getLastdate(),PdfPCell.ALIGN_CENTER));
       table.addCell(getcell(pendrent.getItems().get(i).getPaid(),PdfPCell.ALIGN_CENTER));
      
      
   
      
      
  }

  doc.add(table);
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
      private PdfPCell getcell(String text, int alignment) {
          Font f9=new Font(Font.FontFamily.TIMES_ROMAN,8.0f,Font.NORMAL,BaseColor.BLACK);
    PdfPCell  cell = new PdfPCell(new Phrase(text,f9));
    cell.setPadding(5);
cell.setHorizontalAlignment(30);
cell.setVerticalAlignment(20);
cell.setBorder(PdfPCell.RECTANGLE);
cell.setBorderColor(BaseColor.BLUE);
//To change body of generated methods, choose Tools | Templates.
        return cell;
    }
        private PdfPCell getcell1(String text, int alignment) {
            Font f1=new Font(Font.FontFamily.TIMES_ROMAN,10.0f,Font.BOLD,BaseColor.BLACK);
    PdfPCell  cell = new PdfPCell(new Phrase(text,f1));
    cell.setPadding(5);
cell.setHorizontalAlignment(30);
cell.setVerticalAlignment(20);
cell.setBorder(PdfPCell.RECTANGLE);
cell.setBorderColor(BaseColor.RED);
//To change body of generated methods, choose Tools | Templates.
        return cell;
    }
    

    public void loaddata() throws ClassNotFoundException
    {
    
     try{ Connection conn = dc.Connect();

  //String insert1 = "INSERT INTO user_info VALUES('anubhav','anubhav.com','')";
  //Statement stmt = (Statement) conn.createStatement();
   //stmt.executeUpdate(insert1);
  data.clear();
  SearchField.clear();
  ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM `compemess`");
  int start =1;
  while (rs.next())
  {
      ResultSet rs1 = conn.createStatement().executeQuery("SELECT * FROM students WHERE `reg. no`='"+rs.getString(2)+"'");
      rs1.next();
      String name = rs1.getString(5);
      String hostno = rs1.getString(2);
      String roomno = rs1.getString(3);
              
    
  data.add(new rent(start,Integer.parseInt(rs.getString(2)),name,hostno,roomno,rs.getString(3),Integer.parseInt(rs.getString(4)),rs.getInt(6),rs.getInt(7),rs.getString(8),rs.getString(9)));
  start+=1;
  }
    }
    
        catch (SQLException ex)
                { 
                
               System.err.println("ERRor "+ex);

                }
        Columnname.setCellValueFactory(new PropertyValueFactory<>("name"));
         Columnhostel.setCellValueFactory(new PropertyValueFactory<>("hostel"));
          Columnroom.setCellValueFactory(new PropertyValueFactory<>("room"));
           
        Columnid.setCellValueFactory(new PropertyValueFactory<>("id"));
        Columnreg.setCellValueFactory(new PropertyValueFactory<>("reg"));
        Columnmonth.setCellValueFactory(new PropertyValueFactory<>("month"));
        Columnyear.setCellValueFactory(new PropertyValueFactory<>("year"));
        
        Columnfine.setCellValueFactory(new PropertyValueFactory<>("fine"));
        Columntotal.setCellValueFactory(new PropertyValueFactory<>("total"));
         Columnlastdate.setCellValueFactory(new PropertyValueFactory<>("lastdate"));
                  Columnpaid.setCellValueFactory(new PropertyValueFactory<>("paid"));
         
         // billpending.setItems(null);
         //System.out.print(data);
        pendrent.setItems(data);
    
    }

    @FXML
    private void loadbill(ActionEvent event) throws ClassNotFoundException {
       loaddata();
    }
    
    
    @FXML
    void searchlist(KeyEvent event) {
   SearchField.textProperty().addListener((observable,oldValue,newValue)->{
 
        filtereddata.setPredicate(rent ->{
     
     if(newValue == null || newValue.isEmpty())
     {   return true;
     }
     String lowerCaseFilter = newValue.toLowerCase();
     if (String.valueOf(rent.getName()).toLowerCase().contains(lowerCaseFilter))
     {   return true;
     }
     else if(String.valueOf(rent.getYear()).toLowerCase().contains(lowerCaseFilter))
     {
     return true;
     }
     else if(String.valueOf(rent.getMonth()).toLowerCase().contains(lowerCaseFilter))
     {
     return true;
     }
      else if(String.valueOf(rent.getReg()).toLowerCase().contains(lowerCaseFilter))
     {
     return true;
     }
     return false;
 
 });
 
 });
   Columnname.setCellValueFactory(new PropertyValueFactory<>("name"));
         Columnhostel.setCellValueFactory(new PropertyValueFactory<>("hostel"));
          Columnroom.setCellValueFactory(new PropertyValueFactory<>("room"));
           
        Columnid.setCellValueFactory(new PropertyValueFactory<>("id"));
        Columnreg.setCellValueFactory(new PropertyValueFactory<>("reg"));
        Columnmonth.setCellValueFactory(new PropertyValueFactory<>("month"));
        Columnyear.setCellValueFactory(new PropertyValueFactory<>("year"));
        
        Columnfine.setCellValueFactory(new PropertyValueFactory<>("fine"));
        Columntotal.setCellValueFactory(new PropertyValueFactory<>("total"));
         Columnlastdate.setCellValueFactory(new PropertyValueFactory<>("lastdate"));
                  Columnpaid.setCellValueFactory(new PropertyValueFactory<>("paid"));
         
   
    
 
 SortedList<rent> sorteddata = new SortedList<>(filtereddata);
 sorteddata.comparatorProperty().bind(pendrent.comparatorProperty());
 //tableUser.setItems(null);
 
 pendrent.setItems(sorteddata);
    }
    
      @FXML
    void goback(ActionEvent event) throws IOException {
Parent s1 = FXMLLoader.load(getClass().getResource("/basic/mess.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }
    
    
}

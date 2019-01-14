/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fee;

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
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import java.io.IOException;
import javafx.scene.input.KeyEvent;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import viewstudents.DbConnection;
import viewstudents.StudentidController;
import viewstudents.ViewstudentsController;
import viewstudents.students;


/**
 * FXML Controller class
 *
 * @author govind
 */
public class ShowpendingbillController implements Initializable {
       @FXML
    private VBox v1;
      @FXML
    private Button back;


       @FXML
    private TextField SearchField,SearchField1;
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
     FilteredList<bill> filtereddata1 = new FilteredList<>(filtereddata, e-> true);

      @FXML
    private CheckBox cname1,creg1,chostel1,croom1,cmonth1,cyear1;
        @FXML
    private CheckBox cname11,creg11,chostel11,croom11,cmonth11,cyear11;
    /**
     * Initializes the controller class.
     */
     @FXML
    void printdata(ActionEvent Event) throws FileNotFoundException, DocumentException, ClassNotFoundException, BadElementException, IOException 
    {
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
  Paragraph para3 = new Paragraph("Electric bill Pending \n\n",f3);
  para3.setAlignment(Paragraph.ALIGN_LEFT);
  doc.add(para3);
  
  PdfPTable table = new PdfPTable(13);
  table.setWidthPercentage(100f);
  PdfPCell cell = null;
 
  table.addCell(getcell1("id",PdfPCell.TITLE));
  table.addCell(getcell1("reg. mo",PdfPCell.ANCHOR));
  table.addCell(getcell1("hostel",PdfPCell.BOX));
  table.addCell(getcell1("room",PdfPCell.BOTTOM));
  table.addCell(getcell1("name",PdfPCell.DIV));
  table.addCell(getcell1("month",PdfPCell.HEADER));
  table.addCell(getcell1("year",PdfPCell.TOP));
  table.addCell(getcell1("total unit",PdfPCell.PTABLE));
  table.addCell(getcell1("unit rate",PdfPCell.PHRASE));
  table.addCell(getcell1("bill",PdfPCell.PARAGRAPH));
  table.addCell(getcell1("fine",PdfPCell.ALIGN_CENTER));
  table.addCell(getcell1("total",PdfPCell.ALIGN_CENTER));
  table.addCell(getcell1("due date",PdfPCell.ALIGN_CENTER));
 
  
  for (int i=0;i<billpending.getItems().size();i++)
  {
      table.addCell(getcell(billpending.getItems().get(i).getId().toString(),PdfPCell.ALIGN_CENTER));
      table.addCell(getcell(billpending.getItems().get(i).getReg().toString(),PdfPCell.ALIGN_CENTER));
      table.addCell(getcell(billpending.getItems().get(i).getHostel(),PdfPCell.ALIGN_CENTER));
      table.addCell(getcell(billpending.getItems().get(i).getRoom(),PdfPCell.ALIGN_CENTER));
      table.addCell(getcell(billpending.getItems().get(i).getName(),PdfPCell.ALIGN_CENTER));
      table.addCell(getcell(billpending.getItems().get(i).getMonth(),PdfPCell.ALIGN_CENTER));
      table.addCell(getcell(billpending.getItems().get(i).getYear(),PdfPCell.ALIGN_CENTER));
      table.addCell(getcell(billpending.getItems().get(i).getTotalunit().toString(),PdfPCell.ALIGN_CENTER));
      table.addCell(getcell(billpending.getItems().get(i).getUnitrate().toString(),PdfPCell.ALIGN_CENTER));
      table.addCell(getcell(billpending.getItems().get(i).getAmount().toString(),PdfPCell.ALIGN_CENTER));
      table.addCell(getcell(billpending.getItems().get(i).getFine().toString(),PdfPCell.ALIGN_CENTER));
      table.addCell(getcell(billpending.getItems().get(i).getTotal().toString(),PdfPCell.ALIGN_CENTER));
      table.addCell(getcell(billpending.getItems().get(i).getDate(),PdfPCell.ALIGN_CENTER));
      
      
   
      
      
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc = new DbConnection();
        billpending.setEditable(true);
        SearchField1.setDisable(true);
           try {
               loadpendingbill();
           } catch (ClassNotFoundException ex) {
               Logger.getLogger(ShowpendingbillController.class.getName()).log(Level.SEVERE, null, ex);
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
    
 


private void loadpendingbill() throws ClassNotFoundException
{
 try{ Connection conn = dc.Connect();

  //String insert1 = "INSERT INTO user_info VALUES('anubhav','anubhav.com','')";
  //Statement stmt = (Statement) conn.createStatement();
   //stmt.executeUpdate(insert1);
  data.clear();
  SearchField.clear();
  ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM `pending bill`");
  int start =1;
  while (rs.next())
  {
      ResultSet rs1 = conn.createStatement().executeQuery("SELECT * FROM students WHERE `reg. no`='"+rs.getString(2)+"'");
      rs1.next();
      String name = rs1.getString(5);
      String hostno = rs1.getString(2);
      String roomno = rs1.getString(3);
              
    
  data.add(new bill(start,rs.getInt(2),name,hostno,roomno,rs.getString(3),rs.getString(4),rs.getFloat(5),rs.getFloat(6),rs.getFloat(7),rs.getFloat(8),rs.getInt(9),rs.getString(10)));
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
 
        filtereddata.setPredicate(bill1 ->{
     
     
     String lowerCaseFilter = newValue.toLowerCase();
     if (String.valueOf(bill1.getName()).toLowerCase().contains(lowerCaseFilter) && cname1.isSelected())
     {   return true;
     }
     else if(String.valueOf(bill1.getYear()).toLowerCase().contains(lowerCaseFilter) && cyear1.isSelected())
     {
     return true;
     }
     else if(String.valueOf(bill1.getMonth()).toLowerCase().contains(lowerCaseFilter) && cmonth1.isSelected())
     {
     return true;
     }
      else if(String.valueOf(bill1.getReg()).toLowerCase().contains(lowerCaseFilter) && creg1.isSelected())
     {
     return true;
     }
      else if(String.valueOf(bill1.getHostel()).toLowerCase().contains(lowerCaseFilter) && chostel1.isSelected())
     {
     return true;
     }
      else if(String.valueOf(bill1.getRoom()).toLowerCase().contains(lowerCaseFilter) && croom1.isSelected())
     {
     return true;
     }
     return false;
 
 });
 
 });
     
      
     Columnname.setCellValueFactory(new PropertyValueFactory<>("name"));
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
    void filtersearch1(KeyEvent event) throws SQLException, ClassNotFoundException {
 
        
    SearchField1.textProperty().addListener((observable,oldValue,newValue)->{
 
        filtereddata1.setPredicate(bill ->{
     
 
     String lowerCaseFilter = newValue.toLowerCase();
     if (String.valueOf(bill.getName()).toLowerCase().contains(lowerCaseFilter) && cname11.isSelected())
     {   return true;
     }
     else if(String.valueOf(bill.getYear()).toLowerCase().contains(lowerCaseFilter) && cyear11.isSelected())
     {
     return true;
     }
     else if(String.valueOf(bill.getMonth()).toLowerCase().contains(lowerCaseFilter) && cmonth11.isSelected())
     {
     return true;
     }
      else if(String.valueOf(bill.getReg()).toLowerCase().contains(lowerCaseFilter) && creg11.isSelected())
     {
     return true;
     }
      else if(String.valueOf(bill.getHostel()).toLowerCase().contains(lowerCaseFilter) && chostel11.isSelected())
     {
     return true;
     }
      else if(String.valueOf(bill.getRoom()).toLowerCase().contains(lowerCaseFilter) && croom11.isSelected())
     {
     return true;
     }
     return false;
 
 });
 
 });
     
      
     Columnname.setCellValueFactory(new PropertyValueFactory<>("name"));
        Columnid.setCellValueFactory(new PropertyValueFactory<>("id"));
        Columnreg.setCellValueFactory(new PropertyValueFactory<>("reg"));
        Columnmonth.setCellValueFactory(new PropertyValueFactory<>("month"));
        Columnyear.setCellValueFactory(new PropertyValueFactory<>("year"));
        Columnamount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        Columnfine.setCellValueFactory(new PropertyValueFactory<>("fine"));
        Columntotal.setCellValueFactory(new PropertyValueFactory<>("total"));
         Columndate.setCellValueFactory(new PropertyValueFactory<>("date"));
         
   
    
 
 SortedList<bill> sorteddata = new SortedList<>(filtereddata1);
 
 
 sorteddata.comparatorProperty().bind(billpending.comparatorProperty());
 //tableUser.setItems(null);
  

 billpending.setItems(sorteddata);
        
       // System.out.println(event.getCode().toString().toLowerCase());
    }
    @FXML
    void selectitem(MouseEvent event) throws ClassNotFoundException {
           TableRow<bill> row = new TableRow<>();

if(event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 3 ) {
    try {
            FXMLLoader root2 = new FXMLLoader();
            root2.setLocation(getClass().getResource("submitfees.fxml"));
            
             Stage stage = new Stage();
            Scene sc= new Scene(root2.load());
            stage.setTitle("Pay electric bill");
            stage.setScene(sc);
            
             SubmitfeesController editor1 = root2.getController();
            editor1.setReg1(billpending.getSelectionModel().getSelectedItem().getReg().toString());
            editor1.submitreg();
                        stage.show();
            // Hide this current window (if this is what you want)
            // ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(ViewstudentsController.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    }
     
  
    @FXML
    private void refreshbill(ActionEvent event) throws SQLException, ClassNotFoundException {
       SearchField.setEditable(false);
       SearchField1.setDisable(false);
    
 }
     @FXML
    private void resetbill(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
      Parent s1 = FXMLLoader.load(getClass().getResource("/fee/showpendingbill.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    
 }
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package search;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import static com.itextpdf.text.Element.ALIGN_CENTER;
import static com.itextpdf.text.Element.ALIGN_LEFT;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;

import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.scenario.effect.ImageData;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javafx.scene.input.KeyEvent;
import javafx.event.ActionEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
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
public class SearchController implements Initializable {
    
       @FXML
    private TableView<students> tableUser;

    @FXML
    private TableColumn<students, Integer> Columnid;

    @FXML
    private TableColumn<students, String> Columnhostel;

    @FXML
    private TableColumn<students, String> Columnroom;

    @FXML
    private TableColumn<students, String> Columnreg;

    @FXML
    private TableColumn<students, String> Columnname;

    @FXML
    private TableColumn<students, String> Columngender;

    @FXML
    private TableColumn<students, String> Columnfather;

    @FXML
    private TableColumn<students, String> Columnmother;

    @FXML
    private TableColumn<students, String> Columnroll;

    @FXML
    private TableColumn<students, String> Columnmobile;

    @FXML
    private TableColumn<students, String> Columnguardian;

    @FXML
    private TableColumn<students, String> Columnaddress;

    @FXML
    private TableColumn<students, String> Columncity;

    @FXML
    private TableColumn<students, String> Columnstate;

    @FXML
    private TableColumn<students, String> Columnemail;
    
    @FXML
    private TableColumn<students, String> Columndob;
    
    @FXML
    private TextField filename;
        @FXML
    private TextField SearchField;
        
         @FXML
         private DbConnection dc;

        
       final ObservableList<students>data = FXCollections.observableArrayList();
       
        FilteredList<students> filtereddata = new FilteredList<>(data, e-> true);
    private ObservableList<Object> data1;
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
    void rent(ActionEvent event) throws IOException {
Parent s1 = FXMLLoader.load(getClass().getResource("/basic/rent.fxml"));
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

    @FXML
    void student(ActionEvent event) throws IOException {
Parent s1 = FXMLLoader.load(getClass().getResource("/basic/student.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }
    
    public void sch()
    {
        
        
    SearchField.textProperty().addListener((observable,oldValue,newValue)->{
 
        filtereddata.setPredicate(students ->{
     
     if(newValue == null || newValue.isEmpty())
     {   return true;
     }
     String lowerCaseFilter = newValue.toLowerCase();
     if (String.valueOf(students.getName()).toLowerCase().contains(lowerCaseFilter))
     {   return true;
     }
     else if(String.valueOf(students.getRoom()).toLowerCase().contains(lowerCaseFilter))
     {
     return true;
     }
     else if(String.valueOf(students.getHostel()).toLowerCase().contains(lowerCaseFilter))
     {
     return true;
     }
      else if(String.valueOf(students.getReg()).toLowerCase().contains(lowerCaseFilter))
     {
     return true;
     }
     return false;
 
 });
 
 });
    
    Columnid.setCellValueFactory(new PropertyValueFactory<>("id"));
        Columnhostel.setCellValueFactory(new PropertyValueFactory<>("hostel"));
        Columnroom.setCellValueFactory(new PropertyValueFactory<>("room"));
        Columnreg.setCellValueFactory(new PropertyValueFactory<>("reg"));
        Columnname.setCellValueFactory(new PropertyValueFactory<>("name"));
        Columnemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        Columngender.setCellValueFactory(new PropertyValueFactory<>("gender"));
         Columnfather.setCellValueFactory(new PropertyValueFactory<>("father"));
          Columnmother.setCellValueFactory(new PropertyValueFactory<>("mother"));
           Columnmobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        Columnguardian.setCellValueFactory(new PropertyValueFactory<>("guardian"));
        Columnaddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        Columncity.setCellValueFactory(new PropertyValueFactory<>("city"));
        Columnstate.setCellValueFactory(new PropertyValueFactory<>("state"));
        Columnroll.setCellValueFactory(new PropertyValueFactory<>("roll"));
        Columndob.setCellValueFactory(new PropertyValueFactory<>("dob"));
    
 
 SortedList<students> sorteddata = new SortedList<>(filtereddata);
 sorteddata.comparatorProperty().bind(tableUser.comparatorProperty());
 //tableUser.setItems(null);
 
 tableUser.setItems(sorteddata);
        
        //System.out.println(event.getCode().toString().toLowerCase());
    }
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
            
  Document doc = new Document(PageSize.A1);
  PdfWriter.getInstance(doc, new FileOutputStream("../../Downloads/"+result.get()+".pdf"));
  doc.open();
  Font f1=new Font(FontFamily.TIMES_ROMAN,34.0f,Font.BOLD,BaseColor.BLACK);
  Font f2=new Font(FontFamily.TIMES_ROMAN,30.0f,Font.NORMAL,BaseColor.BLUE);
   Font f3=new Font(FontFamily.TIMES_ROMAN,25.0f,Font.UNDERLINE,BaseColor.BLUE);
   String path = "src/image/log.png";

  Paragraph para1 = new Paragraph("INDIAN INSTITUTE OF INFORMATION TECHNOLOGY KALYANI",f1);
  para1.setAlignment(Paragraph.ALIGN_CENTER);
  doc.add(para1);
    Image img = Image.getInstance(path);
  img.setAlignment(ALIGN_LEFT);
  img.setAbsolutePosition(273,2120);
  img.scaleAbsolute(150,150);
  img.scaleToFit(150,150);
  doc.add(img);
  
  Paragraph para2 = new Paragraph("(Autonomous institution under MHRD , Govt. Of India & Department of\n Information"
          + "Technology & Electronics, Govt. of West Bengal)\n Webel IT Park,Kalyani-741235,West Bengal\n\n\n",f2);
  
  para2.setAlignment(Paragraph.ALIGN_CENTER);
  para2.setPaddingTop(10);
  doc.add(para2);
  Paragraph para3 = new Paragraph("STUDENT LIST \n\n",f3);
  para3.setAlignment(Paragraph.ALIGN_LEFT);
  doc.add(para3);
  
  PdfPTable table = new PdfPTable(16);
  table.setWidthPercentage(100f);
  PdfPCell cell = null;
 
  table.addCell(getcell1("id",PdfPCell.TITLE));
  table.addCell(getcell1("hostel",PdfPCell.ANCHOR));
  table.addCell(getcell1("room",PdfPCell.BOX));
  table.addCell(getcell1("reg. no",PdfPCell.BOTTOM));
  table.addCell(getcell1("name",PdfPCell.DIV));
  table.addCell(getcell1("gender",PdfPCell.HEADER));
  table.addCell(getcell1("father name",PdfPCell.TOP));
  table.addCell(getcell1("mother name",PdfPCell.PTABLE));
  table.addCell(getcell1("roll",PdfPCell.PHRASE));
  table.addCell(getcell1("dob",PdfPCell.PARAGRAPH));
  table.addCell(getcell1("mobile no",PdfPCell.ALIGN_CENTER));
  table.addCell(getcell1("guradian no",PdfPCell.ALIGN_CENTER));
  table.addCell(getcell1("addrecess",PdfPCell.ALIGN_CENTER));
  table.addCell(getcell1("city",PdfPCell.ALIGN_CENTER));
  table.addCell(getcell1("state",PdfPCell.ALIGN_CENTER));
  table.addCell(getcell1("email",PdfPCell.ALIGN_CENTER));
  
  for (int i=0;i<tableUser.getItems().size();i++)
  {
      table.addCell(getcell(tableUser.getItems().get(i).getId().toString(),PdfPCell.ALIGN_CENTER));
      table.addCell(getcell(tableUser.getItems().get(i).getHostel(),PdfPCell.ALIGN_CENTER));
      table.addCell(getcell(tableUser.getItems().get(i).getRoom(),PdfPCell.ALIGN_CENTER));
      table.addCell(getcell(tableUser.getItems().get(i).getReg().toString(),PdfPCell.ALIGN_CENTER));
      table.addCell(getcell(tableUser.getItems().get(i).getName(),PdfPCell.ALIGN_CENTER));
      table.addCell(getcell(tableUser.getItems().get(i).getGender(),PdfPCell.ALIGN_CENTER));
      table.addCell(getcell(tableUser.getItems().get(i).getFather(),PdfPCell.ALIGN_CENTER));
      table.addCell(getcell(tableUser.getItems().get(i).getMother(),PdfPCell.ALIGN_CENTER));
      table.addCell(getcell(tableUser.getItems().get(i).getRoll(),PdfPCell.ALIGN_CENTER));
      table.addCell(getcell(tableUser.getItems().get(i).getDob(),PdfPCell.ALIGN_CENTER));
      table.addCell(getcell(tableUser.getItems().get(i).getMobile(),PdfPCell.ALIGN_CENTER));
      table.addCell(getcell(tableUser.getItems().get(i).getGuardian(),PdfPCell.ALIGN_CENTER));
      table.addCell(getcell(tableUser.getItems().get(i).getAddress(),PdfPCell.ALIGN_CENTER));
      table.addCell(getcell(tableUser.getItems().get(i).getCity(),PdfPCell.ALIGN_CENTER));
      table.addCell(getcell(tableUser.getItems().get(i).getState(),PdfPCell.ALIGN_CENTER));
      table.addCell(getcell(tableUser.getItems().get(i).getEmail(),PdfPCell.ALIGN_CENTER));
      System.out.print(tableUser.getItems().get(i).getReg());
      
      
      
      
  }

  doc.add(table);
  String dat = LocalDateTime.now().toString();
  
  Font f4=new Font(FontFamily.TIMES_ROMAN,10.0f,Font.NORMAL,BaseColor.BLACK);
    Paragraph para4 = new Paragraph("\nissued by govind singh at "+dat,f4);
  para4.setAlignment(Paragraph.ALIGN_BOTTOM);
  para4.setAlignment(Paragraph.ALIGN_RIGHT);
  doc.add(para4);
  doc.close(); 
    }
}
    }
      private PdfPCell getcell(String text, int alignment) {
    PdfPCell  cell = new PdfPCell(new Phrase(text));
    cell.setPadding(5);
cell.setHorizontalAlignment(30);
cell.setVerticalAlignment(20);
cell.setBorder(PdfPCell.RECTANGLE);
cell.setBorderColor(BaseColor.BLUE);
//To change body of generated methods, choose Tools | Templates.
        return cell;
    }
        private PdfPCell getcell1(String text, int alignment) {
            Font f1=new Font(FontFamily.TIMES_ROMAN,15.0f,Font.BOLD,BaseColor.BLACK);
    PdfPCell  cell = new PdfPCell(new Phrase(text,f1));
    cell.setPadding(5);
cell.setHorizontalAlignment(30);
cell.setVerticalAlignment(20);
cell.setBorder(PdfPCell.RECTANGLE);
cell.setBorderColor(BaseColor.RED);
//To change body of generated methods, choose Tools | Templates.
        return cell;
    }
    
     @FXML
    void selectitem(MouseEvent event) {
            TableRow<students> row = new TableRow<>();

if(event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 3 ) {
    try {
            FXMLLoader root2 = new FXMLLoader();
            root2.setLocation(getClass().getResource("/viewstudents/studentid.fxml"));
            
             Stage stage = new Stage();
            Scene sc= new Scene(root2.load(),600,600);
            stage.setTitle("Student info");
            stage.setScene(sc);
            //root.getNamespace().put("reg", tableUser.getSelectionModel().getSelectedItem().getReg());
            //parent root = FXMLLoader.load(getClass().getClassLoader().getResource("/search/search.fxml"));
            String dateofb = tableUser.getSelectionModel().getSelectedItem().getDob();
             DateFormat dt = new SimpleDateFormat("yyyy");
        Calendar cd = Calendar.getInstance();
        String h = dt.format(cd.getTime());
        String yr = dateofb.substring(dateofb.length()-4,dateofb.length());
        int yr1 = Integer.parseInt(yr);
        int h1 = Integer.parseInt(h);
        int ag = h1-yr1;
        
             StudentidController editor1 = root2.getController();
            editor1.setReg1(tableUser.getSelectionModel().getSelectedItem().getReg().toString()); 
             editor1.setEmail1(tableUser.getSelectionModel().getSelectedItem().getEmail()); 
              editor1.setAge1(ag); 
               editor1.setMobile1(tableUser.getSelectionModel().getSelectedItem().getMobile()); 
                editor1.setName1(tableUser.getSelectionModel().getSelectedItem().getName()); 
                 editor1.setState1(tableUser.getSelectionModel().getSelectedItem().getState()); 
                  editor1.setFather1(tableUser.getSelectionModel().getSelectedItem().getFather()); 
                  
            stage.show();
            // Hide this current window (if this is what you want)
            // ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(ViewstudentsController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
      
   }
    }

     @FXML
    void searchlist(KeyEvent event) throws SQLException, ClassNotFoundException {
    
  
        
    SearchField.textProperty().addListener((observable,oldValue,newValue)->{
 
        filtereddata.setPredicate(students ->{
     
     if(newValue == null || newValue.isEmpty())
     {   return true;
     }
     String lowerCaseFilter = newValue.toLowerCase();
     if (String.valueOf(students.getName()).toLowerCase().contains(lowerCaseFilter))
     {   return true;
     }
     else if(String.valueOf(students.getRoom()).toLowerCase().contains(lowerCaseFilter))
     {
     return true;
     }
     else if(String.valueOf(students.getHostel()).toLowerCase().contains(lowerCaseFilter))
     {
     return true;
     }
      else if(String.valueOf(students.getReg()).toLowerCase().contains(lowerCaseFilter))
     {
     return true;
     }
     return false;
 
 });
 
 });
    
    Columnid.setCellValueFactory(new PropertyValueFactory<>("id"));
        Columnhostel.setCellValueFactory(new PropertyValueFactory<>("hostel"));
        Columnroom.setCellValueFactory(new PropertyValueFactory<>("room"));
        Columnreg.setCellValueFactory(new PropertyValueFactory<>("reg"));
        Columnname.setCellValueFactory(new PropertyValueFactory<>("name"));
        Columnemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        Columngender.setCellValueFactory(new PropertyValueFactory<>("gender"));
         Columnfather.setCellValueFactory(new PropertyValueFactory<>("father"));
          Columnmother.setCellValueFactory(new PropertyValueFactory<>("mother"));
           Columnmobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        Columnguardian.setCellValueFactory(new PropertyValueFactory<>("guardian"));
        Columnaddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        Columncity.setCellValueFactory(new PropertyValueFactory<>("city"));
        Columnstate.setCellValueFactory(new PropertyValueFactory<>("state"));
        Columnroll.setCellValueFactory(new PropertyValueFactory<>("roll"));
        Columndob.setCellValueFactory(new PropertyValueFactory<>("dob"));
    
 
 SortedList<students> sorteddata = new SortedList<>(filtereddata);
 sorteddata.comparatorProperty().bind(tableUser.comparatorProperty());
 //tableUser.setItems(null);
 
 tableUser.setItems(sorteddata);
        
        System.out.println(event.getCode().toString().toLowerCase());
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc = new viewstudents.DbConnection();
        tableUser.setEditable (true);
      sch();
           
        
            Connection conn = null;
           try {
               conn = dc.Connect();
           } catch (ClassNotFoundException ex) {
               Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
           } catch (SQLException ex) {
               Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
           }
        
  ResultSet rs = null;
           try {
               rs = conn.createStatement().executeQuery("SELECT * FROM students");
           } catch (SQLException ex) {
               Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
           }
           try { int start =1;
               while (rs.next())
               {
                   data.add(new students(start,rs.getString(2),rs.getString(3),Integer.parseInt(rs.getString(4)),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15),rs.getString(16)));
               start+=1;
               }} catch (SQLException ex) {
               Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
           }
              
    }    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 
package viewstudents;

import com.itextpdf.text.Document;
import addroom.Building;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import delete.check1;
import viewstudents.StudentidController;
import editstudent.EditdetailsController;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author govind
 */
public class ViewstudentsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label label;
    
       

   @FXML
   private TextField searchField;


     @FXML
    private TableView<students> tableUser;

    @FXML
    private TableColumn<students, Integer> Columnid;

    @FXML
    private TableColumn<students, String> Columnhostel;

    @FXML
    private TableColumn<students, String> Columnroom;

    @FXML
    private TableColumn<students, Integer> Columnreg;

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
    private Button btnload;
    
   // private ObservableList<students>data;
    final ObservableList<students>data = FXCollections.observableArrayList();
       
        FilteredList<students> filtereddata = new FilteredList<>(data, e-> true);
    private DbConnection dc;

    
    
    
    
    /* @FXML
    public void changescene(ActionEvent event) throws IOException {
        Parent s1 = FXMLLoader.load(getClass().getResource("addnew.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }
   */
@FXML
    void search(KeyEvent event) throws SQLException, ClassNotFoundException {
    
  
        
    searchField.textProperty().addListener((observable,oldValue,newValue)->{
 
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
     *
     * @param edittedCell
     */
     @FXML
    void newwindow(ActionEvent event) {
         ObservableList<students> selectedRows1, allRoom1;
       allRoom1= tableUser.getItems();
       selectedRows1= tableUser.getSelectionModel().getSelectedItems();
selectedRows1.forEach((students student) -> {
           
           int reg1 = student.getReg();
        try {
            FXMLLoader root1 = new FXMLLoader();
            root1.setLocation(getClass().getResource("/editstudent/editdetails.fxml"));
            
            //parent root = FXMLLoader.load(getClass().getClassLoader().getResource("/search/search.fxml"));
            Stage stage = new Stage();
            Scene sc= new Scene(root1.load(),1100,700);
            stage.setTitle("My New Stage Title");
            stage.setScene(sc);
            
               EditdetailsController editor = root1.getController();
            editor.setReg1(tableUser.getSelectionModel().getSelectedItem().getReg().toString());
            stage.show();
            // Hide this current window (if this is what you want)
            // ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(ViewstudentsController.class.getName()).log(Level.SEVERE, null, ex);
        }
});
    }
     @FXML
    void studentspage(ActionEvent event) throws IOException {
Parent s1 = FXMLLoader.load(getClass().getResource("/basic/student.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
    }
    @FXML
    public void changemobilno(TableColumn.CellEditEvent edittedCell){
        students student1= tableUser.getSelectionModel().getSelectedItem();
        student1.setMobile(edittedCell.getNewValue().toString());
        
        
    }
      @FXML
    void deleterow(ActionEvent event) {
 ObservableList<students> selectedRows, allRoom;
       allRoom= tableUser.getItems();
       selectedRows= tableUser.getSelectionModel().getSelectedItems();
       
       selectedRows.forEach((student) -> {
           allRoom.remove(student);
           String reg1 = student.getReg().toString();
           String ho1 = student.getHostel();
           String ro1 = student.getRoom();
           int elec=0;
           int mes=0;
           int ren=0;
              Alert alert;
         alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setTitle("Confirmation ");
         alert.setHeaderText("OK");
         alert.setContentText("are you sure you want to delete this student from database?");
         Optional<ButtonType> result = alert.showAndWait();
         if (result.get() == ButtonType.OK)
         {
           try
           {
           
               Connection conn = dc.Connect();
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM `pending bill` WHERE reg = '"+reg1+"'");
        if (rs.next())
        {
            elec=1;
        }
         ResultSet rs4 = conn.createStatement().executeQuery("SELECT * FROM `pendmess` WHERE reg = '"+reg1+"'");
        if (rs4.next())
        {
            mes=1;
        }
          ResultSet rs2 = conn.createStatement().executeQuery("SELECT * FROM `pendrent` WHERE reg = '"+reg1+"'");
        if (rs2.next())
        {
            ren=1;
        }
        if (elec+mes+ren>0)
               {
                   
                   String de="";
                   if (elec>0) {de+="1. electric bill ";}
                   if (mes>0) {de+="2. mess bill ";}
                   if (ren>0) {de+="3. room rent";}
           Alert alert1;
         alert1 = new Alert(Alert.AlertType.WARNING);
         alert1.setTitle("ERROR");
         alert1.setHeaderText("first pay the all following fee");
         alert1.setContentText(de);
          alert1.showAndWait();
               }
            else
               {
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
           
           
           } catch (SQLException ex) {
         Logger.getLogger(ViewstudentsController.class.getName()).log(Level.SEVERE, null, ex);
     } catch (ClassNotFoundException ex) {
         Logger.getLogger(ViewstudentsController.class.getName()).log(Level.SEVERE, null, ex);
     } catch (IOException ex) {
         Logger.getLogger(ViewstudentsController.class.getName()).log(Level.SEVERE, null, ex);
     }
         }
        });
       
    }

 
    
    @FXML
    void selectitem(MouseEvent event) {
            TableRow<students> row = new TableRow<>();

if(event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 3 ) {
    try {
            FXMLLoader root2 = new FXMLLoader();
            root2.setLocation(getClass().getResource("studentid.fxml"));
            
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
    
    
    
    public void loadDatabase() throws ClassNotFoundException, SQLException {
        try{
  Connection conn = dc.Connect();
  //data = FXCollections.observableArrayList(); 
  //String insert1 = "INSERT INTO user_info VALUES('anubhav','anubhav.com','')";
  //Statement stmt = (Statement) conn.createStatement();
   //stmt.executeUpdate(insert1);
  
  ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM students");

  while (rs.next())
  {
  data.add(new students(rs.getInt(1),rs.getString(2),rs.getString(3),Integer.parseInt(rs.getString(4)),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15),rs.getString(16)));
          }
        }
    
      
        catch (SQLException ex)
                { 
                
               System.err.println("ERRor "+ex);

                }
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
        //Columnname.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        
        //tableUser.getItems().clear();
        tableUser.setItems(data);
    }
    
    
  
  
    

@FXML
void refreshDatabase(ActionEvent Event) throws IOException {

        Parent s1 = FXMLLoader.load(getClass().getResource("/viewstudents/viewstudents.fxml"));
        Scene s1scene = new Scene(s1);
        
        Stage window = (Stage) ((Node)Event.getSource()).getScene().getWindow();
        
        window.setScene(s1scene);
        window.show();
}

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc = new DbConnection();
        tableUser.setEditable(true);
        try {
            loadDatabase();
            //ColumnName.setCellFactory(TextFieldTableCell.forTableColumn());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViewstudentsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ViewstudentsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    

    private PdfPCell getcell(String text, int alignment) {
    PdfPCell  cell = new PdfPCell(new Phrase(text));
    cell.setPadding(0);
cell.setHorizontalAlignment(alignment);
cell.setBorder(PdfPCell.NO_BORDER);//To change body of generated methods, choose Tools | Templates.
        return cell;
    }
   

    
}

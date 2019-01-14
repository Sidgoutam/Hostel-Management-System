/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addroom;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import addroom.Building;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Sidharth
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TableView<Building>tableview;
    @FXML
    private TableColumn<Building,String>bno;
    @FXML
    private TableColumn<Building,String>rno;
    @FXML
    private TextField Roo;
    @FXML
    private JFXButton btn1;
    
    @FXML
    public void changeBuilding(CellEditEvent edittedCell){
        Building buildingSelected= tableview.getSelectionModel().getSelectedItem();
        buildingSelected.setBuilding_No(edittedCell.getNewValue().toString());
        
        
    }
    @FXML
    public void changeRoom(CellEditEvent edditedCell){
        Building buildingSelected= tableview.getSelectionModel().getSelectedItem();
        buildingSelected.setRoom_No(edittedCell.getNewValue().toString());
        
        
    }
    
   @FXML
    private JFXComboBox<String> cboo;
   
   
    
    ObservableList<String> BuildingList = FXCollections.observableArrayList("hostel1","hostel2","hostel3");
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       cboo.setValue("hostel1");
        cboo.setItems(BuildingList);
        
        bno.setCellValueFactory(new PropertyValueFactory<>("Building_No"));
        rno.setCellValueFactory(new PropertyValueFactory<>("Room_No"));
        tableview.setItems(getPeople());
        tableview.setEditable(true);
        bno.setCellFactory(TextFieldTableCell.forTableColumn());
        rno.setCellFactory(TextFieldTableCell.forTableColumn());
        tableview.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // TODO
    }    
    public ObservableList<Building> getPeople(){
        ObservableList<Building> people = FXCollections.observableArrayList();
        people.add(new Building("hostel1","G-Front"));
        people.add(new Building("hostel2","G-Back"));
        people.add(new Building("hostel3","G-Mid"));
        return people;
    }
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
    
    @FXML
   public void Add()
    {
        Building newEntry = new Building(cboo.getValue(),Roo.getText());
        tableview.getItems().add(newEntry);
        
        
    }
    @FXML
   public void Delete()
   {
       ObservableList<Building> selectedRows, allRoom;
       allRoom= tableview.getItems();
       selectedRows= tableview.getSelectionModel().getSelectedItems();
       
       selectedRows.forEach((building) -> {
           allRoom.remove(building);
        });
           
       
   }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Sidharth
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Pane p1,p2,p3;
    
    @FXML
    public void back(ActionEvent event) throws IOException {
        Parent s2 = FXMLLoader.load(getClass().getResource("/homepage/homepage.fxml"));
        Scene s2scene = new Scene(s2);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(s2scene);
        window.show();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        LoadData();LoadData1();LoadData2();
    }    
    private void LoadData(){
        p1.getChildren().clear();
        ObservableList<PieChart.Data> List= FXCollections.observableArrayList();
        List.add(new PieChart.Data("Filled",67));
        List.add(new PieChart.Data("Avail",33));
        PieChart Graph= new PieChart(List);
        Graph.setTitle("Building 1");
        Graph.setLegendSide(Side.RIGHT);
        p1.getChildren().add(Graph);
        
    }
     private void LoadData1(){
        p2.getChildren().clear();
        ObservableList<PieChart.Data> List1= FXCollections.observableArrayList();
        List1.add(new PieChart.Data("Filled",50));
        List1.add(new PieChart.Data("Avail",50));
        PieChart Graph1= new PieChart(List1);
        Graph1.setTitle("Building 2");
        Graph1.setLegendSide(Side.RIGHT);
        p2.getChildren().add(Graph1);
        
    }
      private void LoadData2(){
        p3.getChildren().clear();
        ObservableList<PieChart.Data> List2= FXCollections.observableArrayList();
        List2.add(new PieChart.Data("Filled",80));
        List2.add(new PieChart.Data("Avail",20));
        PieChart Graph2= new PieChart(List2);
        Graph2.setTitle("Building 3");
        Graph2.setLegendSide(Side.RIGHT);
        p3.getChildren().add(Graph2);
        
    }
    
    
    
  
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Sidharth
 */
public class JavaFXApplication18 extends Application {
    /*private final ObservableList<PieChart.Data> details = FXCollections.observableArrayList();
    private AnchorPane pane;
    private PieChart graph;*/
    
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Dashboard1.fxml"));

        /*stage.setTitle("Room Availibility");
        details.addAll(new PieChart.Data("Seats Left", 75),
        new PieChart.Data("Avail Seats", 25));
        
        pane = new AnchorPane();
        Scene scene = new Scene(pane,600,500);
        
        graph= new PieChart();
        graph.setData(details);
        graph.setTitle("Building");
        graph.setLegendSide(Side.RIGHT);
        graph.setLabelsVisible(true);
        pane.setCenter(graph);*/
        
        
        
        
        
        
        
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

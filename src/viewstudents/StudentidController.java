/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewstudents;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author govind
 */
public class StudentidController implements Initializable {

    @FXML
    private JFXTextField mobile;
    @FXML
    private JFXTextField state;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField father;
    @FXML
    private JFXTextField reg;
    @FXML
    private JFXTextField age;
    @FXML
    private JFXTextField name;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
      
    public void setReg1(String rn1)
    {
    this.reg.setText(rn1);
    }
      
    public void setAge1(int rn1)
    {
        
        String ar = Integer.toString(rn1);
    this.age.setText(ar);
    }
      
    public void setEmail1(String rn1)
    {
    this.email.setText(rn1);
    }
      
    public void setFather1(String rn1)
    {
    this.father.setText(rn1);
    }
      
    public void setState1(String rn1)
    {
    this.state.setText(rn1);
    }
      
    public void setName1(String rn1)
    {
    this.name.setText(rn1);
    }
      
    public void setMobile1(String rn1)
    {
    this.mobile.setText(rn1);
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXML;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author mahas
 */
public class SidePanelFrontController implements Initializable {

    @FXML
    private JFXButton b10;
    @FXML
    private JFXButton b11;
    @FXML
    private JFXButton b5;
    @FXML
    private JFXButton exit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void changeView(ActionEvent event) {
    }

    @FXML
    private void exit(ActionEvent event) {
    }
    
}

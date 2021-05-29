/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Abonne;
import Entities.Avisclient;
import Service.ServiceAbonne;
import Service.ServiceRating;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import oracle.jrockit.jfr.events.Bits;


import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author mahas
 */
public class RatingController implements  Initializable {

      @FXML
    private Rating rat;

    @FXML
    private Label rating;

    @FXML
    private TextArea descR;

    @FXML
    private ComboBox<String> comboBoxCat;
     private Connection con;
        private Statement ste;
    ServiceAbonne serCat = new ServiceAbonne() ;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          try {
              ArrayList<Abonne> arrayList =  (ArrayList) serCat.AfficherAbonne();
              for (int i=0;i<arrayList.size();i++){
                  comboBoxCat.getItems().addAll(arrayList.get(i).getMailabonne());
              }
              rat.ratingProperty().addListener(new ChangeListener<Number>(){
                  
                  public void changed1(ObservableValue<? extends Number> observable, Number oldValue, Number newr) {
                      rating.setText(""+newr);
                  }
                  
                  @Override
                  public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                      rating.setText(""+newValue); }
                  
              });   } catch (SQLException ex) {
              Logger.getLogger(RatingController.class.getName()).log(Level.SEVERE, null, ex);
          }
    } 
    
    
    @FXML
    private void ajouterRating(ActionEvent event) throws SQLException, ParseException {
     
        String mail = comboBoxCat.getValue().toString() ;
                int abid = serCat.getIdAbonne(mail);
     String desc = descR.getText();
            
            String rat = rating.getText();
            double  rating1= Bits.doubleValue(rat);
           
         
         
              
            
           ServiceRating se = new ServiceRating();
         
           Avisclient e = new Avisclient( rating1, abid,desc);
            se.ajouter(e); 
            descR.setText("");

         
            
           
       
    } 

   

   
 }

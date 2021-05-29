/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Film;
import Service.ServiceFilm;
import Entities.Cinema;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Service.ServiceCinema;

/**
 * FXML Controller class
 *
 * @author mahas
 */
public class AjoutCinemaController implements Initializable {
  private Connection con;
        private Statement ste;
    ServiceFilm serCat = new ServiceFilm() ;
    
    @FXML
    private TextField nbr;
    @FXML
    private TextField idfilm;
     @FXML
    private ComboBox<String> comboBoxCat;
      @FXML
    private DatePicker dates;
    @FXML
    private Button bValider;
    @FXML
    private Button bAnnuler;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      ArrayList<Film> arrayList =  (ArrayList) serCat.afficher();
          for (int i=0;i<arrayList.size();i++){
               comboBoxCat.getItems().addAll(arrayList.get(i).getNomfilm());
          }
    }    
      @FXML
    private void ajouterEmploye1(ActionEvent event) {
        
         try {
             //int reference = 1;
            String nom1 = nbr.getText();
            int nom= Integer.parseInt(nom1);
            //String prenom = idfilm.getText();
            //int prenom1= Integer.parseInt(prenom);
           
         
         
              String fil = comboBoxCat.getValue().toString() ;
                int film_id = serCat.getIdfi(fil);
            LocalDate d1 = dates.getValue();
                Date date = java.sql.Date.valueOf(d1);
           ServiceCinema se = new ServiceCinema();
           Time h1 = new Time(12,58,10); 
           Cinema e = new Cinema( nom, film_id,date,h1);
            se.ajouterCinema(e); 
            nbr.setText("");
            idfilm.setText("");
          
    FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("../GUI/ListeCinema.fxml"));
            try {
                Parent root = loader.load();
               ListeFilmController apc = loader.getController();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage()
            );
        }
    }
    @FXML
    private void Annuler(ActionEvent event) throws IOException {
        
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("./GUI/ListeEmploye.fxml"));
       
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("ListeCinema.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
    }
    

}

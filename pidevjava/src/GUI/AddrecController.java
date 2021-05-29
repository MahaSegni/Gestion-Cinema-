/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

//import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import Entities.Reclamation;
import Entities.Abonne;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import Utils.MaConnexion;

import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import Service.ServiceReclamation;
import javafx.util.Duration;
import Entities.Equipement;
import org.controlsfx.control.Rating;
import Service.ServiceEquipement;
import Service.ServiceAbonne;
import javafx.scene.Node;
//import tray.notification.NotificationType;
//import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author selmiolfa
 */
public class AddrecController implements Initializable {

    @FXML
    public ObservableList<Reclamation> data = FXCollections.observableArrayList();
    @FXML
    private TextArea descar;
    @FXML
    private ComboBox abonne;
    @FXML
    private ComboBox field;
    @FXML
    private Button Ajouter;
    @FXML
    private TextField idselectsvg;
    @FXML
    private Rating rate;
       @FXML
    private Button chat;
      @FXML
    private DatePicker date;
    private Connection cnx = MaConnexion.getInstance().getConnection();
    @FXML
   ServiceReclamation sa = new ServiceReclamation();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
//        try {
            field.getItems().addAll(
                    "Cinema",
                    "Parking",
                    "restauration",
                    "Autre"
            );
//            ServiceAbonne sa = new ServiceAbonne();
//            ArrayList<Abonne> arrayList =  (ArrayList) sa.AfficherAbonne();
//            for (int i=0;i<arrayList.size();i++){
//                abonne.getItems().addAll(arrayList.get(i).getMailabonne());
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(AddrecController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
           
           
      
           
  }
   
  
     @FXML
    private void Ajouter(MouseEvent event) throws SQLException {
        
        if (event.getSource() == Ajouter) {
           
           
            Reclamation v = new Reclamation(); 
          
            v.setDescription(descar.getText());
            v.setRate(rate.getRating());
            Timestamp time= new Timestamp(System.currentTimeMillis());
          
        v.setDatee(time);
        System.out.println(time); 
        v.setField(field.getSelectionModel().getSelectedItem().toString());
         new   ServiceReclamation().ajouter(v);
        
          
             
        JOptionPane.showMessageDialog(null, "Reclamation Ajoutée");
            descar.setText("");
            field.setPromptText("");
            rate.setRating(0);
           
           
            
        }
    }
       
  @FXML
        private void chat(javafx.event.ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == chat) {
            String[] args = null;
            chatbot.main(args);
          
        }

    }
                @FXML
    private void menu(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("AcceuilAbonne.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();

    }
    
}

 
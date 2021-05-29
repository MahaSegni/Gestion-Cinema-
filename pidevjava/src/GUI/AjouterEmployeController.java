/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Employe;
import Service.ServiceEmploye;
import Utils.MaConnexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.Mail;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AjouterEmployeController implements Initializable {

    private Connection con;
    private Statement ste;

    @FXML
    private TextField idE ;
    @FXML
    private TextField nomE;
    @FXML
    private TextField prenomE;
    @FXML
    private TextField numtelE;
    @FXML
    private TextField adresseE;
    @FXML
    private Button bValider;
    @FXML
    private Button bAnnuler;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        // try { 
        //con = MaConnexion.getInstance().getConnection();
        // ste=con.createStatement();
        //ResultSet rs=ste.executeQuery("select nom_cat from categorie_produit");
        //while (rs.next())
        //{list.add(rs.getString("nom_cat"));}
        // categorie.setItems(list);
        //} catch(SQLException e) {}
       // idE.addEventFilter(KeyEvent.KEY_TYPED , numeric_Validation(3));
        numtelE.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(8));
        nomE.addEventFilter(KeyEvent.KEY_TYPED, letter_Validation(15));
        prenomE.addEventFilter(KeyEvent.KEY_TYPED, letter_Validation(15));

    }

    @FXML
    private void ajouterEmploye(ActionEvent event) {

        try {
            
           
            //int reference = Integer.parseInt(idE.getText());
            //int reference = 100;
            String nom = nomE.getText();
            String prenom = prenomE.getText();

            int numtel = Integer.parseInt(numtelE.getText());

            String adresse = adresseE.getText() ;
            
            

            //ResultSet rs=ste.executeQuery("SELECT `id` FROM `categorie_produit` WHERE `nom_cat`='"+cat+"'");
            //if(rs.next()){
            ServiceEmploye se = new ServiceEmploye();
            Employe e = new Employe(nom, prenom, numtel, adresse);
            //if(String adresse == UUID.randomUUID().toString()){
            se.ajouter(e);
        
            

            TrayNotification tray = new TrayNotification();
            tray.setTitle("Succés");
            tray.setMessage("employe ajouté et mail de confirmation envoyé avec succées !");
            tray.setAnimationType(AnimationType.POPUP);
            tray.setNotificationType(NotificationType.INFORMATION);
            tray.showAndWait();
            Mail.Mail("maha.segni@esprit.tn", nomE.getText() + " " + prenomE.getText() + " ajouté ", "vous avez ajouté un nouveau employe");

            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("ListeEmploye.fxml"));
            try {
                Parent root = loader.load();
                ListeEmployeController apc = loader.getController();

                nomE.getScene().setRoot(root);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage()
            );
            TrayNotification tray = new TrayNotification();
            tray.setTitle("Try Again");
            tray.setMessage("Employe existe!");
            tray.setAnimationType(AnimationType.POPUP);
            tray.setNotificationType(NotificationType.INFORMATION);
            tray.showAndWait();
        }

    }

    @FXML
    private void Annuler(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeEmploye.fxml"));
        Parent root = loader.load();
        nomE.getScene().setRoot(root);
    }

    public EventHandler<KeyEvent> numeric_Validation(final Integer max_Lengh) {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                TextField txt_TextField = (TextField) e.getSource();
                if (txt_TextField.getText().length() >= max_Lengh) {
                    e.consume();
                }
                /*  if (txt_TextField.getText()) < 0) {                    
                e.consume();
            }*/
                if (e.getCharacter().matches("[0-9.]")) {
                    if (txt_TextField.getText().contains(".") && e.getCharacter().matches("[.]")) {
                        e.consume();
                    } else if (txt_TextField.getText().length() == 0 && e.getCharacter().matches("[.]")) {
                        e.consume();
                    }
                } else {
                    e.consume();
                }
            }
        };
    }

    public EventHandler<KeyEvent> letter_Validation(final Integer max_Lengh) {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                TextField txt_TextField = (TextField) e.getSource();
                if (txt_TextField.getText().length() >= max_Lengh) {
                    e.consume();
                }
                if (e.getCharacter().matches("[A-Za-z]")) {
                } else {
                    e.consume();
                }
            }
        };
    }

}

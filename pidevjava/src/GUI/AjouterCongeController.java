/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Employe;
import Entities.Conge;
import Service.ServiceEmploye;
import Service.ServiceConge;
import Utils.MaConnexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.control.DatePicker;
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
public class AjouterCongeController implements Initializable {

    private Connection con;
    private Statement ste;

    @FXML
    private DatePicker dateC;
    @FXML
    private TextField motifC;
    @FXML
    private TextField nbjourC;
    @FXML
    private ComboBox<Integer> idCC;

    @FXML
    private Button bValider;
    @FXML
    private Button bAnnuler;
    ServiceConge sc = new ServiceConge();
    ServiceEmploye sc1 = new ServiceEmploye();

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
       nbjourC.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(2));
        //motifC.addEventFilter(KeyEvent.KEY_TYPED, letter_Validation(15));
        //prenomE.addEventFilter(KeyEvent.KEY_TYPED, letter_Validation(15));

        ArrayList<Employe> arrayList = (ArrayList) sc1.afficher();
        for (int i = 0; i < arrayList.size(); i++) {
            idCC.getItems().addAll(arrayList.get(i).getIdemp());

        }

//ObservableList<Conge> conges=FXCollections.observableArrayList();
//        ServiceConge cs=new ServiceConge();
//        for(Conge c : cs.getAllConges())
//            conges.add(c);
//        listecoach.setItems(coachObs);
    }

    @FXML
    private void ajouterConge(ActionEvent event) {

        try {

            //int reference = Integer.parseInt(idE.getText());
            //int reference = 100;
//            LocalDate d1 = dateC.getValue();
//             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            String date = java.sql.Date.valueOf(d1).toString();
//            

            LocalDate d1 = dateC.getValue();
            Date date = java.sql.Date.valueOf(d1);

            String motif = motifC.getText();

            int nbjour = Integer.parseInt(nbjourC.getText());

//            Conge c1 = new Conge();
            // c.setUserId(user.getSelectionModel().getSelectedItem().getId());
//            
//              c1.setEmploye_id(idCC.getSelectionModel().getSelectedItem().getIdemp());
//            int idemp = c1.getEmploye_id();

            int idemp1 = idCC.getValue();

        
            // String adresse = adresseE.getText() ;
            //ResultSet rs=ste.executeQuery("SELECT `id` FROM `categorie_produit` WHERE `nom_cat`='"+cat+"'");
            //if(rs.next()){
            Conge c = new Conge(date, motif, nbjour, idemp1);
            //if(String adresse == UUID.randomUUID().toString()){
            sc.ajouter(c);

            TrayNotification tray = new TrayNotification();
            tray.setTitle("Succés");
            tray.setMessage("conge ajouté avec succées !");
            tray.setAnimationType(AnimationType.POPUP);
            tray.setNotificationType(NotificationType.INFORMATION);
            tray.showAndWait();
            // Mail.Mail("djebbi.hanine20@gmail.com", .getText() + " " + prenomE.getText() + " ajouté ", "cc");

            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("ListeConge.fxml"));
            try {
                Parent root = loader.load();
                ListeCongeController apc = loader.getController();

                motifC.getScene().setRoot(root);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            } 
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            TrayNotification tray = new TrayNotification();
            tray.setTitle("Try Again");
            tray.setMessage("conge existe!");
            tray.setAnimationType(AnimationType.POPUP);
            tray.setNotificationType(NotificationType.INFORMATION);
            tray.showAndWait();
        }

    }

    @FXML
    private void Annuler(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeConge.fxml"));
        Parent root = loader.load();
        motifC.getScene().setRoot(root);
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

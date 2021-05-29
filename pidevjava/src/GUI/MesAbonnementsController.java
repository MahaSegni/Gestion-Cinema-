/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Abonne;
import Entities.Abonnement;
import Entities.TypeAbonnement;
import Service.ServiceAbonnement;
import Service.ServiceTypeAbonnement;
import static GUI.ListeTypeAbonnementController.panier;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.time.temporal.ChronoUnit;


/**
 * FXML Controller class
 *
 * @author Zeineb
 */
public class MesAbonnementsController implements Initializable {

    @FXML
    private FlowPane flowpane_abo;
    ServiceAbonnement sa = new ServiceAbonnement();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<Abonnement> list = sa.MesAbonnements(Abonne.abonne.getId());
            AfficherMesAbonnements(list);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MesAbonnementsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

 public int mailAB() {
        
      
     return(Abonne.abonne.getId());
        
        // TODO
    } 
    private void AfficherMesAbonnements(List<Abonnement> list) throws FileNotFoundException {

        ArrayList<VBox> vbx = new ArrayList<>();

        ArrayList<Separator> as = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {

            int id_type_abo = list.get(i).getTypeabonnement(); //id type abo

            ServiceTypeAbonnement sta = new ServiceTypeAbonnement();
            TypeAbonnement ta = sta.gettypeabo(id_type_abo);

            int places_dispo = ta.getPlacesdispo() - sta.places_dispo(ta.getId());

            String type = ta.getType();
            Float prix = ta.getPrix();
            Boolean valide = list.get(i).isValide();
            Date datefin = list.get(i).getDatefin();
            Date datedemande = list.get(i).getDatedemande();
            int id_abo = list.get(i).getId();

            if (places_dispo > 0) {
                //separator vertical entre les services
                Separator h = new Separator(Orientation.VERTICAL);
                h.setPrefWidth(17);
                h.setPrefHeight(44);
                h.setVisible(true);
                as.add(h);
                //creation de vbox pour contenir un service
                VBox VBoxTypeAbonnement = new VBox();
                VBoxTypeAbonnement.setSpacing(2);
                VBoxTypeAbonnement.setAlignment(Pos.CENTER);
                VBoxTypeAbonnement.setPrefHeight(50);
                VBoxTypeAbonnement.setPrefWidth(50);
                //attribution des element
                //  FileInputStream input = new FileInputStream("http://localhost//crudprojetpidev//public//public//images//"+list.get(i).getImage());
                Image image = new Image("http://localhost//integration//integration//projet//projet//projet//public//images//" + ta.getImage());
                // Image image = new Image(input);
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(150);
                imageView.setFitHeight(150);
                Label lbltype = new Label(type);
                Label lbl_places_dispo = new Label("" + places_dispo + " places disponibles");

                Label lblprix = new Label(prix + " DT");
                Button btn_suppp = new Button("Annuler"); 
               
                
                

                Label lblvalide = new Label();
                Label lbldatefin = new Label();
                int diff_datedemande_now=daysBetween(datedemande,new Date());
                int diff_datefin_now=daysBetween(datefin,new Date());

                 if (valide && diff_datefin_now>0) {
                    lblvalide.setText("Expiré");
                    lblvalide.setTextFill(Color.RED);
                    btn_suppp.setDisable(false);

                }
                else if (valide) {
                    lblvalide.setText("En cours");
                    lblvalide.setTextFill(Color.GREEN);
                    btn_suppp.setDisable(true);
                    lbldatefin.setText("jusqu'au "+datefin);

                }
                else if (!valide && diff_datedemande_now<=2) {
                    lblvalide.setText("En attente de paiement");
                    lblvalide.setTextFill(Color.RED);
                    btn_suppp.setDisable(false);

                }

                //btnSupp.setStyle("-fx-background-color : #4099ff");
                btn_suppp.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {

                        try {
                            sa.delete_abonnement(id_abo);
                            ((Node) event.getSource()).getScene().getWindow().hide();
                            Stage stage = new Stage();
                            Parent root = FXMLLoader.load(getClass().getResource("MesAbonnements.fxml"));
                            Scene scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();   } catch (IOException ex) {
                            Logger.getLogger(MesAbonnementsController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        

                    }
                });

                VBoxTypeAbonnement.getChildren().add(imageView);
                VBoxTypeAbonnement.getChildren().add(lbltype);
                VBoxTypeAbonnement.getChildren().add(lbl_places_dispo);

                VBoxTypeAbonnement.getChildren().add(lblprix);
                VBoxTypeAbonnement.getChildren().add(lblvalide);
                VBoxTypeAbonnement.getChildren().add(lbldatefin);

                VBoxTypeAbonnement.getChildren().add(btn_suppp);
                //ajout des vbox du service a vbox
                vbx.add(VBoxTypeAbonnement);
                flowpane_abo.getChildren().add(vbx.get(i));
                flowpane_abo.getChildren().add(as.get(i));
                //controle nombre de de services afficher par ligne
                if (i != 0) {
                    if (((i + 1) % 4) == 0) {
                        Separator sepHoriz = new Separator(Orientation.HORIZONTAL);
                        sepHoriz.setPrefWidth(1120);
                        sepHoriz.setPrefHeight(35);
                        sepHoriz.setVisible(false);
                        flowpane_abo.getChildren().add(sepHoriz);
                    }
                } else {
                    System.out.println(i);
                }
            }
        }

    }

    @FXML
    private void btn_logout(ActionEvent event) throws IOException {

        Abonne.abonne = null;

        ((Node) event.getSource()).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
     public int daysBetween(Date d1, Date d2){
             return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
     }

    @FXML
    private void btn_services(ActionEvent event) throws IOException { 
        
        ((Node) event.getSource()).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ListeTypeAbonnement.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }
    
    

}

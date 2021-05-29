/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Abonne;
import Entities.TypeAbonnement;
import Service.ServiceTypeAbonnement;
import com.sun.javafx.property.adapter.PropertyDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Zeineb
 */
public class ListeTypeAbonnementController implements Initializable {

    @FXML
    private FlowPane flowpane_typeabo;
    @FXML
    private Button btn_monpanier;
    @FXML
    private Button btn_mesabo;
    @FXML
    private Button btn_logout;

    ServiceTypeAbonnement sta = new ServiceTypeAbonnement();
    public static ArrayList<TypeAbonnement> panier = new ArrayList<TypeAbonnement>();
    @FXML
    private Label lbl_description_titre;
    @FXML
    private Label lbl_description;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            List<TypeAbonnement> list = sta.AfficherTypeAbonnement();
            System.out.println("--------------"+list.size());
            afficherTypeAbo(list);
            // TODO
        } catch (Exception ex) {
            Logger.getLogger(ListeTypeAbonnementController.class.getName()).log(Level.SEVERE, null, ex);
        } 

        btn_monpanier.setText("Mon panier " + panier.size());

    }

    private void afficherTypeAbo(List<TypeAbonnement> list) throws FileNotFoundException {

        ArrayList<VBox> vbx = new ArrayList<>();

        ArrayList<Separator> as = new ArrayList<>();
            int j=-1;

        for (int i = 0; i < list.size(); i++) {
TypeAbonnement ta = list.get(i);
            int places_dispo = ta.getPlacesdispo() - sta.places_dispo(ta.getId());
            

            String type = ta.getType();
            Float prix = ta.getPrix();
            
            

            if (places_dispo > 0) {
                j=j+1;
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
                Image image = new Image("http://localhost//integration//integration//projet//projet//projet//public//images//" + list.get(i).getImage());
                // Image image = new Image(input);
                ImageView imageView = new ImageView(image);
                imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        lbl_description.setText(ta.getDescription());

                    }
                });
                imageView.setFitWidth(150);
                imageView.setFitHeight(150);
                Label lbltype = new Label(type);
                Label lbl_places_dispo = new Label("" + places_dispo + " places disponibles");

                Label lblprix = new Label(prix + " DT");
                Button btnAj_Panier = new Button("Ajouter au panier");
                //btnSupp.setStyle("-fx-background-color : #4099ff");

                btnAj_Panier.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {

                        panier.add(ta);

                        btn_monpanier.setText("Mon panier " + panier.size());

                        btnAj_Panier.setDisable(true);

                    }
                });

                try {
                    if (panier.contains(ta)) {
                        btnAj_Panier.setDisable(true);
                    }
                } catch (Exception e) {
                }

                VBoxTypeAbonnement.getChildren().add(imageView);
                VBoxTypeAbonnement.getChildren().add(lbltype);
                VBoxTypeAbonnement.getChildren().add(lbl_places_dispo);

                VBoxTypeAbonnement.getChildren().add(lblprix);
                VBoxTypeAbonnement.getChildren().add(btnAj_Panier);
                //ajout des vbox du service a vbox
                vbx.add(VBoxTypeAbonnement);
                System.out.println("************"+i); 
                flowpane_typeabo.getChildren().add(vbx.get(j));
                
                
                flowpane_typeabo.getChildren().add(as.get(j));

                //controle nombre de de services afficher par ligne
                if (j != 0) {
                    if (((j + 1) % 4) == 0) {
                        Separator sepHoriz = new Separator(Orientation.HORIZONTAL);
                        sepHoriz.setPrefWidth(1120);
                        sepHoriz.setPrefHeight(35);
                        sepHoriz.setVisible(false);
                        flowpane_typeabo.getChildren().add(sepHoriz);
                    }
                } else {
                    System.out.println(i);
                }
            }
        }

    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        Abonne.abonne = null;

        ((Node) event.getSource()).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void mon_panier(ActionEvent event) throws IOException {
        ((Node) event.getSource()).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("MonPanier.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void Btn_MesAbos(ActionEvent event) throws IOException {
        ((Node) event.getSource()).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("MesAbonnements.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }

}

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
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Zeineb
 */
public class MonPanierController implements Initializable {

    @FXML
    private TableView<TypeAbonnement> tableview_panier;
    @FXML
    private Button btn_vider;
    @FXML
    private Button btn_confirmer;
    @FXML
    private Button btn_eliminer;
    @FXML
    private Button btn_retour;
    @FXML
    private TableColumn<TypeAbonnement, String> service;
    @FXML
    private TableColumn<TypeAbonnement, Float> prix;
    @FXML
    private Label total;
    @FXML
    private Button btn_payement_enligne;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
        AfficherTypeAbonnement();
        
        total.setText("Total : " + total() + " DT");
        
        if(total()<50){
        btn_payement_enligne.setDisable(true);
        }


        btn_payement_enligne.setOnAction((event) -> {

            try {
                ((Node) event.getSource()).getScene().getWindow().hide();
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("PaiementEnLigne.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(MonPanierController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
    }

    @FXML
    private void vider(ActionEvent event) throws IOException {

        ListeTypeAbonnementController.panier = new ArrayList<TypeAbonnement>();

        ((Node) event.getSource()).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ListeTypeAbonnement.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void eliminer(ActionEvent event) {

        TypeAbonnement ta = tableview_panier.getSelectionModel().getSelectedItem();
        tableview_panier.getItems().remove(ta);
        // ListeTypeAbonnementController.panier.remove(ta);
                total.setText("Total : " + total() + " DT");


    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        ((Node) event.getSource()).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ListeTypeAbonnement.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     *
     * @throws SQLException
     */
    public void AfficherTypeAbonnement() {

        service.setCellValueFactory(new PropertyValueFactory<>("type"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));

        tableview_panier.setItems(FXCollections.observableList(ListeTypeAbonnementController.panier));

    }

    public static int  total() {

        int somme_totale = 0;
        for (TypeAbonnement ta : ListeTypeAbonnementController.panier) {
            somme_totale += ta.getPrix();
        }
        
        return somme_totale;

    }

    @FXML
    private void confirmer(ActionEvent event) throws IOException {

        ServiceAbonnement sa = new ServiceAbonnement();
        for (TypeAbonnement ta : ListeTypeAbonnementController.panier) {

            Abonnement a = new Abonnement();

            a.setAbonne(Abonne.abonne.getId());
            a.setTypeabonnement(ta.getId());
            a.setValide(false);
            a.setDatedebut(Date.valueOf(LocalDate.now()));
            a.setDatefin(Date.valueOf(LocalDate.now()));
            a.setDatedemande(Date.valueOf(LocalDate.now()));
            System.out.println(a.toString());

            sa.ajoutAbonnement(a);

        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText("Veuillez procéder au paiement au local de Happy Park dans un délai de 2 jours !!!");

        alert.showAndWait();

        ((Node) event.getSource()).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ListeTypeAbonnement.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        ListeTypeAbonnementController.panier = new ArrayList<TypeAbonnement>();

    }

}

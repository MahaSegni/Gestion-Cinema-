/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Abonne;
import Entities.Abonnement;
import Entities.TypeAbonnement;
import Service.ServiceAbonne;
import Service.ServiceAbonnement;
import Utils.stripe_methode;
import com.stripe.exception.StripeException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Zeineb
 */
public class PaiementEnLigneController implements Initializable {

    @FXML
    private TextField Tf_NumCarte;
    @FXML
    private TextField Tf_Annee;
    @FXML
    private TextField Tf_Mois;
   
    
    stripe_methode stm=new stripe_methode();
    ServiceAbonne sa=new ServiceAbonne();
    @FXML
    private PasswordField Tf_Code_Verif;
    @FXML
    private Label lbl_total;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lbl_total.setText(""+MonPanierController.total()+" DT");
    }    

    @FXML
    private void Button_Valider(ActionEvent event) throws StripeException, IOException {
        
        String Abonne_stripe_id=Abonne.abonne.getStripe_id();
        
        if (Abonne_stripe_id==null){
           Abonne_stripe_id= stm.makecus(Abonne.abonne.getNomabonne(),Abonne.abonne.getMailabonne());
           sa.ajouterstripeid(Abonne_stripe_id, Abonne.abonne.getId());
                          
        }
        
        stm.makecard(Abonne_stripe_id, Tf_NumCarte.getText(), Tf_Mois.getText(), Tf_Annee.getText(), Tf_Code_Verif.getText());
       int prix_final = MonPanierController.total();
        stm.payer(prix_final, Abonne_stripe_id);
        
                ServiceAbonnement sa = new ServiceAbonnement();
        for (TypeAbonnement ta : ListeTypeAbonnementController.panier) {

            Abonnement a = new Abonnement();
            
a.setAbonne(Abonne.abonne.getId());
            a.setTypeabonnement(ta.getId());
           sa.ajoutAbonnement_enligne(a);

        }
        ListeTypeAbonnementController.panier=new ArrayList<TypeAbonnement>();
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("payment");
        alert.setContentText("le paiement a été effectué avec succès!");
        alert.showAndWait();
        
         ((Node)event.getSource()).getScene().getWindow().hide();
                Stage stage=new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("MesAbonnements.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
        
    }

    @FXML
    private void Button_Ignorer(ActionEvent event) {
        
            try {
                ((Node)event.getSource()).getScene().getWindow().hide();
                Stage stage=new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("MonPanier.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(MonPanierController.class.getName()).log(Level.SEVERE, null, ex);
            }
    
        
    }
    
}

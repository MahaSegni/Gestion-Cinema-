/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Abonne;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Zeineb
 */
public class AcceuilAbonneController implements Initializable {

    @FXML
    private Label labelNom;
    @FXML
    private Label labelPrenom;
    @FXML
    private Button btn_typeabo;
    @FXML
    private Button btn_mesabos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        labelPrenom.setText(Abonne.abonne.getPrenomabonne());
        labelNom.setText(Abonne.abonne.getNomabonne());
        
        // TODO
    }   
    @FXML
 public int mailAB() {
        
      
     return(Abonne.abonne.getId());
        
        // TODO
    } 
    @FXML
    private void btn_logout(ActionEvent event) throws IOException {
        
        
        Abonne.abonne=null;
        
      ((Node)event.getSource()).getScene().getWindow().hide();
            Stage stage=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();    
    
    }

    @FXML
    private void btn_typeabonnement(ActionEvent event) throws IOException {
        
         ((Node)event.getSource()).getScene().getWindow().hide();
            Stage stage=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("ListeTypeAbonnement.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();    
        
        
    }

    @FXML
    private void btn_ListeAbonnements(ActionEvent event) throws IOException { 
        
        
        
         ((Node)event.getSource()).getScene().getWindow().hide();
            Stage stage=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("MesAbonnements.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();    
        
        
    }
   @FXML
    private void gestReqclamation(ActionEvent event) throws IOException {
        
           Parent tableViewParent = FXMLLoader.load(getClass().getResource("Addrec.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
    }
      @FXML
    private void gestFilm(ActionEvent event) throws IOException {
        
           Parent tableViewParent = FXMLLoader.load(getClass().getResource("FrontCinema.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
    }  
     @FXML
    private void gestionParking(ActionEvent event) throws IOException {
        
           Parent tableViewParent = FXMLLoader.load(getClass().getResource("ajouterReservation.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
    } 
      @FXML
    private void gestionFront(ActionEvent event) throws IOException {
        
         try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Main.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();

            stage.setTitle("cat window");
            stage.setScene(new Scene(root1));
            stage.show();
new animatefx.animation.BounceInDown(root1).play();//animated root node
        } catch (Exception e) {
            System.out.println("can't load cat window");
        }
    }  
    
     @FXML
    private void gestEven (ActionEvent event) throws IOException {
        
           Parent tableViewParent = FXMLLoader.load(getClass().getResource("Front.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
    }  
    
    
}

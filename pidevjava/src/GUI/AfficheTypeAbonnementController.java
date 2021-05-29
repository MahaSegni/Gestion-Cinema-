/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.TypeAbonnement;
import Service.ServiceTypeAbonnement;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Zeineb
 */
public class AfficheTypeAbonnementController implements Initializable {

    @FXML
    private TableColumn<TypeAbonnement, Integer> colId;
    @FXML
    private TableColumn<TypeAbonnement, String> colType;
    @FXML
    private TableColumn<TypeAbonnement, String> colDesc;
    @FXML
    private TableColumn<TypeAbonnement, Float> colPrix;
    private TableColumn<TypeAbonnement, String> colImage;
    @FXML
    private TableColumn<TypeAbonnement, Integer> colPlacesdispo;
    @FXML
    private TableView<TypeAbonnement> tableviewtypeabonnement;
    @FXML
    private ImageView img_type;
    public static TypeAbonnement ta=new TypeAbonnement();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            AfficherTypeAbonnement();
        } catch (SQLException ex) {
            Logger.getLogger(AfficheTypeAbonnementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tableviewtypeabonnement.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> showDetails(newValue));
    }    
    
    
    /**
     *
     * @throws SQLException
     */
    public void AfficherTypeAbonnement() throws SQLException {
        ServiceTypeAbonnement sta=new ServiceTypeAbonnement();
        List<TypeAbonnement> TypeAbonnement=sta.AfficherTypeAbonnement();
        
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        colPlacesdispo.setCellValueFactory(new PropertyValueFactory<>("placesdispo"));
 
        tableviewtypeabonnement.setItems(FXCollections.observableList(TypeAbonnement)); 
    } 
    
    
    private void showDetails(TypeAbonnement r){
    
    
  if(!(r==null)){
      
  img_type.setVisible(true);
        ta = tableviewtypeabonnement.getSelectionModel().getSelectedItem();
      String img = ta.getImage();
    
     
            System.err.println(img);

            Image i = new Image("http://localhost//integration//integration//projet//projet//projet//public//images//"+ img);
            img_type.setImage(i);

 
  } 
    }
    @FXML
    private void ModifierTypeAbonnement(ActionEvent event) throws IOException { 
        
         if(tableviewtypeabonnement.getSelectionModel().getSelectedItem()==null)
       
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
alert.setTitle("Erreur");
//alert.setHeaderText("Look, an Error Dialog");
alert.setContentText("Vous devez sélectionner un type d'abonnement !");

alert.showAndWait();
        }
         else{
            ta = tableviewtypeabonnement.getSelectionModel().getSelectedItem();
      /*  TypeAbonnement t=new TypeAbonnement();
                t.setId(ta.getId());

        t.setType(ta.getType());
        t.setDescription(ta.getDescription());
        t.setPrix(ta.getPrix());
        t.setImage(ta.getImage());*/
        
        
        
        TypeAbonnement.typeabo=ta;
        
        //System.out.println(ConsulterListeRendezVousClientController.rev.getDescription());
         ((Node) event.getSource()).getScene().getWindow().hide();
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("ModifierTypeAbonnement.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
    }
    }
    
         @FXML
    private void AjouterTypeAbonnement(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("AddTypeAbonnement.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();

    }
      @FXML
    private void Menu(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("menu.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();

    }
    
}
    
    
    
    
    
    /*
    
    public static TypeAbonnement ta=new TypeAbonnement();
    
    @FXML
    private void Supprimer_TypeAbo(ActionEvent event) throws IOException { 
     
         if(tableviewtypeabonnement.getSelectionModel().getSelectedItem()==null)
       
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
alert.setTitle("Erreur");
//alert.setHeaderText("Look, an Error Dialog");
alert.setContentText("Vous devez sélectionner un type d'abonnement!");

alert.showAndWait();
        }
         
    else {
             
            ta = tableviewtypeabonnement.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Suppression");
            alert.setContentText("Voulez-vous vraiment supprimer le type d'abonnement sélectionné ?");

             Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
               //int  id = tableviewtypeabonnement.getSelectionModel().getSelectedItem().getId();
System.out.println("*************************************************************");
               ServiceTypeAbonnement sta=new ServiceTypeAbonnement();
               sta.DeleteTypeAbonnement(8);

                ((Node) event.getSource()).getScene().getWindow().hide();
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("AfficheTypeAbonnement.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } else {
                ((Node) event.getSource()).getScene().getWindow().hide();
//     Ecole=TableEcole.getSelectionModel().getSelectedItem();
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("AfficheTypeAbonnement.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }}  
        
        
    */
    
    
    
    
    
    

    

   
    


    
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.File;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mahas
 */
public class MenuController implements Initializable {

    
       @FXML
    private ImageView logo;

    @FXML
    private Button gestionEmploye;

    @FXML
    private Label label;

    @FXML
    private Button gestionConge;
    @FXML
    private Button gestionCinema;
    @FXML
    private Button gestionEquipement;
    @FXML
    private Button gestionReclamation;
    @FXML
    private Button gestionFilm;
    @FXML
    private Button gestionParking;
    @FXML
    private Button agenda;
    @FXML
    private Button gestionPlat;
    @FXML
    private Button gestionCat;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        File file = new File("../../../../../../../../xampp/htdocs/integration/integration/projet/projet/projet/public/images1/logojava.png");
        Image image = new Image(file.toURI().toString());
        logo.setImage(image);
    }  
    
    @FXML
    private void gestEmp(ActionEvent event) throws IOException {
        
           Parent tableViewParent = FXMLLoader.load(getClass().getResource("ListeEmploye.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
    }  
         @FXML
    private void gestCon(ActionEvent event) throws IOException {
        
           Parent tableViewParent = FXMLLoader.load(getClass().getResource("ListeConge.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
    } 
      @FXML
    private void gestFil(ActionEvent event) throws IOException {
        
           Parent tableViewParent = FXMLLoader.load(getClass().getResource("ListeFilm.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
    } 
     @FXML
    private void gestCinema(ActionEvent event) throws IOException {
        
           Parent tableViewParent = FXMLLoader.load(getClass().getResource("ListeCinema.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
    } 

 
  @FXML
    private void gestReqclamation(ActionEvent event) throws IOException {
        
           Parent tableViewParent = FXMLLoader.load(getClass().getResource("allrec.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
    } 
  @FXML
    private void gestEquipemet(ActionEvent event) throws IOException {
        
           Parent tableViewParent = FXMLLoader.load(getClass().getResource("Equipemet.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
    } 
   @FXML
    private void gestionParking(ActionEvent event) throws IOException {
        
           Parent tableViewParent = FXMLLoader.load(getClass().getResource("Reservation.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
    } 
  
       @FXML
    private void agenda(ActionEvent event) throws IOException {
        
        
         Parent tableViewParent = FXMLLoader.load(getClass().getResource("../GUI/gererCalendar.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
    } 
     @FXML
    private void gestionPlat(ActionEvent event) throws IOException {
        
       
           try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PlatMainScreenFXML.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();

            stage.setTitle("cat window");
            stage.setScene(new Scene(root1));
            stage.show();
new animatefx.animation.RollIn(root1).play();//animated root node
        } catch (Exception e) {
            System.out.println("can't load cat window");
        }
       
    } 
       @FXML 
    private void gestionCat(ActionEvent event) throws IOException {
        
             try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CatMainScreenFXML.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();

            stage.setTitle("cat window");
            stage.setScene(new Scene(root1));
            stage.show();
new animatefx.animation.RollIn(root1).play();//animated root node
        } catch (Exception e) {
            System.out.println("can't load cat window");
        }
    } 
//     @FXML
//    private void gestionAbonne(ActionEvent event) throws IOException {
//        
//           Parent tableViewParent = FXMLLoader.load(getClass().getResource(".fxml"));
//        Scene tabbleViewScene = new Scene(tableViewParent);
//        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
//        window.setScene(tabbleViewScene);
//        window.show(); 
//    } 
       @FXML
    private void gestionAbonnement(ActionEvent event) throws IOException {
        
           Parent tableViewParent = FXMLLoader.load(getClass().getResource("BackListeAbonnements.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
    } 
  @FXML
    private void gestEven(ActionEvent event) throws IOException {
        
           Parent tableViewParent = FXMLLoader.load(getClass().getResource("Admin.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
    } 
       @FXML
    private void gestCellu(ActionEvent event) throws IOException {
        
           Parent tableViewParent = FXMLLoader.load(getClass().getResource("gererCellule.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
    } 
     
      @FXML
    private void btn_logout(ActionEvent event) throws IOException {
      
      ((Node)event.getSource()).getScene().getWindow().hide();
            Stage stage=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();    
    
    }
}

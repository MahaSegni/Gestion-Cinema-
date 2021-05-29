/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.TypeAbonnement;
import Service.ServiceTypeAbonnement;
import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
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
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Zeineb
 */
public class ModifierTypeAbonnementController implements Initializable {

    @FXML
    private TextField tfType;
    @FXML
    private TextField tfDescription;
    @FXML
    private TextField tfPrix;
    @FXML
    private TextField tfPlacesdispo;
    @FXML
    private Button ButtonModifierType;
    @FXML
    private Button tfImage;
    @FXML
    private Label tfImage_nom;
    @FXML
    private Button ButtonConsulterType;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tfType.setText(TypeAbonnement.typeabo.getType());
        tfDescription.setText(TypeAbonnement.typeabo.getDescription());
        tfPrix.setText(String.valueOf(TypeAbonnement.typeabo.getPrix()));
        tfImage_nom.setText(TypeAbonnement.typeabo.getImage());
int pd=TypeAbonnement.typeabo.getPlacesdispo();
        tfPlacesdispo.setText(String.valueOf(pd));

        
        
        
    }   
    @FXML
    private void ModifierTypeAbo(ActionEvent event) {
        try{
      if(tfDescription.getText().equals("")){
        
                    Alert alert = new Alert(Alert.AlertType.ERROR);
alert.setTitle("Erreur");
alert.setHeaderText("Erreur");
alert.setContentText("La description est obligatoire !!!");

alert.showAndWait();
        }
      
      else{
        
          ServiceTypeAbonnement vs = new ServiceTypeAbonnement();
    TypeAbonnement r = new TypeAbonnement();
      
r.setType(tfType.getText()); 
r.setDescription(tfDescription.getText()); 
r.setPrix(Float.parseFloat(tfPrix.getText())); 
r.setImage(tfImage_nom.getText()); 
r.setPlacesdispo(Integer.parseInt(tfPlacesdispo.getText()));






      vs.modifierTypeAbonnement(r, TypeAbonnement.typeabo.getId());
      
      
      
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("Modification");
alert.setHeaderText("Modification");
alert.setContentText("Le type d'abonnement a été modifié");
        
      alert.showAndWait();

      
      
      
       TypeAbonnement.typeabo = null;  
         ((Node)event.getSource()).getScene().getWindow().hide();
            Stage stage=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("AfficheTypeAbonnement.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show(); }}
              catch(Exception e){
        
        
        }
     
    }



    String imgname;
     @FXML
        private void importer_img
        (ActionEvent event) throws MalformedURLException, NoSuchAlgorithmException, IOException  {

            System.out.println("Load Image Button Pressed");
            FileChooser filechooser = new FileChooser();

            File file = filechooser.showOpenDialog(null);
            if (file != null) {
                System.out.println("File Was Selected");
                URL url = file.toURI().toURL();
                String urlimg = url.getFile().replaceFirst("/", "");
                //  System.out.println("url = "+urlimg);

                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] urlmd5 = md.digest(urlimg.getBytes());
                final String result = new String(HexBin.encode(urlmd5)) + ".jpeg";
                AddTypeAbonnementController.upload(file, result);
                imgname = result; 
                tfImage_nom.setText(imgname);

                //    System.out.println("url md5 = "+result);

            }
        }
        
        
    

    @FXML
    private void Consulter_typeabo(ActionEvent event) throws IOException {
        
          ((Node)event.getSource()).getScene().getWindow().hide();
            Stage stage=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("AfficheTypeAbonnement.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
    
}

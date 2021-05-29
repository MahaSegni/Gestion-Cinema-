/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Abonne;
import Service.Password;
import Service.ServiceAbonne;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import pidevjava2.pidevjava2FXMain;

/**
 * FXML Controller class
 *
 * @author Zeineb
 */
public class LoginController implements Initializable {

    @FXML
    private TextField tf_mail;
    @FXML
    private PasswordField pf_mdp;
    @FXML
    private Label error_mssg;
 @FXML
    private AnchorPane root;
 @FXML
    private StackPane pane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         if (!pidevjava2FXMain.isSplashLoaded) {
            loadSplashScreen();
        }
            pane.setVisible(false);
        
    }    

    @FXML
    private void btn_seconnecter(ActionEvent event) throws SQLException, IOException {
        
        
        ServiceAbonne sa=new ServiceAbonne();
        Abonne abonne=sa.login(tf_mail.getText());
        System.out.println("****"+pf_mdp.getText());
        if(Password.checkPassword(pf_mdp.getText(), abonne.getPassword())&& abonne.getMailabonne()!=null)
        {
        
                  //  error_mssg.setVisible(false);

            Abonne.abonne=abonne;
            
            
                            
                            
                     if(Abonne.abonne.getRoles().equals("abonne"))   
                     {
                      
                         ((Node)event.getSource()).getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("AcceuilAbonne.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
                      
                     }
                     else if(abonne.getRoles().equals("admin"))
                     {
                     
                    ((Node)event.getSource()).getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("menu.fxml")); 
        
        Scene scene = new Scene(root);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();    
                     
                     
                     }
                            
        
        }
        
        else {
        
        error_mssg.setVisible(true);
        
        }
        
        
        
        
        
    }

    @FXML
    private void btn_sinscrire(ActionEvent event) throws IOException {
        
        ((Node) event.getSource()).getScene().getWindow().hide();
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("Addabonne.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
        
    }
      
  private void loadSplashScreen() {
        try {
            pidevjava2FXMain.isSplashLoaded = true;

            StackPane pane = FXMLLoader.load(getClass().getResource(("/GUI/splash.fxml")));
            root.getChildren().setAll(pane);

            FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), pane);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);

            FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), pane);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);

            fadeIn.play();

            fadeIn.setOnFinished((e) -> {
                fadeOut.play();
            });

            fadeOut.setOnFinished((e) -> {
                try {
                    AnchorPane parentContent = FXMLLoader.load(getClass().getResource(("/GUI/login.fxml")));
                    root.getChildren().setAll(parentContent);
                } catch (IOException ex) {
                   // Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

        } catch (IOException ex) {
           // Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

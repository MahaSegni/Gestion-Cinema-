/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MAROUANE
 */
public class LoadingController implements Initializable {

    private Service thread;
    private boolean flag = false;
    private Dictionnaire dic;

    @FXML
    ProgressBar progressBar = new ProgressBar(0);
    
    @FXML
    private Label percent;
    
    @FXML
    Button close;

    @FXML
    public void handleCloseButtonAction(ActionEvent event) {
        thread.cancel();
        flag = true;
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }



    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //dic = this.chargerDico("frUTF.dic");
        
        thread.setOnSucceeded(e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            try {
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage1 = new Stage();
                stage1.setTitle("Bienvenue sur Dico");
                stage1.setMinHeight(400);
                stage1.setMinWidth(700);
                Scene scene1 = new Scene(root1);
                stage1.setScene(scene1);
                stage1.show();
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            FXMLDocumentController controller
                    = fxmlLoader.<FXMLDocumentController>getController();
           // controller.setDico(dic);
//            controller..setLangue("Français");
//            controller.setCode("FR-fr");
            Stage stage = (Stage) close.getScene().getWindow();
            stage.close();
        });
    }

    public void chargerDico(String path) {

       // Dictionnaire dico =Dictionnaire.getOneDictionnaire("FR.txt");
        thread = new Service<Integer>() {
            @Override
            public Task createTask() {
                return new Task<Integer>() {
                    @Override
                    protected Integer call() throws InterruptedException, IOException {
                        int i = 0;
                        System.out.println("Chargement du Dico ...");
                        try (DataInputStream data = new DataInputStream(getClass().getResourceAsStream(path))) {
                            String mot;
                            while (i < data.available() && flag == false) {
                                updateProgress(i, data.available());
                                updateMessage(new DecimalFormat("#").format((double) i / data.available() * 100) + " %");
                                mot = data.readUTF();
                                
                                i++;
                            }
                            System.out.println("chargé " + i + "mots");
                        } catch (Exception e) {
                        }
                        return i;
                    }
                };
            }
        };
        progressBar.progressProperty().bind(thread.progressProperty());
        percent.textProperty().bind(thread.messageProperty());
        thread.start();
        //return dico;
    }

}

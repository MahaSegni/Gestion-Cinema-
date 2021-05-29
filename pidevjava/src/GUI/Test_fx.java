/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author 
 * BOUARRHA Ayoub
 * LAMINE Marouane 
 */
public class Test_fx extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/gui/FXMLDocument.fxml"));
            stage.setTitle("Correcteur Orthographique");
            stage.getIcons().add(new Image("/gui/icons/icon.png"));
            Scene scene1 = new Scene(root);
            scene1.setFill(Color.TRANSPARENT);
            stage.setScene(scene1);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         launch(args);
    }
}

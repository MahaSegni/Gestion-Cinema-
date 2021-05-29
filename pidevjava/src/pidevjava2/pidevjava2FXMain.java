/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevjava2;


import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/** 
 *
 * @author mahas
 */ 
public class pidevjava2FXMain extends Application {
 public static Boolean isSplashLoaded = false;
    @Override
    public void start(Stage stage) throws IOException {
      // try { 
//            Parent root = FXMLLoader.load(getClass() front wla back !!
//Parent root = FXMLLoader.load(getClass().getResource("../GUI/AcceuilAbonne.fxml"));
   Parent root = FXMLLoader.load(getClass().getResource("../GUI/login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();//} 
       // catch (Exception e) {
          //  System.out.println( e);    
       // }
 
        
       
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

   
}

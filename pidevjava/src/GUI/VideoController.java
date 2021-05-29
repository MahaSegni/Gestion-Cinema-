/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import static javafx.scene.media.MediaPlayer.Status.PLAYING;
import javafx.scene.media.MediaView;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class VideoController implements Initializable {

    @FXML
    private MediaView mv;
   
   

    /**
     * Initializes the controller class.
     */
    MediaPlayer mediaplayerplayer;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        final StackPane root=new StackPane();
       
        String VUrl="file:/C:/Users/mahas/Desktop/imagespi/Clip1.mp4";
        Media newMedia = new Media(VUrl);
        
        MediaPlayer thePlayer = new MediaPlayer(newMedia);
        final MediaView theView=new MediaView(thePlayer);
       
        thePlayer.setAutoPlay(true);
       
       
       
        root.widthProperty().addListener(new InvalidationListener(){
           
            @Override
            public void invalidated(Observable observable){
                theView.setFitHeight(root.getHeight());
            }
                });
       
        root.getChildren().add(theView);
       
       
               
    }
  
   
   
}
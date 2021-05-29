/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Entities.Cat;
import Entities.Plat;
import Service.ServiceCat;
import Service.ServicePlat;
import java.io.IOException;
import javafx.scene.Node;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class MainController implements Initializable {

    @FXML
    private VBox filtreVboxCat;
    @FXML
    private FlowPane flowpaneCat;

    ToggleGroup gp = new ToggleGroup();
    ServiceCat ser = new ServiceCat();
    ServicePlat sp = new ServicePlat();
    public int cat;
    @FXML
    private Label errerLabel;
    @FXML
    private Pane ap;
    @FXML
    private Label articleanim1;
    @FXML
    private Button btnWatchVideo;
    @FXML
    private Button menu;
    @FXML
    private Button video;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<Plat> list = sp.getAll();
        afficherProduit(list);
        FiltreParCategorie();

        gp.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldToggle, Toggle newToggle) {
                //print new selected value after change
                //System.out.println("Selected Radio Button: " + ((RadioButton) newToggle).getText());
                String c = ((RadioButton) newToggle).getText();
                List<Cat> listc = ser.getAll();

                if (c.equalsIgnoreCase("Tout")){
                     errerLabel.setText("");
                    List<Plat> list = sp.getAll();
                    afficherProduit(list);
                }else {
                for (Cat catt : listc) {
                    // System.out.print("test categ NOM   /"+catt.getNomc());
                    if (catt.getNomc().equalsIgnoreCase(c)) {
                        errerLabel.setText("");
                        cat = catt.getIdc();
                        flowpaneCat.getChildren().clear();
                        List<Plat> list = sp.getAllFillter(cat);
                        afficherProduit(list);
                        
                    }
//                    else {
//                        flowpaneCat.getChildren().clear();
//                        errerLabel.setText("Accun éllement trouver");
//                    }
//
                }
                }

                //System.out.print("test categ    /"+cat);
            }

        });

    }

    public void FiltreParCategorie() {

        ServiceCat sc = new ServiceCat();
        List<Cat> list = new ArrayList<>();
        list = sc.getAll();
        //create a toggle group

        RadioButton b = new RadioButton("Tout");
        b.setToggleGroup(gp);
        filtreVboxCat.setSpacing(10);
        filtreVboxCat.getChildren().add(b);

        for (int i = 0; i < list.size(); i++) {
            RadioButton b1 = new RadioButton(list.get(i).getNomc());
            b1.setToggleGroup(gp);
            filtreVboxCat.setSpacing(10);
            filtreVboxCat.getChildren().addAll(b1);

        }
    }

    private void afficherProduit(List<Plat> list) {

        ArrayList<VBox> vbx = new ArrayList<>();

        ArrayList<Separator> as = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {

            try {
                //separator vertical entre les plat
                Separator h = new Separator(Orientation.VERTICAL);
                h.setPrefWidth(17);
                h.setPrefHeight(44);
                h.setVisible(false);
                as.add(h);
                //creation de vbox pour contenir ele plat
                VBox VBoxProduit = new VBox();
                VBoxProduit.setSpacing(5);
                VBoxProduit.setAlignment(Pos.CENTER);
                VBoxProduit.setPrefHeight(100);
                VBoxProduit.setPrefWidth(100);

                //attribution des element
                FileInputStream input = new FileInputStream("C:\\Users\\mahas\\Desktop\\imagespi\\" +list.get(i).getImagep());
              
                Image image = new Image(input);
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(100);
                imageView.setFitHeight(100);

                Label nom = new Label(list.get(i).getNomp());
                Label prix = new Label(Double.toString(list.get(i).getPrixp()) + " DT");
                prix.setStyle("-fx-border-color :  #ff5e5e");
                Button btnSupp = new Button("Read More");
                btnSupp.setStyle("-fx-background-color :  #9efd38");
                btnSupp.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {

                    }
                });

                VBoxProduit.getChildren().add(imageView);
                VBoxProduit.getChildren().add(nom);
                VBoxProduit.getChildren().add(prix);
                VBoxProduit.getChildren().add(btnSupp);

                //ajout des vbox du plat a vbox 
                vbx.add(VBoxProduit);
                flowpaneCat.getChildren().add(vbx.get(i));
                flowpaneCat.getChildren().add(as.get(i));

                //controle nombre de plat afficher par ligne
                if (i != 0) {
                    if (((i + 1) % 3) == 0) {
                        Separator sepHoriz = new Separator(Orientation.HORIZONTAL);
                        sepHoriz.setPrefWidth(1120);
                        sepHoriz.setPrefHeight(35);
                        sepHoriz.setVisible(false);
                        flowpaneCat.getChildren().add(sepHoriz);
                    }
                } else {
                    System.out.println(i);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    private void WatchVideo(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Video.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            
            stage.setTitle("video window");
            stage.setScene(new Scene(root1));
            stage.show();
            
        }catch(Exception e){
            System.out.println("can't load video window");
        }
    }
 @FXML
    private void menuFront(ActionEvent event) throws IOException {

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("AcceuilAbonne.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("video window");
        window.setScene(tabbleViewScene);
        window.show();
    }
     @FXML
    private void video(ActionEvent event) throws IOException {

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Video.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
    }
}

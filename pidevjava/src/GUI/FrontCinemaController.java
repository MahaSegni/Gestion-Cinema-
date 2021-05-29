/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.CategorieF;
import Entities.Film;
import Service.ServiceFilm;
import Entities.Cinema;
import Service.ServiceCategorieF;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Separator;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.web.WebView;
import Service.ServiceCinema;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.io.File;
import java.io.IOException;
import static java.util.Collections.list;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pidevjava2.QrCode;

/**
 * FXML Controller class
 *
 * @author mahas
 */
public class FrontCinemaController implements Initializable {
  @FXML
    private VBox filtreVboxCat;
    @FXML
    private FlowPane flowpaneCat;

    ToggleGroup gp = new ToggleGroup();
      ServiceCategorieF ser = new ServiceCategorieF();
 
    ServiceFilm sp = new ServiceFilm();
    public int cat;
    @FXML
    private Label errerLabel;
    @FXML
    private TextField rechercher;
    @FXML
    private Button chercher;
//      @FXML
//    private Pagination pagination;
   //  Pagination pagination;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
   
        List<Film> list = sp.afficher();
        afficherProduit(list);
      try {
          FiltreParCategorie();
      } catch (SQLException ex) {
          Logger.getLogger(FrontCinemaController.class.getName()).log(Level.SEVERE, null, ex);
      }
      gp.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldToggle, Toggle newToggle) {
                try {
                    //print new selected value after change
                    //System.out.println("Selected Radio Button: " + ((RadioButton) newToggle).getText());
                    String c = ((RadioButton) newToggle).getText();
                    List<CategorieF> listc = ser.AfficherCi();
                    
                    if (c.equalsIgnoreCase("Tout")){
                        errerLabel.setText("");
                        List<Film> list = sp.afficher();
                        afficherProduit(list);
                    }else {
                        for (CategorieF catt : listc) {
                             System.out.print(catt.getDesc_c());
                            if (catt.getDesc_c().equalsIgnoreCase(c)) {
                                errerLabel.setText("");
                                cat = catt.getId();
                                flowpaneCat.getChildren().clear();
                                List<Film> list = sp.getAllFillter(cat);
                                afficherProduit(list);
                                
                            }
                    else {
                        flowpaneCat.getChildren().clear();
                        errerLabel.setText("Accun éllement trouver");
                    }
//
                        }
                    }
                    
                    //System.out.print("test categ    /"+cat);
                } catch (SQLException ex) {
                    Logger.getLogger(FrontCinemaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

    }    
    public static void generate_qr(String qrCodeData) {
        try {
            String filePath = "E:\\QRCODE\\"+qrCodeData+".png";
            String charset = "UTF-8"; // or "ISO-8859-1"
            Map < EncodeHintType, ErrorCorrectionLevel > hintMap = new HashMap < EncodeHintType, ErrorCorrectionLevel > ();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix matrix = new MultiFormatWriter().encode(
                new String(qrCodeData.getBytes(charset), charset),
                BarcodeFormat.QR_CODE, 200, 200, hintMap);
            MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
                .lastIndexOf('.') + 1), new File(filePath));
            System.out.println("QR Code image created successfully!");
        } catch (Exception e) {
            System.err.println(e);
        }
    }
     public void FiltreParCategorie() throws SQLException {

        ServiceCategorieF sc = new ServiceCategorieF();
        List<CategorieF> list = new ArrayList<>();
        list = sc.AfficherCi();
        //create a toggle group

        RadioButton b = new RadioButton("Tout");
        b.setToggleGroup(gp);
        filtreVboxCat.setSpacing(10);
        filtreVboxCat.getChildren().add(b);

        for (int i = 0; i < list.size(); i++) {
            RadioButton b1 = new RadioButton(list.get(i).getDesc_c());
            b1.setToggleGroup(gp);
            filtreVboxCat.setSpacing(10);
            filtreVboxCat.getChildren().addAll(b1);

        }
    }
    private void afficherProduit(List<Film> list) {
       
//   pagination = new Pagination(10);
//        pagination.setStyle(".fx-border-color: blue");
//       pagination.setPageFactory((Integer pageIndex)-> createPage(pageIndex));
//   
//        
//        AnchorPane anchor = new AnchorPane();
//       
//    AnchorPane.setLeftAnchor(pagination, 10.0);
//    AnchorPane.setRightAnchor(pagination, 10.0);
//    anchor.getChildren().add(pagination);
        ArrayList<VBox> vbx = new ArrayList<>();

        ArrayList<Separator> as = new ArrayList<>();
// VBox box = new VBox();
//  ScrollPane spe = new ScrollPane();
//       box.getChildren().addAll(spe);
//       VBox.setVgrow(spe, Priority.ALWAYS);
       

  //VBox VBoxProduit = new VBox();
//WebView c=new WebView();
//       
//        sp.setContent(c);
//       sp.setHbarPolicy(ScrollBarPolicy.NEVER);
//sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
                
        for (int i = 0; i < list.size(); i++) {

            try {
                //separator vertical entre les films
                Separator h = new Separator(Orientation.VERTICAL);
                h.setPrefWidth(17);
                h.setPrefHeight(44);
                h.setVisible(false);
                as.add(h);
                //creation de vbox pour contenir les films
               VBox VBoxProduit = new VBox();
                VBoxProduit.setSpacing(5);
                VBoxProduit.setAlignment(Pos.CENTER);
                VBoxProduit.setPrefHeight(70);
                VBoxProduit.setPrefWidth(120);
               

                //attribution des element
                FileInputStream input = new FileInputStream("C:\\xampp\\htdocs\\integration\\integration\\projet\\projet\\projet\\public\\images1\\"+list.get(i).getFilename());
                Image image = new Image(input);
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(130);
                imageView.setFitHeight(160);
                Label nom = new Label(list.get(i).getNomfilm());
                Label prix = new Label (list.get(i).getDescriptionf()) ;
               Button btnSupp = new Button("Reserver");
                btnSupp.setStyle("-fx-background-color :  #ff6b6b");
//btnSupp.setOnAction((event) -> {
//    QrCode.generate_qr(list.get(i).getNomfilm());
//});
                VBoxProduit.getChildren().add(imageView);
              VBoxProduit.getChildren().add(nom);
                VBoxProduit.getChildren().add(prix);
              VBoxProduit.getChildren().add(btnSupp);
       
                //ajout des vbox de film a vbox 
                vbx.add(VBoxProduit);
                 
                flowpaneCat.getChildren().add(vbx.get(i));
                flowpaneCat.getChildren().add(as.get(i));

                //controle nombre des films afficher par ligne
                if (i != 0) {
                    if (((i + 1) % 6) == 0) {
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
                Logger.getLogger(FrontCinemaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       

    }
    
    
    
    private void afficherProduit1(List<Film> list) throws FileNotFoundException {

        ArrayList<VBox> vbx = new ArrayList<>();

        


        for (int i = 0; i < list.size(); i++) {

            try {
                //separator vertical entre les films
               
                //creation de vbox pour contenir les films
                VBox VBoxProduit = new VBox();
                VBoxProduit.setSpacing(5);
                VBoxProduit.setAlignment(Pos.CENTER);
                VBoxProduit.setPrefHeight(100);
                VBoxProduit.setPrefWidth(100);
                

                //attribution des element
                FileInputStream input = new FileInputStream("C:\\xampp\\htdocs\\integration\\integration\\projet\\projet\\projet\\public\\images1\\"+list.get(i).getFilename());
                Image image = new Image(input);
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(200);
                imageView.setFitHeight(250);
                Label nom = new Label(list.get(i).getNomfilm());
                Label prix = new Label (list.get(i).getDescriptionf()) ;
               Button btnSupp = new Button("Reserver");
                btnSupp.setStyle("-fx-background-color : #4099ff");
               

                VBoxProduit.getChildren().add(imageView);
              VBoxProduit.getChildren().add(nom);
                VBoxProduit.getChildren().add(prix);
              VBoxProduit.getChildren().add(btnSupp);
 
                //ajout des vbox de film a vbox 
                vbx.add(VBoxProduit);
                 
                flowpaneCat.getChildren().add(vbx.get(i));
                

                //controle nombre de plat afficher par ligne
                if (i != 0) {
                    if (((i + 1) % 5) == 0) {
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
                Logger.getLogger(FrontCinemaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
     

    }
 @FXML
    private void Menu(ActionEvent event) throws IOException {
        
           Parent tableViewParent = FXMLLoader.load(getClass().getResource("../GUI/AcceuilAbonne.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
    }
//         
//    private VBox createPage(Integer pageIndex) {
//        VBox pageBox = new VBox();
//         Label label1 = new Label("Content for page with index: " + pageIndex);
//            label1.setFont(new Font("Arial", 24));
//
//            Label label2 = new Label("Main content of the page ...");
//    Label pageLabel = new Label("page : "+(pageIndex+1));
//   pageBox.getChildren().add(label1);
//    pageBox.getChildren().add(label2);
//    return pageBox;}
   
}

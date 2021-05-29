/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.TypeAbonnement;
import Service.ServiceTypeAbonnement;
import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
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
public class AddTypeAbonnementController implements Initializable {

    @FXML
    private TextField tfType;
    @FXML
    private TextField tfDescription;
    @FXML
    private TextField tfPrix;
    @FXML
    private TextField tfPlacesdispo;
    @FXML
    private Button tfImage;
    @FXML
    private Label tfImage_nom;
    @FXML
    private Button ButtonConsulterType;
    @FXML
    private Button ButtonAjouterType;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public static String upload(File file, String fileName) throws IOException {
        BufferedOutputStream stream = null;
        String globalPath = "C:\\xampp\\htdocs\\integration\\integration\\projet\\projet\\projet\\public\\images";
        String localPath = "localhost/";
        /*fileName = file.getName();
        fileName=fileName.replace(" ", "_");*/
        try {
            Path p = file.toPath();

            byte[] bytes = Files.readAllBytes(p);

            File dir = new File(globalPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            // Create the file on server
            File serverFile = new File(dir.getAbsolutePath() + File.separator + fileName);
            stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();
            return localPath + "/" + fileName;
        } catch (IOException ex) {
            return "error2";

        }

        /*     @FXML
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
                upload(file, result);
                imgname = result;

                //    System.out.println("url md5 = "+result);
                txt_photo.setText(imgname);

            }
        }*/
    }

    @FXML
    private void AjouterTypeAbo(ActionEvent event) throws IOException {
        ServiceTypeAbonnement sta = new ServiceTypeAbonnement();

        TypeAbonnement ta = new TypeAbonnement(tfType.getText(), tfDescription.getText(), Float.parseFloat(tfPrix.getText()),
                imgname, Integer.valueOf(tfPlacesdispo.getText()));
        

        sta.AddTypeAbonnement(ta);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Type d'abonnement");
        alert.setContentText("le type d'abonnement a été ajouté avec succès!");
        alert.showAndWait();
        
         ((Node)event.getSource()).getScene().getWindow().hide();
                Stage stage=new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("AfficheTypeAbonnement.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
        

    }

    String imgname;

    @FXML
    private void importer_img(ActionEvent event) throws MalformedURLException, NoSuchAlgorithmException, IOException {

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
            upload(file, result);
            imgname = result;
            tfImage_nom.setText(imgname);

            //    System.out.println("url md5 = "+result);
        }
    }

    @FXML
    private void Consulter_typeabo(ActionEvent event) throws IOException {
        ((Node) event.getSource()).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AfficheTypeAbonnement.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}

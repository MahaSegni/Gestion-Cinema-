/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.CategorieF;
import Entities.Film;
import Service.ServiceCategorieF;
import Service.ServiceFilm;
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
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * FXML Controller class
 *
 * @author mahas
 */
public class AjouterFilmController implements Initializable {
 private Connection con;
        private Statement ste;

    
    @FXML
    private TextField nomfilm;
    @FXML
    private TextField descriptionf;
    @FXML
    private TextField affiche;
     @FXML
    private ComboBox<String> comboBoxCat1;
      @FXML
    private DatePicker datesortie;
    @FXML
    private Button bValider;
    @FXML
    private Button bAnnuler;
     String imgname;
      ServiceCategorieF serCat1 = new ServiceCategorieF() ;
  
 
     @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
             ArrayList<CategorieF> arrayList =  (ArrayList) serCat1.AfficherCi();
             for (int i=0;i<arrayList.size();i++){
                 comboBoxCat1.getItems().addAll(arrayList.get(i).getDesc_c());
             }
         } catch (SQLException ex) {
             Logger.getLogger(AjouterEmployeController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }    
   
    
    @FXML
    private void ajouterFilm(ActionEvent event) {
        
         try {
            String nom = nomfilm.getText();
            String prenom = descriptionf.getText();
            String numtel = affiche.getText();
            String cat_n = comboBoxCat1.getValue().toString() ;
                int cat = serCat1.getIdfi(cat_n);
            LocalDate d1 = datesortie.getValue();
                Date date = java.sql.Date.valueOf(d1);
           ServiceFilm se = new ServiceFilm();
           Film e = new Film( nom, prenom, numtel,cat, date);
            se.ajouter(e); 
            nomfilm.setText("");
            descriptionf.setText("");
            affiche.setText("");
          
    FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("../GUI/ListeFilm.fxml"));
            try {
                Parent root = loader.load();
               ListeFilmController apc = loader.getController();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage()
            );
          
        }    
    }
    
    public static String upload(File file, String fileName) throws IOException {
        BufferedOutputStream stream = null;
        String globalPath = "C:\\xampp\\htdocs\\integration\\integration\\projet\\projet\\projet\\public\\images1";
        String localPath = "localhost:8000/";
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
    }
    
    @FXML
        private void importer_img(ActionEvent event) throws MalformedURLException, NoSuchAlgorithmException, IOException  {

            System.out.println("Load Image Button Pressed");
            FileChooser filechooser = new FileChooser();

            File file = filechooser.showOpenDialog(null);
            if (file != null) {
                System.out.println("File Was Selected");
                URL url = file.toURI().toURL();
                 String urlimg = url.getFile();
               String urlimg1 = url.getFile().replaceFirst("/", "");
                // System.out.println("url = "+url);

                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] urlmd5 = md.digest(urlimg.getBytes());
                final String result = new String(HexBin.encode(urlmd5)) + ".jpeg";
                upload(file, result);
                imgname = urlimg;
                affiche.setText(imgname);

                //    System.out.println("url md5 = "+result);

            }
        }

        @FXML
        private void jButton_SelectImageActionPerformed(java.awt.event.ActionEvent evt) {                                                    
        // select an image and set the image path into a jlabel
        String path = null;
        
        JFileChooser chooser = new JFileChooser();
        
        chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        
        // file extension
        FileNameExtensionFilter extension = new FileNameExtensionFilter("*.Images","jpg","png","jpeg");
        chooser.addChoosableFileFilter(extension);
        
        int filestate = chooser.showSaveDialog(null);
         
        // check if the user select an image
        if(filestate == JFileChooser.APPROVE_OPTION){
            
            File selectedImage = chooser.getSelectedFile();
            path = selectedImage.getAbsolutePath();
            //jLabel_imgpath.setText(path);
            
            //image_path = path;
        }
        
    }  
    
    
 

 
     @FXML
    private void Annuler(ActionEvent event) throws IOException {
        
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("./GUI/ListeEmploye.fxml"));
       
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("ListeFilm.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
    }
    

 
    
}

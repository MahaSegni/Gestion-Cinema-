/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import Entities.Cat;
import Entities.Cinema;
import Entities.Equipement;
import Entities.Reclamation;
import Service.ServiceCat;
import Service.ServiceCinema;
import Service.ServiceEquipement;
import Service.ServiceReclamation;
import Utils.MaConnexion;
import javafx.scene.Node;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class EquipemetController implements Initializable {

       @FXML
    private Button stat;  
    @FXML
    private TextField rechart;
    @FXML
    private TextField lib;
    
    @FXML
    private TableView<Equipement> tab_art;
    @FXML
    private TableColumn<Equipement, Integer> col_ref;
    @FXML
    private TableColumn<Equipement, String> col_lib;
    @FXML
    private TableColumn<Equipement, Integer> col_cat;
    @FXML
    private TableColumn<Equipement, Integer> col_cin;
    @FXML
    private TableColumn<Equipement, Integer> col_prix;
    @FXML
    private TableColumn<Equipement, String> col_date;
    @FXML
    private Button suppart;

    @FXML
    private Button triprix;
     @FXML
    private Button correction;
    @FXML
    private Button modifart;
    @FXML
    private Button ajoutart;
    @FXML
    private Label label;
    @FXML
    private ImageView imgview;
    @FXML
    private TextField ref;
    
    @FXML
    private TextField qt;
    @FXML
    private TextField prix;
    @FXML
    private ComboBox cinema;
    @FXML
    private ComboBox resto;
    @FXML
    private Button annulerart;
       @FXML
    private Button co;
    @FXML
    private RadioButton radioajout;
    @FXML
    private ToggleGroup arwa;
    @FXML
    private RadioButton radiomodif;
    @FXML
    private RadioButton radioconsult;
    private Integer index = -1;
    ObservableList<Equipement> listearticle;
  private Connection cnx = MaConnexion.getInstance().getConnection();
    SimpleDateFormat formater = null;
    java.util.Date aujourdhui = new java.util.Date();
ServiceEquipement sa = new ServiceEquipement();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        /* ObservableList<Integer> listcinema = FXCollections.observableArrayList();
            String req = "select id from cinema";
            PreparedStatement pst = null;
            try {
                pst = cnx.prepareStatement(req);
            } catch (SQLException ex) {
                Logger.getLogger(AddrecController.class.getName()).log(Level.SEVERE, null, ex);
            }
            ResultSet rs = null;
            try {
                rs = pst.executeQuery();
            } catch (SQLException ex) {
                Logger.getLogger(AddrecController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                while (rs.next()) {
                    listcinema.add(rs.getInt("id"));
                  
                }
            } catch (SQLException ex) {
                Logger.getLogger(AddrecController.class.getName()).log(Level.SEVERE, null, ex);
            }
            cinema.setItems(listcinema);*/
            
               ServiceCat sa1 = new ServiceCat();
      ArrayList<Cat> arrayList =  (ArrayList) sa1.getAll();
          for (int i=0;i<arrayList.size();i++){
               resto.getItems().addAll(arrayList.get(i).getNomc());
          }  
              
               ServiceCinema sa2 = new ServiceCinema();
      ArrayList<Cinema> arrayList2 =  (ArrayList) sa2.afficher();
              //le maw ena 3amlitha array
          for (int i=0;i<arrayList2.size();i++){
               cinema.getItems().addAll(arrayList2.get(i).getNum());
          } 
        
        col_ref.setCellValueFactory(new PropertyValueFactory<>("ref"));
        col_lib.setCellValueFactory(new PropertyValueFactory<>("nome"));
        col_cat.setCellValueFactory(new PropertyValueFactory<>("resto_id"));
        col_cin.setCellValueFactory(new PropertyValueFactory<>("cinema_id"));
        col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("dates"));
          ServiceEquipement sa = new ServiceEquipement();
        ObservableList<Equipement> list = sa.afficher();
        tab_art.setItems(list);

    }
 @FXML
    void getSelected(MouseEvent event) {

       if (suppart.isPressed()) {
            index = tab_art.getSelectionModel().getSelectedIndex();
            if (index <= -1) {

                return;
            }
            
             
            ref.setText(col_ref.getCellData(index).toString());
            lib.setText(col_lib.getCellData(index).toString());
            cinema.setPromptText(col_cin.getCellData(index).toString());
            resto.setPromptText(col_cat.getCellData(index).toString());
            prix.setText(String.valueOf(col_prix.getCellData(index)));

        } else {
             label.setText("Consultation ");
        label.setLayoutX(100);
            index = tab_art.getSelectionModel().getSelectedIndex();
            if (index <= -1) {

                return;
            }
         

             
            ref.setText(col_ref.getCellData(index).toString());
            lib.setText(col_lib.getCellData(index).toString());
            cinema.setPromptText(col_cin.getCellData(index).toString());
            resto.setPromptText(col_cat.getCellData(index).toString());
           prix.setText(String.valueOf(col_prix.getCellData(index)));

        }

    }
    
    @FXML
    private void ajouterequi(ActionEvent e) {
        if (e.getSource() == ajoutart) {
           
           
         Equipement v = new Equipement();    
        v.setRef(Integer.valueOf(ref.getText()));
        v.setNome(lib.getText());
         v.setPrix(Integer.valueOf(prix.getText()));
        Timestamp time= new Timestamp(System.currentTimeMillis());
        v.setDates(time);
        System.out.println(time); 
        String fil = resto.getValue().toString() ;
                int resto_id = sa.getcatid(fil);
            sa.getcatid((String) resto.getValue());
        v.setResto_id(resto_id);
         int fil2 = Integer.valueOf(cinema.getValue().toString()) ;
                int cinema_id = sa.getcin(fil2);
            sa.getcin((int) cinema.getValue());
        v.setCinema_id(cinema_id);
        //v.setCinema_id(Integer.valueOf(cinema.getSelectionModel().getSelectedItem().toString()));
         new   ServiceEquipement().ajouter(v);
        
             JOptionPane.showMessageDialog(null, "Equipement Ajoutée");
             
         col_ref.setCellValueFactory(new PropertyValueFactory<>("ref"));
         col_lib.setCellValueFactory(new PropertyValueFactory<>("nome"));
        col_cat.setCellValueFactory(new PropertyValueFactory<>("resto_id"));
        col_cin.setCellValueFactory(new PropertyValueFactory<>("cinema_id"));
        col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("dates"));
         ObservableList<Equipement> list = sa.afficher();
            tab_art.setItems(list);
            ref.setText(null);
            lib.setText(null);
            cinema.setPromptText(null);
            resto.setPromptText(null);
            prix.setText(null);
       
        }
    }
    @FXML
    private void supprimerequi(ActionEvent e) {
        if (e.getSource() == suppart) {
            
            
            sa.supprimer(new Equipement(lib.getText()));
            JOptionPane.showMessageDialog(null, "Equipement Supprimée");
           
          col_ref.setCellValueFactory(new PropertyValueFactory<>("ref"));
         col_lib.setCellValueFactory(new PropertyValueFactory<>("nome"));
        col_cat.setCellValueFactory(new PropertyValueFactory<>("resto_id"));
        col_cin.setCellValueFactory(new PropertyValueFactory<>("cinema_id"));
        col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("dates"));
         ObservableList<Equipement> list2 = sa.afficher();
            tab_art.setItems(list2);
            ref.setText(null);
            lib.setText(null);
            cinema.setPromptText(null);
            resto.setPromptText(null);
            prix.setText(null);
            

        }
    }
      
     @FXML
        private void modifierarticle(javafx.event.ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == modifart) {
           ServiceEquipement sa = new ServiceEquipement();
         Equipement v = new Equipement();    
        v.setRef(Integer.valueOf(ref.getText()));
        v.setNome(lib.getText());
         v.setPrix(Double.parseDouble(prix.getText()));
       
         new   ServiceEquipement().modifier(v);
      JOptionPane.showMessageDialog(null, "Equipement Modifiée");
             col_ref.setCellValueFactory(new PropertyValueFactory<>("ref"));
             col_lib.setCellValueFactory(new PropertyValueFactory<>("nome"));
            col_cat.setCellValueFactory(new PropertyValueFactory<>("resto_id"));
            col_cin.setCellValueFactory(new PropertyValueFactory<>("cinema_id"));
            col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
           col_date.setCellValueFactory(new PropertyValueFactory<>("dates"));
            ObservableList<Equipement> modif = sa.afficher();
            tab_art.setItems(modif);
            ref.setText(null);
            lib.setText(null);
            cinema.setPromptText(null);
            resto.setPromptText(null);
            prix.setText(null);
        }
    }
      
    @FXML
        private void trier(javafx.event.ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == triprix) {
            ServiceEquipement sa = new ServiceEquipement();
            col_ref.setCellValueFactory(new PropertyValueFactory<>("ref"));
             col_lib.setCellValueFactory(new PropertyValueFactory<>("nome"));
            col_cat.setCellValueFactory(new PropertyValueFactory<>("resto_id"));
            col_cin.setCellValueFactory(new PropertyValueFactory<>("cinema_id"));
            col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
           col_date.setCellValueFactory(new PropertyValueFactory<>("dates"));
           ObservableList<Equipement> data = sa.Trierpardate();
            tab_art.setItems(data);
        }
    }
         @FXML
    private void rechrec() {
    
         
            ServiceEquipement sa = new ServiceEquipement();
            col_ref.setCellValueFactory(new PropertyValueFactory<>("ref"));
             col_lib.setCellValueFactory(new PropertyValueFactory<>("nome"));
            col_cat.setCellValueFactory(new PropertyValueFactory<>("resto_id"));
            col_cin.setCellValueFactory(new PropertyValueFactory<>("cinema_id"));
            col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
           col_date.setCellValueFactory(new PropertyValueFactory<>("dates"));
           ObservableList<Equipement> data = sa.afficher();
            FilteredList<Equipement> filteredData = new FilteredList<>(data, b -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        rechart.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate((Equipement Equipement) -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (Equipement.getNome().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches libelle
           
                } else if (String.valueOf(Equipement.getPrix()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false; // Does not match.
                }
            });
        });
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Equipement> sortedData = new SortedList<>(filteredData);
        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tab_art.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tab_art.setItems(sortedData);
           
        }
     @FXML
        private void statistique(javafx.event.ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == stat) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/GUI/stat.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("SurTerrain");
                stage.show();

            

} catch (IOException ex) {
               
            }
        }

    }
     @FXML
        private void co(javafx.event.ActionEvent mouseEvent) {
        if (mouseEvent.getSource() ==co) {
            try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/FXMLDocument.fxml"));
             Stage stage = new Stage();
            stage.setTitle("Correcteur Orthographique");
            stage.getIcons().add(new Image("/GUI/icons/icon.png"));
            Scene scene1 = new Scene(root);
            scene1.setFill(Color.TRANSPARENT);
            stage.setScene(scene1);
            stage.show();
        } catch (IOException ex) {
           
        }
            
            
        }
        
    }
           @FXML
    private void menu(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();

    }
    
}

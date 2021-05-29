/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static java.awt.SystemColor.control;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import javax.swing.JOptionPane;
import Entities.Reclamation;
import Service.ServiceReclamation;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class AllrecController implements Initializable {

     @FXML
    private TableView<Reclamation> tablev;
    @FXML
    private TextField rechart;
    
    @FXML
    private TableColumn<Reclamation, String> coldatee;
 
    @FXML
    private TableColumn<Reclamation, String> coldescription;
    @FXML
    private TableColumn<Reclamation, String> colrate;
    @FXML
    private TableColumn<Reclamation, String> colfield;
  
     @FXML
    private TableColumn<Reclamation, Integer> colabonne_id;
       @FXML
    private Button suppart;
           private Integer index = -1;
  @FXML
    private TextArea description;
    @FXML
    private TextArea abonne_id;
    @FXML
    private TextArea field;
 
     @FXML
    private Button triprix;
      @FXML
    private Button stat;
    @FXML
    private Label articleanim;
    @FXML
    private ImageView imagearticle;
    @FXML
    private Label error;
    @FXML
    private Button menu;
    @FXML
    private Button recherche;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        colabonne_id.setCellValueFactory(new PropertyValueFactory<>("abonne_id"));
        coldatee.setCellValueFactory(new PropertyValueFactory<>("datee"));
        coldescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colrate.setCellValueFactory(new PropertyValueFactory<>("rate"));
        colfield.setCellValueFactory(new PropertyValueFactory<>("field"));
        
        ServiceReclamation s = new ServiceReclamation();
        ObservableList<Reclamation> data = s.afficher();
        tablev.setItems(data);
    } 
     @FXML
    void getSelected(MouseEvent event) {

     

            index = tablev.getSelectionModel().getSelectedIndex();
            if (index <= -1) {

                return;
            }
          
         
            description.setText(coldescription.getCellData(index).toString());
            abonne_id.setText(colabonne_id.getCellData(index).toString());
            field.setText(colfield.getCellData(index).toString());
        
           
        
       

    }


    @FXML
    private void supprimeraticle(javafx.event.ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == suppart) {

            ServiceReclamation sa = new ServiceReclamation();
            sa.supprimer(new Reclamation(field.getText()));
            JOptionPane.showMessageDialog(null, "Reclamation Supprimée");
           
            colabonne_id.setCellValueFactory(new PropertyValueFactory<>("abonne_id"));
            coldatee.setCellValueFactory(new PropertyValueFactory<>("datee"));
            coldescription.setCellValueFactory(new PropertyValueFactory<>("description"));
            colrate.setCellValueFactory(new PropertyValueFactory<>("rate"));
            colfield.setCellValueFactory(new PropertyValueFactory<>("field"));
            ObservableList<Reclamation> data = sa.afficher();
            tablev.setItems(data);
         
            field.setText(null);
            description.setText(null);
            abonne_id.setText(null);
            

        }
    }
    
    @FXML
        private void trierarticleprix(javafx.event.ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == triprix) {
            ServiceReclamation sa = new ServiceReclamation();
            colabonne_id.setCellValueFactory(new PropertyValueFactory<>("abonne_id"));
            coldatee.setCellValueFactory(new PropertyValueFactory<>("datee"));
            coldescription.setCellValueFactory(new PropertyValueFactory<>("description"));
            colrate.setCellValueFactory(new PropertyValueFactory<>("rate"));
            colfield.setCellValueFactory(new PropertyValueFactory<>("field"));
           ObservableList<Reclamation> data = sa.TrierparDate();
            tablev.setItems(data);
        }
    }
   @FXML
    private void rechrec() {
    
         
            ServiceReclamation sa = new ServiceReclamation();
            colabonne_id.setCellValueFactory(new PropertyValueFactory<>("abonne_id"));
            coldatee.setCellValueFactory(new PropertyValueFactory<>("datee"));
            coldescription.setCellValueFactory(new PropertyValueFactory<>("description"));
            colrate.setCellValueFactory(new PropertyValueFactory<>("rate"));
            colfield.setCellValueFactory(new PropertyValueFactory<>("field"));
           ObservableList<Reclamation> data = sa.afficher();
            FilteredList<Reclamation> filteredData = new FilteredList<>(data, b -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        rechart.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate((Reclamation Reclamation) -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (Reclamation.getField().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches libelle
                } else if (Reclamation.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (String.valueOf(Reclamation.getRate()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false; // Does not match.
                }
            });
        });
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Reclamation> sortedData = new SortedList<>(filteredData);
        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tablev.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tablev.setItems(sortedData);
           
        }
    
  @FXML
        private void statistique(javafx.event.ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == stat) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/GUI/stat.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("HappyPark");
                stage.show();

            

} catch (IOException ex) {
                Logger.getLogger(AllrecController.class
.getName()).log(Level.SEVERE, null, ex);
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
      @FXML
    private void recherche(ActionEvent event) {
//        if (event.getSource() == recherche) {
//          ServiceReclamation sa = new ServiceReclamation();
//            colabonne_id.setCellValueFactory(new PropertyValueFactory<>("abonne_id"));
//            coldatee.setCellValueFactory(new PropertyValueFactory<>("datee"));
//            coldescription.setCellValueFactory(new PropertyValueFactory<>("description"));
//            colrate.setCellValueFactory(new PropertyValueFactory<>("rate"));
//            colfield.setCellValueFactory(new PropertyValueFactory<>("field"));
//          
//           sa.rechercher(rechart.getText());
//            ObservableList<Reclamation> data = sa.afficher();
//        }

        ServiceReclamation cs = new ServiceReclamation();
        ObservableList<Reclamation> commandesObs = FXCollections.observableArrayList(cs.rechercher((rechart.getText())));
        tablev.setItems(commandesObs);
    }
}

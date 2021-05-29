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
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import models.Cellule;
import org.controlsfx.control.Notifications;
import Service.ServiceCellule;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Administrateur
 */
public class GererCelluleController implements Initializable {

    @FXML
    private TextField nomCell;
    @FXML
    private ComboBox Dispo;
    @FXML
    private Button ajCel;
    @FXML
    private Button modCel;
    @FXML
    private Button supCel;
    @FXML
    private TextField recell;
    @FXML
    private Button rechCell;
     @FXML
    private TableView<Cellule> listeCel;
      @FXML
    private TableColumn<Cellule, String> colNomCel;
       @FXML
    private TableColumn<Cellule, Boolean> colDispo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Dispo.getItems().addAll(
                    "true",
                    "false"
            );
        colNomCel.setCellValueFactory(new PropertyValueFactory<>("idCellule"));
        colDispo.setCellValueFactory(new PropertyValueFactory<>("dispo"));
        
        ServiceCellule s = new ServiceCellule();
        ObservableList<Cellule> data = s.afficher();
        listeCel.setItems(data);
    }    

    @FXML
    private void ajouterCellule(ActionEvent event) {
       ServiceCellule sc = new ServiceCellule();
       String s = "";
        if (nomCell.getText().trim().isEmpty() || Dispo.getSelectionModel().getSelectedItem().toString().trim().isEmpty()) {
            Notifications notificationBuilder =Notifications.create()
                    .title("Ajouter nouvelle cellule")
                    .text("les champs ne doivent pas etre vides ")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT);
            
            notificationBuilder.darkStyle();
            notificationBuilder.show();
        } else {
            if(Dispo.getSelectionModel().getSelectedItem().toString()=="true"){
            
            sc.ajouter(new Cellule(nomCell.getText(),true));
             }
            else{sc.ajouter(new Cellule(nomCell.getText(),false));
                 }
            Notifications notificationBuilder =Notifications.create()
                    .title("Ajouter nouvelle cellule")
                    .text("cellule ajoutée")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT);
            
            notificationBuilder.darkStyle();
            notificationBuilder.show();
            colNomCel.setCellValueFactory(new PropertyValueFactory<>("idCellule"));
         colDispo.setCellValueFactory(new PropertyValueFactory<>("dispo"));
       
            ObservableList<Cellule> list2 = sc.afficher();
            listeCel.setItems(list2);
            nomCell.setText(null);
            Dispo.setPromptText(null);
            //JOptionPane.showMessageDialog(null, "offre ajoutée");
        }
      // JoptionPane.showMessageDialog
    }



    @FXML
    private void supprimerCellule(ActionEvent event) {
            ServiceCellule sc = new ServiceCellule();
            String s = "";
       

            sc.supprimer(nomCell.getText());
            Notifications notificationBuilder =Notifications.create()
                    .title("supprimer une cellule")
                    .text("cellule supprimée")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT);
            
            notificationBuilder.darkStyle();
            notificationBuilder.show();
            
               colNomCel.setCellValueFactory(new PropertyValueFactory<>("idCellule"));
         colDispo.setCellValueFactory(new PropertyValueFactory<>("dispo"));
       
            ObservableList<Cellule> list2 = sc.afficher();
            listeCel.setItems(list2);
            nomCell.setText(null);
            Dispo.setPromptText(null);
           
    
    }

    @FXML
    private void rechercherCellule(ActionEvent event) throws SQLException {
        
        colNomCel.setCellValueFactory(new PropertyValueFactory<>("idCellule"));
        colDispo.setCellValueFactory(new PropertyValueFactory<>("dispo"));
        String d = recell.getText();
        ServiceCellule s = new ServiceCellule();
        ObservableList<Cellule> data = s.rechercherPardispo(d);
        listeCel.setItems(data);
        
    }
    
    
    
    
    
     private Connection cnn;

    private final String URL ="jdbc:mysql://localhost:3306/happy?user=root&password=Pass&useUnicode=true&characterEncoding=UTF-8";
    private final String LOGIN = "root";
    private final String PASSWORD = "";

    private Connection getConnection() {
        try {
            cnn = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            System.out.println("Conncting !");
            return cnn;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }

  public ObservableList<Cellule> getCelluleListe() throws SQLException{
        ObservableList<Cellule> CelluleListe = FXCollections.observableArrayList();
       Connection conn = getConnection(); 
       String query = "Select * from cellule" ;
       Statement st ;
       ResultSet rs ;
       
       try {
           st= conn.createStatement();
           rs= st.executeQuery(query);
           Cellule cellule ;
           while (rs.next()){
               cellule = new Cellule(rs.getString("idCellule") , rs.getBoolean("dispo"));
           }
       }
       catch (Exception ex)
       {
           ex.printStackTrace();
       }
       
        return CelluleListe ;
    }
    
    @FXML
    void getSelected(MouseEvent event) {

      
        int index = listeCel.getSelectionModel().getSelectedIndex();
            if (index <= -1) {

                return;
            }
            
             
            nomCell.setText(colNomCel.getCellData(index).toString());
            Dispo.setPromptText(colDispo.getCellData(index).toString());
      

        

    }
     @FXML
    private void Menu(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();

    }
   
    
     
    
    
    

   
}

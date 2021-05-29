/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import models.Cellule;
import models.Reservation;
import org.controlsfx.control.Notifications;
import Service.ServiceCellule;
import Service.ServiceReservation;
import Utils.MaConnexion;
import java.io.IOException;
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
public class ReservationController implements Initializable {

    @FXML
    private TableView<Reservation> tabRes;
    @FXML
    private TableColumn<Reservation, String> matRes;
    @FXML
    private TableColumn<Reservation, Date> dateDeb;
    @FXML
    private TableColumn<Reservation, Date> dateFin;
    @FXML
    private TableColumn<Reservation, String> plRes;
    @FXML
    private Button suppRes;
    @FXML
    private TextField matricule;
    @FXML
    private DatePicker dateD;
    @FXML
    private DatePicker dateF;
    @FXML
    private ComboBox<Reservation> place;
    @FXML
    private DatePicker recRes1;
    @FXML
    private DatePicker recRes2;
    @FXML
    private Button rechRes;
    @FXML
    private Button triRes;
    private Connection cnx = MaConnexion.getInstance().getConnection();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        matRes.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        dateDeb.setCellValueFactory(new PropertyValueFactory<>("dateD"));
        dateFin.setCellValueFactory(new PropertyValueFactory<>("dateF"));
        plRes.setCellValueFactory(new PropertyValueFactory<>("idCell"));

        
        ServiceReservation s = new ServiceReservation();
        ObservableList<Reservation> data = s.afficher();
        tabRes.setItems(data);
    }    

    @FXML
    private void getSelected(MouseEvent event) {
         int index = tabRes.getSelectionModel().getSelectedIndex();
            if (index <= -1) {

                return;
            }
            
             
            matricule.setText(matRes.getCellData(index));
            dateD.setPromptText(dateDeb.getCellData(index).toString());
            dateF.setPromptText(dateFin.getCellData(index).toString());
            place.setPromptText(plRes.getCellData(index));

        
    }

    @FXML
    private void supprimerReservation(ActionEvent event) throws SQLException {
        ServiceReservation sc = new ServiceReservation();
          ServiceCellule sa = new ServiceCellule();
            Reservation v = new Reservation();  
            
            /*  ObservableList<Cellule> arrayList =  (ObservableList) sa.afficher();
                
          for (int i=0;i<arrayList.size();i++){
             String f;
              f = place.getValue().toString() ;
              if((arrayList.get(i).getIdCellule()).equals(f) ){
       
             String sql = "update cellule set dispo =true  WHERE idCellule = '"+f+"' ";
              PreparedStatement pst = cnx.prepareStatement(sql);
              pst.executeUpdate();
              
            
              }
            
          } */
            
            String b = matricule.getText();
            sc.supprimer(b);
        
 
        // sc.supprimer(new Reservation(matricule.getText(), Boolean.valueOf(Dispo.getSelectionModel().getSelectedItem().toString())));
            Notifications notificationBuilder =Notifications.create()
                    .title("supprimer une réservation")
                    .text("réservation annulée")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT);
            
            notificationBuilder.darkStyle();
            notificationBuilder.show();
            
               matRes.setCellValueFactory(new PropertyValueFactory<>("matricule"));
               dateDeb.setCellValueFactory(new PropertyValueFactory<>("dateD"));
                dateFin.setCellValueFactory(new PropertyValueFactory<>("dateF"));
                plRes.setCellValueFactory(new PropertyValueFactory<>("idCell"));
            ObservableList<Reservation> list2 = sc.afficher();
            tabRes.setItems(list2);
            matricule.setText(null);
            place.setPromptText(null);
            dateD.setPromptText(null);
            dateF.setPromptText(null);
            
          


            
    }

    @FXML
    private void rechercherReservation(ActionEvent event) throws SQLException {
         matRes.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        dateDeb.setCellValueFactory(new PropertyValueFactory<>("dateD"));
         dateFin.setCellValueFactory(new PropertyValueFactory<>("dateF"));
         plRes.setCellValueFactory(new PropertyValueFactory<>("idCell"));
        //String d = recRes.getText();
        java.sql.Date gettedDatePickerDate1 = java.sql.Date.valueOf(recRes1.getValue());
        java.sql.Date gettedDatePickerDate2 = java.sql.Date.valueOf(recRes2.getValue());
        String d1= gettedDatePickerDate1.toString();
        String d2= gettedDatePickerDate2.toString();
        ServiceReservation s = new ServiceReservation();
        ObservableList<Reservation> data = s.rechercherParDate(d1,d2);
        tabRes.setItems(data);
        
    }

    @FXML
    private void trierReservation(ActionEvent event) {
        matRes.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        dateDeb.setCellValueFactory(new PropertyValueFactory<>("dateD"));
         dateFin.setCellValueFactory(new PropertyValueFactory<>("dateF"));
         plRes.setCellValueFactory(new PropertyValueFactory<>("idCell"));
        ServiceReservation s = new ServiceReservation();
        ObservableList<Reservation> data = s.trie();
        tabRes.setItems(data);
    }
    
    @FXML
    private void Menu(ActionEvent event) throws IOException {
        
           Parent tableViewParent = FXMLLoader.load(getClass().getResource("../GUI/Menu.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
    } 
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Rectangle;
import models.Cellule;
import Service.ServiceCalendar;
import Service.ServiceCellule;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import models.Calendar;
import models.Reservation;
import org.controlsfx.control.Notifications;
import Service.ServiceReservation;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import pidevjava2.MainCalendar;


/**
 * FXML Controller class
 *
 * @author Administrateur
 */
public class GererCalendarController implements Initializable {

    @FXML
    private DatePicker datedCal;
    @FXML
    private TextField titreCal;
    @FXML
    private DatePicker datefCal;
    @FXML
    private TextField descCal;
    
    @FXML
    private Button ajtCal;
    @FXML
    private Button modCal;
    @FXML
    private Button suppCal;
    @FXML
    private TableView<Calendar> listeCalendrier;
    @FXML
    private TableColumn<Calendar, String> titre;
    @FXML
    private TableColumn<Calendar, Date> dateDebut;
    @FXML
    private TableColumn<Calendar, Date> dateFin;
    @FXML
    private TableColumn<Calendar, String> description;
    @FXML
    private Button voirCal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        dateFin.setCellValueFactory(new PropertyValueFactory<>("end"));
        dateDebut.setCellValueFactory(new PropertyValueFactory<>("start"));
        titre.setCellValueFactory(new PropertyValueFactory<>("title"));
        
        ServiceCalendar s = new ServiceCalendar();
        ObservableList<Calendar> data = s.afficher();
        listeCalendrier.setItems(data);
       
    }    

    @FXML
    private void ajouterCalendrier(ActionEvent event) {
        ServiceCalendar sc = new ServiceCalendar();
       String s = "";
        if (titreCal.getText().trim().isEmpty() || descCal.getText().trim().isEmpty()) {
            Notifications notificationBuilder =Notifications.create()
                    .title("Ajouter nouveau rendez-vous")
                    .text("les champs ne doivent pas etre vides ")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT);
            
            notificationBuilder.darkStyle();
            notificationBuilder.show();
        } else {
            if( datevalid(event)==0){ 

         java.sql.Date gettedDatePickerDate1 = java.sql.Date.valueOf(datedCal.getValue());
         java.sql.Date gettedDatePickerDate2 = java.sql.Date.valueOf(datefCal.getValue());
            sc.ajouter(new Calendar(titreCal.getText() ,descCal.getText() ,gettedDatePickerDate1 ,gettedDatePickerDate2 ));
            Notifications notificationBuilder =Notifications.create() 
                    .title("Ajouter nouveau rendez-vous")
                    .text("rendez-vous ajouté")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT);
            
            notificationBuilder.darkStyle();
            notificationBuilder.show();
            
               titre.setCellValueFactory(new PropertyValueFactory<>("title"));
               dateDebut.setCellValueFactory(new PropertyValueFactory<>("start"));
                dateFin.setCellValueFactory(new PropertyValueFactory<>("end"));
                description.setCellValueFactory(new PropertyValueFactory<>("description"));
            ObservableList<Calendar> list2 = sc.afficher();
            listeCalendrier.setItems(list2);
            titreCal.setText(null);
            descCal.setText(null);
            datedCal.setPromptText(null);
            datefCal.setPromptText(null);
            //JOptionPane.showMessageDialog(null, "offre ajoutée");
        }
        }
    }

    @FXML
    private void modifierCalendrier(ActionEvent event) {
        ServiceCalendar sa = new ServiceCalendar();
         java.sql.Date gettedDatePickerDate1 = java.sql.Date.valueOf(datedCal.getValue());
         java.sql.Date gettedDatePickerDate2 = java.sql.Date.valueOf(datefCal.getValue());
            sa.modifier(new Calendar(titreCal.getText(), descCal.getText(), gettedDatePickerDate1, gettedDatePickerDate2));
            JOptionPane.showMessageDialog(null, "Rendez-vous Modifié");
            ObservableList<String> list1 = FXCollections.observableArrayList();
       titre.setCellValueFactory(new PropertyValueFactory<>("title"));
               dateDebut.setCellValueFactory(new PropertyValueFactory<>("start"));
                dateFin.setCellValueFactory(new PropertyValueFactory<>("end"));
                description.setCellValueFactory(new PropertyValueFactory<>("description"));
            ObservableList<Calendar> list2 = sa.afficher();
            listeCalendrier.setItems(list2);
            titreCal.setText(null);
            descCal.setText(null);
            datedCal.setPromptText(null);
            datefCal.setPromptText(null);
    }

    @FXML
    private void supprimerCalendrier(ActionEvent event) {
        
          ServiceCalendar sc = new ServiceCalendar(); 
            sc.supprimer(titreCal.getText());
        
            Notifications notificationBuilder =Notifications.create()
                    .title("supprimer une réservation")
                    .text("réservation annulée")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT);
            
            notificationBuilder.darkStyle();
            notificationBuilder.show();
            
               titre.setCellValueFactory(new PropertyValueFactory<>("title"));
               dateDebut.setCellValueFactory(new PropertyValueFactory<>("start"));
                dateFin.setCellValueFactory(new PropertyValueFactory<>("end"));
                description.setCellValueFactory(new PropertyValueFactory<>("description"));
            ObservableList<Calendar> list2 = sc.afficher();
            listeCalendrier.setItems(list2);
            titreCal.setText(null);
            descCal.setText(null);
            datedCal.setPromptText(null);
            datefCal.setPromptText(null);
    }

    @FXML
    private void showAgenda(ActionEvent event) {
    }
    
    @FXML
    void getSelected(MouseEvent event) {

      
        int index = listeCalendrier.getSelectionModel().getSelectedIndex();
            if (index <= -1) {

                return;
            }
            
             
            titreCal.setText(titre.getCellData(index));
            descCal.setText(description.getCellData(index));
             datedCal.setPromptText(dateDebut.getCellData(index).toString());
            datefCal.setPromptText(dateFin.getCellData(index).toString());
      

        

    }
   
    
     @FXML
    private void planing_seance(ActionEvent event) {
            Stage s = new Stage();
            s.initModality(Modality.APPLICATION_MODAL);
            new MainCalendar().start(s);
    }
    
     @FXML
        private int datevalid(ActionEvent event) {
             java.sql.Date gettedDatePickerDate1 = java.sql.Date.valueOf(datedCal.getValue());
         java.sql.Date gettedDatePickerDate2 = java.sql.Date.valueOf(datefCal.getValue());
        if(gettedDatePickerDate1.compareTo(gettedDatePickerDate2)>0){
            datefCal.setStyle("-fx-border-color:red ; -fx-border-width:2px;");
            new animatefx.animation.Shake(datefCal).play();
           Notifications notificationBuilder =Notifications.create()
                    
                    .text("La date d'entrée doit etre inférieure à la date de sortie")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT);
            
            notificationBuilder.darkStyle();
            notificationBuilder.show();
            return 1 ;

        }
        else return 0 ;

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

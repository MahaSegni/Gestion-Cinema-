/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static com.sun.media.jfxmediaimpl.MediaUtils.error;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.sym.error;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Properties;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import models.Cellule;
import models.Reservation;
import org.controlsfx.control.Notifications;
import static org.controlsfx.validation.ValidationMessage.error;
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
public class AjouterReservationController implements Initializable {

    @FXML
    private DatePicker dateD;
    @FXML
    private TextField matricule;
    @FXML
    private DatePicker dateF;
    @FXML
    private Button btnRes;
    @FXML
    private ComboBox<String> place;
    private Connection cnx = MaConnexion.getInstance().getConnection();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> listCellule = FXCollections.observableArrayList();
            String req = "select idCellule from cellule where dispo=true";
            PreparedStatement pst = null;
            try {
                pst = cnx.prepareStatement(req);
            } catch (SQLException ex) {
                Logger.getLogger(AjouterReservationController.class.getName()).log(Level.SEVERE, null, ex);
            }
            ResultSet rs = null;
            try {
                rs = pst.executeQuery();
            } catch (SQLException ex) {
                Logger.getLogger(AjouterReservationController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                while (rs.next()) {
                    listCellule.add(rs.getString("idCellule"));
                  
                }
            } catch (SQLException ex) {
                Logger.getLogger(AjouterReservationController.class.getName()).log(Level.SEVERE, null, ex);
            }
            place.setItems(listCellule);
        // TODO
     
    }    

    @FXML
    private void ajouterReservation(ActionEvent event) throws SQLException, MessagingException {
 
         
        ServiceReservation sr = new ServiceReservation();
            ServiceCellule sa = new ServiceCellule();
             
        Reservation v = new Reservation(); 
       
       
        if (matricule.getText().trim().isEmpty() ) {
            dateF.setStyle("-fx-border-color:red ; -fx-border-width:2px;");
            dateD.setStyle("-fx-border-color:red ; -fx-border-width:2px;");
            matricule.setStyle("-fx-border-color:red ; -fx-border-width:2px;");
            place.setStyle("-fx-border-color:red ; -fx-border-width:2px;");
            new animatefx.animation.Shake(dateF).play();
             new animatefx.animation.Shake(dateD).play();
              new animatefx.animation.Shake(matricule).play();
               new animatefx.animation.Shake(place).play();
            
            Notifications notificationBuilder =Notifications.create()
                    .title("Ajouter nouvelle réservation")
                    .text("les champs ne doivent pas etre vides ")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT);
            
            notificationBuilder.darkStyle();
            notificationBuilder.show();
        } else {
            
           if( datevalid(event)==0){ 
               
               
         java.sql.Date gettedDatePickerDate1 = java.sql.Date.valueOf(dateD.getValue());
         java.sql.Date gettedDatePickerDate2 = java.sql.Date.valueOf(dateF.getValue());
         //datevalid(event);
        
        v.setMatricule(matricule.getText());
        v.setDateD(gettedDatePickerDate1);
        v.setDateF(gettedDatePickerDate2);   
        v.setIdCell(place.getSelectionModel().getSelectedItem());
        new   ServiceReservation().ajouter(v);
    
            
        
      ObservableList<Cellule> arrayList =  (ObservableList) sa.afficher();
          for (int i=0;i<arrayList.size();i++){
               String fil = place.getSelectionModel().getSelectedItem() ;
              if((arrayList.get(i).getIdCellule()).equals(fil) ){
       
             String sql = "update cellule set dispo =false  WHERE idCellule = '"+fil+"' ";
              PreparedStatement pst = cnx.prepareStatement(sql);
              pst.executeUpdate();
              
            
              }
            
          }
          
            String to = "Maha.segni@esprit.tn";//take user email address from database  ( get emailClient ) 
        final String user = "Maha.segni@esprit.tn";//save it like this   
        final String password = "xvxpjdjklxhwsxpf";//pass of user  

        //1) get the session object     
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getDefaultInstance(properties,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        //2) compose message     
        
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("[Happy PArk][IMPORTANT] Confirmation réservation parking"); 

            //3) create MimeBodyPart object and set your message text     
          //  BodyPart messageBodyPart1 = new MimeBodyPart(); 
            message.setText("Bonne Journée a vous. \n \n"
                    + "Vous avez effectué une réservation dans le parking de Happy Park de l'heure. "
                    +dateD.getValue().toString()
                    + "jusqu'à l'heure \n"
                    + dateF.getValue().toString()
                    +"dans la place"
                    +place.getSelectionModel().getSelectedItem()
                    + "Coordialement. "
                    );

           
            //7) send message  
            Transport.send(message);

            System.out.println("message sent....");
            
            
                Notifications notificationBuilder =Notifications.create()
                    .title("Ajouter nouvelle réservation")
                    .text("votre réservation est bien ajoutée , Merci pour votre confiance!")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT);
            
            notificationBuilder.darkStyle();
            notificationBuilder.show();
            
            matricule.setText(null);
            dateD.setPromptText(null);
            dateF.setPromptText(null);
            place.setPromptText(null);
            
            
            System.out.println("notif....");
        
            
           }
        }
      //  Cellule h= place.getValue();
      // h.

    }

    
        @FXML
        private int datevalid(ActionEvent event) {
             java.sql.Date gettedDatePickerDate1 = java.sql.Date.valueOf(dateD.getValue());
         java.sql.Date gettedDatePickerDate2 = java.sql.Date.valueOf(dateF.getValue());
        if(gettedDatePickerDate1.compareTo(gettedDatePickerDate2)>0){
            dateF.setStyle("-fx-border-color:red ; -fx-border-width:2px;");
            new animatefx.animation.Shake(dateF).play();
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
        
           Parent tableViewParent = FXMLLoader.load(getClass().getResource("../GUI/AcceuilAbonne.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
    }
 
    
}

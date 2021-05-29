/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.comment;
import Entities.event;
import GUI.FXMLDocumentController;
import Service.ServiceAbonne;
import Service.comment_service;
import Service.event_service;
import Utils.MaConnexion;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import doryan.windowsnotificationapi.fr.Notification;
import api.Mail;
import api.MailService;
import api.Sms;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mahas
 */
public class FrontController implements Initializable {

    @FXML
    private TableView<event> tableEvenement;
    @FXML
    private TableColumn<event, String> nom;
    
    ObservableList<event> eve =FXCollections.observableArrayList();
    ObservableList<event> sortedList = FXCollections.observableArrayList();
    @FXML
    private Label eve_nom;
    @FXML
    private Label eve_date;
    @FXML
    private Label eve_duree;
    @FXML
    private Label eve_fin;
    @FXML
    private Label eve_emp;
    @FXML
    private Label eve_place;
    
    public static event selectedEvent = null;
    @FXML
    private Button part;
    @FXML
    private Button quit;
    @FXML
    private ComboBox<String> combo_comment;
    @FXML
    private TextArea text_comment;
    @FXML
    private Label t;
    @FXML
    private TableView<comment> table_comment;
    @FXML
    private TableColumn<comment, String> col_comment;
    ObservableList<comment> com =FXCollections.observableArrayList();
    @FXML
    private DatePicker filter_start;
    @FXML
    private DatePicker filter_end;
    @FXML
    private Button btn_filter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        update_event();
        update_comment();
        combo_mod();
    }    

   public void refresh(){
    table_comment.getItems().clear();
    update_comment();
    }
    public void update_event()
    {
     try {
            
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            Connection conn = MaConnexion.getInstance().getConnection();
            PreparedStatement pt = conn.prepareStatement("select * from evenement");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {            
                eve.add(new event(
                        rs.getString("nomEvent"),
                        rs.getInt("dureeEvent"),
                        rs.getString("emplacement"),
                        rs.getInt("place"),
                        rs.getString("capacite"),
                        rs.getString("dateEvent"),
                        rs.getString("dateEventFin")
                        
                        
                ));
            }
              tableEvenement.setItems(eve);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onClick(MouseEvent event) {
        
        if (event.getClickCount() == 1) //Checking double click
        {

            selectedEvent = tableEvenement.getSelectionModel().getSelectedItem();

            eve_nom.setText(selectedEvent.getNom());
            eve_date.setText(selectedEvent.getDate_debut().toString());
            eve_fin.setText(selectedEvent.getDate_fin().toString());
            int duree = selectedEvent.getDuree();
            Integer y = new Integer(duree);
            eve_duree.setText(y.toString());
            
            eve_emp.setText(selectedEvent.getEmplacement());
            eve_place.setText(selectedEvent.getPlace().toString());
            
        }
    }
  

    @FXML
    private void onParticipe(ActionEvent event) {
        selectedEvent = tableEvenement.getSelectionModel().getSelectedItem();
        String nom = selectedEvent.getNom();
        ServiceAbonne us = new ServiceAbonne();
        String Nomevent = us.getNomEvent(5);
        System.out.println("......"+Nomevent);
        String x = "";
        if(Nomevent.equals(x)){
      
        
        try {
                Notification.sendNotification("module evennement", " Vous ne pouvez pas participer ",TrayIcon.MessageType.INFO);
            } catch (AWTException ex) {
                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{ 
            event_service e = new event_service();
            e.affecter(nom);
            int i=Integer.parseInt(eve_place.getText())-1; 
            eve_place.setText(String.valueOf(i));
             Sms sms = new Sms();
             sms.send();
             
        
        try {
            createPDF();
        } catch (DocumentException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
       
    }

    @FXML
    private void onquit(ActionEvent event) {
        event_service e = new event_service();
        ServiceAbonne us = new ServiceAbonne();
        String nom = us.getNomEvent(5);
        if(nom.length() != 0){
            e.quit(eve_nom.getText());
            int i=Integer.parseInt(eve_place.getText())+1;  
            eve_place.setText(String.valueOf(i));
        }
        else{ 
            try {
                Notification.sendNotification("module evennement", " participez a un evenment dabord ",TrayIcon.MessageType.INFO);
            } catch (AWTException ex) {
                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    private void createPDF() throws DocumentException, FileNotFoundException {
        Document my_pdf_report = new Document();
        //String rand = String.valueOf(generate());
        String pdfName = "c:/pdf/Evenement.pdf";
        PdfWriter.getInstance(my_pdf_report, new FileOutputStream(pdfName));
        my_pdf_report.open();
        my_pdf_report.addTitle("hello");
        selectedEvent = tableEvenement.getSelectionModel().getSelectedItem();
        String nom = selectedEvent.getNom();
        my_pdf_report.add(new Chunk("vous avez participer a l'évenement " + nom));
        my_pdf_report.close();

        sendMail(pdfName);
    }
    
    public void sendMail(String pdfName) {

        Mail mail = new Mail();

        mail.setSubject("Module Evenement");

        mail.setBody("Module Evenement");

        mail.setReciever("maha.segni@esprit.tn");

        mail.setFile(pdfName);
        MailService.doSend(mail);

    }
    private void combo_mod() {
        comment_service cs = new comment_service();
        List<String> arr= cs.affichagecombo();
        ObservableList<String> combo = FXCollections.observableArrayList(arr);
        combo_comment.getItems().clear();
        combo_comment.getItems().addAll(combo);
    }

    @FXML
    private void onAjout(ActionEvent event) {
        comment_service cs = new comment_service();
        event_service ev = new event_service();
        
        
        String nomEvent = combo_comment.getValue();
        int id= ev.getidevent(nomEvent);  
        String comment = text_comment.getText();
        cs.create(comment,id);
        combo_mod();
        text_comment.clear();
        combo_comment.setValue(null);
        refresh();
    }
    public void update_comment() {
       
        try {
            table_comment.getItems().clear();
            col_comment.setCellValueFactory(new PropertyValueFactory<>("content"));
            Connection conn = MaConnexion.getInstance().getConnection();
            PreparedStatement pt = conn.prepareStatement("select * from comment");
            ResultSet rs = pt.executeQuery();
            
            while (rs.next()) {
                
                com.add(new comment(
                        rs.getString("content")));
                
            }
            table_comment.setItems(com);
        } catch (SQLException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
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

    @FXML
    private void onFilter(ActionEvent event) {
        
         LocalDateTime start = filter_start.getValue().atStartOfDay();
        LocalDateTime end = filter_end.getValue().atStartOfDay();
        
        if(start == null & end== null){
            System.out.println("null variabels");
        }
        if (start == null & end!= null){
            System.out.println("enter start date");
        }
        if (start != null & end== null){
            System.out.println("enter end date");
        }
        if(start != null & end !=null){
            search(start, end);
        }
    }
    
    public void search( LocalDateTime start, LocalDateTime end) {

        event_service es = new event_service();
        List<event> list = es.Filter();
        List<event> sort = new ArrayList<>();
        
        for(int i = 0 ; i < list.size(); i++){
           
                String a = list.get(i).getDate_debut();
                DateTimeFormatter date1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
                LocalDateTime date2 = LocalDateTime.parse(a, date1);
                if(start.compareTo(date2) * date2.compareTo(end) >= 0){
                    System.out.println("compare with start"+ start.compareTo(date2));
                    sort.add(new event(list.get(i).getNom()));
                    System.out.println("nom : "+ list.get(i).getNom());
                }
        }
            sortedList.addAll(sort);
            tableEvenement.getItems().clear();
            tableEvenement.setItems(sortedList);


    }
}

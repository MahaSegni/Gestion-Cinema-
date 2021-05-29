
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import Entities.Employe;
import Entities.Conge;
import Entities.EmployeAffi;
import Service.ServiceConge;
import java.io.FileNotFoundException;
import Service.ServiceEmploye;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import Utils.MaConnexion;
import javafx.event.ActionEvent;
import Utils.PDF;
import Utils.PDF1;
import static java.lang.Thread.sleep;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.FormatStringConverter;
import utils.Mail;
import javafx.scene.control.TextField;
import javafx.beans.binding.Bindings;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ListeCongeController implements Initializable {

    @FXML
    private TableView<Conge> Listeconges;
    @FXML
    private TableColumn<Conge, Integer> idcon;
    @FXML
    private TableColumn<Conge, Date> date;
    @FXML
    private TableColumn<Conge, String> motif;
    @FXML
    private TableColumn<Conge, Integer> nbjour;
    @FXML
    private TableColumn<Conge, Integer> idempc;

    private final ObservableList<Conge> data = FXCollections.observableArrayList();
    ObservableList<String> list = FXCollections.observableArrayList("ok", "bb");

    private Statement ste;
    private Connection con;

    @FXML
    private Button ajouter;
    @FXML
    private Button supprimer;

    ServiceConge sp = new ServiceConge();
    @FXML
    private Button front;

    @FXML
    private Button reclamation;
    @FXML
    private Button trier;
    @FXML
    private Button pdf;
 @FXML
    private Button bt_menu;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afficher();
        //RechercheAV();
    }

    public void afficher() {

        /*id.setCellValueFactory (new PropertyValueFactory<>("idemp"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nomemp"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenomemp"));
        numtel.setCellValueFactory(new PropertyValueFactory<>("numtelemp"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresseemp"));
        
        ServiceEmploye s = new ServiceEmploye();
        ObservableList<Employe> data = (ObservableList<Employe>) s.afficher();
        ListeEmployes.setItems(data);*/
        List tabProd = new ArrayList();
        List tabNom = new ArrayList();

        try {
            Connection con = MaConnexion.getInstance().getConnection();
            Statement ste = con.createStatement();
            data.clear();
            //Produit m = new Produit();

            ResultSet rs = ste.executeQuery("SELECT * FROM `conge`");

            // ResultSet rs1 = ste.executeQuery("select employe_id from conge where employe_id='"+rs.getInt(1)+"'");
            while (rs.next()) {
                Conge A = new Conge(rs.getInt(1), rs.getDate(2), rs.getString(3), rs.getInt(4), rs.getInt(5));

                tabProd.add(A);
                //data.add(A);
                data.add(new Conge(A.getIdconge(), A.getDateconge(), A.getMotifconge(), A.getNbjourconge(), A.getEmploye_id()));

            }
            /*   for (int i=0;i<tabProd.size(); i++){
                Employe A = new Employe();
                A=(Employe) tabProd.get(i);
                 ResultSet rs2 = ste.executeQuery("SELECT idconge FROM conge where employe_id= '" + A.getIdemp() + "';" );
                 while (rs2.next()){
                     tabNom.add((int) rs2.getInt(1));
                 }}
             for (int j=0; j<tabNom.size(); j++){
                   Employe A = new Employe();
                A=(Employe) tabProd.get(j);
                int nom= (int) tabNom.get(j);
            data.add(new Employe(A.getIdemp(),A.getNomemp(),A.getPrenomemp(),A.getNumtelemp(),A.getAdresseemp()));      
             }*/

        } catch (Exception e) {
            //Logger.getLogger(tab)
            System.out.println(e);
        }
        //dateCol.setCellValueFactory(new PropertyValueFactory<Coach, String>("dateToString"));

        idcon.setCellValueFactory(new PropertyValueFactory<>("idconge"));
        date.setCellValueFactory(new PropertyValueFactory<>("dateconge"));
        motif.setCellValueFactory(new PropertyValueFactory<>("motifconge"));
        nbjour.setCellValueFactory(new PropertyValueFactory<>("nbjourconge"));
        idempc.setCellValueFactory(new PropertyValueFactory<>("employe_id"));

        Listeconges.setItems(data);
        Listeconges.setEditable(true);
//        TextFieldTableCell.forTableColumn()


        
        date.setCellFactory(TextFieldTableCell.forTableColumn(new DateStringConverter()));
           
        motif.setCellFactory(TextFieldTableCell.forTableColumn());
        nbjour.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        idempc.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        
        
         Thread clock = new Thread() {
            public void run() {
                for (;;) {
                    Calendar Cal = new GregorianCalendar();

                    int sconde = Cal.get(Calendar.SECOND);
                    int minute = Cal.get(Calendar.MINUTE);
                    int heure = Cal.get(Calendar.HOUR);
                    int AM_PM = Cal.get(Calendar.AM_PM);  
                    String pa;
                    if(AM_PM==1){
                        pa="PM";
                    }else{
                        pa="AM";
                    }
                    
                         
                    labDate.setText( + heure + ":" + (minute) + ":" + sconde +" "+pa  );
                    
                 int mois = Cal.get(Calendar.MONTH);
                    int annee = Cal.get(Calendar.YEAR);
                    int jour = Cal.get(Calendar.DAY_OF_MONTH);

                    labdate2.setText( + jour + "/" + (mois+1) + "/" + annee);              
                    
                    
                    try {
                        sleep(1000);

                    } catch (InterruptedException ex) {
                        Logger.getLogger(Calendar.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }

        };
        
clock.start();
        

    }
//
    @FXML
    private void retourMenu(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();

    }
    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("AjouterConge.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();

    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {

        Listeconges.setItems(data);

        ObservableList<Conge> allDemandes, SingleDemandes;
        allDemandes = Listeconges.getItems();
        SingleDemandes = Listeconges.getSelectionModel().getSelectedItems();
        Conge A = SingleDemandes.get(0);
        ServiceConge STD = new ServiceConge(); // STD = Service TAB DEMANDE
        STD.supprimer(A.getIdconge());
        SingleDemandes.forEach(allDemandes::remove);
        TrayNotification tray = new TrayNotification();
        tray.setTitle("Succès");
        tray.setMessage("Suppression avec succès !");
        tray.setAnimationType(AnimationType.POPUP);
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndWait();

    }

    @FXML
    private void ChangerDateh(TableColumn.CellEditEvent bb ) throws SQLException {

        Conge tab_Demandeselected = Listeconges.getSelectionModel().getSelectedItem();
        // PS.setDate(1, new java.sql.Date(a.getDateconge().getTime()));
//         LocalDate d1 = date.getValue();
//            Date date = java.sql.Date.valueOf(d1);

        tab_Demandeselected.setDateconge((Date) bb.getNewValue());
        sp.updatetab2(tab_Demandeselected);
    }

    @FXML
    private void ChangerMotifh(TableColumn.CellEditEvent bb) throws SQLException {
        Conge tab_Demandeselected = Listeconges.getSelectionModel().getSelectedItem();
        tab_Demandeselected.setMotifconge(bb.getNewValue().toString());
        sp.updatetab2(tab_Demandeselected);
    }

    @FXML
    private void ChangerNbjourh(TableColumn.CellEditEvent bb) throws SQLException {
        Conge tab_Demandeselected = Listeconges.getSelectionModel().getSelectedItem();
        tab_Demandeselected.setNbjourconge((int) bb.getNewValue());
        sp.updatetab2(tab_Demandeselected);
    }
//
//    @FXML
//    private void ChangerAdresse(TableColumn.CellEditEvent bb) throws SQLException {
//        Employe tab_Demandeselected = Listeemployes.getSelectionModel().getSelectedItem();
//        tab_Demandeselected.setAdresseemp(bb.getNewValue().toString());
//        sp.updatetab(tab_Demandeselected);
//    }

    @FXML
    private void front(ActionEvent event) throws IOException {

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("ListeConge.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
    }

    @FXML
    private void reclamation(ActionEvent event) throws IOException {
try{
        int tab_Demandeselected = Listeconges.getSelectionModel().getSelectedItem().getIdconge();

        /* Parent tableViewParent = FXMLLoader.load(getClass().getResource("ListeCategorie.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();*/
        TrayNotification tray = new TrayNotification();
        tray.setTitle("Succés");
        tray.setMessage("mail envoyeé avec succées !");
        tray.setAnimationType(AnimationType.POPUP);
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndWait();

        //Mail.Mail("djebbi.hanine20@gmail.com", " congé accepté ", "votre conge a ete accepte");
        Mail.Mail("maha.segni@esprit.tn", " congé numero  " + tab_Demandeselected + "  n a pas ete encore traité ", "je vous rappelle de traiter ma demande de conge");
   
        } catch(Exception e){
        TrayNotification tray = new TrayNotification();
        tray.setTitle("attention");
        tray.setMessage("selectionnez votre conge demandé !");
        tray.setAnimationType(AnimationType.POPUP);
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndWait();
        }
    }

    @FXML
    private void trier(ActionEvent event) throws IOException {
        //int tab_Demandeselected = Listeconges.getSelectionModel().getSelectedItem().getIdemp();

        Listeconges.getItems().clear();
        Listeconges.getItems().clear();
        ServiceConge cs = new ServiceConge();
        ObservableList<Conge> commandesObs = FXCollections.observableArrayList(cs.trierpardate());
        Listeconges.setItems(commandesObs);

        // sp.trierpardate(); 

        /* Parent tableViewParent = FXMLLoader.load(getClass().getResource("ListeCategorie.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();*/
//         TrayNotification tray =new TrayNotification();
//            tray.setTitle("Succés");
//        tray.setMessage("mail envoyeé avec succées !");
//        tray.setAnimationType(AnimationType.POPUP);
//        tray.setNotificationType(NotificationType.INFORMATION);
//        tray.showAndWait();
//        Mail.Mail("djebbi.hanine20@gmail.com", " conge pour  "+ tab_Demandeselected +" refusé ", "votre conge a ete refusé");
    }

//    public void RechercheAV(){
////                // Wrap the ObservableList in a FilteredList (initially display all data).
////        FilteredList<Employe> filteredData = new FilteredList<>(data, b -> true);
////		
////		// 2. Set the filter Predicate whenever the filter changes.
////		rechercher.textProperty().addListener((observable, oldValue, newValue) -> {
////			filteredData.setPredicate(event -> {
////				// If filter text is empty, display all persons.
////								
////				if (newValue == null || newValue.isEmpty()) {
////					return true;
////				}
////				
////				// Compare first name and last name of every person with filter text.
////				String lowerCaseFilter = newValue.toLowerCase();
////				
////				if (event.getNomemp().toLowerCase().contains(lowerCaseFilter) ) {
////					return true; // Filter matches first name.
////				} 
////
////                             
////                        
////                                 
////                             
////				else if (String.valueOf(event.getIdemp()).contains(lowerCaseFilter))
////                        return true;
////                                
////				     else  
////				    	 return false; // Does not match.
////			});
////		});
////		
////		// 3. Wrap the FilteredList in a SortedList. 
////		SortedList<Employe> sortedData = new SortedList<>(filteredData);
////		
////		// 4. Bind the SortedList comparator to the TableView comparator.
////		// 	  Otherwise, sorting the TableView would have no effect.
////		sortedData.comparatorProperty().bind(Listeemployes.comparatorProperty());
////		
////		// 5. Add sorted (and filtered) data to the table.
////		Listeemployes.setItems(sortedData);
//
//
//
//
////       
//
//     sp.recherchecongeById(Integer.parseInt(rechercher.getText()));
//  
//     
//    }
//    
//    
    @FXML
    private void pdf(ActionEvent event) throws DocumentException, BadElementException, IOException, FileNotFoundException, InterruptedException, SQLException {
        PDF1 p = new PDF1();
        p.GeneratePdf("Liste des Conge en PDF");
    }
//
////    @FXML
////    private void stat(ActionEvent event) throws IOException {
////        
////     
////
////        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Statistique.fxml"));
////        Scene tabbleViewScene = new Scene(tableViewParent);
////        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
////        window.setScene(tabbleViewScene);
////        window.show();
////    }
    
     @FXML
    private  javafx.scene.control.TextField  labDate;
    @FXML
    private javafx.scene.control.TextField labdate2;
    
}
    
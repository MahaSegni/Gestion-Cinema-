
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
import static java.lang.Math.E;
import static java.lang.System.in;
import static java.lang.Thread.sleep;
import static java.sql.JDBCType.NULL;
import java.sql.ResultSetMetaData;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.scene.text.Text;
import utils.Mail;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ListeEmployeController implements Initializable {

    @FXML
    private TableView<Employe> Listeemployes;
    @FXML
    private TableView<Conge> Listeconges;
    @FXML
    private TableColumn<Employe, Integer> id;
    @FXML
    private TableColumn<Employe, String> nom;
    @FXML
    private TableColumn<Employe, String> prenom;
    @FXML
    private TableColumn<Employe, Integer> numtel;
    @FXML
    private TableColumn<Employe, String> adresse;


    private final ObservableList<Employe> data = FXCollections.observableArrayList();
    ObservableList<String> list = FXCollections.observableArrayList("ok", "bb");

    private Statement ste;
    private Connection con;

    @FXML
    private Button ajouter;
    @FXML
    private Button supprimer;

    ServiceEmploye sp = new ServiceEmploye();
    @FXML
    private Button front;
    @FXML
    private Button refusconge;
    @FXML
    private Button acceptconge;
    @FXML
    private TextField rechercher;
    @FXML
    private Button pdf;
    @FXML
    private Button stat;
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

            ResultSet rs = ste.executeQuery("SELECT * FROM `employe`");

           // ResultSet rs1 = ste.executeQuery("select employe_id from conge where employe_id='"+rs.getInt(1)+"'");
            while (rs.next()) {
                Employe A = new Employe(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5));

                tabProd.add(A);
                //data.add(A);
                 data.add(new Employe(A.getIdemp(),A.getNomemp(),A.getPrenomemp(),A.getNumtelemp(),A.getAdresseemp()));
                
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
            System.out.println( e);
        }

        id.setCellValueFactory (new PropertyValueFactory<>("idemp"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nomemp"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenomemp"));
        numtel.setCellValueFactory(new PropertyValueFactory<>("numtelemp"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresseemp"));
        

        Listeemployes.setItems(data);
        Listeemployes.setEditable(true);

        id.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        nom.setCellFactory(TextFieldTableCell.forTableColumn());
        prenom.setCellFactory(TextFieldTableCell.forTableColumn());
        numtel.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        adresse.setCellFactory(TextFieldTableCell.forTableColumn());
       
      
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

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("AjouterEmploye.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();

    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {

        Listeemployes.setItems(data);

        ObservableList<Employe> allDemandes, SingleDemandes;
        allDemandes = Listeemployes.getItems();
        SingleDemandes = Listeemployes.getSelectionModel().getSelectedItems();
        Employe A = SingleDemandes.get(0);
        ServiceEmploye STD = new ServiceEmploye(); // STD = Service TAB DEMANDE
        STD.supprimer(A.getIdemp());
        SingleDemandes.forEach(allDemandes::remove);
        TrayNotification tray = new TrayNotification();
        tray.setTitle("Succès");
        tray.setMessage("Suppression avec succès !");
        tray.setAnimationType(AnimationType.POPUP);
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndWait();

    }

    @FXML
    private void ChangerNom(TableColumn.CellEditEvent bb) throws SQLException {

        Employe tab_Demandeselected = Listeemployes.getSelectionModel().getSelectedItem();
        tab_Demandeselected.setNomemp(bb.getNewValue().toString());
        sp.updatetab(tab_Demandeselected);
    }

    @FXML
    private void ChangerPrenom(TableColumn.CellEditEvent bb) throws SQLException {
        Employe tab_Demandeselected = Listeemployes.getSelectionModel().getSelectedItem();
        tab_Demandeselected.setPrenomemp(bb.getNewValue().toString());
        sp.updatetab(tab_Demandeselected);
    }

    @FXML
    private void ChangerNumtel(TableColumn.CellEditEvent bb) throws SQLException {
        Employe tab_Demandeselected = Listeemployes.getSelectionModel().getSelectedItem();
        tab_Demandeselected.setNumtelemp((int) bb.getNewValue());
        sp.updatetab(tab_Demandeselected);
    }

    @FXML
    private void ChangerAdresse(TableColumn.CellEditEvent bb) throws SQLException {
        Employe tab_Demandeselected = Listeemployes.getSelectionModel().getSelectedItem();
        tab_Demandeselected.setAdresseemp(bb.getNewValue().toString());
        sp.updatetab(tab_Demandeselected);
    }

    @FXML
    private void front(ActionEvent event) throws IOException {

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("ListeEmploye.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
    }

    @FXML
    private void acceptconge(ActionEvent event) throws IOException, SQLException {

         try{ 
        int tab_Demandeselected = Listeemployes.getSelectionModel().getSelectedItem().getIdemp();
        
    
       Connection con = MaConnexion.getInstance().getConnection();
            Statement ste = con.createStatement();
            data.clear();
            //Produit m = new Produit();

            ResultSet rs = ste.executeQuery("SELECT * FROM `conge` where employe_id = " + tab_Demandeselected);

           // ResultSet rs1 = ste.executeQuery("select employe_id from conge where employe_id='"+rs.getInt(1)+"'");
            if (rs.next()) {
 
    
     
//    if(exist== true)
//    {  
//        
//      String requete = "select max(idecong from conge where  employe_id =' "+  tab_Demandeselected+"'";      
//        
//        if(!requete.isEmpty())
//        {
//        
        
       
        
        /* Parent tableViewParent = FXMLLoader.load(getClass().getResource("ListeCategorie.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();*/
         TrayNotification tray =new TrayNotification();
            tray.setTitle("Succés");
        tray.setMessage("mail envoyeé avec succées !");
        tray.setAnimationType(AnimationType.POPUP);
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndWait();
        
        //Mail.Mail("djebbi.hanine20@gmail.com", " congé accepté ", "votre conge a ete accepte");
        Mail.Mail("maha.segni@esprit.tn", " congé pour  "+ tab_Demandeselected +"  accepté ", "votre conge a ete accepte");
            }else {
     
        TrayNotification tray =new TrayNotification();
            tray.setTitle("attention");
        tray.setMessage(" l employe n'admet pas une demande de conge !");
        tray.setAnimationType(AnimationType.POPUP);
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndWait();
        
   
}


   
   
    } catch(Exception ex){
        
        TrayNotification tray =new TrayNotification();
            tray.setTitle("Selectionnez l'employe");
        tray.setMessage("veillez selectionner l employe !");
        tray.setAnimationType(AnimationType.POPUP);
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndWait();

        }
   
   
   
   
   
    }

    @FXML
    private void refusconge(ActionEvent event) throws IOException, SQLException {
         try{
        
        int tab_Demandeselected = Listeemployes.getSelectionModel().getSelectedItem().getIdemp();
       
      
      
       
        Connection con = MaConnexion.getInstance().getConnection();
            Statement ste = con.createStatement();
            data.clear();
            //Produit m = new Produit();

            ResultSet rs = ste.executeQuery("SELECT * FROM `conge` where employe_id = " + tab_Demandeselected);

           // ResultSet rs1 = ste.executeQuery("select employe_id from conge where employe_id='"+rs.getInt(1)+"'");
            if (rs.next()) {

                
             
        /* Parent tableViewParent = FXMLLoader.load(getClass().getResource("ListeCategorie.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();*/
         TrayNotification tray =new TrayNotification();
            tray.setTitle("Succés");
        tray.setMessage("mail envoyeé avec succées !");
        tray.setAnimationType(AnimationType.POPUP);
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndWait();
        Mail.Mail("maha.segni@esprit.tn", " conge pour  "+ tab_Demandeselected +" refusé ", "votre conge a ete refusé");
   
    
      }else {
        
        TrayNotification tray =new TrayNotification();
            tray.setTitle("attention");
        tray.setMessage(" l employe n'admet pas une demande de conge !");
        tray.setAnimationType(AnimationType.POPUP);
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndWait();
        
   


} } catch(Exception ex){
        
        TrayNotification tray =new TrayNotification();
            tray.setTitle("Selectionnez l'employe");
        tray.setMessage("veillez selectionner l employe !");
        tray.setAnimationType(AnimationType.POPUP);
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndWait();
}
       }

    public void RechercheAV(ActionEvent event) throws IOException {
//                // Wrap the ObservableList in a FilteredList (initially display all data).
//        FilteredList<Employe> filteredData = new FilteredList<>(data, b -> true);
//		
//		// 2. Set the filter Predicate whenever the filter changes.
//		rechercher.textProperty().addListener((observable, oldValue, newValue) -> {
//			filteredData.setPredicate(event -> {
//				// If filter text is empty, display all persons.
//								
//				if (newValue == null || newValue.isEmpty()) {
//					return true;
//				}
//				
//				// Compare first name and last name of every person with filter text.
//				String lowerCaseFilter = newValue.toLowerCase();
//				
//				if (event.getNomemp().toLowerCase().contains(lowerCaseFilter) ) {
//					return true; // Filter matches first name.
//				} 
//
//                             
//                        
//                                 
//                             
//				else if (String.valueOf(event.getIdemp()).contains(lowerCaseFilter))
//                        return true;
//                                
//				     else  
//				    	 return false; // Does not match.
//			});
//		});
//		
//		// 3. Wrap the FilteredList in a SortedList. 
//		SortedList<Employe> sortedData = new SortedList<>(filteredData);
//		
//		// 4. Bind the SortedList comparator to the TableView comparator.
//		// 	  Otherwise, sorting the TableView would have no effect.
//		sortedData.comparatorProperty().bind(Listeemployes.comparatorProperty());
//		
//		// 5. Add sorted (and filtered) data to the table.
//		Listeemployes.setItems(sortedData);




       
Listeemployes.getItems().clear();
        Listeemployes.getItems().clear();
        ServiceEmploye cs = new ServiceEmploye();
        ObservableList<Employe> commandesObs = FXCollections.observableArrayList(cs.recherchecongeById((rechercher.getText())));
        Listeemployes.setItems(commandesObs);

//    sp.recherchecongeById(Integer.parseInt(rechercher.getText()));
  
     
    }
    
    
    
    @FXML
    private void pdf(ActionEvent event) throws DocumentException, BadElementException, IOException, FileNotFoundException, InterruptedException, SQLException {
        PDF p = new PDF();
        p.GeneratePdf("Liste des Employes en PDF");
    }

    @FXML
    private void stat(ActionEvent event) throws IOException {
        
     

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Statistique.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
    }
@FXML
    private void retourMenu(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();

    }
    
    
    
    
     public void datecourante() {
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
    private  javafx.scene.control.TextField  labDate;
    @FXML
    private javafx.scene.control.TextField labdate2;
     
     
     
//      private javax.swing.JLabel labDate;
//    private javax.swing.JLabel labdate2;
     
}

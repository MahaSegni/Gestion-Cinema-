
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import java.io.FileNotFoundException;
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

import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import Utils.MaConnexion;
import javafx.event.ActionEvent;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import Entities.Cinema;
import java.sql.Time;
import java.util.Date;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.TimeStringConverter;
import Service.ServiceCinema;
import Utils.PDFCinema;
import utils.Mail;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ListeCinemaController implements Initializable {

    @FXML
    private TableView<Cinema> Listeemployes;
    @FXML 
    private TableColumn<Cinema, Integer> id;
    @FXML
    private TableColumn<Cinema, Integer> nom;
    @FXML
    private TableColumn<Cinema, Integer> prenom;
     @FXML
    private TableColumn<Cinema, Time> numtel;
    @FXML
    private TableColumn<Cinema, Date> adresse;


    private final ObservableList<Cinema> data = FXCollections.observableArrayList();
    ObservableList<String> list = FXCollections.observableArrayList("ok", "bb");

    private Statement ste;
    private Connection con;

    @FXML
    private Button ajouter;
    @FXML
    private Button supprimer;

    ServiceCinema sp = new ServiceCinema();
  
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            afficher();
            //RechercheAV();
        } catch (SQLException ex) {
            Logger.getLogger(ListeCinemaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void afficher() throws SQLException {
        
//           id.setCellValueFactory (new PropertyValueFactory<>("num"));
//        nom.setCellValueFactory(new PropertyValueFactory<>("film"));
//        prenom.setCellValueFactory(new PropertyValueFactory<>("heurep"));
//        numtel.setCellValueFactory(new PropertyValueFactory<>("date"));
//        
//        
//        ServiceCinema s = new ServiceCinema();
//        ObservableList<Cinema> data = (ObservableList<Cinema>) s.AfficherCinema();
//        Listeemployes.setItems(data);
       
        
        
        
       
         List tabProd = new ArrayList();
        List tabNom = new ArrayList();

        try {
            Connection con = MaConnexion.getInstance().getConnection();
            Statement ste = con.createStatement();
            data.clear();
            //Produit m = new Produit();

            ResultSet rs = ste.executeQuery("SELECT num, nbr,film,date, heurep  FROM `cinema`");

           // ResultSet rs1 = ste.executeQuery("select employe_id from conge where employe_id='"+rs.getInt(1)+"'");
            while (rs.next()) {
                Cinema A = new Cinema(rs.getInt(1), rs.getInt(2),rs.getInt(3), rs.getDate(4), rs.getTime(5));

                tabProd.add(A);
                //data.add(A);
                 data.add(new Cinema(A.getNum(),A.getNbr(),A.getFilm(),A.getDate(),A.getHeurep()));
                
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
            System.out.println( "erreur");
        }
        id.setCellValueFactory (new PropertyValueFactory<>("num"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nbr"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("film"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("date"));
        numtel.setCellValueFactory(new PropertyValueFactory<>("heurep"));
        Listeemployes.setItems(data);
        Listeemployes.setEditable(true);
            adresse.setCellFactory(TextFieldTableCell.forTableColumn(new DateStringConverter()));
          
        prenom.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        id.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        nom.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
     
       
       

    }
   @FXML
    private void retourfilm(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();

    }
    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("AjoutCinema.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();

    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {

        Listeemployes.setItems(data);

        ObservableList<Cinema> allDemandes, SingleDemandes;
        allDemandes = Listeemployes.getItems();
        SingleDemandes = Listeemployes.getSelectionModel().getSelectedItems();
        Cinema A = SingleDemandes.get(0);
        ServiceCinema STD = new ServiceCinema(); // STD = Service TAB DEMANDE
        STD.delete(A.getNum());
        SingleDemandes.forEach(allDemandes::remove);
    }

   
   

    public void RechercheAV(){
   Listeemployes.getItems().clear();
        Listeemployes.getItems().clear();
        ServiceCinema cs = new ServiceCinema();
        ObservableList<Cinema> commandesObs = FXCollections.observableArrayList(cs.recherchefilmById((rechercher.getText())));
        Listeemployes.setItems(commandesObs);
    }
    
    
    
@FXML
    private void pdf1(ActionEvent event) throws DocumentException, BadElementException, IOException, FileNotFoundException, InterruptedException, SQLException {
        PDFCinema p = new PDFCinema();
        p.GeneratePdf("Liste Cinema en PDF");
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
    private void ChangerNumtel(TableColumn.CellEditEvent bb) throws SQLException {
        Cinema tab_Demandeselected = Listeemployes.getSelectionModel().getSelectedItem();
        tab_Demandeselected.setNbr((int) bb.getNewValue());
        sp.updatetab(tab_Demandeselected);
    }

    @FXML
    private void ChangerDate(TableColumn.CellEditEvent bb ) throws SQLException {

        Cinema tab_Demandeselected = Listeemployes.getSelectionModel().getSelectedItem();
        // PS.setDate(1, new java.sql.Date(a.getDateconge().getTime()));
//         LocalDate d1 = date.getValue();
//            Date date = java.sql.Date.valueOf(d1);

        tab_Demandeselected.setDate((Date) bb.getNewValue());
        sp.updatetab(tab_Demandeselected);
    }
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.CategorieF;
import Entities.Film;
import Service.ServiceCategorieF;
import java.io.FileNotFoundException;
import Service.ServiceEmploye;
import Service.ServiceFilm;
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
import Utils.PDF;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import utils.Mail;

/**
 * FXML Controller class
 *
 * @author mahas
 */
public class ListeFilmController implements Initializable {

    @FXML
    private TableView<Film> Listeemployes;
    @FXML
    private TableColumn<Film, Integer> id;
    @FXML
    private TableColumn<Film, String> nom;
    @FXML
    private TableColumn<Film, String> prenom;
    @FXML
    private TableColumn<Film, String> numtel;
    @FXML
    private TableColumn<Film, Date> adresse;


    private final ObservableList<Film> data1 = FXCollections.observableArrayList();
    ObservableList<String> list1 = FXCollections.observableArrayList("ok", "bb");

    private Statement ste;
    private Connection con;

    @FXML
    private Button ajouter;
    @FXML
    private Button supprimer;
@FXML
    private VBox filtreVboxCat;
    ServiceFilm sp = new ServiceFilm();
    @FXML
    private Button front;
    @FXML
    private Button refusconge;
    @FXML
    private Button acceptconge;
    @FXML
    private TextField rechercher;

    @FXML
    private Button chercher;
    @FXML
    private Button pdf;
    @FXML
    private Button stat;
 @FXML
    private Label label; 
      
    private final static int dataSize = 5;
    @FXML
    private Pagination pagination;
    private final TableView<Film> table = createTable();
    private final List<Film> data = createData();  
    private final static int rowsPerPage = 2;   
    /**
     * Initializes the controller class.
     */
     public ListeFilmController() throws SQLException {
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       // afficher();
        pagination.setPageFactory(this::createPage);    
        //RechercheAV();
    }
 @FXML
    private void ChangerNomfilm(TableColumn.CellEditEvent bb) throws SQLException {
        Film tab_Demandeselected = Listeemployes.getSelectionModel().getSelectedItem();
        tab_Demandeselected.setNomfilm((String) bb.getNewValue());
        sp.updatetab1(tab_Demandeselected);
    }
    

    @FXML
    private void ChangerDatesortie(TableColumn.CellEditEvent bb ) throws SQLException {

        Film tab_Demandeselected = Listeemployes.getSelectionModel().getSelectedItem();
        // PS.setDate(1, new java.sql.Date(a.getDateconge().getTime()));
//         LocalDate d1 = date.getValue();
//            Date date = java.sql.Date.valueOf(d1);

        tab_Demandeselected.setDatesortie((Date) bb.getNewValue());
        sp.updatetab1(tab_Demandeselected);
    }
    
    @FXML
    private void ChangerDescription(TableColumn.CellEditEvent bb ) throws SQLException {

        Film tab_Demandeselected = Listeemployes.getSelectionModel().getSelectedItem();
        // PS.setDate(1, new java.sql.Date(a.getDateconge().getTime()));
//         LocalDate d1 = date.getValue();
//            Date date = java.sql.Date.valueOf(d1);

        tab_Demandeselected.setDescriptionf((String) bb.getNewValue());
        sp.updatetab1(tab_Demandeselected);
    }
     @FXML
    private void ChangerFile(TableColumn.CellEditEvent bb ) throws SQLException {

        Film tab_Demandeselected = Listeemployes.getSelectionModel().getSelectedItem();
        // PS.setDate(1, new java.sql.Date(a.getDateconge().getTime()));
//         LocalDate d1 = date.getValue();
//            Date date = java.sql.Date.valueOf(d1);

        tab_Demandeselected.setFilename((String) bb.getNewValue());
        sp.updatetab1(tab_Demandeselected);
    }
    
     private TableView<Film> createTable() throws SQLException {
Connection con = MaConnexion.getInstance().getConnection();
            Statement ste = con.createStatement();
            ResultSet rs = ste.executeQuery("SELECT id_film, nomfilm, descriptionf, filename, datesortie FROM `film`");
       TableView<Film> table = new TableView<>();
TableColumn<Film, Integer> idColumn = new TableColumn<>("Id_film");
           idColumn.setCellValueFactory(new PropertyValueFactory<>("id_film"));
           idColumn.setPrefWidth(100);
           
           TableColumn<Film, String> nameColumn = new TableColumn<>("nomfilm");
           nameColumn.setCellValueFactory(new PropertyValueFactory<>("nomfilm"));
           nameColumn.setPrefWidth(70);
           
           TableColumn<Film, String> descColumn = new TableColumn<>("descriptionf");
        descColumn.setCellValueFactory(new PropertyValueFactory<>("descriptionf"));
        descColumn.setPrefWidth(70); 
        
        TableColumn<Film, String> fileColumn = new TableColumn<>("filename");
        fileColumn.setCellValueFactory(new PropertyValueFactory<>("filename"));
        fileColumn.setPrefWidth(70); 
        
        TableColumn<Film, Date> dateColumn = new TableColumn<>("datesortie");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("datesortie"));
        dateColumn.setPrefWidth(70); 
           table.getColumns().addAll(idColumn, nameColumn,descColumn,fileColumn,dateColumn);
        
        return table;       
    }
    
    //this method used to fill data in tableview
    private List<Film> createData() throws SQLException {
               
      List<Film> data = new ArrayList<>(dataSize);

         Connection con = MaConnexion.getInstance().getConnection();
            Statement ste = con.createStatement();
            data.clear();
            //Produit m = new Produit();
 ResultSet rs = ste.executeQuery("SELECT id_film, nomfilm, descriptionf, filename, datesortie FROM `film`");
           
           // ResultSet rs1 = ste.executeQuery("select employe_id from conge where employe_id='"+rs.getInt(1)+"'");
           while (rs.next()) {
              Film A = new Film(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5));

               
                //data.add(A);
                 data.add(new Film(A.getId_film(),A.getNomfilm(),A.getDescriptionf(),A.getFilename(),A.getDatesortie()));
                
           }

        return data;
    }
     
    private Node createPage(int pageIndex)  {
        
       try {
           Connection con = MaConnexion.getInstance().getConnection();
           Statement ste = con.createStatement();
        
           //Produit m = new Produit();
           
           
           
           int fromIndex = pageIndex * rowsPerPage;
           int toIndex = Math.min(fromIndex + rowsPerPage, data.size());
           table.setItems(FXCollections.observableArrayList(data.subList(fromIndex, toIndex)));
           return table;
       } catch (SQLException ex) {
           Logger.getLogger(ListeFilmPaginationController.class.getName()).log(Level.SEVERE, null, ex);
       }
       return null;
    }
//    public void afficher() {
//         List tabProd = new ArrayList();
//        List tabNom = new ArrayList();
//
//        try {
//            Connection con = MaConnexion.getInstance().getConnection();
//            Statement ste = con.createStatement();
//            data.clear();
//            ResultSet rs = ste.executeQuery("SELECT id_film, nomfilm, descriptionf, filename, datesortie FROM `film`");
//            while (rs.next()) {
//                Film A = new Film(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5));
//
//                tabProd.add(A);
//               
//                 data.add(new Film(A.getId_film(),A.getNomfilm(),A.getDescriptionf(),A.getFilename(),A.getDatesortie()));
//            }
//        } catch (Exception e) {
//           
//            System.out.println( "erreur");
//        }
//        id.setCellValueFactory (new PropertyValueFactory<>("id_film"));
//        nom.setCellValueFactory(new PropertyValueFactory<>("nomfilm"));
//        prenom.setCellValueFactory(new PropertyValueFactory<>("descriptionf"));
//        numtel.setCellValueFactory(new PropertyValueFactory<>("filename"));
//        adresse.setCellValueFactory(new PropertyValueFactory<>("datesortie"));
//        Listeemployes.setItems(data);
//        Listeemployes.setEditable(true);
//    }
 @FXML
    private void retourCinema(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();

    }
    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("AjouterFilm.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();

    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {

        Listeemployes.setItems(data1);

        ObservableList<Film> allDemandes, SingleDemandes;
        allDemandes = Listeemployes.getItems();
        SingleDemandes = Listeemployes.getSelectionModel().getSelectedItems();
        Film A = SingleDemandes.get(0);
        ServiceFilm STD = new ServiceFilm(); // STD = Service TAB DEMANDE
        STD.supprimerFilm(A.getId_film());
        SingleDemandes.forEach(allDemandes::remove);
//        TrayNotification tray = new TrayNotification();
//        tray.setTitle("Succès");
//        tray.setMessage("Suppression avec succès !");
//        tray.setAnimationType(AnimationType.POPUP);
//        tray.setNotificationType(NotificationType.INFORMATION);
//        tray.showAndWait();

    }

    @FXML
    private void ChangerNom(TableColumn.CellEditEvent bb) throws SQLException {

        Film tab_Demandeselected = Listeemployes.getSelectionModel().getSelectedItem();
        tab_Demandeselected.setNomfilm(bb.getNewValue().toString());
        sp.modifierDemande(tab_Demandeselected);
    }

    @FXML
    private void ChangerPrenom(TableColumn.CellEditEvent bb) throws SQLException {
        Film tab_Demandeselected = Listeemployes.getSelectionModel().getSelectedItem();
        tab_Demandeselected.setDescriptionf(bb.getNewValue().toString());
        sp.modifierDemande(tab_Demandeselected);
    }

    @FXML
    private void ChangerNumtel(TableColumn.CellEditEvent bb) throws SQLException {
        Film tab_Demandeselected = Listeemployes.getSelectionModel().getSelectedItem();
        tab_Demandeselected.setFilename(bb.getNewValue().toString());
        sp.modifierDemande(tab_Demandeselected);
    }

   @FXML
    private void ChangerAdresse(TableColumn.CellEditEvent bb) throws SQLException {
        Film tab_Demandeselected = Listeemployes.getSelectionModel().getSelectedItem();
        //tab_Demandeselected.setDatesortie(bb.getNewValue().);
        sp.modifierDemande(tab_Demandeselected);
    }

  

    public void RechercheAV(){
        ServiceFilm cs = new ServiceFilm();
        ObservableList<Film> commandesObs = FXCollections.observableArrayList(cs.recherchecongeById((rechercher.getText())));
        Listeemployes.setItems(commandesObs);
      

                
    }
    
    //string selected = this.ComboBox.GetItemText(this.ComboBox.SelectedItem); combobox
    
    @FXML
    private void pdf(ActionEvent event) throws DocumentException, BadElementException, IOException, FileNotFoundException, InterruptedException, SQLException {
        PDF p = new PDF();
        p.GeneratePdf("Liste des Films en PDF");
    }
    @FXML
private void generate_qr() {
        try {
            Connection connection = MaConnexion.getInstance().getConnection();
            
            String query = "select id_film, nomfilm from film";
            Statement stmt = null;
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
            	String qrCodeData=rs.getString("nomfilm");
           
            String filePath = "E:\\QRCODE\\"+qrCodeData+".png";
            String charset = "UTF-8"; // or "ISO-8859-1"
            Map < EncodeHintType, ErrorCorrectionLevel > hintMap = new HashMap < EncodeHintType, ErrorCorrectionLevel > ();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix matrix = new MultiFormatWriter().encode(
                new String(qrCodeData.getBytes(charset), charset),
                BarcodeFormat.QR_CODE, 200, 200, hintMap);
            MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
                .lastIndexOf('.') + 1), new File(filePath));
            System.out.println("QR Code image created successfully!");
         }} catch (Exception e) {
            System.err.println(e);
        }
    }

}

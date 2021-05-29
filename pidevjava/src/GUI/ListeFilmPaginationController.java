/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Film;
import Utils.MaConnexion;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author mahas
 */
public class ListeFilmPaginationController implements Initializable {

   @FXML
    private Label label; 
      
    private final static int dataSize = 5;
    @FXML
    private Pagination pagination;
    private final TableView<Film> table = createTable();
    private final List<Film> data = createData();  
    private final static int rowsPerPage = 2;   

    public ListeFilmPaginationController() throws SQLException {
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          pagination.setPageFactory(this::createPage);    
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
    
      
   
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.comment;
import Entities.event;
import GUI.FXMLDocumentController;
import Service.event_service;
import Utils.MaConnexion;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.time.LocalDate;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mahas
 */
public class AdminController implements Initializable {

     @FXML
    private TextField nom;
    @FXML
    private TextField capacite;
    @FXML
    private DatePicker start;
    @FXML
    private DatePicker end;
    @FXML
    private TextField emplacement;
    @FXML
    private TableView<event> table;
    @FXML
    private TableColumn<event, String> col_nom;
    @FXML
    private TableColumn<event, String> col_start;
    @FXML
    private TableColumn<event, String> col_end;
    @FXML
    private TableColumn<event, String> col_emp;
    @FXML
    private TableColumn<event, String> col_cap;

    ObservableList<event> eve =FXCollections.observableArrayList();
    ObservableList<comment> com =FXCollections.observableArrayList();
    List<event> statList =new ArrayList<>();
    @FXML
    private TextField mod_nom;
    @FXML
    private TextField mod_emp;
    @FXML
    private TextField mod_cap;
    @FXML
    private DatePicker mod_start;
    @FXML
    private DatePicker mod_end;
    @FXML
    private Button modifButton;
    @FXML
    private Button supp;
    event_service ev = new event_service();
    
    @FXML
    private ComboBox<String> combo_modif;
    @FXML
    private TableView<comment> table_comment;
    @FXML
    private TableColumn<?, ?> content;
    @FXML
    private BarChart<?, ?> statchart;
    @FXML
    private TextField place_dispo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         update_event();
        combo_mod();
        update_comment();
        stat();
    }    
    
    @FXML
    private void addAction(ActionEvent event) {
        event_service ev = new event_service();
        String nomEvent = nom.getText();
        LocalDate first = start.getValue();
        java.util.Date date_start = java.sql.Date.valueOf(first);
        LocalDate last = end.getValue();
        java.util.Date date_end = java.sql.Date.valueOf(last);
        String emp = emplacement.getText();
        String cap = capacite.getText();
        int place = Integer.parseInt(place_dispo.getText());  
        
        ev.create(nomEvent, date_start, date_end, emp, cap, place);
        refresh();
        combo_mod();
        nom.clear();
        start.setValue(null);
        end.setValue(null);
        emplacement.clear();
        capacite.clear();
    }
    public void refresh(){
    table.getItems().clear();
    update_event();
    }
    private void combo_mod() {
        List<String> arr= ev.affichagecombo();
        ObservableList<String> combo = FXCollections.observableArrayList(arr);
        combo_modif.getItems().clear();
        combo_modif.getItems().addAll(combo);
    }
    public void update_event()
    {
     try {
            
            col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            col_start.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
            col_end.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
            col_emp.setCellValueFactory(new PropertyValueFactory<>("emplacement"));
            col_cap.setCellValueFactory(new PropertyValueFactory<>("capacite"));
            Connection conn = MaConnexion.getInstance().getConnection();
            PreparedStatement pt = conn.prepareStatement("select * from evenement");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {            
                eve.add(new event(
                        rs.getString("nomEvent"),
                        rs.getString("dateEvent"),
                        rs.getString("dateEventFin"),
                        rs.getString("emplacement"),
                        rs.getString("capacite")
                ));
            }
            table.setItems(eve);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void modifierAction(ActionEvent event) {
       String nomp=combo_modif.getValue().toString();
       String nom_mod=mod_nom.getText();
       LocalDate date1=mod_start.getValue();
       LocalDate date3=mod_end.getValue();
       String capa=mod_cap.getText();
       String empla=mod_emp.getText();

       java.util.Date date2 = java.sql.Date.valueOf(date1);
       java.util.Date date4 = java.sql.Date.valueOf(date3);
       Timestamp ts=new Timestamp(date2.getTime());
       Timestamp ts1=new Timestamp(date4.getTime());
       event_service s=new event_service();
       s.modifie_event(ts,ts1,empla,capa,nomp);
       mod_cap.clear();
       mod_emp.clear();
       mod_end.setValue(null);
       mod_start.setValue(null);
       mod_nom.clear();
       refresh();
    }

    @FXML
    private void suppAction(ActionEvent event) {
        event_service sr=new event_service();
        String nom=combo_modif.getValue();
        sr.supprimer_event(nom);
        combo_mod();
        refresh();
    }

    @FXML
    private void dataAction(ActionEvent event) {
         try {
            String nomp=combo_modif.getValue().toString();
            
            Connection conn = MaConnexion.getInstance().getConnection();
            PreparedStatement pt = conn.prepareStatement("select * from evenement where nomEvent=?");
            pt.setString(1,nomp);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) { 
                String nomev=rs.getString("nomEvent");
                Date date_start=rs.getDate("dateEvent");
                Date date_end=rs.getDate("dateEventFin");
                String capev=rs.getString("capacite");
                String empev=rs.getString("emplacement");
                String pattern = "yyyy-MM-dd";
                DateFormat df = new SimpleDateFormat(pattern);
                String dateAsString = df.format(date_start);
                mod_nom.setText(nomev);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate = LocalDate.parse(dateAsString, formatter);
                mod_start.setValue(localDate);
                String dateAsString1 = df.format(date_end);
                DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate1 = LocalDate.parse(dateAsString1, formatter1);
                mod_end.setValue(localDate1);
                mod_cap.setText(capev);
                mod_emp.setText(empev);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void update_comment() {
       
        try {
            table_comment.getItems().clear();
            content.setCellValueFactory(new PropertyValueFactory<>("content"));
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
    public void stat () {
        try {
            XYChart.Series set1 = new XYChart.Series<>();
            event_service e = new event_service();
            statList = e.stat();
            for(int i=0; i < statList.size(); i++){
                String a= statList.get(i).getCapacite();
                int b = Integer.parseInt(a);
                set1.getData().add(new XYChart.Data(statList.get(i).getNom(), b));
            }
            statchart.getData().addAll(set1);
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   @FXML
    private void Menu(ActionEvent event) throws IOException {
        
           Parent tableViewParent = FXMLLoader.load(getClass().getResource("../GUI/menu.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
    }
    
}

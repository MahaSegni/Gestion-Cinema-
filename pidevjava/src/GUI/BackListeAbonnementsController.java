/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 
package GUI;

import Entities.Abonnement;
import Entities.TypeAbonnement;
import Service.ServiceAbonne;
import Service.ServiceAbonnement;
import Service.ServiceTypeAbonnement;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Zeineb
 */
public class BackListeAbonnementsController implements Initializable {

    @FXML
    private TableView<Abonnement> table_view_abo;
    @FXML
    private Button btn_valider;
    @FXML
    private TextField tf_recherche;
    @FXML
    private Label lbl_valide;

    ServiceAbonnement sa = new ServiceAbonnement();
    @FXML
    private TableColumn<Abonnement, String> colNom;
    @FXML
    private TableColumn<Abonnement, String> colPrenom;
    @FXML
    private TableColumn<Abonnement, ?> colService;
    @FXML
    private TableColumn<?, ?> colDatedemande;
    @FXML
    private TableColumn<?, ?> colDatedebut;
    @FXML
    private TableColumn<?, ?> colDatefin;
    @FXML
    private TableColumn<Abonnement, ?> colPrix;
    @FXML
    private PieChart pieChart;
    @FXML
    private Label lbl_recap;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btn_valider.setDisable(true);
        afficher_abonnements("");

        tf_recherche.textProperty().addListener((observable, oldValue, newValue) -> {
            afficher_abonnements(newValue);
        });

        table_view_abo.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> show_valide(newValue));

        btn_valider.setOnAction((event) -> {

            try {
                ServiceAbonnement sa = new ServiceAbonnement();
                sa.updateState(table_view_abo.getSelectionModel().getSelectedItem().getId());

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Abonnement validé");
//alert.setHeaderText("Look, an Error Dialog");
                alert.setContentText("Abonnement validé!");

                alert.showAndWait();
                ((Node) event.getSource()).getScene().getWindow().hide();
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("BackListeAbonnements.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(BackListeAbonnementsController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        
        
        //PIE
         
                  
        

    }

    public void show_valide(Abonnement newvalue) {

        if (newvalue.isValide()) {
            lbl_valide.setText("Valide");
            lbl_valide.setTextFill(Color.GREEN);
            btn_valider.setDisable(true);

        } else {
            lbl_valide.setText("Non Valide");
            lbl_valide.setTextFill(Color.RED);
            btn_valider.setDisable(false);

        }

    }

    public void afficher_abonnements(String nom) {

        if (nom.equals("")) {
            ArrayList<Abonnement> list_abo = (ArrayList) sa.getabo_last2days();
            ServiceTypeAbonnement sta = new ServiceTypeAbonnement();
            ServiceAbonne sab = new ServiceAbonne();

            //  TypeAbonnement ta=sta.gettypeabo(idia);
            table_view_abo.setItems(FXCollections.observableList(list_abo));

            colNom.setCellValueFactory(
                    c -> {
                        SimpleObjectProperty property = new SimpleObjectProperty();

                        property.setValue(sab.get_abonne(c.getValue().getAbonne()).getNomabonne());
                        return property;
                    });
            colPrenom.setCellValueFactory(
                    c -> {
                        SimpleObjectProperty property = new SimpleObjectProperty();

                        property.setValue(sab.get_abonne(c.getValue().getAbonne()).getPrenomabonne());
                        return property;
                    });
            colService.setCellValueFactory(
                    c -> {
                        SimpleObjectProperty property = new SimpleObjectProperty();

                        property.setValue(sta.gettypeabo(c.getValue().getTypeabonnement()).getType());
                        return property;
                    });

            colPrix.setCellValueFactory(
                    c -> {
                        SimpleObjectProperty property = new SimpleObjectProperty();

                        property.setValue(sta.gettypeabo(c.getValue().getTypeabonnement()).getPrix());
                        return property;
                    });
            colDatedemande.setCellValueFactory(new PropertyValueFactory<>("datedemande"));
            colDatedebut.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
            colDatefin.setCellValueFactory(new PropertyValueFactory<>("datefin"));
            
            
            
            //piechart
            int nb_valide=0;
            int nb_non_valide=0;
            for (Abonnement a :list_abo){
            
                if(a.isValide()){
                nb_valide=nb_valide+1;
                }
                else{
                nb_non_valide=nb_non_valide+1;
                }
            
            }
            
            
            ObservableList<PieChart.Data> piechartData=FXCollections.observableArrayList(
            new PieChart.Data("Valide",nb_valide)
                    ,new PieChart.Data("Non Valide",nb_non_valide)
            
            
            
            
            );
            
            
           
            pieChart.setData(piechartData);
            pieChart.setStartAngle(180);
            pieChart.setTitle("Deux derniers jours");
            int nb_abo=nb_non_valide+nb_valide;
           lbl_recap.setText("Récap :  "+nb_abo+" abonnements dont "+nb_valide+" validés et "+nb_non_valide+" non validés");
            
            
            
           }
            

        else {

            ArrayList<Abonnement> list_abo = (ArrayList) sa.getAboByNom(nom);
            ServiceTypeAbonnement sta = new ServiceTypeAbonnement();
            ServiceAbonne sab = new ServiceAbonne();

            //  TypeAbonnement ta=sta.gettypeabo(idia);
            table_view_abo.setItems(FXCollections.observableList(list_abo));

            colNom.setCellValueFactory(
                    c -> {
                        SimpleObjectProperty property = new SimpleObjectProperty();

                        property.setValue(sab.get_abonne(c.getValue().getAbonne()).getNomabonne());
                        return property;
                    });
            colPrenom.setCellValueFactory(
                    c -> {
                        SimpleObjectProperty property = new SimpleObjectProperty();

                        property.setValue(sab.get_abonne(c.getValue().getAbonne()).getPrenomabonne());
                        return property;
                    });
            colService.setCellValueFactory(
                    c -> {
                        SimpleObjectProperty property = new SimpleObjectProperty();

                        property.setValue(sta.gettypeabo(c.getValue().getTypeabonnement()).getType());
                        return property;
                    });

            colPrix.setCellValueFactory(
                    c -> {
                        SimpleObjectProperty property = new SimpleObjectProperty();

                        property.setValue(sta.gettypeabo(c.getValue().getTypeabonnement()).getPrix());
                        return property;
                    });
            colDatedemande.setCellValueFactory(new PropertyValueFactory<>("datedemande"));
            colDatedebut.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
            colDatefin.setCellValueFactory(new PropertyValueFactory<>("datefin"));

        }
    }

    @FXML
    private void valider(ActionEvent event) {

    }
@FXML
    private void Menu(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();

    }
    @FXML
    private void TypeAbo(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("AfficheTypeAbonnement.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();

    }
}

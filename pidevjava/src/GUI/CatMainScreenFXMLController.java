/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import Entities.Cat;
import Service.ServiceCat;
import java.io.IOException;
import javafx.scene.Node;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class CatMainScreenFXMLController implements Initializable {

    @FXML
    private TextField txtNomc;

    @FXML
    private Button btnAjouterCat;
    private Label lbResult;
    @FXML
    private TableView<Cat> tableCat;
    @FXML
    private TableColumn<?, ?> idcColonne;
    @FXML
    private TableColumn<?, ?> nomcColonne;

    ServiceCat ser = new ServiceCat();
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnModifier;

    private static int ic;
    @FXML
    private Button btnGestionPlat;
    @FXML
    private Pane ap;
    @FXML
    private Button btnFront;
    @FXML
    private Label articleanim;
    @FXML
    private Label articleanim1;
    @FXML
    private Button btnGestionCat;
    @FXML
    private Button btnRechercherCat;
    @FXML
    private TextField rechercher;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        btnAjouterCat.setOnAction(e -> {

            

            GridPane grid = new GridPane();
            Cat v = new Cat();

            String f = txtNomc.getText();


            v.setNomc(f);
            ser.add(v);
            txtNomc.setText("");
            GridPane.setConstraints(txtNomc, 0, 0);
            grid.getChildren().add(txtNomc);
            tableCat.getItems().clear();
            afficherListCat();
        });
        tableCat.getItems().clear();
        afficherListCat();
        /*btnModifier.setOnAction(e -> {
            lbResult.getColumns();
        });*/
        tableCat.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                System.out.println(tableCat.getSelectionModel().getSelectedItem());
                Cat c = new Cat();
                txtNomc.setText(tableCat.getSelectionModel().getSelectedItem().getNomc());
                c.setNomc(tableCat.getSelectionModel().getSelectedItem().getNomc());
                ic = tableCat.getSelectionModel().getSelectedItem().getIdc();

            }
        });

    }

    public void afficherListCat() {
        ArrayList arrayList = (ArrayList) ser.getAll();

        ObservableList observableList = FXCollections.observableArrayList(arrayList);

        idcColonne.setCellValueFactory(new PropertyValueFactory<>("idc"));
        nomcColonne.setCellValueFactory(new PropertyValueFactory<>("nomc"));
        tableCat.setItems(observableList);
    }

    @FXML
    private void txtNomc(ActionEvent event) {
    }

    private void btnAfficher(ActionEvent event) {
        tableCat.getItems().clear();
        afficherListCat();
        /*lbResult.setText(new ServiceCat().getAll().toString());*/
    }

    private int CategorieSelectionner() {
        int selectedItem = tableCat.getSelectionModel().getSelectedItem().getIdc();
        int selectedIndex = tableCat.getSelectionModel().getSelectedIndex();
        System.out.println(selectedItem);
        return selectedItem;
    }

    @FXML
    private void supprimerCat(ActionEvent event) throws SQLException {

        int x = CategorieSelectionner();
        Alert a1 = new Alert(Alert.AlertType.WARNING);
        a1.setTitle("Supprimer categorie");
        a1.setContentText("Vous voulez vraiment supprimer cette categorie ?");
        Optional<ButtonType> result = a1.showAndWait();
        if (result.get() == ButtonType.OK) {
            ser.supprimer(x);
            Alert a2 = new Alert(Alert.AlertType.INFORMATION);
            a2.setTitle("Supprimer categorie");
            a2.setContentText("Categorie supprimé avec succés!");
            a2.show();

            tableCat.getItems().clear();
            afficherListCat();

        } else {
            a1.close();
        }
    }

    @FXML
    private void ModifierCat(ActionEvent event) throws SQLException {

        int x = CategorieSelectionner();

        Cat c = new Cat();
        c.setNomc(txtNomc.getText());
        c.setIdc(ic);
        Alert a1 = new Alert(Alert.AlertType.WARNING);
        a1.setTitle("Modifier categorie");
        a1.setContentText("Vous voulez vraiment Modifier cette categorie ?");

        Optional<ButtonType> result = a1.showAndWait();
        if (result.get() == ButtonType.OK) {
            ser.modifier(c, ic);
            Alert a2 = new Alert(Alert.AlertType.INFORMATION);
            a2.setTitle("Modifier categorie");
            a2.setContentText("Categorie modifiée avec succés!");
            a2.show();
            txtNomc.setText("");

            tableCat.getItems().clear();
            afficherListCat();

        } else {
            a1.close();
        }
    }

    private void Modifier(Cat c) {

    }

    @FXML
    private void GestionPlat(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PlatMainScreenFXML.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();

            stage.setTitle("Plat window");
            stage.setScene(new Scene(root1));
            stage.show();

            new animatefx.animation.RollIn(root1).play();//animated root node

        } catch (Exception e) {
            System.out.println("can't load Plat window");
        }

    }

    @FXML
    private void front(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Main.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();

            stage.setTitle("Plat window");
            stage.setScene(new Scene(root1));
            stage.show();

            new animatefx.animation.BounceInDown(root1).play();//animated root node

        } catch (Exception e) {
            System.out.println("can't load Plat window");
        }
    }


    @FXML
    private void GestionCat(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CatMainScreenFXML.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();

            stage.setTitle("cat window");
            stage.setScene(new Scene(root1));
            stage.show();

            new animatefx.animation.RollIn(root1).play();//animated root node

        } catch (Exception e) {
            System.out.println("can't load cat window");
        }
    }

    @FXML
    private void RechercherCat(ActionEvent event) {
        tableCat.getItems().clear();
        tableCat.getItems().clear();
        ServiceCat cs = new ServiceCat();
        ObservableList<Cat> commandesObs = FXCollections.observableArrayList(cs.rechercheCatById((rechercher.getText())));
        tableCat.setItems(commandesObs);
    }
    @FXML
    private void menu(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import ds.desktop.notify.DesktopNotify;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Entities.Cat;
import Entities.Plat;
import Service.ServiceCat;
import Service.ServicePlat;

/**
 * FXML Controller class
 *
 * @author bhk
 */
public class PlatMainScreenFXMLController implements Initializable {

    @FXML
    private TextField txtNomp;
    @FXML
    private TextField txtPrixp;

    @FXML
    private TextField txtImagep;
    @FXML
    private TextField txtDescription;

    @FXML
    private Button btnAjouterPlat;
    @FXML
    private Label lbresult;
    @FXML
    private TableView<Plat> tablePlat;
    @FXML
    private TableColumn<?, ?> idpColonne;
    @FXML
    private TableColumn<?, ?> nompColonne;
    @FXML
    private TableColumn<?, ?> prixpColonne;
    @FXML
    private TableColumn<?, ?> imagepColonne;
    @FXML
    private TableColumn<?, ?> descriptionColonne;
    ServicePlat ser = new ServicePlat();
    ServiceCat serCat = new ServiceCat();
    @FXML
    private Pane ap;
    @FXML
    private TableColumn<?, ?> categorie_idColonne;
    @FXML
    private Button btnModifierPlat;
    @FXML
    private Button btnSupprimerPlat;
    @FXML
    private Button btnGestionCat;

    private static int ic;
    @FXML
    private ComboBox<String> comboBoxCat;
    @FXML
    private Button btnFront;
    @FXML
    private ImageView imageViewImage;
    @FXML
    private Button btnimportimage;

    private String imagePalt;
    List<String> listFichier;
    @FXML
    private Label errors_image;
    @FXML
    private Label articleanim1;
    @FXML
    private Button btnGestionPlat;
    @FXML
    private TextField rechercher;
    @FXML
    private Button btnRechercherPlat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        imagePalt = null;
        listFichier = new ArrayList<>();
        listFichier.add("*.png");
        listFichier.add("*.jpg");

        ArrayList<Cat> arrayList = (ArrayList) serCat.getAll();
        for (int i = 0; i < arrayList.size(); i++) {
            comboBoxCat.getItems().addAll(arrayList.get(i).getNomc());
        }

        //comboBoxCat.setItems(observableList.get(1).getNomc());
        File image = new File("");
        FileInputStream fis = null;
        btnAjouterPlat.setOnAction(e -> {
            try {

                Plat p = new Plat();

               

                String Nomp = this.txtNomp.getText();
                int Prixp = Integer.parseInt(this.txtPrixp.getText());
                // int Categorie_id = Integer.parseInt(this.txtCategorie_id.getText());

                String categories = comboBoxCat.getValue().toString();
                int Categorie_id = serCat.categorie_nom(categories);

                String Imagep = this.txtImagep.getText();
                String Description = this.txtDescription.getText();

                p.setNomp(Nomp);
                p.setPrixp(Prixp);
                p.setCategorie_id(Categorie_id);
                p.setImagep(imagePalt);
                p.setDescription(Description);

                /*
                String s5 = txtNomp.getText();
                String s2 = txtPrixp.getText();
                int i2 = Integer.parseInt(s2);
                String s3 = txtCategorie_id.getText();
                int i3 = Integer.parseInt(s3);
                String s4 = txtImagep.getText();
                 */
                ser.add(p);
                txtNomp.setText("");
                txtPrixp.setText("");

                FileInputStream input = new FileInputStream("./src/images/empty.jpg");
                Image imagee = new Image(input);
                imageViewImage.setImage(imagee);
                txtDescription.setText("");
                tablePlat.getItems().clear();
                afficherListPlat();
            } catch (SQLException ex) {
                Logger.getLogger(PlatMainScreenFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PlatMainScreenFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        tablePlat.getItems().clear();
        afficherListPlat();
        /*btnModifier.setOnAction(e -> {
            lbResult.getColumns();
        });*/
        tablePlat.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                FileInputStream input = null;
                try {
                    System.out.println(tablePlat.getSelectionModel().getSelectedItem());
                    Plat p = new Plat();
                    txtNomp.setText(tablePlat.getSelectionModel().getSelectedItem().getNomp());
                    txtPrixp.setText(String.valueOf(tablePlat.getSelectionModel().getSelectedItem().getPrixp()));
                    //txtCategorie_id.setText(String.valueOf(tablePlat.getSelectionModel().getSelectedItem().getCategorie_id()));
                    input = new FileInputStream("C:/Users/mahas/Documents/NetBeansProjects/pidevjava_Integration_MHFSRZF/pidevjava23_maha/src/images/" + tablePlat.getSelectionModel().getSelectedItem().getImagep());
                    Image imagee = new Image(input);
                    imageViewImage.setImage(imagee);
                    errors_image.setText(tablePlat.getSelectionModel().getSelectedItem().getImagep());
                    txtDescription.setText(tablePlat.getSelectionModel().getSelectedItem().getDescription());
                    p.setNomp(tablePlat.getSelectionModel().getSelectedItem().getNomp());
                    p.setPrixp(tablePlat.getSelectionModel().getSelectedItem().getPrixp());
                    p.setCategorie_id(tablePlat.getSelectionModel().getSelectedItem().getCategorie_id());
                    p.setImagep(tablePlat.getSelectionModel().getSelectedItem().getImagep());
                    p.setDescription(tablePlat.getSelectionModel().getSelectedItem().getDescription());
                    ic = tablePlat.getSelectionModel().getSelectedItem().getIdp();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(PlatMainScreenFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        input.close();
                    } catch (IOException ex) {
                        Logger.getLogger(PlatMainScreenFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        });

    }

    public void afficherListPlat() {
        ArrayList arrayList = (ArrayList) ser.getAll();

        ObservableList observableList = FXCollections.observableArrayList(arrayList);

        idpColonne.setCellValueFactory(new PropertyValueFactory<>("idp"));
        nompColonne.setCellValueFactory(new PropertyValueFactory<>("nomp"));
        prixpColonne.setCellValueFactory(new PropertyValueFactory<>("prixp"));
        categorie_idColonne.setCellValueFactory(new PropertyValueFactory<>("categorie_id"));
        imagepColonne.setCellValueFactory(new PropertyValueFactory<>("imagep"));
        descriptionColonne.setCellValueFactory(new PropertyValueFactory<>("description"));
        tablePlat.setItems(observableList);
    }

    private void btnAfficherPlat(ActionEvent event) {
        tablePlat.getItems().clear();
        afficherListPlat();
        /*lbResult.setText(new ServiceCat().getAll().toString());*/
    }

    private int PlatSelectionner() {
        int selectedItem = tablePlat.getSelectionModel().getSelectedItem().getIdp();
        int selectedIndex = tablePlat.getSelectionModel().getSelectedIndex();
        System.out.println(selectedItem);
        return selectedItem;
    }

    @FXML
    private void ModifierPlat(ActionEvent event) throws SQLException, FileNotFoundException {

        int x = PlatSelectionner();

        Plat p = new Plat();

        //c.setNomp(txtNomp.getText());
        //c.setPrixp=Integer.parseInt(txtPrixp.getText());
        String Nomp = this.txtNomp.getText();
        int Prixp = Integer.parseInt(this.txtPrixp.getText());
        String categories = comboBoxCat.getValue().toString();
        int Categorie_id = serCat.categorie_nom(categories);
        String Imagep = this.txtImagep.getText();
        String Description = this.txtDescription.getText();

        p.setNomp(Nomp);
        p.setPrixp(Prixp);
        p.setCategorie_id(Categorie_id);
        p.setImagep(imagePalt);
        p.setDescription(Description);

        p.setIdp(ic);
        Alert a1 = new Alert(Alert.AlertType.WARNING);
        a1.setTitle("Modifier plat");
        a1.setContentText("Vous voulez vraiment Modifier cette plat ?");

        Optional<ButtonType> result = a1.showAndWait();

        if (result.get() == ButtonType.OK) {

            ser.modifier(p, ic);
            Alert a2 = new Alert(Alert.AlertType.INFORMATION);
            a2.setTitle("Modifier plat");
            a2.setContentText("plat modifié avec succés!");
            a2.show();

            txtNomp.setText("");
            txtPrixp.setText("");
            FileInputStream input = new FileInputStream("./src/images/empty.jpg");
            Image image = new Image(input);
            imageViewImage.setImage(image);
            txtImagep.setText("");
            txtDescription.setText("");

            tablePlat.getItems().clear();
            afficherListPlat();

        } else {
            a1.close();
        }
    }

    private void Modifier(Cat c) {

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

        } catch (Exception e) {
            System.out.println("can't load cat window");
        }
    }

    @FXML
    private void SupprimerPlat(ActionEvent event) throws SQLException {
        int x = PlatSelectionner();
        Alert a1 = new Alert(Alert.AlertType.WARNING);
        a1.setTitle("Supprimer Plat");
        a1.setContentText("Vous voulez vraiment supprimer le plat ?");
        Optional<ButtonType> result = a1.showAndWait();
        if (result.get() == ButtonType.OK) {
            ser.supprimer(x);
            Alert a2 = new Alert(Alert.AlertType.INFORMATION);
            a2.setTitle("Supprimer plat");
            a2.setContentText("Plat supprimé avec succés!");
            a2.show();

            tablePlat.getItems().clear();
            afficherListPlat();

        } else {
            a1.close();
        }
    }

    @FXML
    private void front(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Main.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();

            stage.setTitle("cat window");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception e) {
            System.out.println("can't load cat window");
        }
    }

    @FXML
    private void importerPlatImage(ActionEvent event) throws FileNotFoundException, IOException {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", listFichier));
        File f = fc.showOpenDialog(null);
        if (f != null) {

            errors_image.setText("Image selectionnée" + f.getAbsolutePath());
            InputStream is = null;
            OutputStream os = null;
            try {
                is = new FileInputStream(new File(f.getAbsolutePath()));
                os = new FileOutputStream(new File("./src/images/" + f.getName()));
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }

            } finally {
                is.close();
                os.close();
            }

            File file = new File("./src/images/" + f.getName());
//            System.out.println(file.toURI().toString());
            imageViewImage.setImage(new Image(file.toURI().toString()));
            imagePalt = f.getName();
        } else if (f == null) {
            errors_image.setText("Erreur chargement de l'image");
        }
    }

    @FXML
    private void GestionPlat(ActionEvent event) {
    }

    @FXML
    private void RechercherPlat(ActionEvent event) {
        tablePlat.getItems().clear();
        tablePlat.getItems().clear();
        ServicePlat cs = new ServicePlat();
        ObservableList<Plat> commandesObs = FXCollections.observableArrayList(cs.recherchePlatById((rechercher.getText())));
        tablePlat.setItems(commandesObs);
    }
 @FXML
    private void Menu(ActionEvent event) throws IOException {
     Parent tableViewParent = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Abonne;
import Service.ServiceAbonne;
import Utils.MaConnexion;
import java.io.IOException;
//import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Import;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author Zeineb
 */
public class AddabonneController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfMail;
    @FXML
    private DatePicker tfDatenaiss;
    @FXML
    private TextField tfTel;
    @FXML
    private TextField tfPassword;
    @FXML
    private Button ButtonAjouter;
    @FXML
    private Label erreur_champ_vide;
    @FXML
    private Label erreur_numtel;
    @FXML
    private Label erreur_mail;
    @FXML
    private Label erreur_mail_exist;
    @FXML
    private TextField tfConfirmPassword;
    @FXML
    private Label confirm_mdp;
    @FXML
    private Label confirm_mdp_valide;

    private void handleButtonAction(ActionEvent event) throws SQLException {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        erreur_champ_vide.setVisible(false);
        erreur_numtel.setVisible(false);
        erreur_mail.setVisible(false);
        erreur_mail_exist.setVisible(false);
        confirm_mdp.setVisible(false);
        confirm_mdp_valide.setVisible(false);

        tfDatenaiss.setValue(LocalDate.now());
        tfConfirmPassword.textProperty().addListener(
                (observable, oldValue, newValue) -> {

                    verif_mdp(newValue);

                }
        );

    }

    @FXML
    private void AjouterAbonne(ActionEvent event) throws IOException {

        erreur_champ_vide.setVisible(false);
        erreur_numtel.setVisible(false);
        erreur_mail.setVisible(false);
        erreur_mail_exist.setVisible(false);

        ServiceAbonne sa = new ServiceAbonne();

        if ((tfNom.getText().equals("")) || (tfPrenom.getText().equals(""))
                || (tfMail.getText().equals("")) || (tfTel.getText().equals(""))
                || (tfPassword.getText().equals(""))
                || (tfConfirmPassword.getText().equals(""))
                || (tfDatenaiss.getValue() == null)) {

            erreur_champ_vide.setVisible(true);

        } else {

            if (!Test_num_tel(tfTel.getText())) {
                erreur_numtel.setVisible(true);

            } else {
                String masque = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                        + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
                Pattern pattern = Pattern.compile(masque);
                Matcher controler = pattern.matcher(tfMail.getText());
                if (!controler.matches()) {

                    erreur_mail.setVisible(true);

                } else {
                    if (sa.email_existant(tfMail.getText())) {
                        erreur_mail_exist.setVisible(true);
                    } else {

                        if (!tfConfirmPassword.getText().equals(tfPassword.getText())) {

                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Erreur");
//alert.setHeaderText("Look, an Error Dialog");
                            alert.setContentText("La confirmation du mot de passe n'est pas valide  !");

                            alert.showAndWait();

                        } else {

                            try {
                                Abonne a = new Abonne();
                                a.setNomabonne(tfNom.getText());
                                a.setPrenomabonne(tfPrenom.getText());
                                a.setMailabonne(tfMail.getText());

                                a.setDatenaissabonne(java.sql.Date.valueOf(tfDatenaiss.getValue()));
                                a.setTelephoneabonne(Integer.valueOf(tfTel.getText()));
                                a.setPassword(tfPassword.getText());
                                sa.AddAbonne(a);
                                
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Inscription réussie!");
//alert.setHeaderText("Look, an Error Dialog");
alert.setContentText("Inscription réussie  !");
                            
                            alert.showAndWait();
                            
                            
                                   ((Node)event.getSource()).getScene().getWindow().hide();
                Stage stage=new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                            } catch (NumberFormatException e) {
                                erreur_numtel.setVisible(true);

                                Alert alert = new Alert(Alert.AlertType.ERROR);

                                alert.showAndWait();

                            }
                        }

                    }
                }

            }
        }

    }

    public void verif_mdp(String conf_mdp) {

        if (!conf_mdp.equals(tfPassword.getText())) {
            confirm_mdp.setVisible(true);
            confirm_mdp_valide.setVisible(false);

        } else {

            confirm_mdp.setVisible(false);
            confirm_mdp_valide.setVisible(true);
        }

    }

    public boolean Test_num_tel(String input) {

        try {
            Integer.parseInt(input);
            if (input.length() == 8) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }

}

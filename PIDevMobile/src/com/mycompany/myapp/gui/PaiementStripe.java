/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.util.Resources;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Abonne;
import com.mycompany.myapp.entities.Abonnement;
import com.mycompany.myapp.entities.TypeAbonnement;
import com.mycompany.myapp.services.ServiceAbonne;
import com.mycompany.myapp.services.ServiceAbonnement;
import com.mycompany.myapp.utils.stripe_methode;
import com.stripe.exception.CardException;
import com.stripe.exception.StripeException;
import java.util.ArrayList;

/**
 *
 * @author Zeineb
 */
public class PaiementStripe extends Form {

    stripe_methode stm = new stripe_methode();
    ServiceAbonne sa = new ServiceAbonne();

    public PaiementStripe(Resources res) {
        Toolbar tb = getToolbar();
        setLayout(BoxLayout.y());
        setLayout(new FlowLayout(CENTER, CENTER));
        setTitle("Paiement ");

        Label total = new Label("Total à payer " + PaiementStripe.total() + " DT");

        TextField tfCarte = new TextField("", "Num carte bancaire", 16, TextField.PASSWORD);
        TextField tfDatemois = new TextField("", "mois", 2, TextField.PASSWORD);
        TextField tfDateannee = new TextField("", "annee", 4, TextField.PASSWORD);

        TextField tfCVC = new TextField("", "CVC", 3, TextField.PASSWORD);
        Button btnValider = new Button("Payer Maintenant");

        add(total);

        add(tfCarte);
        add(tfDatemois);
        add(tfDateannee);
        add(tfCVC);

        add(btnValider);

////////////stripe///////////////
        btnValider.addActionListener((evt) -> {

            if (tfCarte.getText().length() > 16
                    || tfCarte.getText().length() < 16) {
                Dialog.show("Failed", "Vérifier le numéro de la carte", new Command("OK"));

            } else {

                String Abonne_stripe_id = Abonne.abonne.getStripe_id();

                if (Abonne_stripe_id == null) {
                    try {
                        Abonne_stripe_id = stm.makecus(Abonne.abonne.getNomabonne(), Abonne.abonne.getMailabonne());
                    } catch (StripeException ex) {
                    }
                    sa.ajouterstripeid(Abonne_stripe_id, Abonne.abonne.getId());

                }
                int err = 0;
                try {
                    stm.makecard(Abonne_stripe_id, tfCarte.getText(), tfDatemois.getText(), tfDateannee.getText(), tfCVC.getText());

                    int prix_final = total();

                    stm.payer(prix_final, Abonne_stripe_id);
                } catch (Exception ex) {
                    Dialog.show("", "Données erronées", new Command("OK"));

                    err = 1;
                    new PaiementStripe(res).show();

                }

                ServiceAbonnement sa = new ServiceAbonnement();
                for (TypeAbonnement ta : TypeAbonnement.Panier) {

                    sa.ajoutAbonnement_enligne(Abonne.abonne.getId(), ta.getId());

                }
                TypeAbonnement.Panier.removeAll(TypeAbonnement.Panier);

                if (err == 0) {
                    Dialog.show("Success", "Le paiement a été effectué avec succés", new Command("OK"));
                    new Acceuil(res).TypeAboForm().show();
                }

            }
        });

        tb.addCommandToOverflowMenu("Deconnexion", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new HomeForm(res).showBack();
                Abonne.abonne = null;
            }
        });

        tb.addMaterialCommandToSideMenu("Services", FontImage.MATERIAL_ADD_ALARM, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new Acceuil(res).TypeAboForm().show();
            }
        }); 
        
         tb.addCommandToLeftBar("Retour",res.getImage("back_command.png"),(evt) -> {
            
            new Panier(res).PanierForm().show();
        });


        /**
         * ******************* TOOLBAR **************************
         */
////////////stripe///////////////
    }

    public static int total() {

        int somme_totale = 0;
        for (TypeAbonnement ta : TypeAbonnement.Panier) {
            somme_totale += ta.getPrix();
        }

        return somme_totale;

    }

    /* public Form Paiement(){
    return this.form;
    }
     */
}

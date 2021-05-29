/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ads.AdsService;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Abonne;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class HomeForm extends BaseForm2 {

    //Form current;
    ConnectionRequest cnxreq = new ConnectionRequest();


    /*Garder traçe de la Form en cours pour la passer en paramètres 
    aux interfaces suivantes pour pouvoir y revenir plus tard en utilisant
    la méthode showBack*/
    public HomeForm(Resources res) {
        //current = this; //Récupération de l'interface(Form) en cours
        
        setTitle("Home");
        setLayout(BoxLayout.y()); 

        TextField tfMailCnx = new TextField("", "Saisir Votre Email");

            TextField tfMdpCnx = new TextField("", "Saisir votre mot de passe",20,TextField.PASSWORD);

        add(new Label("Choose an option"));
        Button btnConnexion = new Button("Se connecter");

        Button btnAddAbonne = new Button("S'inscrire ");

        btnAddAbonne.addActionListener(e -> new AddAbonneForm(res).show());
     
        addAll(tfMailCnx, tfMdpCnx, btnConnexion, btnAddAbonne);

        /**
         * ********CONNEXION*************
         */
        btnConnexion.addActionListener((evt) -> {

            cnxreq.setUrl(Statics.BASE_URL+"/connexionJSON?email=" + tfMailCnx.getText() + "&password=" + tfMdpCnx.getText());

            cnxreq.setPost(false);
            cnxreq.addResponseListener((evt1) -> {

                String message = "";
                try {
                    message = new String(cnxreq.getResponseData(), "utf-8");
                    JSONParser jsonp = new JSONParser();
                    try {

                        Map<String, Object> obj = jsonp.parseJSON(new CharArrayReader(new String(cnxreq.getResponseData()).toCharArray()));
                        System.err.println("*--------------*"+obj);
                        
                        
                        Abonne abo=new Abonne();
                        abo.setId((int) Float.parseFloat(obj.get("id").toString()));
                        abo.setMailabonne(obj.get("mailabonne").toString());
                        abo.setNomabonne(obj.get("prenomabonne").toString());
                        abo.setPrenomabonne(obj.get("nomabonne").toString());
                        abo.setRoles(obj.get("roles").toString());
                        Abonne.abonne=abo;
                        
                        if(abo.getRoles().equals("[abonne]")) {
                       System.out.println("Bienvenue "+Abonne.abonne.getNomabonne() + " "+Abonne.abonne.getPrenomabonne());
                       // new Acceuil(res).TypeAboForm().show();
                      new Acceuil(res).TypeAboForm().show();
                        }
                        
                        else{
                        
                           new HomeFormBack(res).show();
                        
                        }

                    } catch (IOException ex) { 
                        
                    }

                } catch (UnsupportedEncodingException ex) {

                }

            });
            NetworkManager.getInstance().addToQueueAndWait(cnxreq);

        }
        );

    }
;

/**
 * ********CONNEXION*************
 */
}

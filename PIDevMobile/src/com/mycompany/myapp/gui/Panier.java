/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Abonne;
import com.mycompany.myapp.entities.TypeAbonnement;
import java.io.IOException;

/**
 *
 * @author Zeineb
 */
public class Panier extends BaseForm2 {

    private Form PanierAbo = new Form("Panier", new FlowLayout(Component.CENTER, Component.CENTER));
    private Toolbar tb = PanierAbo.getToolbar();
    private EncodedImage enc;

    public Panier(Resources res) {

        
        Container container1 = new Container(BoxLayout.y());
        
        Button payer_button = new Button("Payer maintenant " +PaiementStripe.total()+ " DT");
         payer_button.addActionListener((evt) -> {
            new PaiementStripe(res).show();
            });
        payer_button.getStyle().setFgColor(0X649B88);
        
        
        container1.add(payer_button);
        
        
        
        
        
        for (TypeAbonnement ta : TypeAbonnement.Panier) {
            Container container2 = new Container(BoxLayout.y());
          /*  try {
                enc = EncodedImage.create("/loading.gif");
            } catch (IOException ex) {
            }*/

            String imgUrl = "http://127.0.0.1//integration//integration//projet//projet//projet//public//images//" + ta.getImage();
           //s String imgUrl =  ta.getImage();
            
            Image img = URLImage.createToStorage(enc, imgUrl, imgUrl, URLImage.RESIZE_SCALE);
            ImageViewer i = new ImageViewer(img);
            /**
             * ********** image
             */
            Label lblservice = new Label("Service : " + ta.getType());
            //     lblservice.setTextPosition(Component.LEFT);
            SpanLabel lbldesc = new SpanLabel("Desc : " + ta.getDescription());
            Label lblprix = new Label("Prix : " + ta.getPrix() + "DT");
            /**
             * ********** image
             */
            /**
             * ** buttons
             *///////////
            Button supp_panier = new Button("Supprimer du panier");
            supp_panier.getStyle().setFgColor(0X649B88);

            container2.add(i);
            container2.add(lblservice);
            container2.add(lbldesc);
            container2.add(lblprix);
            container2.add(supp_panier);
            container2.add(super.createLineSeparator(0X649B88));
            container1.add(container2);

            supp_panier.addActionListener((evt) -> {
                TypeAbonnement.Panier.remove(ta);
                new Panier(res).PanierForm().show();

            });

        }
        
        /**
         * ******************* TOOLBAR **************************
         */
        tb.addCommandToOverflowMenu("Deconnexion", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new HomeForm(res).showBack();
                Abonne.abonne = null;
            }
        });

        tb.addMaterialCommandToSideMenu("Mes abonnements", FontImage.MATERIAL_ADD_ALARM, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

            }
        });
        tb.addCommandToLeftBar("Retour",res.getImage("back_command.png"),(evt) -> {
            
            new Acceuil(res).TypeAboForm().show();
        });

        /**
         * ******************* TOOLBAR **************************
         */
        PanierAbo.add(container1);

    }

    public Form PanierForm() {

        return PanierAbo;

    }

}

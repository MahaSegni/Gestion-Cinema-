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
import com.codename1.ui.ComponentImage;
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
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Abonne;
import com.mycompany.myapp.entities.TypeAbonnement;
import com.mycompany.myapp.services.ServiceTypeAbonnement;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;

/**
 *
 * @author Zeineb
 */
public class Acceuil extends BaseForm2 {

    ServiceTypeAbonnement sta = ServiceTypeAbonnement.getInstance();

    private Form lTypeAbo = new Form("Services", new FlowLayout(Component.CENTER, Component.CENTER));
    private  Toolbar tb = lTypeAbo.getToolbar();

    private EncodedImage enc;

    public Acceuil(Resources res) {

        // Toolbar tb=new Toolbar(true);
        // setToolbar(tb);
        // setTitle("Bienvenue "+Abonne.abonne.getPrenomabonne()+" "+Abonne.abonne.getNomabonne());
        /**
         * ******************* TOOLBAR **************************
         */
        /**
         * ******************* List *****************************
         */
        Container container1 = new Container(BoxLayout.y());

        for (TypeAbonnement ta : sta.getListTypeAbonnement()) {

            Container container2 = new Container(BoxLayout.y());

            /* image*/
            try { 
                enc = EncodedImage.create("/loading.gif"); 
            } catch (IOException ex) {
            } 
            String imgUrl = "http://127.0.0.1//integration//integration//projet//projet//projet//public//images//" + ta.getImage();
           // String imgUrl =  ta.getImage();
           
            Image img = URLImage.createToStorage(enc, imgUrl, imgUrl, URLImage.RESIZE_SCALE);
            ImageViewer i = new ImageViewer(img);
            /************ image*/
            Label lblservice = new Label("Service : " + ta.getType());
            //     lblservice.setTextPosition(Component.LEFT);
            SpanLabel lbldesc = new SpanLabel("Desc : " + ta.getDescription());
            Label lblprix = new Label("Prix : " + ta.getPrix()+"DT"); 
            /************ image*/
            /****   buttons *///////////
            Button ajaupanier = new Button("Ajouter au panier");
            ajaupanier.getStyle().setFgColor(0X649B88);
            
            int panier_size=0;
            if (TypeAbonnement.Panier.size()!=0){
            panier_size=TypeAbonnement.Panier.size();
            }
            Button consultpanier = new Button("Consulter le panier ("+panier_size+" choix!)");
            consultpanier.getStyle().setFgColor(0X649B88);
            
            /****   buttons *///////////

            container2.add(i);
            container2.add(lblservice);
            container2.add(lbldesc);
            container2.add(lblprix);
            container2.add(ajaupanier);
            container2.add(consultpanier);

            container2.add(super.createLineSeparator(0X649B88));
            container1.add(container2);
            
            /********** Ajout au panier *//////////////
            ajaupanier.addActionListener((evt) -> {
            TypeAbonnement.Panier.add(ta);
            new Acceuil(res).TypeAboForm().show();
            
            });
            
            consultpanier.addActionListener((evt) -> {
//            new Acceuil(res).TypeAboForm().show();
            new Panier(res).PanierForm().show();
            });
            
            
        }

        
        /**
         * ******************* List *****************************
         */

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
         tb.addCommandToOverflowMenu("Retour", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                  new HomeFront(res).showBack();
         
            }
        });
 
        tb.addMaterialCommandToSideMenu("Services", FontImage.MATERIAL_ADD_ALARM, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
             new Acceuil(res).TypeAboForm().show();            }
        });
Form f=null;
        tb.addMaterialCommandToSideMenu("Abonnement", FontImage.MATERIAL_UPDATE, e ->  new Acceuil(res).TypeAboForm().show());
                 tb.addMaterialCommandToSideMenu("Film", FontImage.MATERIAL_UPDATE, e ->  new ListFilmForm(res).show());
  tb.addMaterialCommandToSideMenu("Reclamation", FontImage.MATERIAL_UPDATE, e ->  new  AjouterReclamation(res,f).show());
tb.addMaterialCommandToSideMenu("Maps", FontImage.MATERIAL_UPDATE, e ->new MapsDemo().start());
tb.addMaterialCommandToSideMenu("Evenement", FontImage.MATERIAL_UPDATE, e ->new EventsListForm(f).show());

        
        
        lTypeAbo.add(container1);
    }

    public Form TypeAboForm() {

        return lTypeAbo;

    }
}

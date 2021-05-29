/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Cat;
import com.mycompany.myapp.entities.Plat;
import com.mycompany.myapp.services.ServiceCat;
import com.mycompany.myapp.services.ServicePlat;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class ListPlatForm extends BaseFormP {
    
    Form current;
    public ListPlatForm(Resources res){
        super("List Plat",BoxLayout.y()); //heritage de Newsfeed & le formulaire est verctical
        
        Toolbar tb= new Toolbar(true);
        current=this;
        setToolbar(tb);
        getTitleArea().setUIID("container");
        //setTitle("Ajouter Plat");
        getContentPane().setScrollVisible(false);
        tb.addCommandToOverflowMenu("Deconnexion", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new HomeForm(res).showBack();
            }
        });
         tb.addCommandToOverflowMenu("Retour", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               new HomeFormP().show();
         
            }
        });
        //appel affichage methode
        ArrayList<Plat>list=ServicePlat.getInstance().afficherPlat();
        for(Plat rec : list){
            String urlImage= "back-logo.jpg";//statique l'image
            Image placeHolder=Image.createImage(120,90);
            EncodedImage enc = EncodedImage.createFromImage(placeHolder,false);
            URLImage urlim= URLImage.createToStorage(enc, urlImage, urlImage,URLImage.RESIZE_SCALE ); 
        
            addButton(urlim,rec.getPrixp(),rec.getNomp(),rec.getCategorie_id(), rec.getImagep(),rec.getDescription());
            
            ScaleImageLabel image = new ScaleImageLabel(urlim);
            Container containerImg = new Container();
            image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        }
    }

  
    private void addButton(Image img, int prixp, String nomp, String categorie_id, String imagep, String description) {
        
        int height=Display.getInstance().convertToPixels(11.5f);
        int width=Display.getInstance().convertToPixels(14f);
        
        Button image = new Button(img.fill(width,height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
        
       
        /* 
        TextArea ta = new TextArea(nomc);
        ta.setUIID("NewsTopLine");
        ta.setEditable(false);
        */
        
        //nomc:jadoudi
        Label nomcTxt= new Label("Nom Plat: "+nomp,"NewsTopLine2");
        Label prixpTxt= new Label("Prix: "+prixp,"NewsTopLine2");
        Label categorie_idTxt= new Label("catégorie: "+categorie_id,"NewsTopLine2");
        Label imagepTxt= new Label("Image: "+imagep,"NewsTopLine2");
        Label descriptionTxt= new Label("Description: "+description,"NewsTopLine2");
        
        
        
        cnt.add(BorderLayout.CENTER,BoxLayout.encloseY(BoxLayout.encloseX(nomcTxt)));
        cnt.add(BorderLayout.CENTER,BoxLayout.encloseY(BoxLayout.encloseX(prixpTxt)));
        cnt.add(BorderLayout.CENTER,BoxLayout.encloseY(BoxLayout.encloseX(categorie_idTxt)));
        cnt.add(BorderLayout.CENTER,BoxLayout.encloseY(BoxLayout.encloseX(imagepTxt)));
        cnt.add(BorderLayout.CENTER,BoxLayout.encloseY(BoxLayout.encloseX(descriptionTxt)));
        add(cnt);
        
    }
    
}

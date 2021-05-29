/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Cat;
import com.mycompany.myapp.services.ServiceCat;
import java.util.ArrayList;

/**
 *
 * @author mahas
 */
public class SearchCat extends BaseFormP{
    Form current;
     private TextField recherche;
     
    public SearchCat(Resources res,String s){
        super("List Catégorie",BoxLayout.y());
        
        Toolbar tb= new Toolbar(true);
        current=this;
        setToolbar(tb);
        getTitleArea().setUIID("container");
        //setTitle("Ajouter Catégorie");
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
       Label lab = new Label("             Recherche ");
add(lab);
        TextField recherche =new TextField();
               recherche.addActionListener(e -> 
        new SearchFilm(res,recherche.getText()).show()
);
       add(recherche);
        //appel affichage methode
   
       
        ArrayList<Cat>list=ServiceCat.getInstance().afficherCat();
        for(Cat rec : list){
             if (rec.getNomc().toLowerCase().trim().contains(s)) {
            String urlImage= "back-logo.jpg";//stique l'image
            Image placeHolder=Image.createImage(120,90);
            EncodedImage enc = EncodedImage.createFromImage(placeHolder,false);
            URLImage urlim= URLImage.createToStorage(enc, urlImage, urlImage,URLImage.RESIZE_SCALE ); 
            
            addButton(urlim,rec,res);
            ScaleImageLabel image = new ScaleImageLabel(urlim);
            Container containerImg = new Container();
            image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
             }
        }
    }

 

    private void addButton(Image img, Cat rec, Resources res) {
        
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
        Label nomcTxt= new Label("Nom cat: "+rec.getNomc(),"NewsTopLine2");
        createLineSeparator();
        
        
       // cnt.add(BorderLayout.CENTER,BoxLayout.encloseY(BoxLayout.encloseX(nomcTxt)));
        
        
        //Delete button
        Label lSupprimer = new Label(" ");
        lSupprimer.setUIID("NewsTopLine");
        Style supprimerStyle = new Style(lSupprimer.getUnselectedStyle());
        supprimerStyle.setFgColor(0xFF5733);
        
        FontImage supprimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprimerStyle);
        lSupprimer.setIcon(supprimerImage);
        lSupprimer.setTextPosition(RIGHT);
        
        //click delete icon
        lSupprimer.addPointerPressedListener(l -> {
            Dialog dig= new Dialog("suppression");
            
            if(dig.show("Suppression","Cette catégorie sera supprimée","Annuler","supprimer")){
                dig.dispose();
            }else{
                dig.dispose();
                
                //appeler la methode delete men cat service
                if(ServiceCat.getInstance().deleteCat(rec.getIdc())){
                    new ListCatForm(res).show();
                }
            }
        });
        
       
        //update icon
         Label lModifier = new Label(" ");
        lModifier.setUIID("NewsTopLine");
        Style modifierStyle = new Style(lModifier.getUnselectedStyle());
        modifierStyle.setFgColor(0xf7ad02);
        
        FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
        lModifier.setIcon(mFontImage);
        lModifier.setTextPosition(LEFT);
        
        lModifier.addPointerPressedListener(l -> {
           // System.out.println("hello update");
           new ModifierCatForm(res,rec).show();
        });
        
        
        
        
        
        cnt.add(BorderLayout.CENTER,BoxLayout.encloseY(BoxLayout.encloseX(nomcTxt,lModifier,lSupprimer)));
        
        add(cnt);
        
    }
    
}

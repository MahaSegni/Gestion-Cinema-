/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.charts.ChartsDemoR;

/**
 *
 * @author mahas
 */
public class HomeBack extends Form{
  

     public HomeBack() {
    }

    public HomeBack(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }

    public HomeBack(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }
    
    
    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }
    
    public Component createLineSeparator(int color) {
        Label separator = new Label("", "WhiteSeparator");
        separator.getUnselectedStyle().setBgColor(color);
        separator.getUnselectedStyle().setBgTransparency(255);
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    protected void addSideMenu1(Resources res) {
       
        Toolbar tb = getToolbar();
        Form f=null;
//        Image img = res.getImage("");
//        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
//            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
//        }
//        ScaleImageLabel sl = new ScaleImageLabel(img);
//        sl.setUIID("BottomPad");
//        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
//        
//        tb.addComponentToSideMenu(LayeredLayout.encloseIn(
//              //  sl,
//                FlowLayout.encloseCenterBottom(
//                        new Label(res.getImage("profile-pic.jpg"), "PictureWhiteBackgrond"))
//        ));
//        
        tb.addMaterialCommandToSideMenu("", FontImage.MATERIAL_UPDATE, e -> new ListFilmBack(res).show());
  
// tb.addMaterialCommandToSideMenu("Abonnement", FontImage.MATERIAL_UPDATE, e ->  new Acceuil(res).TypeAboForm().show());
                 tb.addMaterialCommandToSideMenu("Film", FontImage.MATERIAL_UPDATE, e -> new ListFilmBack(res).show());
  tb.addMaterialCommandToSideMenu("Reclamation", FontImage.MATERIAL_UPDATE, e ->  new  AfficheReclamation(res,f));
tb.addMaterialCommandToSideMenu("Cellule", FontImage.MATERIAL_UPDATE, e ->   new HomeForms().show());
tb.addMaterialCommandToSideMenu("Employe", FontImage.MATERIAL_UPDATE, e ->   new EmployeL().show());
tb.addMaterialCommandToSideMenu("Categorie", FontImage.MATERIAL_UPDATE, e ->   new HomeFormP().show());
tb.addMaterialCommandToSideMenu("Evenement", FontImage.MATERIAL_UPDATE, e ->   new AddEvent(f).show());
tb.addMaterialCommandToSideMenu("Statistique Reclamation ", FontImage.MATERIAL_UPDATE, e ->   new ChartsDemoR().start());

//        tb.addMaterialCommandToSideMenu("Profile", FontImage.MATERIAL_SETTINGS, e -> new ProfileForm(res).show());
//        tb.addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> new WalkthruForm(res).show());
    }
}

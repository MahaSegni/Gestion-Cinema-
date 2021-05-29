/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;


import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.charts.ChartDemosFormR;
// les fichiers eli taayt fehoum lil homeForm winhoum wlla att att a
/**
 *
 * @author mahas
 */
public class BaseFormR extends Form {

     public BaseFormR() {
    }

    public BaseFormR(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }

    public BaseFormR(String title, Layout contentPaneLayout) {
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

    protected void addSideMenu(Resources res, Form f) {
       
         
        Toolbar tb = getToolbar();
     
   //     tb.addMaterialCommandToSideMenu("Accueil", FontImage.MATERIAL_UPDATE, e -> new Home().show());
          tb.addMaterialCommandToSideMenu("List Reclamation", FontImage.MATERIAL_UPDATE,e -> new AfficheReclamation(res,f));
  tb.addMaterialCommandToSideMenu("Ajouter Reclamation", FontImage.MATERIAL_UPDATE, e -> new AjouterReclamation(res,f).show());
 tb.addMaterialCommandToSideMenu("Statistique Reclamation", FontImage.MATERIAL_UPDATE,  e -> new ChartDemosFormR(res,f).show());
 //tb.addMaterialCommandToSideMenu("Notification Reclamation", FontImage.MATERIAL_UPDATE,  e -> new ReactDemo().start(f));
//          tb.getToolbar().addMaterialCommandToLeftSideMenu("Accueil", " ".charAt(0), e -> new HomeForm().show());
//        f.getToolbar().addMaterialCommandToLeftSideMenu("List Reclamation", " ".charAt(0),e -> new AfficheReclamation(f));
//        f.getToolbar().addMaterialCommandToLeftSideMenu("Ajouter Reclamation", " ".charAt(0), e -> new AjouterReclamation(res,f).show());
//        f.getToolbar().addMaterialCommandToLeftSideMenu("Statistique Reclamation", " ".charAt(0), e -> new ChartDemosFormR(res,f).show());

//brby thabyt fil ajout ... jwhoum //        tb.addMaterialCommandToSideMenu("Profile", FontImage.MATERIAL_SETTINGS, e -> new ProfileForm(res).show());
//        tb.addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> new WalkthruForm(res).show());
    }
}
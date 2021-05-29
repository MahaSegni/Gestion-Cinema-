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
import com.mycompany.myapp.charts.BudgetPieChart;
import com.mycompany.myapp.charts.ChartDemosForm1;

/**
 *
 * @author mahas
 */
public class BaseForm extends Form {

     public BaseForm() {
    }

    public BaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }

    public BaseForm(String title, Layout contentPaneLayout) {
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

    protected void addSideMenu(Resources res) {
       
        Toolbar tb = getToolbar();
        Image img = res.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        tb.addComponentToSideMenu(LayeredLayout.encloseIn(
                sl,
                FlowLayout.encloseCenterBottom(
                        new Label(res.getImage("profile-pic.jpg"), "PictureWhiteBackgrond"))
        ));
        
      tb.addMaterialCommandToSideMenu("List des films", FontImage.MATERIAL_UPDATE, e -> new ListFilmBack(res).show());
          tb.addMaterialCommandToSideMenu("Ajout Film", FontImage.MATERIAL_UPDATE, e -> new AddFilmForm(res).show());
  //tb.addMaterialCommandToSideMenu("Avis", FontImage.MATERIAL_UPDATE, e -> new RatingWidget(res).show());
 tb.addMaterialCommandToSideMenu("List des salle Cinema", FontImage.MATERIAL_UPDATE, e -> new listCinemaForm(res).show());
tb.addMaterialCommandToSideMenu("Ajout Cinema", FontImage.MATERIAL_UPDATE, e -> new addCinemaForm(res).show());


tb.addMaterialCommandToSideMenu("Statistique Reservation", FontImage.MATERIAL_UPDATE, e ->new ChartDemosForm1(res).show());
//tb.addMaterialCommandToSideMenu("Maps", FontImage.MATERIAL_UPDATE, e ->new MapsDemo().start());
    //     String resPath = "/E:/mob/";
//tb.addMaterialCommandToSideMenu("QRcode", FontImage.MATERIAL_UPDATE, e ->new QrCode().startApp(res, resPath, focusScrolling));



//        tb.addMaterialCommandToSideMenu("Profile", FontImage.MATERIAL_SETTINGS, e -> new ProfileForm(res).show());
//        tb.addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> new WalkthruForm(res).show());
    }
}
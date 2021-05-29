/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;


import com.codename1.components.InteractionDialog;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

/**
 *
 * @author mahas
 */
public class RatingHappy extends BaseForm {

   public RatingHappy(Resources res) {
        super("", BoxLayout.yCenter());
          
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
       // getTitleArea().setUIID("Container");
        setTitle("Avis");
     //  getContentPane().setScrollVisible(false);
        
       super.addSideMenu(res);
       //+ Display.getInstance().getProperty("Happy Park ", "The App")
       
        
//          Dialog.show("Avis", "Would you mind rating us in the appstore?", "Oui", "Non Merci");
         Label star = new Label("Votre avis nous interesse ");
       TextField desc = new TextField("", "Description", 255, TextArea.ANY);
       Slider rate = createStarRankSlider();
       add(star);
       add(desc);
 //add(FlowLayout.encloseCenter(createStarRankSlider()));
     add(FlowLayout.encloseCenterMiddle(rate));

   System.out.println(rate.getText());
               Button image = new Button("Envoyer");
      Style buttonStyle = image.getAllStyles();
buttonStyle.setBorder(Border.createEmpty());
      buttonStyle.setFgColor(0xff);
   

       add(image);
       
    }


private void initStarRankStyle(Style s, Image star) {
    s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
    s.setBorder(Border.createEmpty());
    s.setBgImage(star);
    s.setBgTransparency(0);
}

private Slider createStarRankSlider() {
    Slider starRank = new Slider();
    starRank.setEditable(true);
    starRank.setMinValue(0);
    starRank.setMaxValue(10);//"native:mainLight", "native:mainLight"
     int fontSize = Display.getInstance().convertToPixels(3);
    Font fnt = Font.createTrueTypeFont("Handlee", "Handlee-Regular.ttf").
                      derive(fontSize, Font.STYLE_PLAIN);
    Style s = new Style(0xffff33, 0, fnt, (byte)0);
    Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
    s.setOpacity(100);
    s.setFgColor(0);
    Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
    initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
    initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
    initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
    initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
    starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
    return starRank;
}

}

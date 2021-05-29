/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Film;
import com.mycompany.myapp.services.ServiceFilm;
import java.util.ArrayList;

/**
 *
 * @author mahas
 */
public class SearchBack extends BaseForm{
    private TextField recherche;
    public  SearchBack(Resources res,String s){
         super("Most Popular", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        getContentPane().setScrollVisible(false);
        super.addSideMenu(res);
Label lab = new Label("             Recherche ");
add(lab);
        TextField recherche = new TextField();
            add(recherche);
          recherche.addActionListener(e -> 
        new SearchBack(res,recherche.getText()).show() );
           ArrayList<Film> list= ServiceFilm.getInstance().getAllTasks();
         for (Film f : list) {
            if (f.getNomfilm().toLowerCase().trim().contains(s)) {
               addButton(res.getImage(f.getFilename()),f.getNomfilm(),f.getDescriptionf(),true,"11","9");
            }
        }
    }

      private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
    }
     private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        Label likes = new Label(likesStr);
        Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
        likes.setIcon(heartImage);
        likes.setTextPosition(RIGHT);
        Label text1 = new Label(text);
        Style heartStyle1 = new Style(text1.getUnselectedStyle());
        Label comments = new Label(commentsStr);
        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
        Container page1 = 
            LayeredLayout.encloseIn(
                image,
                overlay,
                BorderLayout.south(
                    BoxLayout.encloseY(
                          text1,
                            FlowLayout.encloseIn(likes, comments),
                            spacer
                        )
                )
            );
        swipe.addTab("", page1);
        
    }
    
 private void addButton(Image img, String title, String title1, boolean liked, String likeCount, String commentCount) {
       int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);
       cnt.setLeadComponent(image);
       
       TextArea ta = new TextArea(title);
       ta.setUIID("NewsTopLine");
       ta.setEditable(false);
       TextArea ta1 = new TextArea(title1);
       ta1.setUIID("NewsTopLine");
       ta1.setEditable(false);
Label likes = new Label(likeCount);
        Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
        likes.setIcon(heartImage);
        likes.setTextPosition(RIGHT);
       Label comments = new Label(commentCount);
        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                      ta,ta1,
                       BoxLayout.encloseX(likes, comments)
               ));
       add(cnt);
       image.addActionListener(e -> ToastBar.showMessage(title, FontImage.MATERIAL_INFO));
   }
    
    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if(b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
    private void addim(Button image) {
       int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
        image = new Button("Réserver");
       image.setUIID("Label");
      Container cnt = BorderLayout.east(image);
       cnt.setLeadComponent(image);
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
               ));
       add(cnt);}
}

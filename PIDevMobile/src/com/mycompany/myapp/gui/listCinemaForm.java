/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;

import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;

import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Slider;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Cinema;
import com.mycompany.myapp.services.ServiceCinema;

import java.util.ArrayList;
import java.util.Date;


/**
 *
 * @author mahas
 */
public class listCinemaForm extends BaseForm{

    public listCinemaForm(Resources res) {
                 super("", BoxLayout.yCenter());
          
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
       // getTitleArea().setUIID("Container");
        setTitle("Most Popular");
       getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
      
        Tabs swipe = new Tabs();
Label spacer3 = new Label();
        Label spacer1 = new Label();
        Label spacer2 = new Label();
Label spacer4 = new Label();
Label l = new Label("  ");add(l);
         Label Description = new Label("   ");add(Description);
         Label Desc = new Label("    ");add(Desc);
          Button b= new Button ("Deconnexion");
        b.addActionListener(e->
            new HomeForm(res).showBack()
            
        );
        add(b);//
        Button b1= new Button ("Retour");
        b1.addActionListener(e->
            new HomeFormBack(res).show()
        );add(b1);
addTab(swipe, res.getImage("bosslevel.jpg"), spacer4, " 15 Likes  ", " 85 Comments"," 101 mins |Action ");
    addTab(swipe, res.getImage("lion.jpg"), spacer1, " 15 Likes  ", " 85 Comments"," 118 mins |Adventure ");
        addTab(swipe, res.getImage("1610136115754_Poster.jpg"), spacer2, " 15 Likes  ", " 85 Comments"," 104 mins |Drama");
        addTab(swipe, res.getImage("raya.jpg"), spacer3, " 100 Likes  ", " 66 Comments", " 108 mins |Adventure ");
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        
        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
        
       Component.setSameSize(radioContainer, spacer1, spacer2);
       add(LayeredLayout.encloseIn(swipe, radioContainer));
        
        ButtonGroup barGroup = new ButtonGroup();
        RadioButton all = RadioButton.createToggle("All", barGroup);
        all.setUIID("SelectBar");
        RadioButton featured = RadioButton.createToggle("Featured", barGroup);
        featured.setUIID("SelectBar");
        RadioButton popular = RadioButton.createToggle("", barGroup);
        popular.setUIID("SelectBar");
        RadioButton myFavorite = RadioButton.createToggle("My Favorites", barGroup);
        myFavorite.setUIID("SelectBar");
        Label arrow = new Label();
        
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, all,popular, myFavorite),
                FlowLayout.encloseBottom(arrow)
        ));
        
        all.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(all, arrow);
        });
        bindButtonSelection(all, arrow);
//        bindButtonSelection(featured, arrow);
//        bindButtonSelection(popular, arrow);
        bindButtonSelection(myFavorite, arrow);
        myFavorite.addChangeListener(e -> new AlertAvis(res).show());
        
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });

ArrayList<Cinema> list= ServiceCinema.getInstance().getAllTasks();
for(Cinema f : list ){
 Button image = new Button("Reserver");
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);
       cnt.setLeadComponent(image);
    addButton(f.getNum(),f.getNbr(),true,"11","9");
   
    image.addActionListener(ev ->{
               
                    Dialog.show("Alert", "Votre reservation est confirmer ", "OK", null);
});
 addim(image);
Button stat = new Button("Statistique");
       addsupp(stat);
      stat.addActionListener((e) -> new RatingHappy(res).show()
      );
    }
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

    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
        
        
    }
     private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
//        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
//        if(img.getHeight() < size) {
//            img = img.scaledHeight(size);
//        }
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
//        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
//            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
//        }
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
                          //  new SpanLabel(text1, "LargeWhiteText"),
                           text1,
                            FlowLayout.encloseIn(likes, comments),
                            spacer
                        )
                )
            );

        swipe.addTab("", page1);
        
    }
    
 private void addButton( int fil,int fil1, boolean liked, String likeCount, String commentCount) {
       int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
       Button image = new Button();
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);
       cnt.setLeadComponent(image);
       
       TextArea ta = new TextArea(String.valueOf(fil));
       ta.setUIID("NewsTopLine");
       ta.setEditable(false);
       TextArea ta1 = new TextArea(String.valueOf(fil1));
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
       image.addActionListener(e -> ToastBar.showMessage("", FontImage.MATERIAL_INFO));
   }
    
    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if(b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }

    private void addCinema(SpanLabel sp) {
       int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
       Button image = new Button();
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);
       cnt.setLeadComponent(image);
       TextArea ta = new TextArea(sp.toString());
       
       
       
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                       ta
                      // BoxLayout.encloseX(likes, comments)
               ));
       add(cnt);
    //   image.addActionListener(e -> ToastBar.showMessage(title, FontImage.MATERIAL_INFO));
    }

    private void addButton(Image img, int fil,int fil1, Date f) {
     int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
      Container cnt = BorderLayout.west(image);
       cnt.setLeadComponent(image);
       TextArea ta = new TextArea();
       
       Label l= new Label("fil");
       Label l2= new Label("fil1");Label l3= new Label("f");
       ta.setUIID("NewsTopLine");
       ta.setEditable(false);
       cnt.add(BorderLayout.CENTER, 
               
               BoxLayout.encloseY(
                       l,l2
                     
               ));
       add(cnt);
       image.addActionListener(e -> ToastBar.showMessage("", FontImage.MATERIAL_INFO));
    
    }
private void addsupp(Button image) {
       int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
        image = new Button("Stat");
       image.setUIID("Label");
      Container cnt = BorderLayout.west(image);
       cnt.setLeadComponent(image);
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
               ));
       add(cnt);
       
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
       add(cnt);
      
  
    } }
     


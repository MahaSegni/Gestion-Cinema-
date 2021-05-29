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
import com.codename1.ui.TextField;
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
import com.mycompany.myapp.entities.Film;
import com.mycompany.myapp.services.ServiceFilm;
import java.util.ArrayList;
import com.codename1.notifications.LocalNotification;

/**
 *
 * @author mahas
 */
public class ListFilmBack extends BaseForm{
   // private TextField recherche;
      public ListFilmBack (Resources res) {
          super("Film", BoxLayout.yCenter());
          
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
      //  setTitle("Most Popular");
       getTitleArea().setUIID("Container");
       
      // getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
       Button b= new Button ("Deconnexion");
        b.addActionListener(e->
            new HomeForm(res).showBack()
            
        );
        add(b);//
        Button b1= new Button ("Retour");
        b1.addActionListener(e->
            new HomeFormBack(res).show()
        );add(b1);
Label lab = new Label("             Recherche ");
add(lab);
        TextField recherche =new TextField();
       
       add(recherche);
        String rechercheTexte = recherche.getText().toLowerCase().trim();
//        tb.addSearchCommand(e -> 
//        new SearchFilm(res,rechercheTexte).show()
//);
             recherche.addActionListener(e -> 
        new SearchBack(res,recherche.getText()).show()
);
            Button qrcode = new Button("qr");
     qrcode.addActionListener(e-> new QrCode().startApp(res, rechercheTexte, focusScrolling) );
     add(qrcode);
ArrayList<Film> list= ServiceFilm.getInstance().getAllTasks();
for(Film f : list ){
    addButton(res.getImage(f.getFilename()),f.getNomfilm(),f.getDescriptionf(),true,"11","9");
   // System.err.println("id : "+f.getId_film()+"nom: "+f.getNomfilm()+"desc :"+f.getDescriptionf());
    Button deletebtn = new Button("Delete");
final TextField body = new TextField("test");
final TextField title = new TextField("test 2 ");
final TextField id = new TextField("11");
                deletebtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                       
                        if (ServiceFilm.getInstance().supprimerFilm(f)) {
                          //  Dialog.show("Success", "film supprimé avec succès ! ", "Ok", null);
                            LocalNotification n = new LocalNotification();
                n.setAlertBody(body.getText());
                n.setAlertTitle(title.getText());
                n.setId(id.getText());
                int repeatType = LocalNotification.REPEAT_NONE;
                 Display.getInstance().scheduleLocalNotification(n, System.currentTimeMillis() + 10 * 1000, repeatType);
                      System.out.println("Received local notification "+"11"+" in callback localNotificationReceived");  
                 new ListFilmBack(res).show();
                        } else {
                            Dialog.show("ERROR", "Error , film n'est pas supprimé ", "Ok", null);
                        }
                    }
                });
                Button editbtn = new Button("Edit");

                editbtn.addActionListener(e->
                        new EditFilm(res, f).show()
                );
                add(deletebtn);
                add(editbtn);
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
//       Label likes = new Label(likeCount + " Likes  ", "NewsBottomLine");
//       likes.setTextPosition(RIGHT);
//       if(!liked) {
//           FontImage.setMaterialIcon(likes, FontImage.MATERIAL_FAVORITE);
//       } else {
//           Style s = new Style(likes.getUnselectedStyle());
//           s.setFgColor(0xff2d55);
//           FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s);
//           likes.setIcon(heartImage);
//       }
//       Label comments = new Label(commentCount + " Comments", "NewsBottomLine");
//       FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);
       Label comments = new Label(commentCount);
        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
//        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
//            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
//        }
        
       
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

    private void addfilm(SpanLabel sp) {
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

    private void addButton(Image img, String fil,String fil1, Film f) {
     int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
      Container cnt = BorderLayout.west(image);
       cnt.setLeadComponent(image);
       TextArea ta = new TextArea();
       Label l= new Label(fil);
       Label l2= new Label(fil1);//Label l3= new Label(fil2);
       ta.setUIID("NewsTopLine");
       ta.setEditable(false);

       
       
       cnt.add(BorderLayout.CENTER, 
               
               BoxLayout.encloseY(
                       l,l2
                     
               ));
       add(cnt);
      
       image.addActionListener(e -> ToastBar.showMessage(f.getNomfilm(), FontImage.MATERIAL_INFO));
    
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
      
     //  image.addActionListener(e ->
             //  ToastBar.showMessage("", FontImage.MATERIAL_INFO)
             
//               Message m = new Message("Merci d'avoir réserver un ticket");
//Display.getInstance().sendMessage(new String[] {"mahasegni00@gmail.com"}, "Confirmation Reservation", m);

//Message m = new Message("<html><body>Check out <a href=\"https://www.codenameone.com/\">Codename One</a></body></html>");
//m.setMimeType(Message.MIME_HTML);
//
//
//boolean success = m.sendMessageViaCloudSync("Happy Park", "mahasegni00@gmail.com", "Maha Segni", "Confirmation Reservation",
//                            "Check out Codename One at https://www.codenameone.com/");

     //  );
    }
   
}
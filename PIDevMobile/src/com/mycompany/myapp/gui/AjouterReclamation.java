/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Reclamation;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.L10NManager;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.Toolbar;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Abonne;
import com.mycompany.myapp.services.ServiceReclamation;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Adem
 */
public class AjouterReclamation extends BaseFormR {

    L10NManager l10n = L10NManager.getInstance();
    String sysdate2 = l10n.formatDateTimeShort(new Date()).toString();

    private String date_review = "2021-05-21 10:50:40"; //"2021-05-03 17:50:40"
    private String myUsername = "ademclient";

    ArrayList<Float> myList = new ArrayList<>();
 

    public AjouterReclamation(Resources res,Form f) {
         super("", BoxLayout.y());
        
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
      // getTitleArea().setUIID("Container");
      setTitle("Ajouter Reclamation");
        getContentPane().setScrollVisible(false);// bnsba lil les fichiers lokhrin fmch whd mnhoum aamltlo yaadik lil ajout ? wla kn l edit aamla nfs l page ? l affichage
       //super.addSideMenu(res,f); 
     
        Label l = new Label("  ");
         Label l2 = new Label("  ");
         add(l);add(l2);
           Button b= new Button ("Deconnexion");
        b.addActionListener(e->
            new HomeForm(res).showBack()
            
        );
        add(b);//
        Button b1= new Button ("Retour");
        b1.addActionListener(e->
            new Acceuil(res).TypeAboForm().show()
        );add(b1);
        myList.add((float) 0);
        myList.add((float) 1);
        myList.add((float) 2);
        myList.add((float) 3);
        myList.add((float) 4);
        myList.add((float) 5);

        //setTitle("Ajouter Reclamation");
        //setLayout(BoxLayout.y());
        Font fnt = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);

//        TextField tfClientname = new TextField("", myUsername);
//        tfClientname.setText(myUsername);
//        tfClientname.setEditable(false);
         
//        
//        ComboBox RatingCombo = new ComboBox();
//         ArrayList<Reclamation> categories = ServiceReclamation.getInstance().getAllTasks();
//            for (Reclamation t : categories) {
//                RatingCombo.addItem(t.getAbonne());
//            }

        
        TextArea tfDescReview = new TextArea(4, 100);
        tfDescReview.setHint("Your Description...");
        TextField tfCoachname = new TextField("", "Field choosen...");
        TextField tfrate = new TextField("", "Rate our app...");
        TextField tfdatereview = new TextField("", date_review);
        tfdatereview.setText(date_review);
        tfdatereview.setEditable(false);
        
       

//        ComboBox RatingCombo = new ComboBox();
//        
//            for (int i = 0; i < myList.size(); i++) {
//                RatingCombo.addItem(myList.get(i));
//            }
            
//        LocalNotification n = new LocalNotification();
//        n.setId("demo-notification");
//        n.setAlertBody("It's time to take a break and look at me");
//        n.setAlertTitle("Break Time!");
//        n.setAlertSound("/notification_sound_bells.mp3"); //file name must begin with notification_sound
//start

//end

        Button btnValider = new Button("Ajouter Reclamation");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfDescReview.getText().length() == 0) || (tfCoachname.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", "OK", null);
                } else {
                    try {
                        Reclamation r = new Reclamation();

                       // r.setNom_client_review(tfClientname.getText());
                        r.setDescription(tfDescReview.getText());
                        r.setField(tfCoachname.getText());
                        r.setRate(Double.parseDouble(tfrate.getText()));
                        r.setDatee(tfdatereview.getText());
                        
                         r.setAbonne_id(Abonne.abonne.getId());
                       
                      //  r.setRating((float) RatingCombo.getSelectedItem());
                       
                        if(ServiceReclamation.getInstance().AjoutReclamation(r)){
                            //Display.getInstance().scheduleLocalNotification(n, System.currentTimeMillis() + 10 * 1000, LocalNotification.REPEAT_NONE);
                             Dialog.show("Success", "Connection accepted", "OK", null);
                             tfDescReview.setText("");
                             tfCoachname.setText("");
                               if(Display.getInstance().isMinimized()) {
                                     Display.getInstance().callSerially(() -> {
                                       Dialog.show("Welcome", "Thanks for arriving", "OK", null);
                                     });
                           } else {
                                  LocalNotification ln = new LocalNotification();
                                  ln.setId("LnMessage");
                                 ln.setAlertTitle("Welcome");
                                  ln.setAlertBody("Thanks for arriving!");
                                     Display.getInstance().scheduleLocalNotification(ln, System.currentTimeMillis() + 10 * 1000, LocalNotification.REPEAT_NONE);
                                   }
       
                             }
                             
                        else
                             Dialog.show("Error", "Server ERROR", "OK", null);
                         

                    } catch (NumberFormatException e) {
                        Dialog.show("Alert", "Please fill all the fields", "OK", null);
                    }

                }

            }
        });
        addAll(tfDescReview, tfCoachname, tfdatereview, tfrate,btnValider);
        setTransitionOutAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, false, 250));
//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
//                 e -> new HomeForm().show()); // Revenir vers l'interface précédente
//        getToolbar().addCommandToOverflowMenu("Back", null, ev->new HomeForm().show());

    }

    AjouterReclamation() {
       
    }
public void showForm(Form f) {
  Form hi = new Form("Star Slider", new BoxLayout(BoxLayout.Y_AXIS));
  hi.add(FlowLayout.encloseCenter(createStarRankSlider()));
  hi.show();
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
    starRank.setMaxValue(10);
    Font fnt = Font.createTrueTypeFont("native:mainLight", "native:mainLight").
            derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
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

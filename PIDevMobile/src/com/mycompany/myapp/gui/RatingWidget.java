/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.InteractionDialog;
import com.codename1.components.ScaleImageLabel;
import com.codename1.io.Preferences;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Slider;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.messaging.Message;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.AvisClient;
import static com.mycompany.myapp.gui.AddFilmForm.sendMessage;
import com.mycompany.myapp.services.ServiceAvisClient;
import java.util.ArrayList;
import javafx.scene.image.ImageView;
/**
 *
 * @author mahas
 */
public class RatingWidget extends BaseForm {
    private static RatingWidget instance;
    private boolean running;

    private int timeForPrompt;

    private String appstoreUrl;
    private String supportEmail;

  public  RatingWidget(Resources res) {
       super("", BoxLayout.yCenter());
          
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Avis");
     //  getContentPane().setScrollVisible(false);
        
       super.addSideMenu(res);
        Label star = new Label(" ");
      
      Label desc = new Label(" ");
     
      Image img= res.getImage("logo_happy.png");
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        add(sl);
       add(star);
       add(desc);
       ArrayList<Float> myList = new ArrayList<>();

   
        myList.add((float) 0);
        myList.add((float) 1);
        myList.add((float) 2);
        myList.add((float) 3);
        myList.add((float) 4);
        myList.add((float) 5);

        Label star1 = new Label("Votre avis nous interesse ");
     
        Font fnt = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
        TextArea tfDescReview = new TextArea(4, 100);
        tfDescReview.setHint("Your Description...");
         Label star2 = new Label("Note :  ");
     
        ComboBox RatingCombo = new ComboBox();
            for (int i = 0; i < myList.size(); i++) {
                RatingCombo.addItem(myList.get(i));
            }
            
//        LocalNotification n = new LocalNotification();
//        n.setId("demo-notification");
//        n.setAlertBody("It's time to take a break and look at me");
//        n.setAlertTitle("Break Time!");
//        n.setAlertSound("/notification_sound_bells.mp3"); //file name must begin with notification_sound

        Button btnValider = new Button("Envoyer");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfDescReview.getText().length() == 0)  {
                    Dialog.show("Alert", "Please fill all the fields", "OK", null);
                } else {
                    try {
                        AvisClient r = new AvisClient();

                        r.setDescR(tfDescReview.getText());
                        
                        r.setRating((float) RatingCombo.getSelectedItem());
                        if( ServiceAvisClient.getInstance().addTask(r)){ 
//LocalNotification n = new LocalNotification();
//        n.setId("demo-notification");
//        n.setAlertBody("It's time to take a break and look at me");
//        n.setAlertTitle("Break Time!");
//        n.setAlertSound("/notification_sound_bells.mp3"); //file name must begin with notification_sound
//
//
//        Display.getInstance().scheduleLocalNotification(
//                n,
//                System.currentTimeMillis() + 10 * 1000, // fire date/time
//                LocalNotification.REPEAT_MINUTE  // Whether to repeat and what frequency
    //   );
                           // Display.getInstance().scheduleLocalNotification(n, System.currentTimeMillis() + 10 * 1000, LocalNotification.REPEAT_NONE);
                            
                           Dialog.show("Success", "Merci Pour votre visite ", "OK", null);
                           String s = "Merci de confirmer votre avis"; 
Message msg = new Message(s);
msg.setMimeType(Message.MIME_HTML);
sendMessage(new String[] {"mahasegni00@gmail.com"}, " Confirmation ", msg);


                             tfDescReview.setText("");
                 new ListFilmForm(res).show();
                             }
                             
                        else
                             Dialog.show("Error", "Server ERROR", "OK", null);
                         

                    } catch (NumberFormatException e) {
                        Dialog.show("Alert", "Please fill all the fields", "OK", null);
                    }

                }

            }
        });
        addAll(star1,tfDescReview, star2,RatingCombo ,btnValider);
        setTransitionOutAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, false, 250));
}
}











































///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.mycompany.myapp.gui;
//
//import com.codename1.components.InteractionDialog;
//import com.codename1.io.Preferences;
//import com.codename1.io.Util;
//import com.codename1.ui.Button;
//import com.codename1.ui.Dialog;
//import com.codename1.ui.Display;
//import com.codename1.ui.Font;
//import com.codename1.ui.FontImage;
//import com.codename1.ui.Form;
//import com.codename1.ui.Image;
//import com.codename1.ui.Slider;
//import com.codename1.ui.geom.Dimension;
//import com.codename1.ui.layouts.BorderLayout;
//import com.codename1.ui.layouts.FlowLayout;
//import com.codename1.ui.layouts.GridLayout;
//import com.codename1.ui.plaf.Border;
//import com.codename1.ui.plaf.Style;
//import com.codename1.ui.util.Resources;
//import com.codename1.messaging.Message;
//import com.codename1.ui.Toolbar;
//import com.codename1.ui.layouts.BoxLayout;
///**
// *
// * @author mahas
// */
//public class RatingWidget extends BaseForm {
//    private static RatingWidget instance;
//    private boolean running;
//
//    private int timeForPrompt;
//
//    private String appstoreUrl;
//    private String supportEmail;
//
//  public  RatingWidget(Resources res) {
//       super("", BoxLayout.yCenter());
//          
//        Toolbar tb = new Toolbar(true);
//        setToolbar(tb);
//        getTitleArea().setUIID("Container");
//        setTitle("Avis");
//     //  getContentPane().setScrollVisible(false);
//        
//       super.addSideMenu(res);
//       Dialog.show("Rate On Store", "Would you mind rating us in the appstore?", "Go To Store", "Dismiss");
////    InteractionDialog id = new InteractionDialog("Please Rate "  + Display.getInstance().getProperty("AppName", "The App"));
////        int height = id.getPreferredH();
////        Form f = Display.getInstance().getCurrent();
////        id.setLayout(new BorderLayout());
////        Slider rate = createStarRankSlider();
////        Button ok = new Button("OK");
////        Button no = new Button("No Thanks");
////        id.add(BorderLayout.CENTER, FlowLayout.encloseCenterMiddle(rate)).
////                add(BorderLayout.SOUTH, GridLayout.encloseIn(2, no, ok));
////        id.show(f.getHeight()  - height - f.getTitleArea().getHeight(), 0, 0, 0);
////        no.addActionListener(e -> id.dispose());
////        ok.addActionListener(e -> {
////            id.dispose();
////            if(rate.getProgress() >= 9) {
////                if(Dialog.show("Rate On Store", "Would you mind rating us in the appstore?", "Go To Store", "Dismiss")) {
////                    Display.getInstance().execute(appstoreUrl);
////                }
////            } else {
////                if(Dialog.show("Tell Us Why?", "Would you mind writing us a short message explaining how we can improve?", "Write", "Dismiss")) {
////                    Message m = new Message("Heres how you can improve  " + Display.getInstance().getProperty("AppName", "the app"));
////                    Display.getInstance().sendMessage(new String[] {supportEmail}, "Improvement suggestions for " + Display.getInstance().getProperty("AppName", "your app"), m);
////                }
////            }
////        });
//    }
//
//    private void init(String appstoreUrl, String supportEmail) {
//        this.appstoreUrl = appstoreUrl;
//        this.supportEmail = supportEmail;
//        running = true;
//        Thread t = Display.getInstance().startThread(() -> checkTimerThread(), "Review thread");
//        t.start();
//    }
//    public void start(){
//    if(getAppstoreURL() != null) {
//        RatingWidget.bindRatingListener(180000, getAppstoreURL(), "mahasegni00@gmail.com.com");
//    }
//    
//}
//public  String getAppstoreURL() {
//    if(Display.getInstance().getPlatformName().equals("ios")) {
//        return "https://itunes.apple.com/us/app/kitchen-sink-codename-one/id635048865";
//    }
//    if(Display.getInstance().getPlatformName().equals("and")) {
//        return "https://play.google.com/store/apps/details?id=com.codename1.demos.kitchen";
//    }
//    return null;
//}
//    void checkTimerThread() {
//        while(running) {
//            long lastTime = System.currentTimeMillis();
//            int timeEllapsedInApp = Preferences.get("timeElapsedInApp", 0);
//            Util.wait(this, timeForPrompt - timeEllapsedInApp);
//            long total = System.currentTimeMillis() - lastTime;
//            if(total + timeEllapsedInApp < timeForPrompt) {
//                Preferences.set("timeElapsedInApp", (int)(total + timeEllapsedInApp));
//            } else {
//                Display.getInstance().callSerially(() -> showReviewWidget());
//                running = false;
//                instance  = null;
//                return;
//            }
//        }
//    }
//
//    void showReviewWidget() {
//        // block this from happening twice
//        Preferences.set("alreadyRated", true);
//        InteractionDialog id = new InteractionDialog("Please Rate "  + Display.getInstance().getProperty("AppName", "The App"));
//        int height = id.getPreferredH();
//        Form f = Display.getInstance().getCurrent();
//        id.setLayout(new BorderLayout());
//        Slider rate = createStarRankSlider();
//        Button ok = new Button("OK");
//        Button no = new Button("No Thanks");
//        id.add(BorderLayout.CENTER, FlowLayout.encloseCenterMiddle(rate)).
//                add(BorderLayout.SOUTH, GridLayout.encloseIn(2, no, ok));
//        id.show(f.getHeight()  - height - f.getTitleArea().getHeight(), 0, 0, 0);
//        no.addActionListener(e -> id.dispose());
//        ok.addActionListener(e -> {
//            id.dispose();
//            if(rate.getProgress() >= 9) {
//                if(Dialog.show("Rate On Store", "Would you mind rating us in the appstore?", "Go To Store", "Dismiss")) {
//                    Display.getInstance().execute(appstoreUrl);
//                }
//            } else {
//                if(Dialog.show("Tell Us Why?", "Would you mind writing us a short message explaining how we can improve?", "Write", "Dismiss")) {
//                    Message m = new Message("Heres how you can improve  " + Display.getInstance().getProperty("AppName", "the app"));
//                    Display.getInstance().sendMessage(new String[] {supportEmail}, "Improvement suggestions for " + Display.getInstance().getProperty("AppName", "your app"), m);
//                }
//            }
//        });
//    }
//
//    private void initStarRankStyle(Style s, Image star) {
//        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
//        s.setBorder(Border.createEmpty());
//        s.setBgImage(star);
//        s.setBgTransparency(0);
//    }
//
//    private Slider createStarRankSlider() {
//        Slider starRank = new Slider();
//        starRank.setEditable(true);
//        starRank.setMinValue(0);
//        starRank.setMaxValue(10);
//        Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
//                derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
//        Style s = new Style(0xffff33, 0, fnt, (byte)0);
//        Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
//        s.setOpacity(100);
//        s.setFgColor(0);
//        Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
//        initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
//        initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
//        initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
//        initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
//        starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
//        return starRank;
//    }
//
//    /**
//     * Binds the rating widget to the UI if the app wasn't rated yet
//     *
//     * @param time time in milliseconds for the widget to appear
//     * @param appstoreUrl the app URL in the store
//     * @param supportEmail support email address if the rating is low
//     */
//    public static void bindRatingListener(int time, String appstoreUrl, String supportEmail) {
//        if(Preferences.get("alreadyRated", false)) {
//            return;
//        }
//        Resources res = null;
//        instance = new RatingWidget(res);
//        instance.timeForPrompt = time;
//        instance.init(appstoreUrl, supportEmail);
//    }
//
//    /**
//     * This should be invoked by the stop() method as we don't want rating countdown to proceed when the app isn't
//     * running
//     */
//    public static void suspendRating() {
//        if(instance != null) {
//            synchronized(instance) {
//                instance.notify();
//            }
//            instance.running  = false;
//            instance = null;
//        }
//    }
//}
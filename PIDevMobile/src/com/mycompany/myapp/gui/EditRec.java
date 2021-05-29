/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.l10n.L10NManager;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceReclamation;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Adem
 */
public class EditRec extends BaseFormR {

    L10NManager l10n = L10NManager.getInstance();
    String sysdate2 = l10n.formatDateTimeShort(new Date()).toString();

    private String date_review = "2021-05-03 17:50:40";
    private String myUsername = "ademclient";

    ArrayList<Float> myList = new ArrayList<>();

    public EditRec(Form previous, Reclamation r,Resources res) {
         super("", BoxLayout.y());
        
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
      // getTitleArea().setUIID("Container");
      setTitle("Modifier Reclamation");
        getContentPane().setScrollVisible(false);
       
        Form f = null;
        
       super.addSideMenu(res,f); 
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
        myList.add((float) 0);
        myList.add((float) 1);
        myList.add((float) 2);
        myList.add((float) 3);
        myList.add((float) 4);
        myList.add((float) 5);
        
        setTitle("Edit My  Review");
        setLayout(BoxLayout.y());
        Font fnt = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
        
        TextField tfClientname = new TextField("", myUsername);
        tfClientname.setText(myUsername);
        tfClientname.setVisible(false);
        //tfClientname.setEditable(false);
        

        TextArea tfDescReview = new TextArea(4, 100);
        tfDescReview.setText(r.getDescription());
        TextField tfCoachname = new TextField(r.getField());
        tfCoachname.setEditable(false);
        TextField tfdatereview = new TextField("", date_review);
        tfdatereview.setText(date_review);
        tfdatereview.setEditable(false);
        

        ComboBox RatingCombo = new ComboBox();
        
            for (int i = 0; i < myList.size(); i++) {
                RatingCombo.addItem(myList.get(i));
            }
            
            Button btnValider = new Button("Edit Review");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfDescReview.getText().length() == 0) || (tfCoachname.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", "OK", null);
                } else {
                    try {

                        r.setDescription(tfDescReview.getText());
                        
                        r.setDatee(tfdatereview.getText());
                        r.setRate((float) RatingCombo.getSelectedItem());
                        if( ServiceReclamation.getInstance().editReview(r)){
                               LocalNotification n = new LocalNotification();
        n.setId("demo-notification");
        n.setAlertBody("It's time to take a break and look at me");
        n.setAlertTitle("Break Time!");
        n.setAlertSound("/notification_sound_bells.mp3"); //file name must begin with notification_sound


                Display.getInstance().scheduleLocalNotification(
                n,
                System.currentTimeMillis() + 10 * 1000, // fire date/time
                LocalNotification.REPEAT_MINUTE  // Whether to repeat and what frequency
        );
                             Dialog.show("Success", "Connection accepted", "OK", null);
                            // AfficheReclamation f = new AfficheReclamation(previous);
                            new AfficheReclamation(res,f);
                          //  f.setTransitionOutAnimator(CommonTransitions.createEmpty());
                            //  f.show();
                            //maha l affichage mte3ik kifeh liste tht baadho kima ili kenit 3andi ?? ki ili affichage ili kenet 3andi akali writholi fil video eyo ismaa l stat c bn !!
                           
                             }
                             
                        else
                             Dialog.show("Error", "Server ERROR", "OK", null);

                    } catch (NumberFormatException e) {
                        Dialog.show("Alert", "Please fill all the fields", "OK", null);
                    }

                }

            }
        });
        addAll(tfClientname, tfDescReview, tfCoachname, RatingCombo, tfdatereview, btnValider);
        setTransitionOutAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, false, 250));
//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
//                 e -> new AfficheReclamation(previous));
            
            
            
    }
    

}

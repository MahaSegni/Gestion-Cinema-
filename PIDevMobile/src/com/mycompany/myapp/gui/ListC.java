/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Cellule;
import com.mycompany.myapp.services.ServiceCellule;

import java.util.ArrayList;

/**
 *
 * @author Administrateur
 */
public class ListC extends Form{
     Form current;
   // private String Username = "ademclient";
     
     public ListC(Form f) {
        // super("", BoxLayout.y());
       
        Toolbar tb = new Toolbar(true);
       // setToolbar(tb);
      // getTitleArea().setUIID("Container");
    //  setTitle("Ajouter Cellule");
       // getContentPane().setScrollVisible(false);
      // super.addSideMenu(res,f);
   
        Form tempForm = new Form();

        tempForm.setTitle("List des Cellules");
        tempForm.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        tempForm.setTransitionOutAnimator(CommonTransitions.createEmpty());
        GridLayout gridLayout = new GridLayout(1, 6);
        
      
        Font fnt = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
       // Label labelCoachname = new Label("Abonnee");
        Label labelDate = new Label("IdCellule");
        Label labelRating = new Label("Dispo");
       
     //   labelCoachname.getUnselectedStyle().setFont(fnt);
        labelDate.getUnselectedStyle().setFont(fnt);
        labelRating.getUnselectedStyle().setFont(fnt);
     

        Container HeadConainter = new Container(gridLayout);
      //  HeadConainter.add(labelCoachname);
        HeadConainter.add(labelDate);
        HeadConainter.add(labelRating);
     

//        tempForm.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
//                 e -> new HomeReview());
        tempForm.add(HeadConainter);

        ArrayList<Cellule> Reviews = ServiceCellule.getInstance().getAllCellule();
        for (Cellule r : Reviews) {
           
                Container BodyConainter = new Container(gridLayout);
               // BodyConainter.add(new Label(r.getNom_coach_review()));
                BodyConainter.add(new Label(r.getIdCellule()));
                String rate = String.valueOf(r.isDispo());
                BodyConainter.add(new Label(rate));
           
                tempForm.add(BodyConainter);
           

        }
        Button AddReview = new Button("Add Cellule");
        AddReview.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                tempForm.setTransitionOutAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, false, 250));
                Resources res = null;
                new AddTaskForm(tempForm,res).show();
                tempForm.setTransitionOutAnimator(CommonTransitions.createEmpty());
            } // wini l run mtaaha mta3 affichage?? le le ala ki t runi l projet akali win ytl3o les erreurs
        });
        Container cnAdd = new Container(gridLayout);
       
       
        cnAdd.add(AddReview);
        tempForm.add(cnAdd);
       

        tempForm.getToolbar().addSearchCommand(e -> {
            String text = (String) e.getSource();
            if (text == null || text.length() == 0) {
                // clear search
                for (Component cmp : tempForm.getContentPane()) {
                    cmp.setHidden(false);
                    cmp.setVisible(true);
                }
                tempForm.getContentPane().animateLayout(150);
            } else {
                text = text.toLowerCase();
                for (Component cmp : tempForm.getContentPane()) {
                    Container mb = (Container) cmp;
                    if(!(mb.getComponentAt(0) instanceof Button)){
                    Label label1 = (Label) mb.getComponentAt(0);
                    String line1 = label1.getText();
                    Label label2 = (Label) mb.getComponentAt(1);
                    String line2 = label2.getText();
//                    Label label3 = (Label) mb.getComponentAt(2);
                  //  String line3 = label3.getText();
             //       Label label4 = (Label) mb.getComponentAt(3);
               //     String line4 = label4.getText();
                    boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1 ||
                    line2 != null && line2.toLowerCase().indexOf(text) > -1 ;
               //     line3 != null && line3.toLowerCase().indexOf(text) > -1 ||
                //    line4 != null && line4.toLowerCase().indexOf(text) > -1;
                    mb.setHidden(!show);
                    mb.setVisible(show);
                    }
                }
                tempForm.getContentPane().animateLayout(150);
            }
        }, 4);

        tempForm.show();
//        tempForm.setTransitionOutAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, false, 250));
//        tempForm.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
//                e -> new HomeForm().showBack()); // Revenir vers l'interface précédente
       // return tempForm;

    }

    


    
}

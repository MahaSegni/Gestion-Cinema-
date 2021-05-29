/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.messaging.Message;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;

import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;

import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Film;


import com.codename1.io.Log;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Display;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.entities.CategorieF;
import com.mycompany.myapp.services.ServiceCategorieF;
import com.mycompany.myapp.services.ServiceFilm;
import ds.desktop.notify.DesktopNotify;
import java.util.ArrayList;
import javafx.print.PrintColor;




/**
 *
 * @author mahas
 */
public class EditFilm extends BaseForm{
int ida; 

    public EditFilm(Resources res, Film f) {
         super("", BoxLayout.y());
        
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
      // getTitleArea().setUIID("Container");
      setTitle("Modifier Film");
        getContentPane().setScrollVisible(false);
       super.addSideMenu(res);
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
     
         Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
//       
//        Container C2 = new Container(new BorderLayout());
      Container centerContainer = new Container( new BoxLayout(BoxLayout.Y_AXIS_BOTTOM_LAST));
      //  setTitle("Ajouter Film");
      //  setLayout(BoxLayout.y());
      
         Label nom = new Label("Nom film: ");
         Label Description1 = new Label("Description: ");
         Label Datesortie = new Label("Date sortie: ");
            Label Affiche = new Label("Affiche: ");
             Label cat = new Label("Categorie: ");
  
        TextField tfidfilm = new TextField();
        tfidfilm.setText(Integer.toString(f.getId_film()));
        TextField tfNonmfilm = new TextField();
        tfNonmfilm.setText(f.getNomfilm());
        TextField tfDescription= new TextField();
        tfDescription.setText(f.getDescriptionf());
 

        Button btnValider = new Button("Modifier Film");
Style buttonStyle = btnValider.getAllStyles();
buttonStyle.setBorder(Border.createEmpty());
buttonStyle.setFgColor(0xff);

//int c =  (int) com.getSelectedItem();
   LocalNotification n = new LocalNotification();
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNonmfilm.getText().length()==0)||(tfDescription.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    
                        Film t = new Film(Integer.parseInt(tfidfilm.getText()),tfNonmfilm.getText(), tfDescription.getText());
                        if( ServiceFilm.getInstance().EditFilm(t)){
                            Dialog.show("Success","Film updated successfully!",new Command("OK"));
                                            
            }}}}
         ); 
           centerContainer.add(nom);
           centerContainer.add( tfNonmfilm);
              centerContainer.add(Description1);
               centerContainer.add(tfDescription);
               centerContainer.add(btnValider);
            add(centerContainer);
        Button image = new Button();
       image.setUIID("Label");
      Container cnt = BorderLayout.west(image);
       cnt.setLeadComponent(image);
        image.addActionListener(e -> {
            new ListFilmBack(res).show();
        });
          }      
   
   
    


            public static void sendMessage(String [] recipients, String subject, Message msg){
        recipients=new String[] {"mahasegni00@gmail.com"};
     
        Display.getInstance().sendMessage(recipients, subject, msg);
    }
        }
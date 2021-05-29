/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

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

import com.codename1.ui.ComboBox;
import com.codename1.ui.Display;
import com.codename1.ui.RadioButton;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.entities.CategorieF;
import com.mycompany.myapp.services.ServiceCategorieF;
import com.mycompany.myapp.services.ServiceFilm;
import ds.desktop.notify.DesktopNotify;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;





/**
 *
 * @author mahas
 */
public class AddFilmForm extends BaseForm{
int ida; 

    public AddFilmForm(Resources res) {
         super("", BoxLayout.y());
        
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
      // getTitleArea().setUIID("Container");
      setTitle("Ajouter Film");
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
  
        
        TextField tfNonmfilm = new TextField("","nom film");
        TextField tfDescription= new TextField("", "Description");
        Picker date = new Picker();
        
         RadioButton multiSelect = new RadioButton();
         multiSelect.setText(".jpg");
          multiSelect.setText(".jpeg");
           multiSelect.setText(".png/plain");
          // add(multiSelect);
        TextField tfFilename= new TextField("", "Affiche");
        ComboBox combo = new ComboBox<>();
  ArrayList<CategorieF> list= ServiceCategorieF.getInstance().getAllTasks();
for(CategorieF c : list ){
    int id = c.getId();
String des= c.getDesc_c();
combo.addItem(des);
} 


//ArrayList<CategorieF> arrayList =  (ArrayList) ServiceCategorieF.getInstance().getAllTasks();
//          for (int i=0;i<arrayList.size();i++){
//               comboBoxCat.addItem(arrayList.get(i).getId());
//          }
         
////String fil = comboBoxCat.getSelectedItem().toString() ;
////        ArrayList<CategorieF> film_id = ServiceCategorieF.getInstance().getAllTasks1(fil);
//        comboBoxCat.addActionListener((evt) -> {
//
//            ida = (int) comboBoxCat.getSelectedItem();
//            
//        });
        int id= 4;
        
        Button btnValider = new Button("Ajouter Film");
Style buttonStyle = btnValider.getAllStyles();
buttonStyle.setBorder(Border.createEmpty());
buttonStyle.setFgColor(0xff);

//int c =  (int) com.getSelectedItem();
   LocalNotification n = new LocalNotification();
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNonmfilm.getText().length()==0)||(tfDescription.getText().length()==0)||(tfFilename.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    
                        Film t = new Film(tfNonmfilm.getText(), tfDescription.getText(), tfFilename.getText(),date.getDate());
                        if( ServiceFilm.getInstance().addTask(t)){
                            Dialog.show("Success","Film added successfully!",new Command("OK"));
                         
//        LocalNotification ln = new LocalNotification();
//            ln.setId("LnMessage");
//            ln.setAlertTitle("Welcome");
//            ln.setAlertBody("Thanks for arriving!");
//             
         //  Display.getInstance().scheduleLocalNotification(ln, 10, 100000);
         
          DesktopNotify.showDesktopMessage(
    "success !",
    "Film added successfully!",
    DesktopNotify.SUCCESS);
//                LocalNotification n = new LocalNotification();
//        n.setId("demo-notification");
//        n.setAlertBody("It's time to take a break and look at me");
//        n.setAlertTitle("film added !");
      //  n.setAlertSound("/notification_sound_bells.mp3"); //file name must begin with notification_sound

//
//        Display.getInstance().scheduleLocalNotification(
//                ln,
//                System.currentTimeMillis() + 10 * 1000, // fire date/time
//                LocalNotification.REPEAT_MINUTE  // Whether to repeat and what frequency
//        );
              //  new ListFilmForm(res).show();
                    
            }}}}
         ); 
        
         centerContainer.add(nom);
         
              centerContainer.add( tfNonmfilm);
              centerContainer.add(Description1);
       
        centerContainer.add(tfDescription);
         centerContainer.add(Datesortie);
       
        centerContainer.add(date);
         centerContainer.add(Affiche);
         centerContainer.add(multiSelect);
        centerContainer.add(tfFilename);
        centerContainer.add(cat);
          centerContainer.add(combo);
            centerContainer.add(btnValider);
            add(centerContainer);
        Button image = new Button();
       image.setUIID("Label");
      Container cnt = BorderLayout.west(image);
       cnt.setLeadComponent(image);
        image.addActionListener(e -> {
            new ListFilmForm(res).show();
        });
          }      
   
   
    protected String saveFileToDevice(String hi, String ext) throws URISyntaxException {
        URI uri;
  
            uri = new URI(hi);
            String path = uri.getPath();
            int index = hi.lastIndexOf("/");
            hi = hi.substring(index + 1);
            return hi;
        
       
    }


            public static void sendMessage(String [] recipients, String subject, Message msg){
        recipients=new String[] {"mahasegni00@gmail.com"};
     
        Display.getInstance().sendMessage(recipients, subject, msg);
    }
        }
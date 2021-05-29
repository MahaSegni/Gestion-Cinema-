/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Cinema;
import com.mycompany.myapp.services.ServiceCinema;

/**
 *
 * @author mahas
 */
public class addCinemaForm extends BaseForm{
     public addCinemaForm(Resources res) {
      
//         Label l = new Label("Nom Film");
//         Label Description = new Label("Description");
//         Label Datesortie = new Label("Date sortie");
//            Label Affiche = new Label("Affiche");
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
        TextField tfNonmfilm = new TextField("","nombre de place");
   
        Picker date = new Picker();
     
        Button btnValider = new Button("Ajouter salle de cinema");
//        centerContainer.add( l);
//        centerContainer.add(Description);
//        centerContainer.add(Datesortie);
//        centerContainer.add(Affiche);
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfNonmfilm.getText().length()==0)
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Cinema t = new Cinema(Integer.parseInt(tfNonmfilm.getText()));
                        if( ServiceCinema.getInstance().addTask(t))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfNonmfilm,date,btnValider);
       }
}
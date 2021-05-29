/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Event;
import com.mycompany.myapp.services.EventService;

/**
 *
 * @author mehdi
 */
public class AddEvent extends Form{
    
     public AddEvent(Form previous) {
        super("Add a new event", BoxLayout.y());
Button b= new Button ("Deconnexion");
         Resources res = null;
        b.addActionListener(e->
            new HomeForm(res).showBack()
            
        );
        add(b);//
       Button b1= new Button ("Retour");
        b1.addActionListener(e->
            new HomeFormBack(res).show()
        );add(b1);
        TextField tfNom = new TextField(null, "Event name");
        TextField tfEmplacement = new TextField(null, "Event Place");
        TextField tfCapacity = new TextField(null, "Event Capacity");
        Button btn = new Button("Add the event");

        btn.addActionListener((evt) -> {
            if ((tfNom.getText().length() == 0) || (tfEmplacement.getText().length() == 0)) {
                Dialog.show("Alert", "Please fill all the fields", "OK", null);
            } else {
                try {
                    String Nom = tfNom.getText();
                    String capacite = tfCapacity.getText();
                    String emplacement = tfEmplacement.getText();
                    Event e = new Event( Nom, Integer.parseInt(capacite), emplacement );
                    if (new EventService().addEvent(e)) {
                        Dialog.show("SUCCESS", "Event sent", "OK", null);
                    } else {
                        Dialog.show("ERROR", "Server error", "OK", null);
                    }
                } catch (NumberFormatException e) {
                    Dialog.show("ERROR", "Capacity must be a number", "OK", null);
                }

            }
        });

        this.addAll(tfNom, tfCapacity, tfEmplacement , btn);

        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previous.showBack();
        });
    }
}

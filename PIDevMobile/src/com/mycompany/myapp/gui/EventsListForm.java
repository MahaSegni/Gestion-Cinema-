/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.MultiButton;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Event;
import com.mycompany.myapp.services.EventService;
import java.util.ArrayList;

/**
 *
 * @author mehdi
 */
public class EventsListForm extends Form {
    
    public EventsListForm(Form previous) {
        super("Events list", BoxLayout.y());
        
        Container content1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        content1.setScrollableY(true);    
        ArrayList<Event> List = new EventService().getAllEvents();
        System.out.println(List.size());
    for (int i = 0; i < List.size(); i++) 
    {    
    
        System.out.println("---nom---"+List.get(i).getNom());
        final MultiButton mb = new MultiButton();
        mb.setTextLine1("🔠 Nom : "+List.get(i).getNom());
        mb.setTextLine2("⏳ Capacite : "+String.valueOf(List.get(i).getCapacite()));
        mb.setTextLine3("  Emplacement : "+List.get(i).getEmplacement());
       
       content1.addAll(mb);
    
    }
    this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            Resources res = null;
                  new Acceuil(res).TypeAboForm().show();
        });
    this.addAll(content1);
    }
    
}

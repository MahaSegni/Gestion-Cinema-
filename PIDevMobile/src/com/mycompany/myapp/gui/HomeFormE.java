/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author mehdi
 */
public class HomeFormE extends Form {
    
    public HomeFormE() {
        super("Home", BoxLayout.y());
        
        Button btnAddTask = new Button("Add Event");
        Button btnTasksList = new Button("Events List");
        
        btnAddTask.addActionListener((evt) -> {
            new AddEvent(this).show();
        });
        btnTasksList.addActionListener((evt) -> {
            new EventsListForm(this).show();
        });
        
        this.addAll(new Label("Choose an option :"), btnAddTask, btnTasksList);
    }
    
}

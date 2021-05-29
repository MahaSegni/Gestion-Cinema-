/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Cellule;
import com.mycompany.myapp.entities.Task;
import com.mycompany.myapp.services.ServiceCellule;
import com.mycompany.myapp.services.ServiceTask;

/**
 *
 * @author bhk
 */
public class AddTaskForm extends BaseForm{

    public AddTaskForm(Form previous,Resources res) {
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddTask, on peut y revenir
        en utilisant le bouton back
        */
        setTitle("Add a new cellule");
        setLayout(BoxLayout.y());
        Button b= new Button ("Deconnexion");
       
        b.addActionListener(e->
            new HomeForm(res).showBack()
            
        );
        add(b);//
        Button b1= new Button ("Retour");
        b1.addActionListener(e->
            new HomeFormBack(res).show()
        );add(b1);
        TextField tfName = new TextField("","CelluleName");
        CheckBox tfStatus1= new CheckBox("disponible");
        CheckBox tfStatus2= new CheckBox("non disponible");
        Button btnValider = new Button("Add cellule");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        
                        Cellule t = new Cellule( tfName.getText(), true);
                        if( ServiceCellule.getInstance().addCellule(t))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Name must be a string", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfName,tfStatus1,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
                
    }
    
    
}

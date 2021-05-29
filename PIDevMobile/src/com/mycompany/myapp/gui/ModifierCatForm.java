/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Cat;
import com.mycompany.myapp.services.ServiceCat;


/**
 *
 * @author ASUS
 */
public class ModifierCatForm extends BaseFormP{
    Form current;
    public ModifierCatForm(Resources res , Cat r){
        super("Newsfeed",BoxLayout.y()); //heritage de Newsfeed & le formulaire est verctical
        
        Toolbar tb= new Toolbar(true);
        current=this;
        setToolbar(tb);
        getTitleArea().setUIID("container");
        setTitle("Ajouter Catégorie");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
        TextField nomc = new TextField(r.getNomc(),"Nomc",20,TextField.ANY);
        
        nomc.setUIID("NewstopLine");
        nomc.setSingleLineTextArea(true);
        
        Button btnModifier = new Button("Modifier");
        btnModifier.setUIID("Button");
        
        //event on click btnModifier
        btnModifier.addPointerPressedListener(l -> {
            r.setNomc(nomc.getText());
            
        });
        
        //appel fonction modifier cat men service
        if(ServiceCat.getInstance().modifierCat(r)){  //if true
            new ListCatForm(res).show();  
        }
        Button btnAnnuler = new Button("Annuler");
        btnAnnuler.addActionListener(e->{
            new ListCatForm(res).show();
        });
        
        
        
        
        
    }
    
}

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
import com.codename1.ui.util.Resources;

/**
 *
 * @author bhk
 */
public class HomeFormP extends Form {

    Form current;
    /*Garder traçe de la Form en cours pour la passer en paramètres 
    aux interfaces suivantes pour pouvoir y revenir plus tard en utilisant
    la méthode showBack*/
    Resources res = null;
    public HomeFormP() {
        current = this; //Récupération de l'interface(Form) en cours
        setTitle("Home");
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
        add(new Label("Choose an option"));
       
Button btnAddfilm = new Button("Ajouter Cat");
        Button btnListFilm = new Button("List des Cat");
         Button btnAddCinema = new Button("Ajouter Plat");
        Button btnListCinema = new Button("List des salles de Plat");
        btnAddfilm.addActionListener(e -> new AjouterCatForm(res).show());
        btnListFilm.addActionListener(e -> new ListCatForm(res).show());
        
       
         btnAddCinema.addActionListener(e -> new AjouterPlatForm(res).show());
        btnListCinema.addActionListener(e -> new ListPlatForm(res).show());
        addAll(btnAddfilm,btnListFilm,btnAddCinema,btnListCinema);

    }

}

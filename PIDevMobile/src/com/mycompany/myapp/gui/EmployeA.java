/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
//import com.mycompany.myapp.entities.Task;
//import com.mycompany.myapp.services.ServiceTask;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Employe;
import com.mycompany.myapp.services.ServiceEmploye;
//import com.mycompany.myapp.services.ServiceFilm;

/**
 *
 * @author bhk
 */
public class EmployeA extends Form {

    public EmployeA() {

        setTitle("Ajouter Employe");
        setLayout(BoxLayout.y());
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
        ServiceEmploye se = new ServiceEmploye();
        TextField tfNomemp = new TextField("", "nomemp");
        TextField tfPrenomemp = new TextField("", "prenomemp");
        TextField tfNumtelemp = new TextField("", "numtelemp");

        TextField tfAdresseemp = new TextField("", "adresseemp");

        Button btnValider = new Button("Ajouter Employe");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNomemp.getText().length() == 0) || (tfPrenomemp.getText().length() == 0) || (tfNumtelemp.getText().length() == 0) || (tfAdresseemp.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        Employe t = new Employe(tfNomemp.getText(), tfPrenomemp.getText(), Integer.parseInt(tfNumtelemp.getText()), tfAdresseemp.getText());
                        se.ajouter(t);
                        Dialog.show("Success", "Connection accepted", new Command("OK"));

                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }

                }

            }
        });

        addAll(tfNomemp, tfPrenomemp, tfNumtelemp, tfAdresseemp, btnValider);
           }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Calendar;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.ScrollListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
//import com.mycompany.myapp.entities.Task;
//import com.mycompany.myapp.services.ServiceTask;//Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.ScrollListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
//import com.mycompany.myapp.entities.Task;
//import com.mycompany.myapp.services.ServiceTask; 
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Conge;
import com.mycompany.myapp.entities.Employe;
import com.mycompany.myapp.services.ServiceConge;
import com.mycompany.myapp.services.ServiceEmploye;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.control.DatePicker;
//import com.mycompany.myapp.services.ServiceEmploye;

/**
 *
 * @author bhk
 */
public class CongeA extends Form {

    public CongeA() {

        setTitle("Ajouter Conge");
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
        ServiceConge se = new ServiceConge();

        TextField tfMotifconge = new TextField("", "motifconge");
        TextField tfNbjourconge = new TextField("", "nbjourconge");

        Picker date = new Picker();

        ComboBox com = new ComboBox<>();
        Employe e = new Employe();
        ServiceEmploye employeS = new ServiceEmploye();

        ArrayList<Employe> employes = employeS.getEmployes();

       for (Employe c : employes) {

            int i = c.getIdemp();
            com.addItem(i);
        }

        Button btnValider = new Button("Ajouter Conge");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfMotifconge.getText().length() == 0) || (tfNbjourconge.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        Conge t = new Conge(tfMotifconge.getText(), Integer.parseInt(tfNbjourconge.getText()), (int) com.getSelectedItem());//, (int) com.getSelectedItem()
                        if (ServiceConge.getInstance().ajouter(t)) {
                            Dialog.show("Success", "Connection accepted", new Command("OK"));
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }

                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }

                }

            }
        });

        addAll(date, tfMotifconge, tfNbjourconge, com, btnValider);
     }

}

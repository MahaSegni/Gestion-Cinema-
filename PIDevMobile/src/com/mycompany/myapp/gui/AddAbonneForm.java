/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Abonne;
import com.mycompany.myapp.services.ServiceAbonne;

/**
 *
 * @author bhk
 */


public class AddAbonneForm extends Form{

    public AddAbonneForm(Resources res) {
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddTask, on peut y revenir
        en utilisant le bouton back
        */
         setLayout(BoxLayout.y());

        setTitle("Créer un compte");
        
        TextField tfNom = new TextField("","Nom");
        
        TextField tfPrenom= new TextField("", "Prenom");
        TextField tfMail= new TextField("", "Mail");
        Picker picker_Datenaiss= new Picker();
        TextField tfTelephone= new TextField("", "Telephone");
        TextField tfPassword= new TextField("", "Password",1,TextField.PASSWORD);


        Button btnValider = new Button("Créer un compte");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length()==0)||(tfPrenom.getText().length()==0)||(tfMail.getText().length()==0)||(picker_Datenaiss.getText().length()==0)||(tfTelephone.getText().length()==0)||(tfPassword.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Abonne a = new Abonne(tfNom.getText(),tfPrenom.getText(),tfMail.getText(),picker_Datenaiss.getDate(),Integer.parseInt(tfTelephone.getText()),tfPassword.getText());
                        if( ServiceAbonne.getInstance().addAbonne(a)){
                            Dialog.show("Success","Vous êtes inscrit",new Command("OK"));
                                           new HomeForm(res).show();}

                        else
                            Dialog.show("ERROR", "Données", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
  
            }
            

        });
        
        addAll(tfNom,tfPrenom,tfMail,picker_Datenaiss,tfTelephone,tfPassword,btnValider);
        

     //  getToolbar().addCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK); 
       
               // e-> previous.showBack()); // Revenir vers l'interface précédente
                
    }
    
    
}

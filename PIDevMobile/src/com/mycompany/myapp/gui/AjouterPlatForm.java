/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;


import com.mycompany.myapp.entities.Plat;
import com.mycompany.myapp.services.ServicePlat;
import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

import com.mycompany.myapp.entities.Cat;
import com.mycompany.myapp.services.ServiceCat;

/**
 *
 * @author ASUS
 */
public class AjouterPlatForm extends BaseFormP{

    Form current;

    public AjouterPlatForm(Resources res) {
        super("Ajouter Plat", BoxLayout.y()); //heritage de Newsfeed & le formulaire est verctical

        Toolbar tb = new Toolbar(true);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("container");
       // setTitle("Ajouter Plat");
        getContentPane().setScrollVisible(false);

         tb.addCommandToOverflowMenu("Deconnexion", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new HomeForm(res).showBack();
            }
        });
         tb.addCommandToOverflowMenu("Retour", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               new HomeFormP().show();
         
            }
        });
        /*
        tb.addSearchCommand(e->{
            
        });
        Tabs swipe = new Tabs();
        Label s1=new Label();
        Label s2=new Label();
        addTab(swipe,res.getImage(id));
         */
        
        //textField Nomp
        TextField nomp = new TextField("", "Entrer nom Plat!");
        nomp.setUIID("TextFieldBlack");
        addStringValue("Nomc", nomp);
        //textField Prixp
        TextField prixp = new TextField("", "Entrer prix Plat!");
        prixp.setUIID("TextFieldBlack");
        addStringValue("Prixp", prixp);
        //textField Prixp
        TextField categorie_id = new TextField("", "Entrer categorie Plat!");
        categorie_id.setUIID("TextFieldBlack");
        addStringValue("Categorie_id", categorie_id);
        //textField Prixp
        TextField imagep = new TextField("", "Entrer image Plat!");
        imagep.setUIID("TextFieldBlack");
        addStringValue("Imagep", imagep);
        //textField Prixp
        TextField description = new TextField("", "Entrer description Plat!");
        description.setUIID("TextFieldBlack");
        addStringValue("Description", description);
        
        

        //Button Ajouter
        Button btnAjouter = new Button("Ajouter");
        addStringValue("", btnAjouter);

        //onclick button event
        btnAjouter.addActionListener((e) -> {

            try {
                if (nomp.getText().equals("") || prixp.getText().equals("")  || imagep.getText().equals("") || description.getText().equals("") )
                {
                    Dialog.show("Veuillez vérifier les donnes", "", "Annuler", "OK");
                } else {
                    InfiniteProgress ip = new InfiniteProgress();;//loading after insert data

                    final Dialog iDialog = ip.showInfiniteBlocking();
                    
                    
                    Plat r =new Plat(
                            /////////////////////////////////////////////////
                                    Integer.valueOf(prixp.getText()),
                                    String.valueOf(nomp.getText()).toString(),
                                    String.valueOf(categorie_id.getText()).toString(),
                                    String.valueOf(imagep.getText()).toString(),
                                    String.valueOf(description.getText()).toString()
                                    );
                    


                    System.out.println("data plat == " + r);

                    //appel methode ajouterCat mte3 service cat pour ajouter les données fel base
                    ServicePlat.getInstance().ajouterPlat(r);

                    iDialog.dispose();//remove loading after ajout

                    //bech yafichi l listePlat
                    //new ListPlatForm(res).show();

                    refreshTheme();//actualisation    
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });

    }

   

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PddedLabel"))
                .add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}

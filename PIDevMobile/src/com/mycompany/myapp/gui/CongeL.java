/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.WebBrowser;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Conge;
import com.mycompany.myapp.entities.Employe;

//import com.mycompany.myapp.gui.Home;
import com.mycompany.myapp.services.ServiceEmploye;
import com.mycompany.myapp.services.ServiceConge;

import com.mycompany.myapp.utils.Paginator;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author user
 */
public class CongeL extends Form {

    private TextField recherche;

    private CongeL current;

    private ServiceConge congeS;

    private ArrayList<Conge> conges;
    private Map<Integer, ArrayList<Conge>> produits;
    private Paginator paginator;
Resources res=null;
    public CongeL() {
        current = this;
        HomeFormH.addMenu(this);
        setTitle("Liste des Conges");
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
        addShowListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                init();
                current.removeShowListener(this);

            }
        });
        paginator = new Paginator(this);

    }

    private void init() {
        produits = new HashMap<>();
        congeS = new ServiceConge();

        conges = congeS.getConges();
        if (conges.size() == 0) {
            setLayout(BoxLayout.xCenter());
            add(new Label("Vous n'avez aucun conge"));
            refreshTheme();
        } else {

//recherche
            recherche = new TextField("", "Recherche");
            recherche.addActionListener(a -> trier());
            add(recherche);
//afficher
            add(paginator.getAllInOneContainer());
            for (Conge c : conges) {

                produits.put(c.getIdconge(), congeS.getConges());
                addConge(c);
            }

            // pdf 
        }

    }

    private void addConge(Conge c) {

        Container commandeContainer = new Container(BoxLayout.x());
        Container infosContainer = new Container(BoxLayout.y());
        Label dateLabel = new Label("Date: " + c.getDateconge());
        infosContainer.addAll(dateLabel, new Label("Motif: " + c.getMotifconge()), new Label("Nombre de jours du conge: " + c.getNbjourconge()), new Label("reference employe: " + c.getEmploye_id()));

        Container cn1 = new Container();
        Container cn = new Container(BoxLayout.y());
        Button listeButton = new Button("pdf");
        listeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent l) {
                Display.getInstance().execute("http://127.0.0.1:8000/pdfActionc/");
            }
        });

        commandeContainer.add(infosContainer);
        cn1.add(commandeContainer);
        cn.addAll(listeButton, cn1);
 Button deletebtn = new Button("Delete");
 deletebtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                       
                        if (ServiceConge.getInstance().supprimerConge(c)) {
                           Dialog.show("Success", "film supprimé avec succès ! ", "Ok", null);
                            new HomeFormH(res).show();
                        } else {
                            Dialog.show("ERROR", "Error , film n'est pas supprimé ", "Ok", null);
                        }
                    }
                });
 cn.add(deletebtn);
 
        paginator.add(cn);

    }

    private void trier() {
        paginator.clear();
        String rechercheTexte = recherche.getText().toLowerCase().trim();
        int catId = -1;

        for (Conge p : conges) {
            if (p.getMotifconge().toLowerCase().trim().contains(rechercheTexte)) {
                addConge(p);
            }
        }
    }
}

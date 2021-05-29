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

import com.mycompany.myapp.entities.Employe;

//import com.mycompany.myapp.gui.Home;
import com.mycompany.myapp.services.ServiceEmploye;

import com.mycompany.myapp.utils.Paginator;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author user
 */
public class EmployeL extends Form {

//    private EmployeL current;
//    private ArrayList<Employe> employes;
//    
//    private ComboBox listeCat;
//    private EncodedImage enc;
//    private TextField recherche;
//    private Paginator paginator;
//    
//    //private ProduitCommandeService pcs;
//    
//    public EmployeL() {
//        current=this;
//       // Home.addMenu(this);
//        setTitle("Employe");
//        setLayout(BoxLayout.y());
//        paginator=new  Paginator(this);
//       // pcs=new ProduitCommandeService();
//        ServiceEmploye sp=new ServiceEmploye();
//        employes=sp.getEmployes();
//       // ServiceCategorieProduit scp=new ServiceCategorieProduit();
//        //categories=scp.getCategories();
////        try {
////            enc = EncodedImage.create("/loading.png");
////        } catch (IOException ex) {
////            System.out.println(ex);
////        }
//        
//        recherche=new TextField("", "Recherche");
//        recherche.addActionListener(a->trier());
//        add(recherche);
//        
//        listeCat=new ComboBox();
//        listeCat.addItem("Tout");
//        //for (CategorieProduit cp : categories)
////            listeCat.addItem(cp);
////        listeCat.addActionListener(l->trier());
////        add(listeCat);
//        
//        for (Employe p : employes)
//            addEmploye(p);
//        add(paginator.getAllInOneContainer());
//    }
//    
//    private void addEmploye(Employe p) {
//        Container employeC=new Container(BoxLayout.x());
////        URLImage imageURL=URLImage.createToStorage(enc,Statics.IMAGE_URL+p.getImage(),Statics.IMAGE_URL+p.getImage(), URLImage.RESIZE_SCALE);
////        ImageViewer imageProduit=new ImageViewer(imageURL);
////        produitC.add(imageProduit);
//        Container infos=new Container(BoxLayout.y());
//  
//        Button nomButton=new Button(p.getNomemp());
//        Button prenomButton=new Button(p.getPrenomemp());
////        nomButton.addActionListener(new ActionListener() {
////            @Override
////            public void actionPerformed(ActionEvent l) {
////                CategorieProduit cp=null;
////                for (CategorieProduit catP : categories) {
////                    if (catP.getId()==p.getCategorie()) {
////                        cp=catP;
////                        break;
////                    }
////                }
//                //new DetailsEmploye(current, p, cp).show();
////            }
////        });
//        Button ajouterEmploye=new Button("Ajouter un Employe");
//        ajouterEmploye.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent l) {
//                Dialog.show("Employe", "Employe ajouté ", new Command("Ok"));
//                //pcs.ajouter(p.getId(), CurrentUser.CurrentUser().id);
//            }
//        });
//        infos.addAll(nomButton,prenomButton, new Label(Double.toString(p.getNumtelemp())), ajouterEmploye);
//        employeC.add(infos);
//        employeC.setLeadComponent(nomButton);
//        
//        ajouterEmploye.setBlockLead(true);
//        paginator.add(employeC);
//    }
    private TextField recherche;

    private EmployeL current;

    private ServiceEmploye employeS;
    // private ProduitCommandeService produitCommandeS;
    private ArrayList<Employe> employes;
    private Map<Integer, ArrayList<Employe>> produits;//LISTcat
    private Paginator paginator;

    public EmployeL() {
        current = this;
        HomeFormH.addMenu(this);
        setTitle("Liste des Employes");
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
        employeS = new ServiceEmploye();

        employes = employeS.getEmployes();
        if (employes.size() == 0) {
            setLayout(BoxLayout.xCenter());
            add(new Label("Vous n'avez aucun employe"));
            refreshTheme();
        } else {

//recherche
            recherche = new TextField("", "Recherche");
            recherche.addActionListener(a -> trier());
            add(recherche);
//afficher
            add(paginator.getAllInOneContainer());
            for (Employe c : employes) {

                produits.put(c.getIdemp(), employeS.getEmployes());
                addEmploye(c);
            }

            // pdf 
        }

    }

    private void addEmploye(Employe c) {

        Container commandeContainer = new Container(BoxLayout.x());
        Container infosContainer = new Container(BoxLayout.y());
        Label nomLabel = new Label("Nom: " + c.getNomemp());
        infosContainer.addAll(nomLabel, new Label("Prenom: " + c.getPrenomemp()), new Label("Numero Telephone: " + c.getNumtelemp()), new Label("Email: " + c.getAdresseemp()));

        Container cn1 = new Container();
        Container cn = new Container(BoxLayout.y());
        Button listeButton = new Button("pdf");
        listeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent l) {
                Display.getInstance().execute("http://127.0.0.1:8000/pdfAction/");
            }
        });

        commandeContainer.add(infosContainer);
        cn1.add(commandeContainer);
        cn.addAll(listeButton, cn1);

        paginator.add(cn);

    }

    private void trier() {
        paginator.clear();
        String rechercheTexte = recherche.getText().toLowerCase().trim();
        int catId = -1;

        for (Employe p : employes) {
            if (p.getNomemp().toLowerCase().trim().contains(rechercheTexte)) {
                addEmploye(p);
            }
        }
    }
}

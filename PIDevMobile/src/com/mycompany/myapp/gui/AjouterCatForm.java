package com.mycompany.myapp.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Cat;
import com.mycompany.myapp.services.ServiceCat;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASUS
 */

public class AjouterCatForm extends BaseFormP{
   
    Form current;
    public AjouterCatForm(Resources res){
        super("Ajouter Catégorie",BoxLayout.y()); //heritage de Newsfeed & le formulaire est verctical
        
        Toolbar tb= new Toolbar(true);
        current=this;
        setToolbar(tb);
        getTitleArea().setUIID("container");
       
        //setTitle("Ajouter Catégorie");
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
                
             
        //textField Nomc
        TextField nomc= new TextField("","Entrer nom catégorie!");
        nomc.setUIID("TextFieldBlack");
        addStringValue("Nomc",nomc);
        
        //Button Ajouter
        Button btnAjouter=new Button("Ajouter");
        addStringValue("", btnAjouter);
        
        //onclick button event
        btnAjouter.addActionListener((e)->{
            
            
            
            try{
                if(nomc.getText().equals(""))//||description.getText()=="") vid2 26:02
                {
                    Dialog.show("Veuillez vérifier les donnes","","Annuler","OK");
                }
                else{
                    InfiniteProgress ip = new InfiniteProgress();;//loading after insert data
                    
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    
                    Cat r =new Cat(String.valueOf(nomc.getText()).toString());
                    
                    System.out.println("data categorie == "+r);  
                    
                    //appel methode ajouterCat mte3 service cat pour ajouter les données fel base
                    ServiceCat.getInstance().ajouterCat(r);
                    
                    iDialog.dispose();//remove loading after ajout
                    

                    
                    //bech yafichi l listeCat
                    new ListCatForm(res).show();
                    
                    
                ServiceCat.getInstance().SendSms("52551439","Rawend") ;
                   
                    ServiceCat.getInstance().notification();
                    
                    refreshTheme();//actualisation    
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
            

            
            
        });
        
        
    }


    private void addStringValue(String s, Component v) {
       add(BorderLayout.west(new Label(s,"PddedLabel"))
       .add(BorderLayout.CENTER,v));
       add(createLineSeparator(0xeeeeee));
    }
    
}

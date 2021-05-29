/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Abonne;

/**
 *
 * @author mahas
 */
public class HomeFront extends MenuPricipal{
     private Form Home = new Form();
     private  Toolbar tb =new Toolbar();
    public HomeFront (Resources res){
      setLayout(BoxLayout.y());

        setTitle("Menu");
           getContentPane().setScrollVisible(false);
       super.addSideMenu1(res);
       Button b= new Button ("Deconnexion");
        b.addActionListener(e->
            new HomeForm(res).showBack()
            
        );
        add(b);
        
}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

/**
 *
 * @author mahas
 */
public class HomeFormBack extends HomeBack{
     private Form Home = new Form();
     private  Toolbar tb =new Toolbar();
    public HomeFormBack (Resources res){
      setLayout(BoxLayout.y());

        setTitle("Menu");
           getContentPane().setScrollVisible(false);
       super.addSideMenu1(res);
       Button b= new Button ("Deconnexion");
        b.addActionListener(e->
            new HomeForm(res).showBack()
            
        );
        add(b);
        Label star = new Label(" ");
      
      Label desc = new Label(" ");
     
//     
       add(star);
       add(desc);
        
}
}

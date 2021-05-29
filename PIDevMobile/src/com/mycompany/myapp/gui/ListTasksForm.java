/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Cellule;
import com.mycompany.myapp.services.ServiceCellule;
import com.mycompany.myapp.services.ServiceTask;
import java.util.ArrayList;
import static java.util.Collections.list;

/**
 *
 * @author bhk
 */
public class ListTasksForm extends Form{

    public ListTasksForm(Form previous) {
        
        ArrayList<Cellule> cel = ServiceCellule.getInstance().getAllCellule();
        for(Cellule f : cel ){
            String s = String.valueOf(f.getDispo());
 addButton(f.getIdCellule(),s);
}

        
        setTitle("List tasks");
        
       // SpanLabel sp = new SpanLabel();
      //  sp.setText(ServiceCellule.getInstance().getAllCellule().toString());
       // add(sp);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
    



 private void addButton(String fil,String fil1) {
    // int height = Display.getInstance().convertToPixels(11.5f);
      // int width = Display.getInstance().convertToPixels(14f);
       Button image = new Button();
       image.setUIID("Label");
      Container cnt = BorderLayout.west(image);
       cnt.setLeadComponent(image);
       TextArea ta = new TextArea();
       Label l= new Label(fil);
       Label l2= new Label(fil1);//Label l3= new Label(fil2);
       ta.setUIID("NewsTopLine");
       ta.setEditable(false);
     //  add(l);
     //  add(l2);
     cnt.add(BorderLayout.CENTER,
               BoxLayout.encloseY(
                      l,l2
                       
               ));
       
       add(cnt);
}
}
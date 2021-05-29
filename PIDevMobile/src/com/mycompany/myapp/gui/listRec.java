/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;
import com.codename1.components.SpanLabel;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.services.ServiceReclamation;
/**
 *
 * @author dell
 */
public class listRec  extends Form{


  public listRec(Form previous) {
        
        setTitle("List Reclamation");
Label l = new Label("  ");add(l);
         Label Description = new Label("   ");add(Description);
         Label Desc = new Label("    ");add(Desc);
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
        SpanLabel sp = new SpanLabel();
        sp.setText(ServiceReclamation.getInstance().getAllTasks().toString());
        add(sp);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }    


}

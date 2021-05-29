/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Slider;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author mahas
 */
public class AlertAvis extends BaseForm {

    public AlertAvis(Resources res) {
        Dialog id = new Dialog("Votre avis nous interesse "  );
        int height = 25;
        Form f = Display.getInstance().getCurrent();
        id.setLayout(new BorderLayout());
        Slider rate ;//= createStarRankSlider();
        Button ok = new Button("OK");
        Button no = new Button("No Thanks");
       id.add(BorderLayout.CENTER, FlowLayout.encloseCenterMiddle()).
                add(BorderLayout.SOUTH, GridLayout.encloseIn(2, no, ok));
       add(id);
        no.addActionListener(e -> new ListFilmForm(res).show());
        ok.addActionListener(e -> new RatingWidget(res).show());
         id.show();
    }
    
}

package com.mycompany.myapp.gui;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Abonne;
import java.io.IOException;

public class BaseForm2 extends Form {

    Form current;

    public BaseForm2() {
    }

    public BaseForm2(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }

    public BaseForm2(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    public Component createLineSeparator(int color) {
        Label separator = new Label("", "WhiteSeparator");
        separator.getUnselectedStyle().setBgColor(color);
        separator.getUnselectedStyle().setBgTransparency(255);
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    protected void addSideMenu(Resources res) throws IOException {
        Toolbar tb = getToolbar();
//        Image img = res.getImage("Acceuil.jpeg");
//        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
//            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
//        }
        // ScaleImageLabel sl = new ScaleImageLabel(img);
        // sl.setUIID("BottomPad");
        // sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
//        Label user=new Label(res.getImage("profile-pic.png"), "PictureWhiteBackgrond");
        // user.addPointerPressedListener(new ActionListener() {
        //   @Override
        //   public void actionPerformed(ActionEvent evt) {
        //   CompteForm cf=new CompteForm(res);
        //   cf.show();
        //}
        // });
        

        tb.addComponentToSideMenu(LayeredLayout.encloseIn());
        
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.myapp.charts;

import com.codename1.io.Log;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Form;
import com.codename1.ui.List;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.gui.AfficheReclamation;
import com.mycompany.myapp.gui.BaseFormR;
import com.mycompany.myapp.gui.HomeForm;





/**
 *
 * @author shannah
 */
public class ChartDemosFormR extends BaseFormR {
    
    
    boolean drawOnMutableImages;
    List formMenu;
    
    class ListOption {
        Class<AbstractDemoChartR> chartClass;
        String name;
        
        ListOption(Class cls, String name){
            this.chartClass = cls;
            this.name = name;
        }
        
        public String toString(){
            return this.name;
        }
    }
    
    ListOption[] options = new ListOption[]{
      //  new ListOption(BudgetPieChart.class, "Nombre de réservation "),

        new ListOption(TemperatureChartR.class, "Rate par mois"),
    };
    
    public ChartDemosFormR(Resources res, Form f){
        super("Statistique", BoxLayout.yCenter());
         Toolbar tb = new Toolbar(true);
        setToolbar(tb);
      //  setTitle("Most Popular");
       getTitleArea().setUIID("Container");
       
      // getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res,f);
        formMenu = new List();
        for ( int i=0; i<options.length; i++){
            formMenu.addItem(options[i]);
        }
        
        formMenu.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                int sel = formMenu.getCurrentSelected();
                ListOption opt = options[sel];
                Class cls = opt.chartClass;
                if ( ChartsInBoxLayoutR.class.equals(cls) ){
                    Form f = new ChartsInBoxLayoutR().getForm();
                    Command cmd = new Command("Menu"){

                        @Override
                        public void actionPerformed(ActionEvent evt) {
                      
                           new  AfficheReclamation(res,f);
                        }
                        // trah jarb wla fma mochkla okhra !!stanaa
                    };
                    f.setBackCommand(cmd);
                    
                    f.getStyle().setBgColor(0x0);
                    f.getStyle().setBgTransparency(0xff);
                    int numComponents = f.getComponentCount();
                    for (int i=0; i<numComponents; i++) {
                        f.getComponentAt(i).getStyle().setBgColor(0x0);
                        f.getComponentAt(i).getStyle().setBgTransparency(0xff);
                    }
                    
                    f.show();
                    return;
                }
                try {
                    AbstractDemoChartR demo = (AbstractDemoChartR)cls.newInstance();
                    demo.setDrawOnMutableImage(drawOnMutableImages);
                    Form intent = demo.execute();
                    if ( "".equals(intent.getTitle())){
                        intent.setTitle(demo.getName());
                    }
                    Command cmd = new Command("Menu"){

                        @Override
                        public void actionPerformed(ActionEvent evt) {
                           
                           new  AfficheReclamation(res,f);
                        }
                        
                    };
                    intent.setBackCommand(cmd);
                    intent.getStyle().setBgColor(0x0);
                    intent.getStyle().setBgTransparency(0xff);
                    int numComponents = intent.getComponentCount();
                    for (int i=0; i<numComponents; i++) {
                        intent.getComponentAt(i).getStyle().setBgColor(0x0);
                        intent.getComponentAt(i).getStyle().setBgTransparency(0xff);
                    }
                    intent.show();
                } catch (InstantiationException ex) {
                    Log.e(ex);
                } catch (IllegalAccessException ex) {
                    Log.e(ex);
                }
            }
            
        });
        
        setLayout(new BorderLayout());
//        
//        final CheckBox mutableImageCb = new CheckBox("Render on Mutable Image");
//        mutableImageCb.addActionListener(new ActionListener() {
//
//            public void actionPerformed(ActionEvent evt) {
//                drawOnMutableImages =  mutableImageCb.isSelected();
//            }
//            
//        });
//        addComponent(BorderLayout.NORTH, mutableImageCb);
        addComponent(BorderLayout.CENTER, formMenu);
        
    }
    
    
    
}

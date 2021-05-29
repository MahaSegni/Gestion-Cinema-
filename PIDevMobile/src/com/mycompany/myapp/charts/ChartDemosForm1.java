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
import com.mycompany.myapp.gui.BaseForm;
import com.mycompany.myapp.gui.ListFilmBack;
import com.mycompany.myapp.gui.ListFilmForm;




/**
 *
 * @author shannah
 */
public class ChartDemosForm1 extends BaseForm {
    
    
    boolean drawOnMutableImages;
    List formMenu;
    
    class ListOption {
        Class<AbstractDemoChart1> chartClass;
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
//        new ListOption(AverageCubicTemperatureChart.class, "Avg. Cubic Temperature"),
//new ListOption(AverageTemperatureChart.class, "Avg. Temperature"),
//        new ListOption(BudgetDoughnutChart.class, "Budget Doughnut"),
        new ListOption(BudgetPieChart.class, "Nombre de réservation "),
 //       new ListOption(CombinedTemperatureChart.class, "Combined Temperature"),
 //               new ListOption(MultipleTemperatureChart.class, "Multiple Temperature"),
//        new ListOption(ProjectStatusBubbleChart.class, "Project Status Bubble Chart"),
//        new ListOption(SalesBarChart.class, "Sales Bar Chart"),
//        new ListOption(SalesComparisonChart.class, "Sales Comparison Chart"),
//        new ListOption(SalesGrowthChart.class, "Sales Growth Chart"),
//        new ListOption(SalesStackedBarChart.class, "Sales Stacked Bar Chart"),
//        new ListOption(ScatterChart.class, "Scatter Chart"),
//        new ListOption(SensorValuesChart.class, "Sensor Values Chart"),
 //       new ListOption(TemperatureChart.class, "Temperature Chart"),
//        new ListOption(TrigonometricFunctionsChart.class, "Trigonometric Functions Chart"),
//        new ListOption(WeightDialChart.class, "Weight Dial Chart"),
//        new ListOption(ChartsInBoxLayout.class, "Vertical Box Layout"),
//        new ListOption(MetricsStackedBarChart.class, "Metrics Stacked Bar Chart")
    };
    
    public ChartDemosForm1(Resources res){
        super("Statistique", BoxLayout.yCenter());
         Toolbar tb = new Toolbar(true);
        setToolbar(tb);
      //  setTitle("Most Popular");
       getTitleArea().setUIID("Container");
       
      // getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        formMenu = new List();
        for ( int i=0; i<options.length; i++){
            formMenu.addItem(options[i]);
        }
        
        formMenu.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                int sel = formMenu.getCurrentSelected();
                ListOption opt = options[sel];
                Class cls = opt.chartClass;
                if ( ChartsInBoxLayout1.class.equals(cls) ){
                    Form f = new ChartsInBoxLayout1().getForm();
                    Command cmd = new Command("Menu"){

                        @Override
                        public void actionPerformed(ActionEvent evt) {
                          new  ListFilmBack(res).show();
                        }
                        
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
                    AbstractDemoChart1 demo = (AbstractDemoChart1)cls.newInstance();
                    demo.setDrawOnMutableImage(drawOnMutableImages);
                    Form intent = demo.execute();
                    if ( "".equals(intent.getTitle())){
                        intent.setTitle(demo.getName());
                    }
                    Command cmd = new Command("Menu"){

                        @Override
                        public void actionPerformed(ActionEvent evt) {
                           new  ListFilmBack(res).show();
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

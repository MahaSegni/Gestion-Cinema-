/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Film;
import com.mycompany.myapp.services.ServiceFilm;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author mahas
 */
public class statistique {
     Form Stat;
    private Resources theme;
 
    private DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(15);
        renderer.setLabelsColor(0x000000);
        renderer.setLegendTextSize(15);
        renderer.setMargins(new int[]{20, 30, 15, 0});
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        renderer.setZoomButtonsVisible(true);
        renderer.setZoomEnabled(true);
        renderer.setChartTitleTextSize(20);
        renderer.setDisplayValues(true);
        renderer.setShowLabels(true);
        return renderer;
    }

    protected CategorySeries buildCategoryDataset(String title, double[] values) {
      CategorySeries series = new CategorySeries(title);
      try{  
       ServiceFilm eventService=new ServiceFilm();       
        ArrayList<Film> listJournal = eventService.getAllTasks();
      //  ArrayList<Animal> listAnimal = eventService.getAllTasksAnimal();
Iterator<Film> it = listJournal.iterator();
while (it.hasNext()) {
   Film j=it.next();
 long nb =0;
  String nba =null;
    System.out.println(j.getId_film()+" id");
        for( Film ee : listJournal)
        {        
        String n = ee.getNomfilm();
        if(n.equals(j.getNomfilm()))
                nb++;
        }
           series.add(nba,nb);  
}
      }
      catch(Exception i)
      {
          i.printStackTrace();
      }
        return series;
    }

    public Form createPieChartForm() {
        // Generate the values
        double[] values = new double[]{12, 14, 11};

        // Set up the renderer
        int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.YELLOW, ColorUtil.CYAN, ColorUtil.MAGENTA};
        DefaultRenderer renderer = buildCategoryRenderer(colors);
        SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
        r.setGradientEnabled(true);
        r.setGradientStart(0, ColorUtil.BLUE);
        r.setGradientStop(0, ColorUtil.GREEN);
        r.setGradientStop(0, ColorUtil.YELLOW);
        r.setGradientStop(0, ColorUtil.CYAN);
        r.setGradientStop(0, ColorUtil.MAGENTA);
        // Create the chart ... pass the values and renderer to the chart object.
        PieChart chart = new PieChart(buildCategoryDataset("", values), renderer);
        // Wrap the chart in a Component so we can add it to a form
        ChartComponent c = new ChartComponent(chart);
        // Create a form and show it.
        Stat = new Form("Statistique", new BorderLayout());
        Stat.getToolbar().addCommandToLeftBar("Back", null, (u) -> {
            HomeFormBack h;
                h = new HomeFormBack(theme);
           // h.getF().show();
        });
        // Stat.getStyle().setBgColor(0x50d3ed);

        Stat.add(BorderLayout.CENTER, c);


        return Stat;

    }

    public void StatistiqueTest() {

        Stat = createPieChartForm();
         /*  Stat.getToolbar().addCommandToLeftBar("back",null,(evt) -> {
                new EvenementForm().show();
        });*/
        Stat.show();

    }

    public Form getF() {
        return Stat;
    }

    public void setF(Form f) {
        this.Stat = f;
    }

    public statistique() {
        StatistiqueTest();
    }    

}

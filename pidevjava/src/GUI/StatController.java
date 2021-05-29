/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Utils.MaConnexion;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author ArwaBj
 */
public class StatController implements Initializable {

    @FXML
    private BarChart<String,Double> stat;
     @FXML
    private Label label;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), label);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
         fadeTransition.setCycleCount(Animation.INDEFINITE);
        fadeTransition.play();
               Connection cnx = MaConnexion.getInstance().getConnection();

       XYChart.Series<String,Double> series1 =new XYChart.Series<>();
       String requete = "SELECT * FROM reclamation WHERE ( rate >0 and rate<=2 and field like 'Cinema' )";
       try{
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
               
                    series1.getData().add(new XYChart.Data<>(rs.getString("field"),rs.getDouble("rate")));
             
            }
            stat.getData().add(series1);
            series1.setName("Cinema");
       } catch(Exception e) {
           
    }
          XYChart.Series<String,Double> series2 =new XYChart.Series<>();
       String requete2 = "SELECT * FROM reclamation WHERE ( rate >2 and rate<=8 and field like 'restauration' )";
       try{
            PreparedStatement pst = cnx.prepareStatement(requete2);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
               
                    series2.getData().add(new XYChart.Data<>(rs.getString("field"),rs.getDouble("rate")));
             
            }
            stat.getData().add(series2);
            series2.setName("restauration");
       } catch(Exception e) {
           
    }
            XYChart.Series<String,Double> series3=new XYChart.Series<>();
       String requete3 = "SELECT * FROM reclamation WHERE ( rate >2 and rate<=3 and field like 'Parking' )";
       try{
            PreparedStatement pst = cnx.prepareStatement(requete3);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
               
                    series3.getData().add(new XYChart.Data<>(rs.getString("field"),rs.getDouble("rate")));
             
            }
            stat.getData().add(series3);
            series3.setName("Parking");
       } catch(Exception e) {
           
    }     
             XYChart.Series<String,Double> series4=new XYChart.Series<>();
       String requete4 = "SELECT * FROM reclamation WHERE ( rate >1 and rate<=2 and field like 'autre' )";
       try{
            PreparedStatement pst = cnx.prepareStatement(requete4);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
               
                    series4.getData().add(new XYChart.Data<>(rs.getString("field"),rs.getDouble("rate")));
             
            }
            stat.getData().add(series4);
            series4.setName("autre");
       } catch(Exception e) {
           
    } 
       
    }    
    
    }


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import GUI.AddrecController;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import Utils.MaConnexion;

/**
 *
 * @author dell
 */
public class Reclamation {

  private int idrec,abonne_id;
  private String description,field,abonne;
  private Timestamp  datee;
  private double rate;
     

  
  public Reclamation(String abonne, String description, String field) {
        this.abonne = abonne;
        this.description = description;
        this.field = field;
       
    }

    public Reclamation(int abonne_id, String description, String field, double rate) {
        this.abonne_id = abonne_id;
        this.description = description;
        this.field = field;
        this.rate = rate;
    }

 
  public Reclamation(int abonne_id, String description, String field, Timestamp  datee, double rate) {
        this.abonne_id = abonne_id;
        this.description = description;
        this.field = field;
        this.datee = datee;
        this.rate = rate;
    }

    public Reclamation(int idrec, int abonne_id, String description, String field, Timestamp  datee, double rate) {
        this.idrec = idrec;
        this.abonne_id = abonne_id;
        this.description = description;
        this.field = field;
        this.datee = datee;
        this.rate = rate;
    }
            
  
    public Reclamation(Integer parseInt) {
      
    }
    
    public Reclamation() {
      
    }
     public Reclamation(int idrec) {
        this.idrec = idrec;
        
    }
     public Reclamation(String field) {
         this.field = field;
        
    }

  public Reclamation(Integer parseInt,String toString, String toString0,String text,Float parseFloat,Date valueOf) {
    }

    public int getIdrec() {
        return idrec;
    }

    public void setIdrec(int idrec) {
        this.idrec = idrec;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }



    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getAbonne_id() {
        return abonne_id;
    }

    public void setAbonne_id(int abonne_id) {
        this.abonne_id = abonne_id;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "idrec=" + idrec + ", abonne_id=" + abonne_id + ", description=" + description + ", field=" + field + ", datee=" + datee + ", rate=" + rate + "\n";
    }



    public String getAbonne() {
        return abonne;
    }

    public void setAbonne(String abonne) {
        this.abonne = abonne;
    }

    public Timestamp getDatee() {
        return datee;
    }

    public void setDatee(Timestamp datee) {
        this.datee = datee;
    }
     private Connection cnx = MaConnexion.getInstance().getConnection();
     
    public int getcategoid(String value) throws SQLException{
    int result =0;
    String request ="select id from abonne where prenom = '" +value +"'";
    try{
    Statement statement = cnx.createStatement();
    ResultSet rs = statement.executeQuery(request);
    while (rs.next()){
    result = rs.getInt("id");
    }    
    }catch (SQLException troubles){
    troubles.printStackTrace();
    }
     return result;    
    }
    
   

  
    
  
    
}

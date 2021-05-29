/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import com.codename1.l10n.DateFormat;


/**
 *
 * @author dell
 */
public class Reclamation {

  private int idrec,abonne_id;

  private String description,field,abonne,datee;
  private double rate;

    public Reclamation(String text, String text0, String text1) {
      
    }

    public Reclamation(String text, String text0, String text1, String text2) {
       
    }

    public String getAbonne() {
        return abonne;
    }

    public void setAbonne(String abonne) {
        this.abonne = abonne;
    }

    public Reclamation(int idrec, String description, String field, String abonne, String datee, double rate) {
        this.idrec = idrec;
        this.description = description;
        this.field = field;
        this.abonne = abonne;
        this.datee = datee;
        this.rate = rate;
    }

    public int getAbonne_id() {
        return abonne_id;
    }

    public void setAbonne_id(int abonne_id) {
        this.abonne_id = abonne_id;
    }

    public Reclamation(int idrec, int abonne_id, String description, String field, String abonne, String datee, double rate) {
        this.idrec = idrec;
        this.abonne_id = abonne_id;
        this.description = description;
        this.field = field;
        this.abonne = abonne;
        this.datee = datee;
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "idrec=" + idrec + ", abonne_id=" + abonne_id + ", description=" + description + ", field=" + field + ", datee=" + datee + ", rate=" + rate + '}';
    }



  
    

     

    
    public Reclamation() {
      
    }
     public Reclamation(int idrec) {
        this.idrec = idrec;
        
    }
     public Reclamation(String field) {
         this.field = field;
        
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



    

    public String getDatee() {
        return datee;
    }

    public void setDatee(String datee) {
        this.datee = datee;
    }

    

    
}

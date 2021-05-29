/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;


import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import Service.ServiceReclamation;
// ahwkoum mizelo des erreurs bich nabda njarib bil 7aja bil 7aja kima thb behi
/**
 *
 * @author dell
 */
public class Equipement {
    private int id,resto_id,cinema_id;
    private int ref;
    private String nome;
    private double prix;
    private Timestamp dates;

    public Equipement(int resto_id, int cinema_id, int ref, String nome, double prix, Timestamp dates) {
        this.resto_id = resto_id;
        this.cinema_id = cinema_id;
        this.ref = ref;
        this.nome = nome;
        this.prix = prix;
        this.dates = dates;
    }
    
    
      public Equipement(String nome, double prix, int ref) {
       
        this.ref = ref;
        this.nome = nome;
        this.prix = prix;
      
    }
    public Equipement(int id, int resto_id, int cinema_id, int ref, String nome, double prix, Timestamp dates) {
        this.id = id;
        this.resto_id = resto_id;
        this.cinema_id = cinema_id;
        this.ref = ref;
        this.nome = nome;
        this.prix = prix;
        this.dates = dates;
    }

    public Equipement(String text, Integer valueOf, Integer valueOf0) {
        
    }

    public Equipement(String text, int parseInt) {
       
    }

    public Timestamp getDates() {
        return dates;
    }

    public void setDates(Timestamp dates) {
        this.dates = dates;
    }
  
      public Equipement(String nome) {   
            this.nome = nome;
    }
 
      public Equipement() {      
    }
  
    public Equipement(String nome, int cinema_id, int resto_id, double prix, int ref, Timestamp dates) {
        this.resto_id = resto_id;
        this.cinema_id = cinema_id;
        this.ref = ref;
        this.nome = nome;
        this.prix = prix;
        this.dates = dates;
    }
   
   public Equipement(String text, String toString, String toString0, Integer valueOf, Integer valueOf0, Date valueOf1) {
      
    }
   
   public Equipement(String text, Integer valueOf, Double parseDouble) {
      
    }  
   public Equipement(Integer valueOf) {
    }
    public int getId() {
        return id;
    }
//lib.getText(), Integer.valueOf(ref.getText()),Double.parseDouble
    public void setId(int id) {
        this.id = id;
    }

    public int getRef() {
        return ref;
    }

    public void setRef(int ref) {
        this.ref = ref;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }


    public int getResto_id() {
        return resto_id;
    }

    public void setResto_id(int resto_id) {
        this.resto_id = resto_id;
    }

    public int getCinema_id() {
        return cinema_id;
    }

    public void setCinema_id(int cinema_id) {
        this.cinema_id = cinema_id;
    }

  
    @Override
    public String toString() {
       return "Equipement{" + "ref=" + ref + ", resto_id=" + resto_id + ", cinema_id=" + cinema_id + ", nome=" + nome + ", prix=" + prix + ", dates=" + dates + '}'+ "\n";
    }

    public void getResto_id(int resto_id) {
      
    }

  
   
    
}

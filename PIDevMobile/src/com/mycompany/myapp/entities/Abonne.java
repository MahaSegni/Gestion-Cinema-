/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;



/**
 *
 * @author Zeineb
 */
public class Abonne {
   private int id;
   private String nomabonne,prenomabonne,mailabonne;
   private Date datenaissabonne ;
   private int telephoneabonne;
   private String password;
   private String roles;
   private String stripe_id;

    public String getStripe_id() {
        return stripe_id;
    }

    public void setStripe_id(String stripe_id) {
        this.stripe_id = stripe_id;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
   
     public static Abonne abonne=new Abonne();

    public int getId() {
        return id;
    }

    public String getNomabonne() {
        return nomabonne;
    }

    public String getPrenomabonne() {
        return prenomabonne;
    }

    public String getMailabonne() {
        return mailabonne;
    }

    public Date getDatenaissabonne() {
        return datenaissabonne;
    }

    public int getTelephoneabonne() {
        return telephoneabonne;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNomabonne(String nomabonne) {
        this.nomabonne = nomabonne;
    }

    public void setPrenomabonne(String prenomabonne) {
        this.prenomabonne = prenomabonne;
    }

    public void setMailabonne(String mailabonne) {
        this.mailabonne = mailabonne;
    }

    public void setDatenaissabonne(Date datenaissabonne) {
        this.datenaissabonne = datenaissabonne;
    }

    public void setTelephoneabonne(int telephoneabonne) {
        this.telephoneabonne = telephoneabonne;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Abonne() {
    }

    public Abonne(int id, String nomabonne, String prenomabonne, String mailabonne, Date datenaissabonne, int telephoneabonne, String password) {
        this.id = id;
        this.nomabonne = nomabonne;
        this.prenomabonne = prenomabonne;
        this.mailabonne = mailabonne;
        this.datenaissabonne = datenaissabonne;
        this.telephoneabonne = telephoneabonne;
        this.password = password;
    }

    public Abonne(String nomabonne, String prenomabonne, String mailabonne, Date datenaissabonne, int telephoneabonne, String password) {
        this.nomabonne = nomabonne;
        this.prenomabonne = prenomabonne;
        this.mailabonne = mailabonne;
        this.datenaissabonne = datenaissabonne;
        this.telephoneabonne = telephoneabonne;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Abonne{" + "mailabonne=" + mailabonne + '}';
    }
    

    
    
} 

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;


/**
 *
 * @author Zeineb
 */
public class Abonnement {
    private int id;
    private int abonne , typeabonnement;
    private String datedebut,datefin;
    private boolean valide;
    private String datedemande;

    public Abonnement() {
    }

    public Abonnement(int id, int abonne, int typeabonnement, String datedebut, String datefin, boolean valide, String datedemande) {
        this.id = id;
        this.abonne = abonne;
        this.typeabonnement = typeabonnement;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.valide = valide;
        this.datedemande = datedemande;
    }

    public int getId() {
        return id;
    }

    public int getAbonne() {
        return abonne;
    }

    public int getTypeabonnement() {
        return typeabonnement;
    }

    public String getDatedebut() {
        return datedebut;
    }

    public String getDatefin() {
        return datefin;
    }

     public boolean isValide() {
        return valide;
    }
 
    public String getDatedemande() {
        return datedemande;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAbonne(int abonne) {
        this.abonne = abonne;
    }

    public void setTypeabonnement(int typeabonnement) {
        this.typeabonnement = typeabonnement;
    }

    public void setDatedebut(String datedebut) {
        this.datedebut = datedebut;
    }

    public void setDatefin(String datefin) {
        this.datefin = datefin;
    }

    public void setValide(boolean valide) {
        this.valide = valide;
    }

    public void setDatedemande(String datedemande) {
        this.datedemande = datedemande;
    }

    @Override
    public String toString() {
        return "Abonnement{" + "id=" + id + ", abonne=" + abonne + ", typeabonnement=" + typeabonnement + ", datedebut=" + datedebut + ", datefin=" + datefin + ", valide=" + valide + ", datedemande=" + datedemande + '}';
    }
    
    
    
    
}

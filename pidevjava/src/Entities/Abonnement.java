/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author Zeineb
 */
public class Abonnement {
    private int id;
    private int abonne , typeabonnement;
    private Date datedebut,datefin;
    private boolean valide;
    private Date datedemande;

    public Abonnement() {
    }

    public Abonnement(int id, int abonne, int typeabonnement, Date datedebut, Date datefin, boolean valide, Date datedemande) {
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

    public Date getDatedebut() {
        return datedebut;
    }

    public Date getDatefin() {
        return datefin;
    }

     public boolean isValide() {
        return valide;
    }
 
    public Date getDatedemande() {
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

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public void setValide(boolean valide) {
        this.valide = valide;
    }

    public void setDatedemande(Date datedemande) {
        this.datedemande = datedemande;
    }

    @Override
    public String toString() {
        return "Abonnement{" + "id=" + id + ", abonne=" + abonne + ", typeabonnement=" + typeabonnement + ", datedebut=" + datedebut + ", datefin=" + datefin + ", valide=" + valide + ", datedemande=" + datedemande + '}';
    }
    
    
    
    
}

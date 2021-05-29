/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;
import java.time.LocalDate;


/**
 *
 * @author HP
 */
public class Conge {

    private int idconge;
    private Date dateconge;
    private String motifconge;
    private int nbjourconge;
    private int employe_id;

    
    
    public Conge(){}
    public Conge( Date dateconge, String motifconge, int nbjourconge,int employe_id) {
        
        this.dateconge = dateconge;
        this.motifconge = motifconge;
        this.nbjourconge = nbjourconge;
        this.employe_id = employe_id;
    }
    public Conge(int idconge, Date dateconge, String motifconge, int nbjourconge,int employe_id) {
        this.idconge = idconge;
        this.dateconge = dateconge;
        this.motifconge = motifconge;
        this.nbjourconge = nbjourconge;
        this.employe_id = employe_id;
    }

    @Override
    public String toString() {
        //return "idconge" + idconge ;
        return "Conge{" + "idconge=" + idconge + ", dateconge=" + dateconge + ", motifconge=" + motifconge + ", nbjourconge=" + nbjourconge + ", employe_id=" + employe_id + '}';
    }

    
    
    
    public void setIdconge(int idconge) {
        this.idconge = idconge;
    }

    public void setDateconge(Date dateconge) {
        this.dateconge = dateconge;
    }

    public void setMotifconge(String motifconge) {
        this.motifconge = motifconge;
    }

    public void setNbjourconge(int nbjourconge) {
        this.nbjourconge = nbjourconge;
    }

    public void setEmploye_id(int employe_id) {
        this.employe_id = employe_id;
    }
     

    public int getIdconge() {
        return idconge;
    }

    public Date getDateconge() {
        return dateconge;
    }

    public String getMotifconge() {
        return motifconge;
    }

    public int getNbjourconge() {
        return nbjourconge;
    }

    public int getEmploye_id() {
        return employe_id;
    }

    
}

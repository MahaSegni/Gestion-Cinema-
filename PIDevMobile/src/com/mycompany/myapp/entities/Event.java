/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author mahas
 */
public class Event {
    private int id;
    private String nom;
    private int capacite;
    String emplacement;
    
    public Event(int id, String nom, int capacite, String emplacement) {
        this.id = id;
        this.nom = nom;
        this.capacite = capacite;
        this.emplacement=emplacement;
    }

    public Event(String nom, int capacite, String emplacement) {
        this.nom = nom;
        this.capacite = capacite;
        this.emplacement = emplacement;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }
    
    @Override
    public String toString() {
        return "Event[ id=" + id + " ]";
    }
    
}


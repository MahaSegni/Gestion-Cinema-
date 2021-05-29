/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author mahas
 */
@Entity
public class event implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private Integer duree;
    private String emplacement;
    private Boolean etat;
    private Integer place;
    private String capacite;
    private String Date_debut;
    private String Date_fin;

    public event() {
    }

    public event(String nom) {
        this.nom = nom;
    }

    public event(String nom, String Date_debut, String Date_fin) {
        this.nom = nom;
        this.Date_debut = Date_debut;
        this.Date_fin = Date_fin;
    }
    
    public event(String nom, String capacite) {
        this.nom = nom;
        this.capacite = capacite;
    }

    public event(String nom, Integer duree, String emplacement, Integer place, String capacite, String Date_debut, String Date_fin) {
        
        this.nom = nom;
        this.duree = duree;
        this.emplacement = emplacement;
        this.place = place;
        this.capacite = capacite;
        this.Date_debut = Date_debut;
        this.Date_fin = Date_fin;
    }

    public event(String nom, String emplacement, String capacite, String Date_debut, String Date_fin) {
        this.nom = nom;
        this.emplacement = emplacement;
        this.capacite = capacite;
        this.Date_debut = Date_debut;
        this.Date_fin = Date_fin;
    }
    

    
        
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getDuree() {
        return duree;
    }

    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

    public String getCapacite() {
        return capacite;
    }

    public void setCapacite(String capacite) {
        this.capacite = capacite;
    }

    public String getDate_debut() {
        return Date_debut;
    }

    public void setDate_debut(String Date_debut) {
        this.Date_debut = Date_debut;
    }

    public String getDate_fin() {
        return Date_fin;
    }

    public void setDate_fin(String Date_fin) {
        this.Date_fin = Date_fin;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof event)) {
            return false;
        }
        event other = (event) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.event[ id=" + id + " ]";
    }
    
}

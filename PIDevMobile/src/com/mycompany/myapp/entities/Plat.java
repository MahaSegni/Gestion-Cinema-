/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author ASUS
 */
public class Plat {
    private int idp, prixp;
    private String nomp, categorie_id, imagep, description;

    public Plat() {}

    public Plat(int idp, int prixp, String nomp, String categorie_id, String imagep, String description) {
        this.idp = idp;
        this.prixp = prixp;
        this.nomp = nomp;
        this.categorie_id = categorie_id;
        this.imagep = imagep;
        this.description = description;
    }

    public Plat(int prixp, String nomp, String categorie_id, String imagep, String description) {
        this.prixp = prixp;
        this.nomp = nomp;
        this.categorie_id = categorie_id;
        this.imagep = imagep;
        this.description = description;
    }

    public int getIdp() {
        return idp;
    }

    public void setIdp(int idp) {
        this.idp = idp;
    }

    public int getPrixp() {
        return prixp;
    }

    public void setPrixp(int prixp) {
        this.prixp = prixp;
    }

    public String getNomp() {
        return nomp;
    }

    public void setNomp(String nomp) {
        this.nomp = nomp;
    }

    public String getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(String categorie_id) {
        this.categorie_id = categorie_id;
    }

    public String getImagep() {
        return imagep;
    }

    public void setImagep(String imagep) {
        this.imagep = imagep;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;



/**
 *
 * @author mahas
 */
public class Plat {
    private int idp, prixp, categorie_id;
    private String nomp, imagep,description;
    

    public Plat() {
    }

    public Plat(int idp, String nomp, int prixp, int categorie_id, String imagep, String description) {
        this.idp = idp;
        this.nomp = nomp;
        this.prixp = prixp;
        this.categorie_id = categorie_id;
        this.imagep = imagep;
        this.description = description;
    
    }

    public Plat(String nomp, int prixp, int categorie_id, String imagep, String description) {
        this.nomp = nomp;
        this.prixp = prixp;
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

    public int getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(int categorie_id) {
        this.categorie_id = categorie_id;
    }

    public String getNomp() {
        return nomp;
    }

    public void setNomp(String nomp) {
        this.nomp = nomp;
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

    

    @Override
    public String toString() {
        return "Plat: " + "idp=" + idp + ", nomp=" + nomp + ", prixp=" + prixp + ", categorie_id=" + categorie_id + ", imagep=" + imagep + ", description=" + description +"\n";
    }
    
}

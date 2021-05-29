/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import com.codename1.ui.ComboBox;
import com.codename1.ui.List;
import java.util.ArrayList;

/**
 *
 * @author Zeineb
 */
public class TypeAbonnement {
    private int id ; 
    private String type,description;
    private float prix;
    private String image;
    private   int placesdispo; 
   //  public static TypeAbonnement typeabo=new TypeAbonnement();
     
     public static ArrayList<TypeAbonnement> Panier=new ArrayList<TypeAbonnement>();

    public TypeAbonnement() {
    }

    public TypeAbonnement(int id, String type, String description, float prix, String image, int placesdispo) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.prix = prix;
        this.image = image;
        this.placesdispo = placesdispo;
    } 

    public TypeAbonnement(String type, String description, float prix, String image, int placesdispo) {
        this.type = type;
        this.description = description;
        this.prix = prix;
        this.image = image;
        this.placesdispo = placesdispo;
    }
    
    

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TypeAbonnement other = (TypeAbonnement) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public float getPrix() {
        return prix;
    }

    public String getImage() {
        return image;
    }

    public int getPlacesdispo() {
        return placesdispo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPlacesdispo(int placesdispo) {
        this.placesdispo = placesdispo;
    }

    @Override
    public String toString() {
        return "TypeAbonnement{" + "id=" + id + ", type=" + type + ", description=" + description + ", prix=" + prix + ", image=" + image + ", placesdispo=" + placesdispo + '}';
    }
    
    
    
}

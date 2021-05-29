/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import javafx.scene.control.TableColumn;

/**
 *
 * @author Administrateur
 */
public class Cellule {
   
    private String idCellule ;
    public boolean dispo ;


   

    public Cellule() {
    }

    public Cellule( String text , boolean b) {
        this.idCellule = idCellule;
        this.dispo = dispo;
    }

   


    public String getIdCellule() {
        return idCellule;
    }

    public void setIdCellule(String idCellule) {
        this.idCellule = idCellule;
    }

    public boolean isDispo() {
        return dispo;
    }

    public boolean setDispo(boolean dispo) {
        this.dispo = dispo;
        return dispo ;
    }
    
    public boolean getDispo() {
        return dispo;
    }

    @Override
    public String toString() {
        return "cellule{" + "idCellule=" + idCellule + ", dispo=" + dispo + '}';
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;



/**
 *
 * @author mahas
 */
public class Film {
     private int id_film,categorie;
    private String nomfilm,descriptionf,filename;
    private Date datesortie;

    public Film() {
    }

    public Film(int id_film, String nomfilm) {
        this.id_film = id_film;
        this.nomfilm = nomfilm;
    }

    public Film(int id_film, String nomfilm, String descriptionf, String filename, Date datesortie) {
        this.id_film = id_film;
        this.nomfilm = nomfilm;
        this.descriptionf = descriptionf;
        this.filename = filename;
        this.datesortie = datesortie;
    }

    public Film( String nomfilm, String descriptionf, String filename, int categorie, Date datesortie) {
     
        this.nomfilm = nomfilm;
        this.descriptionf = descriptionf;
        this.filename = filename;
        this.categorie = categorie;
        this.datesortie = datesortie;
    }

    public Film( String nomfilm, String descriptionf, String filename, Date datesortie) {
        this.nomfilm = nomfilm;
        this.descriptionf = descriptionf;
        this.filename = filename;
        this.datesortie = datesortie;
    }

    

    public int getCategorie() {
        return categorie;
    }

    public void setCategorie(int categorie) {
        this.categorie = categorie;
    }

    public int getId_film() {
        return id_film;
    }

    public void setId_film(int id_film) {
        this.id_film = id_film;
    }

    public String getNomfilm() {
        return nomfilm;
    }

    public void setNomfilm(String nomfilm) {
        this.nomfilm = nomfilm;
    }

    public String getDescriptionf() {
        return descriptionf;
    }

    public void setDescriptionf(String descriptionf) {
        this.descriptionf = descriptionf;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Date getDatesortie() {
        return datesortie;
    }

    public void setDatesortie(Date datesortie) {
        this.datesortie = datesortie;
    }

    @Override
    public String toString() {
        return "Film{" + "id_film=" + id_film + ", nomfilm=" + nomfilm + ", descriptionf=" + descriptionf + ", filename=" + filename + ", datesortie=" + datesortie + '}'+"\n";
    }
}

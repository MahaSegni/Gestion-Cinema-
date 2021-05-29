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
public class Avisclient {
    private int id,idab;
    private String descR;
private double rating;
    public Avisclient(int id, int rating, int idab, String descR) {
        this.id = id;
        this.rating = rating;
        this.idab = idab;
        this.descR = descR;
    }

    public Avisclient(double rating, int idab, String descR) {
        this.rating = rating;
        this.idab = idab;
        this.descR = descR;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getIdab() {
        return idab;
    }

    public void setIdab(int idab) {
        this.idab = idab;
    }

    public String getDescR() {
        return descR;
    }

    public void setDescR(String descR) {
        this.descR = descR;
    }

   
}

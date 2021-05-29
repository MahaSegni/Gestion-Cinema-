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
public class AvisClient {
    public int id;
    public float rating;
    private String descR;

    public AvisClient() {
    }

    public AvisClient(float rating, String descR) {
        this.rating = rating;
        this.descR = descR;
    }

    public AvisClient(int id, float rating, String descR) {
        this.id = id;
        this.rating = rating;
        this.descR = descR;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getDescR() {
        return descR;
    }

    public void setDescR(String descR) {
        this.descR = descR;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;
//
//import java.sql.Time;

import java.util.Date;

//import java.sql.Date;

/**
 *
 * @author mahas
 */
public class Cinema {
    private int num,nbr,film;
    Date date;
   // LocalTime heurep;

    public Cinema() {
    }

    public Cinema(int nbr) {
        this.nbr = nbr;
    }

    public Cinema(int num, int nbr) {
        this.num = num;
        this.nbr = nbr;
        
    }

    public Cinema(int nbr, int film, Date date) {
        this.nbr = nbr;
        this.film = film;
        this.date = date;
    }

    public int getFilm() {
        return film;
    }

    public void setFilm(int film) {
        this.film = film;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

   

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getNbr() {
        return nbr;
    }

    public void setNbr(int nbr) {
        this.nbr = nbr;
    }

//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }

    @Override
    public String toString() {
        return "Cinema{" + "num=" + num + ", nbr=" + nbr + ", film=" + film + ", date=" + date + '}';
    }

 
   
    
}

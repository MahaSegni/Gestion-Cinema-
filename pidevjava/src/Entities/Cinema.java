/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  Entities;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author mahas
 */
public class Cinema {
    private int num,nbr,film;
    private Date date;
    private Time heurep;

    public Cinema() {
    }

    public Cinema(int num, int nbr, int film, Date date, Time heurep) {
        this.num = num;
        this.nbr = nbr;
        this.film = film;
        this.date = date;
    this.heurep = heurep;
    }

    public Cinema(int nbr, int film, Date date, Time heurep) {
        this.nbr = nbr;
        this.film = film;
        this.date = date;
        this.heurep = heurep;
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

    public Time getHeurep() {
        return heurep;
    }

    public void setHeurep(Time heurep) {
        this.heurep = heurep;
    }

    @Override
    public String toString() {
        return "Cinema: " + "num=" + num + ", nbr=" + nbr + ", film=" + film + ", date=" + date + ", heurep=" + heurep +"\n";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;

/**
 *
 * @author Administrateur
 */
public class Reservation {
    private int idReservation;
    private Date dateD;
    private Date DateF;
    private String matricule ;
    private String idCell ;

    public Reservation(int idReservation, Date dateD, Date DateF, String matricule, String idCell) {
        this.idReservation = idReservation;
        this.dateD = dateD;
        this.DateF = DateF;
        this.matricule = matricule;
        this.idCell = idCell;
    }

    public Reservation(Date dateD, Date DateF, String matricule, String idCell) {
        this.dateD = dateD;
        this.DateF = DateF;
        this.matricule = matricule;
        this.idCell = idCell;
    }

    public Reservation(int idReservation) {
        this.idReservation = idReservation;
    }

    public Reservation() {
    }

 



    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    public Date getDateD() {
        return dateD;
    }

    public void setDateD(Date dateD) {
        this.dateD = dateD;
    }

    public Date getDateF() {
        return DateF;
    }

    public void setDateF(Date DateF) {
        this.DateF = DateF;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getIdCell() {
        return idCell;
    }

    public void setIdCell(String idCell) {
        this.idCell = idCell;
    }

    @Override
    public String toString() {
        return "reservation{" + "idReservation=" + idReservation + ", dateD=" + dateD + ", DateF=" + DateF + ", matricule=" + matricule + ", idCell=" + idCell + '}';
    }
    
    
    
}

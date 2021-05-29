/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.EnumSet;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Cellule;
import models.Reservation;
import Utils.MaConnexion;


/**
 *
 * @author Administrateur
 */
public class ServiceReservation implements IService<Reservation>{
       Connection cnx = MaConnexion.getInstance().getConnection();

    @Override
    public void ajouter(Reservation t) {
         try {
            String requete = "INSERT INTO reservation (dateD,dateF,matricule,idCell) VALUES (?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setDate(1, t.getDateD());
            pst.setDate(2, t.getDateF());
            pst.setString(3, t.getMatricule());
            pst.setString(4, t.getIdCell());
            pst.executeUpdate();
            System.out.println("Reservation ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }   
        
    }

    // @Override
    public void supprimer(String m) {
         try {
            String requete = "DELETE FROM reservation WHERE matricule=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, m);
            pst.executeUpdate();
            System.out.println("reservation supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
  

    @Override
    public ObservableList<Reservation> afficher(){
       ObservableList<Reservation> list = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM reservation";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Reservation(rs.getInt("idReservation"), rs.getDate("dateD"), rs.getDate("dateF"),rs.getString("matricule"), rs.getString("idCell")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
    public ObservableList<Reservation> rechercherParDate(String date1,String date2) throws SQLException {
       ObservableList<Reservation> list = FXCollections.observableArrayList();

        try {

            String requete = "SELECT * FROM reservation where dateD <='" + date1 + " and dateD >= "+ date2 +"'";

            Statement pst = cnx.createStatement();
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                list.add(new Reservation(rs.getInt("idReservation"), rs.getDate("dateD"), rs.getDate("dateF"), rs.getString("matricule") , rs.getString("idCell")));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;

    }
    
 
    
      public ObservableList<Reservation> trie() {
           ObservableList<Reservation> list = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM reservation order by dateD desc";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Reservation(rs.getInt("idReservation"), rs.getDate("dateD"), rs.getDate("dateF"),rs.getString("matricule"), rs.getString("idCell")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

      
      
   


   

    
}

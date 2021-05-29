/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Film;
import Utils.MaConnexion;
import ds.desktop.notify.DesktopNotify;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static javax.swing.UIManager.getInt;

/**
 *
 * @author mahas
 */
public class ServiceFilm {
    private Connection con = MaConnexion.getInstance().getConnection();
     private Statement ste;
     PreparedStatement pst;
      public ArrayList<Film> afficher() {
        ArrayList<Film> myList = new ArrayList<>();
        try {
            String query = "Select * from film";
            ste = con.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()) {
                Film v = new Film();
                v.setId_film(rs.getInt("id_film"));
                v.setNomfilm(rs.getString("nomfilm"));
                v.setDescriptionf(rs.getString("descriptionf"));
                v.setDatesortie(rs.getDate("datesortie"));
                v.setFilename(rs.getString("filename"));
                myList.add(v);
             //System.out.println(v);
              //System.out.println("affichage fait");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
}
            public void ajouter(Film v) throws SQLException {
    ServiceFilm CR = new ServiceFilm();
        
                    String query = "INSERT INTO `film`(`nomfilm`, `descriptionf`, `filename`, `datesortie`)" + " VALUES (?,?,?,?)";
                    pst = con.prepareStatement(query);
//ste = con.createStatement();
                    pst.setString(1, v.getNomfilm());
                    pst.setString(2, v.getDescriptionf());
                    pst.setString(3, v.getFilename());
                    pst.setDate(4, new java.sql.Date(v.getDatesortie().getTime()));
                    pst.executeUpdate();
                   DesktopNotify.showDesktopMessage(
    "success !",
    "Film added successfully!",
    DesktopNotify.SUCCESS);
    }
       public void updatetab1(Film d) throws SQLException {
            try {
        PreparedStatement PS=con.prepareStatement("UPDATE `film` SET `nomfilm`=? ,`descriptionf`=?,`filename`=?, `datesortie`=?  WHERE `id_film`=?");
        pst.setString(1, d.getNomfilm());
            pst.setString(2, d.getDescriptionf());
            pst.setString(3, d.getFilename());
            pst.setDate(4, new java.sql.Date(d.getDatesortie().getTime()));      
             pst.setInt(5,d.getId_film());       
        PS.executeUpdate();
     
            } catch (Exception e) {
                Logger.getLogger(MaConnexion.class.getName()).log(Level.SEVERE,null,e);
            }

    }

      public void modifierDemande(Film d) {
        String req = "update film set nomfilm=?, descriptionf=?,filename=?,datesortie=? where id_film=? ";

        try {
            pst = con.prepareStatement(req);
            pst.setString(1, d.getNomfilm());
            pst.setString(2, d.getDescriptionf());
            pst.setString(3, d.getFilename());
            pst.setDate(4, new java.sql.Date(d.getDatesortie().getTime()));
             DesktopNotify.showDesktopMessage(
    "success !",
    "Film updated successfully!",
    DesktopNotify.SUCCESS);
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("erreur");
        }

    }
      public void supprimerFilm(int id) {
        String req = "delete from film where id_film=? ";

        try {
            pst = con.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
           DesktopNotify.showDesktopMessage(
    "success !",
    "Film deleted successfully!",
    DesktopNotify.SUCCESS);
        } catch (SQLException ex) {
          System.out.println("erreur");
        }
      }
         public int fil_nom(String conv) throws SQLException {
       
        String qry = "SELECT * from film where nomfilm =" + "'" + conv + "'";
        ResultSet res = ste.executeQuery(qry);
        while (res.next()) {
            return res.getInt(1);
        }
        return 0;

    }
    
     public int getIdfi(String c) {
      
        String sql = "select id_film from film where nomfilm=" + "'" + c + "'";
 
        try {
            ResultSet rs = ste.executeQuery(sql);
            rs.next();
            int id = rs.getInt(1);
            return id;
          
        } catch (SQLException ex) {
            Logger.getLogger(ServiceFilm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
     }
    public void decri(int nbr) {
        try {
            
            String req1 = "update cinema set  nbr=nbr-1 where id_film= ? ";
            
            PreparedStatement st1 = con.prepareStatement(req1);
            
            st1.setInt(1,nbr);
            st1.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(MaConnexion.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    
        public ObservableList<Film> recherchecongeById(String charac){
               ObservableList<Film> liste = FXCollections.observableArrayList();
        String requete = "select * from film where  id_film LIKE `"+charac+"` or nomfilm LIKE `"+charac+"` or descriptionf LIKE `"+charac+"`";

     // String requete = "select * from film where  id_film = "+charac+" or nomfilm = "+charac+" or descriptionf = "+charac;
      // String requete = "SELECT * FROM conge WHERE idconge="+(char)34+id+(char)34;     
        try {
             ste = con.createStatement();
            ResultSet rs = ste.executeQuery(requete);
            while(rs.next()) {
                Film c = new Film();
           
                c.setId_film(rs.getInt("id_film"));;
                c.setNomfilm(rs.getString("nomfilm"));
                c.setDescriptionf(rs.getString("descriptionf"));
                c.setFilename(rs.getString("filename"));
                c.setDatesortie(rs.getDate("datesortie"));
                c.setCategorie(rs.getInt("categorie"));;
                liste.add(c);
                 System.out.println(liste);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MaConnexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
       
    }
      
         public ArrayList<Film> getAllFillter(int idc) {
        ArrayList<Film> myList = new ArrayList<>();
        try {
            String query = "Select * from film where id_film ='" + idc +"'";
            ste = con.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()) {
                Film v = new Film();
                v.setId_film(rs.getInt("id_film"));
                v.setNomfilm(rs.getString("nomfilm"));
                v.setDescriptionf(rs.getString("descriptionf"));
                v.setDatesortie(rs.getDate("datesortie"));
                v.setFilename(rs.getString("filename"));
                myList.add(v);

               // System.out.println("");
           
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
}

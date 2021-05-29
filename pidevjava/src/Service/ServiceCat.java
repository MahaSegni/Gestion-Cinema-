/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import ds.desktop.notify.DesktopNotify;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import Entities.Cat;
import Interface.IServiceCat;
import Utils.MaConnexion;

/**
 *
 * @author bhk
 */

public class ServiceCat implements IServiceCat<Cat>{

    Connection cnx = MaConnexion.getInstance().getConnection();
    PreparedStatement pst;
    private Statement ste;
    
    @Override
    public void add(Cat v) {
        try {
            ServiceCat CR = new ServiceCat();
            
            String query = "INSERT INTO `categorie`(`nomc`)" + " VALUES (?)";
            pst = cnx.prepareStatement(query);
//ste = con.createStatement();
pst.setString(1, v.getNomc());

pst.executeUpdate();
System.out.println("Categorie ajoutée ");

        } catch (SQLException ex) {
            Logger.getLogger(ServiceCat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

 @Override
    public ArrayList<Cat> getAll() {
         ArrayList<Cat> myList = new ArrayList<>();
        try {
            String query = "Select * from categorie";
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()) {
                Cat v = new Cat();
                v.setIdc(rs.getInt("idc"));
                v.setNomc(rs.getString("nomc"));
                myList.add(v);
                  
            System.out.println("");
              //System.out.println("affichage fait");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    @Override
    public void modifier(Cat t, int id) throws SQLException {
      String req = "update categorie set nomc=? where idc=? ";

        try {
            pst = cnx.prepareStatement(req);
            pst.setString(1, t.getNomc());
            pst.setInt(2, id);
            pst.executeUpdate();
DesktopNotify.showDesktopMessage(
    "success !",
    "Categorie updated successfully!",
    DesktopNotify.SUCCESS);
        } catch (SQLException ex) {
            System.out.println("erreur");
        }

    }

 @Override
    public void supprimer(int id) throws SQLException {
    String req = "delete from categorie where idc=? ";

        try {
            pst = cnx.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
DesktopNotify.showDesktopMessage(
    "success !",
    "Categorie deleted successfully!",
    DesktopNotify.SUCCESS);
        } catch (SQLException ex) {
          System.out.println("erreur");
          
        }
       
    }
    public void rechercher(String charac) {
        String query = "select * from categorie where  nomc LIKE '%"+charac+"%'";
        List<Cat> list = new ArrayList<>();
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            rs.next();
        System.out.println("Id Cat : "+rs.getInt("idc")+"\n Nom Cat : "+rs.getString("nomc"));

           
        } catch (SQLException ex) {
            System.out.println("aucune categorie disponible!");
        }
       
    }
    /*
    public Cat rechercherById(int charac) {
        String query = "select * from categorie where  idc ='"+charac+"'";
        Cat c = new Cat();
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            c.setIdc(rs.getInt("idc"));
            c.setNomc(rs.getString("nomc"));
           
                            System.out.println("Id Cat : "+rs.getInt("idc")+"\n Nom Cat : "+rs.getString("nomc"));

           
        } catch (SQLException ex) {
            System.out.println("aucune categorie disponible!");
        }
       return c;
    } 
*/
    
     public int categorie_nom(String conv) throws SQLException {
       
        String qry = "SELECT * from categorie where nomc =" + "'" + conv + "'";
        ResultSet res = ste.executeQuery(qry);
        while (res.next()) {
            return res.getInt(1);
        }
        return 0;

    }
     
     public int getIdCategorie(String c) {
        c="sushi" ;
        String sql = "select id from categorie where nomc=" + "'" + c + "'";
 
        try {
            ResultSet rs = ste.executeQuery(sql);
            rs.next();
            int id = rs.getInt(1);
            System.out.println("Id fil cat "+id);
            return 7;
          
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCat.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
        

    }
     
     public List<Cat> rechercheCatById(String id){
               List<Cat> liste = FXCollections.observableArrayList();
               
        String requete = "SELECT * FROM categorie WHERE  idc LIKE '%"+id+"%' or nomc LIKE '%"+id+"%' " ;
     
        try {
             ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(requete);
            while(rs.next()) {
                 Cat v = new Cat();
                v.setIdc(rs.getInt("idc"));
                v.setNomc(rs.getString("nomc"));
                
             
                liste.add(v);
                 //System.out.println(liste);
            }
        } catch (SQLException ex) {
          System.out.println("erreur lors de la recherche");
        }
        return liste;  
    }  

    
}

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

import Entities.Plat;
import Interface.IServiceCat;
import Utils.MaConnexion;

/**
 *
 * @author bhk
 */
public class ServicePlat implements IServiceCat<Plat> {

    Connection cnx = MaConnexion.getInstance().getConnection();
    PreparedStatement pst;
    private Statement ste;

   

    @Override
    public void add(Plat v) {
        try {
            ServicePlat CR = new ServicePlat();

            String query = "INSERT INTO `plat`(`nomp`, `prixp`, `categorie_id`, `imagep`, `description`)" + " VALUES (?,?,?,?,?)";
            pst = cnx.prepareStatement(query);
//ste = con.createStatement();
                    
            pst.setString(1, v.getNomp());
            pst.setInt(2, v.getPrixp());
            pst.setInt(3, v.getCategorie_id());
            pst.setString(4, v.getImagep());
            pst.setString(5, v.getDescription());

            pst.executeUpdate();
            System.out.println("Plat ajouté ");

        } catch (SQLException ex) {
            Logger.getLogger(ServicePlat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifier(Plat t, int id) throws SQLException {
        String req = "update plat set nomp=?, prixp=?, categorie_id=?, imagep=?, description=? where idp=? ";

        try {
            pst = cnx.prepareStatement(req);
            pst.setString(1, t.getNomp());
            pst.setInt(2, t.getPrixp());
            pst.setInt(3, t.getCategorie_id());
            pst.setString(4, t.getImagep());
            pst.setString(5, t.getDescription());
            pst.setInt(6, id);
            
            pst.executeUpdate();
            DesktopNotify.showDesktopMessage(
                    "success !",
                    "Plat updated successfully!",
                    DesktopNotify.SUCCESS);
        } catch (SQLException ex) {
            System.out.println("erreur");
        }

    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req = "delete from plat where idp=? ";

        try {
            pst = cnx.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
            DesktopNotify.showDesktopMessage(
                    "success !",
                    "Plat deleted successfully!",
                    DesktopNotify.SUCCESS);
        } catch (SQLException ex) {
            System.out.println("erreur");

        }
    }

    @Override
    public ArrayList<Plat> getAll() {
        ArrayList<Plat> myList = new ArrayList<>();
        try {
            String query = "Select * from plat";
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()) {
                Plat p = new Plat();

                p.setIdp(rs.getInt("idp"));
                p.setNomp(rs.getString("nomp"));
                p.setPrixp(rs.getInt("prixp"));
                p.setCategorie_id(rs.getInt("categorie_id"));
                p.setImagep(rs.getString("imagep"));
                p.setDescription(rs.getString("description"));
                myList.add(p);

                System.out.println("");
                //System.out.println("affichage fait");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
    
    
     public ArrayList<Plat> getAllFillter(int idc) {
        ArrayList<Plat> myList = new ArrayList<>();
        try {
            String query = "Select * from plat where categorie_id ='" + idc +"'";
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()) {
                Plat p = new Plat();

                p.setIdp(rs.getInt("idp"));
                p.setNomp(rs.getString("nomp"));
                p.setPrixp(rs.getInt("prixp"));
                p.setCategorie_id(rs.getInt("categorie_id"));
                p.setImagep(rs.getString("imagep"));
                p.setDescription(rs.getString("description"));
                myList.add(p);

                System.out.println("");
                //System.out.println("affichage fait");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
    
    /*
    public void rechercherPlat(String charac) {
        String query = "select * from plat where  nomp LIKE '%" + charac + "%' ";
        
        List<Plat> list = new ArrayList<>();
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            rs.next();
            System.out.println("Id Cat : " + rs.getInt("idc") + "\n Nom Cat : " + rs.getString("nomc"));

        } catch (SQLException ex) {
            System.out.println("aucune categorie disponible!");
        }

    }
*/
/*
    public Cat rechercherById(int charac) {
        String query = "select * from categorie where  idc ='" + charac + "'";
        Cat c = new Cat();
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            c.setIdc(rs.getInt("idc"));
            c.setNomc(rs.getString("nomc"));

            System.out.println("Id Cat : " + rs.getInt("idc") + "\n Nom Cat : " + rs.getString("nomc"));

        } catch (SQLException ex) {
            System.out.println("aucune categorie disponible!");
        }
        return c;
    }
*/

   public List<Plat> recherchePlatById(String id){
               List<Plat> liste = FXCollections.observableArrayList();
               
        String requete = "SELECT * FROM categorie WHERE  idp LIKE '%"+id+"%' or nomp LIKE '%"+id+"%'  or prixp LIKE '%"+id+"%' or categorie_id LIKE '%"+id+"%'or imagep LIKE '%"+id+"%'or description LIKE '%"+id+"%' "  ;
     
        try {
             ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(requete);
            while(rs.next()) {
                 Plat v = new Plat();
                v.setIdp(rs.getInt("idp"));
                v.setNomp(rs.getString("nomp"));
                v.setPrixp(rs.getInt("prixp"));
                v.setCategorie_id(rs.getInt("categorie_id"));
                v.setImagep(rs.getString("imagep"));
                v.setDescription(rs.getString("description"));
                
                
             
                liste.add(v);
                 //System.out.println(liste);
            }
        } catch (SQLException ex) {
          System.out.println("erreur lors de la recherche");
        }
        return liste;  
    }  

}


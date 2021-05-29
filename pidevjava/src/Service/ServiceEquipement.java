/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.sql.Connection;
import java.time.format.DateTimeFormatter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Entities.Equipement;
import Entities.Reclamation;
import Utils.MaConnexion;


/**
 *
 * @author dell
 */ 
public class ServiceEquipement implements IService<Equipement>{

    Connection cnx = MaConnexion.getInstance().getConnection();
    @Override
    public void ajouter(Equipement t) {
         try {
             
             
            String requete = "INSERT INTO equipement (ref,nome,resto_id,cinema_id,prix,dates) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(2, t.getNome());
            pst.setDouble(5, t.getPrix());
            pst.setInt(1, t.getRef());
            pst.setInt(3, t.getResto_id());
            pst.setInt(4, t.getCinema_id());
            pst.setTimestamp(6, t.getDates());
            System.out.println(t.getRef()+"/"+t.getNome()+"/"+t.getPrix()+"/"+t.getResto_id()+"/"+t.getDates()+"/"+t.getCinema_id()+"/");
             pst.executeUpdate();
           // System.out.println(t.getRef()+"/"+t.getNome()+"/"+t.getPrix()+"/"+t.getResto_id()+"/"+t.getDates()+"/"+t.getCinema_id()+"/");
           
            System.out.println("Equipement ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

   
    public void supprimer(Equipement t) {
          try {
            String requete = "DELETE FROM equipement WHERE nome=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getNome());
            pst.executeUpdate();
            System.out.println("Equipement supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    //maha d9i9a
    public void modifier(Equipement t) {
        try {
            String requete = "UPDATE equipement SET nome=?, prix=? WHERE ref=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
           ;
            pst.setString(1, t.getNome());
            pst.setInt(3, t.getRef());
            pst.setDouble(2, t.getPrix());
            pst.executeUpdate();
            System.out.println(" Equipement Modifiée! ");
        } catch (SQLException ex) {
            System.out.println(" erreur de modification! ");
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public  ObservableList afficher() {
             ObservableList<Equipement> list = FXCollections.observableArrayList();
        try {
         //String query = "SELECT * FROM (equipement A Inner Join cinema B ON A.cinema_id = B.id) Inner Join categorie C ON A.resto_id = C.id";
          String query ="SELECT * FROM equipement";
          PreparedStatement pst = cnx.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Equipement v = new Equipement();
                v.setRef(rs.getInt("ref"));
                v.setDates(rs.getTimestamp("dates"));
                v.setCinema_id(rs.getInt("cinema_id"));
                v.setResto_id(rs.getInt("resto_id"));
                v.setPrix(rs.getDouble("prix"));
                v.setNome(rs.getString("nome"));
                list.add(v);
                //System.err.println(rs.getString("nom_categorie"));
                //System.err.println(rs.getString("nbr"));
            }

        } catch (SQLException ex) {
            System.out.println("erreur");
        }
        return list;

    }
  /*  public List<Equipement> rechercherParNom(String nom) throws SQLException{
        List<Equipement> list = new ArrayList<>();

        try {

            String requete = "SELECT * FROM equipement where nome='" + nom + "'";

            Statement pst = cnx.createStatement();
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
               Equipement v = new Equipement();
                v.setRef(rs.getInt("ref"));
                v.setDates(rs.getDate("dates"));
                v.setCinema_id(rs.getInt("cinema_id"));
                v.setResto_id(rs.getInt("resto_id"));
                v.setPrix(rs.getDouble("prix"));
                v.setNome(rs.getString("nome"));
                list.add(v);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;

    }*/
     public  ObservableList rechercher(String charac) {
        String requete = "select * from equipement where  nome LIKE '%"+charac+"%' or prix LIKE '%"+charac+"%' or dates LIKE '%"+charac+"%'";
              ObservableList<Equipement> list = FXCollections.observableArrayList();
        try {
            Statement stm = cnx.createStatement();
            ResultSet rst = stm.executeQuery(requete);
            while (rst.next()) {
                Equipement result = new Equipement();
                result.setNome(rst.getString("nome"));
                result.setPrix(rst.getDouble("prix"));
                result.setDates(rst.getTimestamp("dates"));
                list.add(result);
            }
        } catch (SQLException ex) {
            System.out.println("aucune Equipement disponible!");
        }
        return list;
    }
       public ObservableList Trierpardate() {
             ObservableList<Equipement> list = FXCollections.observableArrayList();
           try {
         //String query = "SELECT * FROM (equipement A Inner Join cinema B ON A.cinema_id = B.id) Inner Join categorie C ON A.resto_id = C.id";
          String query ="SELECT * FROM equipement ORDER BY dates DESC";
          PreparedStatement pst = cnx.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Equipement v = new Equipement();
                v.setRef(rs.getInt("ref"));
                v.setDates(rs.getTimestamp("dates"));
                v.setCinema_id(rs.getInt("cinema_id"));
                v.setResto_id(rs.getInt("resto_id"));
                v.setPrix(rs.getDouble("prix"));
                v.setNome(rs.getString("nome"));
                list.add(v);
                //System.err.println(rs.getString("nom_categorie"));
                //System.err.println(rs.getString("nbr"));
            }

        } catch (SQLException ex) {
            System.out.println("erreur");
        }
        return list;

    }
    
        public ObservableList TrierparPrix() {
             ObservableList<Equipement> list = FXCollections.observableArrayList();
            try {
         //String query = "SELECT * FROM (equipement A Inner Join cinema B ON A.cinema_id = B.id) Inner Join categorie C ON A.resto_id = C.id";
          String query ="SELECT * FROM equipement ORDER BY prix DESC";
          PreparedStatement pst = cnx.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Equipement v = new Equipement();
                v.setRef(rs.getInt("ref"));
                v.setDates(rs.getTimestamp("dates"));
                v.setCinema_id(rs.getInt("cinema_id"));
                v.setResto_id(rs.getInt("resto_id"));
                v.setPrix(rs.getDouble("prix"));
                v.setNome(rs.getString("nome"));
                list.add(v);
                //System.err.println(rs.getString("nom_categorie"));
                //System.err.println(rs.getString("nbr"));
            }

        } catch (SQLException ex) {
            System.out.println("erreur");
        }
        return list;

    }
         public List<Equipement> Equipementplusachete() {
          ArrayList<Equipement> List = new ArrayList<>();
        try {
         //String query = "SELECT * FROM (equipement A Inner Join cinema B ON A.cinema_id = B.id) Inner Join categorie C ON A.resto_id = C.id";
          String query ="SELECT * FROM equipement";
          PreparedStatement pst = cnx.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Equipement v = new Equipement();
                v.setRef(rs.getInt("ref"));
                v.setDates(rs.getTimestamp("dates"));
                v.setCinema_id(rs.getInt("cinema_id"));
                v.setResto_id(rs.getInt("resto_id"));
                v.setPrix(rs.getDouble("prix"));
                v.setNome(rs.getString("nome"));
                List.add(v);
                //System.err.println(rs.getString("nom_categorie"));
                //System.err.println(rs.getString("nbr"));
            }
             int  equipecinema=0;
            int equiperesto=0;
            for(int i = 0;i<List.size();i++){
            if(List.get(i).getResto_id()==0){
            equipecinema= equipecinema+1;
            } else 
                if(List.get(i).getCinema_id()==0){
            equiperesto= equiperesto+1;
            }
            }
            if(equiperesto>equipecinema){
            System.out.println("le secteur restauration utilise des equipements plus que cinema");
            } else  if(equiperesto==equipecinema){
            System.out.println("le secteur restauration utilise des equipements le meme que cinema");
            } else System.out.println("le secteur restauration utilise des equipements moins que cinema");

        } catch (SQLException ex) {
            System.out.println("erreur");
        }
        return List;

    }
         
 
    public int getcatid(String c) {
     
        String sql = "select idc from categorie where nomc =" + "'" + c + "'";
 
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            rs.next();
            int id = rs.getInt(1);
            return id;
         
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEquipement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
       

    }
     public int getcin(int c) {
     
        String sql = "select num from cinema where num =" + "'" + c + "'";
 
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            rs.next();
            int id = rs.getInt(1);
            return id;
         
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEquipement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
       

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import models.Cellule;
import Utils.MaConnexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Administrateur
 */
public class ServiceCellule implements IService<Cellule>  {
    
        Connection cnx = MaConnexion.getInstance().getConnection();


    @Override
    public void ajouter(Cellule t) {
    try {
            String requete = "INSERT INTO cellule (idCellule,dispo) VALUES (?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getIdCellule());
            pst.setBoolean(2, t.isDispo());
            pst.executeUpdate();
            System.out.println("Cellule ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }   
    }

  //  @Override
    public void supprimer(String t) {
        try {
            String requete = "DELETE FROM cellule WHERE idCellule=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t);
            pst.executeUpdate();
            System.out.println("Celulle supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    

    @Override
    public ObservableList<Cellule> afficher(){
   ObservableList<Cellule> list = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM cellule";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                
               Cellule v = new Cellule();
               
                v.setIdCellule(rs.getString("idCellule"));
                v.setDispo(rs.getBoolean("dispo"));
        
               
                list.add(v);
               //System.out.println(v);
                //System.err.println(rs.getString("prenom"));
                
                
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;   
    }
    
    
     private Connection cnn;

    private final String URL ="jdbc:mysql://localhost:3306/happy?user=root&password=Pass&useUnicode=true&characterEncoding=UTF-8";
    private final String LOGIN = "root";
    private final String PASSWORD = "";

    private Connection getConnection() {
        try {
            cnn = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            System.out.println("Conncting !");
            return cnn;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }
    
    
    public ObservableList<Cellule> getCelluleListe() throws SQLException{
        ObservableList<Cellule> CelluleListe = FXCollections.observableArrayList();
       Connection conn = getConnection(); 
       String query = "Select * from cellule" ;
       Statement st ;
       ResultSet rs ;
       
       try {
           st= conn.createStatement();
           rs= st.executeQuery(query);
           Cellule cellule ;
           while (rs.next()){
               cellule = new Cellule(rs.getString("idCellule") , rs.getBoolean("dispo"));
           }
       }
       catch (Exception ex)
       {
           ex.printStackTrace();
       }
       
        return CelluleListe ;
    }
    
     public ObservableList<Cellule> rechercherPardispo(String d) throws SQLException {
         ObservableList<Cellule> list = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM cellule where dispo ='" + d + "'";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                
               Cellule v = new Cellule();
               
                v.setIdCellule(rs.getString("idCellule"));
                v.setDispo(rs.getBoolean("dispo"));
               
                list.add(v);
               //System.out.println(v);
                //System.err.println(rs.getString("prenom"));
                
                
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;   

    }
     
     
     public ObservableList<Cellule> getlisteCellule() {
     ObservableList<Cellule> list = FXCollections.observableArrayList();
     String s="";
        String sql = "select * from cellule where dispo= 0 ";
     Connection cnx = MaConnexion.getInstance().getConnection();
   
        try {
         Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
           
            rs.next();
       
            while (rs.next()) {
                list.add(new Cellule(rs.getString("idCellule"), rs.getBoolean("dispo")));
            }
           
         
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReservation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
       

    }
   
    
    

}

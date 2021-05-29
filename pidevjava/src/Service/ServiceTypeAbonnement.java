/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.TypeAbonnement;
import Services.IServiceTypeAbonnement;
import Utils.MaConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zeineb
 */
public class ServiceTypeAbonnement implements IServiceTypeAbonnement {
    
    Connection cnx;
    public List<TypeAbonnement> AfficherTypeAbonnement;

    public ServiceTypeAbonnement() {
        cnx=MaConnexion.getInstance().getConnection();
    } 

    @Override
    public void AddTypeAbonnement(TypeAbonnement ta) {

try { 
    Statement stm=cnx.createStatement();
    String query="INSERT INTO `type_abonnement`( `type`, `description`, `prix`, `image`, `placesdispo`) "
            + "VALUES ('" +ta.getType()+  "','"+ta.getDescription()+   "','"
          +ta.getPrix()+  "', '" +ta.getImage()+ "','"+ta.getPlacesdispo()+  "')";
    stm.executeUpdate(query);
    } catch (SQLException ex) {
        Logger.getLogger(ServiceAbonne.class.getName()).log(Level.SEVERE, null, ex);
    }
       

    }
    
       @Override
    public List<TypeAbonnement> AfficherTypeAbonnement()throws SQLException {
   

        Statement  stm = cnx.createStatement();
              
        
        String query="select * from  type_abonnement order by id desc";


       ResultSet rst=stm.executeQuery(query);
       List<TypeAbonnement>TypeAbonnement = new ArrayList<>();
        while(rst.next())
        {
            TypeAbonnement ta=new TypeAbonnement();
            ta.setId(rst.getInt("id"));

            ta.setType(rst.getString("type"));
            ta.setDescription(rst.getString("description"));
            ta.setPrix(rst.getFloat("prix"));
            ta.setImage(rst.getString("image"));
            ta.setPlacesdispo(rst.getInt("placesdispo"));
     
            TypeAbonnement.add(ta); 
            System.out.println("-------------------------------------"+TypeAbonnement);
     
        }
       
        
    
    
    return TypeAbonnement; 
    
    
    
       
         
    }   
    
    
    @Override
    public void modifierTypeAbonnement(TypeAbonnement ta, int id) {

        try {

            String requete = "UPDATE  type_abonnement set type='" + ta.getType() + "' , description='" + ta.getDescription() 
                         + "', prix='"+ ta.getPrix() +"',image='"+ ta.getImage()+  "', placesdispo='" +ta.getPlacesdispo()+ " ' WHERE id='" + id + "' ";
                    Statement  stm = cnx.createStatement();

            stm.executeUpdate(requete);
            System.out.println("Le type d'abonnement a été modifié");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
    
    
    
    
    /*
    @Override
    public void DeleteTypeAbonnement(int id) {

        String query = "DELETE FROM type_abonnement WHERE id =?";
        try {
            PreparedStatement statement = cnx.prepareStatement(query);       
            statement.setInt(1,id);

            int rowsDeleted = statement.executeUpdate(query);
            if (rowsDeleted > 0) {
                System.out.println("Type d'abonnement supprimé");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceTypeAbonnement.class.getName()).log(Level.SEVERE, null, ex);
        }
    } */

    @Override
    public int places_dispo(int id) {
int nb=0;
        try {
            String requete = "select COALESCE(sum(a.valide),0) result from abonnement a " +
"where a.typeabonnement_id="+id+"";
   
        Statement st3 = cnx.createStatement();
            ResultSet rs = st3.executeQuery(requete);
            
            while (rs.next()) {
               nb=rs.getInt("result");
                 
            }
        } catch (SQLException ex) {
            Logger.getLogger(TypeAbonnement.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return nb;    }

    @Override
    public TypeAbonnement gettypeabo(int idtypeabo) {

   TypeAbonnement ta=new TypeAbonnement();
        try {
            String requete = "select type,placesdispo,prix,image from type_abonnement ta " +
"where ta.id="+idtypeabo+"";
                   // String requete = "SELECT count(*)  rendez_vous r where r.veterinaire_id="+id+" and r.demande BETWEEN f.last_login AND '"+LocalDateTime.now()+"' ";
   
        Statement st3 = cnx.createStatement();
            ResultSet rs = st3.executeQuery(requete);
            
            while (rs.next()) {
               ta.setType(rs.getString("type"));
               ta.setPlacesdispo(rs.getInt("placesdispo"));
               ta.setPrix(rs.getFloat("prix"));
               ta.setImage(rs.getString("image"));
                 
            }
        } catch (SQLException ex) {
            Logger.getLogger(TypeAbonnement.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ta;  


    }

    
   
        
    
    
    
    

    
}

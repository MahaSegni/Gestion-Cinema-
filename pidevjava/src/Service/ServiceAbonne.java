/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Abonne;
import Services.IServiceAbonne;
import Utils.MaConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zeineb
 */
public class ServiceAbonne implements IServiceAbonne {

    Connection cnx;

    public ServiceAbonne() {
        cnx = MaConnexion.getInstance().getConnection();
    }
    @Override
    public void AddAbonne(Abonne a) {
        try {
            Statement stm = cnx.createStatement();
            String query = "INSERT INTO `abonne`( `nomabonne`, `prenomabonne`, `mailabonne`, `datenaissabonne`, `telephoneabonne`, `password`,`roles`) VALUES ('" + a.getNomabonne() + "','" + a.getPrenomabonne() + "','"
                    + a.getMailabonne() + "', '" + a.getDatenaissabonne() + "','" + a.getTelephoneabonne() + "','" + Password.hashPassword(a.getPassword()) + "','abonne')";
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAbonne.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /*@Override
     public void ModifierAbonne(Abonne a) throws SQLException
    { 
          PreparedStatement pst = cnx.prepareStatement(" update abonne set nomabonne = ? where nomabonne=?");
                  
           
           pst.executeUpdate();
    }
    
    
@Override
    public void DeleteAbonne(Abonne a) throws SQLException{
        String query="Delete from abonne where nomabonne=?";
        PreparedStatement pst=cnx.prepareStatement(query);
       
        pst.executeUpdate();
        
    } */
    @Override
    public List<Abonne> AfficherAbonne() throws SQLException {

        Statement stm = cnx.createStatement();

        String query = "select * from  abonne ";

        ResultSet rst = stm.executeQuery(query);
        List<Abonne> abonnes = new ArrayList<>();
        while (rst.next()) {
            Abonne a = new Abonne();
            a.setId(rst.getInt("id"));

            a.setNomabonne(rst.getString("nomabonne"));
            a.setPrenomabonne(rst.getString("Prenomabonne"));
            a.setMailabonne(rst.getString("mailabonne"));
            a.setDatenaissabonne(rst.getDate("datenaissabonne"));
            a.setTelephoneabonne(rst.getInt("telephoneabonne"));
            a.setPassword(rst.getString("password"));
            abonnes.add(a);

        }

        return abonnes;

    }

    @Override
    public boolean email_existant(String mail) {
        boolean result = false;
        try {
            for (Abonne p : AfficherAbonne()) {
                if (p.getMailabonne().equals(mail)) {
                    result = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAbonne.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (result);
    }

    @Override
    public Abonne login(String mailabo) throws SQLException {

        String sql = "select * from abonne where mailabonne='" + mailabo + "'";

        PreparedStatement stm = cnx.prepareStatement(sql);
        ResultSet result = stm.executeQuery(sql);

        Abonne abonne = new Abonne();
        while (result.next()) {
            abonne.setId(result.getInt("id"));
            abonne.setNomabonne(result.getString("nomabonne"));

            abonne.setPrenomabonne(result.getString("prenomabonne"));

            abonne.setMailabonne(result.getString("mailabonne"));
            abonne.setPassword(result.getString("password"));
            abonne.setRoles(result.getString("roles"));
            abonne.setStripe_id(result.getString("stripeid"));
        }

        return abonne;

    }

    @Override
    public Abonne get_abonne(int id_abo) {

    
        Abonne a=new Abonne();
        try {
            String requete = "select nomabonne,prenomabonne from abonne a where a.id="+id_abo;
                   // String requete = "SELECT count(*)  rendez_vous r where r.veterinaire_id="+id+" and r.demande BETWEEN f.last_login AND '"+LocalDateTime.now()+"' ";
   
        Statement st3 = cnx.createStatement();
            ResultSet rs = st3.executeQuery(requete);
            
            while (rs.next()) {
               a.setNomabonne(rs.getString("nomabonne"));
               a.setPrenomabonne(rs.getString("prenomabonne"));
               
                              }
        } catch (SQLException ex) {
            Logger.getLogger(Abonne.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return a;  

    }

    @Override
    public void ajouterstripeid(String stripeid,int idabo) {
 try {

            String requete = "UPDATE  abonne set stripeid='" + stripeid + "'  WHERE id='" + idabo + "' ";
                    Statement  stm = cnx.createStatement();

            stm.executeUpdate(requete);
            System.out.println("Le type d'abonnement a été modifié");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
public int getIdAbonne(String c) {
     
        String sql = "select id from abonne where mailabonne =" + "'" + c + "'";
 
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            rs.next();
            int id = rs.getInt(1);
            return id;
         
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
       

    }
public String getNomEvent(int id){
        String nom="vide";
        try {
            PreparedStatement pt = cnx.prepareStatement("select nomEvent from user where id = '"+id+"'");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                 nom=rs.getString("nomEvent");
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAbonne.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nom;
    }
}

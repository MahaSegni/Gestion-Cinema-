/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Abonnement;
import Entities.TypeAbonnement;
import Services.IServiceAbonnement;
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
public class ServiceAbonnement implements IServiceAbonnement {

    Connection cnx;

    public ServiceAbonnement() {

        cnx = MaConnexion.getInstance().getConnection();

    }

    @Override
    public void ajoutAbonnement(Abonnement a) {

        try {
            String sql = "Insert into abonnement(abonne_id,typeabonnement_id,datedebut,datefin,valide,datedemande)"
                    + " values ('" + a.getAbonne() + "','" + a.getTypeabonnement() + "','" + a.getDatedebut() + "'"
                    + ",'" + a.getDatefin() + "',0,'" + a.getDatedemande() + "')";

            Statement st = cnx.createStatement();
            st.executeUpdate(sql);

            System.out.println("---------000-------------------------------------");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAbonnement.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<Abonnement> MesAbonnements(int id) {
        List<Abonnement> abonnements = new ArrayList<>();

        try {
            Statement stm = cnx.createStatement();

            String query = "select * from  abonnement where abonne_id=" + id+" order by id desc";

            ResultSet rst = stm.executeQuery(query);
            while (rst.next()) {
                Abonnement a = new Abonnement();
                a.setId(rst.getInt("id"));

                a.setAbonne(rst.getInt("abonne_id"));
                a.setTypeabonnement(rst.getInt("typeabonnement_id"));
                a.setDatedebut(rst.getDate("datedebut"));
                a.setDatefin(rst.getDate("datefin"));
                a.setValide(rst.getBoolean("valide"));
                a.setDatedemande(rst.getDate("datedemande"));

                abonnements.add(a);

            }

            return abonnements;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAbonnement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return abonnements;

    }
    
        @Override
    public List<Abonnement> getabo_last2days() {
        List<Abonnement> abonnements = new ArrayList<>();

        try {
            Statement stm = cnx.createStatement();

            String query = "select * from  abonnement where DATEDIFF(DATE(NOW()),datedemande)<=2 order by id desc";

            ResultSet rst = stm.executeQuery(query);
            while (rst.next()) {
                Abonnement a = new Abonnement();
                a.setId(rst.getInt("id"));

                a.setAbonne(rst.getInt("abonne_id"));
                a.setTypeabonnement(rst.getInt("typeabonnement_id"));
                a.setDatedebut(rst.getDate("datedebut"));
                a.setDatefin(rst.getDate("datefin"));
                a.setValide(rst.getBoolean("valide"));
                a.setDatedemande(rst.getDate("datedemande"));

                abonnements.add(a);

            }

            return abonnements;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAbonnement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return abonnements;

    }

    @Override
    public void delete_abonnement(int id) {

        String query = "DELETE FROM abonnement WHERE id="+id;
        try {
            PreparedStatement statement = cnx.prepareStatement(query);       
            //statement.setInt(1,id);

            int rowsDeleted = statement.executeUpdate(query);
            if (rowsDeleted > 0) {
                System.out.println("abonnement supprimé");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceTypeAbonnement.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

    
    @Override
    public List<Abonnement> getAboByNom(String nom) {
        List<Abonnement> abonnements = new ArrayList<>();

        try {
            Statement stm = cnx.createStatement();

            String query = "select * from "
                    + " abonnement a join abonne b on b.id=a.abonne_id "
                    + "where  DATEDIFF(DATE(NOW()),a.datedemande)<=2"
                    + " AND b.nomabonne='"+nom+"' order by a.id desc ";

            ResultSet rst = stm.executeQuery(query);
            while (rst.next()) {
                Abonnement a = new Abonnement();
                a.setId(rst.getInt("id"));

                a.setAbonne(rst.getInt("a.abonne_id"));
                a.setTypeabonnement(rst.getInt("a.typeabonnement_id"));
                a.setDatedebut(rst.getDate("a.datedebut"));
                a.setDatefin(rst.getDate("a.datefin"));
                a.setValide(rst.getBoolean("a.valide"));
                a.setDatedemande(rst.getDate("a.datedemande"));

                abonnements.add(a);

            }

            return abonnements;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAbonnement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return abonnements;

    }

    @Override
    public void updateState(int id) {
   try {

            String requete = "UPDATE  abonnement set valide=1,datedebut=DATE(NOW()),"
                    + "datefin=DATE_ADD(NOW(), INTERVAL 1 MONTH) WHERE id='" + id + "' ";
                    Statement  stm = cnx.createStatement();

            stm.executeUpdate(requete);
            System.out.println("L'etat a été modifié");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    
    @Override
    public void ajoutAbonnement_enligne(Abonnement a) {

        try {
            String sql = "Insert into abonnement(abonne_id,typeabonnement_id,datedebut,datefin,valide,datedemande)"
                    + " values ('" + a.getAbonne() + "','" + a.getTypeabonnement() + "',DATE(NOW())"
                    + ""
                    + ",DATE_ADD(NOW(), INTERVAL 1 MONTH),1,DATE(NOW()))";

            Statement st = cnx.createStatement();
            st.executeUpdate(sql);

            System.out.println("---------000-------------------------------------");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAbonnement.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

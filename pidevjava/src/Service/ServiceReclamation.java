/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;


import Entities.Abonne;
import java.sql.Connection;
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
import Entities.Equipement;
import Entities.Reclamation;
import GUI.AcceuilAbonneController;
import GUI.MesAbonnementsController;
import  Utils.MaConnexion;
import com.stripe.model.Review.Session;

/**
 *
 * @author dell
 */
public class ServiceReclamation implements IService<Reclamation>{
      
     Connection cnx = MaConnexion.getInstance().getConnection();
    @Override
    public void ajouter(Reclamation t) {
         try {
             MesAbonnementsController A = new MesAbonnementsController();
              int mail=A.mailAB();
            String requete = "INSERT INTO reclamation (description,field,rate,abonne_id,datee) VALUES (?,?,?,"+Abonne.abonne.getId()+",?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getDescription());
            pst.setString(2, t.getField());
            pst.setDouble(3, t.getRate());
            
            pst.setTimestamp(4, t.getDatee()); 
            // System.out.println(t.getDescription()+"/"+t.getField()+"/"+t.getRate()+"/"+t.getAbonne_id()+"/"+t.getDatee()+"/");
            pst.executeUpdate();
            System.out.println("Reclamation ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
       }

   
    public void supprimer(Reclamation t) {
          try {
            String requete = "DELETE FROM reclamation WHERE field=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getField());
            pst.executeUpdate();
            System.out.println("Reclamation supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }      
    }

    
    public void modifier(Reclamation t) {
     try {
            String requete = "UPDATE reclamation SET idrec=?, field=?, description=?, datee=?, rate=? WHERE idrec=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(2, t.getField());
            pst.setInt(1, t.getIdrec());
            pst.setString(3, t.getDescription());
            pst.setTimestamp(4, t.getDatee());
            pst.setDouble(5, t.getRate());
            pst.setString(6, t.getField());
            pst.executeUpdate();
            System.out.println(" Reclamation Modifiée! ");
        } catch (SQLException ex) {
            System.out.println(" erreur de modification! ");
            System.err.println(ex.getMessage());
        }    
    }

    @Override
    public ObservableList<Reclamation> afficher(){
   ObservableList<Reclamation> list = FXCollections.observableArrayList();

        try {
            String requete = "SELECT r.idrec, r.description, r.field, r.datee, r.rate, r.abonne_id FROM reclamation r, abonne a where r.abonne_id = a.id";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                
               Reclamation v = new Reclamation();
               
                v.setIdrec(rs.getInt("idrec"));
                v.setDatee(rs.getTimestamp("datee"));
                v.setDescription(rs.getString("description"));
                v.setField(rs.getString("field"));
                v.setRate(rs.getDouble("rate"));
                v.setAbonne_id(rs.getInt("abonne_id"));

                list.add(v);
               //System.out.println(v);
                //System.err.println(rs.getString("prenom"));
                
                
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;   
    }
    
     public List<Reclamation> rechercherParNom(String nom) throws SQLException{
        List<Reclamation> list = new ArrayList<>();

        try {

            String requete = "SELECT * FROM reclamation where field='" + nom + "'";

            Statement pst = cnx.createStatement();
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                 Reclamation v = new Reclamation();
               v.setIdrec(rs.getInt("idrec"));
                v.setDatee(rs.getTimestamp("datee"));
                v.setDescription(rs.getString("description"));
                v.setField(rs.getString("field"));
                v.setRate(rs.getDouble("rate"));
                v.setAbonne_id(rs.getInt("abonne_id"));
                list.add(v);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;

    }
      
       public ObservableList rechercher(String charac) {
        String requete = "select * from reclamation where  field LIKE '%"+charac+"%' or rate LIKE '%"+charac+"%' or datee LIKE '%"+charac+"%'";
        ObservableList<Reclamation> list = FXCollections.observableArrayList();
        try {
            Statement stm = cnx.createStatement();
            ResultSet rst = stm.executeQuery(requete);
            while (rst.next()) {
                Reclamation result = new Reclamation();
                result.setAbonne_id(rst.getInt("abonne_id"));
                result.setDescription(rst.getString("description"));
                result.setField(rst.getString("field"));
                result.setRate(rst.getDouble("rate"));
                result.setDatee(rst.getTimestamp("datee"));
                list.add(result);
            }
        } catch (SQLException ex) {
            System.out.println("aucune Equipement disponible!");
        }
        return list;
    }
       
        public ObservableList<Reclamation> TrierparDate(){
     ObservableList<Reclamation> list = FXCollections.observableArrayList();

        try {
            String requete = "SELECT r.idrec, a.mailabonne, r.description, r.field, r.datee, r.rate, r.abonne_id FROM reclamation r, abonne a where r.abonne_id = a.id ORDER BY r.datee DESC";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                
               Reclamation v = new Reclamation();
               
                v.setIdrec(rs.getInt("idrec"));
                v.setDatee(rs.getTimestamp("datee"));
                v.setDescription(rs.getString("description"));
                v.setField(rs.getString("field"));
                v.setRate(rs.getDouble("rate"));
                v.setAbonne_id(rs.getInt("abonne_id"));
                //System.err.println("L'abonne du reclamation relative"+rs.getString("prenom"));
                list.add(v);
               //System.out.println(v);
                //System.err.println(rs.getString("prenom"));
                
                
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;   
    }
     public List<Reclamation> Trierparrate(){
      List<Reclamation> list = new ArrayList<>();

        try {
            String requete = "SELECT r.idrec, a.prenom, r.description, r.field, r.datee, r.rate, r.abonne_id FROM reclamation r, abonne a where r.abonne_id = a.id ORDER BY r.rate DESC";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                
               Reclamation v = new Reclamation();
               
                v.setIdrec(rs.getInt("idrec"));
                v.setDatee(rs.getTimestamp("datee"));
                v.setDescription(rs.getString("description"));
                v.setField(rs.getString("field"));
                v.setRate(rs.getDouble("rate"));
                v.setAbonne_id(rs.getInt("abonne_id"));
                System.err.println(rs.getString("prenom"));
                list.add(v);
               //System.out.println(v);
                //System.err.println(rs.getString("prenom"));
                
                
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;   
    }
       public int getNbrrecpardate(String charac) {
        String sql="SELECT COUNT(*) FROM reclamation  where  datee LIKE '%"+charac+"%' ";
        ResultSet rs;
        int countIdRec=0;
        try {
            PreparedStatement st= cnx.prepareStatement(sql);
			ResultSet res= st.executeQuery(); 
                        while(res.next()) {
                           countIdRec= res.getInt(1);
                           
                        }
                        
                        System.out.println("Nombre des reclamations par Date:"+countIdRec);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return countIdRec;
    }
      public int getNbrReclamationTotal() {
        String sql="SELECT COUNT(*) FROM reclamation ";
        ResultSet rs;
        int countIdRec=0;
        try {
            PreparedStatement st= cnx.prepareStatement(sql);
			ResultSet res= st.executeQuery(); 
                        while(res.next()) {
                           countIdRec= res.getInt(1);
                           
                        }
                        
                        System.out.println("Nombre des reclamations:"+countIdRec);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return countIdRec;
    }
 
    /* public int getcategoid(String value) throws SQLException{
    int result =0;
    String request ="select id from abonne where prenom = '" +value +"'";
    try{
    Statement statement = cnx.createStatement();
    ResultSet rs = statement.executeQuery(request);
    while (rs.next()){
    result = rs.getInt("id");
    }    
    }catch (SQLException troubles){
    troubles.printStackTrace();
    }
     return result;    
    }*/
     public int getcategoid(String c) {
     
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
//kifeh fil base haaka nn mhch aamla hakeka ahyaa 
}

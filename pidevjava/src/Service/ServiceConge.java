/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Conge;
import Entities.Employe;
import Interface.IService;
import java.sql.*;
import Utils.MaConnexion;
import ds.desktop.notify.DesktopNotify;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author HP
 */
public class ServiceConge implements IService<Conge> {

    private Connection con = MaConnexion.getInstance().getConnection();
    private Statement ste;
    PreparedStatement pst;

    /* public ServiceConge() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }*/
    public void ajouter(Conge c) throws SQLException {
        ServiceConge CR = new ServiceConge();

        String query = "INSERT INTO `conge`(`dateconge`, `motifconge`, `nbjourconge`, `employe_id`)" + " VALUES (?,?,?,?)";
        pst = con.prepareStatement(query);
//ste = con.createStatement();
        // pst.setInt(1, c.getIdconge());

        pst.setDate(1, new java.sql.Date(c.getDateconge().getTime()));
        pst.setString(2, c.getMotifconge());
        pst.setInt(3, c.getNbjourconge());
        pst.setInt(4, c.getEmploye_id());
        pst.executeUpdate();
        System.out.println("conge ajouté");
//        DesktopNotify.showDesktopMessage(
//                "success !",
//                "conge added successfully!",
//                DesktopNotify.SUCCESS);
    }

    public ArrayList<Conge> afficher() {
        ArrayList<Conge> myList = new ArrayList<>();
        try {
            String query = "Select c.idconge, c.dateconge, c.motifconge, e.idemp, c.nbjourconge from conge c,employe e where c.employe_id=e.idemp";
            //String query = "Select c.num, c.nbr, c.date,f.id_film ,f.nomfilm, c.heurep from cinema c ,film f where c.film = f.id_film";
            ste = con.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()) {
                Conge c = new Conge();
                c.setIdconge(rs.getInt("idconge"));;
                c.setDateconge(rs.getDate("dateconge"));
                c.setMotifconge(rs.getString("motifconge"));
                c.setNbjourconge(rs.getInt("nbjourconge"));
                //c.setEmploye_id(rs.getInt("employe_id"));
                c.setEmploye_id(rs.getInt("e.idemp"));
                //System.out.println(rs.getInt("idemp"));
                myList.add(c);
                System.out.println(c);
                //System.out.println("affichage fait");
                //System.out.println(rs.getInt("idemp"));

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    public ArrayList<Conge> trierpardate() {
        ArrayList<Conge> myList = new ArrayList<>();
        try {
            String query = "Select c.idconge, c.dateconge, c.motifconge, e.idemp, c.nbjourconge from conge c,employe e where c.employe_id=e.idemp order by dateconge DESC";
            //String query = "Select c.num, c.nbr, c.date,f.id_film ,f.nomfilm, c.heurep from cinema c ,film f where c.film = f.id_film";
            ste = con.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()) {
                Conge c = new Conge();
                c.setIdconge(rs.getInt("idconge"));;
                c.setDateconge(rs.getDate("dateconge"));
                c.setMotifconge(rs.getString("motifconge"));
                c.setNbjourconge(rs.getInt("nbjourconge"));
                //c.setEmploye_id(rs.getInt("employe_id"));
                c.setEmploye_id(rs.getInt("e.idemp"));
                //System.out.println(rs.getInt("idemp"));
                myList.add(c);
                System.out.println(c);
                //System.out.println("affichage fait");
                //System.out.println(rs.getInt("idemp"));

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    @Override
    public void modifier(Conge c, int id) {
        String req = "update conge set dateconge=?, motifconge=?,nbjourconge=?,employe_id=? where idconge=? ";

        try {
            pst = con.prepareStatement(req);
            pst.setDate(1, new java.sql.Date(c.getDateconge().getTime()));
            pst.setString(2, c.getMotifconge());
            pst.setInt(3, c.getNbjourconge());
            pst.setInt(4, c.getEmploye_id());
            pst.setInt(5, id);
            pst.executeUpdate();
            System.out.println("conge modifié");

//            DesktopNotify.showDesktopMessage(
//                    "success !",
//                    "conge updated successfully!",
//                    DesktopNotify.SUCCESS);

        } catch (SQLException ex) {
            System.out.println("erreur");
        }

    }

    @Override
    public void supprimer(int id) {
        String req = "delete from conge where idconge=? ";

        try {
            pst = con.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("conge supprimé");
//            DesktopNotify.showDesktopMessage(
//                    "success !",
//                    "conge deleted successfully!",
//                    DesktopNotify.SUCCESS);

        } catch (SQLException ex) {
            System.out.println("erreur");
        }
    }

//   
//    public int getIdfi(String c) { 
//     
//        String sql = "select employe_id from conge where idemp=" + "'" + c + "'";
//        try {
//            ResultSet rs = ste.executeQuery(sql);
//            rs.next();
//            int id = rs.getInt(1);
//            return id;
//         
//        } catch (SQLException ex) {
//            Logger.getLogger(ServiceFilm.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return 0;
//                
//       
//
//    }
//   
//   public ObservableList<Conge> getAllConges(){
//        ObservableList<Conge> conges = FXCollections.observableArrayList();
//        try{
//            String request="SELECT idemp FROM employe";
//            Statement s=con.createStatement();
//            ResultSet result=s.executeQuery(request);
//            while(result.next()){
//                Conge c=new Conge();
//                c.setEmploye_id(result.getInt("idemp"));
////                c.setUserId(result.getInt("c.user_id"));
////                c.setHireDate(result.getTimestamp(6).toLocalDateTime());
////                c.setEtat(result.getString("c.etat"));
////                c.setNom(result.getString("u.username"));
////                c.setEmail(result.getString("u.email"));
////                c.setRace(result.getString("c.race"));
//               
//                //c.setExperienceYears(result.getInt("c.experience"));
//                
//                conges.add(c);
//            }
//        } catch (SQLException ex){
//            System.out.println(ex);
//        }
//        return conges;
//    }
//   
    public void updatetab2(Conge ec) throws SQLException {
        //UPDATE `conge` SET `idconge`='[value-1]',`dateconge`='[value-2]',`motifconge`='[value-3]',`nbjourconge`='[value-4]',`employe_id`='[value-5]' WHERE 1
        try {
//        PreparedStatement PS=con.prepareStatement("UPDATE `conge` SET `dateconge`=?,`motifconge`=?,`nbjourconge`=? WHERE `idconge`=?");
            PreparedStatement PS2 = con.prepareStatement("UPDATE conge SET `dateconge`=?,`motifconge`=?,`nbjourconge`=? WHERE `idconge`=?");
            PS2.setDate(1, new java.sql.Date(ec.getDateconge().getTime()));
            PS2.setDate(1, new java.sql.Date(ec.getDateconge().getTime()));
            PS2.setString(2, ec.getMotifconge());
            //PS.setString(2,a.getPrenomemp());
            //PS.setString(2, a.getDescription());
            PS2.setInt(3, ec.getNbjourconge());
            //PS.setString(4,a.getAdresseemp());                   
            PS2.setInt(4, ec.getIdconge());
            PS2.executeUpdate();
            

        } catch (Exception e) {
            Logger.getLogger(MaConnexion.class.getName()).log(Level.SEVERE, null, e);

        }

    }
    
    
    
     
//        public ObservableList<Conge> recherchecongeByIdc(int id){
//               ObservableList<Conge> liste = FXCollections.observableArrayList();
//        String requete = "Select c.idconge, c.dateconge, c.motifconge, e.idemp, c.nbjourconge from conge c,employe e where c.employe_id=e.idemp and idconge="+(char)34+id+(char)34;
//      // String requete = "SELECT * FROM conge WHERE idconge="+(char)34+id+(char)34;
//        try {
//             ste = con.createStatement();
//            ResultSet rs = ste.executeQuery(requete);
//            while(rs.next()) {
//                Conge c = new Conge();
//           
//                c.setIdconge(rs.getInt("idconge"));;
//                c.setDateconge(rs.getDate("dateconge"));
//                c.setMotifconge(rs.getString("motifconge"));
//                c.setNbjourconge(rs.getInt("nbjourconge"));
//                c.setEmploye_id(rs.getInt("idemp"));;
//                liste.add(c);
//                 System.out.println(liste);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(MaConnexion.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return liste;
//       
//    } 
    
    
    
    
         
          public int recuperer_id(Conge v) throws SQLException {

        ArrayList<Conge> v1 = new ArrayList<>();
        ServiceConge SC = new ServiceConge();
        v1 = (ArrayList<Conge>) SC.afficher() ;
       // System.out.println("size " + v1.size());
        int id = 0;
        //System.out.println("size: " + voyages.size());
        for (int i = 0; i < v1.size(); i++)
        {
            if (v1.get(i).equals(v))
            {
                id = v1.get(i).getEmploye_id();
                break;
            }
        }
        return id;
    }

}

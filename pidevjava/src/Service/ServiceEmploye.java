/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Conge;
import Entities.Employe;
import Entities.EmployeAffi;
import Interface.IService;
import java.sql.*;
import Utils.MaConnexion;
import Utils.PDF;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import ds.desktop.notify.DesktopNotify;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javax.management.Notification;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;





/**
 *
 * @author HP
 */
public class ServiceEmploye implements IService<Employe> {

    private Connection con = MaConnexion.getInstance().getConnection();
    private Statement ste;
     PreparedStatement pst;
     
     
     
      
     
    /*public ServiceEmploye() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }*/
     
      @Override
     public ArrayList<Employe> afficher() {
        ArrayList<Employe> myList = new ArrayList<>();
        try {
            
            //Select c.idconge, c.dateconge, c.motifconge, e.idemp, c.nbjourconge from conge c,employe e where c.employe_id=e.idemp
            String query = "Select * from employe";
                   // String query = "Select e.idemp, e.nomemp, e.prenomemp, e.numtelemp,c.idconge, e.adresseemp from employe e, conge c  ";
            ste = con.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()) {
                Employe c = new Employe();
                c.setIdemp(rs.getInt("idemp"));;
                c.setNomemp(rs.getString("nomemp"));
                c.setPrenomemp(rs.getString("prenomemp"));
                c.setNumtelemp(rs.getInt("numtelemp"));
                c.setAdresseemp(rs.getString("adresseemp"));
                //c.setIdconge(rs.getInt("idconge")); ;
                myList.add(c);
                System.out.println(c);
                //System.out.println("affichage fait");
                
               
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

       @Override
    public void modifier(Employe e, int id) {
        String req = "update employe set nomemp=?, prenomemp=?,numtelemp=?,adresseemp=?,idconge=? where idemp=? ";

        try {
            pst = con.prepareStatement(req);
            pst.setString(1, e.getNomemp());
            pst.setString(2, e.getPrenomemp());
            pst.setInt(3, e.getNumtelemp());
            pst.setString(4, e.getAdresseemp());
            pst.setInt(5, id);
            
            pst.executeUpdate();
            System.out.println("employe modifié");
//             DesktopNotify.showDesktopMessage(
//    "success !",
//    "employe updated successfully!",
//    DesktopNotify.SUCCESS);
            
        } catch (SQLException ex) {
            System.out.println("erreur");
        }

    }
    @Override
     public void supprimer(int id) {
        String req = "delete from employe where idemp=? ";

        try {
            pst = con.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("employe supprimé");
//             DesktopNotify.showDesktopMessage(
//    "success !",
//    "employe deleted successfully!",
//    DesktopNotify.SUCCESS);

        } catch (SQLException ex) {
          System.out.println("erreur");
        }
      }

     
      
      
     
   /* public int recuperer_id(Employe e) throws SQLException {
        ArrayList<Employe> ee = new ArrayList<>();
           ServiceEmploye SC = new ServiceEmploye();
         ee = (ArrayList<Employe>)SC.afficher();
        
       // System.out.println("size " + v1.size());
        int id = -1;
        //System.out.println("size: " + voyages.size());
        for (int i = 0; i < ee.size(); i++)
        {
            if (ee.get(i).equals(e))
            {
                id = ee.get(i).getIdemp();
                break;
            }
        }
        return id;
    }

    public boolean employe_existe(Employe e) throws SQLException {
        ServiceEmploye CR = new ServiceEmploye();
        if (CR.recuperer_id(e) == -1) {
            return false;
        }
        return true;
    }
    
   public boolean verifier_nb(Employe e) {
        return ((e.getNumtelemp() >= 10000000));
    }
*/

   
    
     
     

    @Override
     public void ajouter(Employe e )  throws SQLException {
     
         
        /* if (!SC.employe_existe(e))
            { 
                  if (SC.verifier_nb(e) ) {*/
        ServiceFilm CR = new ServiceFilm();

        String query = "INSERT INTO `employe`( `nomemp`, `prenomemp`, `numtelemp`, `adresseemp`)" + " VALUES (?,?,?,?)";
        pst = con.prepareStatement(query);
//ste = con.createStatement();
       
        pst.setString(1, e.getNomemp());
        pst.setString(2, e.getPrenomemp());
        pst.setInt(3, e.getNumtelemp());
        pst.setString(4, e.getAdresseemp());
        pst.executeUpdate();
        System.out.println("employe ajouté");
        
//        DesktopNotify.showDesktopMessage(
//    "success !",
//    "employe added successfully!",
//    DesktopNotify.SUCCESS);
        
                  }  /*else {
                    System.out.println("Verifier vos parametre !!!");
                }
            } else {
                System.out.println("l employe existe déjà");
            }*/


   
  

     
     
     
      public ObservableList<Employe> recherchecongeById(String charac){
               ObservableList<Employe> liste = FXCollections.observableArrayList();
//        String requete = "select * from employe where  idemp LIKE `"+charac+"` or nomemp LIKE `"+charac+"` or prenomemp LIKE `"+charac+"` or adresse LIKE `"+charac+"`";

      String requete = "select * from employe where  idemp = "+(char)34+charac+(char)34+" or nomemp = "+(char)34+charac+(char)34+" or prenomemp = "+(char)34+charac+(char)34+ " or adresseemp = "+(char)34+charac+(char)34;
      // String requete = "SELECT * FROM conge WHERE idconge="+(char)34+id+(char)34;     
        try {
             ste = con.createStatement();
            ResultSet rs = ste.executeQuery(requete);
            while(rs.next()) {
                Employe c = new Employe();
           
                c.setIdemp(rs.getInt("idemp"));;
                c.setNomemp(rs.getString("nomemp"));
                c.setPrenomemp(rs.getString("prenomemp"));
                c.setNumtelemp(rs.getInt("numtelemp"));
                c.setAdresseemp(rs.getString("adresseemp"));;
                liste.add(c);
                 System.out.println(liste);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MaConnexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
       
    } 

      
          public void updatetab(Employe a) throws SQLException {
            try {
        PreparedStatement PS=con.prepareStatement("UPDATE `employe` SET `nomemp`=? ,`prenomemp`=?,`numtelemp`=?,`adresseemp`=? WHERE `idemp`=?");
        PS.setString(1,a.getNomemp());
        PS.setString(1,a.getNomemp());
        PS.setString(2,a.getPrenomemp());
        //PS.setString(2, a.getDescription());
        PS.setInt(3,a.getNumtelemp());                   
        PS.setString(4,a.getAdresseemp());                   
        PS.setInt(5,a.getIdemp());                   
        PS.executeUpdate();
     
            } catch (Exception e) {
                Logger.getLogger(MaConnexion.class.getName()).log(Level.SEVERE,null,e);
            }

    }
      
      
          
          
          
//          
//          public int recuperer_id(Conge v) throws SQLException {
//
//        ArrayList<Conge> v1 = new ArrayList<>();
//        ServiceConge SC = new ServiceConge();
//        v1 = (ArrayList<Conge>) SC.afficher() ;
//       // System.out.println("size " + v1.size());
//        int id = 0;
//        //System.out.println("size: " + voyages.size());
//        for (int i = 0; i < v1.size(); i++)
//        {
//            if (v1.get(i).equals(v))
//            {
//                id = v1.get(i).getEmploye_id();
//                break;
//            }
//        }
//        return id;
//    }

//    public boolean conge_existe(Conge v) throws SQLException {
//        ServiceConge CR = new ServiceConge();
//        if (CR.recuperer_id(v) == 0) {
//            return false;
//        }
//        return true;
//    }
//          
          
          
  
}
      


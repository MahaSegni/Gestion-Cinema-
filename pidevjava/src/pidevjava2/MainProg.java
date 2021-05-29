/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevjava2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import Entities.Equipement;
import Entities.Reclamation;
import Service.ServiceCinema;
import Service.ServiceEquipement;
import Service.ServiceReclamation;
import Utils.MaConnexion;

/**
 *
 * @author dell
 */
public class MainProg {
     
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, IOException{
        Connection cnx=MaConnexion.getInstance().getConnection();
         Timestamp time= new Timestamp(System.currentTimeMillis());
        ServiceReclamation sp = new ServiceReclamation();
         ServiceEquipement sp2 = new ServiceEquipement();
           ServiceCinema sp3 = new ServiceCinema();
           System.out.println(sp3.afficher());
         //Test ajout reclamation
       //sp.ajouter(new Reclamation(80,1,"laala","Autre",sqlDate,3.5));
        //Test supprimer reclamation
        //sp.supprimer(new Reclamation(89,"merci", "Parking",sqlDate,1));
        //Test modifier reclamation
      //sp.modifier(new Reclamation(71,"bonsoir", "Resto",sqlDate,5));
         //Test recherche reclamation multiple choix
     //  sp2.rechercher("2021-03-28 00:00").forEach(System.out::println);
       //Test trie par rate reclamation
        //sp.Trierparrate().forEach(System.out::println);
        //Test nombre des reclamations par date
       // sp.getNbrrecpardate("2021-04-11 15:49:05");
      //System.out.println(sp.afficher());
         
         //Test ajout equipements
        
       // sp2.ajouter(new Equipement(1,66,10,"chaleur",1.5,sqlDate));
         //Test supprimer equipements
         //String nome, int cinema_id, int resto_id, double prix, int ref, Timestamp dates
       // sp2.supprimer(new Equipement("dddd",0,0,66666.0,5,time));
          //Test modifier equipements
          //int id, int resto_id, int cinema_id, int ref, String nome, double prix, Timestamp dates
        sp2.modifier(new Equipement(13,0,0,111,"farah",222,time));
         //Test recherche equipements multiple choix
        //sp2.rechercher("2021-03-28 00:00:00").forEach(System.out::println);
       System.out.println(sp2.afficher());
         //Test trie par prix equipements
       //sp2.TrierparPrix().forEach(System.out::println);
        //Test le secteur qui achete plus des equipements
       //sp2.Equipementplusachete();
      
     
    }
    
}

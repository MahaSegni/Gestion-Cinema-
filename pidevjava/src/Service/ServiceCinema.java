/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;



import Utils.MaConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ds.desktop.notify.DesktopNotify;
import Entities.Cinema;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mahas
 */
public class ServiceCinema {
    private Connection con = MaConnexion.getInstance().getConnection();
     private Statement ste;
     PreparedStatement pst;
    
    public List<Cinema> AfficherCinema() throws SQLException {
        ArrayList<Cinema> myList = new ArrayList<>();
        try {
            String query = "Select c.num, c.nbr, c.date,f.id_film ,f.nomfilm, c.heurep from cinema c ,film f where c.film = f.id_film";
            ste = con.createStatement();
            ResultSet rs = ste.executeQuery(query);
            
            while (rs.next()) {
                Cinema v = new Cinema();
                
                v.setNum(rs.getInt("num"));
                 v.setNbr(rs.getInt("nbr"));
                v.setDate(rs.getDate("date"));
                v.setFilm(rs.getInt("id_film"));
                v.setHeurep(rs.getTime("heurep"));
              System.err.println(rs.getString("nomfilm"));   
                myList.add(v);
               
            }
   
        } catch (SQLException ex) {
            System.out.println("erreur");
        }
        return myList;
}
      public void ajouterCinema(Cinema v) throws SQLException {
    ServiceCinema SC = new ServiceCinema();
        if (!SC.Cinema_existe(v)) 
            { 
                  if (SC.verifier_nb(v) ) {

            
                    String query = "INSERT INTO `cinema`(`nbr`, `film`, `date`, `heurep`)" + " VALUES (?,?,?,?)";
                    pst = con.prepareStatement(query);
//ste = con.createStatement();
                    pst.setInt(1, v.getNbr());
                    pst.setInt(2, v.getFilm());
                    pst.setDate(3, new java.sql.Date(v.getDate().getTime()));
                    pst.setTime(4, new java.sql.Time(v.getHeurep().getTime()));
                    pst.executeUpdate();
                    System.out.println("added");
                    DesktopNotify.showDesktopMessage(
    "success !",
    "Cinema added successfully!",
    DesktopNotify.SUCCESS);
                    } else {
                    System.out.println("Verifier vos parametre !!!");
                }
            } else {
                System.out.println("le voyage existe déjà");
            }
    }
      
      public void updatetab(Cinema a) throws SQLException {
            try {
        PreparedStatement PS=con.prepareStatement("UPDATE `cinema` SET `nbr`=? ,`film`=?,`heurep`=?, `date`=?  WHERE `num`=?");
        PS.setInt(1,a.getNbr());
          PS.setInt(1,a.getNbr());
        PS.setInt(2,a.getFilm());
        PS.setTime(3,a.getHeurep());
        //PS.setString(2, a.getDescription());
        PS.setDate(4, new java.sql.Date(a.getDate().getTime()));                  
             PS.setInt(5,a.getNum());       
        PS.executeUpdate();
     
            } catch (Exception e) {
                Logger.getLogger(MaConnexion.class.getName()).log(Level.SEVERE,null,e);
            }

    }
 
    public void update(Cinema d,int id) throws SQLException {
        String req = "update cinema set nbr=?, film=?,date=?,heurep=? where num=? ";

        try {
            pst = con.prepareStatement(req);
            pst.setInt(1, d.getNbr());
            pst.setInt(2, d.getFilm());
            pst.setDate(3, new java.sql.Date(d.getDate().getTime()));
             pst.setTime(4, new java.sql.Time(d.getHeurep().getTime()));
            pst.setInt(5, id);
            pst.executeUpdate();
DesktopNotify.showDesktopMessage(
    "success !",
    "Cinema updated successfully!",
    DesktopNotify.SUCCESS);
        } catch (SQLException ex) {
            System.out.println("erreur");
        }

    }
      public boolean delete(int id) throws SQLException {
        String req = "delete from cinema where num=? ";

        try {
            pst = con.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
DesktopNotify.showDesktopMessage(
    "success !",
    "Cinema deleted successfully!",
    DesktopNotify.SUCCESS);
        } catch (SQLException ex) {
          System.out.println("erreur");
          
        }
        return false;
      }
      
       public void rechercherCinemaByDate(String charac) {

        ArrayList<Cinema> myList = new ArrayList<>();
        String query = "select * from cinema c, film f where  c.nbr LIKE '%"+charac+"%' or c.film LIKE '%"+charac+"%' or f.nomfilm LIKE '%"+charac+"%' or c.date LIKE '%"+charac+"%' ";
       
        try {
          ste = con.createStatement();
            ResultSet rs = ste.executeQuery(query);
         
            rs.next();
                
              System.out.println("numero salle : "+rs.getInt("num")+"\n nombre :"+rs.getInt("nbr")+"\n Date :"+rs.getDate("date")+"\n ID Film : "+rs.getInt("film")+"\n Heure Projection "+rs.getTime("heurep"));
   
                
           
        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche");
        }
       
    }
       
       
      public int recuperer_id(Cinema v) throws SQLException {

        ArrayList<Cinema> v1 = new ArrayList<>();
        ServiceCinema SC = new ServiceCinema();
        v1 = (ArrayList<Cinema>) SC.AfficherCinema();
       // System.out.println("size " + v1.size());
        int id = -1;
        //System.out.println("size: " + voyages.size());
        for (int i = 0; i < v1.size(); i++) 
        {
            if (v1.get(i).equals(v)) 
            {
                id = v1.get(i).getNum();
                break;
            }
        }
        return id;
    }

    public boolean Cinema_existe(Cinema v) throws SQLException {
        ServiceCinema CR = new ServiceCinema();
        if (CR.recuperer_id(v) == -1) {
            return false;
        }
        return true;
    }
    public boolean verifier_nb(Cinema c) {
        return (c.getNbr() >= 20);
    }

    public boolean verifier_date(Cinema c) {
        Date auj=new Date();
        return (auj==c.getDate() );

    }
    public List<Cinema> tri() throws SQLException {
        ArrayList<Cinema> myList = new ArrayList<>();
        try {
            String query = "Select c.num, c.nbr, c.date,f.id_film ,f.nomfilm, c.heurep from cinema c ,film f where c.film = f.id_film order by date";
            ste = con.createStatement();
            ResultSet rs = ste.executeQuery(query);
            
            while (rs.next()) {
                Cinema c = new Cinema();
                
                c.setNum(rs.getInt("num"));
                 c.setNbr(rs.getInt("nbr"));
                c.setDate(rs.getDate("date"));
                c.setFilm(rs.getInt("id_film"));
                c.setHeurep(rs.getTime("heurep"));
              
                myList.add(c);
                System.err.println(rs.getString("nomfilm"));   
               
            }
   
        } catch (SQLException ex) {
            System.out.println("erreur");
        }
        return myList;
}
     public ArrayList<Cinema> rechercher_Cinema(int id) {

        ArrayList<Cinema> myList = new ArrayList<>();
        String query = "Select * FROM `cinema` WHERE  `film`= ?";
        try {
            PreparedStatement pst = con.prepareStatement(query);
          //  pst.setDate(1, (java.sql.Date) datesortie);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Cinema v = new Cinema();
                v.setNum(rs.getInt("num"));
                 v.setNbr(rs.getInt("nbr"));
                v.setDate(rs.getDate("date"));
                v.setFilm(rs.getInt("film"));
                v.setHeurep(rs.getTime("heurep"));
                
                myList.add(v);
               
                //System.out.println(v);
                
            }
            if (myList.isEmpty()) {
                System.out.println("No data found ");
            }

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche");
        }
        if (!myList.isEmpty()) {
            return myList;
        } else {
            return null;
        }
    }
    public ObservableList<Cinema> recherchecinemaById(int id){
               ObservableList<Cinema> liste = FXCollections.observableArrayList();
        String requete = "SELECT * FROM cinema WHERE  num="+id;
      
        try { 
             ste = con.createStatement();
            ResultSet rs = ste.executeQuery(requete);
            while(rs.next()) {
                 Cinema v = new Cinema();
                v.setNum(rs.getInt("num"));
                 v.setNbr(rs.getInt("nbr"));
                v.setDate(rs.getDate("date"));
                v.setFilm(rs.getInt("film"));
                v.setHeurep(rs.getTime("heurep"));
              
                liste.add(v);
                 //System.out.println(liste);
            }
        } catch (SQLException ex) {
          System.out.println("erreur lors de la recherche");
        }
        return liste;
       
    }   
    public ObservableList<Cinema> recherchefilmById(String id){
               ObservableList<Cinema> liste = FXCollections.observableArrayList();
               
        String requete = "SELECT * FROM cinema WHERE  num LIKE '%"+id+"%' or nbr LIKE '%"+id+"%' or film LIKE '%"+id+"%'or date LIKE '%"+id+"%' or heurep LIKE '%"+id+"%'" ;
      
        try { 
             ste = con.createStatement();
            ResultSet rs = ste.executeQuery(requete);
            while(rs.next()) {
                 Cinema v = new Cinema();
                v.setNum(rs.getInt("num"));
                 v.setNbr(rs.getInt("nbr"));
                v.setDate(rs.getDate("date"));
                v.setFilm(rs.getInt("film"));
                v.setHeurep(rs.getTime("heurep"));
              
                liste.add(v);
                 //System.out.println(liste);
            }
        } catch (SQLException ex) {
          System.out.println("erreur lors de la recherche");
        }
        return liste;  
    } 
     public ArrayList<Cinema> afficher() {
           ArrayList<Cinema> list = new ArrayList<>();
          Connection cnx = MaConnexion.getInstance().getConnection();
        try {
            String requete = "SELECT * from cinema";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                
               Cinema v = new Cinema();
               v.setNum(rs.getInt("num"));
                 v.setNbr(rs.getInt("nbr"));
                v.setDate(rs.getDate("date"));
                v.setFilm(rs.getInt("film"));
                v.setHeurep(rs.getTime("heurep"));
              
                

                list.add(v);
               //System.out.println(v);
                //System.err.println(rs.getString("prenom"));
                
                
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;   
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.event;
import Utils.MaConnexion;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import doryan.windowsnotificationapi.fr.Notification;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.net.MalformedURLException;
/**
 *
 * @author mahas
 */
public class event_service {
    Connection conn = MaConnexion.getInstance().getConnection();
    Statement stmt;
    event e;
    
     public void create(String nom,Date date,Date dateFin, String emplacement, String capacite, int place) {
       
        try {
            String req = "INSERT INTO evenement (nomEvent,dateEvent,dateEventFin,emplacement,capacite,place) VALUES ('"+nom+"','"+date+"','"+dateFin+"','"+emplacement+"','"+capacite+"','"+place+"')";

            PreparedStatement st = conn.prepareStatement(req);
            st.executeUpdate();
            try {
                Notification.sendNotification("module evennement", "evennement ajouté ",TrayIcon.MessageType.INFO);
            } catch (AWTException ex) {
                Logger.getLogger(event_service.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(event_service.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(event_service.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     public List<String> affichagecombo()  {
               List<String> arr=new ArrayList<>();
        try {
            
            
            
            PreparedStatement pt = conn.prepareStatement("select nomEvent from evenement");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                String nom=rs.getString("nomEvent");
                
                arr.add(nom);
            }
        } catch (SQLException ex) {
            Logger.getLogger(event_service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }
     public void affecter(String nom)
{
        try {
            String req = "update abonne set  nomEvent=? where id=5 ";
            String req1 = "update evenement set  place=place-1 where nomEvent= ? ";
            PreparedStatement st = conn.prepareStatement(req);
            PreparedStatement st1 = conn.prepareStatement(req1);
            
            st.setString(1,nom);
            st.executeUpdate();
            
            st1.setString(1,nom);
            st1.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(event_service.class.getName()).log(Level.SEVERE, null, ex);
        }
}

public void quit(String nom)
{
        try {
            String req = "update abonne set  nomEvent=? where id=5 ";
            String req1 = "update evenement set  place=place+1 where nomEvent= ? ";
            PreparedStatement st = conn.prepareStatement(req);
            PreparedStatement st1 = conn.prepareStatement(req1);
            st.setString(1,"");
            st.executeUpdate();
            
            st1.setString(1,nom);
            st1.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(event_service.class.getName()).log(Level.SEVERE, null, ex);
        }
}
     public void supprimer_event (String nom)
    {
        
        try{
            PreparedStatement pt = conn.prepareStatement("delete from evenement where nomEvent=?");
            pt.setString(1,nom);
            pt.executeUpdate();
            try {
                Notification.sendNotification("module evennement", "evennement supprimee ",TrayIcon.MessageType.WARNING);
            } catch (AWTException ex) {
                Logger.getLogger(event_service.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(event_service.class.getName()).log(Level.SEVERE, null, ex);
            }
            } catch (SQLException ex) {

            Logger.getLogger(event_service.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
}
     public void modifie_event(Timestamp date_debut,Timestamp date_fin,String emplacement,String capacite,String nom)
    {
        try {
            PreparedStatement pt = conn.prepareStatement("update evenement set  dateEvent= ?,dateEventFin= ?, emplacement= ?,capacite= ?  where nomEvent= ? ");
            
            pt.setTimestamp(1,date_debut);
            pt.setTimestamp(2,date_fin);
            pt.setString(3,emplacement);
            pt.setString(4,capacite);
            pt.setString(5,nom);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(event_service.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
     public int getidevent(String nom)  {
         int id = 0;
        try {
            PreparedStatement pt = conn.prepareStatement("select * from evenement where nomEvent= ?");
            pt.setString(1,nom);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(event_service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
     }
     
    public List<event> stat() throws SQLException {
        List<event> list = new ArrayList<>();
        PreparedStatement pt = conn.prepareStatement("select nomEvent, capacite from evenement");
        ResultSet rs = pt.executeQuery();
        while (rs.next()) {
            event p = new event(rs.getString("nomEvent"),
                                rs.getString("capacite"));
            list.add(p);
        }

        return list;
    }
    public List<event> Filter()  {
               List<event> arr=new ArrayList<>();
        try {
            
            
            
            PreparedStatement pt = conn.prepareStatement("select nomEvent,dateEvent,dateEventFin from evenement");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                event e= new event(
                rs.getString("nomEvent"),
                rs.getString("dateEvent"),
                rs.getString("dateEventFin")
                );
                
                arr.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(event_service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }
}

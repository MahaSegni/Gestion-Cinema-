/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.event;
import Utils.MaConnexion;
import doryan.windowsnotificationapi.fr.Notification;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.net.MalformedURLException;
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
 * @author mahas
 */
public class comment_service {
    Connection conn = MaConnexion.getInstance().getConnection();
    Statement stmt;
    event e;
    
    public void create( String content,int idEvent) {
       
        try {
            String req = "INSERT INTO comment (content,idUser,idevent) VALUES ('"+content+"','5','"+idEvent+"')";

            PreparedStatement st = conn.prepareStatement(req);
            st.executeUpdate();
            try {
                Notification.sendNotification("Comment", "commentaire ajouté ",TrayIcon.MessageType.INFO);
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
    public void supprimer_comment (String content)
    {
        
        try{
            PreparedStatement pt = conn.prepareStatement("delete from comment where content=?");
            pt.setString(1,content);
            pt.executeUpdate();
            try {
                Notification.sendNotification("Comment", "commentaire supprimee ",TrayIcon.MessageType.WARNING);
            } catch (AWTException ex) {
                Logger.getLogger(event_service.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(event_service.class.getName()).log(Level.SEVERE, null, ex);
            }
            } catch (SQLException ex) {

            Logger.getLogger(event_service.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
}
    public List<String> affichageNomuser()  {
               List<String> arr=new ArrayList<>();
        try {
            PreparedStatement pt = conn.prepareStatement("select nom from user");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                String nom=rs.getString("nom");
                
                arr.add(nom);
            }
        } catch (SQLException ex) {
            Logger.getLogger(event_service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }
    public void display_rdv()
    {
       
        try {
            event e = new event();
            PreparedStatement pt = conn.prepareStatement("select * from comment");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {            
                    rs.getObject("idevent");
                    rs.getString("content");
                    rs.getObject("idUser");
            }   
        } catch (SQLException ex) {
            Logger.getLogger(comment_service.class.getName()).log(Level.SEVERE, null, ex);
        }
}
}

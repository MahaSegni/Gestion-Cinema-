/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Calendar;
import models.Cellule;
import Utils.MaConnexion;

/**
 *
 * @author Administrateur
 */
public class ServiceCalendar implements IService<Calendar> {
 Connection cnx = MaConnexion.getInstance().getConnection();
    @Override
    public void ajouter(Calendar t) {
 /*try {
            String requete = "INSERT INTO calendar (id,title,start,end,description,background_color,text_color,border_color) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.setString(2, t.getTitle());
            pst.setDate(3, t.getStart());
            pst.setDate(4, t.getEnd());
            pst.setString(5, t.getDescription());
            pst.setString(6, t.getBackground_color());
            pst.setString(7, t.getText_color());
            pst.setString(8, t.getBorder_color());
            pst.executeUpdate();
            System.out.println("rendez-vous ajouté !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }       } */

    
    try {
            String requete = "INSERT INTO calendar (id,title,start,end,description,background_color,text_color,border_color) "
                    + "VALUES ('" + t.getId() + "','" + t.getTitle() + "','" + t.getStart() + "','" + t.getEnd() + "','"
                    + t.getDescription() + "','" + t.getBackground_color() + "','"+t.getText_color()+ "','"+t.getBorder_color()+ "')";
            Statement st = cnx.createStatement(); 
            st.executeUpdate(requete);
            System.out.println("rendez-vous Ajoute !! ");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
                

    }
    
    public void supprimer(String t) {
         try {
            String requete = "DELETE FROM calendar WHERE title=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t);
            pst.executeUpdate();
            System.out.println("rendez-vous supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    @Override
    public ObservableList<Calendar> afficher(){
   ObservableList<Calendar> list = FXCollections.observableArrayList();

        try {
            String requete = "SELECT title , start , end , description FROM calendar";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                
               Calendar v = new Calendar();
               
                v.setTitle(rs.getString("title"));
                v.setStart(rs.getDate("start"));
                v.setEnd(rs.getDate("end"));
                v.setDescription(rs.getString("description"));

        
               
                list.add(v);
                
                
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;   
    }

   
    
    public void modifier(Calendar t) {
        try {
            String requete = "UPDATE calendar SET title=?,start=?,end=?,description=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getTitle());
            pst.setDate(2, t.getStart());
            pst.setDate(3, t.getEnd());
            pst.setString(4, t.getDescription());
            
            pst.executeUpdate();
            System.out.println("rendez-vous modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Avisclient;
import Utils.MaConnexion;
import ds.desktop.notify.DesktopNotify;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author mahas
 */
public class ServiceRating {
    private Connection con = MaConnexion.getInstance().getConnection();
     private Statement ste;
     PreparedStatement pst;
       public void ajouter(Avisclient v) throws SQLException {
    ServiceFilm CR = new ServiceFilm();
        
                    String query = "INSERT INTO `avisclient`(`idRclient`, `descR`, `rating`)" + " VALUES (?,?,?)";
                    pst = con.prepareStatement(query);
//ste = con.createStatement();
                    pst.setInt(1, v.getIdab());
                    pst.setString(2, v.getDescR());
                    pst.setDouble(3, v.getRating());
                    pst.executeUpdate();
                   DesktopNotify.showDesktopMessage(
    "success !",
    "Rating added successfully!",
    DesktopNotify.SUCCESS);
    }
}

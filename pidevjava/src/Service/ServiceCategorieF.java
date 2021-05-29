/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;


import Entities.CategorieF;
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
 * @author mahas
 */
public class ServiceCategorieF {
    
     private Connection con = MaConnexion.getInstance().getConnection();
     private Statement ste;
     PreparedStatement pst;
    
    public List<CategorieF> AfficherCi() throws SQLException {
        ArrayList<CategorieF> myList = new ArrayList<>();
        try {
            String query = "Select * from categorief ";
            ste = con.createStatement();
            ResultSet rs = ste.executeQuery(query);
            
            while (rs.next()) {
                CategorieF v = new CategorieF();
                
                v.setId(rs.getInt(1));
                 v.setDesc_c(rs.getString(2));
              
                //System.err.println(rs.getInt(1)+rs.getString(2));
                myList.add(v);
               
            }
   
        } catch (SQLException ex) {
            System.out.println("erreur");
        }
        return myList;
}
     public int getIdfi(String c) {
      
        String sql = "select id from categorief where desc_c=" + "'" + c + "'";
 
        try {
            ResultSet rs = ste.executeQuery(sql);
            rs.next();
            int id = rs.getInt(1);
            return id;
          
        } catch (SQLException ex) {
            Logger.getLogger(ServiceFilm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
        

    }
}

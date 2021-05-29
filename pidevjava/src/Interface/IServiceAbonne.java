/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Abonne;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Zeineb
 */
public interface IServiceAbonne {
    
    public void AddAbonne(Abonne a);
    public List<Abonne>AfficherAbonne() throws SQLException; 
     /*public void ModifierAbonne(Abonne a)throws SQLException;
     public void DeleteAbonne(Abonne a) throws SQLException;*/
public boolean email_existant(String login);
public Abonne login(String mailabo)  throws SQLException;
public Abonne get_abonne(int id_abo);
public void ajouterstripeid(String stripeid,int idabo);



}  

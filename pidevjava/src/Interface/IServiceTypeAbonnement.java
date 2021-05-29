/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.TypeAbonnement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Zeineb
 */
public interface IServiceTypeAbonnement {
    public void AddTypeAbonnement(TypeAbonnement ta);
    public List<TypeAbonnement> AfficherTypeAbonnement()throws SQLException ;  
    public void modifierTypeAbonnement(TypeAbonnement ta, int id);
    /*public void DeleteTypeAbonnement(int id);*/
    public int places_dispo(int id);
    public TypeAbonnement gettypeabo(int idtypeabo);
}

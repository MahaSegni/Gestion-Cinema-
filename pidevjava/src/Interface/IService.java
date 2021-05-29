/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;
import java.sql.SQLException;
import java.util.List;


/**
 *
 * @author HP
 */
public interface IService <T>{
     void ajouter(T t) throws SQLException;
    List<T> afficher() throws SQLException;
  
    void modifier(T t , int id) throws SQLException;
    void supprimer(int id) throws SQLException;
    
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author bhk
 */
public interface IServiceCat <T>{
    public void add(T entity);
    void modifier(T t , int id) throws SQLException;
    void supprimer(int id) throws SQLException;
    public ArrayList<T> getAll();
    
}

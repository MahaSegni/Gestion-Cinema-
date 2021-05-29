/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class MaConnexion {

    private static MaConnexion data;
    private Connection con;
    private static String url = "jdbc:mysql://localhost:3306/happy";
    private static String login = "root";
    private static String pwd = "";

    private MaConnexion() {
        try {
            con = DriverManager.getConnection(url, login, pwd);
            System.out.println("connexion établie");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public Connection getConnection() {
        return con;
    }

    public static MaConnexion getInstance() {
        if (data == null) {
            data = new MaConnexion();
        }
        return data;
    }

}
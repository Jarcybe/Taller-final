/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajofinal;

/**
 *
 * @author USUARIO
 */
import java.sql.*;

public class conexion {
    Connection con = null;
        String url = "jdbc:mysql://localhost/eventosbd";
        String user="root";
        String password="";

    
    public Connection getConexion(){
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,user, password);
            
        } catch (ClassNotFoundException | SQLException e) {
            
            System.err.println(e);
        }
    
    
    return con;
    
    }
    
}

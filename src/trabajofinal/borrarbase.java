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
import javax.swing.JOptionPane;

public class borrarbase {
    
     Connection papa = null;
    
    String ur1 = "jdbc:mysql://localhost/proyecto";
    
    String user = "root";
    
    String password = "";
    
    public borrarbase(){
    
        try{
        
            papa = DriverManager.getConnection(ur1, user, password);
        
            Statement bor = papa.createStatement();
            bor.execute("DROP DATABASE  proyecto");
            
         JOptionPane.showMessageDialog(null, "Base de datos eliminada");
        
        
        }catch(SQLException d){
        
            JOptionPane.showMessageDialog(null, "No hay base de datos para eliminar");
        }
    
    }
}
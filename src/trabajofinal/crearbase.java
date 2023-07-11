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


public class crearbase {
    Connection paco = null;
    
    
    Connection paco2 = null;
    
    String ur1 = "jdbc:mysql://localhost";
    
    String ur2 = "jdbc:mysql://localhost/proyecto";
    
    String user = "root";
    
    String password = "";
    
    public crearbase(){
    
     String entrada = "CREATE TABLE entrada(" +
            
             "cod_E int(11) NOT NULL," +
            
             "tipo_vehiculo varchar(10) NOT NULL," +
             "puesto_p int(11) NOT NULL,"+
             "placa varchar(10) NOT NULL," +
             "HE int(2) NOT NULL,"+
             "ME int(2) NOT NULL,"+
             "codigo int(11) DEFAULT NULL,"+
            
             "PRIMARY KEY (cod_E),"+
             
             "KEY FK_entrada_usuario (codigo),"+
             
             "CONSTRAINT FK_entrada_usuario FOREIGN KEY (codigo) REFERENCES usuarios (codigo) ON DELETE CASCADE ON UPDATE CASCADE"+
             ");";
     
     String usuarios ="CREATE TABLE usuarios(" +
             
             "codigo int(11) NOT NULL AUTO_INCREMENT,"+
             
             "nombre varchar(50) NOT NULL,"+
             "correo varchar(50) NOT NULL," +
             "contraseña varchar(50) NOT NULL," +
             
             "nivel int(4) NOT NULL,"
             
             + "PRIMARY KEY (codigo)"
             + ");";
     
 String registro ="CREATE TABLE registro(" +
             
             "codigos int(11) NOT NULL ,"+
            
             "tipo_vehiculo varchar(10) NOT NULL," +
             
             "puesto_p int(11) NOT NULL,"+
             "placa varchar(10) NOT NULL," +
             "HE int(2) NOT NULL,"+
             "ME int(2) NOT NULL,"+
              "nivel int(4) NOT NULL,"+
             
             
               "HS int(2) NOT NULL,"+
              "MS int(2) NOT NULL,"
             
             + "v_total int(11) NOT NULL,"
             + "T_horas int(2)  NOT NULL,"
             + "t_pagar int(10) NOT NULL,"
             
            +"cod_usuario int(10) NOT NULL,"
             
             + "PRIMARY KEY (codigos),"
             
             + "KEY FK_usu (cod_usuario),"
             
             + "CONSTRAINT FK_usu FOREIGN KEY (cod_usuario) REFERENCES usuarios (codigo) ON DELETE CASCADE ON UPDATE CASCADE"
             + ");";

     
     String factura = "CREATE TABLE factura ("
                 + "id_factura int (11) NOT NULL,"
             
                 + "codigo int (11) NOT NULL,"
                 + "cod_s int (11) NOT NULL,"
             
                 + "PRIMARY KEY(id_factura),"
             
                 + "KEY FK_usuario (codigo),"
                 + "KEY FK_salida (cod_s),"
             
                 + "CONSTRAINT FK_usuario FOREIGN KEY (codigo) REFERENCES usuarios (codigo) ON DELETE CASCADE ON UPDATE CASCADE,"
                 + "CONSTRAINT FK_salida FOREIGN KEY (cod_s) REFERENCES salida (cod_s) ON DELETE CASCADE ON UPDATE CASCADE"
                 + ");";
     
     String salida = "CREATE TABLE salida("
             
             + "cod_s int(11) NOT NULL,"
             
             + "HS int(2) NOT NULL,"
             + "MS int(2) NOT NULL,"
             
             + "v_total int(11) NOT NULL,"
             + "T_horas int(2)  NOT NULL,"
             + "t_pagar int(10) NOT NULL,"
             
             
             + "cod_e int(11) NOT NULL,"
             + "cod_tarifa int(11) NOT NULL,"
             
             + "PRIMARY KEY(cod_s),"
             
             + "KEY FK_salida_entrada (cod_e),"
             + "KEY FK_salida_tarifa (cod_tarifa),"
             
             + "CONSTRAINT FK_salida_entrada FOREIGN KEY (cod_e) REFERENCES entrada (cod_E) ON DELETE CASCADE ON UPDATE CASCADE,"
             
             + "CONSTRAINT FK_salida_tarifa FOREIGN KEY (cod_tarifa) REFERENCES tarifa (cod_t) ON DELETE CASCADE ON UPDATE CASCADE"
             + ");";
     
     String tarifa = "CREATE TABLE tarifa("
             + "cod_t int(11) NOT NULL,"
             
             + "P_moto int(10) NOT NULL,"
             + "P_carro int(10) NOT NULL,"
             + "P_bici int(10) NOT NULL,"
             
             + "PRIMARY KEY(cod_t)"
             + ");";
     
     
   
    try{
        
 paco= DriverManager.getConnection(ur1, user, password);
 
 Statement stmt  = paco.createStatement();
 stmt.execute("create database proyecto");
 
  paco2= DriverManager.getConnection(ur2, user, password);
Statement stmt2 = paco2.createStatement();

  stmt2.execute(usuarios);
  stmt2.execute(entrada);
  stmt2.execute(tarifa);
  stmt2.execute(salida);
  stmt2.execute(registro);
  stmt2.execute(factura);
 
 
 JOptionPane.showMessageDialog(null, "Base de datos creada con exito");
        
        }catch( SQLException d){
            
        JOptionPane.showMessageDialog(null, "Base de datos ya existe");
        }
        
 finally{
    
        try{
        
        if(paco!= null) 
            paco.close();
        
        
        }catch(Exception e){
        System.out.println(e);
        }
    
        
    }
    
    }
    
}

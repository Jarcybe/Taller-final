/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajofinal;


import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.sql.*;


/**
 *
 * @author USUARIO
 */
public class crearusuario extends JDialog{
    
    JLabel vehiculo,correo,contrasena,nivele, relleno;
    JTextField nom,corr,contr;
    JButton crear, cancelar;
    JComboBox ros;
    int codigo = 0;
    

    public crearusuario(Frame capitan, boolean pepin){
        
        super(capitan, pepin);
        
        Container c = getContentPane();
        c.setLayout(new FlowLayout());
        
        vehiculo = new JLabel("      Nombre ");
        correo = new JLabel("        Correo ");
        contrasena = new JLabel("Contraseña ");
        relleno = new JLabel("             ");
        nivele = new JLabel("               Nivel ");
        
        nom = new JTextField(10);
        corr = new JTextField(10);
        contr = new JTextField(10);
        
        crear = new JButton("Crear");
        cancelar = new JButton("Cancelar");
        
        String[] niveles = {"1","2","3"};
        ros = new JComboBox(niveles);
        
        c.add(vehiculo);
        c.add(nom);
        c.add(correo);
        c.add(corr);
        c.add(contrasena);
        c.add(contr);
        c.add(nivele);
        c.add(ros);
        c.add(relleno);
        c.add(crear);
        c.add(cancelar);
        
        crear.addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
    
        basedatos();
        
        }
        });
        
         cancelar.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent a){
        
        dispose();}
        
        });
        
        setSize(220, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    
    public void basedatos(){
        
        
        String url = "jdbc:mysql://localhost/proyecto";
        String usuario = "root";
        String contraseña = "";
        
        boolean a1  = nom.getText().isEmpty();
        boolean a2  = corr.getText().isEmpty();
        boolean a3  = contr.getText().isEmpty();
        
        String nombre = nom.getText();
        String correo = corr.getText();
        String contrasena = contr.getText();
        int numero = ros.getSelectedIndex();
        
        if(!a1 && !a3 && !a2){
        
            if(correo.endsWith("@gmail.com")){
        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña)) {
            // Consulta SQL para insertar los datos en la tabla
            String sql = "INSERT INTO usuarios (codigo, nombre, correo, contraseña, nivel) VALUES (?, ?, ?, ?, ?)";

            // Crear una sentencia preparada para la consulta SQL
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, codigo);
            statement.setString(2, nombre);
            statement.setString(3, correo);
            statement.setString(4, contrasena);
            statement.setInt(5, numero);
            codigo++;
           
            // Ejecutar la consulta y obtener el número de filas afectadas
            int filasAfectadas = statement.executeUpdate();
            if (filasAfectadas > 0) {
                
               JOptionPane.showMessageDialog(null, "Datos insertados correctamente en la base de datos");
             
                conn.close();
                dispose();
            } else {
                
                JOptionPane.showMessageDialog(null, "No se pudieron insertar los datos en la base de datos.");
             
            }
        } catch (SQLException e) {
            
             JOptionPane.showMessageDialog(null, "Error tipo" + e);
    }
            }else{
            
        JOptionPane.showMessageDialog(null, "El correo debe terminar en @gmail.com");
            }
        
        }else{
        JOptionPane.showMessageDialog(null, "Todo debe ser llenado");}
}
}
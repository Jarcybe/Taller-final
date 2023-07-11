/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajofinal;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.sql.*;


/**
 *
 * @author USUARIO
 */
public class login extends JDialog {
    
    JLabel l1,l2;
    JTextField usuario,contraseña;
    JButton btnlogin;
    String u[][];
    String usuar,contrasena;
    String correoBD ;
    String contraseñaBD ;
    int nivel;
    
    
    public login(Frame papa, boolean modal){
    super(papa,modal);
    l1 = new JLabel("Correo:      ");
    l2 = new JLabel("Contraseña:    ");
    usuario = new JTextField(10);
    contraseña = new JTextField(10);
    
    btnlogin = new JButton("Login");
    Container c = getContentPane();
    c.setLayout(new FlowLayout());
    c.add(l1);
    c.add(usuario);
    c.add(l2);
    c.add(contraseña);
    c.add(btnlogin);
    
    btnlogin.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            
            String corre = usuario.getText();
            String contr = contraseña.getText();
             Login2(corre, contr);
        }
        });
   
   
   setSize(250,200);
    setLocationRelativeTo(null);
   setVisible(true);
}
    
    


   

public void Login2(String correoIngresado, String contraseñaIngresada) {
    String url = "jdbc:mysql://localhost:3306/proyecto";
    String usuario = "root";
    String contraseña = "";
    
    // Obtener los datos del usuario desde la base de datos
    try (Connection conn = DriverManager.getConnection(url, usuario, contraseña);
         PreparedStatement statement = conn.prepareStatement("SELECT correo, contraseña, nivel FROM usuarios WHERE correo = ?")) {
        
        statement.setString(1, correoIngresado);
        
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                correoBD = resultSet.getString("correo");
                contraseñaBD = resultSet.getString("contraseña");
                nivel = resultSet.getInt("nivel");
            }
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al obtener los datos de usuario: " + e);
    }

    // Verificar si los datos ingresados coinciden con los datos de la base de datos
    if (correoIngresado.equals(correoBD) && contraseñaIngresada.equals(contraseñaBD)) {
        // Los datos son correctos, crear el archivo "log" con el número de nivel
        crearArchivoLog(nivel);
         JOptionPane.showMessageDialog(null, "Login exitoso. Archivo 'log' creado.");
        dispose();
    } else {
         JOptionPane.showMessageDialog(null, "Login fallido. Los datos ingresados son incorrectos.");
    }
}


public void crearArchivoLog(int nivel) {
        try (FileWriter writer = new FileWriter("log")) {
            writer.write(String.valueOf(nivel));
        } catch (IOException e) {
             JOptionPane.showMessageDialog(null, "Error al crear el archivo 'log': " + e);
        }
    }
        
}


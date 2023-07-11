/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajofinal;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;


public class modificarusuario extends JDialog {
    
    JLabel nombre,correo,contrasena,nivele,cod, relleno;
    JTextField nom,corr,contr,codigo;
    JButton modificar, cancelar,buscar;
    JComboBox ros;
    
     public modificarusuario(Frame no, boolean se){
         
         super(no, se);
        Container c = getContentPane();
        c.setLayout(new FlowLayout());
        
        cod = new JLabel("Codigo ");
        nombre = new JLabel("Nombre ");
        correo = new JLabel("Correo ");
        contrasena = new JLabel("Contraseña ");
        nivele = new JLabel("         Nivel ");
        relleno = new JLabel("          ");
        
        
        nom = new JTextField(10);
        corr = new JTextField(10);
        contr = new JTextField(10);
        codigo = new JTextField(5);
        
        modificar = new JButton("modificar");
        cancelar = new JButton("cancelar");
        buscar = new JButton("buscar");
        
        
        String[] niveles = {"1","2","3"};
        ros = new JComboBox(niveles);
        
        
        c.add(cod);
        c.add(codigo);
        c.add(buscar);
        c.add(nombre);
        c.add(nom);
        c.add(correo);
        c.add(corr);
        c.add(contrasena);
        c.add(contr);
        c.add(nivele);
        c.add(ros);
        c.add(relleno);
        c.add(modificar);
        c.add(cancelar);
        
        buscar.addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
    
        encontrar();
        
        }
        });
        
        modificar.addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
    
        modificar();
        
        }
        });
        
        cancelar.addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
    
        dispose();
        
        }
        });
        
        setSize(210, 250);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public void encontrar(){
        String url = "jdbc:mysql://localhost:3306/proyecto";
        String usuario = "root";
        String contraseña = "";
        
        try{
        int numeroBuscado = Integer.parseInt(codigo.getText());
        
        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña)) {
            // Consulta SQL para buscar el número en la tabla
            String sql = "SELECT * FROM usuarios WHERE codigo = ?";
            
            // Crear una sentencia preparada para la consulta SQL
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, numeroBuscado);
            
            // Ejecutar la consulta y obtener los resultados
            ResultSet resultSet = statement.executeQuery();
            
            // Verificar si se encontró el número
            if (resultSet.next()) {
                // El número fue encontrado
                
                String nomb = resultSet.getString("nombre");
                String corrb = resultSet.getString("correo");
                String contrb = resultSet.getString("contraseña");
                int numero = resultSet.getInt("nivel");
                
                nom.setText(nomb);
                corr.setText(corrb);
                contr.setText(corrb);
                contr.setText(contrb); 
                
                conn.close();
                
            } else {
               
        JOptionPane.showMessageDialog(null, "El usuario no existe");
            }
        } catch (SQLException e) {
            
            
        JOptionPane.showMessageDialog(null, e);
        }
        
        }catch(HeadlessException | NumberFormatException a){
        
        JOptionPane.showMessageDialog(null, "Coloque un numero pendejo");
        }
    }
    
    public void modificar(){
        
        
        String url = "jdbc:mysql://localhost:3306/proyecto";
        String usuario = "root";
        String contraseña = "";
        
        int numeroBuscado = Integer.parseInt(codigo.getText());
        String nuevoNombre = nom.getText();
        String nuevoCorreo = corr.getText();
        String nuevaContraseña = contr.getText();
        
        boolean a1  = nom.getText().isEmpty();
        boolean a2  = corr.getText().isEmpty();
        boolean a3  = contr.getText().isEmpty();
        
        if(!a1 && !a2 && !a3){
            
            if(nuevoCorreo.endsWith("@gmail.com")){
        int numero = 0;
        
        
         try(Connection conn = DriverManager.getConnection(url, usuario, contraseña)){
            
            
            
        String sql = "UPDATE usuarios SET  codigo = ?, nombre = ?, correo = ?, contraseña = ?, nivel = ? WHERE codigo = ?;";
            
        PreparedStatement luz = conn.prepareStatement(sql);
        
        luz.setInt(1, numeroBuscado);
        luz.setString(2, nuevoNombre);
        luz.setString(3, nuevoCorreo);
        luz.setString(4, nuevaContraseña);
        luz.setInt(5, numero);
        luz.setInt(6, numeroBuscado);
        
        luz.executeUpdate();
        luz.close();
        
                    JOptionPane.showMessageDialog(null, "Datos cambiados con exito :D");

        dispose();
        }catch(Exception d){
        
        JOptionPane.showMessageDialog(null, "ERROR al actualizar"+ d.getMessage(),
                "ERROR",
                JOptionPane.ERROR_MESSAGE);
        }
            }else{
            JOptionPane.showMessageDialog(null, "El email debe terminar en: @gmail.com");
            }
    }else{
        
        JOptionPane.showMessageDialog(null, "Todo debe ser llenado");
        }
    }
}

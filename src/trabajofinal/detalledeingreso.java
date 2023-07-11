/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajofinal;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

/**
 *
 * @author USUARIO
 */
public class detalledeingreso extends JDialog{
    
    JLabel vehiculo,Placa,horaentrada,minentrada,nombrecliente, relleno1, relleno2;
    JTextField cajavehiculo,cajaplaca,cajahoraentrada,cajaminentrada,cajanombrecliente;
    JButton guardar, cancelar;
    int codigo = 0;
    int codigous = 0;
   
    
    
    public detalledeingreso(Frame papa, boolean frita, String tipoVehiculo, int puesto, String placa, String horaEntrada, String minutosEntrada){
       
        super(papa, frita);
        Container c = getContentPane();
        c.setLayout(new FlowLayout());
        
        vehiculo = new JLabel("       Vehiculo   ");
        Placa = new JLabel("          Placa         ");
        horaentrada = new JLabel("           Hora de entrada            ");
        minentrada = new JLabel("          Minuto de entrada        ");
        nombrecliente = new JLabel("         Nombre cliente     ");
        relleno1 = new JLabel ("         ");
        relleno2 = new JLabel ("          ");
        
        cajavehiculo = new JTextField(10);
        cajaplaca = new JTextField(10);
        cajahoraentrada = new JTextField(3);
        cajaminentrada = new JTextField(3);
        cajanombrecliente = new JTextField(15);
        
        guardar = new JButton("guardar");
        cancelar = new JButton("cancelar");
        
        String[] niveles = {"1","2","3"};
        
        c.add(vehiculo);
        c.add(cajavehiculo);
        c.add(Placa);
        c.add(cajaplaca);
        c.add(horaentrada);
        c.add(cajahoraentrada);
        c.add(minentrada);

        c.add(relleno2);
        c.add(cajaminentrada);
        c.add(relleno1);
       
        c.add(nombrecliente);
                
        c.add(cajanombrecliente);
        c.add(guardar);
        c.add(cancelar);
        
        cajavehiculo.setEditable(false);
        cajaplaca.setEditable(false);
        cajahoraentrada.setEditable(false);
        cajaminentrada.setEditable(false);
        
        
        
        cajahoraentrada.setText(horaEntrada);
        cajaminentrada.setText(minutosEntrada);
        
        String tipoVehicul = obtenerTipoVehiculo(tipoVehiculo);
        String placaExtraida = extraerPlaca(placa);
        cajaplaca.setText(placaExtraida);
        cajavehiculo.setText(tipoVehicul);
        
        guardar.addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
    
        
        
        int horae = Integer.parseInt(horaEntrada);
        int mine = Integer.parseInt(minutosEntrada);
        
        encontrar();
        mandar(codigo,tipoVehicul,puesto,placaExtraida,horae,mine,codigous);
        
        mandar2(codigo,tipoVehicul,puesto,placaExtraida,horae,mine,codigous);
        codigo++;
                }
        });
        
         cancelar.addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
     dispose();
        
                }
        });

        setSize(200, 340);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    
    public static String extraerPlaca(String placa) {
    String[] partes = placa.split("-");
    if (partes.length >= 3) {
        return partes[2];
    } else {
        return "";
    }
}
    
    public static String obtenerTipoVehiculo(String tipo) {
    if (tipo.equalsIgnoreCase("C")) {
        return "Carro";
    } else if (tipo.equalsIgnoreCase("B")) {
        return "Bicicleta";
    } else if (tipo.equalsIgnoreCase("M")) {
        return "Moto";
    } else {
        return "Tipo de vehículo desconocido";
    }
}
    
    public void mandar(int codigo, String tipoVehiculo, int puesto, String placa, int horaEntrada, int minutosEntrada,  int codigous){
        
         String url = "jdbc:mysql://localhost:3306/proyecto";
         String usuario = "root";
         String contraseña = "";
        

        
        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña)) {
            // Consulta SQL para insertar los datos en la tabla
            String sql = "INSERT INTO entrada  "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            
            
            
            // Crear una sentencia preparada para la consulta SQL
            PreparedStatement statement = conn.prepareStatement(sql);
            
            
            statement.setInt(1, codigo);
            statement.setString(2, tipoVehiculo);
            statement.setInt(3, puesto);
            statement.setString(4, placa);
            statement.setInt(5, horaEntrada);
            statement.setInt(6, minutosEntrada);
            statement.setInt(7, codigous);
            codigo++;
            
            System.out.println(tipoVehiculo+" "+puesto+" "+placa+" "+horaEntrada+" "+minutosEntrada+" "+codigo+" "+codigous);
            
            // Ejecutar la consulta y obtener el número de filas afectadas
            int filasAfectadas = statement.executeUpdate();
            if (filasAfectadas > 0) {
                 JOptionPane.showMessageDialog(null, "Datos insertados correctamente en la base de datos.");
              conn.close();
              dispose();
            } else {
                 JOptionPane.showMessageDialog(null, "No se pudieron insertar los datos en la base de datos.");
             
            }
        } catch (SQLException e) {
            
            System.out.println(e);
    }
    }
    
public void mandar2(int codigo, String tipoVehiculo, int puesto, String placa, int horaEntrada, int minutosEntrada, int codigoUsuario) {
    System.out.println("siii");
    String url = "jdbc:mysql://localhost:3306/proyecto";
    String usuario = "root";
    String contraseña = "";

    try (Connection conn = DriverManager.getConnection(url, usuario, contraseña)) {
        // Consulta SQL para insertar los datos en la tabla
        String sql = "INSERT INTO registro (codigos, tipo_vehiculo, puesto_p, placa, HE, ME, cod_usuario, HS, MS, v_total, T_horas, t_pagar, nivel) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // Crear una sentencia preparada para la consulta SQL
        PreparedStatement statement = conn.prepareStatement(sql);
        
        statement.setInt(1, codigo);
        statement.setString(2, tipoVehiculo);
        statement.setInt(3, puesto);
        statement.setString(4, placa);
        statement.setInt(5, horaEntrada);
        statement.setInt(6, minutosEntrada);
        statement.setInt(7, codigoUsuario);
        statement.setInt(8, 00);
        statement.setInt(9, 00);
        statement.setInt(10, 00);
        statement.setInt(11, 00);
        statement.setInt(12, 00);
        statement.setInt(13, 00);

        // Ejecutar la consulta y obtener el número de filas afectadas
        int filasAfectadas = statement.executeUpdate();
        if (filasAfectadas > 0) {
            JOptionPane.showMessageDialog(null, "Datos insertados correctamente en la base de datos.");
            conn.close();
            dispose();
        } else {
            JOptionPane.showMessageDialog(null, "No se pudieron insertar los datos en la base de datos.");
        }
    } catch (SQLException e) {
        System.out.println(e);
    }
}

    
     public void encontrar(){
        String url = "jdbc:mysql://localhost:3306/proyecto";
        String usuario = "root";
        String contraseña = "";
        
        String nombreabuscar = cajanombrecliente.getText();
        
        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña)) {
            // Consulta SQL para buscar el número en la tabla
            String sql = "SELECT * FROM usuarios WHERE nombre = ?";
            
            // Crear una sentencia preparada para la consulta SQL
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, nombreabuscar);
            
            // Ejecutar la consulta y obtener los resultados
            ResultSet resultSet = statement.executeQuery();
            
            // Verificar si se encontró el número
            if (resultSet.next()) {
                // El nombre fue encontrado
                
                
                 codigous = resultSet.getInt("codigo");
                 System.out.println(codigous);
                
                 
                
                conn.close();
                
            } else {
                System.out.println("El usuario no existe");
            }
        } catch (SQLException e) {
        }
        
        
    }
    
}

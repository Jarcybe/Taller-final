/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajofinal;

import java.awt.Frame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;


/**
 *
 * @author USUARIO
 */
public class modificaregistros extends JDialog{
    
    JLabel nombre, cod;
    JTextField codigo;
    JButton buscar, cancelar ;
    JTable tabla;
    DefaultTableModel modelo;
    JScrollPane Scroll;
    JComboBox<String> j;
    int codF = 0, codS = 0, codUsu = 0;
    int hs = 0, thor = 0, vhor = 0, total = 0, codE = 0;
    String vehiculo = "", placa = "", cliente = "", USU = "";
    int HE = 0, codC = 0;
    
   
    public modificaregistros (Frame pe, boolean rez) {
        
        super(pe, rez);

        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        modelo = new DefaultTableModel() {
        Class[] columnTypes = new Class[] { Integer.class, String.class, String.class, String.class, String.class };

          
        };

        tabla = new JTable(modelo);

        nombre = new JLabel("nombre");
        cod = new JLabel("codigo");

        codigo = new JTextField(10);

        buscar = new JButton("buscar");
        cancelar = new JButton("cancelar");

        modelo.addColumn("factura ");
        modelo.addColumn("vehiculo ");
        modelo.addColumn("placa ");
        modelo.addColumn("cliente ");
        modelo.addColumn("H entrada ");
        modelo.addColumn("H salida ");
        modelo.addColumn("horas ");
        modelo.addColumn("valor hora ");
        modelo.addColumn("total ");
        modelo.addColumn("empleado ");
 
        Scroll = new JScrollPane(tabla, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        String[] niv = { "placa ", "factura ", "todos ", "empleado" };
        j = new JComboBox<>(niv);

        c.add(nombre);
        c.add(j);
        c.add(cod);
        c.add(codigo);
        c.add(buscar);
        c.add(Scroll);
        c.add(cancelar);

        buscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               
                condicional2();
            }
        });

        cancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              
                dispose();
            }
        });

        setSize(500, 550);
        setLocationRelativeTo(null);
        setVisible(true);
    }

      
          public void condicional2() {
        if (j.getSelectedIndex() == 0) {
            String placaBuscada = codigo.getText();
            obtener(modelo,placaBuscada);
            System.out.println("0");

        }
        if (j.getSelectedIndex() == 1) {
            int codigoBuscado = Integer.parseInt(codigo.getText());
            obtener1(modelo, codigoBuscado);
            System.out.println("1");
        }
        if (j.getSelectedIndex() == 2) {
            obtener2(modelo);
            System.out.println("2");
        }
        if (j.getSelectedIndex() == 3) {
            
            System.out.println("3");
        }
        if (j.getSelectedIndex() == 4) {
            
            System.out.println("4");
        }
    }
          
public void obtener2(DefaultTableModel model) {
    // Configurar la conexión a la base de datos
    String url = "jdbc:mysql://localhost:3306/proyecto";
    String usuario = "root";
    String contraseña = "";
    
    try (Connection conn = DriverManager.getConnection(url, usuario, contraseña)) {
        // Consulta SQL para obtener todos los registros de la tabla
        String sql = "SELECT * FROM registro";
        
        // Crear una sentencia para la consulta SQL
        PreparedStatement statement = conn.prepareStatement(sql);
        
        // Ejecutar la consulta y obtener los resultados
        ResultSet resultSet = statement.executeQuery();
        
        // Recorrer el resultado y agregar los datos al modelo de la JTable
        while (resultSet.next()) {
            int codigo = resultSet.getInt("codigos");
            String tipoVehiculo = resultSet.getString("tipo_vehiculo");
            int puesto = resultSet.getInt("puesto_p");
            String placa = resultSet.getString("placa");
            int HE = resultSet.getInt("HE");
            int HS = resultSet.getInt("HS");
            int horas = resultSet.getInt("T_horas");
            double valorHora = resultSet.getDouble("v_total");
            double total = resultSet.getDouble("t_pagar");
            int codUsuario = resultSet.getInt("cod_usuario");
            
            // Agregar los datos al modelo de la JTable
            model.addRow(new Object[]{codigo, tipoVehiculo, puesto, placa, HE, HS, horas, valorHora, total, codUsuario});
        }
        
        // Cerrar la conexión
        conn.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
}

public void obtener(DefaultTableModel model, String placaBuscada) {
    // Configurar la conexión a la base de datos
    String url = "jdbc:mysql://localhost:3306/proyecto";
    String usuario = "root";
    String contraseña = "";
    
    try (Connection conn = DriverManager.getConnection(url, usuario, contraseña)) {
        // Consulta SQL para obtener los registros que coincidan con la placa
        String sql = "SELECT * FROM registro WHERE placa = ?";
        
        // Crear una sentencia para la consulta SQL
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, placaBuscada);
        
        // Ejecutar la consulta y obtener los resultados
        ResultSet resultSet = statement.executeQuery();
        
        // Recorrer el resultado y agregar los datos al modelo de la JTable
        while (resultSet.next()) {
            int codigo = resultSet.getInt("codigos");
            String tipoVehiculo = resultSet.getString("tipo_vehiculo");
            int puesto = resultSet.getInt("puesto_p");
            String placa = resultSet.getString("placa");
            int HE = resultSet.getInt("HE");
            int HS = resultSet.getInt("HS");
            int horas = resultSet.getInt("T_horas");
            double valorHora = resultSet.getDouble("v_total");
            double total = resultSet.getDouble("t_pagar");
            int codUsuario = resultSet.getInt("cod_usuario");
            
            // Agregar los datos al modelo de la JTable
            model.addRow(new Object[]{codigo, tipoVehiculo, puesto, placa, HE, HS, horas, valorHora, total, codUsuario});
        }
        
        // Cerrar la conexión
        conn.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
}

public void obtener1(DefaultTableModel model, int codigoBuscado) {
    // Configurar la conexión a la base de datos
    String url = "jdbc:mysql://localhost:3306/proyecto";
    String usuario = "root";
    String contraseña = "";
    
    try (Connection conn = DriverManager.getConnection(url, usuario, contraseña)) {
        // Consulta SQL para obtener los registros que coincidan con el código
        String sql = "SELECT * FROM registro WHERE codigos = ?";
        
        // Crear una sentencia para la consulta SQL
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, codigoBuscado);
        
        // Ejecutar la consulta y obtener los resultados
        ResultSet resultSet = statement.executeQuery();
        
        // Recorrer el resultado y agregar los datos al modelo de la JTable
        while (resultSet.next()) {
            int codigo = resultSet.getInt("codigos");
            String tipoVehiculo = resultSet.getString("tipo_vehiculo");
            int puesto = resultSet.getInt("puesto_p");
            String placa = resultSet.getString("placa");
            int HE = resultSet.getInt("HE");
            int HS = resultSet.getInt("HS");
            int horas = resultSet.getInt("T_horas");
            double valorHora = resultSet.getDouble("v_total");
            double total = resultSet.getDouble("t_pagar");
            int codUsuario = resultSet.getInt("cod_usuario");
            
            // Agregar los datos al modelo de la JTable
            model.addRow(new Object[]{codigo, tipoVehiculo, puesto, placa, HE, HS, horas, valorHora, total, codUsuario});
        }
        
        // Cerrar la conexión
        conn.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
}




}


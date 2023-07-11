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
public class buscaregistros extends JDialog{
    
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
    
   
    public buscaregistros(Frame pe, boolean rez) {
        
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
            obtenerTodos2(modelo);
            System.out.println("0");

        }
        if (j.getSelectedIndex() == 1) {
            
            System.out.println("1");
        }
        if (j.getSelectedIndex() == 2) {
            obtenerTodos(modelo);
            
        }
        if (j.getSelectedIndex() == 3) {
            
            System.out.println("3");
        }
        if (j.getSelectedIndex() == 4) {
            
            System.out.println("4");
        }
    }
      
public void obtenerTodos(DefaultTableModel model) {
    // Configurar la conexión a la base de datos
    String url = "jdbc:mysql://localhost:3306/proyecto";
    String usuario = "root";
    String contraseña = "";

    // Variables para almacenar los datos


    // Realizar la consulta de los codigos de factura
    try (Connection conn = DriverManager.getConnection(url, usuario, contraseña);
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT id_factura, codigo, cod_s FROM factura")) {

        // Iterar sobre los resultados y obtener los datos
        if (rs.next()) {
            codF = rs.getInt("id_factura");
            codUsu = rs.getInt("codigo");
            codS = rs.getInt("cod_s");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e);
    }

    // Realizar la consulta de salida
    try (Connection conn = DriverManager.getConnection(url, usuario, contraseña);
         PreparedStatement statement = conn.prepareStatement("SELECT HS, v_total, T_horas, t_pagar, cod_e FROM salida WHERE cod_s = ?")) {
        statement.setInt(1, codS);
        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            hs = rs.getInt("HS");
            thor = rs.getInt("T_horas");
            vhor = rs.getInt("v_total");
            total = rs.getInt("t_pagar");
            codE = rs.getInt("cod_e");
        } else {
            JOptionPane.showMessageDialog(null, "El usuario no existe");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e);
    }

    // Realizar la consulta de entrada
    try (Connection conn = DriverManager.getConnection(url, usuario, contraseña);
         PreparedStatement statement = conn.prepareStatement("SELECT tipo_vehiculo, placa, HE, codigo FROM entrada WHERE cod_E = ?")) {
        statement.setInt(1, codE);
        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            vehiculo = rs.getString("tipo_vehiculo");
            placa = rs.getString("placa");
            HE = rs.getInt("HE");
            codC = rs.getInt("codigo");
        } else {
            JOptionPane.showMessageDialog(null, "El usuario no existe");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e);
    }

    // Obtener el nombre del empleado
    try (Connection conn = DriverManager.getConnection(url, usuario, contraseña);
         PreparedStatement statement = conn.prepareStatement("SELECT nombre FROM usuarios WHERE codigo = ?")) {
        statement.setInt(1, codUsu);
        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            USU = rs.getString("nombre");
        } else {
            JOptionPane.showMessageDialog(null, "El usuario no existe");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e);
    }

    // Obtener el nombre del cliente
    try (Connection conn = DriverManager.getConnection(url, usuario, contraseña);
         PreparedStatement statement = conn.prepareStatement("SELECT nombre FROM usuarios WHERE codigo = ?")) {
        statement.setInt(1, codC);
        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            cliente = rs.getString("nombre");
        } else {
            JOptionPane.showMessageDialog(null, "El usuario no existe");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e);
    }
     model.addRow(new Object[]{codF, vehiculo, placa, cliente, HE, hs, thor, vhor, total, USU});

    // Imprimir los datos obtenidos
    System.out.println("Factura: " + codF +
            ", vehiculo: " + vehiculo +
            ", placa: " + placa +
            ", cliente: " + cliente +
            ", Hora entrada: " + HE +
            ", hora salida: " + hs +
            ", horas: " + thor +
            ", Valor h: " + vhor +
            ", total: " + total +
            ", empleado: " + USU +
            ", Cod Empleado: " + codUsu +
            ", Cod cliente: " + codC);
}

public void obtenerTodos2(DefaultTableModel model) {
    // Configurar la conexión a la base de datos
    String url = "jdbc:mysql://localhost:3306/proyecto";
    String usuario = "root";
    String contraseña = "";


    // Realizar la consulta de los códigos de factura
    try (Connection conn = DriverManager.getConnection(url, usuario, contraseña);
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT id_factura, codigo, cod_s FROM factura WHERE placa = '" + placa + "'")) {

        // Iterar sobre los resultados y obtener los datos
        if (rs.next()) {
            codF = rs.getInt("id_factura");
            codUsu = rs.getInt("codigo");
            codS = rs.getInt("cod_s");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e);
    }

    // Realizar la consulta de salida
    try (Connection conn = DriverManager.getConnection(url, usuario, contraseña);
         PreparedStatement statement = conn.prepareStatement("SELECT HS, v_total, T_horas, t_pagar, cod_e FROM salida WHERE cod_s = ?")) {
        statement.setInt(1, codS);
        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            hs = rs.getInt("HS");
            thor = rs.getInt("T_horas");
            vhor = rs.getInt("v_total");
            total = rs.getInt("t_pagar");
            codE = rs.getInt("cod_e");
        } else {
            JOptionPane.showMessageDialog(null, "El usuario no existe");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e);
    }

    // Realizar la consulta de entrada
    try (Connection conn = DriverManager.getConnection(url, usuario, contraseña);
         PreparedStatement statement = conn.prepareStatement("SELECT tipo_vehiculo, placa, HE, codigo FROM entrada WHERE cod_E = ?")) {
        statement.setInt(1, codE);
        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            vehiculo = rs.getString("tipo_vehiculo");
            placa = rs.getString("placa");
            HE = rs.getInt("HE");
            codC = rs.getInt("codigo");
        } else {
            JOptionPane.showMessageDialog(null, "El usuario no existe");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e);
    }

    // Obtener el nombre del empleado
    try (Connection conn = DriverManager.getConnection(url, usuario, contraseña);
         PreparedStatement statement = conn.prepareStatement("SELECT nombre FROM usuarios WHERE codigo = ?")) {
        statement.setInt(1, codUsu);
        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            USU = rs.getString("nombre");
        } else {
            JOptionPane.showMessageDialog(null, "El usuario no existe");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e);
    }

    // Obtener el nombre del cliente
    try (Connection conn = DriverManager.getConnection(url, usuario, contraseña);
         PreparedStatement statement = conn.prepareStatement("SELECT nombre FROM usuarios WHERE codigo = ?")) {
        statement.setInt(1, codC);
        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            cliente = rs.getString("nombre");
        } else {
            JOptionPane.showMessageDialog(null, "El usuario no existe");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e);
    }

    // Agregar los datos a la tabla
    model.addRow(new Object[]{codF, vehiculo, placa, cliente, HE, hs, thor, vhor, total, USU});

    // Imprimir los datos obtenidos
    System.out.println("Factura: " + codF +
            ", vehiculo: " + vehiculo +
            ", placa: " + placa +
            ", cliente: " + cliente +
            ", Hora entrada: " + HE +
            ", hora salida: " + hs +
            ", horas: " + thor +
            ", Valor h: " + vhor +
            ", total: " + total +
            ", empleado: " + USU +
            ", Cod Empleado: " + codUsu +
            ", Cod cliente: " + codC);
}
}

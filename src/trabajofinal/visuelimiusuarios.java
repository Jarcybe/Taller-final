/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajofinal;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class visuelimiusuarios extends JDialog {
    JLabel nombre, cod;
    JTextField codigo;
    JButton buscar, cancelar, eliminar;
    JTable tabla;
    DefaultTableModel modelo;
    JScrollPane Scroll;
    JComboBox<String> j;

    public visuelimiusuarios(Frame pe, boolean rez) {
        super(pe, rez);

        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        modelo = new DefaultTableModel() {
            Class[] columnTypes = new Class[] { Integer.class, String.class, String.class, String.class, String.class, Boolean.class };

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        };

        tabla = new JTable(modelo);

        nombre = new JLabel("nombre");
        cod = new JLabel("codigo");

        codigo = new JTextField(10);

        buscar = new JButton("buscar");
        cancelar = new JButton("cancelar");
        eliminar = new JButton("eliminar");

        modelo.addColumn("Codigo");
        modelo.addColumn("nivel");
        modelo.addColumn("nombre");
        modelo.addColumn("correo");
        modelo.addColumn("contraseña");
        modelo.addColumn("eliminar");

        Scroll = new JScrollPane(tabla, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        String[] niv = { "todos ", "codigos ", "nivel 1", "nivel 2", "nivel 3" };
        j = new JComboBox<>(niv);

        c.add(nombre);
        c.add(j);
        c.add(cod);
        c.add(codigo);
        c.add(buscar);
        c.add(Scroll);
        c.add(eliminar);
        c.add(cancelar);

        buscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                condicional();
            }
        });

        cancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        eliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarUsuarios();
            }
        });

        setSize(500, 550);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void condicional() {
        if (j.getSelectedIndex() == 0) {
            obtener(modelo);
            System.out.println("0");

        }
        if (j.getSelectedIndex() == 1) {
            obtener1(modelo);
            System.out.println("1");
        }
        if (j.getSelectedIndex() == 2) {
            obtener2(modelo);
            System.out.println("2");
        }
        if (j.getSelectedIndex() == 3) {
            obtener3(modelo);
            System.out.println("3");
        }
        if (j.getSelectedIndex() == 4) {
            obtener4(modelo);
            System.out.println("4");
        }
    }

    private void obtener(DefaultTableModel model) {
        // Configurar la conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/proyecto";
        String usuario = "root";
        String contraseña = "";

        // Realizar la consulta para obtener los datos
        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT codigo, nivel, nombre, correo, contraseña FROM usuarios")) {

            // Iterar sobre los resultados y agregarlos a la tabla
            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                String nivel = rs.getString("nivel");
                String nombre = rs.getString("nombre");
                String correo = rs.getString("correo");
                String contrasena = rs.getString("contraseña");

                model.addRow(new Object[]{codigo, nivel, nombre, correo, contrasena, false});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void obtener1(DefaultTableModel model) {
        // Configurar la conexión a la base de datos
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
                
                String codigo = resultSet.getString("codigo");
                String nivel = resultSet.getString("nivel");
                String nombre = resultSet.getString("nombre");
                String correo = resultSet.getString("correo");
                String contrasena = resultSet.getString("contraseña");
                
                System.out.println(codigo);
                System.out.println(nivel);
                System.out.println(nombre);
                System.out.println(correo);
                System.out.println(contrasena);
                 
                model.addRow(new Object[]{codigo, nivel, nombre, correo, contrasena});
                
                conn.close();
                
            } else {
                 JOptionPane.showMessageDialog(null, "El usuario no existe");;
            }
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, "Error tipo:" + e);
        }
        
    }catch(HeadlessException | NumberFormatException a){
    
     JOptionPane.showMessageDialog(null, "Falta el numero");
    }
       
     }
    
    private void obtener2(DefaultTableModel model) {
        // Configurar la conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/proyecto";
        String usuario = "root";
        String contraseña = "";
        
        
        int numeroBuscado = 0;
        
        
        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña)) {
            // Consulta SQL para buscar el número en la tabla
            String sql = "SELECT * FROM usuarios WHERE nivel = ?";
            
            // Crear una sentencia preparada para la consulta SQL
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, numeroBuscado);
            
            // Ejecutar la consulta y obtener los resultados
            ResultSet resultSet = statement.executeQuery();
            
            // Verificar si se encontró el número
            if (resultSet.next()) {
                // El número fue encontrado
                
                String codigo = resultSet.getString("codigo");
                String nivel = resultSet.getString("nivel");
                String nombre = resultSet.getString("nombre");
                String correo = resultSet.getString("correo");
                String contrasena = resultSet.getString("contraseña");
                
                System.out.println(codigo);
                System.out.println(nivel);
                System.out.println(nombre);
                System.out.println(correo);
                System.out.println(contrasena);
                 
                model.addRow(new Object[]{codigo, nivel, nombre, correo, contrasena});
                
                conn.close();
                
            } else {
                JOptionPane.showMessageDialog(null, "El usuario no existe");
            }
        } catch (SQLException e) {
            
             JOptionPane.showMessageDialog(null, "Error tipo: " + e);
        }
        
     }
    
    private void obtener3(DefaultTableModel model) {
        // Configurar la conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/proyecto";
        String usuario = "root";
        String contraseña = "";
        
        int numeroBuscado = 1;
        
        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña)) {
            // Consulta SQL para buscar el número en la tabla
            String sql = "SELECT * FROM usuarios WHERE nivel = ?";
            
            // Crear una sentencia preparada para la consulta SQL
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, numeroBuscado);
            
            // Ejecutar la consulta y obtener los resultados
            ResultSet resultSet = statement.executeQuery();
            
            // Verificar si se encontró el número
            if (resultSet.next()) {
                // El número fue encontrado
                
                String codigo = resultSet.getString("codigo");
                String nivel = resultSet.getString("nivel");
                String nombre = resultSet.getString("nombre");
                String correo = resultSet.getString("correo");
                String contrasena = resultSet.getString("contraseña");
                
                System.out.println(codigo);
                System.out.println(nivel);
                System.out.println(nombre);
                System.out.println(correo);
                System.out.println(contrasena);
                 
                model.addRow(new Object[]{codigo, nivel, nombre, correo, contrasena});
                
                conn.close();
                
            } else {
                System.out.println("El usuario no existe");
            }
        } catch (SQLException e) {
        }
       
     }
     
    private void obtener4(DefaultTableModel model) {
        // Configurar la conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/proyecto";
        String usuario = "root";
        String contraseña = "";
        
        int numeroBuscado = 2;
        
        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña)) {
            // Consulta SQL para buscar el número en la tabla
            String sql = "SELECT * FROM usuarios WHERE nivel = ?";
            
            // Crear una sentencia preparada para la consulta SQL
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, numeroBuscado);
            
            // Ejecutar la consulta y obtener los resultados
            ResultSet resultSet = statement.executeQuery();
            
            // Verificar si se encontró el número
            if (resultSet.next()) {
                // El número fue encontrado
                
                String codigo = resultSet.getString("codigo");
                String nivel = resultSet.getString("nivel");
                String nombre = resultSet.getString("nombre");
                String correo = resultSet.getString("correo");
                String contrasena = resultSet.getString("contraseña");
                
                System.out.println(codigo);
                System.out.println(nivel);
                System.out.println(nombre);
                System.out.println(correo);
                System.out.println(contrasena);
                 
                model.addRow(new Object[]{codigo, nivel, nombre, correo, contrasena});
                
                conn.close();
                
            } else {
                System.out.println("El usuario no existe");
            }
        } catch (SQLException e) {
        }
       
     }
     
    private void eliminarUsuarios() {
        int rowCount = modelo.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            Boolean eliminar = (Boolean) modelo.getValueAt(i, 5);
            if (eliminar) {
                int codigo = (int) modelo.getValueAt(i, 0);
                eliminarUsuario(codigo);
                modelo.removeRow(i);
            }
        }
    }

    private void eliminarUsuario(int codigo) {
        // Configurar la conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/proyecto";
        String usuario = "root";
        String contraseña = "";

        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña)) {
            // Crear la sentencia SQL para eliminar el usuario
            String sql = "DELETE FROM usuarios WHERE codigo = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, codigo);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
}

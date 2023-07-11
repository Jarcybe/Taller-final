/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajofinal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class valorhora extends JDialog {
   JTextField horaCarroField,horaMotoField,horaBicicletaField;
    

    public valorhora(Frame png, boolean chat) {
        
        super(png, chat);
                
        setTitle("Formulario de Tarifas");
        
Container x = getContentPane();
        x.setLayout(new FlowLayout());
        
        JLabel horaCarroLabel = new JLabel("Hora para Carro            ");
        horaCarroField = new JTextField(10);

        JLabel horaMotoLabel = new JLabel("Hora para Moto             ");
        horaMotoField = new JTextField(10);

        JLabel horaBicicletaLabel = new JLabel("Hora para Bicicleta      ");
        horaBicicletaField = new JTextField(10);

        JButton guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                guardarTarifa();
                dispose();
            }
        });

        x.add(horaCarroLabel);
        x.add(horaCarroField);
        x.add(horaMotoLabel);
        x.add(horaMotoField);
        x.add(horaBicicletaLabel);
        x.add(horaBicicletaField);
         
        x.add(guardarButton);

        
        setSize(250, 150);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void guardarTarifa() {
        String horaCarro = horaCarroField.getText();
        String horaMoto = horaMotoField.getText();
        String horaBicicleta = horaBicicletaField.getText();

        try {
            // Configurar la conexión a la base de datos
            String url = "jdbc:mysql://localhost:3306/proyecto";
            String usuario = "root";
            String contraseña = "";

            // Establecer la conexión
            Connection conn = DriverManager.getConnection(url, usuario, contraseña);

            // Insertar los valores en la tabla tarifa
            String sql = "INSERT INTO tarifa (cod_t, P_moto, P_carro, P_bici) VALUES (?, ?, ?, ?)";

            // Obtener el próximo valor de cod_t
            int cod_t = obtenerProximoCodT(conn);

            // Preparar la sentencia SQL
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, cod_t);
            statement.setString(2, horaMoto);
            statement.setString(3, horaCarro);
            statement.setString(4, horaBicicleta);

            // Ejecutar la sentencia SQL
            statement.executeUpdate();

            // Cerrar la conexión
            conn.close();

            JOptionPane.showMessageDialog(this, "Tarifa guardada exitosamente!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar la tarifa:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private int obtenerProximoCodT(Connection conn) throws SQLException {
        String sql = "SELECT MAX(cod_t) FROM tarifa";
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        if (resultSet.next()) {
            int maxCodT = resultSet.getInt(1);
            return maxCodT + 1;
        } else {
            return 1;
        }
    }

    
}
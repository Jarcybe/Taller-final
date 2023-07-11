/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajofinal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class baseformulario extends JFrame {
    private JTextField dbNameField;
    private JTextField serverIpField;
    private JTextField usernameField;
    private JPasswordField passwordField;

    private static final String CONFIG_FILE = "config.properties";

    public baseformulario() {
        setTitle("Configuración de Base de Datos");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel dbNameLabel = new JLabel("Nombre de la Base de Datos:");
        dbNameField = new JTextField();

        JLabel serverIpLabel = new JLabel("Dirección IP del Servidor:");
        serverIpField = new JTextField();

        JLabel usernameLabel = new JLabel("Usuario:");
        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordField = new JPasswordField();

        JButton connectButton = new JButton("Conectar");
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conectar();
            }
        });

        panel.add(dbNameLabel);
        panel.add(dbNameField);
        panel.add(serverIpLabel);
        panel.add(serverIpField);
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); // Espacio en blanco
        panel.add(connectButton);

        add(panel);
        
        setSize(350, 250);
        setVisible(true);
    }

    private void conectar() {
        String dbName = dbNameField.getText();
        String serverIp = serverIpField.getText();
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        String url = "jdbc:mysql://" + serverIp + "/" + dbName;

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            JOptionPane.showMessageDialog(this, "Conexión exitosa a la base de datos!");

            guardarConfiguracion(dbName, serverIp, username, password);

            connection.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al conectar a la base de datos:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void guardarConfiguracion(String dbName, String serverIp, String username, String password) {
        Properties properties = new Properties();
        properties.setProperty("dbName", dbName);
        properties.setProperty("serverIp", serverIp);
        properties.setProperty("username", username);
        properties.setProperty("password", password);

        try (OutputStream output = new FileOutputStream(CONFIG_FILE)) {
            properties.store(output, "Configuración de Base de Datos");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   

    private void cargarConfiguracion() {
        File configFile = new File(CONFIG_FILE);
        if (configFile.exists()) {
            Properties properties = new Properties();
            try (InputStream input = new FileInputStream(CONFIG_FILE)) {
                properties.load(input);

                dbNameField.setText(properties.getProperty("dbName"));
                serverIpField.setText(properties.getProperty("serverIp"));
                usernameField.setText(properties.getProperty("username"));
                passwordField.setText(properties.getProperty("password"));
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al cargar la configuración", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
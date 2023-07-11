/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajofinal;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;

/**
 *
 * @author USUARIO
 */
public class buscarfactura extends JDialog{
    
    JLabel vehiculo,nombrecliente,placa,horaentrada,minentrada,horasalida,minsalida,valorhora,horas,totalapagar,numerofactura;
    JTextField cajavehiculo,cajanombrem,cajaplaca,cajahoraentrada,cajaminentrada,cajahorasalida,cajaminsalida,cajavalorhora,cajahoras,cajatotalapagar,cajafactura;
    JButton guardar, cancelar;
    int idFactura;
    int codigo;
    int codS;
    int codigofactura;
    String nombreUsuario="";
    int codSs;
    int HS;
    int MS;
    int vTotal ;
    int totalHoras ;
    int totalPagar ;
    int codE ;
    int codTarifa ;
    int codES ;
    String tipoVehiculoS;
    int puestoP ;
    String placaS ;
    String HE ;
    String ME ;
    int codigoEntrada ;
    
    
    
    
    public buscarfactura(Frame pepe, boolean sarui){
        
        super(pepe, sarui);
        Container c = getContentPane();
        c.setLayout(new FlowLayout());  
        
        numerofactura = new JLabel("NO. factura");
        vehiculo = new JLabel("Vehiculo                       ");
        nombrecliente = new JLabel("Nombre cliente            ");
        placa = new JLabel("Placa                            ");
        horaentrada = new JLabel("Hora entrada                ");
        minentrada = new JLabel("Min entrada                   ");
        horasalida = new JLabel("Hora salida                    ");
        minsalida = new JLabel("Min salida                     ");
        valorhora = new JLabel("Valor hora                     ");
        horas = new JLabel("Horas                             ");
        totalapagar = new JLabel("Total a pagar                ");
        
        
        cajafactura = new JTextField(8);
        cajavehiculo = new JTextField(10);
        cajanombrem = new JTextField(10);
        cajaplaca = new JTextField(10);
        cajahoraentrada = new JTextField(10);
        cajaminentrada = new JTextField(10);
        cajahorasalida = new JTextField(10);
        cajaminsalida = new JTextField(10);
        cajavalorhora = new JTextField(10);
        cajahoras = new JTextField(10);
        cajatotalapagar = new JTextField(10);
        
        
        guardar = new JButton("buscar");
        cancelar = new JButton("cancelar");
        
        
        c.add(numerofactura);
        c.add(cajafactura);
        c.add(guardar);
        c.add(vehiculo);
        c.add(cajavehiculo);
        c.add(nombrecliente);
        c.add(cajanombrem);
        c.add(placa);
        c.add(cajaplaca);
        c.add(horaentrada);
        c.add(cajahoraentrada);
        c.add(minentrada);
        c.add(cajaminentrada);
        c.add(horasalida);
        c.add(cajahorasalida);
        c.add(minsalida);
        c.add(cajaminsalida);
        c.add(valorhora);
        c.add(cajavalorhora);
        c.add(horas);
        c.add(cajahoras);
        c.add(totalapagar);
        c.add(cajatotalapagar);
        
        c.add(cancelar);
        
        guardar.addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
            
            System.out.println(nombreUsuario);
        codigofactura = Integer.parseInt(cajafactura.getText());
        obtenerDatosFactura(codigofactura);
        nombreUsuario = obtenerNombreUsuarioPorCodigo(codigo);
        cajanombrem.setText(nombreUsuario);
        obtenerDatosSalidaPorCodigo(codS);
        
        obtenerDatosEntradaPorCodigo(codS);
        
        cajahorasalida.setText(String.valueOf(HS));
        cajaminsalida.setText(String.valueOf(MS)) ;
        cajahoras.setText(String.valueOf(vTotal)) ;
        cajatotalapagar.setText(String.valueOf(totalPagar)) ;
        cajavalorhora.setText(String.valueOf(totalHoras));
        cajaplaca.setText(placaS);
        cajavehiculo.setText(tipoVehiculoS);
        cajahoraentrada.setText(HE);
        cajaminentrada.setText(ME);
        }
        });
        
        
        cancelar.addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent s){
        
        dispose();
        }
        
        });
        
        setSize(250, 350);
         setLocationRelativeTo(null);
        setVisible(true);
    }
    
 public void obtenerDatosFactura(int codigoFactura) {
    // Configurar la conexión a la base de datos
    String url = "jdbc:mysql://localhost:3306/proyecto";
    String usuario = "root";
    String contraseña = "";

    try (Connection conn = DriverManager.getConnection(url, usuario, contraseña)) {
        // Consulta SQL para obtener los datos de la factura por código
        String sql = "SELECT id_factura, codigo, cod_s FROM factura WHERE id_factura = ?";

        // Crear una sentencia preparada para la consulta SQL
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, codigoFactura);

        // Ejecutar la consulta y obtener los resultados
        ResultSet resultSet = statement.executeQuery();

        // Verificar si se encontraron resultados
        if (resultSet.next()) {
            // Obtener los datos de las columnas
             idFactura = resultSet.getInt("id_factura");
             codigo = resultSet.getInt("codigo");
             codS = resultSet.getInt("cod_s");


            conn.close();
            return;
        } else {
          //  JOptionPane.showMessageDialog(null, "->No se encontraron datos en la tabla factura para el código de factura: " + codigoFactura);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al obtener datos de factura: " + e.getMessage());
    }
}
 
 public String obtenerNombreUsuarioPorCodigo(int codigo) {
    

    String url = "jdbc:mysql://localhost:3306/proyecto";
    String usuario = "root";
    String contraseña = "";

    try (Connection conn = DriverManager.getConnection(url, usuario, contraseña)) {
        String sql = "SELECT nombre FROM usuarios WHERE codigo = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, codigo);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            nombreUsuario = resultSet.getString("nombre");
        }

        conn.close();
    } catch (SQLException e) {
    }

    return nombreUsuario;
}
 
 public void obtenerDatosSalidaPorCodigo(int codigo) {
    String url = "jdbc:mysql://localhost:3306/proyecto";
    String usuario = "root";
    String contraseña = "";

    try (Connection conn = DriverManager.getConnection(url, usuario, contraseña)) {
        String sql = "SELECT cod_s, HS, MS, v_total, T_horas, t_pagar, cod_e, cod_tarifa FROM salida WHERE cod_s = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, codigo);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
             codSs = resultSet.getInt("cod_s");
             HS = resultSet.getInt("HS");
             MS = resultSet.getInt("MS");
             vTotal = resultSet.getInt("v_total");
             totalHoras = resultSet.getInt("T_horas");
             totalPagar = resultSet.getInt("t_pagar");
             codE = resultSet.getInt("cod_e");
             codTarifa = resultSet.getInt("cod_tarifa");

        } else {
            System.out.println("No se encontraron datos en la tabla salida para el código: " + codigo);
        }

        conn.close();
    } catch (SQLException e) {
    }
}


public void obtenerDatosEntradaPorCodigo(int codigo) {
    String url = "jdbc:mysql://localhost:3306/proyecto";
    String usuario = "root";
    String contraseña = "";

    try (Connection conn = DriverManager.getConnection(url, usuario, contraseña)) {
        String sql = "SELECT cod_E, tipo_vehiculo, puesto_p, placa, HE, ME, codigo FROM entrada WHERE cod_E = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, codigo);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
             codES = resultSet.getInt("cod_E");
             tipoVehiculoS = resultSet.getString("tipo_vehiculo");
             puestoP = resultSet.getInt("puesto_p");
             placaS = resultSet.getString("placa");
             HE = resultSet.getString("HE");
             ME = resultSet.getString("ME");
             codigoEntrada = resultSet.getInt("codigo");

        } else {
           // System.out.println("No se encontraron datos en la tabla entrada para el código: " + codigo);
        }

        conn.close();
    } catch (SQLException e) {
      //  e.printStackTrace();
    }
}

    
}

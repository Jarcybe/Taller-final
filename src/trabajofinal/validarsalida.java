/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajofinal;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.JOptionPane;


/**
 *
 * @author USUARIO
 */
public class validarsalida extends JDialog{
    
    JLabel vehiculo,nombrecliente,placa,horaentrada,minentrada,horasalida,minsalida,valorhora,horas,totalapagar;
    JTextField cajavehiculo,cajanombrem,cajaplaca,cajahoraentrada,cajaminentrada,cajahorasalida,cajaminsalida,cajavalorhora,cajahoras,cajatotalapagar;
    JButton guardar, cancelar;
    String nombreaencontrar;
    
    int codeus=0;
    int codSalis= 0;
    int codEntrada=0;
    int codTarifa= 0;
    int codefac = 0;
    int codUsu = 0;    
    
    int tarifa=0, mins, hos, precioC =0, precioM=0, precioB=0, THORA=0, valor, Tpagar=0;
    String codigo;
    String vehiculos ;
    String puesto ;
    String placas ;
    String HE ;
    String ME;
    String codigos ;
    String placaExtraida;
    
    
    
    public validarsalida( Frame no, boolean jodas, String HS, String MS, String placas){
        super(no, jodas);
        Container c = getContentPane();
        c.setLayout(new FlowLayout()); 
        
        //C-13-ASC123-15-13
        
        hos = Integer.parseInt(HS);
        
        vehiculo = new JLabel("Vehiculo              ");
        nombrecliente = new JLabel("Nombre cliente   ");
        placa = new JLabel("Placa                     ");
        horaentrada = new JLabel("Hora entrada        ");
        minentrada = new JLabel("Min entrada          ");
        horasalida = new JLabel("Hora salida           ");
        minsalida = new JLabel("Min salida            ");
        valorhora = new JLabel("Valor hora            ");
        horas = new JLabel("Horas                    ");
        totalapagar = new JLabel("Total a pagar        ");
        
        
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
        
        
        guardar = new JButton("guardar");
        cancelar = new JButton("cancelar");
        
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
        c.add(guardar);
        c.add(cancelar);
        
         placaExtraida = placas;
        System.out.print(placaExtraida + "\n");
        
        System.out.print(placas);
        
        obtener1();
        
        encontrarnombre();
        
        encontrarTarifa();
        
        tarifa();
        
        resultados();
        
        cajavehiculo.setText(vehiculos);
        cajanombrem.setText(nombreaencontrar);
        cajaplaca.setText(placaExtraida);
        cajahoraentrada.setText(HE);
        cajaminentrada.setText(ME);
        
        cajahorasalida.setText(HS);
        cajaminsalida.setText(MS);
        
        String valorhor = String.valueOf(valor);
        cajavalorhora.setText(valorhor);
        
        String totalh = String.valueOf(THORA);
        cajahoras.setText(totalh);
        
        String plata = String.valueOf(Tpagar);
        cajatotalapagar.setText(plata);
        
        guardar.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent barto){
        
         mins = Integer.parseInt(MS);
            mandar(codSalis, hos, mins,  valor,THORA, Tpagar, codEntrada, tarifa);
            
            codSalis++;
            
            mandarfactura(codefac, codSalis, codSalis );
            
        codefac++;
        }
        });
        
        setSize(220, 350);
         setLocationRelativeTo(null);
        setVisible(true);
    }
    
    
   
    
         public void encontrarnombre(){
        String url = "jdbc:mysql://localhost:3306/proyecto";
        String usuario = "root";
        String contraseña = "";
        
        
        
        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña)) {
            // Consulta SQL para buscar el número en la tabla
            String sql = "SELECT * FROM usuarios WHERE nombre = ?";
            
            // Crear una sentencia preparada para la consulta SQL
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, codeus);
            
            // Ejecutar la consulta y obtener los resultados
            ResultSet resultSet = statement.executeQuery();
            
            // Verificar si se encontró el número
            if (resultSet.next()) {
                // El nombre fue encontrado
                
                
                 nombreaencontrar = resultSet.getString("nombre");
                 System.out.println("->" +nombreaencontrar);
                
                conn.close();
                
            } else {
                System.out.println("El usuario no existe");
            }
        } catch (SQLException e) {
            
         
            System.out.println(e);
        }
        
        
    }
         
   public void obtener1() {
        // Configurar la conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/proyecto";
        String usuario = "root";
        String contraseña = "";
        
       
        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña)) {
            // Consulta SQL para buscar el número en la tabla
            String sql = "SELECT * FROM entrada WHERE placa = ?";
            
            // Crear una sentencia preparada para la consulta SQL
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, placaExtraida);
            
            // Ejecutar la consulta y obtener los resultados
            ResultSet resultSet = statement.executeQuery();
            
            // Verificar si se encontró el número
            if (resultSet.next()) {
                // El número fue encontrado
                
                 codigo = resultSet.getString("cod_E");
                 vehiculos = resultSet.getString("tipo_vehiculo");
                 puesto = resultSet.getString("puesto_p");
                 placas = resultSet.getString("placa");
                 HE = resultSet.getString("HE");
                 ME = resultSet.getString("ME");
                 codigos = resultSet.getString("codigo");
                 
                System.out.print("\n"+codigo+"\n");
                System.out.print(vehiculos+"\n");
                System.out.print(puesto+"\n");
                System.out.print("1"+placas+"\n");
                System.out.print(HE+"\n");
                System.out.print(ME+"\n");
                System.out.print(codigos+"\n");
                
                conn.close();
                return;
                
            } else {
                 JOptionPane.showMessageDialog(null, "???????????El usuario no existe");
            }
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, "Error tipo:" + e);
        }
        
    
       
     }
   
    public static String extraerPlaca(String placa) {
    String[] partes = placa.split("-");
    if (partes.length >= 3) {
        return partes[2];
        
    } else {
        return "";
    }
}
    
    public void encontrarTarifa(){
    
          String url = "jdbc:mysql://localhost:3306/proyecto";
         String usuario = "root";
         String contraseña = "";
         
         
         try(Connection conn = DriverManager.getConnection(url, usuario, contraseña)){
         String sql = "SELECT MAX(cod_t) AS cod_t FROM tarifa";
         
        Statement pepin = conn.createStatement();
       
        ResultSet aver = pepin.executeQuery(sql);
        
       if(aver.next()){
       
       tarifa = aver.getInt("cod_t");
       
       System.out.println("Tarifa es:" + tarifa);
       
        conn.close();
       }   
         }catch(Exception d){
         
         JOptionPane.showMessageDialog(null, d);
        
         }
    }
    
    public void tarifa(){
    
     String url = "jdbc:mysql://localhost:3306/proyecto";
     String usuario = "root";
     String contraseña = "";
    
     try(Connection conn = DriverManager.getConnection(url, usuario, contraseña)){
         String sql = "SELECT * FROM tarifa WHERE cod_t = ?";

        PreparedStatement evilpepin = conn.prepareStatement(sql);
       
        evilpepin.setInt(1, tarifa);
        ResultSet aver = evilpepin.executeQuery();
        
       if(aver.next()){
       
       precioC = aver.getInt("P_carro");
       precioM = aver.getInt("P_moto");
       precioB = aver.getInt("P_bici");
       
       System.out.println("prrecio carro: " + precioC +"\n"
               + "precio moto: "+ precioM + "\n"
                       + "precio bicicleta: " + precioB );
       
        conn.close();
        return;
       }   
       else{JOptionPane.showMessageDialog(null, "Tarfia no encontrada");}
         }catch(Exception d){
         
         JOptionPane.showMessageDialog(null, "Error de tarifa "+d);
        
         }
    
    }
    
    public void resultados(){
      
        int HOEN = Integer.parseInt(HE);
        
        int HOSA = hos;//Integer.parseInt(cajahorasalida.getText());
        
       
       if("Carro".equals(vehiculos)){
       
       valor = precioC;
       
       }
       
       if("Moto".equals(vehiculos)){
       
       valor = precioM;
       
       }
       
       if("Bicicleta".equals(vehiculos)){
       
       valor = precioB;
       }
       
       THORA = HOSA - HOEN; 
       
       Tpagar = THORA * valor;
       
    
    }
    
    
     public void mandar(int codsalida, int HS, int MS, int valorH,int totalHoras, int totalpagar, int codEntrada, int codTarifa){
        String url = "jdbc:mysql://localhost:3306/proyecto";
        String usuario = "root";
        String contraseña = "";

        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña)) {
            String sql = "INSERT INTO salida (cod_s, HS, MS, v_total, T_horas, t_pagar,cod_e, cod_tarifa) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, codsalida);
            statement.setInt(2, HS);
            statement.setInt(3, MS);
            statement.setInt(4, valorH);
            statement.setInt(5, totalHoras);
            statement.setInt(6, totalpagar);
            statement.setInt(7, codEntrada);
            statement.setInt(8, codTarifa);
            
            encontrar5(codsalida, HS, MS,  valorH,totalHoras, totalpagar, codEntrada, codTarifa);

            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Datos insertados correctamente en la base de datos.");
            } else {
                System.out.println("No se pudieron insertar los datos en la base de datos.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     

public void mandarfactura(int codfa, int codus, int fac) {
    String url = "jdbc:mysql://localhost:3306/proyecto";
    String usuario = "root";
    String contraseña = "";

    try (Connection conn = DriverManager.getConnection(url, usuario, contraseña)) {
        String insertarFactura = "INSERT INTO factura (id_factura, codigo, cod_s) "
                + "VALUES (?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(insertarFactura);

        statement.setInt(1, codfa);
        statement.setInt(2, fac);
        statement.setInt(3, codus);
       

        int filasAfectadas = statement.executeUpdate();

        if (filasAfectadas > 0) {
            JOptionPane.showMessageDialog(null, "Datos insertados correctamente en la base de datos.");
        } else {
            JOptionPane.showMessageDialog(null, "No se pudieron insertar los datos en la base de datos.");
        }

        conn.close();
    } catch (SQLException e) {
    }
}



    public  void encontrar5(int codsalida, int HSS, int MSS, int valorH,int totalHoras, int totalpagar, int codEntrada, int codTarifa) {
        // Datos de conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/proyecto";
        String usuario = "root";
        String contraseña = "";

        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Establecer conexión con la base de datos
            conexion = DriverManager.getConnection(url, usuario, contraseña);

            // Consulta SQL para obtener los datos de las columnas
            String consulta = "SELECT codigos, tipo_vehiculo, puesto_p, placa, HE, ME, cod_usuario, HS, MS, v_total, T_horas, t_pagar, nivel FROM registro ORDER BY codigos ASC";
            statement = conexion.prepareStatement(consulta);

            // Ejecutar la consulta
            resultSet = statement.executeQuery();

            // Recorrer el resultado y almacenar los datos en variables
            int codigo = 0;
            while (resultSet.next()) {
                codigo = resultSet.getInt("codigos");
                String tipoVehiculo = resultSet.getString("tipo_vehiculo");
                int puestoP = resultSet.getInt("puesto_p");
                String placa = resultSet.getString("placa");
                int HE = resultSet.getInt("HE");
                int ME = resultSet.getInt("ME");
                int codUsuario = resultSet.getInt("cod_usuario");
                int HS = resultSet.getInt("HS");
                int MS = resultSet.getInt("MS");
                double vTotal = resultSet.getDouble("v_total");
                double THoras = resultSet.getDouble("T_horas");
                double tPagar = resultSet.getDouble("t_pagar");
                int nivel = resultSet.getInt("nivel");
                
                mandar2(codigo,tipoVehiculo,puestoP,placa,HE,ME,codUsuario,HSS,MSS,valorH,totalHoras,totalpagar,codTarifa);
                
            }

            // El siguiente código generará el nuevo código para la próxima inserción
            int nuevoCodigo = codigo + 1;
            System.out.println("Nuevo código para la próxima inserción: " + nuevoCodigo);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar conexiones y liberar recursos
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


public void mandar2(int codigo, String tipoVehiculo, int puesto, String placa, int horaEntrada, int minutosEntrada, int codigoUsuario, int HS, int MS, int valorH,int totalHoras, int totalpagar, int codTarifa) {
        String url = "jdbc:mysql://localhost:3306/proyecto";
        String usuario = "root";
        String contraseña = "";

        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña)) {
            // Consulta SQL para actualizar los datos en la tabla
            String sql = "UPDATE registro SET tipo_vehiculo = ?, puesto_p = ?, placa = ?, HE = ?, ME = ?, HS = ?, MS = ?, v_total = ?, T_horas = ?, t_pagar = ?, cod_usuario = ? WHERE codigo = ?";

            // Crear una sentencia preparada para la consulta SQL
            PreparedStatement statement = conn.prepareStatement(sql);
            
            
            statement.setInt(1, codigo);
            statement.setString(2, tipoVehiculo);
            statement.setInt(3, puesto);
            statement.setString(4, placa);
            statement.setInt(5, horaEntrada);
            statement.setInt(6, minutosEntrada);
            statement.setInt(7, HS);
            statement.setInt(8, MS);
            statement.setInt(9, valorH);
            statement.setInt(10, totalHoras);
            statement.setInt(11, totalpagar);
            statement.setInt(12, codigoUsuario);
            statement.setInt(13, codigo);

            // Ejecutar la consulta y obtener el número de filas afectadas
            int filasAfectadas = statement.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Datos actualizados correctamente en la base de datos.");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudieron actualizar los datos en la base de datos.");
            }
        } catch (SQLException e) {
        }
    
}
    
    
}
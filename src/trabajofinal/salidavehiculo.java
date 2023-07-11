/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajofinal;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static javax.swing.text.html.parser.DTDConstants.MS;

/**
 *
 * @author USUARIO
 */

public class salidavehiculo extends JDialog{
    
    JLabel placa;
    JTextField cajaplaca;
    JButton validar, cancelar;
    
  String prueba1;
    
   
    public salidavehiculo(Frame chu, boolean cha){
        
        super(chu, cha);
        
        Container c = getContentPane();
        c.setLayout(new FlowLayout());
        
        placa = new JLabel("V-PLACA-HS-MS ");
      
        cajaplaca = new JTextField(10);
        
        validar = new JButton("validar ");
        cancelar = new JButton("cancelar");
       
        c.add(placa);
        c.add(cajaplaca);
        c.add(validar);
        c.add(cancelar);
        
        validar.addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
    
         validarPlaca(cajaplaca.getText());
        dispose();
        }
        });
        
         cancelar.addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
    
        dispose();
        
        }
        });

        setSize(270, 100);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public static void validarPlaca(String placas) {
   // Extraer las partes de la placa
    String[] partes = placas.split("-");
    String tipoVehiculo = partes[0];
    String puestoEstacionamiento = partes[1];
    String placaNumerica = partes[2];
    String horaSalida = partes[3];
    String minutoSalida = partes[4];

// Validar el tipo de vehículo
if (tipoVehiculo.equals("M")) {
    // Validar el puesto de estacionamiento
    int puesto = Integer.parseInt(puestoEstacionamiento);
    if (puesto < 1 || puesto > 10) {
        System.out.println("Puesto de estacionamiento inválido para tipo M");
        return;
    }
    
    // Validar la placa
    if (!placaNumerica.matches("[A-Za-z]{3}\\d{2}[A-Za-z]")) {
        System.out.println("Placa inválida para tipo M");
        return;
    }
} else if (tipoVehiculo.equals("C")) {
    // Validar el puesto de estacionamiento
    int puesto = Integer.parseInt(puestoEstacionamiento);
    if (puesto < 11 || puesto > 20) {
        System.out.println("Puesto de estacionamiento inválido para tipo C");
        return;
    }
    
    // Validar la placa
    if (!placaNumerica.matches("[A-Za-z]{3}\\d{3}")) {
        System.out.println("Placa inválida para tipo C");
        return;
    }
} else if (tipoVehiculo.equals("B")) {
    // Validar el puesto de estacionamiento
    int puesto = Integer.parseInt(puestoEstacionamiento);
    if (puesto < 21 || puesto > 25) {
        System.out.println("Puesto de estacionamiento inválido para tipo B");
        return;
    }
    
    // Validar la placa
    if (!placaNumerica.equals("0000")) {
        System.out.println("Placa inválida para tipo B");
        return;
    }
} else {
    System.out.println("Tipo de vehículo inválido");
    return;
}

// Validar la hora de entrada y los minutos de entrada
int hora = Integer.parseInt(horaSalida);
int minutos = Integer.parseInt(minutoSalida);
if (hora < 0 || hora > 23 || minutos < 0 || minutos > 59) {
    System.out.println("Hora de entrada o minutos de entrada inválidos");
    return;
}
    
    BaseDeDatos(horaSalida, minutoSalida, placaNumerica);
   
     
   
}
    
public static void BaseDeDatos( String horaSalida, String minutoSalida, String placa) {

    new validarsalida( null, true ,horaSalida,  minutoSalida,  placa);
} 
}

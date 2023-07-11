/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajofinal;

import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USUARIO
 */
public class ingresarvehiculo extends JDialog{
    
    JLabel vehiculo,registroplaca;
    JTextField cajaregistroplaca;
    JButton mostrar, mostrartodo, validar, cancelar;
    JComboBox veh;
    JTable table;
    JScrollPane rollo;
    DefaultTableModel model;
    JTextArea textArea;
    int numeroc;
    
    String horad ="";
    
    public ingresarvehiculo(Frame por, boolean que){
        
        super(por, que);
        
        Container c = getContentPane();
        c.setLayout(new FlowLayout());
        
        vehiculo = new JLabel("vehiculo ");
        registroplaca = new JLabel("V-P-PLACA-HE-ME");
        
        
        cajaregistroplaca = new JTextField(10);
        
        
        mostrar = new JButton("mostrar");
        cancelar = new JButton("cancelar");
        mostrartodo = new JButton("mostrar todos");
        validar = new JButton("validar");
        
        String[] vehiculos = {"moto","carro","bicicleta"};
        veh = new JComboBox(vehiculos);
       

        // Crear un TextArea
        textArea = new JTextArea(7,27);
        textArea.setEditable(false); // Hacer que el TextArea sea de solo lectura
        rollo = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        // Generar los números y agregarlos al TextArea
         for (int i = 1; i <= 25; i++) {
            textArea.append(Integer.toString(i));
            if (i % 10 == 0) {
                textArea.append("\n");
            } else {
                textArea.append("     ");
            }
        }


        c.add(vehiculo);
        c.add(veh);
        c.add(mostrar);
        c.add(mostrartodo);
        c.add(rollo);
        c.add(registroplaca);
        c.add(cajaregistroplaca);
        c.add(validar);
        c.add(cancelar);
        
        validar.addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
         
         
         System.out.println(cajaregistroplaca.getText());
        validarPlaca(cajaregistroplaca.getText());
        dispose();
        
        }
        });
        
        cancelar.addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
         
   dispose();
        }
        });
        
        setSize(410, 250);
         setLocationRelativeTo(null);
        setVisible(true);
    }
    
    
    
    public void validar(JTextArea textArea, int number){
        
        if(numeroc<=10){
            cambiar(textArea,number);
        }
        if(numeroc<=20 && numeroc >=11){
            cambiar2(textArea,number);
        }
        if(numeroc >=21 && numeroc <= 25){
            cambiar3(textArea,number);
        }
    }
    
     public static void cambiar(JTextArea textArea, int number) {
      String currentText = textArea.getText();

    // Crear una expresión regular para buscar el número específico
    String regex = "\\b" + number + "\\b";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(currentText);

    // Reemplazar solo el número deseado en el texto
    String newText = matcher.replaceAll("m");

    // Actualizar el JTextArea con el texto modificado
    textArea.setText(newText);
    
    }
     
      public static void cambiar2(JTextArea textArea, int number) {
      String currentText = textArea.getText();

    // Crear una expresión regular para buscar el número específico
    String regex = "\\b" + number + "\\b";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(currentText);

    // Reemplazar solo el número deseado en el texto
    String newText = matcher.replaceAll("c");

    // Actualizar el JTextArea con el texto modificado
    textArea.setText(newText);
    
    }
      
       public static void cambiar3(JTextArea textArea, int number) {
      String currentText = textArea.getText();

    // Crear una expresión regular para buscar el número específico
    String regex = "\\b" + number + "\\b";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(currentText);

    // Reemplazar solo el número deseado en el texto
    String newText = matcher.replaceAll("b");

    // Actualizar el JTextArea con el texto modificado
    textArea.setText(newText);
    
    }
    
  
    
    
public static void validarPlaca(String placa) {// Verificar el formato de la placa
// Extraer las partes de la placa
String[] partes = placa.split("-");
String tipoVehiculo = partes[0];
String puestoEstacionamiento = partes[1];
String placaNumerica = partes[2];
String horaEntrada = partes[3];
String minutosEntrada = partes[4];

// Validar el tipo de vehículo
if (tipoVehiculo.equals("M")) {
    // Validar el puesto de estacionamiento
    int puesto = Integer.parseInt(puestoEstacionamiento);
    if (puesto < 11 || puesto > 20) {
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
    if (puesto < 1 || puesto > 10) {
        System.out.println("Puesto de estacionamiento inválido para tipo C");
        return;
    }
    
    // Validar la placa
    if (!placaNumerica.matches("[A-Za-z]{3}\\d{3}")) {
        JOptionPane.showMessageDialog(null, "Placa inválida para tipo C");
        return;
    }
} else if (tipoVehiculo.equals("B")) {
    // Validar el puesto de estacionamiento
    int puesto = Integer.parseInt(puestoEstacionamiento);
    if (puesto < 21 || puesto > 25) {
      JOptionPane.showMessageDialog(null, "Puesto de estacionamiento inválido para tipo B");
        return;
    }
    
    // Validar la placa
    if (!placaNumerica.equals("0000")) {
      JOptionPane.showMessageDialog(null,"Placa inválida para tipo B");
        return;
    }
} else {
    System.out.println("Tipo de vehículo inválido");
    return;
}

// Validar la hora de entrada y los minutos de entrada
int hora = Integer.parseInt(horaEntrada);
int minutos = Integer.parseInt(minutosEntrada);
if (hora < 0 || hora > 23 || minutos < 0 || minutos > 59) {
    System.out.println("Hora de entrada o minutos de entrada inválidos");
    return;
}
    // Llamar a la función para agregar los datos a la base de datos
    agregarDatosBaseDeDatos(tipoVehiculo, Integer.parseInt(puestoEstacionamiento), placa, horaEntrada, minutosEntrada);
    

}



public static void agregarDatosBaseDeDatos(String tipoVehiculo, int puesto, String placa, String horaEntrada, String minutosEntrada) {

    new detalledeingreso( null, true ,tipoVehiculo,  puesto,  placa,  horaEntrada,  minutosEntrada);
}
    
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package trabajofinal;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author USUARIO
 */
public class Trabajofinal extends JFrame{
    JMenuBar barra;
    JMenu basedatos, login, usuarios,vehiculos;
    JMenuItem crearbase, borrarbase, iniciarse, cerrarse, crear, modificar, visualizar, ingresar,salida, buscaregistro, modificaregistro, buscarfactura, valorhora;
    Boolean log = true;
    
    
    
    public Trabajofinal(){
        
        
        barra = new JMenuBar();
        setJMenuBar(barra);
        
        basedatos = new JMenu("base de datos");
        login = new JMenu("login");
        usuarios = new JMenu("usuarios");
        vehiculos = new JMenu("vehiculos");
        
        
        crearbase = new JMenuItem("crear base de datos");
        borrarbase = new JMenuItem("eliminar base de datos");
        iniciarse = new JMenuItem("iniciar sesion");
        cerrarse = new JMenuItem("cerrar sesion");
        crear = new JMenuItem("crear");
        modificar = new JMenuItem("modificar");
        visualizar = new JMenuItem("visualizar");
        ingresar = new JMenuItem("ingresar");
        salida = new JMenuItem("salida");
        buscaregistro = new JMenuItem("buscar registros");
        modificaregistro = new JMenuItem("modificar registro");
        buscarfactura = new JMenuItem("buscar factura");
        valorhora = new JMenuItem("valor hora");
        
        
        basedatos.add(crearbase);
        basedatos.add(borrarbase);
        login.add(iniciarse);
        login.add(cerrarse);
        usuarios.add(crear);
        usuarios.add(modificar);
        usuarios.add(visualizar);
        vehiculos.add(ingresar);
        vehiculos.add(salida);
        vehiculos.add(buscaregistro);
        vehiculos.add(modificaregistro);
        vehiculos.add(buscarfactura);
        vehiculos.add(valorhora);
        barra.add(basedatos);
        barra.add(login);
        barra.add(usuarios);
        barra.add(vehiculos);
        
        
        iniciarse.addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
    
        login();
        
        }
        });
        
        crear.addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
            
        crearusu();
        }
        });
        
        modificar.addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
    
        modificarusu();
        
        }
        });
        
        visualizar.addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
    
        visueliusuarios();
        
        }
        });
        
        ingresar.addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
    
        ingresarvehiculo();
        
        }
        });
        
        salida.addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
    
        salidavehiculo();
        
        }
        });
        
        buscarfactura.addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
    
        buscarfactura();
        
        }
        });
        
        crearbase.addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
    
        crearbase();
        
        }
        });
        
        borrarbase.addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
    
        borrarbase();
        
        }
        });
        
        valorhora.addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
    
        valorhora();
        }
        });
        
        buscaregistro.addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
    
        buscaregistros();
        
        }
        });
        
        cerrarse.addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
    
        cerrarse();
        
        }
        });
        
        modificaregistro.addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
    
        modiregistros();
        
        }
        });
        
        setSize(350, 250);
        setVisible(true);
    }
    
    
    
    public void login(){
        new login(null,true);
    }

    public void crearusu(){
       new crearusuario(null, true);
        
    }
    
    public void modificarusu(){
        
    if (comprobarArchivoLog() == true ) {
        if( leerArchivoLog() == true){
        
        new modificarusuario(null, true);
        }
        
    } else {
        login();
    }
        
       
    }
    
    
    
    public void visueliusuarios(){
        
        if (comprobarArchivoLog() == true ) {
        if( leerArchivoLog() == true){
        
        new visuelimiusuarios(null, true);
        }
        
    } else {
        login();
    }
        
        
    }
    
    public void ingresarvehiculo(){
        
    if (comprobarArchivoLog() == true ) {
        if( leerArchivoLog2() == true){
        
        new ingresarvehiculo(null, true);
        }
        
    } else {
        login();
    } 
        
        
        
    }
    
    public void salidavehiculo(){
        
    if (comprobarArchivoLog() == true ) {
        if( leerArchivoLog2() == true){

        new salidavehiculo(null, true);
        }
    } else {
        login();
    } 
   
    }
    
    public void buscarfactura(){
        
    if (comprobarArchivoLog() == true ) {
        if( leerArchivoLog2() == true){
        
        new buscarfactura(null, true);
        }
        
        
    } else {
        login();
    }
        
        
    }
    
    public void crearbase(){
        new crearbase();
    }
    
    public void borrarbase(){
        new borrarbase();
    }
    
    public void valorhora(){
        if (comprobarArchivoLog() == true ) {
        if( leerArchivoLog() == true){
        
        new valorhora(null, true);
        }
        
    } else {
        login();
    }
      
    }
    
    public void buscaregistros(){
         if (comprobarArchivoLog() == true ) {
        if( leerArchivoLog() == true){
        
        new buscaregistros(null, true);
        }
        
    } else {
        login();
    }      
    }
    
    public void cerrarse(){
        new CerrarSesion();
    }
    
        public void modiregistros(){
         if (comprobarArchivoLog() == true ) {
        if( leerArchivoLog() == true){
        
        new modificaregistros(null, true);
        }
        
    } else {
        login();
    }      
    }
    
    
    public boolean comprobarArchivoLog() {
    String archivoLog = "log";

    File file = new File(archivoLog);
    boolean existe = file.exists();

    if (existe) {
        return true;
    } else {
        
        return false;
    }
}
    
    
public boolean leerArchivoLog() {
    String archivoLog = "log";

    try (BufferedReader br = new BufferedReader(new FileReader(archivoLog))) {
        String linea = br.readLine();
        if (linea != null) {
            int numero = Integer.parseInt(linea);
            if (numero <= 0) {
                return true;
            } else {
                return false;
            }
        } else {
            System.out.println("Error: el archivo 'log' está vacío.");
        }
    } catch (IOException | NumberFormatException e) {
        System.out.println("Error al leer el archivo 'log': " + e.getMessage());
    }
    
    return false;
}

public boolean leerArchivoLog2() {
    String archivoLog = "log";

    try (BufferedReader br = new BufferedReader(new FileReader(archivoLog))) {
        String linea = br.readLine();
        if (linea != null) {
            int numero = Integer.parseInt(linea);
            if (numero <= 1) {
                return true;
            } else {
                return false;
            }
        } else {
            System.out.println("Error: el archivo 'log' está vacío.");
        }
    } catch (IOException | NumberFormatException e) {
        System.out.println("Error al leer el archivo 'log': " + e.getMessage());
    }
    
    return false;
}

public boolean leerArchivoLog3() {
    String archivoLog = "log";

    try (BufferedReader br = new BufferedReader(new FileReader(archivoLog))) {
        String linea = br.readLine();
        if (linea != null) {
            int numero = Integer.parseInt(linea);
            if (numero <= 0 && numero >= 2) {
                return true;
            } else {
                return false;
            }
        } else {
            System.out.println("Error: el archivo 'log' está vacío.");
        }
    } catch (IOException | NumberFormatException e) {
        System.out.println("Error al leer el archivo 'log': " + e.getMessage());
    }
    
    return false;
}
    
    public static void main(String[] args) {
        new Trabajofinal();
    }
    
}

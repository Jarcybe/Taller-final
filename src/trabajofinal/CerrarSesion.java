/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajofinal;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
/**
 *
 * @author USUARIO
 */
public class CerrarSesion {
    
    
    
    public CerrarSesion() {
        String archivoLog = "log";

        Path path = Paths.get(archivoLog);

        if (Files.exists(path)) {
            try {
                Files.delete(path);
                System.out.println("Sesión cerrada. Archivo 'log' eliminado.");
            } catch (IOException e) {
                System.out.println("Error al cerrar la sesión. No se pudo eliminar el archivo 'log'.");
            }
        } else {
            System.out.println("No hay una sesión iniciada.");
        }
    }

}

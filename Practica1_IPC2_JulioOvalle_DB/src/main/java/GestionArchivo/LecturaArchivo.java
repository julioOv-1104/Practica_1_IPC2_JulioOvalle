package GestionArchivo;

import InterfazGrafica.MenuPrincipal;
import java.io.*;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.SwingUtilities;

public class LecturaArchivo implements Runnable {

    private File archivo;
    private int velocidad;
    private MenuPrincipal menu;

    public LecturaArchivo(String ruta, int velocidad, MenuPrincipal menu) {
        this.archivo = new File(ruta);
        this.velocidad = velocidad;
        this.menu = menu;
    }

    public void leerArchivo(String linea) {

        
    }

    @Override
    public void run() {

        try {
            // Crear un FileReader y envolverlo en un BufferedReader
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);

            String linea;
            System.out.println("");
            System.out.println("Contenido del archivo:");

            // Leer línea por línea
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
                String texto = linea;
                
                

                SwingUtilities.invokeLater(() -> {
                    menu.getTxtArea().append(texto+"\n");
                });

                Thread.sleep(velocidad); // Cambia segun la velocidad que asigna el usuario

            }

            // Cerrar el lector
            br.close();
            fr.close();

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } catch (InterruptedException er) {
            System.out.println("El hilo se detuvo");
        }

    }

}

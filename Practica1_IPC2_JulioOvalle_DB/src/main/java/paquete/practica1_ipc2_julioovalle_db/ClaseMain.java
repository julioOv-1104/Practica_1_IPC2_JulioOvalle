package paquete.practica1_ipc2_julioovalle_db;
import InterfazGrafica.MenuPrincipal;
import java.sql.*;
import java.util.Scanner;

public class ClaseMain {
    


    public static void main(String[] args) {

        Connection conn = ConexionBaseDeDatos.conectarConBaseDeDatos();//Se conecta con la base de datos

        Scanner escaner = new Scanner(System.in);

        MenuPrincipal menu = new MenuPrincipal(conn);
        menu.setVisible(true);

    }
}

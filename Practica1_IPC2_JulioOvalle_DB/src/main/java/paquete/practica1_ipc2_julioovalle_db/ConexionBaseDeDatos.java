package paquete.practica1_ipc2_julioovalle_db;

import java.sql.*;

public class ConexionBaseDeDatos {

    private static final String IP = "localhost";
    private static final int PUERTO = 3306;
    private static final String SCHEMA = "eventos_hyrule";
    private static final String USER = "universal";
    private static final String PASSWORD = "12345";

    private static final String URL = "jdbc:mysql://" + IP + ":" + PUERTO + "/" + SCHEMA;

    private static Connection conect;

    public static Connection conectarConBaseDeDatos() {

        try {
            conect = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Base de Datos:" + conect.getSchema());
            System.out.println("Catalogo: " + conect.getCatalog());
            return conect;
        } catch (SQLException e) {
            System.out.println("Error al conectarse a la BD");
            e.printStackTrace();
            return null;
        }

    }
}

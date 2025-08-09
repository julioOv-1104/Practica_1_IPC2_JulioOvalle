package PaqueteDAOs;

import PaqueteEntidades.*;
import java.sql.*;
import java.util.Scanner;

public class ParticipanteDAO {

    private Connection conn;

    public ParticipanteDAO(Connection conn) {
        this.conn = conn;
    }

    public void registrarParticipante(Participante participante) {

        String REGISTRAR_PARTICIPANTE = "INSERT INTO participante (email,institucion,tipo_participante,nombre)"
                + "VALUES (?,?,?,?)";

        /* Scanner escaner = new Scanner(System.in);

        System.out.println("Ingrese nombre");
        String nombre = escaner.nextLine();

        System.out.println("Ingrese Instituto");
        String instit = escaner.nextLine();

        System.out.println("Ingrese eMail");
        String eMail = escaner.nextLine();

        System.out.println("Ingrese Tipo");
        String tipo = escaner.nextLine();*/
        
        try {
            PreparedStatement ps = conn.prepareStatement(REGISTRAR_PARTICIPANTE);

            ps.setString(1, participante.geteMail());
            ps.setString(2, participante.getInstitucion());
            ps.setString(3, participante.getTipo().name());
            ps.setString(4, participante.getNombre());

            int n = ps.executeUpdate();
            System.out.println("sql ejecutado: " + ps);
            System.out.println("Rows affected " + n);

            ps.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error");
        }

    }

}

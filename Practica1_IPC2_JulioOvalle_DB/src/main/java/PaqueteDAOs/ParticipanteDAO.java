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

        try {
            PreparedStatement ps = conn.prepareStatement(REGISTRAR_PARTICIPANTE);
            ps.setString(1, participante.geteMail());
            ps.setString(2, participante.getInstitucion());
            ps.setString(3, participante.getTipo().name());
            ps.setString(4, participante.getNombre());

            int n = ps.executeUpdate();
            System.out.println("sql ejecutado: " + ps);
            System.out.println("Rows affected " + n);

            //ps.close();
            //conn.close();
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("Error al registrar Participante");
        }

    }

    public boolean buscarParticipanteDuplicado(String correo) {
        //Busca si hay un participante que tenga el mismo correo electronico
        String sql = "SELECT email FROM participante WHERE email = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Algo salio mal con la busqueda del participante");
        }

        return false;
    }

}

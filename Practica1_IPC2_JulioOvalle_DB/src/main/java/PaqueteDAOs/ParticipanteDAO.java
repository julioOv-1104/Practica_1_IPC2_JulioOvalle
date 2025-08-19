package PaqueteDAOs;

import PaqueteEntidades.*;
import java.sql.*;
import javax.swing.JOptionPane;

public class ParticipanteDAO extends EntidadDAO {

    public ParticipanteDAO(Connection conn) {
        this.setConn(conn);

    }

    public void comprobarExistencia(Participante nuevoParticipante) {
        //Verifica que no exista el participante
        if (buscarPorParametros(nuevoParticipante.geteMail(), "email", "participante", getConn())) {
            System.out.println("Ya existe este participante");
            JOptionPane.showMessageDialog(null, "Ya exite este participante", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);

        } else {
            registrarParticipante(nuevoParticipante);//Registra el nuevo participante en la BD
        }

    }

    public void registrarParticipante(Participante participante) {

        String registrarParticipante = "INSERT INTO participante (email,institucion,tipo_participante,nombre)"
                + "VALUES (?,?,?,?)";

        try {
            PreparedStatement ps = getConn().prepareStatement(registrarParticipante);
            ps.setString(1, participante.geteMail());
            ps.setString(2, participante.getInstitucion());
            ps.setString(3, participante.getTipo().name());
            ps.setString(4, participante.getNombre());

            int n = ps.executeUpdate();
            System.out.println("sql ejecutado: " + ps);
            System.out.println("Rows affected " + n);
            JOptionPane.showMessageDialog(null, "Participante registrado con exito", "Todo bien", JOptionPane.PLAIN_MESSAGE);

            //ps.close();
            //conn.close();
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("Error al registrar Participante");
        }

    }


}

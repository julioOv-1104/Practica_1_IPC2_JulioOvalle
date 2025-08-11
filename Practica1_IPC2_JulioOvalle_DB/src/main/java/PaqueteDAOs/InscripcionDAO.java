package PaqueteDAOs;

import PaqueteEntidades.Inscripcion;
import java.sql.*;
import javax.swing.JOptionPane;

public class InscripcionDAO extends EntidadDAO {

    public InscripcionDAO(Connection conn) {
        this.setConn(conn);
    }

    public void inscribirParticipante(Inscripcion inscripcion) {

        String inscribirParticipante = "INSERT INTO inscripcion (tipo_inscripcion , email_participante, "
                + "codigo_evento, es_valida) VALUES(?,?,?,?)";

        try {
            PreparedStatement ps = getConn().prepareStatement(inscribirParticipante);
            ps.setString(1, inscripcion.getTipoInscripcion().name());
            ps.setString(2, inscripcion.geteMailParticipante());
            ps.setString(3, inscripcion.getCodigoEvento());
            ps.setBoolean(4, inscripcion.isEsValida());
            
            int n = ps.executeUpdate();
            
            System.out.println("Rows affected "+ n);
            System.out.println("SQL ejecutado " + ps );
            JOptionPane.showMessageDialog(null, "Participante inscrito", "Todo bien", JOptionPane.PLAIN_MESSAGE);
            
            //ps.close();
            //conn.close();

        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("Error al inscribir al participante");
        }
    }

}

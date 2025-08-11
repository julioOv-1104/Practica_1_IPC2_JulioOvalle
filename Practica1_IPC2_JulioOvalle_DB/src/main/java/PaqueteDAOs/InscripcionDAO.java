package PaqueteDAOs;

import PaqueteEntidades.Inscripcion;
import java.sql.*;

public class InscripcionDAO extends EntidadDAO {

    public InscripcionDAO(Connection conn) {
        this.setConn(conn);
    }

    public void inscribirParticipante(Inscripcion inscripcion) {

        String INSCRIBIR_PARTICIPANTE = "INSERT INTO inscripcion (tipo_inscripcion , email_participante, "
                + "codigo_evento, es_valida) VALUES(?,?,?,?)";

        try {
            PreparedStatement ps = getConn().prepareStatement(INSCRIBIR_PARTICIPANTE);
            ps.setString(1, inscripcion.getTipoInscripcion().name());
            ps.setString(2, inscripcion.geteMailParticipante());
            ps.setString(3, inscripcion.getCodigoEvento());
            ps.setBoolean(4, inscripcion.isEsValida());
            
            int n = ps.executeUpdate();
            
            System.out.println("Rows affected "+ n);
            System.out.println("SQL ejecutado " + ps );
            
            //ps.close();
            //conn.close();

        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("Error al inscribir al participante");
        }
    }

}

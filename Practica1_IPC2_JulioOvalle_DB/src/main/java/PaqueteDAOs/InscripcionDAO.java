package PaqueteDAOs;

import PaqueteEntidades.Inscripcion;
import java.sql.*;
import javax.swing.JOptionPane;

public class InscripcionDAO extends EntidadDAO {

    public InscripcionDAO(Connection conn) {
        this.setConn(conn);
    }

    public void comprobarExistencia(Inscripcion nuevaInscripcion) {

        //Verifica que el participante y el evento existan
        if (buscarPorParametros(nuevaInscripcion.geteMailParticipante(), "email", "participante", getConn())
                && buscarPorParametros(nuevaInscripcion.getCodigoEvento(), "codigo_evento", "evento", getConn())) {
            //ambos existen
            System.out.println("Ambos existen, correo y evento");
            comprobarCuposRestantes(nuevaInscripcion.getCodigoEvento(), nuevaInscripcion.geteMailParticipante(), nuevaInscripcion);

        } else {
            JOptionPane.showMessageDialog(null, "Hay un error en uno o ambos datos", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }
    
    private void comprobarCuposRestantes(String codigo, String correo, Inscripcion nuevaInscripcion) {

        int cupoMaximo = 0;
        int cupoUsado = 0;

        String cupoOcupado = "SELECT COUNT(*) FROM inscripcion WHERE codigo_evento = ?";
        String cupoDisponible = "SELECT cupo_maximo FROM evento WHERE codigo_evento = ?";

        try {

            PreparedStatement ps = getConn().prepareStatement(cupoOcupado);
            ps.setString(1, codigo);

            System.out.println(ps);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                //Obtiene cuantas personas ya están inscritas en el evento
                cupoUsado = Integer.parseInt(rs.getString("COUNT(*)"));
                System.out.println("CUPO USADO " + cupoUsado);

            }

            PreparedStatement ps2 = getConn().prepareStatement(cupoDisponible);
            ps2.setString(1, codigo);

            System.out.println(cupoDisponible);
            ResultSet rs2 = ps2.executeQuery();

            if (rs2.next()) {
                //Obtiene cuantos espacios hay en total
                cupoMaximo = Integer.parseInt(rs2.getString("cupo_maximo"));
                System.out.println("CUPO MAXIMO " + cupoMaximo);

            }

            if (cupoUsado < cupoMaximo) {
                //Si aun hay espacios
                buscarInscripcionDuplicada(correo, codigo,nuevaInscripcion);
            } else {
                //Si no hay espacio no hace nada
                JOptionPane.showMessageDialog(null, "Ya no hay cupos para este evento", "ERROR", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Algo salio mal con la busqueda de cupos");
        }

    }
    
    private void buscarInscripcionDuplicada(String correo, String codigo, Inscripcion nuevaInscripcion) {

        String sql = "SELECT 1 FROM " + "inscripcion" + " WHERE " + "codigo_evento" + " = ?"
                + " AND " + "email_participante" + " = ?";

        try {
            PreparedStatement ps = getConn().prepareStatement(sql);
            ps.setString(1, codigo);
            ps.setString(2, correo);
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                //la inscripcion está duplicada
                JOptionPane.showMessageDialog(null, "Ya existe esta inscrpcion", "ERROR", JOptionPane.ERROR_MESSAGE);
                throw new SQLException();
            } else {
                //no está duplicada
                inscribirParticipante(nuevaInscripcion);
            }
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("Algo salio mal con la busqueda de inscripciones duplicadas " +e.getMessage());
        }

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

            System.out.println("Rows affected " + n);
            System.out.println("SQL ejecutado " + ps);
            JOptionPane.showMessageDialog(null, "Participante inscrito", "Todo bien", JOptionPane.PLAIN_MESSAGE);


        } catch (SQLException e) {
            
            System.out.println("Error al inscribir al participante " +e.getMessage());
        }
    }

}

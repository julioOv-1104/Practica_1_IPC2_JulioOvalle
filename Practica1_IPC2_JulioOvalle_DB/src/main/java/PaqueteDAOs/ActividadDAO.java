package PaqueteDAOs;

import PaqueteEntidades.*;
import com.mysql.cj.protocol.Resultset;
import java.sql.*;
import javax.swing.JOptionPane;

public class ActividadDAO extends EntidadDAO {

    public ActividadDAO(Connection conn) {
        this.setConn(conn);
    }

    //este metodo comprueba que el correo sea de alguien con una inscripcion valida y que no sea un asistente
    public boolean comprobarEncargado(String correo) {

        String buscarEncargado = "SELECT 1 FROM inscripcion WHERE email_participante = ? AND es_valida = true "
                + "AND tipo_inscripcion != 'ASISTENTE'";

        try {
            PreparedStatement ps = getConn().prepareStatement(buscarEncargado);

            ps.setString(1, correo);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;//Si encuentra la inscripcion que cumpla
            } else {
                JOptionPane.showMessageDialog(null, "Hay un error con los datos", "REVISAR DATOS", JOptionPane.ERROR_MESSAGE);
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al comprobar al encargado");
            return false;
        }

    }

    public void registrarActividad(Actividad actividad) {//Registra el actividad en la BD

        String insertarActividad = "INSERT INTO actividad (codigo_actividad, codigo_evento, email_encargado, "
                + "titulo,tipo_actividad, cupo_maximo,hora_inicio, hora_fin) "
                + "VALUES(?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement ps = getConn().prepareStatement(insertarActividad);

            ps.setString(1, actividad.getCodigoActividad());
            ps.setString(2, actividad.getCodigoEvento());
            ps.setString(3, actividad.geteMailEncargado());
            ps.setString(4, actividad.getTitulo());
            ps.setString(5, actividad.getTipoActividad().name());
            ps.setInt(6, actividad.getCupoMaximo());
            ps.setTime(7, java.sql.Time.valueOf(actividad.getHoraInicio()));
            ps.setTime(8, java.sql.Time.valueOf(actividad.getHoraFin()));

            int n = ps.executeUpdate();
            System.out.println("sql ejecutado: " + ps);
            System.out.println("Rows affected " + n);
            JOptionPane.showMessageDialog(null, "Actividad registrada con exito", "Todo bien", JOptionPane.PLAIN_MESSAGE);

        } catch (SQLException e) {
            //e.printStackTrace();//lo comento porque el erro que imprime es muy grande y parece que el programa falla
            System.out.println("Error al registrar Actividad");
        }
    }

}

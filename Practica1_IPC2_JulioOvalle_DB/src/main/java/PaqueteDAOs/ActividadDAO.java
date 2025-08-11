package PaqueteDAOs;

import PaqueteEntidades.*;
import java.sql.*;

public class ActividadDAO extends EntidadDAO {


    public ActividadDAO(Connection conn) {
        this.setConn(conn);
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

        } catch (SQLException e) {
            //e.printStackTrace();//lo comento porque el erro que imprime es muy grande y parece que el programa falla
            System.out.println("Error al registrar Actividad");
        }
    }
    
    

}

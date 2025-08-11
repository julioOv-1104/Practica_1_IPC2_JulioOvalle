package PaqueteDAOs;

import PaqueteEntidades.Evento;
import java.sql.*;

public class EventoDAO extends EntidadDAO {


    public EventoDAO(Connection conn) {
        this.setConn(conn);
    }

    public void registrarEvento(Evento evento) {//Registra el evento en la BD

        String INSERTAR_EVENTO = "INSERT INTO evento (codigo_evento, fecha, tipo_evento,titulo, ubicacion, cupo_maximo) "
                + "VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement ps = getConn().prepareStatement(INSERTAR_EVENTO);
            ps.setString(1, evento.getCodigoEvento());
            ps.setDate(2, java.sql.Date.valueOf(evento.getFecha()));
            ps.setString(3, evento.getTipo().name());
            ps.setString(4, evento.getTitulo());
            ps.setString(5, evento.getUbicacion());
            ps.setInt(6, evento.getCupoMaximo());

            int n = ps.executeUpdate();
            System.out.println("sql ejecutado: " + ps);
            System.out.println("Rows affected " + n);
            
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("Error al registrar Evento");
        }
    }
    
}

package PaqueteDAOs;

import PaqueteEntidades.Evento;
import java.sql.*;

public class EventoDAO {

    private Connection conn;

    public EventoDAO(Connection conn) {
        this.conn = conn;
    }

    public void registrarEvento(Evento evento) {

        String INSERTAR_EVENTO = "INSERT INTO evento (codigo_evento, fecha, tipo_evento,titulo, ubicacion, cupo_maximo) "
                + "VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement ps = conn.prepareStatement(INSERTAR_EVENTO);
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
            System.out.println("Error al registrar Evento");
        }
    }
    
    public boolean buscarEventoDuplicado(String codigo) {
        //Busca si hay un evento que tenga el mismo codigo
        String sql = "SELECT codigo_evento FROM participante WHERE codigo_evento = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, codigo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Algo salio mal con la busqueda del evento");
        }

        return false;
    }
}

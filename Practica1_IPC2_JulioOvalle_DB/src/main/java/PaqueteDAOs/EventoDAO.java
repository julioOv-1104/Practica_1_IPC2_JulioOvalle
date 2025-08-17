package PaqueteDAOs;

import PaqueteEntidades.Evento;
import java.sql.*;
import javax.swing.JOptionPane;

public class EventoDAO extends EntidadDAO {


    public EventoDAO(Connection conn) {
        this.setConn(conn);
    }

    public void registrarEvento(Evento evento) {//Registra el evento en la BD

        String insertarEvento = "INSERT INTO evento (codigo_evento, fecha, tipo_evento,titulo, ubicacion, cupo_maximo,costo_inscripcion) VALUES(?,?,?,?,?,?,?)";

        try {
            PreparedStatement ps = getConn().prepareStatement(insertarEvento);
            ps.setString(1, evento.getCodigoEvento());
            ps.setDate(2, java.sql.Date.valueOf(evento.getFecha()));
            ps.setString(3, evento.getTipo().name());
            ps.setString(4, evento.getTitulo());
            ps.setString(5, evento.getUbicacion());
            ps.setInt(6, evento.getCupoMaximo());
            ps.setDouble(7, evento.getCostoEvento());

            int n = ps.executeUpdate();
            System.out.println("sql ejecutado: " + ps);
            System.out.println("Rows affected " + n);
            JOptionPane.showMessageDialog(null, "Evento registrado con exito", "Todo bien", JOptionPane.PLAIN_MESSAGE);
            
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("Error al registrar Evento " + e.getMessage());
        }
    }
    
}

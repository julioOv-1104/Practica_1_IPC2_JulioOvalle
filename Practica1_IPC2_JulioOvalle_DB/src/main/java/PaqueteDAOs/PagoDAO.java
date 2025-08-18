package PaqueteDAOs;

import PaqueteEntidades.Inscripcion;
import PaqueteEntidades.Pago;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class PagoDAO extends EntidadDAO {

    public PagoDAO(Connection conn) {
        this.setConn(conn);
    }

    public void comprobarExistencia(Pago nuevoPago) {

        //Verifica que el participante y el evento existan
        if (buscarPorParametros(nuevoPago.getEmail_participante(), "email", "participante", getConn())
                && buscarPorParametros(nuevoPago.getCodigo_evento(), "codigo_evento", "evento", getConn())) {
            //ambos existen
            System.out.println("Ambos existen, correo y evento");
            buscarInscripcion(nuevoPago.getEmail_participante(), 
                    nuevoPago.getCodigo_evento(), nuevoPago.getMonto(), nuevoPago);//Busca inscripcion existente

        } else {
            JOptionPane.showMessageDialog(null, "Hay un error en uno o ambos datos", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void buscarInscripcion(String correo, String codigo, double monto, Pago nuevoPago) {

        String sql = "SELECT 1 FROM inscripcion WHERE " + "codigo_evento" + " = ?"
                + " AND email_participante = ?";

        try {
            PreparedStatement ps = getConn().prepareStatement(sql);
            ps.setString(1, codigo);
            ps.setString(2, correo);
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                //la inscripcion existe
                verificaPagoSuficiente(nuevoPago, monto);
            } else {
                //no está inscrito
                JOptionPane.showMessageDialog(null, "No existe la inscripcion", "ERROR", JOptionPane.ERROR_MESSAGE);
                throw new SQLException();
            }
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("Algo salio mal con la busqueda");
        }

    }

    public void verificaPagoSuficiente(Pago pago, double monto) {//antes de terminar verifica si el monto es suficiente
        String pregunta = "SELECT costo_inscripcion FROM evento WHERE codigo_evento = ?";

        try {
            PreparedStatement ps = getConn().prepareStatement(pregunta);
            ps.setString(1, pago.getCodigo_evento());
            ResultSet rs = ps.executeQuery();

            System.out.println("SQL ejecutado " + ps);

            if (rs.next()) {
                double costoEvento = rs.getDouble("costo_inscripcion");
                if (monto >= costoEvento) {//Si el monto recibido es por lo menos el esperado
                    pagarInscripcion(pago, monto);
                } else {
                    JOptionPane.showMessageDialog(null, "monto insuficiente", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro en revisar el monto");
        }

    }

    public void pagarInscripcion(Pago pago, double monto) {

        String registrarPago = "INSERT INTO pago (email_participante , codigo_evento, metodo_pago,monto) "
                + " VALUES(?,?,?,?)";

        try {

            PreparedStatement ps = getConn().prepareStatement(registrarPago);
            ps.setString(1, pago.getEmail_participante());
            ps.setString(2, pago.getCodigo_evento());
            ps.setString(3, pago.getMetodoDePago().name());
            ps.setDouble(4, monto);

            int n = ps.executeUpdate();

            System.out.println("Rows affected " + n);
            System.out.println("SQL ejecutado " + ps);
            JOptionPane.showMessageDialog(null, "Pago realizado con exito", "Todo bien", JOptionPane.PLAIN_MESSAGE);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al registrar pago");

        }
    }

}

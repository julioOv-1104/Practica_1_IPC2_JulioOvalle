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

    public void verificaPagoSuficiente(Pago pago, double monto) {
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

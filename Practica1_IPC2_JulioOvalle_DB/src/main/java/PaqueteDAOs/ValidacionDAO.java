package PaqueteDAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class ValidacionDAO extends EntidadDAO {

    public ValidacionDAO(Connection conn) {
        this.setConn(conn);
    }

    public void comprobarExistencia(String correo, String codigo) {

        if (buscarPorParametros(correo, "email", "participante", getConn())
                && buscarPorParametros(codigo, "codigo_evento", "evento", getConn())) {//valida que existan

            buscarPago(correo, codigo);

        } else {
            JOptionPane.showMessageDialog(null, "Hay un error en uno o ambos datos", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void buscarPago(String correo, String codigo) {//Busca el pago
        String busqueda = "SELECT 1 FROM pago WHERE email_participante = ? AND codigo_evento = ?";

        try {
            PreparedStatement ps = getConn().prepareStatement(busqueda);
            ps.setString(1, correo);
            ps.setString(2, codigo);
            ResultSet rs = ps.executeQuery();

            System.out.println("SQL ejecutado " + ps);

            if (rs.next()) {
                //si el pago ya se realizó     
                validarPago(correo, codigo);
            } else {
                JOptionPane.showMessageDialog(null, "Pendiente de pago", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println("Error en buscar pago " + e.getMessage());
            System.out.println("error en buscar pago");
        }

    }

    public void validarPago(String correo, String codigo) {
        String pago = "UPDATE inscripcion SET es_valida = true WHERE email_participante = ? "
                + "AND codigo_evento = ?";

        try {
            PreparedStatement ps2 = getConn().prepareStatement(pago);
            ps2.setString(1, correo);
            ps2.setString(2, codigo);

            int m = ps2.executeUpdate();
            System.out.println("Rows affected " + m);
            System.out.println("SQL ejecutado: " + ps2);
            System.out.println("Se validó la inscripción");
            JOptionPane.showMessageDialog(null, "Se valido la inscripcion", "Todo bien", JOptionPane.PLAIN_MESSAGE);
        } catch (Exception e) {
            System.out.println("Error al validar inscripcion");
        }

    }

}

package PaqueteDAOs;

import PaqueteEntidades.Actividad;
import PaqueteEntidades.AsistenciaYCertificado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class AsistenciaYCertificadoDAO extends EntidadDAO {

    private String codigoEncontrado;

    public AsistenciaYCertificadoDAO(Connection conn) {
        this.setConn(conn);
    }

    public void comprobarExistencia(AsistenciaYCertificado asistencia) {

        //revisa que existam los datos de el participante y la actividad
        if (buscarPorParametros(asistencia.getCorreoParticipante(), "email", "participante", getConn())
                && buscarPorParametros(asistencia.getCodigoActividad(), "codigo_actividad", "actividad", getConn())) {

            System.out.println("Ambos existen: participante y actividad");
            comprobarEspacios(asistencia.getCodigoActividad(), asistencia.getCorreoParticipante(), asistencia);

        } else {
            JOptionPane.showMessageDialog(null, "Hay un error en uno o ambos datos", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void comprobarEspacios(String codigoActividad, String correo, AsistenciaYCertificado asistencia) {

        int cupoMaximo = 0;
        int cupoUsado = 0;

        String cupoOcupado = "SELECT COUNT(*) FROM asistencia WHERE codigo_actividad = ?";
        String cupoDisponible = "SELECT cupo_maximo FROM actividad WHERE codigo_actividad = ?";

        try {

            PreparedStatement ps = getConn().prepareStatement(cupoOcupado);
            ps.setString(1, codigoActividad);

            System.out.println(ps);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                //Obtiene cuantas personas ya están inscritas en el evento
                cupoUsado = Integer.parseInt(rs.getString("COUNT(*)"));
                System.out.println("CUPO USADO " + cupoUsado);

            }

            PreparedStatement ps2 = getConn().prepareStatement(cupoDisponible);
            ps2.setString(1, codigoActividad);

            System.out.println(cupoDisponible);
            ResultSet rs2 = ps2.executeQuery();

            if (rs2.next()) {
                //Obtiene cuantos espacios hay en total
                cupoMaximo = Integer.parseInt(rs2.getString("cupo_maximo"));
                System.out.println("CUPO MAXIMO " + cupoMaximo);

            }

            if (cupoUsado < cupoMaximo) {
                //Si aun hay espacios
                buscarAsistenciaDuplicada(correo, codigoActividad, asistencia);
            } else {
                //Si no hay espacio no hace nada
                JOptionPane.showMessageDialog(null, "Ya no hay cupos para esta actividad", "ERROR", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Algo salio mal con la busqueda de cupos " + e.getMessage());
        }

    }

    private void buscarAsistenciaDuplicada(String correo, String codigo, AsistenciaYCertificado asistencia) {

        String sql = "SELECT 1 FROM asistencia WHERE email_participante = ?"
                + " AND codigo_actividad = ?";

        try {
            PreparedStatement ps = getConn().prepareStatement(sql);
            ps.setString(1, correo);
            ps.setString(2, codigo);

            System.out.println(ps);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                //la asistencia está duplicada
                JOptionPane.showMessageDialog(null, "Ya existe esta asistencia", "ERROR", JOptionPane.ERROR_MESSAGE);
                throw new SQLException();
            } else {
                //no está duplicada
                buscarCodigoEvento(asistencia);
            }
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("Algo salio mal con la busqueda " + e.getMessage());
        }
    }

    public void registrarAsistencia(AsistenciaYCertificado asistencia) {//Registra el actividad en la BD

        String insertarActividad = "INSERT INTO asistencia (email_participante, codigo_actividad) "
                + "VALUES(?,?)";

        try {
            PreparedStatement ps = getConn().prepareStatement(insertarActividad);

            ps.setString(1, asistencia.getCorreoParticipante());
            ps.setString(2, asistencia.getCodigoActividad());

            int n = ps.executeUpdate();
            System.out.println("sql ejecutado: " + ps);
            System.out.println("Rows affected " + n);
            JOptionPane.showMessageDialog(null, "Asistencia registrada con exito", "Todo bien", JOptionPane.PLAIN_MESSAGE);

        } catch (SQLException e) {
            //e.printStackTrace();//lo comento porque el erro que imprime es muy grande y parece que el programa falla
            System.out.println("Error al ingresar la asistencia " + e.getMessage());
        }

    }

    public void buscarCodigoEvento(AsistenciaYCertificado asistencia) {

        String buscarCodigo = "SELECT codigo_evento FROM actividad WHERE codigo_actividad = ?";

        try {
            PreparedStatement ps = getConn().prepareStatement(buscarCodigo);

            ps.setString(1, asistencia.getCodigoActividad());

            ResultSet rs = ps.executeQuery();
            System.out.println("sql ejecutado: " + ps);

            if (rs.next()) {
                codigoEncontrado = rs.getString("codigo_evento");
                System.out.println("codigo_evento = " + codigoEncontrado);
                //Verifica que exista su inscripcion y esté validada
                comprobarInscripcion(asistencia);
            }

        } catch (SQLException e) {
            e.printStackTrace();//lo comento porque el erro que imprime es muy grande y parece que el programa falla
            System.out.println("Error al buscar codigo del evento " + e.getMessage());
        }
    }

    public void buscarCertificadoDuplicado(AsistenciaYCertificado asistencia) {

        String buscarCertificado = "SELECT 1 FROM certificado WHERE codigo_evento = ? AND email_participante = ?";
        try {
            PreparedStatement ps = getConn().prepareStatement(buscarCertificado);
            ps.setString(1, asistencia.getCodigoActividad());
            ps.setString(2, asistencia.getCorreoParticipante());

            ResultSet rs = ps.executeQuery();
            System.out.println("sql ejecutado: " + ps);

            if (rs.next()) {
                //Si encuentra el certificado duplicado no hace nada
                JOptionPane.showMessageDialog(null, "Ya existe este certificado", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else {
                System.out.println("El certificado no está duplicado");
                registrarCertificado(asistencia);
            }

        } catch (SQLException e) {
            //e.printStackTrace();//lo comento porque el erro que imprime es muy grande y parece que el programa falla
            System.out.println("Error al buscar codigo del evento " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al buscar codigo del evento", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void comprobarInscripcion(AsistenciaYCertificado asistencia) {

        String sql = "SELECT 1 FROM inscripcion WHERE email_participante = ?"
                + " AND codigo_evento = ? AND es_valida = true";

        try {
            PreparedStatement ps = getConn().prepareStatement(sql);
            ps.setString(1, asistencia.getCorreoParticipante());
            ps.setString(2, codigoEncontrado);

            System.out.println(ps);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                //La inscripcion existe y es valida
                registrarAsistencia(asistencia);//Crea el certificado

            } else {
                //no existe la inscripcion o no es valida aún
                JOptionPane.showMessageDialog(null, "Ya existe esta asistencia", "ERROR", JOptionPane.ERROR_MESSAGE);
                throw new SQLException();
            }
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("Algo salio mal con la busqueda " + e.getMessage());

        }

    }

    private void registrarCertificado(AsistenciaYCertificado asistencia) {//Registra el actividad en la BD

        String insertarCertificado = "INSERT INTO certificado (email_participante, codigo_evento) "
                + "VALUES(?,?)";

        try {
            PreparedStatement ps = getConn().prepareStatement(insertarCertificado);

            ps.setString(1, asistencia.getCorreoParticipante());
            ps.setString(2, asistencia.getCodigoActividad());

            int n = ps.executeUpdate();
            System.out.println("sql ejecutado: " + ps);
            System.out.println("Rows affected " + n);
            JOptionPane.showMessageDialog(null, "Certificado registrado con exito", "Todo bien", JOptionPane.PLAIN_MESSAGE);

        } catch (SQLException e) {
            //e.printStackTrace();//lo comento porque el erro que imprime es muy grande y parece que el programa falla
            System.out.println("Error al registrar el certificado " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al registrar el certificado", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }

}

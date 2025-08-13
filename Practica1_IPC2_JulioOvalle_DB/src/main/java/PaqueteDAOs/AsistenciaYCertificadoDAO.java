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
            
            buscarCertificadoDuplicado(asistencia);
            
        } catch (SQLException e) {
            //e.printStackTrace();//lo comento porque el erro que imprime es muy grande y parece que el programa falla
            System.out.println("Error al ingresar la asistencia");
        }

    }

    public void buscarCodigoEvento(AsistenciaYCertificado asistencia) {


        /*String buscarCodigoEvento = "SELECT act.codigo_evento FROM asistencia a JOIN actividad act "
                + "ON a.codigo_actividad = act.codigo_actividad WHERE a.codigo_actividad = ?";*/
        
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
            System.out.println("Error al buscar codigo del evento");
        }
    }

    private void buscarCertificadoDuplicado(AsistenciaYCertificado asistencia) {

        String buscarCertificado = "SELECT 1 FROM certificado WHERE codigo_evento = ? AND email_participante = ?";
        try {
            PreparedStatement ps = getConn().prepareStatement(buscarCertificado);
            ps.setString(1, codigoEncontrado);
            ps.setString(2, asistencia.getCorreoParticipante());

            ResultSet rs = ps.executeQuery();
            System.out.println("sql ejecutado: " + ps);

            if (rs.next()) {
                //Si encuentra el certificado duplicado no hace nada
            } else {
                System.out.println("El certificado no está duplicado");
                registrarCertificado(asistencia);
            }

        } catch (SQLException e) {
            //e.printStackTrace();//lo comento porque el erro que imprime es muy grande y parece que el programa falla
            System.out.println("Error al buscar codigo del evento");
        }

    }
    
    public void comprobarInscripcion(AsistenciaYCertificado asistencia){
        
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
                throw new SQLException();
            } else {
                //no existe la inscripcion o no es valida aún
                JOptionPane.showMessageDialog(null, "Ya existe esta asistencia", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("Algo salio mal con la busqueda");
        }
    
    }

    private void registrarCertificado(AsistenciaYCertificado asistencia) {//Registra el actividad en la BD

        String insertarCertificado = "INSERT INTO certificado (email_participante, codigo_evento) "
                + "VALUES(?,?)";

        try {
            PreparedStatement ps = getConn().prepareStatement(insertarCertificado);

            ps.setString(1, asistencia.getCorreoParticipante());
            ps.setString(2, codigoEncontrado);

            int n = ps.executeUpdate();
            System.out.println("sql ejecutado: " + ps);
            System.out.println("Rows affected " + n);
            JOptionPane.showMessageDialog(null, "Certificado registrado con exito", "Todo bien", JOptionPane.PLAIN_MESSAGE);

        } catch (SQLException e) {
            //e.printStackTrace();//lo comento porque el erro que imprime es muy grande y parece que el programa falla
            System.out.println("Error al registrar el certificado");
        }

    }

}

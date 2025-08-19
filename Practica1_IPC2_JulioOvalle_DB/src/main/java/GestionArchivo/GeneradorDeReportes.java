package GestionArchivo;

import InterfazGrafica.*;
import PaqueteEntidades.*;
import Reportes.*;
import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import javax.swing.JOptionPane;

public class GeneradorDeReportes {

    private Connection conn;
    private String ruta;
    IFCargaArchivo archivocargado;

    public GeneradorDeReportes(Connection conn, String ruta) {
        this.conn = conn;
        this.ruta = ruta;
    }

    public void generarReporteEventos(List<Evento> eventos, String rutaDestino) {

        StringBuilder html = new StringBuilder();

        html.append("<html><head><title>Reporte de Eventos</title></head><body>");
        html.append("<h1>Eventos Registrados</h1>");
        html.append("<table border='1'>");
        html.append("<tr><th>Código</th><th>Título</th><th>Fecha</th><th>Cupo</th></tr>");

        for (Evento e : eventos) {
            html.append("<tr>")
                    .append("<td>").append(e.getCodigoEvento()).append("</td>")
                    .append("<td>").append(e.getTitulo()).append("</td>")
                    .append("<td>").append(e.getFecha()).append("</td>")
                    .append("<td>").append(e.getCupoMaximo()).append("</td>")
                    .append("</tr>");
        }

        html.append("</table>");
        html.append("</body></html>");

        try {
            FileWriter writer = new FileWriter(rutaDestino + "/reporte_eventos.html");
            writer.write(html.toString());
            writer.close();
            System.out.println("Reporte generado en: " + rutaDestino);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void generarParticipantes(List<ReportesParticipantes> participantes, String rutaDestino) {

        StringBuilder html = new StringBuilder();

        //Estructura del archivo de html para los reportea
        html.append("<html><head><title>Reporte de Participantes</title></head><body>");
        html.append("<h1>Participantes Inscritos</h1>");
        html.append("<table border='1'>");
        html.append("<tr><th>Correo</th><th>Tipo</th><th>Nombre</th><th>Institucion</th><th>Validado o no</th></tr>");

        for (ReportesParticipantes p : participantes) {

            html.append("<tr>")
                    .append("<td>").append(p.geteMail()).append("</td>")
                    .append("<td>").append(p.getTipo()).append("</td>")
                    .append("<td>").append(p.getNombre()).append("</td>")
                    .append("<td>").append(p.getInstitucion()).append("</td>")
                    .append("<td>").append(p.isEsValida()).append("</td>")
                    .append("</tr>");
        }

        html.append("</table>");
        html.append("</body></html>");

        try {
            FileWriter writer = new FileWriter(rutaDestino + "/reporte_eventos.html");
            writer.write(html.toString());
            writer.close();
            System.out.println("Reporte de participantes escrito en: " + rutaDestino);
        } catch (IOException ex) {
            System.out.println("Error en reportes de participantes");
            ex.printStackTrace();
        }
    }

    //Este metodo se encarga de filtrar los datos en la BD
    public List<ReportesParticipantes> obtenerParticipantes(String sql) {

        //Lista en donde iran los objetos que se mostraran en los reportes
        List<ReportesParticipantes> lista = new ArrayList<>();
        TipoParticipantes tipo = TipoParticipantes.ESTUDIANTE;//por defecto

        System.out.println(sql);

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                ReportesParticipantes rep = new ReportesParticipantes(
                        rs.getString("email"),
                        tipo = tipo.valueOf(rs.getString("tipo_participante")),
                        rs.getString("nombre"),
                        rs.getString("institucion"),
                        rs.getBoolean("es_valida")
                );
                lista.add(rep);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en los datos", "ERROR", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
        return lista;
    }

    public void crearReportePrueba() {

        // String ruta = "C:\\Users\\Usuario\\OneDrive\\Documents\\Reportes html";
        String sql = "SELECT email,tipo_participante,nombre,institucion,es_valida FROM participante INNER JOIN inscripcion"
                + " ON participante.email = inscripcion.email_participante AND inscripcion.codigo_evento = 'EVT-00000001' "
                + "AND participante.tipo_participante = 'PROFESIONAL' AND participante.institucion = 'Triforce Software';";

        generarParticipantes(obtenerParticipantes(sql), ruta);

    }

    public void generarActividades(List<ReportesActividades> actividades, String rutaDestino) {

        StringBuilder html = new StringBuilder();

        html.append("<html><head><title>Reporte de Participantes</title></head><body>");
        html.append("<h1>Participantes Inscritos</h1>");
        html.append("<table border='1'>");
        html.append("<tr><th>Correo</th><th>Tipo</th><th>Nombre</th><th>Institucion</th><th>Validado o no</th></tr>");

        for (ReportesActividades a : actividades) {

            html.append("<tr>")
                    .append("<td>").append(a.getCodigoActividad()).append("</td>")
                    .append("<td>").append(a.getCodigoEvento()).append("</td>")
                    .append("<td>").append(a.getTitulo()).append("</td>")
                    .append("<td>").append(a.getNombreEncargado()).append("</td>")
                    .append("<td>").append(a.getHoraInicio()).append("</td>")
                    .append("<td>").append(a.getCupoMaximo()).append("</td>")
                    .append("<td>").append(a.getCantidadParticipantes()).append("</td>")
                    .append("</tr>");
        }

        html.append("</table>");
        html.append("</body></html>");

        try {
            FileWriter writer = new FileWriter(rutaDestino + "/reporte_eventos.html");
            writer.write(html.toString());
            writer.close();
            System.out.println("Reporte de actividades escrito en: " + rutaDestino);
        } catch (IOException ex) {
            System.out.println("Error en reportes de actividades");
            ex.printStackTrace();
        }
    }
    
    
    public List<ReportesActividades> obtenerActividades(String sql) {

        //Lista en donde iran los objetos que se mostraran en los reportes
        List<ReportesActividades> lista = new ArrayList<>();
        LocalTime hora = null;
        int cupo;
        int cantidad;

        System.out.println(sql);

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                ReportesActividades rep = new ReportesActividades(
                        
                        rs.getString("codigo_actividad"),
                        rs.getString("codigo_evento"),
                        rs.getString("titulo"),
                        rs.getString("nombre"),
                        hora = LocalTime.parse(rs.getString("hora_inicio")),
                        cupo = Integer.parseInt(rs.getString("cupo_maximo")),
                        cantidad = Integer.parseInt(rs.getString("COUNT(*)"))
                        
                );
                
                lista.add(rep);//Se añade la actividad creada
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en los datos", "ERROR", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
        return lista;
    }
}

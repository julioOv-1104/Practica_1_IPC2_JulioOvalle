package GestionArchivo;

import InterfazGrafica.*;
import PaqueteDAOs.*;
import PaqueteEntidades.*;
import PaqueteEntidades.TipoEventos;
import java.io.*;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.SwingUtilities;

public class LecturaArchivo implements Runnable {

    private File archivo;
    private int velocidad;
    private MenuPrincipal menu;
    private Connection conn;

    public LecturaArchivo(String ruta, int velocidad, MenuPrincipal menu, Connection conn) {
        this.archivo = new File(ruta);
        this.velocidad = velocidad;
        this.menu = menu;
        this.conn = conn;
    }

    @Override
    public void run() {//Carga el archivo

        try {

            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);

            String linea;
            System.out.println("");
            System.out.println("Contenido del archivo:");

            // Leer línea por línea
            while ((linea = br.readLine()) != null) {

                String texto = linea;
                leerArchivo(linea);

                SwingUtilities.invokeLater(() -> {
                    menu.getTxtArea().append(texto + "\n");
                });

                Thread.sleep(velocidad); // espera segun la velocidad que asigna el usuario

            }

            // Cerrar el lector
            br.close();
            fr.close();

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } catch (InterruptedException er) {
            System.out.println("El hilo se detuvo");
        }

    }

    public void leerArchivo(String linea) {

        if (linea.startsWith("REGISTRO_EVENTO")) {
            String[] valores = extraerParametros(linea);
            registrarEvento(valores, linea);

        } else if (linea.startsWith("REGISTRO_PARTICIPANTE")) {

            String[] valores = extraerParametros(linea);
            registrarParticipante(valores, linea);

        } else if (linea.startsWith("INSCRIPCION")) {
            String[] valores = extraerParametros(linea);
            inscribir(valores, linea);

        } else if (linea.startsWith("PAGO")) {
            String[] valores = extraerParametros(linea);
            pagar(valores, linea);

        } else if (linea.startsWith("VALIDAR_INSCRIPCION")) {
            String[] valores = extraerParametros(linea);
            validar(valores, linea);

        } else if (linea.startsWith("REGISTRO_ACTIVIDAD")) {
            String[] valores = extraerParametros(linea);
            registrarActividad(valores, linea);
            

        } else if (linea.startsWith("ASISTENCIA")) {
            String[] valores = extraerParametros(linea);
            registrarAsistencia(valores, linea);

            
        } else if (linea.startsWith("CERTIFICADO")) {
            String[] valores = extraerParametros(linea);
            registrarCertificado(valores, linea);

            
            
        } else if (linea.startsWith("REPORTE_PARTICIPANTES")) {
            System.out.println("REPORTE_PARTICIPANTES");

        } else if (linea.startsWith("REPORTE_ACTIVIDADES")) {
            System.out.println("REPORTE_ACTIVIDADES");

        } else if (linea.startsWith("REPORTE_EVENTOS")) {
            System.out.println("REPORTE_EVENTOS");

        } else {
            System.out.println("\nsalto de linea\n");

        }
    }

    // Método para cortar parámetros dela linea de comando
    private String[] extraerParametros(String linea) {

        String parametros = linea.substring(linea.indexOf("(") + 1, linea.lastIndexOf(")"));

        String[] valores = parametros.split(",");

        for (int i = 0; i < valores.length; i++) {
            valores[i] = valores[i].replace("\"", "");
        }

        return valores;//retorna el arreglo con todos los parametros separados
    }

    private void registrarEvento(String[] valores, String linea) {

        try {

            int cupo = Integer.parseInt(valores[5]);
            LocalDate fecha = LocalDate.parse(valores[1]);
            TipoEventos tipo = TipoEventos.CHARLA;//por defecto
            tipo = tipo.valueOf(valores[2]);
            double costo = Double.parseDouble(valores[6]);

            Evento nuevoEvento = new Evento(valores[0], valores[3], valores[4], cupo, fecha, tipo, costo);
            EventoDAO even = new EventoDAO(conn);
            even.comprobarExistencia(nuevoEvento);

        } catch (Exception e) {
            System.out.println("Error en la linea: " + linea);
            menu.getTxtAreaErrores().append("\n error en la linea --> " + linea);
        }

    }

    private void registrarParticipante(String[] valores, String linea) {

        try {

            TipoParticipantes tipo = TipoParticipantes.ESTUDIANTE;
            tipo = tipo.valueOf(valores[1]);

            Participante nuevoParticipante = new Participante(valores[0], valores[2], valores[3], tipo);
            ParticipanteDAO part = new ParticipanteDAO(conn);
            part.comprobarExistencia(nuevoParticipante);

        } catch (Exception e) {
            System.out.println("Error en la linea: " + linea + " " + e.getMessage());
            menu.getTxtAreaErrores().append("\n error en la linea --> " + linea);
        }
    }

    private void inscribir(String[] valores, String linea) {

        try {

            TipoInscripciones tipo = TipoInscripciones.ASISTENTE;
            tipo = tipo.valueOf(valores[2]);

            Inscripcion nuevaInscripcion = new Inscripcion(valores[0], valores[1], tipo);
            InscripcionDAO inscripcion = new InscripcionDAO(conn);
            inscripcion.comprobarExistencia(nuevaInscripcion);

        } catch (Exception e) {
            System.out.println("Error en la linea: " + linea + " " + e.getMessage());
            menu.getTxtAreaErrores().append("\n error en la linea --> " + linea);
        }

    }

    private void pagar(String[] valores, String linea) {

        try {

            MetodoDePago metodo = MetodoDePago.EFECTIVO;
            metodo = metodo.valueOf(valores[2]);
            double monto = Double.parseDouble(valores[3]);

            PagoDAO pagodao = new PagoDAO(conn);
            Pago nuevoPago = new Pago(valores[0], valores[1], metodo, monto);
            pagodao.comprobarExistencia(nuevoPago);

        } catch (Exception e) {
            System.out.println("Error en la linea: " + linea + " " + e.getMessage());
            menu.getTxtAreaErrores().append("\n error en la linea --> " + linea);
        }

    }

    private void validar(String[] valores, String linea) {

        try {

            Validacion nuevaValidacion = new Validacion(valores[0], valores[1]);
            ValidacionDAO valida = new ValidacionDAO(conn);
            valida.comprobarExistencia(valores[0], valores[1]);

        } catch (Exception e) {
            System.out.println("Error en la linea: " + linea + " " + e.getMessage());
            menu.getTxtAreaErrores().append("\n error en la linea --> " + linea);
        }
    }
    
    private void registrarActividad(String[] valores, String linea) {

        try {
            
            TipoActividades tipo = TipoActividades.CHARLA;
            tipo = tipo.valueOf(valores[2]);
            int cupo = Integer.parseInt(valores[7]);
            LocalTime horaIni = LocalTime.parse(valores[5]);
            LocalTime horaFin = LocalTime.parse(valores[6]);

            Actividad nuevaActividad = new Actividad(valores[0], valores[1], valores[4], valores[3], tipo, cupo, horaIni,horaFin);
            ActividadDAO act = new ActividadDAO(conn);
            act.comprobarExistencia(nuevaActividad);
            
        } catch (Exception e) {
            System.out.println("Error en la linea: " + linea + " " + e.getMessage());
            menu.getTxtAreaErrores().append("\n error en la linea --> " + linea);
        }
    }
    
    private void registrarAsistencia(String[] valores, String linea) {

        try {

            AsistenciaYCertificado nuevaAsistencia = new AsistenciaYCertificado(valores[0], valores[1]);
            AsistenciaYCertificadoDAO asis = new AsistenciaYCertificadoDAO(conn);
            asis.comprobarExistencia(nuevaAsistencia);
            
        } catch (Exception e) {
            System.out.println("Error en la linea: " + linea + " " + e.getMessage());
            menu.getTxtAreaErrores().append("\n error en la linea --> " + linea);
        }
    }
    
    private void registrarCertificado(String[] valores, String linea) {

        try {

            AsistenciaYCertificado nuevoCertificado = new AsistenciaYCertificado(valores[0], valores[1]);
            AsistenciaYCertificadoDAO certi = new AsistenciaYCertificadoDAO(conn);
            certi.buscarCertificadoDuplicado(nuevoCertificado);
            
        } catch (Exception e) {
            System.out.println("Error en la linea: " + linea + " " + e.getMessage());
            menu.getTxtAreaErrores().append("\n error en la linea --> " + linea);
        }
    }

}

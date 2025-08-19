
package Reportes;

import java.time.LocalTime;


public class ReportesActividades {
    
    private String codigoActividad, codigoEvento,titulo,nombreEncargado;
    private int cupoMaximo, cantidadParticipantes;
    private LocalTime horaInicio;

    public ReportesActividades(String codigoActividad, String codigoEvento, String titulo, String nombreEncargado, LocalTime horaInicio, int cupoMaximo, int cantidadParticipantes) {
        this.codigoActividad = codigoActividad;
        this.codigoEvento = codigoEvento;
        this.titulo = titulo;
        this.nombreEncargado = nombreEncargado;
        this.cupoMaximo = cupoMaximo;
        this.cantidadParticipantes = cantidadParticipantes;
        this.horaInicio = horaInicio;
    }

    public String getCodigoActividad() {
        return codigoActividad;
    }

    public void setCodigoActividad(String codigoActividad) {
        this.codigoActividad = codigoActividad;
    }

    public String getCodigoEvento() {
        return codigoEvento;
    }

    public void setCodigoEvento(String codigoEvento) {
        this.codigoEvento = codigoEvento;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNombreEncargado() {
        return nombreEncargado;
    }

    public void setNombreEncargado(String nombreEncargado) {
        this.nombreEncargado = nombreEncargado;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    public int getCantidadParticipantes() {
        return cantidadParticipantes;
    }

    public void setCantidadParticipantes(int cantidadParticipantes) {
        this.cantidadParticipantes = cantidadParticipantes;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }
    
    
    
}


package PaqueteEntidades;

import java.time.LocalTime;

public class Actividad {
    
    private String codigoActividad, codigoEvento, eMailEncargado,Titulo;
    private TipoActividades tipoActividad;
    private int cupoMaximo;
    private LocalTime horaInicio, horaFin;

    public Actividad(String codigoActividad, String codigoEvento, String eMailEncargado, String Titulo, TipoActividades tipoActividad, 
            int cupoMaximo, LocalTime horaInicio, LocalTime horaFin) {
        this.codigoActividad = codigoActividad;
        this.codigoEvento = codigoEvento;
        this.eMailEncargado = eMailEncargado;
        this.Titulo = Titulo;
        this.tipoActividad = tipoActividad;
        this.cupoMaximo = cupoMaximo;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
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

    public String geteMailEncargado() {
        return eMailEncargado;
    }

    public void seteMailEncargado(String eMailEncargado) {
        this.eMailEncargado = eMailEncargado;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public TipoActividades getTipoActividad() {
        return tipoActividad;
    }

    public void setTipoActividad(TipoActividades tipoActividad) {
        this.tipoActividad = tipoActividad;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }
    
    
}

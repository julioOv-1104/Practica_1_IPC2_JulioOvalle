package PaqueteEntidades;

import java.time.LocalDate;

public class Evento {

    private String codigoEvento, titulo, ubicacion;
    private int cupoMaximo;
    private double costoEvento;
    private LocalDate fecha;
    private TipoEventos tipo;

    public Evento(String codigoEvento, String titulo, String ubicacion, int cupoMaximo, LocalDate fecha, 
            TipoEventos tipo, double costo) {
        this.codigoEvento = codigoEvento;
        this.titulo = titulo;
        this.ubicacion = ubicacion;
        this.cupoMaximo = cupoMaximo;
        this.fecha = fecha;
        this.tipo = tipo;
        this.costoEvento = costo;
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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public TipoEventos getTipo() {
        return tipo;
    }

    public void setTipo(TipoEventos tipo) {
        this.tipo = tipo;
    }

    public double getCostoEvento() {
        return costoEvento;
    }

    public void setCostoEvento(double costoEvento) {
        this.costoEvento = costoEvento;
    }
    
    

}

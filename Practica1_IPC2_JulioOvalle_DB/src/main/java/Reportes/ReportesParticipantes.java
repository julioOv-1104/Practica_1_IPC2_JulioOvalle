package Reportes;

import PaqueteEntidades.TipoParticipantes;

public class ReportesParticipantes {

    private String nombre, institucion, eMail;
    private TipoParticipantes tipo;
    private boolean esValida;

    public ReportesParticipantes(String eMail, TipoParticipantes tipo, String nombre, String institucion, boolean esValida) {
        this.nombre = nombre;
        this.institucion = institucion;
        this.eMail = eMail;
        this.tipo = tipo;
        this.esValida = esValida;
    }

    public boolean isEsValida() {
        return esValida;
    }

    public void setEsValida(boolean esValida) {
        this.esValida = esValida;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public TipoParticipantes getTipo() {
        return tipo;
    }

    public void setTipo(TipoParticipantes tipo) {
        this.tipo = tipo;
    }

}

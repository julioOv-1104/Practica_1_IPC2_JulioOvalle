
package PaqueteEntidades;

public class Inscripcion {
    private String eMailParticipante , codigoEvento;
    private boolean esValida = false;//False es el valor inicial
    private TipoInscripciones tipoInscripcion;

    public Inscripcion(String eMailParticipante, String codigoEvento, TipoInscripciones tipoInscripcion) {
        this.eMailParticipante = eMailParticipante;
        this.codigoEvento = codigoEvento;
        this.tipoInscripcion = tipoInscripcion;
    }

    public String geteMailParticipante() {
        return eMailParticipante;
    }

    public void seteMailParticipante(String eMailParticipante) {
        this.eMailParticipante = eMailParticipante;
    }

    public String getCodigoEvento() {
        return codigoEvento;
    }

    public void setCodigoEvento(String codigoEvento) {
        this.codigoEvento = codigoEvento;
    }

    public boolean isEsValida() {
        return esValida;
    }

    public void setEsValida(boolean esValida) {
        this.esValida = esValida;
    }

    public TipoInscripciones getTipoInscripcion() {
        return tipoInscripcion;
    }

    public void setTipoInscripcion(TipoInscripciones tipoInscripcion) {
        this.tipoInscripcion = tipoInscripcion;
    }
    
    
}


package PaqueteEntidades;

public class Validacion {
    
    private String correoParticipante, codigoEvento;

    public Validacion(String correoParticipante, String codigoEvento) {
        this.correoParticipante = correoParticipante;
        this.codigoEvento = codigoEvento;
    }

    public String getCorreoParticipante() {
        return correoParticipante;
    }

    public void setCorreoParticipante(String correoParticipante) {
        this.correoParticipante = correoParticipante;
    }

    public String getCodigoEvento() {
        return codigoEvento;
    }

    public void setCodigoEvento(String codigoEvento) {
        this.codigoEvento = codigoEvento;
    }
    
    
    
}

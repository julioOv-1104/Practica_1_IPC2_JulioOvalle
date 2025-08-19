
package Reportes;


public class Certificados {
    private String email_participante, codigo_evento;

    public Certificados(String email_participante, String codigo_evento) {
        this.email_participante = email_participante;
        this.codigo_evento = codigo_evento;
    }

    public String getEmail_participante() {
        return email_participante;
    }

    public void setEmail_participante(String email_participante) {
        this.email_participante = email_participante;
    }

    public String getCodigo_evento() {
        return codigo_evento;
    }

    public void setCodigo_evento(String codigo_evento) {
        this.codigo_evento = codigo_evento;
    }
    
    
    
}

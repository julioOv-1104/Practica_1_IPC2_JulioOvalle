
package PaqueteEntidades;

public class Pago {
    
    private String email_participante, codigo_evento;
    private MetodoDePago metodoDePago;
    private final int monto = 50;

    public Pago(String email_participante, String codigo_evento, MetodoDePago metodoDePago) {
        this.email_participante = email_participante;
        this.codigo_evento = codigo_evento;
        this.metodoDePago = metodoDePago;
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

    public MetodoDePago getMetodoDePago() {
        return metodoDePago;
    }

    public void setMetodoDePago(MetodoDePago metodoDePago) {
        this.metodoDePago = metodoDePago;
    }

    public int getMonto() {
        return monto;
    }
  
}

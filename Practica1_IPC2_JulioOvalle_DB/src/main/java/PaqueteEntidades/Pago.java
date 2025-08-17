
package PaqueteEntidades;

public class Pago {
    
    private String email_participante, codigo_evento;
    private MetodoDePago metodoDePago;
    private double monto;

    public Pago(String email_participante, String codigo_evento, MetodoDePago metodoDePago, double monto) {
        this.email_participante = email_participante;
        this.codigo_evento = codigo_evento;
        this.metodoDePago = metodoDePago;
        this.monto = monto;
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

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    
}

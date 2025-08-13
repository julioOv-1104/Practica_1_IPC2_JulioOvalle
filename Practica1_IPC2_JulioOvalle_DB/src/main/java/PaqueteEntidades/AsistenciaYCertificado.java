
package PaqueteEntidades;

public class AsistenciaYCertificado {
    
    private String correoParticipante, codigoActividad;

    public AsistenciaYCertificado(String correoParticipante, String codigoActividad) {
        this.correoParticipante = correoParticipante;
        this.codigoActividad = codigoActividad;
    }
    
    

    public String getCorreoParticipante() {
        return correoParticipante;
    }

    public void setCorreoParticipante(String correoParticipante) {
        this.correoParticipante = correoParticipante;
    }

    public String getCodigoActividad() {
        return codigoActividad;
    }

    public void setCodigoActividad(String codigoActividad) {
        this.codigoActividad = codigoActividad;
    }
 
}

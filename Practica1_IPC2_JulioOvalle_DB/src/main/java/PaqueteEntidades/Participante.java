
package PaqueteEntidades;


public class Participante {
    private String nombre, institucion, eMail;
    private TipoParticipantes Tipo;

     public Participante(String nombre, String institucion, String eMail, TipoParticipantes Tipo) {
         this.nombre = nombre;
         this.institucion = institucion;
         this.eMail = eMail;
         this.Tipo = Tipo;
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
        return Tipo;
    }

    public void setTipo(TipoParticipantes Tipo) {
        this.Tipo = Tipo;
    }
    
    

}

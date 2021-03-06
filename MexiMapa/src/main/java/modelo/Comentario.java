package modelo;
// Generated 7/05/2019 01:45:17 PM by Hibernate Tools 4.3.1




public class Comentario  implements java.io.Serializable {


     private int idcomentario;
     private Marcador marcador;
     private Usuario usuario;
     private String comentario;
     private Integer calificacion;

    public Comentario() {
    }

	
    public Comentario(int idcomentario, Marcador marcador, Usuario usuario) {
        this.idcomentario = idcomentario;
        this.marcador = marcador;
        this.usuario = usuario;
    }
    
    public Comentario(int idcomentario, Marcador marcador, Usuario usuario,String comentario) {
        this.idcomentario = idcomentario;
        this.marcador = marcador;
        this.usuario = usuario;
    }
    
    public Comentario(int idcomentario, Marcador marcador, Usuario usuario, String comentario, Integer calificacion) {
       this.idcomentario = idcomentario;
       this.marcador = marcador;
       this.usuario = usuario;
       this.comentario = comentario;
       this.calificacion = calificacion;
    }
   
    public int getIdcomentario() {
        return this.idcomentario;
    }
    
    public void setIdcomentario(int idcomentario) {
        this.idcomentario = idcomentario;
    }
    public Marcador getMarcador() {
        return this.marcador;
    }
    
    public void setMarcador(Marcador marcador) {
        this.marcador = marcador;
    }
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public String getComentario() {
        return this.comentario;
    }
    
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    public Integer getCalificacion() {
        return this.calificacion;
    }
    

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }
    
    public String toString(){
     return this.comentario;
    }


}



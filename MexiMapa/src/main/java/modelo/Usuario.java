package modelo;
// Generated 26/03/2019 05:09:40 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Usuario generated by hbm2java
 */
public class Usuario  implements java.io.Serializable {


     private int idUsuario;
     private String nombre;
     private String contrasenia;
     private String correo;
     private String rol;
     private Set comentarios = new HashSet(0);
     private Set temas = new HashSet(0);
     private Set marcadors = new HashSet(0);

    public Usuario() {
    }

	
    public Usuario(int idUsuario, String contrasenia, String correo, String rol) {
        this.idUsuario = idUsuario;
        this.contrasenia = contrasenia;
        this.correo = correo;
        this.rol = rol;
    }
    public Usuario(int idUsuario, String nombre, String contrasenia, String correo, String rol, Set comentarios, Set temas, Set marcadors) {
       this.idUsuario = idUsuario;
       this.nombre = nombre;
       this.contrasenia = contrasenia;
       this.correo = correo;
       this.rol = rol;
       this.comentarios = comentarios;
       this.temas = temas;
       this.marcadors = marcadors;
    }
   
    public int getIdUsuario() {
        return this.idUsuario;
    }
    
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getContrasenia() {
        return this.contrasenia;
    }
    
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    public String getCorreo() {
        return this.correo;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getRol() {
        return this.rol;
    }
    
    public void setRol(String rol) {
        this.rol = rol;
    }
    public Set getComentarios() {
        return this.comentarios;
    }
    
    public void setComentarios(Set comentarios) {
        this.comentarios = comentarios;
    }
    public Set getTemas() {
        return this.temas;
    }
    
    public void setTemas(Set temas) {
        this.temas = temas;
    }
    public Set getMarcadors() {
        return this.marcadors;
    }
    
    public void setMarcadors(Set marcadors) {
        this.marcadors = marcadors;
    }




}



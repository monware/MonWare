/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

//import modelo.Rol;
import modelo.Usuario;
import modelo.UsuarioDAO;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.security.SecureRandom;
import java.math.BigInteger;

/**
 *
 * @author jonathan
 */
@ManagedBean
@RequestScoped
public class AgregarInformador {
    private String correo;
    private String nombre;
    private String apaterno;  
    private String amaterno;
    private String contrasenia;
    private String rol;
    
     private Set temas = new HashSet(0);
     private Set marcadors = new HashSet(0);
     private Set comentarios = new HashSet(0);


    public AgregarInformador(String correo, String nombre, String apaterno, String amaterno, String contrasenia, String rol, Set temas, Set marcadors, Set comentarios) {
       this.correo = correo;
       this.nombre = nombre;
       this.apaterno = apaterno;
       this.amaterno = amaterno;
       this.contrasenia = contrasenia;
       this.rol = rol;
       this.temas = temas;
       this.marcadors = marcadors;
       this.comentarios = comentarios;
    }
    
    
    public void agregarInformador(String correo, String nombre, String apaterno, String amaterno){
        Usuario u = new Usuario();
        u.setNombre(nombre);
        u.setCorreo(correo);
        u.setApaterno(apaterno);
        u.setAmaterno(amaterno);
        u.setContrasenia(generaContrasenia(10));  
        u.setRol("Informador");
            
        UsuarioDAO udb = new UsuarioDAO();
        udb.save(u);
        
    }

    private String generaContrasenia(int i) {
      SecureRandom srand = new SecureRandom();
      String contrasenia = new BigInteger(130, srand).toString(32);
      return contrasenia.substring(0,i);
    }

    
    
}
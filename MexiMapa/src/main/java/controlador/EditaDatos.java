/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import modelo.UsuarioDAO;
import modelo.Usuario;

/**
 *
 * @author lizbethac
 */
public class EditaDatos {
 
    private String nombre;  
    private String correo;
    private String contrasenia;    

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    public void editaDatos(Usuario u){
    UsuarioDAO udb = new UsuarioDAO();
    u=udb.buscaPorCorreo(u.getCorreo());
    if(u!=null){
    u.setNombre(nombre);
    u.setCorreo(correo);
    u.setContrasenia(contrasenia);    
    udb.update(u);
      }
    }
}

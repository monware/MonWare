/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modelo.UsuarioDAO;
import modelo.Usuario;
import javax.faces.application.FacesMessage;

/**
 *
 * @author lizbethac
 */
@ManagedBean
@ViewScoped
public class EditaDatos{
 
    private String nombre;
    private String apaterno;
    private String amaterno;
    private String correo;
    private String contrasenia;  

    public String getApaterno() {
        return apaterno;
    }

    public void setApaterno(String apaterno) {
        this.apaterno = apaterno;
    }

    public String getAmaterno() {
        return amaterno;
    }

    public void setAmaterno(String amaterno) {
        this.amaterno = amaterno;
    }
      

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public void editaDatos(){
        Usuario u = new Usuario();
        UsuarioDAO udb = new UsuarioDAO();
        ControladorSesion.UserLogged us= (ControladorSesion.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("comentarista");
        u = udb.buscaPorCorreo(us.getCorreo());
        if(u!=null){ 
            if (nombre != ""){          
            u.setNombre(nombre);
            }
            if(apaterno!=""){
                u.setApaterno(apaterno);
            }
            if(amaterno!=""){
                u.setAmaterno(amaterno);
            }
            if(contrasenia != ""){
                u.setContrasenia(contrasenia); 
            }
            udb.update(u);
            FacesMessage msg = new FacesMessage("Tus datos han sido actualizados");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
}

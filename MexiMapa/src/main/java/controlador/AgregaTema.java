/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import modelo.Tema;
import modelo.TemaDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import modelo.Usuario;
import modelo.UsuarioDAO;

/**
 *
 * @author jorge
 */
@ManagedBean
public class AgregaTema {
    private String nombre;
  
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    public void agregraTema(){
        Tema tema = new Tema();
        TemaDAO daoTema = new TemaDAO();
        UsuarioDAO daoUsuario = new UsuarioDAO();
        ControladorSesion.UserLogged us= (ControladorSesion.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        Usuario usuarioA = daoUsuario.buscaPorCorreo(us.getCorreo());
        tema.setNombre(nombre);
        tema.setUsuario(usuarioA);
        daoTema.save(tema);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import com.mycompany.prueba.Tema;
import com.mycompany.prueba.TemaDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import com.mycompany.prueba.Usuario;
import com.mycompany.prueba.UsuarioDAO;

/**
 *
 * @author jorge
 */
@ManagedBean
public class AgregaTema {
    private String nombre;
    private String color;
  
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
    //Falta Agregarle a que marcador pertenece.
    public void agregraTema(){
        Tema tema = new Tema();
        TemaDAO daoTema = new TemaDAO();
        UsuarioDAO daoUsuario = new UsuarioDAO();
        //Usuario aux = daoUsuario.find(3);
        ControladorSesion.UserLogged us= (ControladorSesion.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("informador");
        Usuario usuarioA = daoUsuario.buscaPorCorreo(us.getCorreo());
        tema.setNombre(nombre);
        tema.setColor(color);
        tema.setUsuario(usuarioA);
        daoTema.save(tema);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.inject.Named;
import javax.enterprise.context.Dependent;
import com.mycompany.prueba.Usuario;
import com.mycompany.prueba.UsuarioDAO;
import com.mycompany.prueba.Comentario;
import com.mycompany.prueba.ComentarioDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.faces.context.FacesContext;

/**
 * 
 * @author yisus
 * @author ALEX
 * 
 */

@ManagedBean
@ViewScoped
@Named(value = "eliminarComentarista")
@Dependent

public class EliminarComentarista implements Serializable{
    
    private String correo;
    private List<Usuario> listaComentaristas;

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    
    /**
     * 
     */
    public void eliminaComentarista(){
    
        UsuarioDAO udao = new UsuarioDAO();
        ComentarioDAO cdao = new ComentarioDAO();
        EliminaComentario c = new EliminaComentario();
        Usuario u = udao.buscaPorCorreo(this.correo);
        if(u!=null){
            List<Comentario> comentarios = cdao.ObtenComentarioPorUsuario(this.correo);
            for(Comentario comentario : comentarios){
                c.setIdComentario(comentario.getIdcomentario());
                c.eliminaComentarioAdministrador();
            }
        udao.delete(u);
        }
    }
    
       @PostConstruct
    public void listaInformadores() {
        UsuarioDAO uda = new UsuarioDAO();
        this.listaComentaristas = uda.listaComentaristas();
        
        
    }

    public List<Usuario> getListaComentaristas() {
        return listaComentaristas;
    }

}

    


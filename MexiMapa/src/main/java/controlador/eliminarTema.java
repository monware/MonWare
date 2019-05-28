/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import com.mycompany.prueba.Comentario;
import com.mycompany.prueba.ComentarioDAO;
import com.mycompany.prueba.Marcador;
import com.mycompany.prueba.MarcadorDAO;
import com.mycompany.prueba.Tema;
import com.mycompany.prueba.TemaDAO;
import com.mycompany.prueba.Usuario;
import com.mycompany.prueba.UsuarioDAO;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;

/**
 *
 * @author yisus
 */
@Named(value = "eliminarTema1")
@Dependent
@ManagedBean
@ViewScoped

public class eliminarTema implements Serializable{
    private List<Tema> listaTemas;
    private String nombre_tema;

    public String getNombre_tema() {
        return nombre_tema;
    }

    public void setNombre_tema(String nombre_tema) {
        this.nombre_tema = nombre_tema;
    }
    
    @PostConstruct
    public void listaTemas() {
        TemaDAO tdao = new TemaDAO();
        this.listaTemas = tdao.listaTemas();
    }
    public List<Tema> getListaTemas() {
        return listaTemas;
    }
    
    public void eliminaTemaAdministrador(){
        
        TemaDAO daoTema = new TemaDAO();
        Tema tema = daoTema.find(this.getNombre_tema());
        if(tema != null){
            for(Object m:tema.getMarcadors()){
                MarcadorDAO daoMarcador = new MarcadorDAO();
                Marcador marcador = (Marcador) m;
                if(marcador.getComentarios() != null){
                    for(Object c : marcador.getComentarios()){
                        ComentarioDAO daoComentario = new ComentarioDAO();
                        Comentario comentario = (Comentario)c;
                        daoComentario.delete(comentario);
                    }
                }
            daoMarcador.delete(marcador);
            }
            daoTema.delete(tema);
             FacesMessage msg = new FacesMessage("El tema "+tema.getNombre()+" fue removido con exito.");
             FacesContext.getCurrentInstance().addMessage(null, msg);
        }else{
            System.out.println("No existe el tema");
        } 
    }
    
    public void eliminaTemaInformador(){
        UsuarioDAO daoUsuario = new UsuarioDAO();
        ControladorSesion.UserLogged us= (ControladorSesion.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("informador");
        Usuario usuarioA = daoUsuario.buscaPorCorreo(us.getCorreo());
        
        for(Object prueba: usuarioA.getTemas()){
            TemaDAO temadao = (TemaDAO) prueba;
            Tema tema = temadao.find(this.getNombre_tema());
            if(tema != null){
                for(Object m: tema.getMarcadors()){
                    MarcadorDAO daoMarcador = new MarcadorDAO();
                    Marcador marcador = (Marcador) m;
                    if(marcador.getComentarios() != null){
                        for(Object c : marcador.getComentarios()){
                            ComentarioDAO daoComentario = new ComentarioDAO();
                            Comentario comentario = (Comentario)c;
                            daoComentario.delete(comentario);
                        }
                    }
                    daoMarcador.delete(marcador);
                }
                temadao.delete(tema);
                FacesMessage msg = new FacesMessage("El tema "+tema.getNombre()+" fue removido con exito.");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
    }

    
    public List<Tema> getListaTemasUsuario() {
        UsuarioDAO daoUsuario = new UsuarioDAO();
        ControladorSesion.UserLogged us= (ControladorSesion.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("informador");
        TemaDAO tdao = new TemaDAO();
        this.listaTemas = tdao.ObtenTemasPorUsuario(us.getCorreo());
        return listaTemas;
    }
}

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
import modelo.Comentario;
import modelo.ComentarioDAO;
import modelo.Marcador;
import modelo.MarcadorDAO;
import modelo.Tema;
import modelo.TemaDAO;
import modelo.UsuarioDAO;
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

public class EliminarTema implements Serializable{
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
            this.listaTemas.remove(tema);
             FacesMessage msg = new FacesMessage("El tema "+tema.getNombre()+" fue removido con exito.");
             FacesContext.getCurrentInstance().addMessage(null, msg);
        }else{
            System.out.println("No existe el tema");
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

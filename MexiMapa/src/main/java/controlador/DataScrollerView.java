/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import modelo.Comentario;
import modelo.Usuario;

/**
 *
 * @author yisus tomada plantilla de java prime faces
 */
    @ManagedBean
@ViewScoped
public class DataScrollerView implements Serializable {
     
    private List<Comentario> comentarios;
    private Usuario u;     
    
    @ManagedProperty("#{ObtenerComentarios}")
    private ObtenerComentarios service;
     
    @PostConstruct
    public void init() {
        comentarios = service.creaArregloComentarios(u);
    }
 
    public void setService(ObtenerComentarios service) {
        this.service = service;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public Usuario getUsuario() {
        return u;
    }

    public void setUsuario(Usuario u) {
        this.u = u;
    }
    
}

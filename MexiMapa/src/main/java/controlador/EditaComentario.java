/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.faces.application.FacesMessage;
import modelo.Comentario;
import modelo.ComentarioDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import modelo.Usuario;
import modelo.UsuarioDAO;
/**
 *
 * @author jorge
 */
@ManagedBean
public class EditaComentario {
    private int idComentario;
    private String comentario;

    public int getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    public void editaComentario(){
        UsuarioDAO daoUsuario = new UsuarioDAO();
        ControladorSesion.UserLogged us= (ControladorSesion.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("comentarista");
        Usuario usuarioA = daoUsuario.buscaPorCorreo(us.getCorreo());
        for(Object ecomen:usuarioA.getComentarios()){
            Comentario comentarios = (Comentario) ecomen;
            ComentarioDAO comentariodao = new ComentarioDAO();
            comentariodao.find(idComentario);
            if(comentariodao != null){
                comentarios.setComentario(comentario);
                comentariodao.update(comentarios);
                FacesMessage msg = new FacesMessage("El Comentario se modifico correctamente");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }else{
                FacesMessage msg = new FacesMessage("hubó un pequeño error avisa a los administradores");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
    }
    
}

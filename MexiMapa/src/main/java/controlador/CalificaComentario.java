/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
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
public class CalificaComentario {
    private int idComentario;
    private int calificacion;

    public int getIdComentario() {
        return idComentario;
    }

    public float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }
    
    public void calificaComentario(){
        UsuarioDAO daoUsuario = new UsuarioDAO();
        ControladorSesion.UserLogged us= (ControladorSesion.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("comentarista");
        Usuario usuarioA = daoUsuario.buscaPorCorreo(us.getCorreo());
        for(Object coment:usuarioA.getComentarios()){
            ComentarioDAO cdao = new ComentarioDAO();
            Comentario comentario = (Comentario) coment;
            comentario = cdao.find(idComentario);
            if(comentario != null){
                comentario.setCalificacion(calificacion);
                cdao.update(comentario);
            }
        }
    }
}

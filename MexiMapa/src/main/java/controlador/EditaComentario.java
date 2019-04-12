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
public class EditaComentario {
    private int idComentario;

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
    private String comentario;
    


    public void editaComentario(){
        UsuarioDAO daoUsuario = new UsuarioDAO();
        ControladorSesion.UserLogged us= (ControladorSesion.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("comentarista");
        Usuario usuarioA = daoUsuario.buscaPorCorreo(us.getCorreo());
        for(Object ecomen:usuarioA.getComentarios()){
            ComentarioDAO comentariodao = (ComentarioDAO) ecomen;
            Comentario comentario1 =comentariodao.find(idComentario);
            if(comentario1 != null){
                comentario1.setComentario(comentario);
            }
        }
    }
    
}

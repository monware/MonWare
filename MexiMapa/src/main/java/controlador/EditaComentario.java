/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import modelo.Comentario;
import modelo.ComentarioDAO;
import javax.faces.bean.ManagedBean;
/**
 *
 * @author jorge
 */
@ManagedBean
public class EditaComentario {
    private String comentario;
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
    
    public void editaComentario(){
        ComentarioDAO cdao = new ComentarioDAO();
        Comentario u = cdao.find(idComentario);
        if(u != null){
            u.setComentario(comentario);
            cdao.update(u);
        }
        
    }
    
}

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
public class EliminaComentario {
    private int idComentario;
    
    public int getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }
    
    public void eliminaComentario(){
        //Usuario u = new Usuario();
        ComentarioDAO udao = new ComentarioDAO();
        Comentario u = udao.find(idComentario);
        if(u!=null){
            udao.delete(u);
        } 
    }
}

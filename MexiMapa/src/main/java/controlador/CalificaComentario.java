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
public class CalificaComentario {
    private int idComentario;
    private float calificacion;

    public int getIdComentario() {
        return idComentario;
    }

    public float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(float calificacion) {
        this.calificacion = calificacion;
    }

    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }
    
    public void calificaComentario(){
        ComentarioDAO cdao = new ComentarioDAO();
        Comentario u = cdao.find(idComentario);
        if(u != null){
            u.setCalificacion(calificacion);
            cdao.update(u);
        }
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import com.mycompany.prueba.Comentario;
import com.mycompany.prueba.ComentarioDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
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
    
    public void calificaComentario(int id, int cal){
        ComentarioDAO cdao = new ComentarioDAO();
        Comentario comentario = cdao.find(id);
        if(comentario != null){
            int aux = cal +1;
            comentario.setCalificacion(aux);
            cdao.update(comentario);
            FacesMessage msg = new FacesMessage("El comentario "+comentario.getComentario()+" Se calificó correctamente, corresponde al tema "+comentario.getMarcador().getTema());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        FacesMessage msg = new FacesMessage("Lo siento un error inesperado contacta con los desarrolladores para mas inf");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    public void descalificaComentario(int id, int cal){
        ComentarioDAO cdao = new ComentarioDAO();
        Comentario comentario = cdao.find(id);
        if(comentario != null&& cal > 0){
            int aux = cal -1;
            comentario.setCalificacion(aux);
            cdao.update(comentario);
            FacesMessage msg = new FacesMessage("Al comentario "+comentario.getComentario()+" Se le descalificó correctamente, corresponde al tema "+comentario.getMarcador().getTema());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }else{
            FacesMessage msg = new FacesMessage("Lo siento un error inesperado contacta con los desarrolladores para mas inf");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
}

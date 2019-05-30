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
import com.mycompany.prueba.Marcador;
import com.mycompany.prueba.MarcadorDAO;
import com.mycompany.prueba.Usuario;
import com.mycompany.prueba.UsuarioDAO;
import javax.faces.application.FacesMessage;
/**
 *
 * @author jorge
 */
@ManagedBean
public class AgregaComentario {
    private int idComentario;
    private Marcador marcador;
    private Usuario usuario;
    private String comentario;
    private int calificacion;

    public int getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }

    public Marcador getMarcador() {
        return marcador;
    }

    public void setMarcador(Marcador marcador) {
        this.marcador = marcador;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }
    
    public void agregaComentario(Usuario usuario,Marcador marcador,String comentario,Integer calificacion){
        Comentario comentar = new Comentario();
        ComentarioDAO daoComentario = new ComentarioDAO();
        comentar.setMarcador(marcador);
        comentar.setUsuario(usuario);
        comentar.setComentario(comentario);
        comentar.setCalificacion(calificacion); 
        daoComentario.save(comentar);
        FacesMessage msg = new FacesMessage("El comentario"+comentar.getIdcomentario() +" hecho por "+ comentar.getUsuario()+" fue agregado con exito.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
    }
}

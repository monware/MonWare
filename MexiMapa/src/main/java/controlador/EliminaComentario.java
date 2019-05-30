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
public class EliminaComentario {
    private int idComentario;
    private String correo;
    
    public int getIdComentario() {
        return idComentario;
    }
    
    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public void eliminaComentarioAdministrador(int id){
        ComentarioDAO udao = new ComentarioDAO();
        Comentario u = udao.find(id);
        if(u!=null){
            udao.delete(u);
        }
    }
    
    public void eliminaComentarioAdministrador(){
        ComentarioDAO udao = new ComentarioDAO();
        Comentario u = udao.find(idComentario);
        if(u!=null){
            udao.delete(u);
            FacesMessage msg = new FacesMessage("El comentario se elimino correctamente, que corresponde al tema Admi");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    public void eliminaComentarioComentarista(int id){
        UsuarioDAO daoUsuario = new UsuarioDAO();
        ControladorSesion.UserLogged us= (ControladorSesion.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("comentarista");
        Usuario usuarioA = daoUsuario.buscaPorCorreo(us.getCorreo());
        for(Object comen:usuarioA.getComentarios()){
            ComentarioDAO comendao = new ComentarioDAO();
            Comentario comentario = (Comentario) comen;
            comentario = comendao.find(id);
            if(comentario !=null){
                comendao.delete(comentario);
                FacesMessage msg = new FacesMessage("El comentario "+comentario.getComentario()+" Se elimino correctamente, que corresponde al tema "+comentario.getMarcador().getTema());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
    }
   
    public void eliminaComentarioInformador(int id){
        UsuarioDAO daoUsuario = new UsuarioDAO();
        ControladorSesion.UserLogged us= (ControladorSesion.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("informador");
        Usuario usuarioA = daoUsuario.buscaPorCorreo(us.getCorreo());
        for(Object marca:usuarioA.getMarcadors()){
            MarcadorDAO marcadordao = (MarcadorDAO) marca;
            Marcador marcador = marcadordao.find(idComentario);
            for(Object coment:marcador.getComentarios()){
                ComentarioDAO comendao = new ComentarioDAO();
                Comentario comentario = (Comentario) coment;
                comentario = comendao.find(id);
                if(comentario !=null){
                    comendao.delete(comentario);
                    FacesMessage msg = new FacesMessage("El comentario "+comentario.getComentario()+" Se elimino correctamente, que corresponde al tema "+comentario.getMarcador().getTema());
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                }
            }
        }
    }
}

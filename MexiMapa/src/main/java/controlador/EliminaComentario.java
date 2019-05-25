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
import com.mycompany.prueba.Tema;
import com.mycompany.prueba.TemaDAO;
import com.mycompany.prueba.Usuario;
import com.mycompany.prueba.UsuarioDAO;
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
    
    //Falta otro para administrador
    
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
        }
    }
    
    //Idea: Como el comentarista puede eliminar sus comentarios solo buscamos en su lista de comentarios asociados y elimine el que guste.
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
            }
        }
    }
    //Idea: Como el Inofrmador puede Eliminar de los Temas los que quiera Obtenemos lo siguiente.
    public void eliminaComentarioInformador(int id){
        UsuarioDAO daoUsuario = new UsuarioDAO();
        ControladorSesion.UserLogged us= (ControladorSesion.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("informador");
        Usuario usuarioA = daoUsuario.buscaPorCorreo(us.getCorreo());
        for(Object marca:usuarioA.getMarcadors()){
            MarcadorDAO marcadordao = (MarcadorDAO) marca;
            //pensar como modificar para recibir el marcador deseado puedo pasarle el id de los marcadores
            //y ahi buscar los comentarios que quiera :/
            Marcador marcador = marcadordao.find(idComentario); //id Marcador
            //if(marcador!= null) si es que sigo con esa idea
            for(Object coment:marcador.getComentarios()){
                ComentarioDAO comendao = new ComentarioDAO();
                Comentario comentario = (Comentario) coment;
                comentario = comendao.find(id);
                if(comentario !=null){
                    comendao.delete(comentario);
                }
            }
        }
    }
}

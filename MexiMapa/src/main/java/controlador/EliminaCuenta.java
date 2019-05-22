/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import com.mycompany.prueba.Comentario;
import com.mycompany.prueba.ComentarioDAO;
import com.mycompany.prueba.Usuario;
import com.mycompany.prueba.UsuarioDAO;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
/**
 *
 * @author lizbethac
 */
@ManagedBean
@SessionScoped
@ViewScoped
public class EliminaCuenta{
    private int idusuario;

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }
    
    public String eliminaCuenta(){     
         UsuarioDAO daoUsuario = new UsuarioDAO();
         ComentarioDAO cdao = new ComentarioDAO();
        EliminaComentario c= new EliminaComentario();
        ControladorSesion.UserLogged us= (ControladorSesion.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("comentarista");
        Usuario usuarioA = daoUsuario.buscaPorCorreo(us.getCorreo());
        if(usuarioA!=null){
            List<Comentario> comentarios = cdao.ObtenComentarioPorUsuario(us.getCorreo());
            for(Comentario comentario : comentarios){
                c.setIdComentario(comentario.getIdcomentario());
                c.eliminaComentarioAdministrador();
            }
            daoUsuario.delete(usuarioA);
        }
        return "/index?faces-redirect=true";
    }
    
    public String cancelar(){ 
        return "/index?faces-redirect=true";
    }       
}

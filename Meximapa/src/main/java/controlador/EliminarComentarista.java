/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.mycompany.prueba.Usuario;
import com.mycompany.prueba.UsuarioDAO;
import com.mycompany.prueba.Comentario;
import com.mycompany.prueba.ComentarioDAO;
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author ALEX
 */
@ManagedBean
public class EliminarComentarista {
    
    private String correo;

    /**
     * 
     * @return 
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * 
     * @param correo 
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    /**
     * 
     */
    public void eliminaComentarista(){
    
        UsuarioDAO udao = new UsuarioDAO();
        ComentarioDAO cdao = new ComentarioDAO();
        Comentario comentario = new Comentario();
        Usuario u = udao.buscaPorCorreo(correo);
        
        if(u!=null){
        udao.delete(u);
        }
    }
}

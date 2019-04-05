/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.Usuario;
import modelo.UsuarioDAO;
import modelo.Comentario;
import modelo.ComentarioDAO;
import java.util.Set;
import java.util.HashSet;
import Java.util.Iterator;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author ALEX
 */
@ManagedBean
public class EliminarComentarista {
    
    private String correo;

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public void eliminaComentarista(){
    
        UsuarioDAO udao = new UsuarioDAO();
        Usuario u = udao.find(correo);
        Set comentarios = new HashSet(u.getComentarios());
        Iterator iterador = comentarios.next();
        while(comentarios.hasNext()){
            //llamar e elimina comentario

        if(u!=null){
        udao.delete(u);
        }
    }
}

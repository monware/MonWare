/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import com.mycompany.prueba.Comentario;
import com.mycompany.prueba.Usuario;

/**
 *
 * @author yisus
 */
@ManagedBean(name = "carService")
@ApplicationScoped
public class ObtenerComentarios {
     
    //public Usuario comentarista;
     
    public List<Comentario> creaArregloComentarios(Usuario u) {
        List<Comentario> list = new ArrayList<Comentario>();
        Comentario[] comentarios = (Comentario[]) u.getComentarios().toArray();
        for(int i = 0 ; i < u.getComentarios().size() ; i++) {
            list.add(comentarios[i]);
        }
         
        return list;
    }
}
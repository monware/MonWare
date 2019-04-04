/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import javax.faces.bean.ManagedBean;
import modelo.Usuario;
import modelo.UsuarioDAO;
/**
 *
 * @author jorge
 */
@ManagedBean
public class EliminarInformador {
    private String correo;
    
    public String getIdInformador() {
        return correo;
    }

    
    public void eliminaComentario(){
        //Usuario u = new Usuario();
        UsuarioDAO udb = new UsuarioDAO();
        Usuario u = udb.find(correo);
        if(u!=null){
            if(u.getRol() == "Informador")
            udb.delete(u);
        } 
    }
}

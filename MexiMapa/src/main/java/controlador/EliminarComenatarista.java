/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.Usuario;
import modelo.UsuarioDAO;

/**
 *
 * @author ALEX
 */
public class EliminarComenatarista {
    
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
        if(u!=null){
        udao.delete(u);
        }
    }
}

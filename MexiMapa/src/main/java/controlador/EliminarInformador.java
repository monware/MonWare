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
    
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    

    
    public void eliminaInformador(){
        //Usuario u = new Usuario();
        UsuarioDAO udb = new UsuarioDAO();
        Usuario u = udb.find(correo);
        System.out.println("pruebdsdsd1");
        String prueba = u.getRol();
        System.out.println("pruebdsdsd");
        System.out.println("Pruebas" + prueba);
        if(u!=null){
            if(u.getRol() == "INFORMADOR")
            udb.delete(u);
        } 
    }
}

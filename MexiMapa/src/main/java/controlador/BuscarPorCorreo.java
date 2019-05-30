/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;


import com.mycompany.prueba.Usuario;
import com.mycompany.prueba.UsuarioDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author jorge
 */
@ManagedBean
@SessionScoped
//@RequestScoped
public class BuscarPorCorreo {
    private String correo;
    private String contrasenia;
    private Usuario resultado;

    public Usuario getResultado() {
        return resultado;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    
    public Usuario buscaPorCorreo(){
        if(correo.equals("") && contrasenia.equals(""))
            return null;
        UsuarioDAO ubd = new UsuarioDAO();
        resultado =  ubd.buscaPorCorreoContrasenia(correo,contrasenia);
        return resultado;
    }
}
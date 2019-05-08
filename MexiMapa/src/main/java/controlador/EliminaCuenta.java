/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import com.mycompany.prueba.Usuario;
import com.mycompany.prueba.UsuarioDAO;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
/**
 *
 * @author lizbethac
 */
@ManagedBean
@SessionScoped
public class EliminaCuenta{
    private int idusuario;

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }
    
    public void eliminaCuenta(){     
        UsuarioDAO udao = new UsuarioDAO();
        Usuario u = udao.find("Algo@alg.com");
        if(u!=null){
            udao.delete(u);
        }
    }
    
    public String cancelar(){ 
        return "/index?faces-redirect=true";
    }
            
}

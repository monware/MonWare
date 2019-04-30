/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import java.util.List;
import javax.faces.bean.ManagedBean;
import modelo.Tema;
import modelo.TemaDAO;
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
        UsuarioDAO udb = new UsuarioDAO();
        TemaDAO tdb = new TemaDAO();
        Usuario u = udb.buscaPorCorreo(correo);
        EliminaTema tema = new EliminaTema();
        if(u!=null){
            if(u.getRol() == 3){
            List<Tema> listaTemas = tdb.ObtenTemasPorUsuario(u.getNombre());
            for(int i=0;i<listaTemas.size();i++){
                tema.setNombre(listaTemas.get(i).getNombre());
                tema.eliminaTemaAdministrador();
            }
            udb.delete(u);
            }
        }
        } 
    }


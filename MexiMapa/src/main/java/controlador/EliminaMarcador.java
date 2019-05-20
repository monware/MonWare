/*
    public void setTema(Tema tema){
	this.tema = tema;
    }
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;
import com.mycompany.prueba.Marcador;
import com.mycompany.prueba.MarcadorDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import com.mycompany.prueba.Comentario;
import com.mycompany.prueba.ComentarioDAO;
import com.mycompany.prueba.Usuario;
import com.mycompany.prueba.UsuarioDAO;

/**
 *
 *@author lizbethac
 */
@ManagedBean
public class EliminaMarcador{
    private int idMarcador;    

    public int getIdMarcador(){
	return idMarcador;
    } 

    public void setIdMarcador(int idMarcador){
	this.idMarcador = idMarcador;
    }   

    public void eliminaMarcador(){
        UsuarioDAO daoUsuario = new UsuarioDAO();
        ControladorSesion.UserLogged us= (ControladorSesion.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("informador");
        Usuario usuarioA = daoUsuario.buscaPorCorreo(us.getCorreo());
         
        for(Object prueba: usuarioA.getMarcadors()){
            MarcadorDAO daoMarcador = new MarcadorDAO();
            Marcador marcador = new Marcador();
            for(Object c : marcador.getComentarios()){
                ComentarioDAO daoComentario = new ComentarioDAO();
                Comentario comentario = (Comentario)c;
                daoComentario.delete(comentario);
            }            
            daoMarcador.delete(marcador);
        }
    }
        
    }
    

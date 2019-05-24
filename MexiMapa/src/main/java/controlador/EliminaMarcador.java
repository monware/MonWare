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
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

/**
 *
 *@author lizbethac
 */
@ManagedBean
@ViewScoped
@Named(value = "eliminarMarcador")
@Dependent
public class EliminaMarcador{
    private int idMarcador;    
    private List<Marcador> listaMarcadores;

    public int getIdMarcador(){
	return idMarcador;
    } 

    public void setIdMarcador(int idMarcador){
	this.idMarcador = idMarcador;
    }   
       
        
 @PostConstruct
    public void listaMarcadores() {
        MarcadorDAO mdao = new MarcadorDAO();
        UsuarioDAO udao = new UsuarioDAO();
        ControladorSesion.UserLogged us= (ControladorSesion.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("informador");
        Usuario u = udao.buscaPorCorreo(us.getCorreo());
        this.listaMarcadores = mdao.listaMarcadores(u);
    }

    public List<Marcador> getListaMarcadores() {
        return listaMarcadores;
    }
    
        public void eliminaMarcador(){
        UsuarioDAO udao = new UsuarioDAO();
        ControladorSesion.UserLogged us= (ControladorSesion.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("informador");
        Usuario u = udao.buscaPorCorreo(us.getCorreo());
        ComentarioDAO cdao = new ComentarioDAO();
        MarcadorDAO daoMarcador = new MarcadorDAO();
        Marcador marcador = new Marcador();
        
        if(u!=null){
              MarcadorDAO mdao = new MarcadorDAO();
        Marcador m = mdao.find(idMarcador);
        if(m!=null){
            for(Object c : m.getComentarios()){
                ComentarioDAO daoComentario = new ComentarioDAO();
                Comentario comentario = (Comentario)c;
                daoComentario.delete(comentario);
            }            
            mdao.delete(m);
        }
            listaMarcadores.remove(marcador.getIdmarcador());
        }
}
    public void eliminaMarcadorAdministrador(){
         
        MarcadorDAO mdao = new MarcadorDAO();
        Marcador m = mdao.find(idMarcador);
        if(m!=null){
            for(Object c : m.getComentarios()){
                ComentarioDAO daoComentario = new ComentarioDAO();
                Comentario comentario = (Comentario)c;
                daoComentario.delete(comentario);
            }            
            mdao.delete(m);
        }
    }
        
    }
    

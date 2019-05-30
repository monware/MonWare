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
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

/**
 *
 *@author lizbethac
 */
@ManagedBean
@ViewScoped
@Named(value = "eliminarMarcador")
public class EliminaMarcador implements Serializable{
    private List<Marcador> listaMarcadores;
    private Marcador marcador;
    private int idMarcador;
      
    public int getIdMarcador() {
        return idMarcador;
    }
    
    public void setIdMarcador(int idMarcador) {
       this.idMarcador = idMarcador;
    }
    
    public Marcador getMarcador() {
        return marcador;
    }

    public void setMarcador(Marcador marcador) {
        this.marcador = marcador;
    }

    @PostConstruct
    public void listaMarcadores() {
        MarcadorDAO tdao = new MarcadorDAO();
        this.listaMarcadores = tdao.listaMarcadores();
    }

    public List<Marcador> getListaMarcador() {
        return listaMarcadores;
    }

    public void eliminaMarcadorInformador(){
        MarcadorDAO daoMarcador = new MarcadorDAO();
        Marcador marcador = daoMarcador.find(this.getIdMarcador());
        if(marcador.getComentarios() != null){
            ComentarioDAO daoComentario = new ComentarioDAO();
            for(Object c:marcador.getComentarios()){
                Comentario comentario = (Comentario)c;
                daoComentario.delete(comentario);
            }
        }
        daoMarcador.delete(marcador);
        this.listaMarcadores.remove(marcador);
        FacesMessage msg = new FacesMessage("El marcador"+ marcador +" fue removido con exito.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public List<Marcador> getListaMarcadoresUsuario() {
        ControladorSesion.UserLogged us= (ControladorSesion.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("informador");
        MarcadorDAO mdao = new MarcadorDAO();
        this.listaMarcadores = mdao.ObtenMarcadoresPorUsuario(us.getCorreo());
        return listaMarcadores;
    }

    public void eliminaMarcadorAdministrador(){
        MarcadorDAO daoMarcador = new MarcadorDAO();
        Marcador marcador = daoMarcador.find(this.getIdMarcador());
        if(marcador!= null){
            if(marcador.getComentarios() != null){
                for(Object c:marcador.getComentarios()){
                    ComentarioDAO daoComentario = new ComentarioDAO();
                    Comentario comentario = (Comentario)c;
                    daoComentario.delete(comentario);
                }
            }
            daoMarcador.delete(marcador);
            FacesMessage msg = new FacesMessage("El Marcador "+marcador.getDescripcion()+" fue removido con exito.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
}
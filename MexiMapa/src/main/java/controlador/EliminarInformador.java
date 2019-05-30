/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.Comentario;
import modelo.ComentarioDAO;
import modelo.Marcador;
import modelo.MarcadorDAO;
import modelo.Tema;
import modelo.TemaDAO;
import javax.faces.bean.ManagedBean;
import modelo.Usuario;
import modelo.UsuarioDAO;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author yisus
 */

@ManagedBean
@ViewScoped
@Named(value = "eliminarInformador")
@Dependent

public class EliminarInformador implements Serializable{
    private List<Usuario> listaInformadores;
    private String correo;

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public void eliminaInformador(){
        UsuarioDAO udao = new UsuarioDAO();
        Usuario u = udao.buscaPorCorreo(this.correo);
        TemaDAO tdao = new TemaDAO();
        EliminarTema t = new EliminarTema();
        ComentarioDAO cdao = new ComentarioDAO();
        EliminaComentario c = new EliminaComentario();
        MarcadorDAO mdao = new MarcadorDAO();
        EliminaMarcador m = new EliminaMarcador();
        if(u!=null){
            List<Tema> temasInformador = tdao.ObtenTemasPorUsuario(u.getCorreo());
            if(!temasInformador.isEmpty()){
                for(Tema tema : temasInformador){
                    t.setNombre_tema(tema.getNombre());
                    t.eliminaTemaAdministrador(); 
                }
            }
        List<Marcador> marcadoresInformador = mdao.ObtenMarcadoresPorUsuario(u.getCorreo());
        
        if(!marcadoresInformador.isEmpty()){
            for(Marcador marc : marcadoresInformador){
                m.setIdMarcador(marc.getIdmarcador());
                m.eliminaMarcadorAdministrador();
            }
        }
        List<Comentario> comentariosInformador = cdao.ObtenComentarioPorUsuario(u.getCorreo());
        if(!comentariosInformador.isEmpty()){
            for(Comentario comen : comentariosInformador){
                c.setIdComentario(comen.getIdcomentario());
                c.eliminaComentarioAdministrador();
            }
        }
        udao.delete(u);
        listaInformadores.remove(u);
        FacesMessage msg = new FacesMessage("El informador"+u.getNombre()+u.getApaterno()+" fue removido con exito.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    @PostConstruct
    public void listaInformadores() {
        UsuarioDAO uda = new UsuarioDAO();
        this.listaInformadores = uda.listaInformadores();
    }

    public List<Usuario> getListaInformadores() {
        return listaInformadores;
    }
}

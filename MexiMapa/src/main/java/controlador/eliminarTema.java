/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import com.mycompany.prueba.Comentario;
import com.mycompany.prueba.ComentarioDAO;
import com.mycompany.prueba.Marcador;
import com.mycompany.prueba.MarcadorDAO;
import com.mycompany.prueba.Tema;
import com.mycompany.prueba.TemaDAO;
import com.mycompany.prueba.Usuario;
import com.mycompany.prueba.UsuarioDAO;

/**
 *
 * @author yisus
 */
@Named(value = "eliminarTema1")
@Dependent
@ManagedBean
@ViewScoped

public class eliminarTema implements Serializable{

    /**
     * Creates a new instance of eliminaTema
     */
    
    private List<SelectItem> listaTemas;
    private Tema tema;

    public eliminarTema() {
        tema = new Tema();
    }
    
    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }
    
    public void eliminaTemaAdministrador(){
        TemaDAO daoTema = new TemaDAO();
        Tema tema = daoTema.find(this.tema.getNombre());
        if(tema != null){
            for(Object m:tema.getMarcadors()){
                MarcadorDAO daoMarcador = new MarcadorDAO();
                Marcador marcador = (Marcador) m;
                if(marcador.getComentarios() != null){
                    for(Object c : marcador.getComentarios()){
                        ComentarioDAO daoComentario = new ComentarioDAO();
                        Comentario comentario = (Comentario)c;
                        daoComentario.delete(comentario);
                    }
                }
            daoMarcador.delete(marcador);
            }
            daoTema.delete(tema);
        }else{
            System.out.println("No existe el tema");
        } 
    }
    
    public void eliminaTemaInformador(){
        UsuarioDAO daoUsuario = new UsuarioDAO();
        ControladorSesion.UserLogged us= (ControladorSesion.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("informador");
        Usuario usuarioA = daoUsuario.buscaPorCorreo(us.getCorreo());
        
        for(Object prueba: usuarioA.getTemas()){
            TemaDAO temadao = (TemaDAO) prueba;
            Tema tema = temadao.find(this.tema.getNombre());
            if(tema != null){
                for(Object m: tema.getMarcadors()){
                    MarcadorDAO daoMarcador = new MarcadorDAO();
                    Marcador marcador = (Marcador) m;
                    if(marcador.getComentarios() != null){
                        for(Object c : marcador.getComentarios()){
                            ComentarioDAO daoComentario = new ComentarioDAO();
                            Comentario comentario = (Comentario)c;
                            daoComentario.delete(comentario);
                        }
                    }
                    daoMarcador.delete(marcador);
                }
                temadao.delete(tema);
            }
        }
    }

    public List<SelectItem> getListaTemas() {
        this.listaTemas = new ArrayList<SelectItem>();
        TemaDAO tdb = new TemaDAO();
        List<Tema> p = tdb.listaTemas();
        listaTemas.clear();
        
        for(Tema temas : p){
            SelectItem temaItem = new SelectItem(temas.getNombre(), temas.getNombre()); 
            this.listaTemas.add(temaItem);
    }
        System.out.println(listaTemas);
        return listaTemas;
    }
    
    
    public List<SelectItem> getListaTemasUsuario() {
        
        UsuarioDAO daoUsuario = new UsuarioDAO();
        ControladorSesion.UserLogged us= (ControladorSesion.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("informador");
        
        this.listaTemas = new ArrayList<SelectItem>();
        TemaDAO tdb = new TemaDAO();
        List<Tema> p = tdb.ObtenTemasPorUsuario(us.getCorreo());
        listaTemas.clear();
        
        for(Tema temas : p){
            SelectItem temaItem = new SelectItem(temas.getNombre(), temas.getNombre()); 
            this.listaTemas.add(temaItem);
    }
        System.out.println(listaTemas);
        return listaTemas;
    }
}

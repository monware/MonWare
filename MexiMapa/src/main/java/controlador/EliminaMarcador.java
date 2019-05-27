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
import javax.faces.model.SelectItem;
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
    private List<SelectItem> listaMarcadores;
      private Marcador marcador;
      private int idMarcador;
      private String marcador_descripcion;
      
          public EliminaMarcador(){
        marcador = new Marcador();
    }

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

    public String getMarcador_descripcion() {
        return marcador_descripcion;
    }

    public void setMarcador_descripcion(String marcador_descripcion) {
        this.marcador_descripcion = marcador_descripcion;
    }
   
    
    public void eliminaMarcadorInformador(){
        UsuarioDAO daoUsuario = new UsuarioDAO();
        ControladorSesion.UserLogged us= (ControladorSesion.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("informador");
        Usuario usuarioA = daoUsuario.buscaPorCorreo(us.getCorreo());
        
        for(Object m:usuarioA.getMarcadors()){
            Marcador marcador = (Marcador) m;
            MarcadorDAO mdao = new MarcadorDAO();
            marcador = mdao.find(this.marcador.getIdmarcador());
            if(marcador!=null){
                for(Object c : marcador.getComentarios()){
                           ComentarioDAO comendao = new ComentarioDAO();
                           Comentario comentario = (Comentario) c;
                               comendao.delete(comentario);
                           }
                mdao.delete(marcador);
            }
        }
     }
      
    
    
        public List<SelectItem> getListaMarcadores(){
        this.listaMarcadores = new ArrayList<SelectItem>();
        MarcadorDAO tdb = new MarcadorDAO();
        List<Marcador> p = tdb.listaMarcadores();
        listaMarcadores.clear();
        
        for(Marcador marcadores : p){
            SelectItem temaItem = new SelectItem(marcadores.getDescripcion(), marcadores.getDescripcion()); 
            this.listaMarcadores.add(temaItem);
    }
        System.out.println(listaMarcadores);
        return listaMarcadores;
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
    

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
import javax.faces.application.FacesMessage;
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
        
        for(Object prueba: usuarioA.getMarcadors()){
            MarcadorDAO daoMarcador = new MarcadorDAO();
            Marcador marcador = (Marcador) prueba;
            marcador = daoMarcador.find(this.idMarcador);
            if(marcador != null){
                for(Object m: marcador.getComentarios()){
                    ComentarioDAO comendao = new ComentarioDAO();
                    Comentario comentario = (Comentario) m;
                    if(comentario !=null){
                        comendao.delete(comentario);
                    }
                }
                daoMarcador.delete(marcador);
            }
        }
    }
    
    
        public List<SelectItem> getListaMarcadores(){
        this.listaMarcadores = new ArrayList<SelectItem>();
        MarcadorDAO tdb = new MarcadorDAO();
        List<Marcador> p = tdb.listaMarcadores();
        listaMarcadores.clear();
        
        for(Marcador marcadores : p){
            SelectItem marcadorItem = new SelectItem(marcadores.getDescripcion(), marcadores.getDescripcion()); 
            this.listaMarcadores.add(marcadorItem);
    }
        System.out.println(listaMarcadores);
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
            }else{
                System.out.println("No existe el tema");  
            }
    }
}
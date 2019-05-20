/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.mycompany.prueba.Comentario;
import com.mycompany.prueba.ComentarioDAO;
import com.mycompany.prueba.Marcador;
import com.mycompany.prueba.MarcadorDAO;
import com.mycompany.prueba.Tema;
import com.mycompany.prueba.TemaDAO;
import javax.faces.bean.ManagedBean;
import com.mycompany.prueba.Usuario;
import com.mycompany.prueba.UsuarioDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
/**
 *
 * @author jorge
 */

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
        eliminarTema t = new eliminarTema();
        ComentarioDAO cdao = new ComentarioDAO();
        EliminaComentario c = new EliminaComentario();
        MarcadorDAO mdao = new MarcadorDAO();
        EliminaMarcador m = new EliminaMarcador();
        
        if(u!=null){
              List<Tema> temasInformador = tdao.ObtenTemasPorUsuario(u.getCorreo());
              for(Tema tema : temasInformador){
                t.setTema(tema);
                t.eliminaTemaAdministrador(); 
                }
              
              List<Marcador> marcadoresInformador = mdao.ObtenMarcadoresPorUsuario(u.getCorreo());
              for(Marcador marc : marcadoresInformador){
                m.setIdMarcador(marc.getIdmarcador());
                m.eliminaMarcador();
                }
              
              List<Comentario> comentariosInformador = cdao.ObtenComentarioPorUsuario(correo);
              for(Comentario comen : comentariosInformador){
                c.setIdComentario(comen.getIdcomentario());
                c.eliminaComentarioAdministrador();
                }
              
            System.out.println("eliminaInformador JAVA");
            udao.delete(u);
        }
          System.out.println("no elimino JAVA");
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

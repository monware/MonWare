/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import com.mycompany.prueba.Tema;
import com.mycompany.prueba.TemaDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import com.mycompany.prueba.Comentario;
import com.mycompany.prueba.ComentarioDAO;
import com.mycompany.prueba.Marcador;
import com.mycompany.prueba.MarcadorDAO;
import com.mycompany.prueba.Usuario;
import com.mycompany.prueba.UsuarioDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
/**
 *
 * @author jorge, Barajas
 */
@ManagedBean
@ViewScoped

public class EliminaTema implements Serializable{

    private List<SelectItem> listaTemas;
    private Tema tema;


    public EliminaTema(){
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
                System.out.println(marcador);
                if(marcador.getComentarios() != null){
                    for(Object c : marcador.getComentarios()){
                        ComentarioDAO daoComentario = new ComentarioDAO();
                        Comentario comentario = (Comentario) c;
                        System.out.println(comentario);
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
    
    public List<SelectItem> getListaTemas(){
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
    
}

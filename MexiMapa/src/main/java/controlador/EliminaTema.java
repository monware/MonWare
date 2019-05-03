/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import modelo.Tema;
import modelo.TemaDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import modelo.Comentario;
import modelo.ComentarioDAO;
import modelo.Marcador;
import modelo.MarcadorDAO;
import modelo.Usuario;
import modelo.UsuarioDAO;
/**
 *
 * @author jorge
 */
@ManagedBean
public class EliminaTema {
    private String nombre;
    private int idMarcador;
    private int idComentario;

    public int getIdMarcador() {
        return idMarcador;
    }

    public void setIdMarcador(int idMarcador) {
        this.idMarcador = idMarcador;
    }

    public int getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    //comentario, marcadores, tema 
    
    public void eliminaTemaAdministrador(){
        TemaDAO daoTema = new TemaDAO();
        Tema tema = daoTema.find(nombre);
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
            Tema tema = temadao.find(nombre);
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
}

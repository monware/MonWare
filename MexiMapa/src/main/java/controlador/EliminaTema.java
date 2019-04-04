/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import modelo.Tema;
import modelo.TemaDAO;
import javax.faces.bean.ManagedBean;
import modelo.Comentario;
import modelo.ComentarioDAO;
import modelo.Marcador;
import modelo.MarcadorDAO;
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
    
    public void eliminaTema(){
        //Usuario u = new Usuario();
        Comentario comentario = new Comentario();
        ComentarioDAO daoComentario = new ComentarioDAO();
        Marcador marcador = new Marcador();
        MarcadorDAO daoMarcador = new MarcadorDAO();
        TemaDAO daoTema = new TemaDAO();
        Marcador marc = new Marcador();
        //comentario, marcadores, tema 
        Tema u = daoTema.find(nombre);
        comentario = daoComentario.find(idComentario);
        marcador = daoMarcador.find(idMarcador);
        if(u!=null){
            daoComentario.delete(comentario);
            daoMarcador.delete(marcador);
            daoTema.delete(u);
        } 
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import java.util.List;
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
    //comentario, marcadores, tema 
    public void eliminaTema(){
        Tema tema = new Tema();
        TemaDAO daoTema = new TemaDAO();
        Marcador marcador = new Marcador();
        MarcadorDAO daoMarcador = new MarcadorDAO();
        Comentario comentario = new Comentario();
        ComentarioDAO daoComentario = new ComentarioDAO();
        tema = daoTema.find(nombre);
        List<Marcador> lst = daoMarcador.encuentraMarcadores(nombre);
        for(Marcador mar:lst){
            int aux = mar.getIdMarcador();
            List<Comentario> lsta = daoComentario.encuentraComentario(aux);
            for(Comentario com:lsta){
                comentario = daoComentario.find(com.getIdComentario());
                if(comentario!= null){
                daoComentario.delete(comentario);
                }   
            }
            marcador = daoMarcador.find(aux);
            if(marcador!= null){
                daoMarcador.delete(marcador);
            }
        }
        if(tema != null){
            daoTema.delete(tema);
        }
        
    }

}

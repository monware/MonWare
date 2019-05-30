/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modelo.Comentario;
import modelo.ComentarioDAO;
import javax.faces.bean.ViewScoped;
/**
 *
 * @author jorge
 */
@ManagedBean
@RequestScoped
@ViewScoped

public class ComentarioBeam {
    
    public Comentario comen;
    
    private static List<Comentario> lista = new ArrayList();

    public List<Comentario> getLista() {
        return lista;
    }
    
    public static void setLista(List<Comentario> lista) {
      ComentarioBeam.lista = lista;
    }
    public static void u(){
        ComentarioDAO comentarioDAO = new ComentarioDAO();
        ComentarioBeam.lista = comentarioDAO.buscaPorMarcador(VerMarcadores.select.getIdmarcador());
    }
    
}


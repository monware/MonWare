/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import com.mycompany.prueba.Comentario;
import com.mycompany.prueba.ComentarioDAO;
/**
 *
 * @author jorge
 */
@ManagedBean
@RequestScoped

public class ComentarioBeam {
    
    private static List<Comentario> lista = new ArrayList();

    public List<Comentario> getLista() {
        return lista;
    }

    public void setLista(List<Comentario> lista) {
        this.lista = lista;
    }
    
    @PostConstruct
    public void listar(){
        ComentarioDAO cdao = new ComentarioDAO();
        this.lista = cdao.findAll();
    }
        
    
}


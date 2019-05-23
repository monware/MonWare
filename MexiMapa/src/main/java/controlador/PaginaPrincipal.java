/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import com.mycompany.prueba.Marcador;
import com.mycompany.prueba.MarcadorDAO;
import com.mycompany.prueba.Tema;
import com.mycompany.prueba.TemaDAO;
import com.mycompany.prueba.UsuarioDAO;
import javax.annotation.PostConstruct;

/**
 *
 * @author yisus
 */

@ManagedBean
@RequestScoped
public class PaginaPrincipal {

     Tema tema;
     List<Marcador> marcadores;

     
     
    public List<Marcador> getMarcadores(){
         return marcadores;
     }
     
    public List<Tema> getAllTemas(){
        TemaDAO tdb = new TemaDAO();
        return tdb.findAll();
    }
    
    
    
    public void setTema(Tema tema) {
        this.tema = tema;
    }

    public Tema getTema() {
        return tema;
    }

    public void setMarcadores(List<Marcador> marcadores) {
        this.marcadores = marcadores;
    }
    
}

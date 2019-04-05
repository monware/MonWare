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
import modelo.Marcador;
import modelo.Tema;
import modelo.TemaDAO;

/**
 *
 * @author yisus
 */

@ManagedBean
@RequestScoped
public class PaginaPrincipal {
     Tema tema;
     String estado;
     Set<Marcador> marcadores;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
     
     
     public Set<Marcador> getMarcadores(){
         return tema.getMarcadors();
     }
     
    public List<Tema> getAllTemas(){
        TemaDAO tdb = new TemaDAO();
        return tdb.findAll();
        
    }
    
    public List<String> getAllEstados(){
        List<String> estados = Arrays.asList("Aguascalientes","Guanajuato","Zacatecas","Ciudad de Mexico","Chihuhua");
        return estados;
        
    }

     public void onCountryChange() {
        if(tema !=null && !tema.equals(""))
            marcadores = tema.getMarcadors();
        else
            marcadores = new HashSet(0);
    }
     
    public void setTema(Tema tema) {
        this.tema = tema;
    }

    public Tema getTema() {
        return tema;
    }

    public void setMarcadores(Set<Marcador> marcadores) {
        this.marcadores = marcadores;
    }
    
}

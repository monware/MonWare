/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.mycompany.prueba.Marcador;
import com.mycompany.prueba.MarcadorDAO;
import com.mycompany.prueba.Tema;
import com.mycompany.prueba.TemaDAO;
import com.mycompany.prueba.Usuario;
import com.mycompany.prueba.UsuarioDAO;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;
import org.primefaces.PrimeFaces;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author jonathan
 */
@ManagedBean
@ViewScoped
@Named(value = "verMarcadores")
@Dependent

public class VerMarcadores implements Serializable{
    
    private MapModel simpleModel;
    private Marker marker;
    private String marcadors;
    List<Tema> listaTemas;
    private Tema tema ;
    //private String nombre_tema;
   
    //@PostConstruct      
    public void VerMarcador(){
        simpleModel = new DefaultMapModel();
        MarcadorDAO mdb = new MarcadorDAO();
        List<Marcador> marcadores = mdb.findAll();
        for(Marcador m :marcadores){
            LatLng cord = new LatLng(m.getLatitud(),m.getLongitud());
            Marker marcador = new Marker(cord,m.getDescripcion());
            simpleModel.addOverlay(marcador);
        

      /**  simpleModel = new DefaultMapModel();
        MarcadorDAO mdb = new MarcadorDAO();
        List<Marcador> marcadores = mdb.ObtenMarcadoresPorTema(tema);
        for(Marcador m :marcadores){
            LatLng cord = new LatLng(m.getLatitud(),m.getLongitud());
            Marker marcador = new Marker(cord,m.getDescripcion());
            Marker prueba = new Marker(cord,m.getDatos());
            simpleModel.addOverlay(marcador);
            simpleModel.addOverlay(prueba);
        }*/
        }
        }
    
public List<Tema> listaTemas() {
        TemaDAO tdao = new TemaDAO();
        this.listaTemas = tdao.listaTemas();
        return this.listaTemas;
    }
    public List<Tema> getListaTemas() {
        return listaTemas;
    }
    
    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }
    
    public MapModel getSimpleModel() {
        return simpleModel;
    }
    
    public void onMarkerSelect(OverlaySelectEvent event) {
       marker =(Marker) event.getOverlay();
    }

    public Marker getMarker() {
        return marker;
    }

    public String getMarcadors() {
        return marcadors;
    }

    public void setMarcadors(String marcadors) {
        this.marcadors = marcadors;
    }
    
}

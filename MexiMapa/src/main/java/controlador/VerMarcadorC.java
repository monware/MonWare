/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import com.mycompany.prueba.Comentario;
import com.mycompany.prueba.ComentarioDAO;
import com.mycompany.prueba.Marcador;
import com.mycompany.prueba.MarcadorDAO;
import com.mycompany.prueba.Tema;
import com.mycompany.prueba.TemaDAO;
import com.mycompany.prueba.Usuario;
import com.mycompany.prueba.UsuarioDAO;
import org.primefaces.event.RateEvent;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author jorge
 */
@ManagedBean
@ViewScoped
public class VerMarcadorC implements Serializable{
    private MapModel simpleModel;
    private Marker marker;
    private Tema tema;
    private Usuario usuario;
    private Double latitud;
    private Double longitud;
    private String datos;
    private String comentario;
    private String descripcion;
    private Integer calificacion;
    public static Marcador select;
    private List<Comentario> listacom;
    
     
    @PostConstruct
    public void verMarcadoresC(){
        
        simpleModel = new DefaultMapModel();
        MarcadorDAO mdb = new MarcadorDAO();
        List<Marcador> marcadores = mdb.findAll();
        for(Marcador m :marcadores){
            LatLng cord = new LatLng(m.getLatitud(),m.getLongitud());
            Marker marcador = new Marker(cord,m.getDescripcion());
            simpleModel.addOverlay(marcador);
        }
    }

    public MapModel getSimpleModel() {
        return simpleModel;
    }
    
    public void onMarkerSelect(OverlaySelectEvent event) {
       marker =(Marker) event.getOverlay();
       this.latitud = marker.getLatlng().getLat();
       this.longitud = marker.getLatlng().getLng();
       MarcadorDAO marcadorDAO = new MarcadorDAO();
       select = marcadorDAO.buscaMarcadorPorLatLng(latitud, longitud);
       ComentarioBeam.update();
    }
    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public String getDatos() {
        return datos;
    }

    public void setDatos(String datos) {
        this.datos = datos;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public static Marcador getMarca() {
        return select;
    }

    public static void setMarca(Marcador marca) {
        VerMarcadorC.select = marca;
    }

    public Marcador getSelect() {
        return select;
    }

    public void setSelect(Marcador select) {
        this.select = select;
    }
    
    
    
    
    public void agregarComentario(){
        ControladorSesion.UserLogged us = (ControladorSesion.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("comentarista");
        UsuarioDAO daoUsuario = new UsuarioDAO();
        Usuario usuarioA = daoUsuario.buscaPorCorreo(us.getCorreo());
        Comentario comen = new Comentario();
        MarcadorDAO marcadorDAO = new MarcadorDAO();
        Marcador ma = marcadorDAO.buscaMarcadorPorLatLng(latitud,longitud);
        AgregaComentario com = new AgregaComentario();
        com.agregaComentario(usuarioA,ma,comentario,calificacion);
        ComentarioBeam.update();
    }
    
    public void incrementaCalificacion(){
        calificacion++;
    }
}

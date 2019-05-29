/* * To change this license header, choose License Headers in Project 
 Properties. * To change this template file, choose Tools | Templates * and 
 open the template in the editor. */

package controlador;


import com.mycompany.prueba.Marcador;
import com.mycompany.prueba.MarcadorDAO;
import com.mycompany.prueba.Mensajes;
import com.mycompany.prueba.Tema;
import com.mycompany.prueba.TemaDAO;
import com.mycompany.prueba.Usuario;
import com.mycompany.prueba.UsuarioDAO;
import java.io.BufferedWriter;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.servlet.ServletContext;
/**
 *
 * @author jorge

 */
@ManagedBean
@ViewScoped
public class AgregaTema implements Serializable {
    private Marker marcador;
    private MapModel simpleModel;
    private double longitud;
    private double latitud;
    private Tema tema;
    private String descripcion;
    private String datos;
    private LatLng centro; 
    private String nombreTema;
    private String color;
    private List<Tema> listaTemas;
    
    @PostConstruct
    public void init(){
        centro = new LatLng(23.382390, -102.291477);
        simpleModel = new DefaultMapModel();
        marcador = new Marker(centro,"Arrastrame");
        marcador.setDraggable(true);
//      marcador.setClickable(true);
        simpleModel.addOverlay(marcador);
        this.latitud = marcador.getLatlng().getLat();
        this.longitud = marcador.getLatlng().getLng();
    }
    public List<Tema> getListaTemas() {
        TemaDAO tdao = new TemaDAO();
        this.listaTemas = tdao.listaTemas();
        return listaTemas;
    }
    public LatLng getCentro() {
        return centro;
    }

    public String getNombreTema() {
        return nombreTema;
    }

    public void setNombreTema(String nombreTema) {
        this.nombreTema = nombreTema;
    }
    
 
    public Marker getMarcador() {
        return marcador;
    }

    public MapModel getSimpleModel() {
        return simpleModel;
    }
    
    public void onMarkerDrag(MarkerDragEvent event){
        marcador = event.getMarker();
        this.latitud = marcador.getLatlng().getLat();
        this.longitud = marcador.getLatlng().getLng();
    }

    public void onPointSelect(PointSelectEvent event) {
        LatLng latlng = event.getLatLng();
        marcador = simpleModel.getMarkers().get(0);
        marcador.setLatlng(latlng);
        this.latitud = latlng.getLat();
        this.longitud = latlng.getLng();
        this.centro = latlng;
        
    }
    
    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }
    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }

    public String getDatos() {
        return datos;
    }

    public void setDatos(String datos) {
        this.datos = datos;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
    
    
    
    public String agregaMarcador(){
        MarcadorDAO mdb =new MarcadorDAO();
        UsuarioDAO udb = new UsuarioDAO();
        TemaDAO tdao = new TemaDAO();
        Marcador m = mdb.buscaMarcadorPorLatLng(latitud, longitud);
        setTema(tdao.find(this.getNombreTema()));
        ControladorSesion.UserLogged us= (ControladorSesion.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("informador");
        Usuario u = udb.buscaPorCorreo(us.getCorreo());
        if(m!= null){
            this.descripcion ="";
            Mensajes.fatal("Ya existe un marcador con estas cordenadas \n" +"Lat: "+this.latitud +" Lng: "+this.longitud);
            return "";
        }
        if(tema==null){
           tema = new Tema();
           tema.setUsuario(u);
           tema.setNombre(nombreTema);
           tema.setColor(color);
           tdao.save(tema);
   
        m = new Marcador();
        m.setDescripcion(descripcion);
        m.setLatitud(latitud);
        m.setLongitud(longitud);
        m.setDatos(datos);
        m.setTema(tema);
        m.setUsuario(u);
        mdb.save(m);
        FacesMessage msg = new FacesMessage("El tema"+ m.getTema() +" hecho por "+ m.getUsuario() +" fue agregado con exito.\n Con la descripcion"+ m.getDescripcion());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        else{   return "";}
        return "";
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}


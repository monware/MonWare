/*
    public void setTema(Tema tema){
	this.tema = tema;
    }
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;
import javax.annotation.PostConstruct;
import modelo.Marcador;
import modelo.MarcadorDAO;
import modelo.UsuarioDAO;
import modelo.Tema;
import modelo.TemaDAO;
import modelo.Usuario;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import modelo.Mensajes;
import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 *@author lizbethac
 */
@ManagedBean
public class ColocaMarcador{

    private int idMarcador;
    private Tema tema;
    private Usuario usuario;
    private String correo;
    private Double latitud;
    private Double longitud;
    private String descripcion;
    private String datos;
    private Marker marcador;
    private MapModel simpleModel;

    public void setIdMarcador(int idMarcador){
	this.idMarcador = idMarcador;
    }
    public void setTema(Tema tema){
	this.tema = tema;
    }
    
    public void setUsuario(String correo){
	this.correo = correo;
    }
    
    public void setLatitud(Double latitud){
	this.latitud = latitud;
    }
    
    public void setLongitud(Double longitud){
	this.longitud = longitud;
    }
    
    public void setDescripcion(String descripcion){
	this.descripcion = descripcion;
    }
    
    public void setDatos(String datos){
	this.datos = datos;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Double getLongitud() {
        return longitud;
    }   
    
    public Double getLatitud(){
        return latitud;
    }
    
    public String getDatos(){
        return datos;
    }
    
    public String colocaMarcador(){
        UsuarioDAO udao = new UsuarioDAO();
        MarcadorDAO mdao = new MarcadorDAO();
        Marcador m = new Marcador();
        Usuario u = new Usuario();
/*
        TemaDAO t = new TemaDAO();        
        u.getCorreo();
        Usuario prueba= udao.find("Algo@al.com");
        String a = prueba.getCorreo();

	Tema tema = t.find("Chilaquiles");  
        m.setUsuario(prueba);
        m.setTema(tema);
	m.setLatitud(latitud);
	m.setLongitud(longitud);
        m.setDescripcion(descripcion);
        */
        m = mdao.buscaMarcadorPorLatLng(latitud, longitud);
        if(m!= null){
            this.descripcion ="";
            Mensajes.fatal("Ya existe un marcador con estas cordenadas \n" +"Lat: "+this.latitud +" Lng: "+this.longitud);
            return "";
        }
        m = new Marcador();
        ControladorSesion.UserLogged us= (ControladorSesion.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("INFORMADOR");
        u = udao.buscaPorCorreo(us.getCorreo());
        m.setUsuario(u);
        m.setTema(tema);
	m.setLatitud(latitud);
	m.setLongitud(longitud);
        m.setDescripcion(descripcion);
        m.setDatos(datos);        
        mdao.save(m);
        Mensajes.info("Se guardo el marcador");
        return "";
    }
    
     @PostConstruct
    public void init(){
        simpleModel = new DefaultMapModel();
        marcador = new Marker(new LatLng(23.382390, -102.291477),"Arrastrame");
        marcador.setDraggable(true);
//        marcador.setClickable(true);
        simpleModel.addOverlay(marcador);
        this.latitud = marcador.getLatlng().getLat();
        this.longitud = marcador.getLatlng().getLng();
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
     
        
           
    }
   
}

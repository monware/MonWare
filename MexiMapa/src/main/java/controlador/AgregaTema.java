
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
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import com.mycompany.prueba.Usuario;
import com.mycompany.prueba.UsuarioDAO;
import javax.annotation.PostConstruct;
import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
/**
 *
 * @author jorge
 */
@ManagedBean
public class AgregaTema {
    private String nombre;
    private String color;
    private Usuario usuario;
    private Double latitud;
    private Double longitud;
    private Tema tema;
    private String descripcion;
    private String datos;
    private Marker marcador;
    private MapModel simpleModel;
    private LatLng centro;  


    
    @PostConstruct
    public void init(){
        centro = new LatLng(23.382390, -102.291477);
        simpleModel = new DefaultMapModel();
        marcador = new Marker(centro,"Arrastrame");
        marcador.setDraggable(true);
         
//        marcador.setClickable(true);
        simpleModel.addOverlay(marcador);
        this.latitud = marcador.getLatlng().getLat();
        this.longitud = marcador.getLatlng().getLng();
    }
    
    public Marker getMarcador() {
        return marcador;
    }

    public void setMarcador(Marker marcador) {
        this.marcador = marcador;
    }

    public MapModel getSimpleModel() {
        return simpleModel;
    }

    public void setSimpleModel(MapModel simpleModel) {
        this.simpleModel = simpleModel;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public LatLng getCentro() {
        return centro;
    }

    public void setCentro(LatLng centro) {
        this.centro = centro;
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

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDatos() {
        return datos;
    }

    public void setDatos(String datos) {
        this.datos = datos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    //Falta Agregarle a que marcador pertenece.
    public void agregraTema(){
        Tema tema = new Tema();
        TemaDAO daoTema = new TemaDAO();
        UsuarioDAO daoUsuario = new UsuarioDAO();
        //Usuario aux = daoUsuario.find(3);
        ControladorSesion.UserLogged us= (ControladorSesion.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("informador");
        Usuario usuarioA = daoUsuario.buscaPorCorreo(us.getCorreo());
        tema.setNombre(nombre);
        tema.setColor(color);
        tema.setUsuario(usuarioA);
        daoTema.save(tema);
    }
    
    public void agregaMarcador(){
       MarcadorDAO mdb =new MarcadorDAO();
        UsuarioDAO udb = new UsuarioDAO();
        Marcador m = mdb.buscaMarcadorPorLatLng(latitud, longitud);
        if(m!= null){
            this.descripcion ="";
            //Mensajes.fatal("Ya existe un marcador con estas cordenadas \n" +"Lat: "+this.latitud +" Lng: "+this.longitud);
            //return "";
        }
        m = new Marcador();
        ControladorSesion.UserLogged us= (ControladorSesion.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        Usuario u = udb.buscaPorCorreo(us.getCorreo());
        m.setDescripcion(descripcion);
        m.setLatitud(latitud);
        m.setLongitud(longitud);
        //this.creaIcono(color,50,50);
        //System.out.println(color);
        //m.setIcon("resources/images/"+color+".svg");
        m.setUsuario(u);
        mdb.save(m);
        //this.descripcion ="";
        //Mensajes.info("Se guardo el marcador");
        //return "";
    }
    
    public void agregaTemaConMarcador(){
        agregraTema();
        agregaMarcador();
    }
}

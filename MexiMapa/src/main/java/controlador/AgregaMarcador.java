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
public class AgregaMarcador implements Serializable {
    private Marker marcador;
    private MapModel simpleModel;
    private double longitud;
    private double latitud;
    private Tema tema;
    private String descripcion;
    private String datos;
    private LatLng centro; 
    private String nombreTema;
    private List<Tema> listaTemas;
    private String color;
    
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
           this.creaIcono(color,50,50);
           tema.setColor("resources/images/"+color+".svg");
           tdao.save(tema);
        }
        m = new Marcador();
        m.setDescripcion(descripcion);
        m.setLatitud(latitud);
        m.setLongitud(longitud);
        m.setDatos(datos);
        m.setTema(tema);
        m.setUsuario(u);
        mdb.save(m);
        FacesMessage msg = new FacesMessage("El marcador"+ m.getDescripcion() +" hecho por "+ m.getUsuario() +" fue agregado con exito.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        return "";
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    private void creaIcono(String color,int largo,int ancho){
        String s = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n";
        s+="<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\" \"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n";
			s+="<svg width=\""+largo+"\" height=\""+ancho+"\" version=\"1.1\" id=\"Capa_1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" x=\"0px\" y=\"0px\" style=\"enable-background:new 0 0 512 512;\" xml:space=\"preserve\">\n<g>\n";
        int x =largo/2;
        int y = (ancho/3);
        int radio = ((largo+ancho)/2)/4;

        int[] p ={x-radio,y,x+radio,y,x,(y*3)};
        s+= creaPoligono(p,"#"+color);
        s+=creaCirculo(x,y,radio,"#"+color,true);
        s+=creaCirculo(x,y,radio/2,"black",true);

        s+="</g>\n"+"</svg>";
        
        try {
             ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String destino = (servletContext.getRealPath("/"))+"resources/images/";
            System.out.println(destino);
            FileOutputStream fileOut = new FileOutputStream(new File(destino + color+".svg"));
            OutputStreamWriter osOut = new OutputStreamWriter(fileOut);
            BufferedWriter out = new BufferedWriter(osOut);
            out.write(s);
            out.close();
        } catch (IOException ioe) {
            System.out.println("No pude guardar en el archivo" );
//            System.exit(1);
        }


    }

    private String creaCirculo(int x ,int y , int r,String color,boolean stroke){
        String s = stroke ? "<circle cx=\""+x+"\" cy=\"" +y+"\"  r=\"" + r + "\" stroke=\"white\" stroke-width=\"1\"  fill=\"" + color + "\" />\n" : "<circle cx=\""+x+"\" cy=\"" +y+"\"  r=\"" + r + "\" stroke=\"black\" stroke-width=\"0\"  fill=\"" + color + "\" />\n";
        return  s;

    }

    private String creaPoligono(int[] puntos,String color){
        String p = "";
        if(puntos.length%2 != 0)
          return "Los puntos estan mal";
        for(int i=0;i<puntos.length;i+=2){
          p+=puntos[i]+","+puntos[i+1]+" ";
        }
        return "<polygon points=\""+p+"\" \n style=\" fill:" +color+";stroke:black;stroke-width:1;\" /> \n";
    }
    
    
}

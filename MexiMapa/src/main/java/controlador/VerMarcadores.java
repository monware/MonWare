/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.Comentario;
import modelo.Marcador;
import modelo.MarcadorDAO;
import modelo.Tema;
import modelo.TemaDAO;
import modelo.Usuario;
import modelo.UsuarioDAO;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;
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
@Named(value = "verMarcadores")

public class VerMarcadores implements Serializable{
    
    private MapModel simpleModel;
    private Marker marker;
    private String marcadors;
    List<Tema> listaTemas;
    private Double latitud;
    private Double longitud;
    private String nombreTema ;
    private String comentario;
    private String descripcion;
    public static Marcador select;
    private List<Comentario> listacom;

   
    @PostConstruct      
    public void init(){
        simpleModel = new DefaultMapModel();
        TemaDAO tdao = new TemaDAO();
        FacesContext context = FacesContext.getCurrentInstance();
        Object p=context.getExternalContext().getSessionMap().get("informador");
        Object p1=context.getExternalContext().getSessionMap().get("comentarista");
        Object p2=context.getExternalContext().getSessionMap().get("administrador");
        List<Tema> temas = tdao.findAll();
        for(Tema t : temas){
            String color = t.getColor();
            this.creaIcono(color, 50, 50);
        }
        MarcadorDAO mdao = new MarcadorDAO();
        List<Marcador> l = mdao.listaMarcadores();
        simpleModel = new DefaultMapModel();
        
        for(Marcador m :l){
            LatLng cord = new LatLng(m.getLatitud(),m.getLongitud());
            Marker prueba = new Marker(cord,m.getDatos(),m.getDescripcion());
            String color = m.getTema().getColor();
            if(p== null && p1== null && p2 ==null){
                prueba.setIcon("resources/media/"+color+".svg");
            }else{
                prueba.setIcon("../../resources/media/"+color+".svg");
            }
            simpleModel.addOverlay(prueba);
       
        }
    }
    
    public void verMarcadores(){
        MarcadorDAO mdao = new MarcadorDAO();
        List<Marcador> l = mdao.ObtenMarcadoresPorTema(nombreTema);
        FacesContext context = FacesContext.getCurrentInstance();
        Object p=context.getExternalContext().getSessionMap().get("informador");
        Object p1=context.getExternalContext().getSessionMap().get("comentarista");
        Object p2=context.getExternalContext().getSessionMap().get("administrador");
        simpleModel = new DefaultMapModel();
        for(Marcador m :l){
            LatLng cord = new LatLng(m.getLatitud(),m.getLongitud());
            Marker prueba = new Marker(cord,m.getDatos(),m.getDescripcion());
            String color = m.getTema().getColor();
            if(p== null && p1== null && p2 ==null){
                prueba.setIcon("resources/media/"+color+".svg");
            }else{
                prueba.setIcon("../../resources/media/"+color+".svg");
            }
            simpleModel.addOverlay(prueba);
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

    public String getNombreTema() {
        return nombreTema;
    }

    public void setNombreTema(String nombreTema) {
        this.nombreTema = nombreTema;
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

    public List<Comentario> getListacom() {
        return listacom;
    }

    public void setListacom(List<Comentario> listacom) {
        this.listacom = listacom;
    }

    public Marcador getSelect() {
        return select;
    }

    public static void setSelect(Marcador select) {
        VerMarcadores.select = select;
    }
    
    
    public MapModel getSimpleModel() {
        return simpleModel;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public void onMarkerSelect(OverlaySelectEvent event) {
       marker =(Marker) event.getOverlay();
       this.latitud = marker.getLatlng().getLat();
       this.longitud = marker.getLatlng().getLng();
       MarcadorDAO marcadorDAO = new MarcadorDAO();
       select = marcadorDAO.buscaMarcadorPorLatLng(latitud, longitud);
       ComentarioBeam.u();
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
            String destino = (servletContext.getRealPath("/"))+"resources/media/";
            System.out.println(destino);
            FileOutputStream fileOut = new FileOutputStream(new File(destino + color+".svg"));
            OutputStreamWriter osOut = new OutputStreamWriter(fileOut);
            BufferedWriter out = new BufferedWriter(osOut);
            out.write(s);
            out.close();
        } catch (IOException ioe) {
            System.out.println("No pude guardar en el archivo" );
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
    
    public void agregarComentario(){
        ControladorSesion.UserLogged us = (ControladorSesion.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("comentarista");
        UsuarioDAO daoUsuario = new UsuarioDAO();
        Usuario usuarioA = daoUsuario.buscaPorCorreo(us.getCorreo());
        Comentario comen = new Comentario();
        MarcadorDAO marcadorDAO = new MarcadorDAO();
        Marcador ma = marcadorDAO.buscaMarcadorPorLatLng(latitud,longitud);
        AgregaComentario com = new AgregaComentario();
        com.agregaComentario(usuarioA,ma,comentario,0);
        ComentarioBeam.u();
    }
    
}

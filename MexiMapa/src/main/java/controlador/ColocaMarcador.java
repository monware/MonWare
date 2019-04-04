/*
    public void setTema(Tema tema){
	this.tema = tema;
    }
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;
import modelo.Marcador;
import modelo.MarcadorDAO;
import modelo.UsuarioDAO;
import modelo.Tema;
import modelo.TemaDAO;
import modelo.Usuario;
import javax.faces.bean.ManagedBean;

/**
 *
 *@author lizbethac
 */
@ManagedBean
public class ColocaMarcador{

    private int idMarcador;
    private Tema tema;
    private Usuario usuario;
    private Double latitud;
    private Double longitud;
    private String descripcion;
    private String datos;

    public void setIdMarcador(int idMarcador){
	this.idMarcador = idMarcador;
    }
    public void setTema(Tema tema){
	this.tema = tema;
    }
    
    public void setUsuario(Usuario usuario){
	this.usuario = usuario;
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
    
    public void colocaMarcador(){
        Marcador m = new Marcador();
        UsuarioDAO u= new UsuarioDAO();                
        TemaDAO t = new TemaDAO();        
        u.find("Algo@al.com");
	t.find("Chilaquiles");        
        m.setUsuario(usuario);
        m.setTema(tema);
	m.setLatitud(latitud);
	m.setLongitud(longitud);
        m.setDescripcion(descripcion);
        m.setDatos(datos);        
        MarcadorDAO mdao = new MarcadorDAO();
        mdao.save(m);
    }
}

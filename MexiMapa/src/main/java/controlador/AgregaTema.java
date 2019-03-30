/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import modelo.Tema;
import modelo.TemaDAO;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author jorge
 */
@ManagedBean
public class AgregaTema {
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    public void agregraTema(){
        Tema u = new Tema();
        u.setNombre(nombre);
        TemaDAO udao = new TemaDAO();
        udao.save(u);
    }
}

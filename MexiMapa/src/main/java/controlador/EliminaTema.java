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
public class EliminaTema {
    private int idTema;
    
    public void eliminaMarcador(){
        //Usuario u = new Usuario();
        TemaDAO udao = new TemaDAO();
        Tema u = udao.find(idTema);
        if(u!=null){
            udao.delete(u);
        } 
    }
}

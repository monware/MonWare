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
import javax.faces.bean.ManagedBean;

/**
 *
 *@author lizbethac
 */
@ManagedBean
public class EliminaMarcador{
    private int idMarcador;    

    public int getIdMarcador(){
	return idMarcador;
    } 

    public void setIdMarcador(int idMarcador){
	this.idMarcador = idMarcador;
    }   

    public void eliminaMarcador(){
	MarcadorDAO mdao = new MarcadorDAO();
	Marcador m = mdao.find(idMarcador);
	if(m!=null){
	    mdao.delete(m);
	}
    }
}

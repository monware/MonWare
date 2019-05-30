/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modelo.Comentario;
import modelo.ComentarioDAO;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;
/**
 *
 * @author jorge
 */
@ManagedBean
@RequestScoped
@ViewScoped

public class ComentarioBeam {
    
    public Comentario comen;
    
    private static List<Comentario> lista = new ArrayList();

    public List<Comentario> getLista() {
        return lista;
    }

    public Comentario getComen() {
        return comen;
    }

    public void setComen(Comentario comen) {
        this.comen = comen;
    }
    
    
    public static void setLista(List<Comentario> lista) {
      ComentarioBeam.lista = lista;
    }
    public static void u(){
        ComentarioDAO comentarioDAO = new ComentarioDAO();
        ComentarioBeam.lista = comentarioDAO.buscaPorMarcador(VerMarcadores.select.getIdmarcador());
    }
    
    public void Edita(RowEditEvent event) {
        try{
            this.comen = (Comentario) event.getObject();
            ComentarioDAO cDAO = new ComentarioDAO();
            cDAO.update(this.comen);
            FacesMessage msg = new FacesMessage("Se modifico correctamente");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }catch(Exception e){
            FacesMessage msg = new FacesMessage("Erro desconocido mandar mensaje a los administradores");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
}


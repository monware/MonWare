/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import com.mycompany.prueba.Usuario;
import com.mycompany.prueba.UsuarioDAO;

/**
 *
 * @author yisus
 */
@ManagedBean
public class Validaciones {
    
      public void validacionNombreAgregarInformador(FacesContext arg0, UIComponent arg1, Object arg2)
         throws ValidatorException {
           System.out.println("NO");
           System.out.println(((String)arg2));
          if (((String)arg2).length()<2) {
              System.out.println("Si");
           throw new ValidatorException(new FacesMessage("Cadena corta"));}
      
        }
      
        public void validacionEmailAgregarInformador(FacesContext arg0, UIComponent arg1, Object arg2)
         throws ValidatorException {
            
             Matcher matcher = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE).matcher((String)arg2);
             if(!matcher.find())
            {
            throw new ValidatorException(new FacesMessage("El correo no cuenta con el formato requerido"));
            }
            //UsuarioDAO udb = new UsuarioDAO();
            //if(udb.find((String)arg2)!=null){
            //throw new ValidatorException(new FacesMessage("El correo ya existe, intente con otro"));}
            
        }
        public void validacionEmailEliminarInformador(FacesContext arg0, UIComponent arg1, Object arg2)
         throws ValidatorException {
            
             Matcher matcher = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE).matcher((String)arg2);
             if(!matcher.find())
            {
            throw new ValidatorException(new FacesMessage("El correo no cuenta con el formato requerido, revise el correo"));
            }
            UsuarioDAO udb = new UsuarioDAO();
            if(udb.find((String)arg2)==null){
            throw new ValidatorException(new FacesMessage("El correo no existe, intente con otro"));
            }
            
        }
        public void validacionNombreEliminarTema(FacesContext arg0, UIComponent arg1, Object arg2)
         throws ValidatorException {
            
            TemaDAO tdb = new TemaDAO();
            if(tdb.find((String)arg2)==null){
            throw new ValidatorException(new FacesMessage("El Tema no existe, revise por favor"));
            }
            
        }
        public static void error(String error) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", error));
        }
   }
      


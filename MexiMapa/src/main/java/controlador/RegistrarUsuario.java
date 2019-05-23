/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.mycompany.prueba.Usuario;
import com.mycompany.prueba.UsuarioDAO;
import java.util.Locale;

import java.util.Properties;
import javax.faces.application.FacesMessage;
import javax.mail.*;
import javax.mail.internet.*;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author ALEX
 */
@ManagedBean
public class RegistrarUsuario {
    
    private String nombre;
    private String apaterno;
    private String amaterno;
    private String correo;
    private String contrasenia;
    private Integer rol;

    public int getRol() {
        return rol;
    }

    public void setRol(Integer rol) {
        this.rol = rol;
    }

    /**
     * 
     * 
     * @return 
     */
    public String getApaterno() {
        return apaterno;
    }

    /**
     * 
     * @param apaterno 
     */
    public void setApaterno(String apaterno) {
        this.apaterno = apaterno;
    }

    /**
     * 
     * @return 
     */
    public String getAmaterno() {
        return amaterno;
    }

    /**
     * 
     * @param amaterno 
     */
    public void setAmaterno(String amaterno) {
        this.amaterno = amaterno;
    }

    /**
     * 
     * @return 
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * 
     * @param nombre 
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * 
     * @return 
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * 
     * @param correo 
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * 
     * @return 
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * 
     * @param contrasenia 
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    
    /**
     * 
     */
    public String agregaUsuario(){
        Usuario u = new Usuario();
        u.setNombre(nombre);
        u.setCorreo(correo);
        u.setApaterno(apaterno);
        u.setAmaterno(amaterno);
        u.setContrasenia(contrasenia);
        u.setRol(2);
        
        UsuarioDAO udb = new UsuarioDAO();
        Usuario x = udb.buscaPorCorreo(u.getCorreo());
        if(x!=null){
        return "/Error?faces-redirect=true";
        }else{
        
            udb.save(u);
        
        //correo a donde se mandará la confirmación 
        String receptor = u.getCorreo();
        //cuerpo del correo
        String mensaje = "Se ha completado tu registro\n";
        //método que manda el correo
        mandaCorreo(receptor,"Confirmacion correo", mensaje,"monwareorg@gmail.com");
        
        FacesContext context = FacesContext.getCurrentInstance();
        context.getViewRoot().setLocale(new Locale("es-Mx"));
        
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro se realizo de manera exitosa", ""));
        
        return "/InicioSesion?faces-redirect=true";
        }
        
    }
    

    /**
     * 
     * @param a el destinatario del correo
     * @param asunto el asunto del correo
     * @param msg el cuerpo del correo
     * @param usr el correo emisor del mensaje
     * @return true si envía el correo, false en otro caso
     */
    //va así para evitar problemas
    private boolean mandaCorreo(String a, String asunto, String msg, final String usr) {
        boolean enviado = true;
        // Get system properties
        Properties properties = new Properties();

        // Setup mail server
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Get the default Session object.
        
        Session session;
        session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usr, "monw@re22");
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(usr));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(a));
            message.setSubject(asunto);
            message.setText(msg);
            Transport.send(message);
        } catch (MessagingException mex) {
            enviado = false;
        }
        return enviado;
    }
}

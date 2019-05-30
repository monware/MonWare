/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.Usuario;
import modelo.UsuarioDAO;
import java.io.Serializable;
import java.util.Properties;
import javax.faces.application.FacesMessage;
import javax.mail.*;
import javax.mail.internet.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author ALEX
 */

@ManagedBean
@ViewScoped
public class RegistrarUsuario implements Serializable {
    
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


    public String getApaterno() {
        return apaterno;
    }


    public void setApaterno(String apaterno) {
        this.apaterno = apaterno;
    }


    public String getAmaterno() {
        return amaterno;
    }


    public void setAmaterno(String amaterno) {
        this.amaterno = amaterno;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getCorreo() {
        return correo;
    }


    public void setCorreo(String correo) {
        this.correo = correo;
    }


    public String getContrasenia() {
        return contrasenia;
    }


    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    

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
            String receptor = u.getCorreo();
            String mensaje = "Se ha completado tu registro\n";        
            mandaCorreo(receptor,"Confirmacion correo", mensaje,"monwareorg@gmail.com");        
            FacesMessage msg = new FacesMessage("El usuario fue añadido con exito.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        return "/InicioSesion?faces-redirect=true";
        }

    }
    
    private boolean mandaCorreo(String a, String asunto, String msg, final String usr) {
        boolean enviado = true;
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
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

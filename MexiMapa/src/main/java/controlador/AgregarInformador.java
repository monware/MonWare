/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.Usuario;
import modelo.UsuarioDAO;
import java.util.HashSet;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.security.SecureRandom;
import java.math.BigInteger;
import java.util.Properties;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author ALEX
 */
@ManagedBean
@RequestScoped
public class AgregarInformador {
 private String correo;
    private String nombre;
    private String apaterno;
    private String amaterno;
    private String contrasenia;
    private int rol;
    private String apellido;
    private Set temas = new HashSet(0);
    private Set marcadors = new HashSet(0);
    private Set comentarios = new HashSet(0);

    /*public AgregarInformador(){
        apaterno="-";
        amaterno="-";
    }*/
    public String getApellido() {
        return apellido;
    }
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    public void setApaterno(String apaterno) {
        this.apaterno = apaterno;
    }

    public void setAmaterno(String amaterno) {
        this.amaterno = amaterno;
    }

    public String getApaterno() {
        return apaterno;
    }

    public String getAmaterno() {
        return amaterno;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public Set getTemas() {
        return temas;
    }

    public void setTemas(Set temas) {
        this.temas = temas;
    }

    public Set getMarcadors() {
        return marcadors;
    }

    public void setMarcadors(Set marcadors) {
        this.marcadors = marcadors;
    }

    public Set getComentarios() {
        return comentarios;
    }

    public void setComentarios(Set comentarios) {
        this.comentarios = comentarios;
    }
    
    public void agregarInformador(){
        Usuario u = new Usuario();
        u.setNombre(nombre);
        u.setCorreo(correo);
        u.setApaterno(apaterno);
        u.setAmaterno(amaterno);
        u.setContrasenia(generaContrasenia(10));  
        u.setRol(3);
        UsuarioDAO udb = new UsuarioDAO();
        udb.save(u);
        String receptor = u.getCorreo();
        String mensaje = "Felicidades, nuevo informador, se ha completado tu registro a MexiMapa\n su password es:\n" + u.getContrasenia();
        mandaCorreo(receptor,"Confirmacion correo", mensaje,"monwareorg@gmail.com");
        
        
        FacesMessage msg = new FacesMessage("El usuario fue añadido con exito.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    private String generaContrasenia(int i) {
      SecureRandom srand = new SecureRandom();
      String contrasenia = new BigInteger(130, srand).toString(32);
      return contrasenia.substring(0,i);
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

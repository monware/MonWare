/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author jorge
 */
public class UsuarioDAO extends AbstractDAO<Usuario> {
    
    /**
     * 
     */
    public UsuarioDAO(){
        super();
    }
    
    /**
     * 
     * @param usuario 
     */
    @Override
    public void save(Usuario usuario){
        super.save(usuario);
    }
    
    /**
     * 
     * @param usuario 
     */
    @Override
    public  void update(Usuario usuario){
        super.update(usuario);
    }
    
    /**
     * 
     * @param usuario 
     */
    @Override
    public void delete(Usuario usuario){
        super.delete(usuario);
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    public Usuario find(String id){
        return super.find(Usuario.class, id);
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    public Usuario find(int id){
        return super.find(Usuario.class, id);
    }

    /**
     * 
     * @return 
     */
    public List<Usuario> findAll(){
        return super.findAll(Usuario.class);
    
    }


       public Usuario buscaPorCorreo(String email){
        Usuario usuario = null;
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String hql = "From Usuario  u where u.correo = :email";
            Query query = session.createQuery(hql);
            query.setParameter("email", email);
            usuario = (Usuario)query.uniqueResult();

            tx.commit();
        }catch(HibernateException e){
            if(tx!=null){
                tx.rollback();
            }
            e.printStackTrace();
        }finally{
            session.close();
        }

        return usuario;
    }
     
       public Usuario buscaPorCorreoContrasenia(String correo,String contrasenia){
        Usuario u =null;
        Session session = this.sessionFactory.openSession();
        Transaction tx =null;
        try{
            tx = session.beginTransaction();
            String hql = "from Usuario where correo = :correo and contrasenia = :contrasenia";
            Query query = session.createQuery(hql);
            query.setParameter("correo", correo);
            query.setParameter("contrasenia",contrasenia);
            u = (Usuario)query.uniqueResult();
            tx.commit();
        }catch(HibernateException e){
            if(tx!=null){
                tx.rollback();
            }
            e.printStackTrace();
        }finally{
            session.close();
        }
        return u;
    }
}

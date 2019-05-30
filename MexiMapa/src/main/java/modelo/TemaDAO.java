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
public class TemaDAO extends AbstractDAO<Tema>{
    
    public TemaDAO(){
        super();
    }
    
    
    @Override
    public void save(Tema tema){
        super.save(tema);
    }
    

    @Override
    public void update(Tema tema){
        super.save(tema);
    }
    

    @Override
    public void delete(Tema tema){
        super.delete(tema);
    }
       

    public Tema find(int id){
        return super.find(Tema.class, id);
    }

    
    public Tema find(String id){
        return super.find(Tema.class, id);
    }
    

    public List<Tema> findAll(){
        return super.findAll(Tema.class);
    }
    
    
    public List<Tema> listaTemas(){
        List<Tema> m = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        String hql = "FROM Tema";
        try{
            m=session.createQuery(hql).list();
            tx.commit();
            session.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            tx.rollback();
        }
     return m;   
    }

    public List<Tema> ObtenTemasPorUsuario(String correo){
        List<Tema> m = null;
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String hql = "from Tema m where m.usuario.correo = :correo";
            Query query = session.createQuery(hql);
            query.setParameter("correo", correo);
            m = (List<Tema>)query.list();
            tx.commit();
            
        }catch(HibernateException e){
            if(tx!=null){
                tx.rollback();
            }
            e.printStackTrace();

        }finally{
            session.close();
        }
        return m;
    }
}

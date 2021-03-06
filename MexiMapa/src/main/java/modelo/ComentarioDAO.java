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
public class ComentarioDAO extends AbstractDAO<Comentario>{
    public ComentarioDAO(){
        super();
    }
    
    @Override
    public void save(Comentario comentario){
        super.save(comentario);
    }
    

    @Override
    public  void update(Comentario comentario){
        super.update(comentario);
    }
    

    @Override
    public void delete(Comentario comentario){
        super.delete(comentario);
    }
    

    public Comentario find(int id){
        return super.find(Comentario.class, id);
    }


    public List<Comentario> findAll(){
        return super.findAll(Comentario.class);
    
    }
    
    public List<Comentario> ObtenComentarioPorUsuario(String correo){
        List<Comentario> m = null;
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String hql = "from Comentario m where m.usuario.correo = :correo";
            Query query = session.createQuery(hql);
            query.setParameter("correo", correo);
            m = (List<Comentario>)query.list();
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
    
    public List<Comentario> buscaPorMarcador(int idMarcador){
        List<Comentario> listacom = null;
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String hql = "from Comentario where idmarcador = :idMarcador";
            Query query = session.createQuery(hql);
            query.setParameter("idMarcador", idMarcador);
            listacom = (List<Comentario>) query.list();
            tx.commit();
        }catch(HibernateException e){
            if(tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        }finally{
            session.close();
        }
        return listacom;
    }
    
    public List<Comentario> listaComentario(){
        List<Comentario> m = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        String hql = "FROM Comentario";
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
}

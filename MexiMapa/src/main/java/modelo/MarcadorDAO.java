/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author jorge
 */
public class MarcadorDAO extends AbstractDAO<Marcador>{
    
    public MarcadorDAO(){
        super();
    }
    
    @Override
    public void save(Marcador marcador){
        super.save(marcador);
    }
    
    @Override
    public void update(Marcador marcador){
        super.save(marcador);
    }
    

    @Override
    public void delete(Marcador marcador){
        super.delete(marcador);
    }
       

    public Marcador find(int id){
        return super.find(Marcador.class, id);
    }
    
     public Marcador find(String descripcion){
        return super.find(Marcador.class, descripcion);
    }
     
    public List<Marcador> findAll(){
        return super.findAll(Marcador.class);
    }

    public Marcador buscaMarcadorPorLatLng(double lat,double lng) {
        Marcador m = null;
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String hql = "from Marcador where longitud = :lng and latitud = :lat";
            Query query = session.createQuery(hql);
            query.setParameter("lng", lng);
            query.setParameter("lat", lat);
            m = (Marcador)query.uniqueResult();
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
    
    public List<Marcador> ObtenMarcadoresPorUsuario(String correo){
        List<Marcador> m = null;
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String hql = "from Marcador m where m.usuario.correo = :correo";
            Query query = session.createQuery(hql);
            query.setParameter("correo", correo);
            m = (List<Marcador>)query.list();
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
     
    public List<Marcador> ObtenMarcadoresPorTema(String ntema){
        List<Marcador> m = null;
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String hql = "from Marcador m where m.tema.nombre = :ntema";
            Query query = session.createQuery(hql);
            query.setParameter("ntema", ntema);
            m = (List<Marcador>)query.list();
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

     public List<Marcador> ObtenMarcadoresPorTema(Tema tema){
        List<Marcador> m = null;
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        String ntema = tema.getNombre();
        try{
            tx = session.beginTransaction();
            String hql = "from Marcador m where m.tema.nombre = :ntema";
            Query query = session.createQuery(hql);
            query.setParameter("ntema", ntema);
            m = (List<Marcador>)query.list();
            tx.commit();
        }
        catch(HibernateException e){
            if(tx!=null){
                tx.rollback();
            }
            e.printStackTrace();

        }finally{
            session.close();
        }
        return m;
     }
     
     public List<Marcador> creaArregloMarcadores(Usuario u) {
        List<Marcador> list = new ArrayList<Marcador>();
        Marcador[] marcadores = (Marcador[]) u.getMarcadors().toArray();
        for(int i = 0 ; i < u.getMarcadors().size() ; i++) {
            list.add(marcadores[i]);
        }
        return list;
}
     
         public List<Marcador> listaMarcadores(Usuario u){
        List<Marcador> listaMarker = null;
        Session session = this.sessionFactory.openSession();
        Transaction tx =null;
        try{
            tx = session.beginTransaction();
            String hql = "from Marcador  m where m.usuario= :u";
            Query query = session.createQuery(hql);
            listaMarker= (List<Marcador>)query.list();
            tx.commit();
        }catch(HibernateException e){
            if(tx!=null){
                tx.rollback();
            }
            e.printStackTrace();

        }finally{
            session.close();
        }
        return listaMarker;
    }
     
    public List<Marcador> listaMarcadores(){
        List<Marcador> m = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        String hql = "FROM Marcador";
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

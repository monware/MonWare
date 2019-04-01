/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;

/**
 *
 * @author jorge
 */
public class TemaDAO extends AbstractDAO<Tema>{
    
    public TemaDAO(){
        super();
    }
    
    
    /**
     * 
     * @param  
     */
    @Override
    public void save(Tema tema){
        super.save(tema);
    }
    
    /**
     * 
     * @param marcador 
     */
    @Override
    public void update(Tema tema){
        super.save(tema);
    }
    
    /**
     * 
     * @param marcador 
     */
    @Override
    public void delete(Tema tema){
        super.delete(tema);
    }
       
    /**
     * 
     * @param id
     * @return 
     */
    public Tema find(int id){
        return super.find(Tema.class, id);
    }
    
    /**
     * 
     * @return 
     */
    public List<Tema> findAll(){
        return super.findAll(Tema.class);
    }
}

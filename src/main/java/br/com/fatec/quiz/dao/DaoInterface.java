package br.com.fatec.quiz.dao;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Daniel Hideki
 * @param <T>
 * @param <Id>
 */
public interface DaoInterface <T, Id extends Serializable> {
    
    public void persist(T entity);
    public void update(T entity);
    public T findById(Id id);
    public void delete(T entity);
    public List<T> findAll();
}

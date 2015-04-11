package br.com.fatec.quiz.dao;

import br.com.fatec.quiz.model.Dificuldade;
import static br.com.fatec.quiz.util.HibernateUtil.getSessionFactory;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Daniel Hideki
 */
public class DificuldadeDao implements DaoInterface<Dificuldade, Integer>{

    private Session currentSession;

    private Transaction currentTransaction;

    public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionwithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }
    
        public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }
    
    @Override
    public void persist(Dificuldade dificuldade) {
        getCurrentSession().save(dificuldade);
    }

    @Override
    public void update(Dificuldade dificuldade) {
        getCurrentSession().update(dificuldade);
    }

    @Override
    public Dificuldade findById(Integer id) {
        Dificuldade dificuldade = (Dificuldade) getCurrentSession().get(Dificuldade.class, id);
        return dificuldade;
    }

    @Override
    public void delete(Dificuldade dificuldade) {
        getCurrentSession().delete(dificuldade);
    }

    @Override
    public List<Dificuldade> findAll() {
        List<Dificuldade> dificuldadeList = getCurrentSession().createQuery("from Dificuldade").list();
        return dificuldadeList;
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

}

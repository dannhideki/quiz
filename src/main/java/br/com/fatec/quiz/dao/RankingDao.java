package br.com.fatec.quiz.dao;

import br.com.fatec.quiz.model.Ranking;
import static br.com.fatec.quiz.util.HibernateUtil.getSessionFactory;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Daniel Hideki
 */
public class RankingDao implements DaoInterface<Ranking, Integer> {

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
    public void persist(Ranking ranking) {
        getCurrentSession().save(ranking);
    }

    @Override
    public void update(Ranking ranking) {
        getCurrentSession().update(ranking);
    }

    @Override
    public Ranking findById(Integer id) {
        Ranking ranking = (Ranking) getCurrentSession().get(Ranking.class, id);
        return ranking;
    }

    @Override
    public void delete(Ranking ranking) {
        getCurrentSession().delete(ranking);
    }

    @Override
    public List<Ranking> findAll() {
        List<Ranking> rankingList = getCurrentSession().createQuery("from Ranking").list();
        return rankingList;
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

package br.com.fatec.quiz.dao;

import br.com.fatec.quiz.model.Respostas;
import static br.com.fatec.quiz.util.HibernateUtil.getSessionFactory;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author hideki
 */
public class RespostasDao implements DaoInterface<Respostas, Integer> {

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
    public void persist(Respostas resposta) {
        getCurrentSession().save(resposta);
    }

    @Override
    public void update(Respostas resposta) {
        getCurrentSession().update(resposta);
    }

    @Override
    public Respostas findById(Integer id) {
         Respostas resposta = (Respostas)getCurrentSession().get(Respostas.class, id);
         return resposta;
    }

    @Override
    public void delete(Respostas resposta) {
        getCurrentSession().delete(resposta);
    }

    @Override
    public List<Respostas> findAll() {
        List<Respostas> respostasList = getCurrentSession().createQuery("from Respostas").list();
        return respostasList;
    }
    
    public List<Respostas> trazRespostasPorPergunta(Integer id){
        List<Respostas> respostasList = new ArrayList<>();
        List list = getCurrentSession()
                .createSQLQuery("SELECT r.ID, r.RESPOSTA FROM RESPOSTAS r WHERE r.PERGUNTA_ID =:pergunta")
                .setParameter("pergunta", id)
                .list();
        for (Object obj : list) {
            Object[] row = (Object[]) obj;
            Integer respostaId = (Integer) row[0];
            String descricao = (String) row[1];
            Respostas resposta = new Respostas();
            resposta.setId(respostaId);
            resposta.setResposta(descricao);
            respostasList.add(resposta);
        }
        
        return respostasList;
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

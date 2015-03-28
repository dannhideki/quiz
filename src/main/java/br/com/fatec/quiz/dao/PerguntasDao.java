package br.com.fatec.quiz.dao;

import br.com.fatec.quiz.enums.DificuldadeEnum;
import br.com.fatec.quiz.model.Dificuldade;
import br.com.fatec.quiz.model.Perguntas;
import br.com.fatec.quiz.model.Respostas;
import static br.com.fatec.quiz.util.HibernateUtil.getSessionFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author hideki
 */
public class PerguntasDao implements DaoInterface<Perguntas, Integer> {

    private Session currentSession;

    private Transaction currentTransaction;
    private final RespostasDao respostasDao;
    private final DificuldadeDao dificuldadeDao;

    public PerguntasDao() {
        respostasDao = new RespostasDao();
        dificuldadeDao = new DificuldadeDao();
        respostasDao.setCurrentSession(openCurrentSession());
        dificuldadeDao.setCurrentSession(openCurrentSession());
    }

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
    public void persist(Perguntas pergunta) {
        getCurrentSession().save(pergunta);
    }

    @Override
    public void update(Perguntas pergunta) {
        getCurrentSession().update(pergunta);
    }

    @Override
    public Perguntas findById(Integer id) {
        Perguntas perguntas = (Perguntas) getCurrentSession().get(Perguntas.class, id);
        return perguntas;
    }

    @Override
    public void delete(Perguntas pergunta) {
        getCurrentSession().delete(pergunta);
    }

    @Override
    public List<Perguntas> findAll() {
        List<Perguntas> perguntasList = (List<Perguntas>) getCurrentSession().createQuery("from Perguntas").list();
        return perguntasList;
    }

    //SELECT * FROM PERGUNTAS INNER JOIN RESPOSTAS ON PERGUNTAS.RESPOSTA_ID = RESPOSTAS.ID
    public List<Perguntas> trazPerguntasERespostas() {
        List<Perguntas> perguntasList = new ArrayList<>();

        String sql = "SELECT p.ID, p.PERGUNTA, p.DIFICULDADE, p.EXPLICACAO FROM PERGUNTAS p";
        List objList = getCurrentSession()
                .createSQLQuery(sql)
                .list();

        for (Object object : objList) {
            Object[] row = (Object[]) object;
            Integer perguntaId = (Integer) row[0];
            String descricaoPergunta = (String) row[1];
            Integer dificuldadeId = (Integer) row[2];
            String explicacao = (String) row[3];
            
            Perguntas pergunta = new Perguntas();
            pergunta.setId(perguntaId);
            pergunta.setPergunta(descricaoPergunta);
            pergunta.setExplicacao(explicacao);
            
            List<Respostas> respostas = respostasDao.trazRespostasPorPergunta(perguntaId);
            pergunta.setResposta(respostas);

            Dificuldade dificuldade = dificuldadeDao.findById(dificuldadeId);
            pergunta.setDificuldade(dificuldade);
            perguntasList.add(pergunta);
        }

        return perguntasList;
    }

    public List<Perguntas> findAllCorrect() {
        List<Perguntas> perguntasList = new ArrayList<>();
        String sql = "SELECT P.pergunta,D.dificultade,R.resposta,P.explicacao FROM PERGUNTAS P JOIN RESPOSTAS R ON R.ID = P.RESPOSTA_ID JOIN DIFICULDADE D ON D.ID = P.DIFICULDADE";
        List objList = getCurrentSession()
                .createSQLQuery(sql)
                .list();
        //List<Perguntas> perguntasList = (List<Perguntas>) getCurrentSession().createQuery("from Perguntas pergunta join pergunta.resposta").list();
        for (Object object : objList) {
            Object[] row = (Object[]) object;
            Perguntas pergunta = new Perguntas();
            pergunta.setPergunta((String) row[0]);
            Dificuldade dificuldade = new Dificuldade();
            dificuldade.setDificultade((String) row[1]);
            pergunta.setDificuldade(dificuldade);
            Respostas resposta = new Respostas();
            resposta.setResposta((String) row[2]);
            pergunta.setResposta(Arrays.asList(resposta));
            pergunta.setExplicacao((String) row[3]);
            perguntasList.add(pergunta);
        }

        return perguntasList;
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

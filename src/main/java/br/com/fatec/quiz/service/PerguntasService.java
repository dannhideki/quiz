package br.com.fatec.quiz.service;

import br.com.fatec.quiz.dao.PerguntasDao;
import br.com.fatec.quiz.model.Perguntas;
import java.util.List;

/**
 *
 * @author hideki
 */
public class PerguntasService {

    private PerguntasDao perguntaDao;

    public PerguntasService() {
        this.perguntaDao = new PerguntasDao();
    }

    public void persist(Perguntas pergunta) {
        this.perguntaDao.openCurrentSessionwithTransaction();
        this.perguntaDao.persist(pergunta);
        this.perguntaDao.closeCurrentSession();
    }

    public void update(Perguntas pergunta) {
        this.perguntaDao.openCurrentSessionwithTransaction();
        this.perguntaDao.update(pergunta);
        this.perguntaDao.closeCurrentSession();
    }

    public Perguntas findById(Integer id) {
        this.perguntaDao.openCurrentSession();
        Perguntas pergunta = this.perguntaDao.findById(id);
        this.perguntaDao.closeCurrentSession();
        return pergunta;
    }

    public void delete(Integer id) {
        this.perguntaDao.openCurrentSessionwithTransaction();
        Perguntas pergunta = this.perguntaDao.findById(id);
        this.perguntaDao.delete(pergunta);
        this.perguntaDao.closeCurrentSession();
    }

    public List<Perguntas> findAll(){
        this.perguntaDao.openCurrentSession();
        List<Perguntas> perguntas = (List<Perguntas>)this.perguntaDao.findAll();
        return perguntas;
    }
    
    public List<Perguntas> findAllCorrect(){
        this.perguntaDao.openCurrentSession();
        List<Perguntas> perguntas = (List<Perguntas>)this.perguntaDao.findAllCorrect();
        return perguntas;
    } 
    
    public List<Perguntas> trazPerguntasERespostas(){
        this.perguntaDao.openCurrentSession();
        List<Perguntas> perguntas = (List<Perguntas>)this.perguntaDao.trazPerguntasERespostas();
        return perguntas;
    } 

    public PerguntasDao getPerguntaDao() {
        return perguntaDao;
    }

    public void setPerguntaDao(PerguntasDao perguntaDao) {
        this.perguntaDao = perguntaDao;
    }
}

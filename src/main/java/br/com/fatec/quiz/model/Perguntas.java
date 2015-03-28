package br.com.fatec.quiz.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author hideki
 */
@Entity
@Table
public class Perguntas implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    private String pergunta;
    @OneToOne
    @JoinColumn(name = "DIFICULDADE", referencedColumnName = "ID")
    private Dificuldade dificuldade;
    @OneToMany
    @JoinColumn(name = "ID", referencedColumnName = "ID")
    private List<Respostas> resposta;
    private String explicacao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public Dificuldade getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(Dificuldade dificuldade) {
        this.dificuldade = dificuldade;
    }

    public String getExplicacao() {
        return explicacao;
    }

    public void setExplicacao(String explicacao) {
        this.explicacao = explicacao;
    }

    public List<Respostas> getResposta() {
        return resposta;
    }

    public void setResposta(List<Respostas> resposta) {
        this.resposta = resposta;
    }
}

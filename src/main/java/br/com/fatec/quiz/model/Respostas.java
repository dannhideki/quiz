package br.com.fatec.quiz.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author Daniel Hideki
 */
@Entity
@Table
public class Respostas implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    private String resposta;
    
    @ManyToOne
    @JoinColumn(name = "PERGUNTA_ID", referencedColumnName = "ID")
    private Perguntas pergunta;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public Perguntas getPergunta() {
        return pergunta;
    }

    public void setPergunta(Perguntas pergunta) {
        this.pergunta = pergunta;
    }
}
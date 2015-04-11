package br.com.fatec.quiz.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Daniel Hideki
 */
@Entity
@Table
public class Dificuldade implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    
    private String dificultade;
    
    private Integer pontos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPontos() {
        return pontos;
    }

    public void setPontos(Integer pontos) {
        this.pontos = pontos;
    }

    public String getDificultade() {
        return dificultade;
    }

    public void setDificultade(String dificultade) {
        this.dificultade = dificultade;
    }
}

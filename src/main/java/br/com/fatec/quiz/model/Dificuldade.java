package br.com.fatec.quiz.model;

import br.com.fatec.quiz.enums.DificuldadeEnum;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author hideki
 */
@Entity
@Table
public class Dificuldade implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    
 //   @Enumerated(EnumType.STRING)
 //   @Column(name = "DIFICULTADE")
 //   private DificuldadeEnum dificuldade;
    
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

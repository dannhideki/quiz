package br.com.fatec.quiz.enums;

/**
 *
 * @author hideki
 */
public enum DificuldadeEnum {

    FACIL("FÁCIL"),MEDIO("MÉDIO"),DIFICIL("DIFICIL");

    private String descricao;

    DificuldadeEnum(String descricao) {
        this.descricao = descricao;

    }
    
    public static DificuldadeEnum convertByString(String string){
        for (DificuldadeEnum value : DificuldadeEnum.values()) {
            if(value.getDescricao().equals(string)){
                return value;
            }
        }
        return null;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
}

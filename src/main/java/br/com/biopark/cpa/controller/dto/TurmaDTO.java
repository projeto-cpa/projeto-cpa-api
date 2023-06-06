package br.com.biopark.cpa.controller.dto;

import br.com.biopark.cpa.models.Turma;
import lombok.Data;

@Data
public class TurmaDTO {

    private String nome;
    private String descricao;

    public TurmaDTO(Turma turma) {
        this.nome = turma.getNome();
        this.descricao = turma.getDescricao();
    }
}

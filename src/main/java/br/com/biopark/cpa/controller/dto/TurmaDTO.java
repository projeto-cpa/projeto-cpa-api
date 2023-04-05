package br.com.biopark.cpa.controller.dto;

import java.util.Date;

import br.com.biopark.cpa.models.Turma;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TurmaDTO {

    private long id;
    private String nome;
    private String descricao;
    private String periodo;
    private String curso;
    private boolean ativo;
    private Date criadoEm;
    private Date atualizadoEm;
    private Boolean sucesso;

    public TurmaDTO(Turma turma) {
        this.id = turma.getId();
        this.nome = turma.getNome();
        this.descricao = turma.getDescricao();
        this.periodo = turma.getPeriodo();
        this.curso = turma.getCurso();
        this.ativo = turma.getAtivo(); 
        this.sucesso = true;
        this.criadoEm = turma.getCriadoEm();
        this.atualizadoEm = turma.getAtualizadoEm();
    }
}

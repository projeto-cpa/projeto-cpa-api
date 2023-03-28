package br.com.biopark.cpa.controller.dto;


import java.util.Date;

import br.com.biopark.cpa.models.Disciplina;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DisciplinaDTO {

    private long id;
    private String nome;
    private String descricao;
    private Boolean ativo;
    private Date dataCriacao;
    private Date dataAtualizacao;
    private Boolean sucesso;

    public DisciplinaDTO(Disciplina disciplina) {
        this.id = disciplina.getId();
        this.nome = disciplina.getNome();
        this.descricao = disciplina.getDescricao();
        this.ativo = disciplina.getAtivo(); 
        this.sucesso = true;
        this.dataCriacao = disciplina.getDataCriacao();
        this.dataAtualizacao = disciplina.getDataAtualizacao();
    }
}

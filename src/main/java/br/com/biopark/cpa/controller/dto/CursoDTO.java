package br.com.biopark.cpa.controller.dto;


import java.util.Date;

import br.com.biopark.cpa.models.Curso;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CursoDTO {

    private long id;
    private String nome;
    private String descricao;
    private Boolean ativo;
    private Date dataCriacao;
    private Date dataAtualizacao;
    private Boolean sucesso;

    public CursoDTO(Curso curso) {
        this.id = curso.getId();
        this.nome = curso.getNome();
        this.descricao = curso.getDescricao();
        this.ativo = curso.getAtivo(); 
        this.sucesso = true;
        this.dataCriacao = curso.getDataCriacao();
        this.dataAtualizacao = curso.getDataAtualizacao();
    }
}

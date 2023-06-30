package br.com.biopark.cpa.controller.dto;


import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import br.com.biopark.cpa.models.Curso;
import br.com.biopark.cpa.models.Disciplina;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CursoDTO {

    @NotBlank(message = "Nome não pode ser vazio")
    @NotNull(message = "Nome não pode ser vazio")
    private long id;
    private String nome;
    private String descricao;
    private Boolean ativo;
    private Date dataCriacao;
    private Date dataAtualizacao;
    private Boolean sucesso;
    //private List<Disciplina> disciplinas;

    public CursoDTO(Curso curso) {
        this.id = curso.getId();
        this.nome = curso.getNome();
        this.descricao = curso.getDescricao();
        this.ativo = curso.getAtivo(); 
        this.sucesso = true;
        this.dataCriacao = curso.getDataCriacao();
        this.dataAtualizacao = curso.getDataAtualizacao();
        //this.disciplinas = curso.getDisciplinas();
    }

    public static Page<CursoDTO> converter(Page<Curso> cursos){
        return cursos.map(CursoDTO::new);
    }
    
}

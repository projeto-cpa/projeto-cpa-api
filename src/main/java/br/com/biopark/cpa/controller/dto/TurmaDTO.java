package br.com.biopark.cpa.controller.dto;

import java.util.Date;

import org.springframework.data.domain.Page;

import br.com.biopark.cpa.models.Turma;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TurmaDTO {

    private Long id;
    private String nome;
    private String descricao;
    private String periodo;
    private String curso;
    private Boolean ativo;
    private Date dataCriacao;
    private Date dataAtualizacao;
    private Boolean sucesso;

    public TurmaDTO(Turma turma) {
        this.id = turma.getId();
        this.nome = turma.getNome();
        this.descricao = turma.getDescricao();
        this.ativo = turma.getAtivo(); 
        this.sucesso = true;
        this.dataCriacao = turma.getDataCriacao();
        this.dataAtualizacao = turma.getDataAtualizacao();
    }

    public static Page<TurmaDTO> converter(Page<Turma> turmas){
        return turmas.map(TurmaDTO::new);
    }
}

package br.com.biopark.cpa.controller.dto;

import br.com.biopark.cpa.models.Avaliacao;
import lombok.Data;

import java.util.Date;

@Data
public class AvaliacaoDTO {

    private String titulo;
    private Date dataExpiracao;
    private Date dataCriacao;
    private Date dataAtualizacao;

    public AvaliacaoDTO(Avaliacao avaliacao) {
        this.titulo = avaliacao.getTitulo();
        this.dataExpiracao = avaliacao.getDataExpiracao();
        this.dataCriacao = avaliacao.getDataCriacao();
        this.dataAtualizacao = avaliacao.getDataAtualizacao();
    }
}

package br.com.biopark.cpa.controller.dto;

import java.util.Date;

import br.com.biopark.cpa.models.enums.TipoPergunta;
import br.com.biopark.cpa.models.Pergunta;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PerguntaDTO {

    private long id;
    private String texto;
    private TipoPergunta tipo;
    private Boolean ativo;
    private Date dataCriacao;
    private Date dataAtualizacao;
    private Boolean sucesso;

    public PerguntaDTO(Pergunta pergunta) {
        this.id = pergunta.getId();
        this.texto = pergunta.getTexto();
        this.tipo = pergunta.getTipo();
        this.ativo = pergunta.getAtivo();
        this.sucesso = true;
        this.dataCriacao = pergunta.getDataCriacao();
        this.dataAtualizacao = pergunta.getDataAtualizacao();
    }
}
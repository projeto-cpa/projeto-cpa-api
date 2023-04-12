package br.com.biopark.cpa.controller.dto;

import java.util.Date;

import br.com.biopark.cpa.models.Resposta;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespostaDTO {

    private long id;
    private String texto;
    private Long nota;
    private Date dataCriacao;
    private Date dataAtualizacao;
    private Boolean sucesso;

    public RespostaDTO(Resposta resposta) {
        this.id = resposta.getId();
        this.texto = resposta.getTexto();
        this.nota = resposta.getNota();
        this.sucesso = true;
        this.dataCriacao = resposta.getDataCriacao();
        this.dataAtualizacao = resposta.getDataAtualizacao();
        // adicionar Id_eixo, e tipo_resposta.
    }
}
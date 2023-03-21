package br.com.biopark.cpa.controller.dto;

import java.util.Date;

import br.com.biopark.cpa.models.Pergunta;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PerguntaDTO {

    private long id;
    private String nome;
    private Date criadoEm;
    private Date atualizadoEm;
    private Boolean sucesso;

    public PerguntaDTO(Pergunta pergunta) {
        this.id = pergunta.getId();
        this.nome = pergunta.getNome();
        this.sucesso = true;
        this.criadoEm = pergunta.getCriadoEm();
        this.atualizadoEm = pergunta.getAtualizadoEm();

        // adicionar Id_eixo, e tipo_resposta.
    }
}

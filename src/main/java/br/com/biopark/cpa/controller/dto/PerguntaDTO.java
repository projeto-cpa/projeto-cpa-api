package br.com.biopark.cpa.controller.dto;

import java.util.Date;
import java.util.List;

import br.com.biopark.cpa.models.enums.TipoPergunta;
import br.com.biopark.cpa.models.Pergunta;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
public class PerguntaDTO {

    private long id;
    private String texto;
    private TipoPergunta tipo;
    private Boolean ativo;
    private Date dataCriacao;
    private Date dataAtualizacao;
    private Long eixoId;
    private String nomeEixo;
    private Boolean sucesso;

    public PerguntaDTO(Pergunta pergunta) {
        this.id = pergunta.getId();
        this.texto = pergunta.getTexto();
        this.tipo = pergunta.getTipo();
        this.ativo = pergunta.getAtivo();
        this.eixoId = pergunta.getEixo().getId();
        this.nomeEixo = pergunta.getEixo().getNome();
        this.sucesso = true;
        this.dataCriacao = pergunta.getDataCriacao();
        this.dataAtualizacao = pergunta.getDataAtualizacao();
    }

    public static Page<PerguntaDTO> converter(Page<Pergunta> perguntas) {
        return perguntas.map(PerguntaDTO::new);
    }

    public static List<PerguntaDTO> converter(List<Pergunta> perguntas) {
        return perguntas.stream().map(PerguntaDTO::new).toList();
    }
}
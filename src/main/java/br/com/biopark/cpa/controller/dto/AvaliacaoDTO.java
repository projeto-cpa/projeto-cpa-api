package br.com.biopark.cpa.controller.dto;

import br.com.biopark.cpa.models.Avaliacao;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

@Data
public class AvaliacaoDTO {

    private long id;
    private String titulo;
    private Date dataExpiracao;
    private Date dataCriacao;
    private Date dataAtualizacao;
    private List<PerguntaDTO> perguntaDTO;

    public AvaliacaoDTO(Avaliacao avaliacao) {
        this.id = avaliacao.getId();
        this.titulo = avaliacao.getTitulo();
        this.dataExpiracao = avaliacao.getDataExpiracao();
        this.dataCriacao = avaliacao.getDataCriacao();
        this.dataAtualizacao = avaliacao.getDataAtualizacao();
        this.perguntaDTO = PerguntaDTO.converter(avaliacao.getPerguntaList());
    }

    public static Page<AvaliacaoDTO> converter(Page<Avaliacao> avaliacaoPage) {
        return avaliacaoPage.map(AvaliacaoDTO::new);
    }
}

package br.com.biopark.cpa.controller.form;

import br.com.biopark.cpa.models.Avaliacao;
import br.com.biopark.cpa.models.Pergunta;
import br.com.biopark.cpa.models.Resposta;
import br.com.biopark.cpa.service.AvaliacaoService;
import br.com.biopark.cpa.service.PerguntaService;
import lombok.Data;

@Data
public class RespostaForm {;

    private String texto;
    private Long nota;
    private Long idPergunta;
    private Long avaliacaoId;

    public Resposta converter(PerguntaService perguntaService, AvaliacaoService avaliacaoService) {

        Pergunta pergunta = perguntaService.buscarPorId(idPergunta);
        Avaliacao avaliacao = avaliacaoService.buscarPorId(avaliacaoId);

        return new Resposta(this.texto, this.nota, pergunta, avaliacao);
    }

}
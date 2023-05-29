package br.com.biopark.cpa.controller.form;

import br.com.biopark.cpa.models.Avaliacao;
import br.com.biopark.cpa.models.Pergunta;
import br.com.biopark.cpa.models.Resposta;
import br.com.biopark.cpa.models.Usuario;
import br.com.biopark.cpa.service.AvaliacaoService;
import br.com.biopark.cpa.service.PerguntaService;
import br.com.biopark.cpa.service.UsuarioService;
import lombok.Data;

@Data
public class RespostaForm {;

    private String texto;
    private Long nota;
    private Long idPergunta;
    private Long avaliacaoId;
    private Long usuarioId;

    public Resposta converter(PerguntaService perguntaService, AvaliacaoService avaliacaoService,
                              UsuarioService usuarioService) {

        Pergunta pergunta = perguntaService.buscarPorId(idPergunta);
        Avaliacao avaliacao = avaliacaoService.buscarPorId(avaliacaoId);
        Usuario usuario = usuarioService.buscarPorId(usuarioId);

        return new Resposta(this.texto, this.nota, pergunta, avaliacao, usuario);
    }

}
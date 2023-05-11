package br.com.biopark.cpa.controller.form;

import br.com.biopark.cpa.models.Avaliacao;
import br.com.biopark.cpa.models.Pergunta;
import br.com.biopark.cpa.models.Turma;
import br.com.biopark.cpa.service.PerguntaService;
import br.com.biopark.cpa.service.TurmaService;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoForm {

    private String titulo;
    private List<Long> listaPerguntas;
    private List<Long> listaTurmas;
    private Date dataExpiracao;

    public Avaliacao converter(PerguntaService perguntaService, TurmaService turmaService){

        List<Pergunta> perguntaList = perguntaService.buscaPerguntasPorIds(listaPerguntas);
        List<Turma> turmaList = turmaService.buscarTurmasPorIds(listaTurmas);

        return new Avaliacao(titulo, perguntaList, turmaList, dataExpiracao);
    }
}

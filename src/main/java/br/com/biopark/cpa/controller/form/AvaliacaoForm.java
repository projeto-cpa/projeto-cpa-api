package br.com.biopark.cpa.controller.form;

import br.com.biopark.cpa.models.Avaliacao;
import br.com.biopark.cpa.service.PerguntaService;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoForm {

    private String titulo;
    private List<Integer> listaPerguntas;
    private List<Integer> listaTurmas;
    private long usuarioId;
    private Date dataExpiracao;

    public Avaliacao converter(PerguntaService perguntaService){

        return null;
    }
}

package br.com.biopark.cpa.controller.form;

import java.util.Optional;

import br.com.biopark.cpa.models.enums.TipoPergunta;
import br.com.biopark.cpa.models.Pergunta;
import br.com.biopark.cpa.models.Resposta;
import br.com.biopark.cpa.repository.PerguntaRepository;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespostaForm {;

    private String texto;
    private Long nota;
    private Long idPergunta;

    public Resposta converter(PerguntaRepository perguntaRepository) {
        Optional<Pergunta> optionalPergunta = perguntaRepository.findById(this.idPergunta);
        if (optionalPergunta.isPresent()) {
            Pergunta pergunta = optionalPergunta.get();
            if(pergunta.getTipo().equals(TipoPergunta.AVALIATIVA)){
                return new Resposta(this.nota, pergunta);
            }else if(pergunta.getTipo().equals(TipoPergunta.DESCRITIVA)){
                return new Resposta(this.texto, pergunta);
            }else{
                throw new IllegalArgumentException("Tipo de pergunta desconhecida");
            }
        } else {
            throw new IllegalArgumentException("Pergunta não encontrada");
        }
    }

}
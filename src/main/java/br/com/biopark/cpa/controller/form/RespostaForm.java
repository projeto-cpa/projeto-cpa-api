package br.com.biopark.cpa.controller.form;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.biopark.cpa.enums.TipoPergunta;
import br.com.biopark.cpa.models.Pergunta;
import br.com.biopark.cpa.models.Resposta;
import br.com.biopark.cpa.repository.PerguntaRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespostaForm {

    @Autowired
    private PerguntaRepository PerguntaRepository;

    @NotBlank(message = "Nome não pode ser vazio")
    @NotNull(message = "Nome não pode ser vazio")
    private String texto;
    private Long nota;
    private Long idPergunta;

    public Resposta converter(RespostaForm form) {
        Optional<Pergunta> optionalPergunta = PerguntaRepository.findById(form.idPergunta);
        if (optionalPergunta.isPresent()) {
            Pergunta pergunta = optionalPergunta.get();
            if(pergunta.getTipo().equals(TipoPergunta.AVALIATIVA)){
                return new Resposta(form.nota, pergunta);
            }else if(pergunta.getTipo().equals(TipoPergunta.DESCRITIVA)){
                return new Resposta(form.texto, pergunta);
            }else{
                throw new IllegalArgumentException("Tipo de pergunta desconhecida");
            }
        } else {
            throw new IllegalArgumentException("Pergunta não encontrada");
        }
    }

}
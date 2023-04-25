package br.com.biopark.cpa.controller.form;

import br.com.biopark.cpa.models.enums.TipoPergunta;
import br.com.biopark.cpa.models.Pergunta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PerguntaForm {

    @NotBlank(message = "Nome não pode ser vazio")
    @NotNull(message = "Nome não pode ser vazio")
    private String texto;
    private String tipo;
    private Boolean ativo;

    public Pergunta converter(PerguntaForm form) {
        for (TipoPergunta t : TipoPergunta.values()) {
            if (t.getCode().equals(this.tipo)) {
                return new Pergunta(form.texto, t, form.ativo);
            }
        }
        throw new IllegalArgumentException("Tipo de pergunta inválida");
    }

}
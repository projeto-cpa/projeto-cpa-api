package br.com.biopark.cpa.controller.form;

import br.com.biopark.cpa.models.enums.TipoPergunta;
import br.com.biopark.cpa.models.Pergunta;
import br.com.biopark.cpa.service.EixoService;
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
    @NotNull
    private String tipo;
    @NotNull
    private long eixoId;
    @NotNull
    private Boolean ativo;

    public Pergunta converter(PerguntaForm form, EixoService eixoService) throws EnumConstantNotPresentException {

        TipoPergunta tipoPergunta;

        try {
            tipoPergunta = TipoPergunta.valueOf(form.getTipo());
            return new Pergunta(form.texto, tipoPergunta, form.ativo, eixoService.buscarPorId(this.eixoId));
        } catch (EnumConstantNotPresentException e) {
            throw new EnumConstantNotPresentException(TipoPergunta.class, "Tipo de pergunta não existe!");
        }

    }

}
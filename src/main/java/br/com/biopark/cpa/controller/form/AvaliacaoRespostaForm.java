package br.com.biopark.cpa.controller.form;

import br.com.biopark.cpa.models.Avaliacao;
import br.com.biopark.cpa.service.AvaliacaoService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class AvaliacaoRespostaForm {

    @NotNull
    @NotBlank
    private Long avaliacaoId;
    @NotNull
    private List<RespostaForm> respostas;

    public Avaliacao converter(AvaliacaoService avaliacaoService) {

        RespostaForm respostaForm = new RespostaForm();

        Avaliacao avaliacao = avaliacaoService.buscarPorId(this.avaliacaoId);

//        avaliacao.setRespostaList(respostaForm.converter(this.respostas));

        return null;
    }
}

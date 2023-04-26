package br.com.biopark.cpa.controller.form.alteracao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class alteracaoCargoForm {

    @NotNull
    private Long idCargo;
    
    private String nome;
    private String descricao;
    private Boolean ativo;

}

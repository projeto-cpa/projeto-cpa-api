package br.com.biopark.cpa.controller.form.alteracao;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlterarCargoForm {

    @NotNull
    private Long idCargo;
    
    private String nome;
    private String descricao;
    private Boolean ativo;

}

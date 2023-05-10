package br.com.biopark.cpa.controller.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CargoForm {

    @NotBlank(message = "Nome não pode ser vazio")
    @NotNull(message = "Nome não pode ser vazio")
    private String nome;
    private String descricao;
    private Boolean ativo;
    
}

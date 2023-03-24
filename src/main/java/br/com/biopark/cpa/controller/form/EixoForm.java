package br.com.biopark.cpa.controller.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class EixoForm {

    @NotBlank
    @NotNull
    private String nome;

    @NotBlank
    @NotNull
    private String descricao;
}

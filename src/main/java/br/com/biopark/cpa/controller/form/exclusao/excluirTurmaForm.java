package br.com.biopark.cpa.controller.form.exclusao;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class excluirTurmaForm {

    @NotNull
    private Long idTurma;

}


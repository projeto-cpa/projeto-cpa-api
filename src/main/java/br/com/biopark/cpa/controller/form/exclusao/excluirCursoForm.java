package br.com.biopark.cpa.controller.form.exclusao;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class excluirCursoForm {

    @NotNull
    private Long idCurso;

}
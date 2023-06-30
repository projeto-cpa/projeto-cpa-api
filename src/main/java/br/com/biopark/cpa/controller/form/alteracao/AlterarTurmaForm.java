package br.com.biopark.cpa.controller.form.alteracao;

import br.com.biopark.cpa.models.Curso;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlterarTurmaForm {
    @NotNull
    private Long idTurma;   
    private String nome;
    private String descricao;
    private String periodo;
    private Curso curso;
    private Boolean ativo;
}


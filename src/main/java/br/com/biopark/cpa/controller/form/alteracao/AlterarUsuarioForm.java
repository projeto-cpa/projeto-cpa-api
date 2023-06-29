package br.com.biopark.cpa.controller.form.alteracao;

import br.com.biopark.cpa.models.Cargo;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlterarUsuarioForm {

    @NotNull
    private Long idUsuario;
    private String senha;
    private String nome;
    private String email;
    private Boolean ativo;
    private Cargo cargo;

}

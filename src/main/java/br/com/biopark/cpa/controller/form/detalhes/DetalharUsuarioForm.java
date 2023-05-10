package br.com.biopark.cpa.controller.form.detalhes;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetalharUsuarioForm {

    @NotNull
    private Long idUsuario;

}

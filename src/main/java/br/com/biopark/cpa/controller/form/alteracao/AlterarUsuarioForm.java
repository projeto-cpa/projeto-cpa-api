package br.com.biopark.cpa.controller.form.alteracao;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlterarUsuarioForm {

    @NotNull
    private Long idUsuario;
    private String senha;

    

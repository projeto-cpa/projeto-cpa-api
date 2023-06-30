package br.com.biopark.cpa.controller.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecuperarSenhaForm {
    private String email;
    private String senha;
    private String codigo;
}

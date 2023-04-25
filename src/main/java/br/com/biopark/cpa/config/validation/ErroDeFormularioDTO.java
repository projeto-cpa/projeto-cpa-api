package br.com.biopark.cpa.config.validation;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ErroDeFormularioDTO {

    private String campo;
    private String erro;

    public ErroDeFormularioDTO(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }
    
}

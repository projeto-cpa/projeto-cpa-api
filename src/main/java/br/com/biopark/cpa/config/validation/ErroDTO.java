package br.com.biopark.cpa.config.validation;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter @Setter
public class ErroDTO {

    private String campo;
    private String erro;

    public ErroDTO(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }

    public ErroDTO(String erro) {
        this.campo = erro;
    }
    
}

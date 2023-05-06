package br.com.biopark.cpa.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenDTO {

    private String token;
    private Boolean sucesso;

    public TokenDTO(String token) {
        this.token = token;
        this.sucesso = true;
    }

}

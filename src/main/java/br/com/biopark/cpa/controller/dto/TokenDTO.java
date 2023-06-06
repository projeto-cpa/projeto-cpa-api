package br.com.biopark.cpa.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenDTO {

    private String token;
    private Boolean sucesso;
    private Long idUsuario;

    public TokenDTO(String token, Boolean sucesso, Long idUsuario) {
        this.token = token;
        this.sucesso = sucesso;
        this.idUsuario = idUsuario;
    }

}

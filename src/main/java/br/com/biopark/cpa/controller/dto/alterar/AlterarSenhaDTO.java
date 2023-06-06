package br.com.biopark.cpa.controller.dto.alterar;

import br.com.biopark.cpa.models.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlterarSenhaDTO {

    // private String imagem;

    private Boolean sucesso;
    private String senha;

    public AlterarSenhaDTO(String senha) {
        this.senha = senha;
    }

    public AlterarSenhaDTO(Usuario usuario) {
        // this.imagem =usuario.getImagem();
        this.senha = getSenha();
        this.sucesso = true;
    }

}

package br.com.biopark.cpa.controller.dto;

import java.util.Date;

import br.com.biopark.cpa.models.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UsuarioDTO {
    
    private String nome;
    private String sobrenome;
    private String nomeCargo;
    private String senha;
    private Date dataNascimento;

    public UsuarioDTO() {

    }

    public UsuarioDTO(Usuario usuario) {
        this.nome = usuario.getNome();
        this.sobrenome = usuario.getSobrenome();
        this.senha = usuario.getSenha();
        this.nomeCargo = usuario.getCargo().getNome();
        this.dataNascimento = usuario.getDataNascimento();
    }

}
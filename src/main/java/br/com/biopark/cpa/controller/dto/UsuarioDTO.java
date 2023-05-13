package br.com.biopark.cpa.controller.dto;

import java.util.Date;

import br.com.biopark.cpa.models.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {
    
    private String email;
    private String nome;
    private String sobrenome;
    private String nomeCargo;
    private Date dataNascimento;

    public UsuarioDTO() {

    }

    public UsuarioDTO(String email, String nome, String sobrenome, String nomeCargo,
            Date dataNascimento) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.nomeCargo = nomeCargo;
        this.dataNascimento = dataNascimento;
    }

    public UsuarioDTO(Usuario usuario) {
        this.nome = usuario.getNome();
        this.sobrenome = usuario.getSobrenome();
        this.nomeCargo = usuario.getCargo().getNome();
        this.dataNascimento = usuario.getDataNascimento();
    }

}
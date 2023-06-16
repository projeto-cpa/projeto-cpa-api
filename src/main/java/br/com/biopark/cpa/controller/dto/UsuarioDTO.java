package br.com.biopark.cpa.controller.dto;

import java.util.Date;

import br.com.biopark.cpa.models.Usuario;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UsuarioDTO {
    
    private Long id;
    private String email;
    private String nome;
    private String sobrenome;
    private String nomeCargo;
    private Date dataNascimento;
    private String imagem;

    private String codigoRecuperacao;

    public UsuarioDTO() {

    }

    public UsuarioDTO(String email, String nome, String sobrenome, String nomeCargo, String imagem) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.nomeCargo = nomeCargo;
        this.imagem = imagem;
    }

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.email = usuario.getEmail();
        this.nome = usuario.getNome();
        this.sobrenome = usuario.getSobrenome();
        this.nomeCargo = usuario.getCargo().getNome();
        this.dataNascimento = usuario.getDataNascimento();
        this.imagem = usuario.getImagem();
        this.codigoRecuperacao = usuario.getCodigoRecuperacao();
    }

}
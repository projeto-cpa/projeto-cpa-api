package br.com.biopark.cpa.controller.dto;

import java.util.Date;

import org.springframework.data.domain.Page;

import br.com.biopark.cpa.models.Usuario;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UsuarioDTO {
    
    private String email;
    private String nome;
    private Boolean ativo;
    private String sobrenome;
    private String cargo;
    private long id_cargo;
    private Date dataNascimento;
    private String imagem;
    private String senha;

    public UsuarioDTO() {

    }

    // public UsuarioDTO(String email, String nome, String sobrenome, Long idCargo, String imagem) {
    //     this.email = email;
    //     this.nome = nome;
    //     this.sobrenome = sobrenome;
    //     this.idCargo = idCargo;
    //     this.imagem = imagem;
    // }

    public UsuarioDTO(String nome, String email, String senha, Long id_cargo, Boolean ativo) {
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.id_cargo = id_cargo;
        this.ativo = ativo;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.imagem = imagem;
    }

    public UsuarioDTO(Usuario usuario) {
        this.email = usuario.getEmail();
        this.nome = usuario.getNome();
        this.cargo = usuario.getCargo().getNome();
        this.id_cargo = usuario.getCargo().getId();
        this.senha = usuario.getSenha();
        this.ativo = usuario.getAtivo();
        this.sobrenome = usuario.getSobrenome();
        this.dataNascimento = usuario.getDataNascimento();
        this.imagem = usuario.getImagem();
    }

    public static Page<UsuarioDTO> converter(Page<Usuario> usuarios){
        return usuarios.map(UsuarioDTO::new);
    }

}
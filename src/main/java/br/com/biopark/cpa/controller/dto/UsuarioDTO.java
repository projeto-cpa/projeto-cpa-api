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
    
    private Long id;
    private String email;
    private String nome;
    private Boolean ativo;
    private String sobrenome;
    private String cargo;
    private long idCargo;
    private Date dataNascimento;
    private String imagem;
    private String senha;

    private String codigoRecuperacao;

    public UsuarioDTO() {

    }

    public UsuarioDTO(String nome, String email, String senha, Long idCargo, Boolean ativo) {
        this.id = id;
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.idCargo = idCargo;
        this.ativo = ativo;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.imagem = imagem;
    }

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.email = usuario.getEmail();
        this.nome = usuario.getNome();
        this.cargo = usuario.getCargo().getNome();
        this.idCargo = usuario.getCargo().getId();
        this.senha = usuario.getSenha();
        this.ativo = usuario.getAtivo();
        this.sobrenome = usuario.getSobrenome();
        this.dataNascimento = usuario.getDataNascimento();
        this.imagem = usuario.getImagem();
        this.codigoRecuperacao = usuario.getCodigoRecuperacao();
    }

    public static Page<UsuarioDTO> converter(Page<Usuario> usuarios){
        return usuarios.map(UsuarioDTO::new);
    }

}
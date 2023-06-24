package br.com.biopark.cpa.controller.dto;

import java.util.Date;

import org.springframework.data.domain.Page;

import br.com.biopark.cpa.models.Curso;
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
    private String sobrenome;
    private long idCargo;
    private Date dataNascimento;
    private String imagem;

    public UsuarioDTO() {

    }

    public UsuarioDTO(String email, String nome, String sobrenome, Long idCargo, String imagem) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.idCargo = idCargo;
        this.imagem = imagem;
    }

    public UsuarioDTO(Usuario usuario) {
        this.email = usuario.getEmail();
        this.nome = usuario.getNome();
        this.sobrenome = usuario.getSobrenome();
        this.idCargo = usuario.getCargo().getId();
        this.dataNascimento = usuario.getDataNascimento();
        this.imagem = usuario.getImagem();
    }

    public static Page<UsuarioDTO> converter(Page<Usuario> usuarios){
        return usuarios.map(UsuarioDTO::new);
    }

}
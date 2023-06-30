package br.com.biopark.cpa.controller.form;

import java.util.Date;

import br.com.biopark.cpa.models.Cargo;
import br.com.biopark.cpa.models.Usuario;
import br.com.biopark.cpa.repository.CargoRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioForm {
    
    @NotBlank
    @NotNull
    private String nome;
    private String sobrenome;
    private String senhaAtual;
    @NotNull
    @NotBlank
    private String senha;
    @NotNull
    @NotBlank
    private String email;
    private Cargo cargo;
    @NotNull
    private long idCargo;
    private Date dataNascimento;
    private String imagem;
    private Boolean ativo;

    public Usuario converter(CargoRepository cargoRepository){
        return new Usuario(this.nome, this.email, this.senha, cargoRepository.findById(idCargo), this.ativo);
    }

}

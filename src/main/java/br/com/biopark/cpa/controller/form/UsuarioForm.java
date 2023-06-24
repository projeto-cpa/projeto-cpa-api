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
    // @NotBlank
    // @NotNull
    private String sobrenome;
    @NotNull
    @NotBlank
    private String senha;
    @NotNull
    @NotBlank
    private String email;
    private Cargo cargo;
    @NotNull
    private long id_cargo;
    // @NotNull
    private Date dataNascimento;
    private String imagem;
    @NotNull
    private Boolean ativo;

    // public Usuario converter(CargoRepository cargoRepository){
    //     return new Usuario(this.nome, this.sobrenome, senha, cargoRepository.findById(cargoId), dataNascimento, this.email, this.imagem);
    // }

    public Usuario converter(CargoRepository cargoRepository){
        return new Usuario(this.nome, this.email, this.senha, cargoRepository.findById(id_cargo), this.ativo);
    }

}

package br.com.biopark.cpa.controller.form;

import java.util.Date;

import br.com.biopark.cpa.models.Usuario;
import br.com.biopark.cpa.repository.CargoRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UsuarioForm {
    
    @NotBlank
    @NotNull
    private String nome;

    @NotBlank
    @NotNull
    private String sobrenome;

    @NotNull
    @NotBlank
    private String senha;
    
    @NotNull
    private long cargoId;

    @NotNull
    private Date dataNascimento;

    public Usuario converter(CargoRepository cargoRepository){
        return new Usuario(this.nome, this.sobrenome, senha, cargoRepository.findById(cargoId), dataNascimento);
    }

}
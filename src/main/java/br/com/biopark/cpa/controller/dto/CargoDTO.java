package br.com.biopark.cpa.controller.dto;


import java.util.Date;

import br.com.biopark.cpa.models.Cargo;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CargoDTO {
    

    private long id;
    private String nome;
    private Date createdAt;
    private Date updatedAt;
    private boolean cadastrado;

    public CargoDTO(Cargo cargo) {
        this.id = cargo.getId();
        this.nome = cargo.getNome();
        this.cadastrado = true;
        this.createdAt = cargo.getCreatedAt();
        this.updatedAt = cargo.getUpdatedAt();
    }
}

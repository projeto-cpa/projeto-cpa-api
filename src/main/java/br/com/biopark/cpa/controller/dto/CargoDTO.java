package br.com.biopark.cpa.controller.dto;


import java.util.Date;

import br.com.biopark.cpa.models.Cargo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CargoDTO {

    private long id;
    private String nome;
    private String descricao;
    private boolean ativo;
    private Date criadoEm;
    private Date atualizadoEm;
    private Boolean sucesso;

    public CargoDTO(Cargo cargo) {
        this.id = cargo.getId();
        this.nome = cargo.getNome();
        this.descricao = cargo.getDescricao();
        this.ativo = cargo.getAtivo(); 
        this.sucesso = true;
        this.criadoEm = cargo.getCriadoEm();
        this.atualizadoEm = cargo.getAtualizadoEm();
    }
}

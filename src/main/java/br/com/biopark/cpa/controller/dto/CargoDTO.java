package br.com.biopark.cpa.controller.dto;


import java.util.Date;

import org.springframework.data.domain.Page;

import br.com.biopark.cpa.models.Cargo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CargoDTO {

    private long id;
    private String nome;
    private String descricao;
    private Boolean ativo;
    private Date dataCriacao;
    private Date dataAtualizacao;
    private Boolean sucesso;

    public CargoDTO(Cargo cargo) {
        this.id = cargo.getId();
        this.nome = cargo.getNome();
        this.descricao = cargo.getDescricao();
        this.ativo = cargo.getAtivo(); 
        this.sucesso = true;
        this.dataCriacao = cargo.getDataCriacao();
        this.dataAtualizacao = cargo.getDataAtualizacao();
    }

    public static Page<CargoDTO> converter(Page<Cargo> cargos){
        return cargos.map(CargoDTO::new);
    }
}

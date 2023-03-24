package br.com.biopark.cpa.controller.dto;

import java.util.Date;

import br.com.biopark.cpa.models.Eixo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class EixoDTO {
    
    private long id;
    private String nome;
    private String descricao;
    private Date createdAt;
    private Date updatedAt;

    public EixoDTO(Eixo eixo){
        this.id = eixo.getId();
        this.nome = eixo.getNome();
        this.descricao = eixo.getDescricao();
        this.createdAt = eixo.getCreatedAt();
        this.updatedAt = eixo.getUpdatedAt();
    }
}

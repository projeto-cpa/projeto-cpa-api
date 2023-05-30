package br.com.biopark.cpa.controller.dto;

import java.util.Date;

import br.com.biopark.cpa.models.Cargo;
import br.com.biopark.cpa.models.Eixo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter @Setter
@NoArgsConstructor
public class EixoDTO {
    
    private long id;
    private String nome;
    private String descricao;
    private Date createdAt;
    private Date updatedAt;
    private boolean sucesso;

    public EixoDTO(Eixo eixo){
        this.id = eixo.getId();
        this.nome = eixo.getNome();
        this.descricao = eixo.getDescricao();
        this.createdAt = eixo.getDataCriacao();
        this.updatedAt = eixo.getDataAtualizacao();
        this.sucesso = true;
    }

    public static  Page<EixoDTO> converter(Page<Eixo> eixos) {
        return eixos.map(EixoDTO::new);
    }
}

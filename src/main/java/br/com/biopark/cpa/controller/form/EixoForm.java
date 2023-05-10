package br.com.biopark.cpa.controller.form;

import br.com.biopark.cpa.models.Eixo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class EixoForm {

    @NotBlank
    @NotNull
    private String nome;

    @NotBlank
    @NotNull
    private String descricao;

    @NotNull
    private boolean ativo;

    /**
     * Converte o objeto form para o objeto de entidade passado como parametro e insere os novos dados
     *
     * @param eixo
     * @return
     */
    public Eixo converterParaAtuaizacao(Eixo eixo) {
        eixo.setNome(this.nome);
        eixo.setDescricao(this.descricao);
        eixo.setAtivo(this.ativo);
        return eixo;
    }
}

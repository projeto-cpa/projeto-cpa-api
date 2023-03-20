package br.com.biopark.cpa.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Pergunta")
@NoArgsConstructor
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private long id;

    @Column
    @Getter
    @Setter
    private String descricao;

    // Adicionar ID_Eixo, tipo_respostas.

    @Column
    @Getter
    @Setter
    private Boolean ativo;

    @Column(name = "criado_em")
    @Getter
    @Setter
    private Date criadoEm;

    @Column(name = "atualizado_em")
    @Getter
    @Setter
    private Date atualizadoEm;

    public Pergunta(String descricao, Boolean ativo) {
        this.descricao = descricao;
        this.ativo = ativo;
        this.criadoEm = new Date();
        this.atualizadoEm = new Date();
    }

}
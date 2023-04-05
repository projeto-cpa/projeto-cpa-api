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
@Table(name = "turma")
@NoArgsConstructor
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private long id;

    @NotNull
    @Column
    @Getter
    @Setter
    private String nome;

    @Column
    @Getter
    @Setter
    private String descricao;

    @Column
    @Getter
    @Setter
    private String periodo;

    @Column
    @Getter
    @Setter
    private String curso;

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

    public Turma(String nome, String descricao, String periodo, String curso, Boolean ativo) {
        this.nome = nome;
        this.descricao = descricao;
        this.periodo = periodo;
        this.curso = curso;
        this.ativo = ativo;
        this.criadoEm = new Date();
        this.atualizadoEm = new Date();
    }
}

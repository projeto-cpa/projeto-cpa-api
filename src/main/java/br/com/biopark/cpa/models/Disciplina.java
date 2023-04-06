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
import lombok.Setter;

@Entity
@Table(name = "disciplina")
@Getter
@Setter
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_disciplina")
    @Getter
    @Setter
    private long id;

    @Column
    @Getter
    @Setter
    private Boolean ativo;

    @NotNull
    @Column
    @Getter
    @Setter
    private String nome;

    @NotNull
    @Column
    @Getter
    @Setter
    private Curso curso;

    @NotNull
    @Column
    @Getter
    @Setter
    private String descricao;

    @Column(name = "data_criacao")
    @Getter
    @Setter
    private Date dataCriacao;

    @Column(name = "data_atualizacao")
    @Getter
    @Setter
    private Date dataAtualizacao;

    public Disciplina() {
    }

    public Disciplina(Boolean ativo, String nome, String descricao, Curso curso) {
        this.ativo = ativo;
        this.nome = nome;
        this.descricao = descricao;
        this.dataCriacao = new Date();
        this.dataAtualizacao = new Date();
    }
}

package br.com.biopark.cpa.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    private long id;

    @Column
    private Boolean ativo;

    @NotNull
    @Column
    private String nome;

    @NotNull
    @Column
    private String descricao;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_curso")
    private Curso curso;

    @Column(name = "data_criacao")
    private Date dataCriacao;

    @Column(name = "data_atualizacao")
    private Date dataAtualizacao;

    public Disciplina() {
    }

    public Disciplina(Boolean ativo, String nome, String descricao, Curso curso) {
        this.ativo = ativo;
        this.nome = nome;
        this.descricao = descricao;
        this.dataCriacao = new Date();
        this.dataAtualizacao = new Date();
        this.curso = curso;
    }

}

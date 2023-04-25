package br.com.biopark.cpa.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "curso")
@Getter
@Setter
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curso")
    private long id;

    @Column
    private Boolean ativo;

    @NotNull
    @Column
    private String nome;

    @Column
    private String descricao;

    @NotNull
    @OneToMany(mappedBy = "curso", fetch = FetchType.LAZY)
    private List<Disciplina> disciplinas = new ArrayList<>();

    @NotNull
    @OneToMany(mappedBy = "curso", fetch = FetchType.LAZY)
    private List<Turma> turmas = new ArrayList<>();

    @Column(name = "data_criacao")
    private Date dataCriacao;

    @Column(name = "data_atualizacao")
    private Date dataAtualizacao;

    public Curso() {

    }

    public Curso(Boolean ativo, String nome, String descricao) {
        this.ativo = ativo;
        this.nome = nome;
        this.descricao = descricao;
        this.dataCriacao = new Date();
        this.dataAtualizacao = new Date();
    }

}

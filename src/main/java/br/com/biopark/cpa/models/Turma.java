package br.com.biopark.cpa.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "turma")
@Getter
@Setter
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column
    private Boolean ativo;

    @NotNull
    @Column
    private String nome;

    @Column
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_curso")
    private Curso curso;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "turma")
    private List<Usuario> usuarios = new ArrayList<>();

    @Column(name = "data_criacao")
    private Date dataCriacao;

    @Column(name = "data_atualizacao")
    private Date dataAtualizacao;

    public Turma() {
        
    }

    public Turma(String nome, String descricao, Boolean ativo) {
        this.nome = nome;
        this.descricao = descricao;
        this.ativo = ativo;
        this.dataCriacao = new Date();
        this.dataAtualizacao = new Date();
    }
}

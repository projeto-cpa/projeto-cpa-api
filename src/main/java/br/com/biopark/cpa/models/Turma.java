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

    @Column
    @Getter
    @Setter
    private Boolean ativo;

    @NotNull
    @Column
    @Getter
    @Setter
    private String nome;

    @Column
    @Getter
    @Setter
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_curso")
    @Getter
    @Setter
    private Curso curso;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "turma")
    @Getter
    @Setter
    private List<Usuario> usuarios = new ArrayList<>();

    @Column(name = "data_criacao")
    @Getter
    @Setter
    private Date dataCriacao;

    @Column(name = "data_atualizacao")
    @Getter
    @Setter
    private Date dataAtualizacao;

    public Turma(String nome, String descricao, Boolean ativo) {
        this.nome = nome;
        this.descricao = descricao;
        this.ativo = ativo;
        this.dataCriacao = new Date();
        this.dataAtualizacao = new Date();
    }
}

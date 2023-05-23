package br.com.biopark.cpa.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "turma")
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToMany(mappedBy = "turmaList")
    private List<Avaliacao> avaliacaoList = new ArrayList<>();

    @Column(name = "data_criacao")
    private Date dataCriacao;

    @Column(name = "data_atualizacao")
    private Date dataAtualizacao;

    public Turma(String nome, String descricao, Curso curso) {
        this.nome = nome;
        this.descricao = descricao;
        this.curso = curso;
        this.ativo = true;
        this.dataCriacao = new Date();
        this.dataAtualizacao = new Date();
    }
}

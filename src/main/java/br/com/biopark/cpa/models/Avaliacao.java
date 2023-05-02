package br.com.biopark.cpa.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "avaliacao")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String titulo;

    @ManyToMany
    @JoinTable(
            name = "avaliacao_pergunta",
            joinColumns = @JoinColumn(name = "avaliacao_id"),
            inverseJoinColumns = @JoinColumn(name = "pergunta_id")
    )
    private List<Pergunta> perguntaList = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "avaliacao_turma",
            joinColumns = @JoinColumn(name = "avaliacao_id"),
            inverseJoinColumns = @JoinColumn(name = "turma_id")
    )
    private List<Turma> turmaList = new ArrayList<>();

    @Column(name = "data_criacao")
    private Date dataCriacao;

    @Column(name = "data_atualizacao")
    private Date dataAtualizacao;

    @Column(name = "data_expiracao")
    private Date dataExpiracao;
}

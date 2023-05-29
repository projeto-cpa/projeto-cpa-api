package br.com.biopark.cpa.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author joao gama
 */
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

    @NotNull
    @ManyToMany
    @JoinTable(
            name = "avaliacao_pergunta",
            joinColumns = @JoinColumn(name = "avaliacao_id"),
            foreignKey = @ForeignKey(name = "avaliacao_pergunta_fk"),
            inverseJoinColumns = @JoinColumn(name = "pergunta_id")
    )
    private List<Pergunta> perguntaList = new ArrayList<>();

    @NotNull
    @ManyToMany
    @JoinTable(
            name = "avaliacao_turma",
            joinColumns = @JoinColumn(name = "avaliacao_id"),
            foreignKey = @ForeignKey(name = "avaliacao_turma_fk"),
            inverseJoinColumns = @JoinColumn(name = "turma_id")
    )
    private List<Turma> turmaList = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "avaliacao_usuario",
            joinColumns = @JoinColumn(name = "avaliacao_id"),
            foreignKey = @ForeignKey(name = "avaliacao_usuario_fk"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id"),
            inverseForeignKey = @ForeignKey(name = "usuario_avaliacao_fk")
    )
    private List<Usuario> usuarioList = new ArrayList<>();

    @ManyToMany(mappedBy = "avaliacoesRespondidas")
    private List<Usuario> usuarioListRespondentes = new ArrayList<>();

    @OneToMany(mappedBy = "avaliacao")
    private List<Resposta> respostaList = new ArrayList<>();

    @Column(name = "data_criacao")
    private Date dataCriacao;

    @Column(name = "data_atualizacao")
    private Date dataAtualizacao;

    @NotNull
    @Column(name = "data_expiracao")
    private Date dataExpiracao;

    public Avaliacao(String titulo, List<Pergunta> perguntaList, List<Turma> turmaList, Date dataExpiracao) {
        this.titulo = titulo;
        this.perguntaList = perguntaList;
        this.turmaList = turmaList;
        this.dataExpiracao = dataExpiracao;
        this.dataCriacao = new Date();
        this.dataAtualizacao = new Date();
    }
}

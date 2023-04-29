package br.com.biopark.cpa.models;


import br.com.biopark.cpa.controller.form.EixoForm;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "eixo")
@NoArgsConstructor
@Getter
@Setter
public class Eixo {
        
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

    @Column(name = "data_criacao")
    private Date dataCriacao;

    @Column(name = "data_atualizacao")
    private Date dataAtualizacao;

    @OneToMany(mappedBy = "eixo")
    private List<Pergunta> perguntaList;

    public Eixo(String nome, String descricao, Boolean ativo) {
        this.nome = nome;
        this.descricao = descricao;
        this.ativo = ativo;
        this.dataCriacao = new Date();
        this.dataAtualizacao = new Date();
    }

    public Eixo(EixoForm form) {
        this.nome = form.getNome();
        this.descricao = form.getDescricao();
        this.ativo = form.isAtivo();
        this.dataCriacao = new Date();
        this.dataAtualizacao = new Date();
    }
}

package br.com.biopark.cpa.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pergunta")
@NoArgsConstructor
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private long id;

    @Column
    @Getter
    @Setter
    private String nome;

    @Column
    @Getter
    @Setter
    private String tipo;

    @Column
    @Getter
    @Setter
    private Boolean ativo;

    @Column(name = "data_criacao")
    @Getter
    @Setter
    private Date dataCriacao;

    @Column(name = "data_atualizacao")
    @Getter
    @Setter
    private Date dataAtualizacao;

    public Pergunta(String nome, String tipo, Boolean ativo) {
        this.nome = nome;
        this.tipo = tipo;
        this.ativo = ativo;
        this.dataCriacao = new Date();
        this.dataAtualizacao = new Date();
    }

}
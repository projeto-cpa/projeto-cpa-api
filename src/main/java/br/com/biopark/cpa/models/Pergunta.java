package br.com.biopark.cpa.models;

import java.util.Date;

import br.com.biopark.cpa.enums.TipoPergunta;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pergunta")
@Getter
@Setter
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column
    private String texto;

    @Column
    @Enumerated(EnumType.STRING)
    private TipoPergunta tipo;

    @Column
    private Boolean ativo;

    @Column(name = "data_criacao")
    private Date dataCriacao;

    @Column(name = "data_atualizacao")
    private Date dataAtualizacao;

    public Pergunta() {
        
    }

    public Pergunta(String texto, TipoPergunta tipo, Boolean ativo) {
        this.texto = texto;
        this.tipo = tipo;
        this.ativo = ativo;
        this.dataCriacao = new Date();
        this.dataAtualizacao = new Date();
    }

}
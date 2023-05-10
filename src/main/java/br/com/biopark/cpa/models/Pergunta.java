package br.com.biopark.cpa.models;

import java.util.Date;

import br.com.biopark.cpa.models.enums.TipoPergunta;
import jakarta.persistence.*;
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
    private Boolean ativo;

    @Column
    private String texto;

    @ManyToOne
    @JoinColumn(name = "eixo_id")
    private Eixo eixo;

    @Column
    @Enumerated(EnumType.STRING)
    private TipoPergunta tipo;

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
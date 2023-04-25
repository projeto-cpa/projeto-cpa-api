package br.com.biopark.cpa.models;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "resposta")
@Getter
@Setter
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resposta")
    private long id;

    @Column
    private String texto;

    // TODO: fazer a relação com o usuario
    // @NotNull
    // @JoinColumn(name = "id_usuario")
    // @ManyToOne
    // private Usuario usuario;

    @NotNull
    @JoinColumn(name = "id_pergunta")
    @ManyToOne
    private Pergunta pergunta;

    @Column
    private Long nota;

    @Column(name = "data_criacao")
    private Date dataCriacao;

    @Column(name = "data_atualizacao")
    private Date dataAtualizacao;

    public Resposta() {

    }

    /**
     * Resposta descritiva
     * 
     * @param texto
     * @param pergunta
     * @param usuario
     * @param dataCriacao
     * @param dataAtualizacao
     */
    public Resposta(String texto, Pergunta pergunta) {
        this.texto = texto;
        this.nota = (long) 0;
        this.setPergunta(pergunta);
        this.setDataCriacao(dataCriacao);
        this.setDataAtualizacao(dataAtualizacao);
        this.dataAtualizacao = new Date();
        this.dataCriacao = new Date();
    }

    /**
     * Resposta avaliativa
     * 
     * @param nota
     * @param pergunta
     * @param usuario
     * @param dataCriacao
     * @param dataAtualizacao
     */
    public Resposta(Long nota, Pergunta pergunta) {
        this.nota = nota;
        this.texto = null;
        this.setPergunta(pergunta);
        // this.setUsuario(usuario);
        this.setDataCriacao(dataCriacao);
        this.setDataAtualizacao(dataAtualizacao);
        this.dataAtualizacao = new Date();
        this.dataCriacao = new Date();
    }

}
package br.com.biopark.cpa.models;

import java.util.Date;

import br.com.biopark.cpa.controller.form.RespostaForm;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "resposta")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resposta")
    private long id;

    @Column
    private String texto;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_pergunta")
    private Pergunta pergunta;

    @Column
    private Long nota;

    @ManyToOne
    @JoinColumn(name = "avaliacao_id")
    private Avaliacao avaliacao;

    @Column(name = "data_criacao")
    private Date dataCriacao;

    @Column(name = "data_atualizacao")
    private Date dataAtualizacao;

    public Resposta(String texto, Long nota, Pergunta pergunta, Avaliacao avaliacao) {
        this.texto = texto;
        this.nota = nota;
        this.pergunta = pergunta;
        this.avaliacao = avaliacao;
        this.dataAtualizacao = new Date();
        this.dataCriacao = new Date();
    }
}
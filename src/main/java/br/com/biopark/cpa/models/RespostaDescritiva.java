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
@Table(name = "resposta_descritiva")
public class RespostaDescritiva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resposta_descritiva")
    @Getter @Setter
    private long id;

    @NotNull
    @Column
    @Getter @Setter
    private String texto;

    @NotNull
    @JoinColumn(name = "id_pergunta")
    @ManyToOne
    @Getter @Setter
    private Pergunta pergunta;

    @NotNull
    @JoinColumn(name = "id_usuario")
    @ManyToOne
    @Getter @Setter
    private Usuario usuario;

    @NotNull
    @JoinColumn(name = "id_eixo")
    @ManyToOne
    @Getter @Setter
    private Eixo eixo;

    @Column(name = "data_criacao")
    @Getter
    @Setter
    private Date dataCriacao;

    @Column(name = "data_atualizacao")
    @Getter
    @Setter
    private Date dataAtualizacao;

    public RespostaDescritiva() {

    }

    public RespostaDescritiva(String texto, Pergunta pergunta, Usuario usuario, Eixo eixo, Date dataNascimento) {
        this.texto = texto;
        this.setPergunta(pergunta);
        this.setUsuario(usuario);
        this.setEixo(eixo);
        this.dataCriacao = new Date();
        this.dataAtualizacao = new Date();
    }
}
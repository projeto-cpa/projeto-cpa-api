package br.com.biopark.cpa.models;


import br.com.biopark.cpa.controller.form.EixoForm;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Getter @Setter
public class Eixo {
        
    @Id
    @Column(name = "id")
    @Getter
    @Setter
    private long id;

    @Column
    @Getter
    @Setter
    private Boolean ativo;

    @NotNull
    @Column
    @Getter
    @Setter
    private String nome;

    @Column
    @Getter
    @Setter
    private String descricao;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_eixo")
    @Getter
    @Setter
    private List<Pergunta> turmas = new ArrayList<>();

    @Column(name = "data_criacao")
    @Getter
    @Setter
    private Date dataCriacao;

    @Column(name = "data_atualizacao")
    @Getter
    @Setter
    private Date dataAtualizacao;

    public Eixo(String nome, String descricao, Boolean ativo) {
        this.nome = nome;
        this.descricao = descricao;
        this.ativo = ativo;
        this.dataCriacao = new Date();
        this.dataAtualizacao = new Date();
    }

    public Date getCreatedAt() {
        return null;
    }
}

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
@Table(name = "usuario")
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private long id;
    
    // TODO: Colocar o campo ativo

    @NotNull
    @Column
    private String nome;

    @NotNull
    @Column 
    private String sobrenome;

    @NotNull
    @Column
    private String senha;

    @ManyToOne
    @JoinColumn(name = "id_turma")

    private Turma turma;

    @NotNull
    @Column(name = "data_nascimento")
    private Date dataNascimento;

    @NotNull
    @JoinColumn(name = "id_cargo")
    @ManyToOne
    private Cargo cargo;

    @Column(name = "data_criacao")
    private Date dataCriacao;

    @Column(name = "data_atualizacao")
    private Date dataAtualizacao;

    public Usuario() {

    }

    public Usuario(String nome, String sobrenome, String senha, Cargo cargo, Date dataNascimento) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.senha = senha;
        this.setCargo(cargo);
        this.dataCriacao = new Date();
        this.dataAtualizacao = new Date();
        this.dataNascimento = dataNascimento;
    }
}
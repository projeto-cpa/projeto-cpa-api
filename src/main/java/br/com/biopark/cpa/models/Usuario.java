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
@Table(name = "Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private long id;

    @NotNull
    @Column
    @Getter
    @Setter
    private String nome;

    @NotNull
    @Column 
    @Getter
    @Setter
    private String sobrenome;

    @NotNull
    @Column
    @Getter
    @Setter
    private String senha;

    @NotNull
    @Column(name = "data_nascimento")
    @Getter
    @Setter
    private Date dataNascimento;

    @NotNull
    @JoinColumn(name = "id_cargo")
    @ManyToOne
    @Getter
    @Setter
    private Cargo cargo;

    @Column(name = "created_at")
    @Getter
    @Setter
    private Date createdAt;

    @Column(name = "updated_at") 
    @Getter
    @Setter
    private Date updatedAt;

    public Usuario() {

    }

    public Usuario(String nome, String sobrenome, String senha, Cargo cargo, Date dataNascimento) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.senha = senha;
        this.setCargo(cargo);
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.dataNascimento = dataNascimento;
    }
}

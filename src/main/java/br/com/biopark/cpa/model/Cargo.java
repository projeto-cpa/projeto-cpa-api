package br.com.biopark.cpa.model;

import java.security.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cargo")
@Embeddable
public class Cargo {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "nome")
    private String nome;

    // controle de historico

    @Column(name = "dataCriacao")
    private Timestamp dataCriacao;

    @Column(name = "dataModificacao")
    private Timestamp dataModificacao;

    @Column(name = "criadoPor")
    private int criadoPor;

    @Column(name = "modificadoPor")
    private int modificadoPor;

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

}

package br.com.biopark.cpa.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Cargo")
@NoArgsConstructor
public class Cargo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @Getter @Setter
    private long id;

    @NotNull
    @Column
    @Getter @Setter
    private String nome;

    @Column(name = "created_at")
    @Getter @Setter
    private Date createdAt;

    @Column(name = "updated_at")
    @Getter @Setter
    private Date updatedAt;

    public Cargo(String nome) {
        this.nome = nome;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }
}

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
import lombok.Setter;

@Entity
@Table(name = "Cargo")
public class Cargo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_cargo")
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
}

package br.com.biopark.cpa.models;

import java.util.Date;

import br.com.biopark.cpa.controller.form.EixoForm;
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
@Table(name = "eixo")
@NoArgsConstructor
@Getter @Setter
public class Eixo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_eixo")
    private long id;

    @NotNull
    @Column
    private String nome;

    @NotNull
    @Column
    private String descricao;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    public Eixo(EixoForm form){
        this.nome = form.getNome();
        this.descricao = form.getDescricao();
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }
}

package br.com.biopark.cpa.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "Usuario")
@Getter
@Setter
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private long id;

    @Column
    private Boolean ativo;

    @NotNull
    @Column
    private String email;

    @NotNull
    @Column
    private String nome;

    @Column
    private String sobrenome;

    @NotNull
    @Column
    private String senha;

    @ManyToOne
    @JoinColumn(name = "id_turma")
    private Turma turma;

    @Column(name = "data_nascimento")
    private Date dataNascimento;

    @NotNull
    @JoinColumn(name = "id_cargo")
    @ManyToOne
    private Cargo cargo;

    @ManyToMany(mappedBy = "usuarioList")
    private List<Avaliacao> avaliacaoList = new ArrayList<>();

    @Lob
    @Column(length = 99999999)
    private String imagem;

    @Column(name = "codigo_recuperacao")
    private String codigoRecuperacao;

    @Column(name = "data_criacao")
    private Date dataCriacao;

    @Column(name = "data_atualizacao")
    private Date dataAtualizacao;

    public Usuario () {}

    public Usuario(String nome, String email, String senha, Cargo cargo, Boolean ativo) {
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.cargo = cargo;
        this.dataCriacao = new Date();
        this.dataAtualizacao = new Date();
        // this.dataNascimento = dataNascimento;
        // this.imagem = imagem;
        // this.sobrenome = sobrenome;
        this.ativo = ativo;
        // this.setCargo(cargo);
    }


    /**
     * @return - Lista de regras de usuario
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

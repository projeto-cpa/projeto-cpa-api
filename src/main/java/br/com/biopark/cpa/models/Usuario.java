package br.com.biopark.cpa.models;

import java.util.Collection;
import java.util.Date;
import java.util.List;

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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "Usuario")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_usuario")
    @Getter @Setter
    private long id;

    @NotNull
    @Column
    @Getter @Setter
    private String nome;

    @NotNull
    @Column 
    @Getter @Setter
    private String sobrenome;

    @NotNull
    @Column
    @Getter @Setter
    private String senha;

    @NotNull
    @Column(name = "data_nascimento")
    @Getter @Setter
    private Date dataNascimento;

    @NotNull
    @JoinColumn(name = "id_cargo")
    @ManyToOne
    @Getter @Setter
    private Cargo cargo;

    @Column(name = "created_at")
    @Getter @Setter
    private Date createdAt;

    @Column(name = "updated_at") 
    @Getter @Setter
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
        return this.nome;
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

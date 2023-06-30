package br.com.biopark.cpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.biopark.cpa.models.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    Usuario findById(long id);

    Optional<Usuario> findById(Long usuarioRespondenteId);

    Usuario findByNome(String nome);

    Usuario findByEmail(String email);

    
}
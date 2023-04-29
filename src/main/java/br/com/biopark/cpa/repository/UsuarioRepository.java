package br.com.biopark.cpa.repository;

<<<<<<< Updated upstream
=======
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
>>>>>>> Stashed changes
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.biopark.cpa.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    public Usuario findById(long id);

<<<<<<< Updated upstream
=======
    public Page<Usuario> findByNome(String nome, Pageable pageable);
>>>>>>> Stashed changes
}
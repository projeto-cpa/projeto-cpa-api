package br.com.biopark.cpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.biopark.cpa.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Usuario findById(long id);

    public Usuario findByAtivo(boolean ativo);

    public Usuario findByNome(String nome);

}

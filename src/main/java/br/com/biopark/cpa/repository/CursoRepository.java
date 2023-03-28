package br.com.biopark.cpa.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.biopark.cpa.models.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long>{
    
    public Curso findById(long id);

    public Curso findByNome(String nome);
    
}

package br.com.biopark.cpa.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.biopark.cpa.models.Disciplina;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long>{
    
    public Disciplina findById(long id);
    
}

package br.com.biopark.cpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.biopark.cpa.models.Disciplina;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long>{
    
    public Disciplina findById(long id);

    public Disciplina findByAtivo(boolean ativo);

    public Disciplina findByNome(String nome);
    
}

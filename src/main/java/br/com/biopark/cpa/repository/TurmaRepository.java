package br.com.biopark.cpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.biopark.cpa.models.Turma;

public interface TurmaRepository extends JpaRepository<Turma, Long>{
    
    public Turma findById(long id);
    
}

package br.com.biopark.cpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.biopark.cpa.models.Pergunta;

public interface PerguntaRepository extends JpaRepository<Pergunta, Long> {

    public Pergunta findById(long id);
    
}
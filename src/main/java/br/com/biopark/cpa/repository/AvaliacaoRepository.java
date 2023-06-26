package br.com.biopark.cpa.repository;

import br.com.biopark.cpa.models.Avaliacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    public Avaliacao findByTitulo (String titulo);
    
}

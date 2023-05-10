package br.com.biopark.cpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.biopark.cpa.models.Eixo;

public interface EixoRepository extends JpaRepository<Eixo, Long>{

    Page<Eixo> findByNome(String nomeEixo, Pageable pageable);
}

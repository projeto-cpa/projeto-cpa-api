package br.com.biopark.cpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.biopark.cpa.models.Resposta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RespostaRepository extends JpaRepository<Resposta, Long> {

    public Optional<Resposta> findById(Long id);

    public Page<Resposta> findByNota(Long nota, Pageable paginacao);

    public Page<Resposta> findByTexto(String texto, Pageable paginacao);

}
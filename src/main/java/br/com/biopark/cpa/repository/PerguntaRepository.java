package br.com.biopark.cpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.biopark.cpa.models.Pergunta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PerguntaRepository extends JpaRepository<Pergunta, Long> {

    Optional<Pergunta> findById(Long id);

    Pergunta findByAtivo(boolean ativo);

    List<Pergunta> findAllByIdIn(List<Long> listaPerguntas);
  
    Page<Pergunta> findByTexto(String textoPergunta, Pageable paginacao);
}
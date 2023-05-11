package br.com.biopark.cpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.biopark.cpa.models.Pergunta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PerguntaRepository extends JpaRepository<Pergunta, Long> {

    public Optional<Pergunta> findById(Long id);

    public Pergunta findByAtivo(boolean ativo);

    public Page<Pergunta> findByTexto(String textoPergunta, Pageable paginacao);

}
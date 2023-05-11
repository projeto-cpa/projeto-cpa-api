package br.com.biopark.cpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.biopark.cpa.models.Pergunta;

public interface PerguntaRepository extends JpaRepository<Pergunta, Long> {

    public Optional<Pergunta> findById(Long id);

    public Pergunta findByAtivo(boolean ativo);

    Page<Pergunta> findByEixo(String nomeEixo, Pageable pageable);

    Page<Pergunta> findByEixoNome(String nomeEixo, Pageable pageable);

    List<Pergunta> findAllByIdIn(List<Long> listaPerguntas);
}
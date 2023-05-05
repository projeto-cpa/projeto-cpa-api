package br.com.biopark.cpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.biopark.cpa.models.Pergunta;

public interface PerguntaRepository extends JpaRepository<Pergunta, Long> {

    public Optional<Pergunta> findById(Long id);

    public Pergunta findByAtivo(boolean ativo);

    public Pergunta findByTexto(String texto);

}
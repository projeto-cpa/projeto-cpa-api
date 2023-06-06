package br.com.biopark.cpa.repository;


import br.com.biopark.cpa.models.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TurmaRepository extends JpaRepository<Turma, Long> {
    List<Turma> findAllByIdIn(List<Long> listaTurmas);
}

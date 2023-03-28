package br.com.biopark.cpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.biopark.cpa.models.Curso;
import br.com.biopark.cpa.models.Disciplina;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    public Curso findById(long id);

    public Curso findByAtivo(boolean ativo);

    public Curso findByNome(String nome);

    public List<Curso> findByDisciplinas(List<Disciplina> disciplinas);

}

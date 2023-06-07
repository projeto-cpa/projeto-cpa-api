package br.com.biopark.cpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.biopark.cpa.models.Curso;
import jakarta.transaction.Transactional;

@Transactional
public interface CursoRepository extends JpaRepository<Curso, Long> {

    public Curso findById(long id);

    public Curso findByAtivo(boolean ativo);

    //public Curso findByNome(String nome);

    public Page<Curso> findByNome(String nomeCurso, Pageable paginacao);
}

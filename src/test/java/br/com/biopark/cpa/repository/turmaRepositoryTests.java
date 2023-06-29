package br.com.biopark.cpa.repository;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import br.com.biopark.cpa.models.Curso;
import br.com.biopark.cpa.models.Turma;

@RunWith(SpringRunner.class)
@DataJpaTest
public class turmaRepositoryTests {

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Test
    public void testePersistenciaRespostas() throws Exception {
        Curso curso = new Curso(true, "Banco de dados", "Banco de dados ADS");
        entityManager.persist(curso);

        Turma turma = new Turma("3° Periodo - ADS", "Noturno", curso, "Ultimo teste boy");
        entityManager.persist(turma);

        Pageable pageable = PageRequest.of(0, 5);

        Turma turmaPersistido = turmaRepository.findByNome(turma.getNome(), pageable).getContent().get(0);
        Curso cursoPersistido = cursoRepository.findByNome(curso.getNome(), pageable).getContent().get(0);

        entityManager.flush();
        entityManager.clear();

        // afirmacoes
        Assert.assertEquals(turmaPersistido.getNome(), turma.getNome());
        Assert.assertEquals(turmaPersistido.getDescricao(), turma.getDescricao());
        Assert.assertEquals(turmaPersistido.getAtivo(), turma.getAtivo());

        Assert.assertEquals(cursoPersistido.getNome(), cursoPersistido.getNome());
        Assert.assertEquals(cursoPersistido.getDescricao(), cursoPersistido.getDescricao());
        Assert.assertEquals(cursoPersistido.getAtivo(), cursoPersistido.getAtivo());

    }
}

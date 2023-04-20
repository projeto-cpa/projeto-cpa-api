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

import br.com.biopark.cpa.models.Curso;
import br.com.biopark.cpa.models.Disciplina;

@RunWith(SpringRunner.class)
@DataJpaTest
public class disciplinaRepositoryTests {

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Test
    public void testePersistenciaDisciplina() throws Exception {

        // cria, persiste e limpa
        Curso curso = new Curso(true, "Banco de Dados", "Delete sem where");

        entityManager.persist(curso);
        entityManager.flush();
        entityManager.clear();

        Curso cursoPersistido = cursoRepository.findByNome(curso.getNome());

        // ----------------------------------

        Disciplina disciplina = new Disciplina(true, "Banco de Dados", "Delete sem where", cursoPersistido);
        
        entityManager.persist(disciplina);
        entityManager.flush();
        entityManager.clear();

        Disciplina disciplinaPersistido = disciplinaRepository.findByNome(disciplina.getNome());
        
        // afirmacoes curso
    

        // afirmacoes disciplina
        Assert.assertEquals(disciplinaPersistido.getNome(), disciplina.getNome());
        Assert.assertEquals(disciplinaPersistido.getDescricao(), disciplina.getDescricao());
        Assert.assertEquals(disciplinaPersistido.getAtivo(), disciplina.getAtivo());
    }
}

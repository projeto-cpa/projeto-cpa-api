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

@RunWith(SpringRunner.class)
@DataJpaTest
public class cursoRepositoryTests {

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CursoRepository cursoRepository;

    @Test
    public void testePersistenciaCursos() throws Exception {

        // cria, persiste e limpa
        Curso ads = new Curso(true, "Analise e Desenvolvimento de Sistemas", "Formata meu pc");
        
        entityManager.persist(ads);
        entityManager.flush();
        entityManager.clear();

        Curso persistido = cursoRepository.findByNome(ads.getNome());

        // afirmacoes
        Assert.assertEquals(persistido.getNome(), ads.getNome());
        Assert.assertEquals(persistido.getDescricao(), ads.getDescricao());
        Assert.assertEquals(persistido.getAtivo(), ads.getAtivo());
    }
}

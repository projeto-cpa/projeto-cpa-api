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

    @Test
    public void testePersistenciaDisciplinas() throws Exception {

        // cria, persiste e limpa
        Curso ads = new Curso(false, "Analise e Desenvolvimento de Sistemas", "Formata meu pc");
        Disciplina banco = new Disciplina(false, "Banco de dados", "Nao removendo o banco", ads);
        
        entityManager.persist(banco);
        //entityManager.flush();
        entityManager.clear();

        Disciplina persistido = disciplinaRepository.findByNome(banco.getNome());

        // afirmacoes
        Assert.assertEquals(persistido.getNome(), banco.getNome());
        Assert.assertEquals(persistido.getDescricao(), banco.getDescricao());
        Assert.assertEquals(persistido.getAtivo(), banco.getAtivo());
    }
}

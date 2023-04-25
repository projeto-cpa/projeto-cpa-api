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

import br.com.biopark.cpa.models.Pergunta;
import br.com.biopark.cpa.models.enums.TipoPergunta;

@RunWith(SpringRunner.class)
@DataJpaTest
public class perguntaRepositoryTests {

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PerguntaRepository perguntaRepository;

    @Test
    public void testePersistenciaPerguntas() throws Exception {
        // cria, persiste e limpa
        Pergunta perguntaUm = new Pergunta("Como foi seu dia?", TipoPergunta.DESCRITIVA, true);

        entityManager.persist(perguntaUm);
        entityManager.flush();
        entityManager.clear();

        Pergunta persistido = perguntaRepository.findByTexto(perguntaUm.getTexto());

        // afirmacoes
        Assert.assertEquals(persistido.getTexto(), perguntaUm.getTexto());
        Assert.assertEquals(persistido.getTipo(), perguntaUm.getTipo());
        Assert.assertEquals(persistido.getAtivo(), perguntaUm.getAtivo());
    }
}

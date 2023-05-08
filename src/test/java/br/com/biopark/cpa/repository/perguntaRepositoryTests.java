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
        Pageable pageable = PageRequest.of(0, 5);
        Pergunta persistidoPergunta = perguntaRepository.findByTexto(perguntaUm.getTexto(), pageable).getContent()
                .get(0);

        entityManager.flush();
        entityManager.clear();

        // afirmacoes
        Assert.assertNotNull(persistidoPergunta);
        Assert.assertEquals(persistidoPergunta.getTexto(), perguntaUm.getTexto());
        Assert.assertEquals(persistidoPergunta.getTipo(), perguntaUm.getTipo());
        Assert.assertEquals(persistidoPergunta.getAtivo(), perguntaUm.getAtivo());
    }
}

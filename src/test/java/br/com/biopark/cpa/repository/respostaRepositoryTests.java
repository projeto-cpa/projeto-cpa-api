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
import br.com.biopark.cpa.models.Resposta;
import br.com.biopark.cpa.models.enums.TipoPergunta;

@RunWith(SpringRunner.class)
@DataJpaTest
public class respostaRepositoryTests {

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PerguntaRepository perguntaRepository;

    @Autowired
    private RespostaRepository respostaRepository;

    @Test
    public void testePersistenciaRespostas() throws Exception {
        Pergunta perguntaUm = new Pergunta("Como foi seu dia?", TipoPergunta.DESCRITIVA, true);
        entityManager.persist(perguntaUm);
        Pergunta persistidoPergunta = perguntaRepository.findByTexto(perguntaUm.getTexto());

        Resposta respostaUm = new Resposta("Resposta padrão...", perguntaUm);
        entityManager.persist(respostaUm);
        Resposta persistidoResposta = respostaRepository.findByTexto(respostaUm.getTexto());

        // afirmacoes da perguntaUm
        Assert.assertNotNull(persistidoPergunta);
        Assert.assertEquals(persistidoPergunta.getTexto(), perguntaUm.getTexto());
        Assert.assertEquals(persistidoPergunta.getTipo(), perguntaUm.getTipo());
        Assert.assertEquals(persistidoPergunta.getAtivo(), perguntaUm.getAtivo());

        // afirmacoes da respostaUm
        Assert.assertNotNull(persistidoResposta);
        Assert.assertEquals(persistidoResposta.getTexto(), respostaUm.getTexto());
        Assert.assertEquals(persistidoResposta.getPergunta(), respostaUm.getPergunta());

        entityManager.flush();
        entityManager.clear();

    }
}

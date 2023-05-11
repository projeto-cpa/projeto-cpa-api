package br.com.biopark.cpa.repository;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.biopark.cpa.models.Eixo;
import br.com.biopark.cpa.models.Pergunta;
import br.com.biopark.cpa.models.enums.TipoPergunta;

@RunWith(SpringRunner.class)
@DataJpaTest
public class eixoRepositoryTests {

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EixoRepository eixoRepository;

    @Autowired
    private PerguntaRepository perguntaRepository;

    @Test
    public void testePersistenciaEixo() throws Exception {

        Eixo eixo = new Eixo("Banco de dados", "Banco de dados ADS", true);
        entityManager.persist(eixo);

        Pergunta pergunta = new Pergunta("Qual banco de dados nós usamos?", TipoPergunta.AVALIATIVA, true);
        entityManager.persist(pergunta);
 
        Pageable pageable = PageRequest.of(0, 5);

        Eixo persistidoEixo = eixoRepository.findByNome(eixo.getNome(), pageable).getContent().get(0);
        Pergunta persistidoPergunta = perguntaRepository.findByTexto(pergunta.getTexto(), pageable).getContent().get(0);

        entityManager.flush();
        entityManager.clear();

        // afirmacoes
        Assert.assertEquals(persistidoEixo.getNome(), eixo.getNome());
        Assert.assertEquals(persistidoEixo.getDescricao(), eixo.getDescricao());
        Assert.assertEquals(persistidoEixo.getAtivo(), eixo.getAtivo());

        Assert.assertEquals(persistidoPergunta.getTexto(), persistidoPergunta.getTexto());
        Assert.assertEquals(persistidoPergunta.getTipo(), persistidoPergunta.getTipo());
        Assert.assertEquals(persistidoPergunta.getAtivo(), persistidoPergunta.getAtivo());
    }
}

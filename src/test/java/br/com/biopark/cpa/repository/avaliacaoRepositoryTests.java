package br.com.biopark.cpa.repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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

import br.com.biopark.cpa.models.Avaliacao;
import br.com.biopark.cpa.models.Pergunta;

@RunWith(SpringRunner.class)
@DataJpaTest
public class avaliacaoRepositoryTests {

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Test
    public void testePersistenciaAvaliacao() throws Exception {
        // cria, persiste e limpa
        Pergunta pergunta = new Pergunta("Pergunta 1", null, null, null);

        List<Pergunta> listaPergunta = new ArrayList<>();

        pergunta.add(listaPergunta);

        Avaliacao avaliacao = new Avaliacao("Avaliacao 1", "Acertei quantas perguntas?", "ADS", );
        entityManager.persist(avaliacao);
        Pageable pageable = PageRequest.of(0, 5);
        Avaliacao persistidoAvaliacao = avaliacaoRepository.findByTitulo(avaliacao.getTitulo(), pageable).getContent().get(0);

        entityManager.flush();
        entityManager.clear();

        // afirmacoes
        Assert.assertNotNull(persistidoAvaliacao);
        Assert.assertEquals(persistidoAvaliacao.getTitulo(), avaliacao.getTitulo());
        Assert.assertEquals(persistidoAvaliacao.getPerguntaList(), avaliacao.getPerguntaList());
        Assert.assertEquals(persistidoAvaliacao.getRespostaList(), avaliacao.getRespostaList());
        Assert.assertEquals(persistidoAvaliacao.getTurmaList(), avaliacao.getTurmaList());
        Assert.assertEquals(persistidoAvaliacao.getUsuarioList(), avaliacao.getUsuarioList());
        Assert.assertEquals(persistidoAvaliacao.getDataCriacao(), avaliacao.getDataCriacao());
        Assert.assertEquals(persistidoAvaliacao.getDataAtualizacao(), avaliacao.getDataAtualizacao());
        
    }
}

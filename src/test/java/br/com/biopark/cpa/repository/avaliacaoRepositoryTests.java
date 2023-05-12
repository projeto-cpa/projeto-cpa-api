package br.com.biopark.cpa.repository;

import java.util.Date;
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

import br.com.biopark.cpa.models.Avaliacao;
import br.com.biopark.cpa.models.Pergunta;
import br.com.biopark.cpa.models.Turma;

@RunWith(SpringRunner.class)
@DataJpaTest
public class avaliacaoRepositoryTests {

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;
    private PerguntaRepository perguntaRepository;
    private TurmaRepository turmaRepository;

    @Test
    public void testePersistenciaAvaliacao() throws Exception {
        // cria, persiste e limpa

        //cria os atributos com os valores que o construtor da classe avaliação espera
        String titulo = "Avaliacao 1";

        List<Long> listaIDPergunta = new ArrayList<>();
        listaIDPergunta.add(1L);
        listaIDPergunta.add(2L);
        listaIDPergunta.add(3L);

        List<Long> ListaIDTurma = new ArrayList<>();
        ListaIDTurma.add(1l);
        ListaIDTurma.add(2l);
        ListaIDTurma.add(3l);

        Date data = new Date();

        List<Pergunta> listaPerguntas = perguntaRepository.findAllByIdIn(listaIDPergunta);
        List<Turma> listaTurmas = turmaRepository.findAllByIdIn(ListaIDTurma);

        Avaliacao avaliacao = new Avaliacao(titulo, listaPerguntas, listaTurmas, data);
        entityManager.persist(avaliacao);

        Avaliacao persistidoAvaliacao = avaliacaoRepository.findByTitulo(avaliacao.getTitulo());
        
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

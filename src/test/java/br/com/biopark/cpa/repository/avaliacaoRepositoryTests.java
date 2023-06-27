// package br.com.biopark.cpa.repository;

// import java.util.Date;
// import java.util.ArrayList;
// import java.util.List;

// import org.junit.Assert;
// import org.junit.Rule;
// import org.junit.Test;
// import org.junit.rules.ErrorCollector;
// import org.junit.runner.RunWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
// import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
// import org.springframework.test.context.junit4.SpringRunner;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Pageable;
// import br.com.biopark.cpa.models.Avaliacao;
// import br.com.biopark.cpa.models.Eixo;
// import br.com.biopark.cpa.models.Pergunta;
// import br.com.biopark.cpa.models.Turma;
// import br.com.biopark.cpa.models.enums.TipoPergunta;

// @RunWith(SpringRunner.class)
// @DataJpaTest
// public class avaliacaoRepositoryTests {

// @Rule
// public ErrorCollector error = new ErrorCollector();

// @Autowired
// private TestEntityManager entityManager;

// @Autowired
// private AvaliacaoRepository avaliacaoRepository;

// @Autowired
// private EixoRepository eixoRepository;

// @Test
// public void testePersistenciaAvaliacao() throws Exception {
// // cria, persiste e limpa

// //cria os atributos com os valores que o construtor da classe avaliação
// espera
// Eixo eixo = new Eixo("Primeiro eixo", "Eixo 1", true);
// entityManager.persist(eixo);

// Pageable pageable = PageRequest.of(0, 5);
// Eixo eixoPersistido = eixoRepository.findByNome(eixo.getNome(),
// pageable).getContent().get(0);

// Pergunta perguntaUm = new Pergunta("Pergunta 1", TipoPergunta.DESCRITIVA,
// true, eixoPersistido);
// entityManager.persist(perguntaUm);

// Pergunta perguntaDois = new Pergunta("Pergunta 2", TipoPergunta.AVALIATIVA,
// true, eixoPersistido);
// entityManager.persist(perguntaDois);

// Turma turmaUm = new Turma("Turma 1", "Turma que fez a prova", true);
// entityManager.persist(turmaUm);
        
// Turma turmaDois = new Turma("Turma 2", "Turma que fez a prova denovo", true);
// entityManager.persist(turmaDois);

// List<Pergunta> listaPerguntas = new ArrayList<>();
// listaPerguntas.add(perguntaUm);
// listaPerguntas.add(perguntaDois);

// List<Turma> listaTurmas = new ArrayList<>();
// listaTurmas.add(turmaUm);
// listaTurmas.add(turmaDois);

// Date data = new Date();

// Avaliacao avaliacao = new Avaliacao("Avaliação teste", listaPerguntas,
// listaTurmas, data);
// entityManager.persist(avaliacao);

// Avaliacao persistidoAvaliacao =
// avaliacaoRepository.findByTitulo(avaliacao.getTitulo());
        
// entityManager.flush();
// entityManager.clear();

// // afirmacoes
// Assert.assertNotNull(persistidoAvaliacao);
// Assert.assertEquals(persistidoAvaliacao.getTitulo(), avaliacao.getTitulo());
// Assert.assertEquals(persistidoAvaliacao.getPerguntaList(),
// avaliacao.getPerguntaList());
// Assert.assertEquals(persistidoAvaliacao.getTurmaList(),
// avaliacao.getTurmaList());
// Assert.assertEquals(persistidoAvaliacao.getDataExpiracao(),
// avaliacao.getDataExpiracao());
        
// }
// }

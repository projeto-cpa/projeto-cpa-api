// package br.com.biopark.cpa.repository;

// import java.text.SimpleDateFormat;
// import java.util.Date;

// import org.h2.store.Data;
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
// import java.util.ArrayList;
// import java.util.Date;
// import java.util.List;
// import br.com.biopark.cpa.models.Avaliacao;
// import br.com.biopark.cpa.models.Eixo;
// import br.com.biopark.cpa.models.Pergunta;
// import br.com.biopark.cpa.models.Resposta;
// import br.com.biopark.cpa.models.Turma;
// import br.com.biopark.cpa.models.enums.TipoPergunta;
// import ch.qos.logback.core.read.ListAppender;

// @RunWith(SpringRunner.class)
// @DataJpaTest
// public class respostaRepositoryTests {

// @Rule
// public ErrorCollector error = new ErrorCollector();

// @Autowired
// private TestEntityManager entityManager;

// @Autowired
// private PerguntaRepository perguntaRepository;

// @Autowired
// private RespostaRepository respostaRepository;

// @Autowired
// private EixoRepository eixoRepository;

// @Test
// public void testePersistenciaRespostas() throws Exception {
// Eixo eixo = new Eixo("Banco de dados", "Banco de dados ADS", true);
// entityManager.persist(eixo);

// Turma turmaUm = new Turma("Turma Um", "Teste de turma", true);
// entityManager.persist(turmaUm);

// Pergunta perguntaUm = new Pergunta("Como foi seu dia?",
// TipoPergunta.DESCRITIVA, true, eixo);
// entityManager.persist(perguntaUm);

// Pageable pageable = PageRequest.of(0, 5);

// entityManager.flush();
// entityManager.clear();

// List<Pergunta> perguntaList = new ArrayList<Pergunta>();

// perguntaList.add(perguntaUm);

// List<Turma> turmaList = new ArrayList<Turma>();

// turmaList.add(turmaUm);

// Pergunta persistidoPergunta =
// perguntaRepository.findByTexto(perguntaUm.getTexto(), pageable).getContent()
// .get(0);

// SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
// Date data = formato.parse("23/11/2031");

// Avaliacao avaliacao = new Avaliacao("Avalicao ADS 3P", perguntaList,
// turmaList, data);
// entityManager.persist(avaliacao);

// Resposta respostaUm = new Resposta("Foi bom !", (long) 100, perguntaUm,
// avaliacao);
// entityManager.persist(respostaUm);
// Resposta persistidoResposta =
// respostaRepository.findByTexto(respostaUm.getTexto(), pageable).getContent()
// .get(0);

// // afirmacoes da perguntaUm
// Assert.assertNotNull(persistidoPergunta);
// Assert.assertEquals(persistidoPergunta.getTexto(), perguntaUm.getTexto());
// Assert.assertEquals(persistidoPergunta.getTipo(), perguntaUm.getTipo());
// Assert.assertEquals(persistidoPergunta.getAtivo(), perguntaUm.getAtivo());

// // afirmacoes da respostaUm
// Assert.assertNotNull(persistidoResposta);
// Assert.assertEquals(persistidoResposta.getTexto(), respostaUm.getTexto());
// Assert.assertEquals(persistidoResposta.getPergunta(),
// respostaUm.getPergunta());

// entityManager.flush();
// entityManager.clear();

// }
// }

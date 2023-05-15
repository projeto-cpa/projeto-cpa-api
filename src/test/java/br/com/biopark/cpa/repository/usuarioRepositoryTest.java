package br.com.biopark.cpa.repository;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import br.com.biopark.cpa.models.Cargo;
import br.com.biopark.cpa.models.Usuario;

@RunWith(SpringRunner.class)
@DataJpaTest
public class usuarioRepositoryTest {

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CargoRepository cargoRepository;

    @Test
    public void testePersistenciaDisciplinas() throws Exception {

        Cargo aluno = new Cargo("aluno", "aluno", false);

        entityManager.persist(aluno);
        entityManager.flush();
        entityManager.clear();

        Pageable pageable = PageRequest.of(0, 5);
        Cargo persistidoCargo = cargoRepository.findByNome(aluno.getNome(), pageable).getContent().get(0);

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date data = formato.parse("05/07/2002");

        Usuario usuarioUm = new Usuario("Elielder", "não sei o sobrenome", "admin",
                aluno, data,
                "elielder@gmail.com");

        entityManager.persist(usuarioUm);
        entityManager.flush();
        entityManager.clear();

        Usuario persistidoUsuario = usuarioRepository.findByNome(usuarioUm.getNome());

        // afirmacoes
        Assert.assertEquals(persistidoCargo.getNome(), aluno.getNome());
        Assert.assertEquals(persistidoCargo.getDescricao(), aluno.getDescricao());
        Assert.assertEquals(persistidoCargo.getAtivo(), aluno.getAtivo());

        Assert.assertEquals(persistidoUsuario.getNome(), usuarioUm.getNome());
        Assert.assertEquals(persistidoUsuario.getSobrenome(), usuarioUm.getSobrenome());
        Assert.assertEquals(persistidoUsuario.getCargo(), usuarioUm.getCargo());

    }
}

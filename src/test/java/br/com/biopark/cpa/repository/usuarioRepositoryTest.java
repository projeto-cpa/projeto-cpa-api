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

        Cargo professor = new Cargo("Professor", "Professor", false);

        entityManager.persist(professor);
        entityManager.flush();
        entityManager.clear();

        Pageable pageable = PageRequest.of(0, 5);
        Cargo persistidoCargo = cargoRepository.findByNome(professor.getNome(), pageable).getContent().get(0);

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date data = formato.parse("05/07/2002");

        Usuario Matheus = new Usuario("Matheus", "Schuch", "admin", professor, data);

        entityManager.persist(Matheus);
        entityManager.flush();
        entityManager.clear();

        Usuario persistidoUsuario = usuarioRepository.findByNome(Matheus.getNome(), pageable).getContent()
                .get(0);

        // afirmacoes
        Assert.assertEquals(persistidoCargo.getNome(), professor.getNome());
        Assert.assertEquals(persistidoCargo.getDescricao(), professor.getDescricao());
        Assert.assertEquals(persistidoCargo.getAtivo(), professor.getAtivo());

        Assert.assertEquals(persistidoUsuario.getNome(), Matheus.getNome());
        Assert.assertEquals(persistidoUsuario.getSobrenome(), Matheus.getSobrenome());
        Assert.assertEquals(persistidoUsuario.getCargo(), Matheus.getCargo());

    }
}

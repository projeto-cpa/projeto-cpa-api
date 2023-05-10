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

import br.com.biopark.cpa.models.Cargo;

@RunWith(SpringRunner.class)
@DataJpaTest
public class cargoRepositoryTests {

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CargoRepository cargoRepository;

    @Test
    public void testePersistenciaCargos() throws Exception {
        Cargo professor = new Cargo("Professor de testes", "Professor de testes", false);
        entityManager.persist(professor);

        Pageable pageable = PageRequest.of(0, 5);
        Cargo persistido = cargoRepository.findByNome(professor.getNome(), pageable).getContent().get(0);

        entityManager.flush();
        entityManager.clear();

        Assert.assertEquals(persistido.getNome(), professor.getNome());
        Assert.assertEquals(persistido.getDescricao(), professor.getDescricao());
        Assert.assertEquals(persistido.getAtivo(), professor.getAtivo());
    }
}

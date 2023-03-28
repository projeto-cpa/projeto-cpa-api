package br.com.biopark.cpa.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.biopark.cpa.models.Cargo;

@RunWith(SpringRunner.class)
@DataJpaTest
public class cargoRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CargoRepository cargoRepository;

    @Test
    public void testePersistenciaCargos() throws Exception {

        // cria, persiste e limpa
        Cargo professor = new Cargo("Professor", "Professor", false);
        
        entityManager.persist(professor);
        entityManager.flush();
        entityManager.clear();

        Cargo persistido = cargoRepository.findByNome(professor.getNome());

        // afirmacoes
        Assert.assertEquals(persistido.getNome(), professor.getNome());
        Assert.assertEquals(persistido.getDescricao(), professor.getDescricao());
        Assert.assertEquals(persistido.getAtivo(), professor.getAtivo());
    }
}

package br.com.biopark.cpa.repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
import br.com.biopark.cpa.models.Usuario;

@RunWith(SpringRunner.class)
@DataJpaTest
public class usuarioRepositoryTests {

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void testePersistenciaUsuario() throws Exception {

        Cargo aluno = new Cargo("aluno", "sla", true);

        entityManager.persist(aluno);
        entityManager.flush();
        entityManager.clear();

        String myDate = "2014/10/29 18:10:45";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = sdf.parse(myDate);
        long millis = date.getTime();
        Date data = new Date(millis);

        // cria, persiste e limpa
        Usuario usuario1 = new Usuario("Fulaninho", "Fulanao", "123456", aluno, data);

        entityManager.persist(usuario1);
        entityManager.flush();
        entityManager.clear();

        Pageable pageable = PageRequest.of(1, 5);

        Usuario persistido = (Usuario) usuarioRepository.findByNome(usuario1.getNome(), pageable);

        // afirmacoes
        Assert.assertEquals(persistido.getNome(), usuario1.getNome());
    }
}

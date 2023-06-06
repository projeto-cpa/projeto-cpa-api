package br.com.biopark.cpa.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import br.com.biopark.cpa.models.Resposta;

public interface RespostaRepository extends JpaRepository<Resposta, Long> {

    public Resposta findById(long id);

    public Page<Resposta> findByTexto(String texto, Pageable paginacao);

}

package br.com.biopark.cpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.biopark.cpa.models.Resposta;

public interface RespostaRepository extends JpaRepository<Resposta, Long> {

    public Resposta findById(long id);

    public Resposta findByTexto(String texto);

}

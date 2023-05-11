package br.com.biopark.cpa.service;

import br.com.biopark.cpa.models.Avaliacao;
import br.com.biopark.cpa.repository.AvaliacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;

    public AvaliacaoService(AvaliacaoRepository avaliacaoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
    }

    @Transactional
    public Avaliacao cadastrar(Avaliacao avaliacao) {

        try {
            return avaliacaoRepository.save(avaliacao);
        } catch (RuntimeException e) {
            throw new RuntimeException("Não foi possível cadastrar a avaliação: " + e.getMessage());
        }
    }
}

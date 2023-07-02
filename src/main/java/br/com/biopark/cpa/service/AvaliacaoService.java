package br.com.biopark.cpa.service;

import br.com.biopark.cpa.models.Avaliacao;
import br.com.biopark.cpa.repository.AvaliacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
            throw new RuntimeException("Não foi possível cadastrar a avaliação da Cpa: " + e.getMessage());
        }
    }

    public Page<Avaliacao> listar(Pageable pageable) {
        return avaliacaoRepository.findAll(pageable);
    }

    public Avaliacao buscarPorId(long avaliacaoId) {

        Optional<Avaliacao> avaliacao = avaliacaoRepository.findById(avaliacaoId);

        if (avaliacao.isPresent())
            return avaliacao.get();
        else
            throw new EntityNotFoundException("Avaliação não encontrada");

    }
}

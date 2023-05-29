package br.com.biopark.cpa.service;

import br.com.biopark.cpa.config.validation.ValidacaoException;
import br.com.biopark.cpa.models.Avaliacao;
import br.com.biopark.cpa.models.Pergunta;
import br.com.biopark.cpa.models.Resposta;
import br.com.biopark.cpa.models.Usuario;
import br.com.biopark.cpa.repository.AvaliacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;
    private final UsuarioService usuarioService;

    public AvaliacaoService(AvaliacaoRepository avaliacaoRepository, UsuarioService usuarioService) {
        this.avaliacaoRepository = avaliacaoRepository;
        this.usuarioService = usuarioService;
    }

    @Transactional
    public Avaliacao cadastrar(Avaliacao avaliacao) {

        try {
            return avaliacaoRepository.save(avaliacao);
        } catch (RuntimeException e) {
            throw new RuntimeException("Não foi possível cadastrar a avaliação: " + e.getMessage());
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

    /**
     * Função que verifica a quantidade de respostas respondida por aquele usuario para aquela valiação e salva
     * a avaliação na lista de avaliações respondidas pelo usuario
     *
     * @param avaliacao
     * @param usuario
     * @return
     * @throws ValidacaoException
     */
    public Avaliacao finalizarAvaliacao(Avaliacao avaliacao, Usuario usuario) throws ValidacaoException {
        List<Pergunta> perguntaRespondidas = new ArrayList<>();

        for (Resposta resposta : usuario.getRespostaList()) {
            if (resposta.getAvaliacao().equals(avaliacao)) {
                perguntaRespondidas.add(resposta.getPergunta());
            }
        }

        if (avaliacao.getPerguntaList().size() == perguntaRespondidas.size()) {
            avaliacao.getUsuarioListRespondentes().add(usuario);
            usuario.getAvaliacoesRespondidas().add(avaliacao);
            usuarioService.atualizar(usuario.getId());
            avaliacao = avaliacaoRepository.save(avaliacao);
        } else {
            throw new ValidacaoException("Ainda há perguntas a serem respondidas na avaliação");
        }

        return avaliacao;
    }
}

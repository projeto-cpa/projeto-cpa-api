package br.com.biopark.cpa.service;

import br.com.biopark.cpa.config.validation.ValidacaoException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import br.com.biopark.cpa.models.Resposta;
import br.com.biopark.cpa.repository.RespostaRepository;

@Service
public class RespostaService {

    @Autowired
    private RespostaRepository respostaRepository;

    @Transactional
    public Resposta cadastrar(Resposta resposta) throws ValidacaoException {

        Resposta respostaCadastrada = new Resposta();

        perguntaEstaNaAvaliacao(resposta);

        try {
            respostaCadastrada = respostaRepository.save(resposta);
        } catch (Exception e) {
            System.out.println("Não foi possivel cadastrar a resposta: " + e.getStackTrace());
        }

        return respostaCadastrada;
    }

    private void perguntaEstaNaAvaliacao(Resposta resposta) throws ValidacaoException {
        if (!resposta.getAvaliacao().getPerguntaList().contains(resposta.getPergunta())) {
            throw new ValidacaoException("Pergunta que está sendo respondida não está nesta avaliação",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Iterable<Resposta> listarResposta() {
        return respostaRepository.findAll();
    }

}
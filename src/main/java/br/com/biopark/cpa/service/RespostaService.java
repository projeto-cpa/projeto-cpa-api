package br.com.biopark.cpa.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.biopark.cpa.models.Resposta;
import br.com.biopark.cpa.repository.RespostaRepository;

@Service
public class RespostaService {

    @Autowired
    private RespostaRepository respostaRepository;

    @Transactional
    public Resposta cadastrar(Resposta resposta) {

        Resposta respostaCadastrada = new Resposta();

        perguntaEstaNaAvaliacao(resposta);

        try {
            respostaCadastrada = respostaRepository.save(resposta);
        } catch (Exception e) {
            System.out.println("Não foi possivel cadastrar a resposta: " + e.getStackTrace());
        }

        return respostaCadastrada;
    }

    private void perguntaEstaNaAvaliacao(Resposta resposta) {
        if (resposta.getAvaliacao().getPerguntaList().contains(resposta.getPergunta())) {

        }
    }

    public Iterable<Resposta> listarResposta() {
        return respostaRepository.findAll();
    }

}
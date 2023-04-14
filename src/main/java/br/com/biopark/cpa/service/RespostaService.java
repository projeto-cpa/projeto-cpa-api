package br.com.biopark.cpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.biopark.cpa.models.Resposta;
import br.com.biopark.cpa.repository.RespostaRepository;

@Service
public class RespostaService {

    @Autowired
    private RespostaRepository respostaRepository;

    public Resposta cadastrar(Resposta resposta) {

        Resposta respostaCadastrada = new Resposta();

        try {
            respostaCadastrada = respostaRepository.save(resposta);
        } catch (Exception e) {
            System.out.println("Não foi possivel cadastrar a resposta: " + e.getStackTrace());
        }

        return respostaCadastrada;
    }

    // implemente a listagem de cargos
    public Iterable<Resposta> listarResposta() {
        return respostaRepository.findAll();
    }

}
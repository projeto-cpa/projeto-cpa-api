package br.com.biopark.cpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import br.com.biopark.cpa.models.Pergunta;
import br.com.biopark.cpa.repository.PerguntaRepository;

@Service
public class PerguntaService {

    @Autowired
    private PerguntaRepository perguntaRepository;

    public Pergunta cadastrar(Pergunta pergunta) {

        Pergunta perguntaCadastrada = new Pergunta();

        try {
            perguntaCadastrada = perguntaRepository.save(pergunta);
        } catch (Exception e) {
            System.out.println("Não foi possivel cadastrar a pergunta: " + e.getStackTrace());
        }

        return perguntaCadastrada;
    }

    public Page<Pergunta> listarPergunta(Pageable pergunta) {
        return perguntaRepository.findAll(pergunta);
    }

}
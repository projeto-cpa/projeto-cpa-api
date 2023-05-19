package br.com.biopark.cpa.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import br.com.biopark.cpa.models.Pergunta;
import br.com.biopark.cpa.repository.PerguntaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PerguntaService {

    @Autowired
    private PerguntaRepository perguntaRepository;

    @Transactional
    public Pergunta cadastrar(Pergunta pergunta) {

        Pergunta perguntaCadastrada = new Pergunta();

        try {
            perguntaCadastrada = perguntaRepository.save(pergunta);
        } catch (Exception e) {
            System.out.println("Não foi possivel cadastrar a pergunta: " + e.getStackTrace());
        }

        return perguntaCadastrada;
    }

    public Iterable<Pergunta> listarPergunta() {
        return perguntaRepository.findAll();
    }

    public Page<Pergunta> buscarPorEixo(String nomeEixo, Pageable pageable) {
        try {
            return perguntaRepository.findByEixoNome(nomeEixo, pageable);
        } catch (RuntimeException e) {
            throw new RuntimeException("Nenhuma pergunta encontrada");
        }
    }

    public Page<Pergunta> listar(Pageable pageable) {
        try {
            return perguntaRepository.findAll(pageable);
        } catch (RuntimeException e) {
            throw new RuntimeException("Nenhuma pergunta encontrada");
        }
    }

    public List<Pergunta> buscaPerguntasPorIds(List<Long> listaPerguntas) {

        List<Pergunta> perguntaList = perguntaRepository.findAllByIdIn(listaPerguntas);

        return perguntaList;
    }

    public Pergunta buscarPorId(Long idPergunta) {

        Optional<Pergunta> pergunta = perguntaRepository.findById(idPergunta);

        return pergunta.orElse(null);

    }
}
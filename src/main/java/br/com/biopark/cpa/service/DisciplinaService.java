package br.com.biopark.cpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.biopark.cpa.models.Disciplina;
import br.com.biopark.cpa.repository.DisciplinaRepository;
import jakarta.transaction.Transactional;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Transactional
    public Disciplina cadastrar(Disciplina disciplina) {

        Disciplina disciplinaCadastrado = new Disciplina();

        try {
            disciplinaCadastrado = disciplinaRepository.save(disciplina);
        } catch (Exception e) {
            System.out.println("Não foi possivel cadastrar o disciplina: " + e.getStackTrace());
        }

        return disciplinaCadastrado;
    }
    
    // implemente a listagem de disciplinas
    public Iterable<Disciplina> listarDisciplinas() {
        return disciplinaRepository.findAll();
    }
    
}

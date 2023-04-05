package br.com.biopark.cpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.biopark.cpa.models.Turma;
import br.com.biopark.cpa.repository.TurmaRepository;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    public Turma cadastrar(Turma turma) {

        Turma turmaCadastrado = new Turma();

        try {
            turmaCadastrado = turmaRepository.save(turma);
        } catch (Exception e) {
            System.out.println("Não foi possivel cadastrar o cargo: " + e.getStackTrace());
        }

        return turmaCadastrado;
    }
    
    // implemente a listagem de cargos
    public Iterable<Turma> listarTurmas() {
        return turmaRepository.findAll();
    }
    
}

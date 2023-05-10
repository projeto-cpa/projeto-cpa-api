package br.com.biopark.cpa.service;

import br.com.biopark.cpa.models.Turma;
import br.com.biopark.cpa.repository.TurmaRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TurmaService {

    TurmaRepository turmaRepository;

    @Transactional
    public Turma cadastrar(Turma turma) {
        try{
            return turmaRepository.save(turma);
        } catch (RuntimeException e)  {
            throw new RuntimeException("Erro ao cadastrar turma: " + e.getMessage());
        }
    }
}

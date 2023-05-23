package br.com.biopark.cpa.service;

import br.com.biopark.cpa.models.Turma;
import br.com.biopark.cpa.repository.TurmaRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    @Transactional
    public Turma cadastrar(Turma turma) {

        Turma turmaCadastrado = new Turma();

        try {
            turmaCadastrado = turmaRepository.save(turma);
        } catch (Exception e) {
            System.out.println("Não foi possivel cadastrar o disciplina: " + e.getStackTrace());
        }

        return turmaCadastrado;
    }

    public Turma atualizar(Long id, String nome, String descricao, Boolean ativo) {
        Turma turma = turmaRepository.findById(id).get();
        turma.setDataAtualizacao(new Date());
        turma.setNome(nome);
        turma.setDescricao(descricao);
        turma.setAtivo(ativo);
        turmaRepository.save(turma);
        return turma;
    }

    public Page<Turma> listar(Pageable turmas) {
        return turmaRepository.findAll(turmas);
    }

    public Turma excluirTurma(Long id) {
        Turma turma = turmaRepository.findById(id).get();
        turmaRepository.delete(turma);
        return turma;
    }

    public Turma ativarDesativarTurma(Long id) {
        Turma turma = turmaRepository.findById(id).get();
        Boolean ativo = turma.getAtivo().equals(true) ? false : true;
        turma.setAtivo(ativo);
        turmaRepository.save(turma);
        return turma;
    }

    public List<Turma> buscarTurmasPorIds(List<Long> listaTurmas) {

        List<Turma> turmaList = turmaRepository.findAllByIdIn(listaTurmas);

        return turmaList;
    }

    public Page<Turma> buscaPorNome(String nomeTurma, Pageable turmas) {
        return turmaRepository.findByNome(nomeTurma, turmas);
    }
}

package br.com.biopark.cpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.biopark.cpa.models.Curso;
import br.com.biopark.cpa.repository.CursoRepository;

import java.util.Date;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public Curso cadastrar(Curso curso) {

        Curso cursoCadastrado = new Curso();

        try {
            cursoCadastrado = cursoRepository.save(curso);
        } catch (Exception e) {
            System.out.println("Não foi possivel cadastrar o curso: " + e.getStackTrace());
        }

        return cursoCadastrado;
    }

    public Iterable<Curso> listarCursos() {
        return cursoRepository.findAll();
    }

    public Page<Curso> listar(Pageable cursos) {
        return cursoRepository.findAll(cursos);
    }

    public Page<Curso> buscaPorNome(String nomeCurso, Pageable curso) {
        return cursoRepository.findByNome(nomeCurso, curso);
    }

    public Curso buscarCurso(Long idCurso) {
        Optional<Curso> curso = cursoRepository.findById(idCurso);

        if (curso.isPresent())
            return curso.get();

        return null;
    }

    public Curso excluirCurso(Long id) {
        Curso curso = cursoRepository.findById(id).get();
        cursoRepository.delete(curso);
        return curso;
    }

    public Curso ativarDesativarCurso(Long id) {
        Curso curso = cursoRepository.findById(id).get();
        Boolean ativo = curso.getAtivo().equals(true) ? false : true;
        curso.setAtivo(ativo);
        cursoRepository.save(curso);
        return curso;
    }

    public Curso atualizar(Long id, String nome, String descricao, Boolean ativo) {
        Curso curso = cursoRepository.findById(id).get();
        curso.setDataAtualizacao(new Date());
        curso.setNome(nome);
        curso.setDescricao(descricao);
        curso.setAtivo(ativo);
        cursoRepository.save(curso);
        return curso;
    }

}

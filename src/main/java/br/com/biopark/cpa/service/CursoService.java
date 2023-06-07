package br.com.biopark.cpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.biopark.cpa.models.Cargo;
import br.com.biopark.cpa.models.Curso;
import br.com.biopark.cpa.repository.CursoRepository;

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

}

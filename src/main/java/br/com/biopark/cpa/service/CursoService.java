package br.com.biopark.cpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.biopark.cpa.models.Curso;
import br.com.biopark.cpa.repository.CursoRepository;

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
    
    // implemente a listagem de cursos
    public Iterable<Curso> listarCursos() {
        return cursoRepository.findAll();
    }
    
}

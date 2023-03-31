package br.com.biopark.cpa.controller;

import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.biopark.cpa.controller.dto.CursoDTO;
import br.com.biopark.cpa.controller.form.CursoForm;
import br.com.biopark.cpa.models.Curso;
import br.com.biopark.cpa.repository.DisciplinaRepository;
import br.com.biopark.cpa.service.CursoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/curso")
@CrossOrigin(origins = { "http://localhost:8080", "http://localhost:3005" })
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<CursoDTO> cadastrar(@RequestBody @Valid CursoForm form, UriComponentsBuilder uriBuilder)
            throws Exception {

        Curso curso = form.converter(disciplinaRepository);
        cursoService.cadastrar(curso);

        URI uri = uriBuilder.path("/curso/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(uri).body(new CursoDTO(curso));
    }
    
    @GetMapping
    public Iterable<Curso> listarCursos() {
        return cursoService.listarCursos();
    }
    
}
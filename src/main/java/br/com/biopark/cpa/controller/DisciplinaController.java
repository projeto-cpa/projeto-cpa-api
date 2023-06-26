package br.com.biopark.cpa.controller;

import java.net.URI;

import br.com.biopark.cpa.service.CursoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.biopark.cpa.controller.dto.DisciplinaDTO;
import br.com.biopark.cpa.controller.form.DisciplinaForm;
import br.com.biopark.cpa.models.Curso;
import br.com.biopark.cpa.models.Disciplina;
import br.com.biopark.cpa.repository.CursoRepository;
import br.com.biopark.cpa.service.DisciplinaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/disciplina")
@Transactional
@SecurityRequirement(name = "bearer-key")
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;

    @Autowired
    private CursoService cursoService;

    @PostMapping
    @CrossOrigin(origins = { "http://localhost:8080", "http://localhost:3005" })
    public ResponseEntity<DisciplinaDTO> cadastrar(@RequestBody @Valid DisciplinaForm form, UriComponentsBuilder uriBuilder) {

        Curso curso = cursoService.buscarCurso(form.getIdCurso());

        Disciplina disciplina = new Disciplina(form.getAtivo(), form.getNome(), form.getDescricao(), curso);
        disciplina = disciplinaService.cadastrar(disciplina);
        URI uri = uriBuilder.path("disciplina/{id}").buildAndExpand(disciplina.getId()).toUri();

        return ResponseEntity.created(uri).body(new DisciplinaDTO(disciplina));

    }

    @GetMapping
    @CrossOrigin(origins = { "http://localhost:8080", "http://localhost:3005" })
    public Iterable<Disciplina> listarDisciplinas() {
        return disciplinaService.listarDisciplinas();
    }
}

package br.com.biopark.cpa.controller;

import br.com.biopark.cpa.controller.dto.TurmaDTO;
import br.com.biopark.cpa.controller.form.TurmaForm;
import br.com.biopark.cpa.models.Turma;
import br.com.biopark.cpa.service.CursoService;
import br.com.biopark.cpa.service.TurmaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/turma")
public class TurmaController {


    private final TurmaService turmaService;

    private final CursoService cursoService;

    public TurmaController(TurmaService turmaService, CursoService cursoService) {
        this.turmaService = turmaService;
        this.cursoService = cursoService;
    }

    @PostMapping
    private ResponseEntity<TurmaDTO> cadastrar(@RequestBody @Valid TurmaForm form, UriComponentsBuilder uriBuilder) {

        Turma turma = form.converter(cursoService);

        turma = turmaService.cadastrar(turma);

        URI uri = uriBuilder.path("/eixo/{id}").buildAndExpand(turma.getId()).toUri();

        return ResponseEntity.created(uri).body(new TurmaDTO(turma));
    }
}

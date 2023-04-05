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

import br.com.biopark.cpa.controller.dto.TurmaDTO;
import br.com.biopark.cpa.controller.form.TurmaForm;
import br.com.biopark.cpa.models.Turma;

import br.com.biopark.cpa.service.TurmaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RequestMapping
@RestController
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    @Transactional
    @PostMapping("/cadastro/turmas")
    @CrossOrigin(origins = { "http://localhost:3306", "http://localhost:3005" })
    public ResponseEntity<TurmaDTO> cadastrar(@RequestBody @Valid TurmaForm form,
            UriComponentsBuilder uriBuilder) {
        Turma turma = new Turma(form.getNome(), form.getDescricao(), form.getPeriodo(), form.getCurso(), form.getAtivo());
        turma = turmaService.cadastrar(turma);
        URI uri = uriBuilder.path("turma/{id}").buildAndExpand(turma.getId()).toUri();
        return ResponseEntity.created(uri).body(new TurmaDTO(turma));
    }

    @GetMapping("/listagem/turmas")
    @CrossOrigin(origins = { "http://localhost:3306", "http://localhost:3005" })
    public Iterable<Turma> listarTurmas() {
        return turmaService.listarTurmas();
    }
}

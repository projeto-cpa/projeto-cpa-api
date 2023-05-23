package br.com.biopark.cpa.controller;

import br.com.biopark.cpa.controller.dto.TurmaDTO;
import br.com.biopark.cpa.controller.form.TurmaForm;
import br.com.biopark.cpa.controller.form.alteracao.AlterarTurmaForm;
import br.com.biopark.cpa.controller.form.ativacao.AtivarTurmaForm;
import br.com.biopark.cpa.controller.form.exclusao.excluirTurmaForm;
import br.com.biopark.cpa.models.Curso;
import br.com.biopark.cpa.models.Turma;
import br.com.biopark.cpa.service.CursoService;
import br.com.biopark.cpa.service.TurmaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/turma")
@CrossOrigin(origins = { "http://localhost:8080", "http://localhost:3005" })
@Transactional
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    @Autowired
    private CursoService cursoService;

    @PostMapping
    public ResponseEntity<TurmaDTO> cadastrar(@RequestBody @Valid TurmaForm form, UriComponentsBuilder uriBuilder) {
        Curso curso = cursoService.buscarCurso(form.getIdCurso());

        Turma turma = new Turma(form.getNome(), form.getDescricao(), curso);
        turma = turmaService.cadastrar(turma);
        URI uri = uriBuilder.path("turma/{id}").buildAndExpand(turma.getId()).toUri();
        return ResponseEntity.created(uri).body(new TurmaDTO(turma));
    }

    @PutMapping
    public ResponseEntity<TurmaDTO> atualizar(@RequestBody @Valid AlterarTurmaForm form,UriComponentsBuilder uriBuilder) {
        Turma turma = turmaService.atualizar(form.getIdTurma(), form.getNome(), form.getDescricao(), form.getAtivo());
        URI uri = uriBuilder.path("turma/{id}").buildAndExpand(turma.getId()).toUri();
        return ResponseEntity.created(uri).body(new TurmaDTO(turma));
    }

    @GetMapping
    public Page<TurmaDTO> listar(@RequestParam(required = false) String nomeTurma, @RequestParam int pagina, @RequestParam int qtd) {
        Pageable paginacao = PageRequest.of(pagina, qtd);
        if (nomeTurma == null) {
            Page<Turma> turmas = turmaService.listar(paginacao);
            return TurmaDTO.converter(turmas);
        } else {
            Page<Turma> turmas = turmaService.buscaPorNome(nomeTurma, paginacao);
            return TurmaDTO.converter(turmas);
        }
    }

    @DeleteMapping
    public ResponseEntity<TurmaDTO> excluirTurma(@RequestBody @Valid excluirTurmaForm form, UriComponentsBuilder uriBuilder) {
        Turma turma = turmaService.excluirTurma(form.getIdTurma());
        URI uri = uriBuilder.path("turma/{id}").buildAndExpand(turma.getId()).toUri();
        return ResponseEntity.created(uri).body(new TurmaDTO(turma));
    }

    @PutMapping("/ativacao")
    public ResponseEntity<TurmaDTO> ativarDesativarTurma(@RequestBody @Valid AtivarTurmaForm form, UriComponentsBuilder uriBuilder) {
        Turma turma = turmaService.ativarDesativarTurma(form.getIdTurma());
        URI uri = uriBuilder.path("turma/{id}").buildAndExpand(turma.getId()).toUri();
        return ResponseEntity.created(uri).body(new TurmaDTO(turma));
    }

}

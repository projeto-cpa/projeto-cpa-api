package br.com.biopark.cpa.controller;

import java.net.URI;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.biopark.cpa.controller.dto.CursoDTO;
import br.com.biopark.cpa.controller.form.CursoForm;
import br.com.biopark.cpa.controller.form.alteracao.AlterarCursoForm;
import br.com.biopark.cpa.controller.form.ativacao.AtivarCursoForm;
import br.com.biopark.cpa.controller.form.exclusao.excluirCursoForm;
import br.com.biopark.cpa.models.Curso;
import br.com.biopark.cpa.service.CursoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/curso")
@Transactional
@SecurityRequirement(name = "bearer-key")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping
    public ResponseEntity<CursoDTO> cadastrar(@RequestBody @Valid CursoForm form, UriComponentsBuilder uriBuilder) throws Exception {
        Curso curso = new Curso(form.getAtivo(), form.getNome(), form.getDescricao());
        curso = cursoService.cadastrar(curso);
        URI uri = uriBuilder.path("curso/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(uri).body(new CursoDTO(curso));
    }

    @PutMapping
    public ResponseEntity<CursoDTO> atualizar(@RequestBody @Valid AlterarCursoForm form, UriComponentsBuilder uriBuilder) {
        Curso curso = cursoService.atualizar(form.getIdCurso(), form.getNome(), form.getDescricao(), form.getAtivo());
        URI uri = uriBuilder.path("curso/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(uri).body(new CursoDTO(curso));
    }

    @GetMapping
    public Page<CursoDTO> listar(@RequestParam(required = false) String nomeCurso, @RequestParam int pagina, @RequestParam int qtd) {
        Pageable paginacao = PageRequest.of(pagina, qtd);
        if (nomeCurso == null) {
            Page<Curso> cursos = cursoService.listar(paginacao);
            return CursoDTO.converter(cursos);
        } else {
            Page<Curso> cursos = cursoService.buscaPorNome(nomeCurso, paginacao);
            return CursoDTO.converter(cursos);
        }

    }

    @DeleteMapping
    public ResponseEntity<CursoDTO> excluirCurso(@RequestBody @Valid excluirCursoForm form, UriComponentsBuilder uriBuilder) {
        Curso curso = cursoService.excluirCurso(form.getIdCurso());
        URI uri = uriBuilder.path("curso/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(uri).body(new CursoDTO(curso));
    }

    @PutMapping("/ativacao")
    public ResponseEntity<CursoDTO> ativarDesativarCurso(@RequestBody @Valid AtivarCursoForm form, UriComponentsBuilder uriBuilder) {
        Curso curso = cursoService.ativarDesativarCurso(form.getIdCurso());
        URI uri = uriBuilder.path("curso/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(uri).body(new CursoDTO(curso));
    }

}
package br.com.biopark.cpa.controller.form;

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

import br.com.biopark.cpa.controller.dto.PerguntaDTO;
import br.com.biopark.cpa.controller.form.PerguntaForm;
import br.com.biopark.cpa.models.Pergunta;

import br.com.biopark.cpa.service.PerguntaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RequestMapping
@RestController
public class PerguntaController {

    @Autowired
    private PerguntaService perguntaService;

    @Transactional
    @PostMapping("/cadastro/perguntas")
    @CrossOrigin(origins = { "http://localhost:8080", "http://localhost:3005" })
    public ResponseEntity<PerguntaDTO> cadastrar(@RequestBody @Valid CargoForm form, UriComponentsBuilder uriBuilder) {
        Pergunta pergunta = new Pergunta(form.getDescricao(), form.getAtivo());
        pergunta = perguntaService.cadastrar(pergunta);
        URI uri = uriBuilder.path("pergunta/{id}").buildAndExpand(pergunta.getId()).toUri();
        return ResponseEntity.created(uri).body(new PerguntaDTO(pergunta));
    }

    @GetMapping("/listagem/perguntas")
    @CrossOrigin(origins = { "http://localhost:8080", "http://localhost:3005" })
    public Iterable<Pergunta> listarPergunta() {
        return perguntaService.listarPergunta();
    }
}
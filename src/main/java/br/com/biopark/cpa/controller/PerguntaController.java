package br.com.biopark.cpa.controller;

import java.net.URI;

import br.com.biopark.cpa.service.EixoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.biopark.cpa.controller.dto.PerguntaDTO;
import br.com.biopark.cpa.controller.form.PerguntaForm;
import br.com.biopark.cpa.controller.form.ativacao.AtivarPerguntaForm;
import br.com.biopark.cpa.controller.form.exclusao.excluirPerguntaForm;
import br.com.biopark.cpa.models.Pergunta;
import br.com.biopark.cpa.service.PerguntaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pergunta")
@SecurityRequirement(name = "bearer-key")
public class PerguntaController {

    private final PerguntaService perguntaService;

    private final EixoService eixoService;

    public PerguntaController(PerguntaService perguntaService, EixoService eixoService) {
        this.perguntaService = perguntaService;
        this.eixoService = eixoService;
    }

    /**
     * Cadastra pergunta
     *
     * @param form
     * @param uriBuilder
     * @return
     */
    @PostMapping
    public ResponseEntity<PerguntaDTO> cadastrar(@RequestBody @Valid PerguntaForm form,
            UriComponentsBuilder uriBuilder) {
        Pergunta pergunta = form.converter(form, eixoService);
        pergunta = perguntaService.cadastrar(pergunta);
        URI uri = uriBuilder.path("pergunta/{id}").buildAndExpand(pergunta.getId()).toUri();
        return ResponseEntity.created(uri).body(new PerguntaDTO(pergunta));
    }

    /**
     * Lista perguntas por eixo
     *
     * @param nomeEixo
     * @param pagina
     * @param qtd
     * @return
     */
    @GetMapping
    public Page<PerguntaDTO> listar(@RequestParam(required = false) String textoPergunta, @RequestParam int pagina,
                                    @RequestParam int qtd) {

        Pageable pageable = PageRequest.of(pagina, qtd);

        Page<Pergunta> perguntas;
        if (textoPergunta != null) {
            perguntas = perguntaService.buscarPorPergunta(textoPergunta, pageable);
        } else {
            perguntas = perguntaService.listar(pageable);
        }
        return PerguntaDTO.converter(perguntas);
    }

    @PutMapping
    public ResponseEntity<PerguntaDTO> atualizar(@RequestBody @Valid PerguntaForm form,
            UriComponentsBuilder uriBuilder) {
        Pergunta pergunta = perguntaService.atualizar(form.getIdPergunta(), form.getTexto(), form.getTipo(),
                form.getEixoId(), form.getAtivo());
        URI uri = uriBuilder.path("pergunta/{id}").buildAndExpand(pergunta.getId()).toUri();
        return ResponseEntity.created(uri).body(new PerguntaDTO(pergunta));
    }

    @DeleteMapping
    public ResponseEntity<PerguntaDTO> excluirPergunta(@RequestBody @Valid excluirPerguntaForm form,
            UriComponentsBuilder uriBuilder) {
        Pergunta pergunta = perguntaService.excluirPergunta(form.getIdPergunta());
        URI uri = uriBuilder.path("pergunta/{id}").buildAndExpand(pergunta.getId()).toUri();
        return ResponseEntity.created(uri).body(new PerguntaDTO(pergunta));
    }

    @PutMapping("/ativacao")
    public ResponseEntity<PerguntaDTO> ativarDesativarPergunta(@RequestBody @Valid AtivarPerguntaForm form,
            UriComponentsBuilder uriBuilder) {
        Pergunta pergunta = perguntaService.ativarDesativarPergunta(form.getIdPergunta());
        URI uri = uriBuilder.path("pergunta/{id}").buildAndExpand(pergunta.getId()).toUri();
        return ResponseEntity.created(uri).body(new PerguntaDTO(pergunta));
    }
}
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
    public Page<PerguntaDTO> listar(@RequestParam(required = false) String nomeEixo, @RequestParam int pagina,
                                    @RequestParam int qtd) {

        Pageable pageable = PageRequest.of(pagina, qtd);

        Page<Pergunta> perguntas;
        if (nomeEixo != null) {
            perguntas = perguntaService.buscarPorEixo(nomeEixo, pageable);
        } else {
            perguntas = perguntaService.listar(pageable);
        }
        return PerguntaDTO.converter(perguntas);
    }
}
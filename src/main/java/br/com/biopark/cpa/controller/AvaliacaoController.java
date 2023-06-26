package br.com.biopark.cpa.controller;

import br.com.biopark.cpa.controller.dto.AvaliacaoDTO;
import br.com.biopark.cpa.controller.form.AvaliacaoForm;
import br.com.biopark.cpa.controller.form.AvaliacaoRespostaForm;
import br.com.biopark.cpa.models.Avaliacao;
import br.com.biopark.cpa.service.AvaliacaoService;
import br.com.biopark.cpa.service.PerguntaService;
import br.com.biopark.cpa.service.TurmaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/avaliacao")
@SecurityRequirement(name = "bearer-key")
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;

    private final PerguntaService perguntaService;

    private final TurmaService turmaService;

    public AvaliacaoController(AvaliacaoService avaliacaoService, PerguntaService perguntaService, TurmaService turmaService) {
        this.avaliacaoService = avaliacaoService;
        this.perguntaService = perguntaService;
        this.turmaService = turmaService;
    }

    @PostMapping
    public ResponseEntity<AvaliacaoDTO> cadastrar(@RequestBody @Valid AvaliacaoForm form, UriComponentsBuilder uriBuilder) {

        Avaliacao avaliacao = form.converter(perguntaService, turmaService);

        avaliacao = avaliacaoService.cadastrar(avaliacao);

        URI uri = uriBuilder.path("/avaliacao/{id}").buildAndExpand(avaliacao.getId()).toUri();

        return ResponseEntity.created(uri).body(new AvaliacaoDTO(avaliacao));
    }

    @GetMapping
    public Page<AvaliacaoDTO> listar(@RequestParam int page, @RequestParam int size, @RequestParam(required = false) String sort, Pageable pageable) {

        Page<Avaliacao> avaliacaoPage = avaliacaoService.listar(pageable);

        return AvaliacaoDTO.converter(avaliacaoPage);
    }

    public AvaliacaoDTO responderAvaliacao(@RequestBody AvaliacaoRespostaForm form) {


        return null;
    }
}

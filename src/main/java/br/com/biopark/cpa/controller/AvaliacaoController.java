package br.com.biopark.cpa.controller;

import br.com.biopark.cpa.config.validation.ValidacaoException;
import br.com.biopark.cpa.controller.dto.AvaliacaoDTO;
import br.com.biopark.cpa.controller.form.AvaliacaoForm;
import br.com.biopark.cpa.models.Avaliacao;
import br.com.biopark.cpa.models.Usuario;
import br.com.biopark.cpa.service.AvaliacaoService;
import br.com.biopark.cpa.service.PerguntaService;
import br.com.biopark.cpa.service.TurmaService;
import br.com.biopark.cpa.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;

    private final PerguntaService perguntaService;

    private final TurmaService turmaService;

    private final UsuarioService usuarioService;

    public AvaliacaoController(AvaliacaoService avaliacaoService, PerguntaService perguntaService, TurmaService turmaService, UsuarioService usuarioService) {
        this.avaliacaoService = avaliacaoService;
        this.perguntaService = perguntaService;
        this.turmaService = turmaService;
        this.usuarioService = usuarioService;
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

    @PutMapping("/responder-avaliacao/{avaliacao_id}/{usuario_id}")
    public ResponseEntity<AvaliacaoDTO> responderAvaliacao(@PathVariable(name = "avaliacao_id") Long avaliacaoId,
                                                           @PathVariable(name = "usuario_id") Long usuarioId) throws ValidacaoException {

        Avaliacao avaliacao = avaliacaoService.buscarPorId(avaliacaoId);
        Usuario usuario = usuarioService.buscarPorId(usuarioId);
        avaliacao = avaliacaoService.finalizarAvaliacao(avaliacao, usuario);

        return ResponseEntity.ok(new AvaliacaoDTO(avaliacao));
    }
}

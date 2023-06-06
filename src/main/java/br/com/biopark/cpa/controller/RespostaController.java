package br.com.biopark.cpa.controller;

import java.net.URI;

import br.com.biopark.cpa.config.validation.ValidacaoException;
import br.com.biopark.cpa.service.AvaliacaoService;
import br.com.biopark.cpa.service.PerguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.biopark.cpa.controller.dto.RespostaDTO;
import br.com.biopark.cpa.controller.form.RespostaForm;
import br.com.biopark.cpa.models.Resposta;
import br.com.biopark.cpa.repository.PerguntaRepository;
import br.com.biopark.cpa.service.RespostaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/resposta")
@CrossOrigin(origins = { "http://localhost:8080", "http://localhost:3005" })

public class RespostaController {

    private final RespostaService respostaService;

    final
    PerguntaService perguntaService;

    final
    AvaliacaoService avaliacaoService;

    public RespostaController(RespostaService respostaService, PerguntaService perguntaService, AvaliacaoService avaliacaoService) {
        this.respostaService = respostaService;
        this.perguntaService = perguntaService;
        this.avaliacaoService = avaliacaoService;
    }

    @PostMapping
    public ResponseEntity<RespostaDTO> cadastrar(@RequestBody @Valid RespostaForm form, UriComponentsBuilder uriBuilder) throws ValidacaoException {

        Resposta resposta = form.converter(perguntaService, avaliacaoService);

        resposta = respostaService.cadastrar(resposta);

        URI uri = uriBuilder.path("resposta/{id}").buildAndExpand(resposta.getId()).toUri();

        return ResponseEntity.created(uri).body(new RespostaDTO(resposta));
    }

    @GetMapping
    public Iterable<Resposta> listarResposta() {
        return respostaService.listarResposta();
    }
}
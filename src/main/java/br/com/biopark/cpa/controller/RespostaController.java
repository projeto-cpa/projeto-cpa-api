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
@Transactional
public class RespostaController {

    @Autowired
    private RespostaService respostaService;

    @Autowired
    PerguntaRepository perguntaRepository;

    @PostMapping
    public ResponseEntity<RespostaDTO> cadastrar(@RequestBody @Valid RespostaForm form,
            UriComponentsBuilder uriBuilder) {
        Resposta resposta = form.converter(perguntaRepository);
        resposta = respostaService.cadastrar(resposta);
        URI uri = uriBuilder.path("resposta/{id}").buildAndExpand(resposta.getId()).toUri();
        return ResponseEntity.created(uri).body(new RespostaDTO(resposta));
    }

    @GetMapping
    public Iterable<Resposta> listarResposta() {
        return respostaService.listarResposta();
    }
}
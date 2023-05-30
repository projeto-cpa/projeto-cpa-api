package br.com.biopark.cpa.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.biopark.cpa.controller.dto.EixoDTO;
import br.com.biopark.cpa.controller.form.EixoForm;
import br.com.biopark.cpa.models.Eixo;
import br.com.biopark.cpa.service.EixoService;
import jakarta.validation.Valid;

@RequestMapping("/eixo")
@RestController
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:3005"})
public class EixoController {

    @Autowired
    private EixoService eixoService;

    @PostMapping
    public ResponseEntity<EixoDTO> cadastrar(@RequestBody @Valid EixoForm form, UriComponentsBuilder uriBuilder) throws Exception{
        Eixo eixo = new Eixo(form);
        eixoService.cadastrar(eixo);

        URI uri = uriBuilder.path("/eixo/{id}").buildAndExpand(eixo.getId()).toUri();
		return ResponseEntity.created(uri).body(new EixoDTO(eixo));
    }

    @GetMapping
     public Page<EixoDTO> listar(@RequestParam(required = false) String nomeEixo, @RequestParam int pagina,
                                 @RequestParam int qtd) {

        Pageable pageable = PageRequest.of(pagina, qtd);

        if (nomeEixo == null) {
            Page<Eixo> eixos = eixoService.listar(pageable);
            return EixoDTO.converter(eixos);
        } else {
            Page<Eixo> eixos = eixoService.buscarPorNome(nomeEixo, pageable);
            return EixoDTO.converter(eixos);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EixoDTO> atualizar(@PathVariable long id, @RequestBody @Valid EixoForm form) throws Exception {
        Eixo eixo = eixoService.buscarPorId(id);

        eixo = eixoService.atualizar(form.converterParaAtuaizacao(eixo));

        return ResponseEntity.ok(new EixoDTO(eixo));
    }
    
}

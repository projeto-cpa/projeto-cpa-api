package br.com.biopark.cpa.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.biopark.cpa.controller.dto.EixoDTO;
import br.com.biopark.cpa.controller.form.EixoForm;
import br.com.biopark.cpa.models.Eixo;
import br.com.biopark.cpa.service.EixoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RequestMapping("/eixo")
@RestController
public class EixoController {

    @Autowired
    private EixoService eixoService;

    @Transactional
    @PostMapping
    public ResponseEntity<EixoDTO> cadastrar(@RequestBody @Valid EixoForm form, UriComponentsBuilder uriBuilder) throws Exception{

        

        Eixo eixo = new Eixo(form.getNome(), form.getDescricao(), false);
        eixoService.cadastrar(eixo);

        URI uri = uriBuilder.path("/eixo/{id}").buildAndExpand(eixo.getId()).toUri();
		return ResponseEntity.created(uri).body(new EixoDTO(eixo));
    }
    
}

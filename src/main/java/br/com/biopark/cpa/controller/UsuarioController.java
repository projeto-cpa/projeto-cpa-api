package br.com.biopark.cpa.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.biopark.cpa.controller.dto.UsuarioDTO;
import br.com.biopark.cpa.controller.form.UsuarioForm;
import br.com.biopark.cpa.models.Usuario;
import br.com.biopark.cpa.repository.CargoRepository;
import br.com.biopark.cpa.service.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = { "http://localhost:8080", "http://localhost:3005" })
@Transactional
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CargoRepository cargoRepository;

    @PostMapping
    public ResponseEntity<UsuarioDTO> cadastrar(@RequestBody @Valid UsuarioForm form, UriComponentsBuilder uriBuilder) throws Exception {

        Usuario usuario = form.converter(cargoRepository);
        usuarioService.cadastrar(usuario);

        URI uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).body(new UsuarioDTO(usuario));
    }
    
}
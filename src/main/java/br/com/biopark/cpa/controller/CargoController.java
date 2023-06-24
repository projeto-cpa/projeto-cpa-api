package br.com.biopark.cpa.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.biopark.cpa.controller.dto.CargoDTO;
import br.com.biopark.cpa.controller.form.CargoForm;
import br.com.biopark.cpa.controller.form.alteracao.AlterarCargoForm;
import br.com.biopark.cpa.controller.form.ativacao.AtivarCargoForm;
import br.com.biopark.cpa.controller.form.exclusao.excluirCargoForm;
import br.com.biopark.cpa.models.Cargo;
import br.com.biopark.cpa.service.CargoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/cargo")
@Transactional
public class CargoController {

    @Autowired
    private CargoService cargoService;

    @PostMapping
    public ResponseEntity<CargoDTO> cadastrar(@RequestBody @Valid CargoForm form, UriComponentsBuilder uriBuilder) {
        Cargo cargo = new Cargo(form.getNome(), form.getDescricao(), form.getAtivo());
        cargo = cargoService.cadastrar(cargo);
        URI uri = uriBuilder.path("cargo/{id}").buildAndExpand(cargo.getId()).toUri();
        return ResponseEntity.created(uri).body(new CargoDTO(cargo));
    }

    @PutMapping
    public ResponseEntity<CargoDTO> atualizar(@RequestBody @Valid AlterarCargoForm form,
            UriComponentsBuilder uriBuilder) {
        Cargo cargo = cargoService.atualizar(form.getIdCargo(), form.getNome(), form.getDescricao(), form.getAtivo());
        URI uri = uriBuilder.path("cargo/{id}").buildAndExpand(cargo.getId()).toUri();
        return ResponseEntity.created(uri).body(new CargoDTO(cargo));
    }

    @GetMapping
    public Page<CargoDTO> listar(@RequestParam(required = false) String nomeCargo, @RequestParam int pagina,
            @RequestParam int qtd) {
        Pageable paginacao = PageRequest.of(pagina, qtd);
        if (nomeCargo == null) {
            Page<Cargo> cargos = cargoService.listar(paginacao);
            return CargoDTO.converter(cargos);
        } else {
            Page<Cargo> cargos = cargoService.buscaPorNome(nomeCargo, paginacao);
            return CargoDTO.converter(cargos);
        }
    }

    @GetMapping(value="/listar")
    public List<CargoDTO> listarParaCadastroDeCargos(@RequestParam(required = false) String nomeCargo) {
        if (nomeCargo == null) {
            List<Cargo> cargos = cargoService.listarParaCadastroDeCargos();
            return CargoDTO.converterParaLista(cargos);
        } else {
            List<Cargo> cargos = cargoService.buscaPorNomeNaLista(nomeCargo);
            return CargoDTO.converterParaLista(cargos);
        }
    }

    @DeleteMapping
    public ResponseEntity<CargoDTO> excluirCargo(@RequestBody @Valid excluirCargoForm form,
            UriComponentsBuilder uriBuilder) {
        Cargo cargo = cargoService.excluirCargo(form.getIdCargo());
        URI uri = uriBuilder.path("cargo/{id}").buildAndExpand(cargo.getId()).toUri();
        return ResponseEntity.created(uri).body(new CargoDTO(cargo));
    }

    @PutMapping("/ativacao")
    public ResponseEntity<CargoDTO> ativarDesativarCargo(@RequestBody @Valid AtivarCargoForm form,
            UriComponentsBuilder uriBuilder) {
        Cargo cargo = cargoService.ativarDesativarCargo(form.getIdCargo());
        URI uri = uriBuilder.path("cargo/{id}").buildAndExpand(cargo.getId()).toUri();
        return ResponseEntity.created(uri).body(new CargoDTO(cargo));
    }

}
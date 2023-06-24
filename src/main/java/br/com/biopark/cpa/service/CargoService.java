package br.com.biopark.cpa.service;

import java.util.Date;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.biopark.cpa.models.Cargo;
import br.com.biopark.cpa.repository.CargoRepository;

@Service
public class CargoService {

    @Autowired
    private CargoRepository cargoRepository;

    public Cargo cadastrar(Cargo cargo) {

        Cargo cargoCadastrado = new Cargo();

        try {
            cargoCadastrado = cargoRepository.save(cargo);
        } catch (Exception e) {
            System.out.println("Não foi possivel cadastrar o cargo: " + e.getStackTrace());
        }

        return cargoCadastrado;
    }

    public Page<Cargo> listar(Pageable cargos) {
        return cargoRepository.findAll(cargos);
    }

    public Page<Cargo> buscaPorNome(String nomeCargo, Pageable cargos) {
        return cargoRepository.findByNome(nomeCargo, cargos);
    }

    // implemente o motodo de ativar ou desativar um cargo pelo id
    public Cargo ativarDesativarCargo(Long id) {
        Cargo cargo = cargoRepository.findById(id).get();
        Boolean ativo = cargo.getAtivo().equals(true) ? false : true;
        cargo.setAtivo(ativo);
        cargoRepository.save(cargo);
        return cargo;
    }

    public Cargo excluirCargo(Long id) {
        Cargo cargo = cargoRepository.findById(id).get();
        cargoRepository.delete(cargo);
        return cargo;
    }

    public Cargo buscarCargo(Long idCargo) {
        Optional<Cargo> cargo = cargoRepository.findById(idCargo);
        if (cargo.isPresent())
            return cargo.get();

        return null;
    }

    // implemente o metodo para atualizar o cargo
    public Cargo atualizar(Long id, String nome, String descricao, Boolean ativo) {
        Cargo cargo = cargoRepository.findById(id).get();
        cargo.setDataAtualizacao(new Date());
        cargo.setNome(nome);
        cargo.setDescricao(descricao);
        cargo.setAtivo(ativo);
        cargoRepository.save(cargo);
        return cargo;
    }
}

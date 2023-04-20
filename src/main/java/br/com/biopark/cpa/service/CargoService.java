package br.com.biopark.cpa.service;

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
    
    // implemente a listagem de cargos
    public Iterable<Cargo> listarCargos() {
        return cargoRepository.findAll();
    }
    
}

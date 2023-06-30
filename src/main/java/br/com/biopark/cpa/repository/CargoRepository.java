package br.com.biopark.cpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.biopark.cpa.models.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Long>{
    
    public Cargo findById(long id_cargo);
    public Page<Cargo> findByNome(String nomeCargo, Pageable paginacao);
    List<Cargo> findAllByNome(String nomeCargo);
    public Cargo findByAtivo(boolean ativo);
    
}

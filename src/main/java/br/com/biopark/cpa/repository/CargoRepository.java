package br.com.biopark.cpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.biopark.cpa.models.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Long>{
    
    public Cargo findById(long id);
    public Page<Cargo> findByNome(String nomeCargo, Pageable paginacao);
    public Cargo findByAtivo(boolean ativo);
    
}

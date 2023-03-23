package br.com.biopark.cpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.biopark.cpa.models.Cargo;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long>{
    
    public Cargo findById(long id);
    public Cargo findByNome(String nome);
    
}

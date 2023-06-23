package br.com.biopark.cpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.biopark.cpa.models.UsuarioCSV;

@Repository
public interface UsuarioCSVRepository extends JpaRepository<UsuarioCSV, Long>{}
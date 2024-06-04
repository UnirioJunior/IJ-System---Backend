package br.com.ijSystem.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ijSystem.Entities.PacienteEntities;

public interface PacienteRepository extends JpaRepository<PacienteEntities, Long>{

}

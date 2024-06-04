package br.com.ijSystem.Repositories;


import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ijSystem.Entities.UsuarioVerificadorEntity;

public interface UsuarioVerificadorRepository extends JpaRepository<UsuarioVerificadorEntity, Long>{

	public Optional<UsuarioVerificadorEntity> findByUuid(UUID uuid);
}

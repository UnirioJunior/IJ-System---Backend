package br.com.ijSystem.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ijSystem.Entities.AgendamentoEntities;
import br.com.ijSystem.Entities.ConfigWhatsAppEntiti;

public interface ConfigWhatsAppRepositories extends JpaRepository<ConfigWhatsAppEntiti, Long>{
	
	// Busca configuração pelo ID do usuário
    ConfigWhatsAppEntiti findByUsuarioId(Long usuarioId);

}

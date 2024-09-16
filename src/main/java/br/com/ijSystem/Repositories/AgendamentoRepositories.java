package br.com.ijSystem.Repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ijSystem.Entities.AgendamentoEntities;

public interface AgendamentoRepositories extends JpaRepository<AgendamentoEntities, Long>{
	
	// Buscar agendamentos dentro de um intervalo de datas (usando LocalDateTime)
    List<AgendamentoEntities> findByInicioBetween(LocalDateTime inicio, LocalDateTime fim);
}

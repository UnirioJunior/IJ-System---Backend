package br.com.ijSystem.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ijSystem.Entities.AgendamentoEntities;
import br.com.ijSystem.Repositories.AgendamentoRepositories;

@Service
public class AgendamentoService {
	
	@Autowired
	private AgendamentoRepositories agenRepositorie;
	
	public List<AgendamentoEntities> listarTodos(){
		return agenRepositorie.findAll();
	}
	
	public void inserir(AgendamentoEntities javo) {
		agenRepositorie.save(javo);
	}
	
	public void excluir(Long id) {
		agenRepositorie.deleteById(id);
    }
}

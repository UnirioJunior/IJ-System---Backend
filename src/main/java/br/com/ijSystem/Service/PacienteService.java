package br.com.ijSystem.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ijSystem.DTO.PacienteDTO;
import br.com.ijSystem.Entities.PacienteEntities;
import br.com.ijSystem.Repositories.PacienteRepository;

@Service
public class PacienteService {
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	public List<PacienteDTO> listarTodos(){
		List<PacienteEntities> paciente = pacienteRepository.findAll();
		return paciente.stream().map(PacienteDTO::new).toList();
	}
	
	public void inserir(PacienteDTO paciente) {
		PacienteEntities pacienteEntities = new PacienteEntities(paciente);
		pacienteRepository.save(pacienteEntities);
	}

	public PacienteDTO alterar(PacienteDTO paciente) {
		PacienteEntities pacienteEntities = new PacienteEntities(paciente);
		return new PacienteDTO(pacienteRepository.save(pacienteEntities));
	}
	
	public void excluir(Long id) {
		PacienteEntities usuario = pacienteRepository.findById(id).get();
		pacienteRepository.delete(usuario);
	}
	
	public PacienteDTO buscarPorId(Long id) {
		return new PacienteDTO(pacienteRepository.findById(id).get());
	}
	
}

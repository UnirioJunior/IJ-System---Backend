package br.com.ijSystem.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ijSystem.DTO.AnamneseDTO;
import br.com.ijSystem.DTO.PacienteDTO;
import br.com.ijSystem.Entities.AnamneseEntities;
import br.com.ijSystem.Entities.PacienteEntities;
import br.com.ijSystem.Repositories.AnamneseRepositories;
import br.com.ijSystem.Repositories.PacienteRepository;

@Service
public class AnamneseService {
	
	@Autowired
	private AnamneseRepositories anamneseRepository;
	
	@Autowired
    private PacienteRepository pacienteRepository;
	
	public List<AnamneseDTO> listarTodos(){
		List<AnamneseEntities> anamnese = anamneseRepository.findAll();
		return anamnese.stream().map(AnamneseDTO::new).toList();
	}
	
	public void inserir(AnamneseDTO anamnese) {
		AnamneseEntities anamneseEntities = new AnamneseEntities(anamnese);
		if (anamnese.getPacienteDTO() != null && anamnese.getPacienteDTO().getId() != null) {
            PacienteEntities paciente = pacienteRepository.findById(anamnese.getPacienteDTO().getId()).orElse(null);
            if (paciente != null) {
                anamneseEntities.setPaciente(paciente);
            }
        }
		anamneseRepository.save(anamneseEntities);
	}
	
	public AnamneseDTO alterar(AnamneseDTO anamnese) {
		AnamneseEntities anamneseEntities = new AnamneseEntities(anamnese);
		return new AnamneseDTO(anamneseRepository.save(anamneseEntities));
	}
	
	public void excluir(Long id) {
		AnamneseEntities anamnese = anamneseRepository.findById(id).get();
		anamneseRepository.delete(anamnese);
	}
	
	public AnamneseDTO buscarPorId(Long id) {
		return new AnamneseDTO(anamneseRepository.findById(id).get());
	}
}

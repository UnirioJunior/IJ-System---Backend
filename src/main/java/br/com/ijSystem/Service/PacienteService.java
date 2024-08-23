package br.com.ijSystem.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ijSystem.DTO.PacienteDTO;
import br.com.ijSystem.Entities.PacienteEntities;
import br.com.ijSystem.Entities.UserEntities;
import br.com.ijSystem.Repositories.PacienteRepository;
import br.com.ijSystem.Repositories.UserRepository;

@Service
public class PacienteService {
    
    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private UserRepository userRepository; // Adicione o repositório de UserEntities
    
    public List<PacienteDTO> listarTodos() {
        List<PacienteEntities> pacientes = pacienteRepository.findAll();
        return pacientes.stream().map(PacienteDTO::new).toList();
    }
    
    public void inserir(PacienteDTO pacienteDTO) {
        // Buscar o UserEntities no banco de dados
        UserEntities usuario = userRepository.findById(pacienteDTO.getUsuario().getId())
                              .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        
        // Criar o PacienteEntities e associar o usuario
        PacienteEntities pacienteEntities = new PacienteEntities(pacienteDTO);
        pacienteEntities.setUsuario(usuario);
        
        pacienteRepository.save(pacienteEntities);
    }

    public PacienteDTO alterar(PacienteDTO pacienteDTO) {
        // Buscar o UserEntities no banco de dados
        UserEntities usuario = userRepository.findById(pacienteDTO.getUsuario().getId())
                              .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        
        // Criar o PacienteEntities e associar o usuario
        PacienteEntities pacienteEntities = new PacienteEntities(pacienteDTO);
        pacienteEntities.setUsuario(usuario);
        
        return new PacienteDTO(pacienteRepository.save(pacienteEntities));
    }
    
    public void excluir(Long id) {
        PacienteEntities paciente = pacienteRepository.findById(id)
                               .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
        pacienteRepository.delete(paciente);
    }
    
    public PacienteDTO buscarPorId(Long id) {
        return new PacienteDTO(pacienteRepository.findById(id)
                               .orElseThrow(() -> new RuntimeException("Paciente não encontrado")));
    }
}


package br.com.ijSystem.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ijSystem.DTO.PacienteDTO;
import br.com.ijSystem.Service.PacienteService;

@RestController
@RequestMapping(value = "/paciente")
@CrossOrigin
public class PacienteController {
	
	@Autowired
	private PacienteService pacienteService;
	
	@GetMapping
	public List<PacienteDTO> listarTodos() {
		return pacienteService.listarTodos();
	}
	
	@PostMapping
	public void  inserir(@RequestBody PacienteDTO paciente) {
		pacienteService.inserir(paciente);
	}
	
	@PutMapping
	public PacienteDTO alterar(@RequestBody PacienteDTO paciente) {
		return pacienteService.alterar(paciente);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable("id") Long id){
		pacienteService.excluir(id);
		return ResponseEntity.ok().build();
	}
}

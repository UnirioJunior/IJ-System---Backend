package br.com.ijSystem.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import br.com.ijSystem.Entities.AgendamentoEntities;
import br.com.ijSystem.Service.AgendamentoService;

@RestController
@RequestMapping(value = "/agendamento")
@CrossOrigin
public class AgendamentoController {
	
	@Autowired
	private AgendamentoService agenService;
	
	@GetMapping
	public List<AgendamentoEntities> listarTodos() {
		return agenService.listarTodos();
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<AgendamentoEntities> buscarPorId(@PathVariable Long id) {
        AgendamentoEntities agendamento = agenService.buscarPorId(id);
        return ResponseEntity.ok(agendamento);
    }

	@PostMapping
	public void  inserir(@RequestBody AgendamentoEntities paciente) {
		agenService.inserir(paciente);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable("id") Long id){
		agenService.excluir(id);
		return ResponseEntity.ok().build();
	}
	
}
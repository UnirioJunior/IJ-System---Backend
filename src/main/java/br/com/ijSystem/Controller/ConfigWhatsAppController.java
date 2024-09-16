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
import br.com.ijSystem.Entities.ConfigWhatsAppEntiti;
import br.com.ijSystem.Service.AgendamentoService;
import br.com.ijSystem.Service.ConfigWhatsAppService;

@RestController
@RequestMapping(value = "/whatsapp")
@CrossOrigin
public class ConfigWhatsAppController {
	
	@Autowired
	private ConfigWhatsAppService whats;
	
	@GetMapping
	public List<ConfigWhatsAppEntiti> listarTodos() {
		return whats.listarTodos();
	}
	
//	@GetMapping("/{id}")
//    public ResponseEntity<ConfigWhatsAppEntiti> buscarPorId(@PathVariable Long id) {
//        AgendamentoEntities agendamento = whats.buscarPorId(id);
//        return ResponseEntity.ok(agendamento);
//    }

	@PostMapping
	public void  inserir(@RequestBody ConfigWhatsAppEntiti paciente) {
		whats.inserir(paciente);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable("id") Long id){
		whats.excluir(id);
		return ResponseEntity.ok().build();
	}
	
}
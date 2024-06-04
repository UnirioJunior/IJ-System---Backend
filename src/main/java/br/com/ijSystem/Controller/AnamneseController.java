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

import br.com.ijSystem.DTO.AnamneseDTO;
import br.com.ijSystem.Service.AnamneseService;
@RestController
@RequestMapping(value = "/anamnese")
@CrossOrigin
public class AnamneseController {
	
	@Autowired
	private AnamneseService anamneseService;
	
	@GetMapping
	public List<AnamneseDTO> listarTodos(){
		return anamneseService.listarTodos();
	}
	
	@PostMapping
	public void inserir (@RequestBody AnamneseDTO anamnese) {
		anamneseService.inserir(anamnese);
	}
	
	@PutMapping
	public AnamneseDTO alterar(@RequestBody AnamneseDTO anamnese) {
		return anamneseService.alterar(anamnese);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable("id") Long id){
		anamneseService.excluir(id);
		return ResponseEntity.ok().build();
	}
	
}

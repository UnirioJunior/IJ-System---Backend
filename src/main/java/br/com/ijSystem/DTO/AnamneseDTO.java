package br.com.ijSystem.DTO;

import org.springframework.beans.BeanUtils;

import br.com.ijSystem.Entities.AnamneseEntities;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnamneseDTO {
	
	private Long id;
	private String descricao;
	private PacienteDTO pacienteDTO;
	
	
	public AnamneseDTO(AnamneseEntities anamnese) {
		BeanUtils.copyProperties(anamnese, this);
	}
	public AnamneseDTO () {
		
	}
}

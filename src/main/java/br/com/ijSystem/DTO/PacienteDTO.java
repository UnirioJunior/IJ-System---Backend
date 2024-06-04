package br.com.ijSystem.DTO;


import org.springframework.beans.BeanUtils;

import br.com.ijSystem.Entities.PacienteEntities;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class PacienteDTO {
	
	private Long id;
	private String name;
	
	public PacienteDTO(PacienteEntities paciente) {
		BeanUtils.copyProperties(paciente, this);
	}
	
	public PacienteDTO() {
		
	}

}

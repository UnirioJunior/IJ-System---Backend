package br.com.ijSystem.DTO;


import org.springframework.beans.BeanUtils;

import br.com.ijSystem.Entities.PacienteEntities;

public class PacienteDTO {
	
	private Long id;
	private String name;
	
	public PacienteDTO(PacienteEntities paciente) {
		BeanUtils.copyProperties(paciente, this);
	}
	
	public PacienteDTO() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}

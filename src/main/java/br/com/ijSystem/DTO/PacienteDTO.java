package br.com.ijSystem.DTO;


import org.springframework.beans.BeanUtils;

import br.com.ijSystem.Entities.PacienteEntities;

public class PacienteDTO {
    
    private Long id;
    private String name;
    private UserDTO usuario;
    
    public PacienteDTO(PacienteEntities paciente) {
        BeanUtils.copyProperties(paciente, this);
        // Copiando manualmente o campo usuario se não for nulo
        if (paciente.getUsuario() != null) {
            this.usuario = new UserDTO(paciente.getUsuario());
        }
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

	public UserDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UserDTO usuario) {
		this.usuario = usuario;
	}
	
}

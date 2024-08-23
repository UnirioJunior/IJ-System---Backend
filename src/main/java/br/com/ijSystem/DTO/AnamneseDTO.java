package br.com.ijSystem.DTO;

import java.util.Objects;


import br.com.ijSystem.Entities.AnamneseEntities;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnamneseDTO {
	
	private Long id;
	private String descricao;
	private PacienteDTO paciente;
	private UserDTO usuario;
	private String data;
	
	
	public AnamneseDTO(AnamneseEntities anamnese) {
        this.id = anamnese.getId();
        this.descricao = anamnese.getDescricao();
        
        if (anamnese.getPaciente() != null) {
            this.paciente = new PacienteDTO(anamnese.getPaciente());
        }
        
        if (anamnese.getUsuario() != null) {
            this.usuario = new UserDTO(anamnese.getUsuario());
        }
        
        // Atribuir o valor de data
        this.data = anamnese.getData();
    }
	
	public AnamneseDTO () {
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public PacienteDTO getPaciente() {
		return paciente;
	}
	public void setPaciente(PacienteDTO pacienteDTO) {
		this.paciente = pacienteDTO;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public UserDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UserDTO usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnamneseDTO other = (AnamneseDTO) obj;
		return Objects.equals(id, other.id);
	}
	
	
}

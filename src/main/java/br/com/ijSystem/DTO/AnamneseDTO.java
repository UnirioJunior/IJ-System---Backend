package br.com.ijSystem.DTO;

import java.util.Objects;

import org.springframework.beans.BeanUtils;

import br.com.ijSystem.Entities.AnamneseEntities;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnamneseDTO {
	
	private Long id;
	private String descricao;
	private PacienteDTO paciente;
	
	
	public AnamneseDTO(AnamneseEntities anamnese) {
		BeanUtils.copyProperties(anamnese, this);
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

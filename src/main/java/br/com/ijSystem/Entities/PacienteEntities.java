package br.com.ijSystem.Entities;

import java.util.Objects;

import org.springframework.beans.BeanUtils;

import br.com.ijSystem.DTO.PacienteDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_PACIENTE")
public class PacienteEntities {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "paciente_id")
	private Long id;
	
	@Column(nullable = false, name = "paciente_name")
	private String name;
	
	public PacienteEntities(PacienteDTO paciente) {
		BeanUtils.copyProperties(paciente, this);
	}
	
	public PacienteEntities() {
		
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
		PacienteEntities other = (PacienteEntities) obj;
		return Objects.equals(id, other.id);
	}

	
	
	
}

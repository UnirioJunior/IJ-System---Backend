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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TB_PACIENTE")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
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

	
	
	
}

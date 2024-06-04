package br.com.ijSystem.Entities;


import org.springframework.beans.BeanUtils;

import br.com.ijSystem.DTO.AnamneseDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "TB_ANAMNESE")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class AnamneseEntities {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, name = "anamnese_descricao", columnDefinition = "TEXT")
	private String descricao;
	
	@ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private PacienteEntities paciente;
	
	public AnamneseEntities () {
		
	}
	
	public AnamneseEntities(AnamneseDTO anamnese) {
		BeanUtils.copyProperties(anamnese, this);
		if (anamnese.getPacienteDTO() != null) {
            this.paciente = new PacienteEntities();
            this.paciente.setId(anamnese.getPacienteDTO().getId());
            this.paciente.setName(anamnese.getPacienteDTO().getName());
        }
	}
	
	
}

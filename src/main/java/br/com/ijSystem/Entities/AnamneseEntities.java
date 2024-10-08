package br.com.ijSystem.Entities;


import java.util.Objects;

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

@Entity
@Table (name = "TB_ANAMNESE")
public class AnamneseEntities {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, name = "anamnese_descricao", columnDefinition = "TEXT")
	private String descricao;
	@ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private PacienteEntities paciente;
	@ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false) 
    private UserEntities usuario;
	@Column(nullable = false)
	private String data;
	
	public AnamneseEntities () {
		
	}
	
	public AnamneseEntities(AnamneseDTO anamnese) {
	    BeanUtils.copyProperties(anamnese, this);
	    if (anamnese.getPaciente() != null) {
	        this.paciente = new PacienteEntities();
	        this.paciente.setId(anamnese.getPaciente().getId());
	    }
	    if (anamnese.getUsuario() != null) {
	        this.usuario = new UserEntities();
	        this.usuario.setId(anamnese.getUsuario().getId());
	    }
	}
	

	public UserEntities getUsuario() {
		return usuario;
	}

	public void setUsuario(UserEntities usuario) {
		this.usuario = usuario;
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

	public PacienteEntities getPaciente() {
		return paciente;
	}

	public void setPaciente(PacienteEntities paciente) {
		this.paciente = paciente;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
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
		AnamneseEntities other = (AnamneseEntities) obj;
		return Objects.equals(id, other.id);
	}
	
	
}

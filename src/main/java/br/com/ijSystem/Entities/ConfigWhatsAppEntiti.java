package br.com.ijSystem.Entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "CONFIG_WHATSAPP")
public class ConfigWhatsAppEntiti {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false) 
    private UserEntities usuario;
	
	@Column(nullable = false)
	private String sessionId;

	
	@Column(nullable = false)
	private String numUser;
	
	@Column(nullable = true)
	private Boolean isAtivo;
	
	@Column(nullable = false)
	private String mensagemPaciente;
	
	@Column(nullable = false)
	private String mensagemNumUser;
	
	public ConfigWhatsAppEntiti() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserEntities getUsuario() {
		return usuario;
	}

	public void setUsuario(UserEntities usuario) {
		this.usuario = usuario;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getMensagemPaciente() {
		return mensagemPaciente;
	}

	public void setMensagemPaciente(String mensagemPaciente) {
		this.mensagemPaciente = mensagemPaciente;
	}

	public String getMensagemNumUser() {
		return mensagemNumUser;
	}

	public void setMensagemNumUser(String mensagemNumUser) {
		this.mensagemNumUser = mensagemNumUser;
	}

	public String getNumUser() {
		return numUser;
	}

	public void setNumUser(String numUser) {
		this.numUser = numUser;
	}

	public Boolean getIsAtivo() {
		return isAtivo;
	}

	public void setIsAtivo(Boolean isAtivo) {
		this.isAtivo = isAtivo;
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
		ConfigWhatsAppEntiti other = (ConfigWhatsAppEntiti) obj;
		return Objects.equals(id, other.id);
	}
	
	
}

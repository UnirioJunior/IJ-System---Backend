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
@Table(name = "TB_AGENDAMENTO")
public class AgendamentoEntities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "agendamento_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false) 
    private UserEntities usuario;

    @Column(nullable = false, name = "paciente_nome")
    private String nomePaciente;

    @Column(nullable = false, name = "paciente_telefone")
    private String telefone;

    @Column(nullable = false, name = "paciente_data_nascimento")
    private String dataNascimento;

    @Column(nullable = false, name = "paciente_tipo_atendimento")
    private String tipoAtendimento;

    @Column(nullable = false, name = "agendamento_inicio")
    private String inicio;

    @Column(nullable = false, name = "agendamento_fim")
    private String fim;

    
    public AgendamentoEntities() {
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



	public String getNomePaciente() {
		return nomePaciente;
	}



	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}



	public String getTelefone() {
		return telefone;
	}



	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}



	public String getDataNascimento() {
		return dataNascimento;
	}



	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}



	public String getTipoAtendimento() {
		return tipoAtendimento;
	}



	public void setTipoAtendimento(String tipoAtendimento) {
		this.tipoAtendimento = tipoAtendimento;
	}



	public String getInicio() {
		return inicio;
	}



	public void setInicio(String inicio) {
		this.inicio = inicio;
	}



	public String getFim() {
		return fim;
	}



	public void setFim(String fim) {
		this.fim = fim;
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
		AgendamentoEntities other = (AgendamentoEntities) obj;
		return Objects.equals(id, other.id);
	}
	
}

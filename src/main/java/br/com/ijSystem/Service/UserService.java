package br.com.ijSystem.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.ijSystem.DTO.UserDTO;
import br.com.ijSystem.Entities.UserEntities;
import br.com.ijSystem.Entities.UsuarioVerificadorEntity;
import br.com.ijSystem.Entities.enums.TipoSituacaoUsuario;
import br.com.ijSystem.Repositories.UserRepository;
import br.com.ijSystem.Repositories.UsuarioVerificadorRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository usuarioRepository;
	
	@Autowired
	private UsuarioVerificadorRepository usuarioVerificadorRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private EmailService emailService;
	
	public List<UserDTO> listarTodos(){
		List<UserEntities> usuarios = usuarioRepository.findAll();
		return usuarios.stream().map(UserDTO::new).toList();
	}
	
	public void inserir(UserDTO usuario) {
		UserEntities usuarioEntity = new UserEntities(usuario);
		usuarioEntity.setSenha(passwordEncoder.encode(usuario.getSenha()));
		usuarioRepository.save(usuarioEntity);
	}
	
	public void inserirNovoUsuario(UserDTO usuario) {
		UserEntities usuarioEntity = new UserEntities(usuario);
		usuarioEntity.setSenha(passwordEncoder.encode(usuario.getSenha()));
		usuarioEntity.setSituacao(TipoSituacaoUsuario.PENDENTE);
		usuarioEntity.setId(null);
		usuarioRepository.save(usuarioEntity);
		
		UsuarioVerificadorEntity verificador = new UsuarioVerificadorEntity();
		verificador.setUsuario(usuarioEntity);
		verificador.setUuid(UUID.randomUUID());
		verificador.setDataExpiracao(Instant.now().plusMillis(900000));
		usuarioVerificadorRepository.save(verificador);
		
		//TODO - Enviar um email para verificar a conta
		emailService.enviarEmailTexto(usuario.getEmail(), 
				"Novo usuário cadastrado", 
				"Você está recebendo um email de cadastro o número para validação é " + verificador.getUuid());
		
	}
	
	public String verificarCadastro(String uuid) {
	
		UsuarioVerificadorEntity usuarioVerificacao = usuarioVerificadorRepository.findByUuid(UUID.fromString(uuid)).get();
		
		if(usuarioVerificacao != null) {
			if(usuarioVerificacao.getDataExpiracao().compareTo(Instant.now()) >= 0) {
				
				UserEntities u = usuarioVerificacao.getUsuario();
				u.setSituacao(TipoSituacaoUsuario.ATIVO);
				
				usuarioRepository.save(u);
				
				return "Usuário Verificado";
			}else {
				usuarioVerificadorRepository.delete(usuarioVerificacao);
				return "Tempo de verificação expirado";
			}
		}else {
			return "Usuario não verificado";
		}
		
	}
	
	public UserDTO alterar(UserDTO usuario) {
		UserEntities usuarioEntity = new UserEntities(usuario);
		usuarioEntity.setSenha(passwordEncoder.encode(usuario.getSenha()));
		return new UserDTO(usuarioRepository.save(usuarioEntity));
	}
	
	public void excluir(Long id) {
		UserEntities usuario = usuarioRepository.findById(id).get();
		usuarioRepository.delete(usuario);
	}
	
	public UserDTO buscarPorId(Long id) {
		return new UserDTO(usuarioRepository.findById(id).get());
	}
}

package br.com.ijSystem.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ijSystem.Entities.AgendamentoEntities;
import br.com.ijSystem.Entities.ConfigWhatsAppEntiti;
import br.com.ijSystem.Repositories.AgendamentoRepositories;
import br.com.ijSystem.Repositories.ConfigWhatsAppRepositories;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepositories agendamentoRepository;

    @Autowired
    private ConfigWhatsAppRepositories configWhatsAppRepositories;

    @Autowired
    private ConfigWhatsAppService configWhatsAppService;

    public void verificarAgendamentosEEnviarMensagens() {
        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime intervaloInicio = agora.minusSeconds(10);
        LocalDateTime intervaloFim = agora.plusSeconds(10);

        System.out.println("Verificando agendamentos entre " + intervaloInicio + " e " + intervaloFim);

        // Verificar agendamentos para enviar mensagem para o paciente
        List<AgendamentoEntities> agendamentosPaciente = agendamentoRepository
            .findByInicioBetween(intervaloInicio, intervaloFim);
        System.out.println("Encontrados " + agendamentosPaciente.size() + " agendamentos para pacientes.");

        for (AgendamentoEntities agendamento : agendamentosPaciente) {
            ConfigWhatsAppEntiti config = configWhatsAppRepositories.findByUsuarioId(agendamento.getUsuario().getId());
            if (config != null) {
                System.out.println("Enviando mensagem para paciente: " + agendamento.getNomePaciente());
                try {
                    configWhatsAppService.enviarMensagemParaPaciente(agendamento, config);
                } catch (Exception e) {
                    System.err.println("Erro ao enviar mensagem para o paciente: " + e.getMessage());
                }
            } else {
                System.out.println("Configuração WhatsApp não encontrada para o usuário: " + agendamento.getUsuario().getId());
            }
        }

        // Verificar agendamentos para enviar mensagem para o numUser
        List<AgendamentoEntities> agendamentosNumUser = agendamentoRepository
            .findByInicioBetween(intervaloInicio, intervaloFim);
        System.out.println("Encontrados " + agendamentosNumUser.size() + " agendamentos para numUser.");

        for (AgendamentoEntities agendamento : agendamentosNumUser) {
            ConfigWhatsAppEntiti config = configWhatsAppRepositories.findByUsuarioId(agendamento.getUsuario().getId());
            if (config != null) {
                System.out.println("Enviando mensagem para numUser: " + agendamento.getUsuario().getId());
                try {
                    configWhatsAppService.enviarMensagemParaNumUser(agendamento, config);
                } catch (Exception e) {
                    System.err.println("Erro ao enviar mensagem para o numUser: " + e.getMessage());
                }
            } else {
                System.out.println("Configuração WhatsApp não encontrada para o usuário: " + agendamento.getUsuario().getId());
            }
        }
    }

    public List<AgendamentoEntities> listarTodos() {
        return agendamentoRepository.findAll();
    }

    public AgendamentoEntities buscarPorId(Long id) {
        return agendamentoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Agendamento não encontrado com o id: " + id));
    }

    public void inserir(AgendamentoEntities javo) {
        agendamentoRepository.save(javo);
    }

    public void excluir(Long id) {
        agendamentoRepository.deleteById(id);
    }
}


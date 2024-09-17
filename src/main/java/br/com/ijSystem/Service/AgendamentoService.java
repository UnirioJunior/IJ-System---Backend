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

        // Verificação para o paciente (24 horas antes)
        LocalDateTime intervaloInicioPaciente = agora.minusMinutes(1); // Pode ajustar a margem para 1 ou mais minutos
        LocalDateTime intervaloFimPaciente = agora.plusMinutes(1);     // Tolerância de 1 minuto para encontrar o agendamento

        System.out.println("Verificando agendamentos para pacientes entre " + intervaloInicioPaciente + " e " + intervaloFimPaciente);

        List<AgendamentoEntities> agendamentosPaciente = agendamentoRepository
            .findByInicioBetween(intervaloInicioPaciente.minusHours(24), intervaloFimPaciente.minusHours(24)); // 24 horas antes

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

        // Verificação para o numUser (1 hora antes)
        LocalDateTime intervaloInicioNumUser = agora.minusMinutes(1); // Pode ajustar a margem para 1 ou mais minutos
        LocalDateTime intervaloFimNumUser = agora.plusMinutes(1);     // Tolerância de 1 minuto para encontrar o agendamento

        System.out.println("Verificando agendamentos para numUser entre " + intervaloInicioNumUser + " e " + intervaloFimNumUser);

        List<AgendamentoEntities> agendamentosNumUser = agendamentoRepository
            .findByInicioBetween(intervaloInicioNumUser.minusHours(1), intervaloFimNumUser.minusHours(1)); // 1 hora antes

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


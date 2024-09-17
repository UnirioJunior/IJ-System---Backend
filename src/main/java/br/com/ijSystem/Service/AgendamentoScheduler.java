package br.com.ijSystem.Service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoScheduler {

    private final AgendamentoService agendamentoService;

    public AgendamentoScheduler(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @Scheduled(fixedRate = 900000)  // 900000 ms = 15 minutos
    public void verificarAgendamentos() {
        agendamentoService.verificarAgendamentosEEnviarMensagens();
    }

}

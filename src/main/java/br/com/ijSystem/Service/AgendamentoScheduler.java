package br.com.ijSystem.Service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoScheduler {

    private final AgendamentoService agendamentoService;

    public AgendamentoScheduler(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    // Executa a verificação a cada 15 minutos //
    
    @Scheduled(fixedRate = 5000)  //  900000
    public void verificarAgendamentos() {
        agendamentoService.verificarAgendamentosEEnviarMensagens();
    }
}

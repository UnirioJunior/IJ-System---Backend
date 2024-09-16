package br.com.ijSystem.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.ijSystem.Entities.AgendamentoEntities;
import br.com.ijSystem.Entities.ConfigWhatsAppEntiti;
import br.com.ijSystem.Repositories.AgendamentoRepositories;
import br.com.ijSystem.Repositories.ConfigWhatsAppRepositories;

@Service
public class ConfigWhatsAppService {

private final RestTemplate restTemplate;
    
    @Autowired
    private ConfigWhatsAppRepositories whats;

    public ConfigWhatsAppService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Método para enviar mensagem para o paciente
    public void enviarMensagemParaPaciente(AgendamentoEntities agendamento, ConfigWhatsAppEntiti config) throws Exception {
        String apiUrl = "http://localhost:3001/client/sendMessage/" + config.getSessionId();
        String chatId = formatarNumeroParaChatId(agendamento.getTelefone());

        // Mensagem base com placeholders
        String mensagemBase = config.getMensagemPaciente() != null ? config.getMensagemPaciente() :
            "Olá, [nomePaciente]. Esta é uma mensagem automática, estou passando aqui para lembrar que você tem um agendamento marcado dia [inicio].";

        // Substituir placeholders pelos valores reais do agendamento
        String mensagemPersonalizada = substituirVariaveisNaMensagem(mensagemBase, agendamento);

        String payload = String.format(
            "{\"chatId\": \"%s\", \"contentType\": \"string\", \"content\": \"%s\"}",
            chatId, mensagemPersonalizada
        );

        enviarRequisicao(apiUrl, payload);
    }

    // Método para enviar mensagem para o numUser
    public void enviarMensagemParaNumUser(AgendamentoEntities agendamento, ConfigWhatsAppEntiti config) throws Exception {
        String apiUrl = "http://localhost:3001/client/sendMessage/" + config.getSessionId();
        String chatId = formatarNumeroParaChatId(config.getNumUser());

        // Mensagem base com placeholders
        String mensagemBase = config.getMensagemNumUser() != null ? config.getMensagemNumUser() : "[mensagem] [nomePaciente] [inicio]";

        // Substituir placeholders pelos valores reais do agendamento e da mensagem do numUser
        String mensagemPersonalizada = substituirVariaveisNaMensagem(mensagemBase, agendamento);

        String payload = String.format(
            "{\"chatId\": \"%s\", \"contentType\": \"string\", \"content\": \"%s\"}",
            chatId, mensagemPersonalizada
        );

        enviarRequisicao(apiUrl, payload);
    }

    // Método que substitui as variáveis na mensagem
    private String substituirVariaveisNaMensagem(String mensagemBase, AgendamentoEntities agendamento) {
        // Substituir o nome do paciente
        String mensagem = mensagemBase.replace("[nomePaciente]", agendamento.getNomePaciente());

        // Substituir a data do agendamento (início)
        mensagem = mensagem.replace("[inicio]", agendamento.getInicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));

        // Se houver a variável [mensagem], substituí-la
        if (mensagem.contains("[mensagem]")) {
            mensagem = mensagem.replace("[mensagem]", "Lembrete importante:"); // Exemplo de mensagem padrão para o numUser
        }

        return mensagem;
    }

    private void enviarRequisicao(String apiUrl, String payload) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-api-key", "masterkey");

        HttpEntity<String> request = new HttpEntity<>(payload, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                apiUrl, HttpMethod.POST, request, String.class
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                System.out.println("Mensagem enviada com sucesso: " + response.getBody());
            } else {
                throw new Exception("Falha ao enviar a mensagem. Status: " + response.getStatusCode());
            }
        } catch (Exception e) {
            throw new Exception("Erro ao enviar mensagem: " + e.getMessage(), e);
        }
    }

    private String formatarNumeroParaChatId(String numero) {
        // Remove caracteres especiais
        numero = numero.replaceAll("[^\\d]", ""); // Remove tudo que não é dígito

        // Se o número tiver 11 dígitos e o terceiro for "9", remove o nono dígito
        if (numero.length() == 11 && numero.startsWith("9", 2)) {
            numero = numero.substring(0, 2) + numero.substring(3);
        }

        // Adiciona o código do país e sufixo para WhatsApp
        return "55" + numero + "@c.us";
    }
    
    public List<ConfigWhatsAppEntiti> listarTodos(){
        return whats.findAll();
    }

    public ConfigWhatsAppEntiti buscarPorId(Long id) {
        return whats.findById(id)
            .orElseThrow(() -> new RuntimeException("Configuração não encontrada com o id: " + id));
    }

    public void inserir(ConfigWhatsAppEntiti javo) {
        whats.save(javo);
    }

    public void excluir(Long id) {
        whats.deleteById(id);
    }
}


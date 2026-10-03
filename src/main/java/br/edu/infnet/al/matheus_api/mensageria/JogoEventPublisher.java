package br.edu.infnet.al.matheus_api.mensageria;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class JogoEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public JogoEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publicarJogoCadastrado(String titulo) {
        try {
            String mensagem = "Novo jogo adicionado ao catálogo: " + titulo;
            rabbitTemplate.convertAndSend(RabbitMQConfig.FILA_JOGO_CADASTRADO, mensagem);
            System.out.println(">>> [RABBITMQ - MATHEUS API] Evento publicado na fila para o jogo: " + titulo);
        } catch (Exception e) {
            System.err.println(">>> [AVISO] Falha ao publicar evento no RabbitMQ: " + e.getMessage());
        }
    }
}
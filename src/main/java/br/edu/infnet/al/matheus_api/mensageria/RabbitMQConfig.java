package br.edu.infnet.al.matheus_api.mensageria;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String FILA_JOGO_CADASTRADO = "jogo.cadastrado.queue";

    @Bean
    public Queue filaJogoCadastrado() {
        return new Queue(FILA_JOGO_CADASTRADO, true);
    }
}
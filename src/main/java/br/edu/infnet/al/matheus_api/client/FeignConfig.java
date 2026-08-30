package br.edu.infnet.al.matheus_api.client;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public RequestInterceptor userAgentInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("User-Agent", "MatheusAPI/1.0 (aluno@infnet.edu.br)");
        };
    }
}